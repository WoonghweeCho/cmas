package docfbaro.query;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jcf.data.handler.StreamHandler;
import jcf.iam.core.common.util.UserInfoHolder;
import jcf.query.core.QueryExecutor;
import jcf.query.core.mapper.BeanRowMapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.JdbcUtils;

import com.dwenc.cmas.common.instance.service.support.InstcFactory;
import com.dwenc.cmas.common.utils.SqlInjectionUtil;

import docfbaro.common.Constants;
import docfbaro.common.ObjUtil;
import docfbaro.common.SpringUtil;
import docfbaro.common.StringUtil;
import docfbaro.common.WebContext;
import docfbaro.iam.UserInfo;
import docfbaro.iam.authentication.UserDefinition;
import docfbaro.query.callback.BatchTransactionCallback;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 :
 * 프로그램 :
 * 설    명 : {@link CommonDao}의 구현체
 * 작 성 자 :
 * 작성일자 : 2011-12-01
 * 수정이력
 * ---------------------------------------------------------------
 * 수정일          이  름    사유
 * ---------------------------------------------------------------
 * 2011-12-01             최초 작성
 * ---------------------------------------------------------------
 * </pre>
 * @version 1.0
 */
public class CommonDaoExtImpl implements CommonDao {

    private QueryExecutor queryExecutor;

