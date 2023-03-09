package com.dwenc.cmas.common.naming;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 :
 * 프로그램 : NamingIF
 * 설    명 : 
 * <pre>
 * Naming Rule를 구현하기 위한 인터페이스를 제공한다.
 * 구현 방법은 데이터 베이스 컬럼명과 화면상에 사용할 변수 attribute 명 간의 규칙을
 * getAttributeName(), getSetMethodName(), getGetMethodName() 메소드를 통해
 * 구현하면 된다.
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

public interface NamingIF {

    /**
     * 데이터 베이스 컬럼명을 화면상에 사용할 변수 attribute 명으로 변경할 Rule을 정의한다.
     *
     * @param columnName 데이터 베이스 컬럼명
     * @return 화면상에 사용할 변수 attrubute명
     */
    public String getAttributeName(String columnName);

    /**
     * 데이터 베이스 컬럼명을 VO(Value Object)와 같은 클래스의 setter 명으로 변경할 Rule을 정의 한다.
     *
     * @param columnName 데이터 베이스 컬럼명
     * @return String 화면상에 사용할 변수 attrubute명
     */
    public String getSetMethodName(String columnName);

    /**
     * 데이터 베이스 컬럼명을 VO(Value Object)와 같은 클래스의 getter 명으로 변경할 Rule을 정의 한다.
     *
     * @param columnName 데이터 베이스 컬럼명
     * @return String 화면상에 사용할 변수 attrubute명
     */
    public String getGetMethodName(String columnName);

}



