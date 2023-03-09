package com.dwenc.cmas.common.utils;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 : 공통
 * 프로그램 : DaoUtil
 * 설      명 : DAO의 트랜잭션 처리 설정을 위한 Utility Class
 * 작 성 자 : 홍두희
 * 작성일자 : 2012-12-05
 * 수정이력
 * ---------------------------------------------------------------
 * 수정일          이  름    사유
 * ---------------------------------------------------------------
 * 2011-12-07             최초 작성
 * ---------------------------------------------------------------
 * </pre>
 * @version 1.0
 */
public class DaoUtil {

    /**
     * job type에 따른 트랜잭션 처리시 저장되는 Key
     */
    public final static String DWE_CUD_FILTER_KEY = "DWE_CUD_FILTER_KEY";

    /**
     * job type에 따른 트랜잭션 처리시 저장되는 insert 쿼리를 위한 값
     */
    public final static String DWE_CREATE_KEY = "DWE_CREATE_FILTER_VALUE";

    /**
     * job type에 따른 트랜잭션 처리시 저장되는 update 쿼리를 위한 값
     */
    public final static String DWE_UPDATE_KEY = "DWE_UPDATE_FILTER_VALUE";

    /**
     * job type에 따른 트랜잭션 처리시 저장되는 delete 쿼리를 위한 값
     */
    public final static String DWE_DELETE_KEY = "DWE_DELETE_FILTER_VALUE";

}
