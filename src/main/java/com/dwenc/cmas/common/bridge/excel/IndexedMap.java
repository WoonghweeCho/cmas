package com.dwenc.cmas.common.bridge.excel;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 : 공통모듈
 * 프로그램 : IndexedMap
 * 설    명 : HashMap의 Column 을 Index로 접근하여 데이터 조회를 가능하게 하는
 *           List<Map<String, Object>>의 Wrapping Class 이다.
 * 작 성 자 : 
 * 작성일자 : 2012-02-06
 * 수정이력
 * ---------------------------------------------------------------
 * 수정일                    이  름          사유
 * ---------------------------------------------------------------
 * 2012-02-06     
 * ---------------------------------------------------------------
 * </pre>
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndexedMap extends HashMap {

    private static final long serialVersionUID = -2431394598900745774L;

    private List<Map<String, Object>> ldata;

    private Object columnName[];

    public IndexedMap(List<Map<String, Object>> ldata) {
        this.ldata = ldata;
        ArrayList al = new java.util.ArrayList(ldata.get(0).keySet());
        columnName = al.toArray();
    }

    public IndexedMap() {}

    /**
     * List<Map<String, Object>>의 column 사이즈를 리턴한다.
     *
     * @return List<Map<String, Object>>의 column count
     */
    public int columnSize(){
        return columnName.length;
    }

    /**
     * List<Map<String, Object>>의 row 사이즈를 리턴한다.
     *
     * @return List<Map<String, Object>>의 row count
     */
    public int rowSize(){
        return ldata.size();
    }

    /**
     * 컬럼명을 리턴한다.
     *
     * @return List<Map<String, Object>>의 i column 명
     */
    public String columnName(int i){
        return (String)(columnName[i]);
    }

    /**
     * i column의 j row의 값을 Object로 리턴한다.
     *
     * @param  i column index
     * @param  j row index
     * @return Object
     */
    public Object get(int i, int j){
        return ldata.get(j).get(columnName[i]);
    }
    /**
     * i column의 j row의 값을 Object로 리턴한다.
     *
     * @param  i column index
     * @param  j row index
     * @return Object
     */
    public String getString(int i, int j){
        return ldata.get(j).get(columnName[i]).toString();
    }
    /**
     * i column의 j row의 값을 Object로 리턴한다.
     *
     * @param  i column index
     * @param  j row index
     * @return Object
     */
    public int getInt(int i, int j){
        return (Integer)ldata.get(j).get(columnName[i]);
    }
    /**
     * i column의 j row의 값을 float로 리턴한다.
     *
     * @param  i column index
     * @param  j row index
     * @return float
     */
    public float getFloat(int i, int j){
        return (Float)ldata.get(j).get(columnName[i]);
    }
    /**
     * i column의 j row의 값을 long로 리턴한다.
     *
     * @param  i column index
     * @param  j row index
     * @return long
     */
    public long getLong(int i, int j){
        return (Long)ldata.get(j).get(columnName[i]);
    }
    /**
     * i column의 j row의 값을 short로 리턴한다.
     *
     * @param  i column index
     * @param  j row index
     * @return short
     */
    public short getShort(int i, int j){
        return (Short)ldata.get(j).get(columnName[i]);
    }
    /**
     * i column의 j row의 값을 boolean로 리턴한다.
     *
     * @param  i column index
     * @param  j row index
     * @return boolean
     */
    public boolean getBoolean(int i, int j){
        return (Boolean)ldata.get(j).get(columnName[i]);
    }

    public String toString(){
        String result = "Column Size : " + columnSize() + "\n" + "Row Size : " + rowSize() + "\n";
        return result + ldata.toString();
    }
}



