package com.dwenc.cmas.common.datasource.service.support;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Service;

import com.dwenc.cmas.common.sysMng.service.support.WasFactory;

import docfbaro.common.ObjUtil;
import docfbaro.common.StringUtil;
import docfbaro.iam.UserInfo;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 :
 * 프로그램 : DatSrcFactory
 * 설    명 : xplatform의 transaction filter 정보 메모리화
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
@Service
public class DatSrcFactory implements Observer {

    @Autowired
    private SqlMapClientTemplate sqlMapClientTemplate;

    @Autowired
    @Qualifier("appProperties")
    private Properties appProperties;

    @Autowired
    private WasFactory wasFactory;

    private List<Map<String, Object>> mDatSrc = new ArrayList<Map<String, Object>>();

	@PostConstruct
	public void init() {
        String instcId = System.getProperty("instcId");
        if(instcId == null) instcId = StringUtil.getText(appProperties.get("dwe.serverInfo.instcId"));
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("instcId", instcId);
	    mDatSrc = (List<Map<String, Object>>)sqlMapClientTemplate.queryForList("datSrc.retrieveDatSrcList", input);
	}

	/**
	 * <pre>
	 * 입력된 datSrcId에 해당하는 datSrc 목록을 반환한다.
	 * </pre>
	 * @return Map<String, Object>
	 */
	public List<Map<String, Object>> getDatSrc() {
		return mDatSrc;
	}

	/**
	 * <pre>
	 *  DatSrc
	 * </pre>
	 */
    public String getDatSrc( String instcId, String reqPath, String reqUrl ) {
        if(ObjUtil.isNull(instcId)) {
            instcId = StringUtil.getText(UserInfo.getUserInfo().getEtcInfo().get("instcId"));
            return "dataSource_" + instcId;
        }
        if(ObjUtil.isNull(reqPath) && ObjUtil.isNull(reqUrl)) { // default
            return "dataSource_" + instcId;
        }
        String datSrcId = null;
        if(!ObjUtil.isNull(reqPath)) {
            datSrcId = getDatSrcByQuery( instcId, reqPath );
        }
        if(datSrcId == null) {
            datSrcId = getDatSrcByUrl( instcId, reqUrl );
        }
        return datSrcId;
    }

    /**
     * <pre>
     *  Url를 통해서 DatSrc 정보 조회
     * </pre>
     */
	public String getDatSrcByUrl( String InstcId, String reqUrl ) {
        Iterator it = mDatSrc.iterator();
        while ( it.hasNext() ) {
            Object ojb = it.next();
            if ( ojb == null )
                continue;
            Map<String, Object> mData = (Map<String, Object>)ojb;
            if(!StringUtil.getText(mData.get("instcId")).equals(InstcId)) continue;
            String tmpJob = StringUtil.getText(mData.get("demUrl"));
            if(!tmpJob.startsWith("/")) continue;
            if ( tmpJob.indexOf( "*" ) > 0) {
                tmpJob = tmpJob.substring( 0, tmpJob.indexOf( "*", 1 ));
                if ( reqUrl.startsWith( tmpJob ) ) {
                    return StringUtil.getText(mData.get("datSrcId"));
                }
            } else {
                if ( reqUrl.equals( tmpJob ) ) {
                    return StringUtil.getText(mData.get("datSrcId"));
                }
            }
        }
        return null;
    }

	/**
	 * <pre>
	 *  Query를 통해서 DatSrc 정보 조회
	 * </pre>
	 */
    public String getDatSrcByQuery( String InstcId, String reqPath ) {
        Iterator it = mDatSrc.iterator();
        while ( it.hasNext() ) {
            Object ojb = it.next();
            if ( ojb == null )
                continue;
            Map<String, Object> mData = (Map<String, Object>)ojb;
            if(!StringUtil.getText(mData.get("instcId")).equals(InstcId)) continue;
            String tmpJob = StringUtil.getText(mData.get("demUrl"));
            if(tmpJob.startsWith("/")) continue;
            if ( tmpJob.indexOf( "*" ) > 0) {
                tmpJob = tmpJob.substring( 0, tmpJob.indexOf( "*", 1 ));
                if ( reqPath.startsWith( tmpJob ) ) {
                    return StringUtil.getText(mData.get("datSrcId"));
                }
            } else {
                if ( reqPath.equals( tmpJob ) ) {
                    return StringUtil.getText(mData.get("datSrcId"));
                }
            }
        }
        return null;
    }

    /**
     * <pre>
     *  DatSrcFactory에서 사용하는 Map을 초기화한다.
     * </pre>
     */
	public void refresh() {
		synchronized (this) {
			this.mDatSrc = new ArrayList<Map<String, Object>>();
		}
	}

	/**
	 * <PRE>
	 *  DatSrcFactory에서 사용하는 Map을 초기화한다.
	 * </PRE>
	 *
	 * @param o
	 * @param arg
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public void update(Observable o, Object arg) {
		this.refresh();
	}

    /**
     * <PRE> 요청된 url을 통해서 실제 서비스할 서버의 url로 변환
     * </PRE>
     *
     * @param strReqUrl
     */
    public String getContextUrl(String strReqUrl){
        String targetUrl = "";
            for(int i=0;i<mDatSrc.size();i++) {
                String reqUrl = StringUtil.getText(mDatSrc.get(i).get("demUrl"));
                reqUrl = reqUrl.replace("*", "");
                String reqUrlArry[] = reqUrl.split(";");
                for(int j=0;j<reqUrlArry.length;j++) {
                    if(strReqUrl.startsWith( reqUrlArry[j] ) == true) {
                    	System.out.println("getContextUrl reqUrlArry[j] true is :"+reqUrlArry[j]);
                    	targetUrl = StringUtil.getText(appProperties.getProperty("dwe.serverInfo.center.url"));

                        break;
                    }
                }
            }
            if(targetUrl.equals("")) StringUtil.getText(appProperties.get("dwe.serverInfo.url.context"));
            return targetUrl;
    }
}

