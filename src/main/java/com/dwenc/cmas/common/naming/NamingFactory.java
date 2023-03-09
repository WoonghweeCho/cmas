package com.dwenc.cmas.common.naming;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 : 공통
 * 프로그램 : NamingFactory
 * 설    명 :
 * <pre>
 * NamingIF를 상속 받은 Naming convention 클래스를 읽어 와서 ResultConverter의 Naming Rule에 반영한다.
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

import java.util.Observable;
import java.util.Observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import docfbaro.common.ClassUtils;

public class NamingFactory implements Observer {

    /**
     * log 처리를 위한 변수 선언
     */
    private static final Logger logger = LoggerFactory.getLogger(NamingFactory.class);

    private static NamingIF naming = new DefaultNaming();

    private NamingFactory() {
        init();
    }

    /**
     * com.dwenc.cmas.common.naming.DefaultNaming을 기본적으로 loading 한다
     */
    public static void init(){
        try {
            naming = (NamingIF)ClassUtils.getInstance("com.dwenc.cmas.common.naming.DefaultNaming");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * loading된 Naming Rule 클래스를 리턴한다.
     * @return NamingIF
     */
    public static NamingIF getNaming(){
        return naming;
    }

    /**
     * Naming Rule 클래스를 갱신
     * @param o the observable object.
     * @param arg notifyObservers method에 전달되어지는 argument.
     */
    public void update(Observable o, Object arg){
        synchronized (this) {
            init();
        }
    }

}



