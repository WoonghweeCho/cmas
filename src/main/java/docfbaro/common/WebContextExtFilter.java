package docfbaro.common;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dwenc.cmas.common.accessLog.service.AccessLog;
import com.dwenc.cmas.common.metaData.service.support.DataSourcePool;
import com.dwenc.cmas.common.utils.ConfigUtil;

import docfbaro.iam.UserInfo;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 :
 * 프로그램 : WebContextFilter
 * 설    명 : 요청이 들어올 때마다 WebContext에 request, response 등을 세팅해주는 서블릿 필터 보통 이 필터는 다른 필터보다 먼저 적용되도록 설정할 것을 권장한다.
 * 작 성 자 :
 * 작성일자 :
 * 수정이력
 * ---------------------------------------------------------------
 * 수정일          이  름    사유
 * ---------------------------------------------------------------
 *
 * ---------------------------------------------------------------
 * </pre>
 * @version 1.0
 */
public class WebContextExtFilter implements Filter {

    /**
     * log 처리를 위한 변수 선언
     */
    private static final Logger logger = LoggerFactory.getLogger(WebContextFilter.class);

    ServletContext servletContext;

    protected Set<String> urlExtends = null;

    protected Set<String> includeUrls = null;

    protected FilterConfig config = null;

    public void init(FilterConfig aConfig) throws ServletException{
        config = aConfig;
        servletContext = config.getServletContext();
        loadExtendConfig();
    }

    private void loadExtendConfig(){
        String strUrlExtends = config.getInitParameter("urlExtends");
        if(strUrlExtends != null){
            String arryUrlExtends[] = strUrlExtends.trim().split(",");
            List<String> urlExtendsList = Arrays.asList(arryUrlExtends);
            urlExtends = new HashSet<String>(urlExtendsList);
        }

        String strIncludeUrls = config.getInitParameter("includeUrls");
        if(strIncludeUrls != null){
            String arryIncludeUrls[] = strIncludeUrls.trim().split(",");
            List<String> includeUrlList = Arrays.asList(arryIncludeUrls);
            includeUrls = new HashSet<String>(includeUrlList);
        }
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException{

        HttpServletRequest req = (HttpServletRequest)request;
        logger.info("HttpServletRequest.getRequestURL() : " + req.getRequestURL());
        HttpServletResponse res = (HttpServletResponse)response;
        boolean bChk = false;

        if(urlExtends == null && includeUrls == null){
            // 별도 필터파라미터가 web.xml에 정의되지 않았다면 <filter-mapping>에서 이미 필터링하는 것으로 간주한다
            bChk = true;
        }else if(urlExtends != null){
            Iterator<String> it = urlExtends.iterator();
            while(!bChk && it.hasNext()){
                String tmpJob = (String)it.next();
                if(req.getRequestURI().endsWith(tmpJob)){
                    bChk = true;
                }
            }
        }else if(includeUrls != null){
            Iterator<String> it = includeUrls.iterator();
            while(!bChk && it.hasNext()){
                String tmpJob = (String)it.next();
                if(req.getRequestURI().indexOf(tmpJob) > -1){
                    bChk = true;
                }
            }
        }

        if(bChk){
            WebContext.setServletContext(servletContext);
            WebContext.setRequest(req);
            WebContext.setResponse(res);
            logger.debug("WebContext Request url => " + req.getRequestURI());
            WebContext.getDocRoot();
            if(req.getHeader("referer") != null && req.getHeader("referer").indexOf("selectMenuCd") > 0){
                String selectMenuCd = req.getParameter("selectMenuCd");
                UserInfo.getUserInfo().setMenuCd(selectMenuCd);
            }
            insertAccess(req);
        }
        chain.doFilter(request, response);
    }

    private Connection conn = null;

    private Properties appProperties;

    // 인가 정보 입력
    public void insertAccess(HttpServletRequest request){
        String menuId = StringUtil.getText(WebContext.getRequest().getSession().getAttribute("menuId"));
        if(menuId.equals("")) {
            System.out.println("insertAccess 1");
            return;
        }
        if(request.getRequestURI().endsWith(".xpl") || request.getRequestURI().endsWith(".ajax")
                || request.getRequestURI().indexOf("accessLog") > -1){
        }else{
            System.out.println("insertAccess 2");
            return;
        }

        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String reqUrl = "";
            conn = getJDBCConnection("cmas");
            List<String> authList = (List<String>)AccessLog.getAuthList();
            if(authList.size() == 0){
                String sql = "SELECT MENU_ID, DEM_URL FROM CO_MENU_CTG_DEM_URL";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                // 결과를 순차적으로 출력한다.
                while(rs.next()){
                    reqUrl = rs.getString("DEM_URL");
                    if(!reqUrl.startsWith("/")){
                        reqUrl = "/" + reqUrl;
                    }
                    reqUrl = rs.getString("MENU_ID") + "_" + reqUrl;
                    authList.add(reqUrl);
                }
            }
            reqUrl = request.getRequestURI();
            if(authList.contains(menuId + "_" + reqUrl)){
                return;
            }
            authList.add(menuId + "_" + reqUrl);

            String sql = "INSERT INTO CO_MENU_CTG_DEM_URL";
            sql += " (";
            sql += " MENU_ID";
            sql += " , DEM_URL";
            sql += " , REM";
            sql += " , FST_REG_DT";
            sql += " , FST_REG_USER_ID";
            sql += " , FNL_EDIT_DT";
            sql += " , FNL_EDIT_USER_ID";
            sql += " , OGG_CD";
            sql += " , OGG_TIME";
            sql += " )";
            sql += " VALUES (";
            sql += " '" + menuId + "'";
            sql += " , '" + reqUrl + "'";
            sql += " , ''";
            sql += " , sysdate";
            sql += " , 'dweadm'";
            sql += " , sysdate";
            sql += " , 'dweadm'";
            sql += ", '100'";
            sql += ", TO_TIMESTAMP(TO_CHAR(SYSDATE, 'YYYYMMDDHH24MISS'), 'YYYYMMDDHH24MISSFF')";
            sql += ")";
            System.out.println("WebContext Request sql => " + sql);
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            conn.commit();
        }catch(Exception e){
            //            System.out.println(e.toString());
        }finally{
            if(rs != null){
                try{
                    rs.close();
                }catch(Exception e){
                }
            }
            if(ps != null){
                try{
                    ps.close();
                }catch(Exception e){
                }
            }
            if(conn != null){
                try{
                    conn.close();
                }catch(Exception e){
                }
            }
        }
    }

    // 컨넥션 정보 조회
    public Connection getJDBCConnection(String spec) throws Exception{
        if(appProperties == null)
            appProperties = ConfigUtil.getProperties(appProperties);
        appProperties.put("spec", spec);
        try{
            conn = DataSourcePool.getInstance().getConnection(appProperties);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * Take this filter out of service.
     */
    public void destroy(){
        this.urlExtends = null;
        this.includeUrls = null;
        this.config = null;
    }

}
