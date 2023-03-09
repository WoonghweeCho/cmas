package com.dwenc.cmas.common.naming;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 :
 * 프로그램 : DefaultNaming
 * 설    명 : 
 * <pre>
 *     데이터베이스의 컬럼명을 기준으로 프로젝트 기반의 naming rule을 적용하기 위한 클래스.
 *     본클래스에서 취득한 attribute명과 setter method의 이름을 이용하여 resultset의 데이터를
 *     entity(Value Object)형 클래스에 저장한다.
 *
 *     이 DefaultNaming 클래스는 JDK V1.3이상에서 동작한다.
 * </pre>
 * 작 성 자 : DWE
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

import java.util.StringTokenizer;

/**
 * <pre>
 *     데이터베이스의 컬럼명을 기준으로 프로젝트 기반의 naming rule을 적용하기 위한 클래스.
 *     본클래스에서 취득한 attribute명과 setter method의 이름을 이용하여 resultset의 데이터를
 *     entity(Value Object)형 클래스에 저장한다.
 *
 *     이 DefaultNaming 클래스는 JDK V1.3이상에서 동작한다.
 * </pre>
 */
public class DefaultNaming implements NamingIF {

    /**
     * DB컬럼명 혹은 변경하고자하는 베이스명을 이용하여 해당하는 attribute명을 리턴한다.
     *
     * @param columnName 데이터베이스의 컬럼명 혹은 변환하고자하는 베이스명
     * @return String 변환된 attribute명
     */
    public String getAttributeName(String columnName){
        StringTokenizer dbColumnName = new StringTokenizer(columnName.toLowerCase(), "_");
        int cnt = dbColumnName.countTokens();

        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < cnt; i++)
            if (i == 0) {
                buf.append(dbColumnName.nextToken());
            } else {
                String str = dbColumnName.nextToken();
                buf.append(str.substring(0, 1).toUpperCase());
                buf.append(str.substring(1, str.length()));
            }

        return buf.toString();
    }

    /**
     * DB컬럼명 혹은 변경하고자하는 베이스명을 이용하여 해당하는 set method의 명을 리턴한다.
     *
     * @param columnName 데이터베이스의 컬럼명 혹은 변환하고자하는 베이스명
     * @return String 컬럼명을 기준으로 해당 컬럼의 set method명
     */
    public String getSetMethodName(String columnName){
        String str = "";
        str = getAttributeName(columnName);
        return "set" + str.substring(0, 1).toUpperCase() + str.substring(1, str.length());
    }

    /**
     * DB컬럼명 혹은 변경하고자하는 베이스명을 이용하여 해당하는 get method의 명을 리턴한다.
     *
     * @param columnName 데이터베이스의 컬럼명 혹은 변환하고자하는 베이스명
     * @return String 컬럼명을 기준으로 해당 컬럼의 get method명
     */
    public String getGetMethodName(String columnName){
        String str = "";
        str = getAttributeName(columnName);
        return "get" + str.substring(0, 1).toUpperCase() + str.substring(1, str.length());
    }

}