    /*
     * (non-Javadoc)
     *
     * @see
     * docfbaro.query.CommonDao#queryForObject(Object statementId, Object parameter, Class<T> clazz)
     */
    public <T> T queryForObject(Object statementId, Object parameter, Class<T> clazz){
    	SqlInjectionUtil.checkInjectionString(parameter);
        return queryExecutor.queryForObject(statementId, parameter, clazz);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * docfbaro.query.CommonDao#queryForMap(Object statementId, Object parameter)
     */
    public Map<String, Object> queryForMap(Object statementId, Object parameter){
    	SqlInjectionUtil.checkInjectionString(parameter);
        return queryExecutor.queryForMap(statementId, parameter);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * docfbaro.query.CommonDao#queryForList(Object statementId, Object parameter, Class<T> clazz)
     */
    public <T> List<T> queryForList(Object statementId, Object parameter, Class<T> clazz){
    	SqlInjectionUtil.checkInjectionString(parameter);
        return queryExecutor.queryForList(statementId, parameter, clazz);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * docfbaro.query.CommonDao#queryForList(Object statementId, Object parameter, RowMapper<T> rowMapper)
     */
    public <T> List<T> queryForList(Object statementId, Object parameter, RowMapper<T> rowMapper){
    	SqlInjectionUtil.checkInjectionString(parameter);
        return queryExecutor.queryForList(statementId, parameter, rowMapper);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * docfbaro.query.CommonDao#queryForList(Object statementId, Object parameter, int skipRows, int maxRows, Class<T> clazz)
     */
    public <T> List<T> queryForList(Object statementId, Object parameter, int skipRows, int maxRows, Class<T> clazz){
    	SqlInjectionUtil.checkInjectionString(parameter);
        return queryExecutor.queryForList(statementId, parameter, skipRows, maxRows, clazz);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * docfbaro.query.CommonDao#queryForMapList(Object statementId, Object parameter)
     */
    public List<Map<String, Object>> queryForMapList(Object statementId, Object parameter){
    	SqlInjectionUtil.checkInjectionString(parameter);
        return queryExecutor.queryForMapList(statementId, parameter);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * docfbaro.query.CommonDao#queryForMapList(Object statementId, Object parameter, int skipRows, int maxRows)
     */
    @SuppressWarnings("deprecation")
    public List<Map<String, Object>> queryForMapList(Object statementId, Object parameter, int skipRows, int maxRows){
    	SqlInjectionUtil.checkInjectionString(parameter);
        return queryExecutor.queryForMapList(statementId, parameter, skipRows, maxRows, true);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * docfbaro.query.CommonDao#queryForInt(Object statementId, Object parameter)
     */
    public Integer queryForInt(Object statementId, Object parameter){
    	SqlInjectionUtil.checkInjectionString(parameter);
        return queryExecutor.queryForInt(statementId, parameter);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * docfbaro.query.CommonDao#queryForLong(Object statementId, Object parameter)
     */
    public Long queryForLong(Object statementId, Object parameter){
    	SqlInjectionUtil.checkInjectionString(parameter);
        return queryExecutor.queryForLong(statementId, parameter);
    }

    /*
     * (non-Javadoc)
     * @see docfbaro.query.CommonDao#queryForStream(java.lang.Object, java.lang.Object, jcf.data.handler.StreamHandler)
     */
    public <T> void queryForStream(Object statementId, Object parameter, StreamHandler<T> streamHandler){
    	SqlInjectionUtil.checkInjectionString(parameter);
        queryExecutor.queryForStream(statementId, parameter, streamHandler);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * docfbaro.query.CommonDao#update(Object statementId, Object parameter)
     */
    public Integer update(Object statementId, Object parameter){
        parameter = setOgg(parameter);
        return queryExecutor.update(statementId, parameter);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * docfbaro.query.CommonDao#batchUpdate(Object statementId, List<?> parameter)
     */
    public int[] batchUpdate(Object statementId, List<?> parameter){
        parameter = setOggs(parameter);
        return queryExecutor.batchUpdate(statementId, parameter);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * docfbaro.query.CommonDao#batchUpdate(List<T> parameter, BatchTransactionCallback<T> callback)
     */
    public <T> int[] batchUpdate(List<T> parameter, BatchTransactionCallback<T> callback){
        parameter = setOggs(parameter);

        List<Object> seeds = new ArrayList<Object>(callback.getBatchSize());

        int[] batchResult = new int[parameter.size()];
        int loop = 0;

        for(T record : parameter){
            seeds.add(callback.doInTransaction(record));

            if(seeds.size() == callback.getBatchSize()){
                int[] local = queryExecutor.batchUpdate(callback.getStatementId(), seeds);

                System.arraycopy(local, 0, batchResult, loop++ * callback.getBatchSize(), callback.getBatchSize());

                seeds.clear();
            }
        }

        if(seeds.size() > 0){
            System.arraycopy(queryExecutor.batchUpdate(callback.getStatementId(), seeds), 0, batchResult, loop
                    * callback.getBatchSize(), callback.getBatchSize());
        }

        return batchResult;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * docfbaro.query.CommonDao#queryAndBatchUpdate(String statementId, Object parameter, Class<T> clazz, final BatchTransactionCallback<T> callback)
     */
    public <T> void queryAndBatchUpdate(String statementId, Object parameter, Class<T> clazz,
            final BatchTransactionCallback<T> callback){
        parameter = setOgg(parameter);

        final BeanRowMapper<T> mapper = new BeanRowMapper<T>(clazz);
        final List<Object> seeds = new ArrayList<Object>(callback.getBatchSize());

        queryExecutor.queryForList(statementId, parameter, new RowMapper<T>() {

            public T mapRow(ResultSet rs, int rowNum) throws SQLException{

                seeds.add(callback.doInTransaction(mapper.mapRow(rs, rowNum)));

                if(seeds.size() == callback.getBatchSize()){
                    queryExecutor.batchUpdate(callback.getStatementId(), seeds);
                    seeds.clear();
                }

                return null;
            }
        });

        if(seeds.size() > 0){
            queryExecutor.batchUpdate(callback.getStatementId(), seeds);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * docfbaro.query.CommonDao#executeCallStatement(Object statementId, Object parameter)
     */
    public Map<String, Object> executeCallStatement(Object statementId, Object parameter){
        parameter = setOgg(parameter);
        return queryExecutor.executeCallStatement(statementId, parameter);
    }

    public void setQueryExecutor(QueryExecutor queryExecutor){
        this.queryExecutor = queryExecutor;
    }

    public Object setOgg(Object parameter){
    	if(parameter == null)
    		return parameter;
        try{
            InstcFactory instcFactory = (InstcFactory)SpringUtil.getApplicationContext().getBean("instcFactory");
            Class<?> clz = parameter.getClass();
            String oggTime = instcFactory.getOggTime();

            // 세션이 없을 경우에 default siteCd를 추출
            Map<String, Object> instc = instcFactory.getInstc(Constants.instcId);
            String defaultSiteCd = StringUtil.getText(instc.get("siteCd"));
            String oggCd = defaultSiteCd;

            String siteCd = "";
            if(parameter instanceof java.util.HashMap){
                String inputOggCd = "";
                // 업무에서 명시적으로 입력했을 경우
                UserDefinition userDef = UserInfoHolder.getUserInfo(UserDefinition.class);
                if (userDef != null) {
                    inputOggCd = StringUtil.getText(UserInfo.getUserInfo().getCurSite().get("oggCd"));
                }
                if(!StringUtil.getText(inputOggCd).equals("")) {
                    oggCd = inputOggCd;
                } else {
                    // 업무데이터에서 siteCd 추출하여 oggCd에 적용
                    siteCd = StringUtil.getText(((HashMap)parameter).get("siteCd"));
                    if(!StringUtil.getText(siteCd).equals("")) {
                        // 견적에서 siteCd 확장의미 적용 때문에 예외 처리
                        if(siteCd.length() > 5) siteCd = defaultSiteCd;
                        oggCd = siteCd;
                    } else {
                        // 세션에서 siteCd를 추출
                        UserDefinition user = UserInfoHolder.getUserInfo(UserDefinition.class);
                        if (user != null) {
                            String oggCd2 = "";
                            // 화면에 선택된 COA0101 객체의 값이 있을 경우 우선됨
                            String UIOggCd = StringUtil.getText(WebContext.getRequest().getParameter("oggCd"));
                            if(!UIOggCd.equals("")) {
                                oggCd2 = UIOggCd;
                            } else {
                                oggCd2 = StringUtil.getText(UserInfo.getUserInfo().getCurSite().get("siteCd"));
                            }
                            if(!oggCd2.equals("")) oggCd = oggCd2;
                        } else {
                            if(!siteCd.equals("")) oggCd = defaultSiteCd;
                        }
                    }
                }
                if ( oggCd.length() > 5 ) {
                	oggCd = defaultSiteCd;
                }
                // 대표 siteCd로 변환
                oggCd = instcFactory.getRepSiteCd(oggCd);
                ((HashMap)parameter).put("oggCd", oggCd);
                ((HashMap)parameter).put("oggTime", oggTime);
            }else{
                BeanInfo beanInfo = Introspector.getBeanInfo(clz);
                PropertyDescriptor[] props = beanInfo.getPropertyDescriptors();
                String inputOggCd = "";
                // siteCd 추출
                for(PropertyDescriptor prop : props){
                    if(prop.getName().equals("oggCd")){
                        inputOggCd = (String)prop.getReadMethod().invoke(parameter, (Object[]) null);
                        if(!StringUtil.getText(inputOggCd).equals("")) {
                            break;
                        }
                    }
                    if(prop.getName().equals("siteCd")){
                        siteCd = (String)prop.getReadMethod().invoke(parameter, (Object[]) null);
                        if(!StringUtil.getText(siteCd).equals("")) {
                            break;
                        }
                    }
                }
                if(!StringUtil.getText(inputOggCd).equals("")) {
                    oggCd = inputOggCd;
                } else {
                    if(!StringUtil.getText(siteCd).equals("")) {
                        if(siteCd.length() > 5) siteCd = defaultSiteCd;
                        oggCd = siteCd;
                    } else {
                        UserDefinition user = UserInfoHolder.getUserInfo(UserDefinition.class);
                        if (user != null) {
                            String oggCd2 = "";
                            // 화면에 선택된 COA0101 객체의 값이 있을 경우 우선됨
                            String UIOggCd = StringUtil.getText(WebContext.getRequest().getParameter("oggCd"));
                            if(!UIOggCd.equals("")) {
                                oggCd2 = UIOggCd;
                            } else {
                                oggCd2 = StringUtil.getText(UserInfo.getUserInfo().getCurSite().get("siteCd"));
                            }
                            if(!oggCd2.equals("")) oggCd = oggCd2;
                        } else {
                            if(!siteCd.equals("")) oggCd = defaultSiteCd;
                        }
                    }
                }
                if ( oggCd.length() > 5 ) {
                	oggCd = defaultSiteCd;
                }
                // 대표 siteCd로 변환
                oggCd = instcFactory.getRepSiteCd(oggCd);
                for(PropertyDescriptor prop : props){
                    if(prop.getName().equals("oggCd")){
                        Method getMethod = prop.getWriteMethod();
                        getMethod.invoke(parameter, oggCd);
                    }
                    if(prop.getName().equals("oggTime")){
                        Method getMethod = prop.getWriteMethod();
                        getMethod.invoke(parameter, oggTime);
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return parameter;
    }

    /**
     * 현장코드 세션에 기록
     *
     * @param input 입력파라미터
     */
    public Map<String, Object> changeSite(Map<String, Object> input){
        List<Map<String, Object>> result = retrievePrjCode(input);
        if(result.size() > 0){
            UserInfo.getUserInfo().setCurSite((HashMap<String, Object>)result.get(0));
            return (HashMap<String, Object>)result.get(0);
        }else{
            return new HashMap<String, Object>();
        }
    }

    /**
     * 현장코드 리스트 조회
     *
     * @param input 입력파라미터
     * @return Query를 수행한 결과
     * @exception Exception
     */
    public List<Map<String, Object>> retrievePrjCode(Map<String, Object> input){
        input.put("qrylang", ObjUtil.getSiteSql());
        return queryForMapList("site.retrieveSite", input);
    }

    public <T> List<T> setOggs(List<T> parameter){
        try{
            for(Object obj : parameter){
                setOgg(obj);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return parameter;
    }

    /*
     * (non-Javadoc)
     * @see docfbaro.query.CommonDao#queryForMapStringValueList(java.lang.Object, java.lang.Object)
     */
    public List<Map<String, String>> queryForMapStringValueList(Object statementId, Object parameter) {
        return queryExecutor.queryForList(statementId, parameter, new RowMapper<Map<String, String>>(){

            @Override
            public Map<String, String> mapRow(ResultSet rs, int rowNum) throws SQLException {
                Map<String, String> map = new HashMap<String, String>();

                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();

                for (int i = 1; i <= columnCount; i++) {
                    map.put(JdbcUtils.convertUnderscoreNameToPropertyName(rsmd.getColumnName(i)), rs.getString(i));
                }
                return map;
            }
        });
    }
}
