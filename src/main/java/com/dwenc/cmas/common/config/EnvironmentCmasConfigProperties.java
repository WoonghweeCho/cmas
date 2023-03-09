package com.dwenc.cmas.common.config;

import docfbaro.util.config.annotation.ConfigFile;
import docfbaro.util.config.annotation.ConfigKey;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 : 공통
 * 프로그램 : EnvironmentConfigProperties
 * 설    명 : properties 파일을 로딩 기능
 * 작 성 자 :
 * 작성일자 :
 * 수정이력
 * ---------------------------------------------------------------
 * 수정일          이  름    사유
 * ---------------------------------------------------------------
 *
 * ---------------------------------------------------------------
 * </pre>
 *
 * @version 1.0
 */
@ConfigFile("properties/environment-cmas-config.properties")
public class EnvironmentCmasConfigProperties {

    @ConfigKey("dwe.chart.url")
    private String chartUrl;

    @ConfigKey("dwe.ecos.url")
    private String ecosUrl;

    @ConfigKey("dwe.eai.url")
    private String eaiUrl;

    @ConfigKey("dwe.eai.barocontosap_zdoc.wsdl")
    private String baroconToSapZdocWsdl;

    @ConfigKey("dwe.eai.barocontosap_zdoc.prefix")
    private String baroconToSapZdocPrefix;

    @ConfigKey("dwe.eai.barocontosap_zdoc.namespace")
    private String baroconToSapZdocNamespace;

    @ConfigKey("dwe.eai.barocontosap_md.wsdl")
    private String baroconToSapMdWsdl;

    @ConfigKey("dwe.eai.barocontosap_md.prefix")
    private String baroconToSapMdPrefix;

    @ConfigKey("dwe.eai.barocontosap_md.namespace")
    private String baroconToSapMdNamespace;

    @ConfigKey("dwe.eai.barocontosap_etc.wsdl")
    private String baroconToSapEtcWsdl;

    @ConfigKey("dwe.eai.barocontosap_etc.prefix")
    private String baroconToSapEtcPrefix;

    @ConfigKey("dwe.eai.barocontosap_etc.namespace")
    private String baroconToSapEtcNamespace;

    @ConfigKey("dwe.eai.barocontomdm.wsdl")
    private String baroconToMdmWsdl;

    @ConfigKey("dwe.eai.barocontomdm.prefix")
    private String baroconToMdmPrefix;

    @ConfigKey("dwe.eai.barocontomdm.namespace")
    private String baroconToMdmNamespace;


    public String getChartUrl(){
        return chartUrl;
    }


    public void setChartUrl(String chartUrl){
        this.chartUrl = chartUrl;
    }


    public String getEcosUrl(){
        return ecosUrl;
    }


    public void setEcosUrl(String ecosUrl){
        this.ecosUrl = ecosUrl;
    }


    public String getEaiUrl(){
        return eaiUrl;
    }


    public void setEaiUrl(String eaiUrl){
        this.eaiUrl = eaiUrl;
    }


    public String getBaroconToSapZdocWsdl(){
        return baroconToSapZdocWsdl;
    }


    public void setBaroconToSapZdocWsdl(String baroconToSapZdocWsdl){
        this.baroconToSapZdocWsdl = baroconToSapZdocWsdl;
    }


    public String getBaroconToSapZdocPrefix(){
        return baroconToSapZdocPrefix;
    }


    public void setBaroconToSapZdocPrefix(String baroconToSapZdocPrefix){
        this.baroconToSapZdocPrefix = baroconToSapZdocPrefix;
    }


    public String getBaroconToSapZdocNamespace(){
        return baroconToSapZdocNamespace;
    }


    public void setBaroconToSapZdocNamespace(String baroconToSapZdocNamespace){
        this.baroconToSapZdocNamespace = baroconToSapZdocNamespace;
    }


    public String getBaroconToSapMdWsdl(){
        return baroconToSapMdWsdl;
    }


    public void setBaroconToSapMdWsdl(String baroconToSapMdWsdl){
        this.baroconToSapMdWsdl = baroconToSapMdWsdl;
    }


    public String getBaroconToSapMdPrefix(){
        return baroconToSapMdPrefix;
    }


    public void setBaroconToSapMdPrefix(String baroconToSapMdPrefix){
        this.baroconToSapMdPrefix = baroconToSapMdPrefix;
    }


    public String getBaroconToSapMdNamespace(){
        return baroconToSapMdNamespace;
    }


    public void setBaroconToSapMdNamespace(String baroconToSapMdNamespace){
        this.baroconToSapMdNamespace = baroconToSapMdNamespace;
    }


    public String getBaroconToSapEtcWsdl(){
        return baroconToSapEtcWsdl;
    }


    public void setBaroconToSapEtcWsdl(String baroconToSapEtcWsdl){
        this.baroconToSapEtcWsdl = baroconToSapEtcWsdl;
    }


    public String getBaroconToSapEtcPrefix(){
        return baroconToSapEtcPrefix;
    }


    public void setBaroconToSapEtcPrefix(String baroconToSapEtcPrefix){
        this.baroconToSapEtcPrefix = baroconToSapEtcPrefix;
    }


    public String getBaroconToSapEtcNamespace(){
        return baroconToSapEtcNamespace;
    }


    public void setBaroconToSapEtcNamespace(String baroconToSapEtcNamespace){
        this.baroconToSapEtcNamespace = baroconToSapEtcNamespace;
    }


    public String getBaroconToMdmWsdl(){
        return baroconToMdmWsdl;
    }


    public void setBaroconToMdmWsdl(String baroconToMdmWsdl){
        this.baroconToMdmWsdl = baroconToMdmWsdl;
    }


    public String getBaroconToMdmPrefix(){
        return baroconToMdmPrefix;
    }


    public void setBaroconToMdmPrefix(String baroconToMdmPrefix){
        this.baroconToMdmPrefix = baroconToMdmPrefix;
    }


    public String getBaroconToMdmNamespace(){
        return baroconToMdmNamespace;
    }

    public void setBaroconToMdmNamespace(String baroconToMdmNamespace){
        this.baroconToMdmNamespace = baroconToMdmNamespace;
    }

}