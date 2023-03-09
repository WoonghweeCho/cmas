package com.dwenc.cmas.common.converter;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import docfbaro.common.StringUtil;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 :
 * 프로그램 : HashMapConverter
 * 설    명 : HashMap <-> Json Rich UI에서 정의한 기본 포멧을 유지한여 처리한다.
 * 작 성 자 : 배태일
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
public class HashMapConverter extends JSONConverter {

    private static Logger logger = LoggerFactory.getLogger(HashMapConverter.class);

    /**
     * <pre>
     * HashMap/List<Map<String, Object>>/Map/List -> Entity
     * </pre>
     * @param Object
     *            data
     * @return Map
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Map getEntities(Object data) throws Exception {
        Map groupList = new HashMap();
        try {
            Map map = (Map) data;
            String dataKey = map.keySet().iterator().next().toString();
            boolean bChk = false;
            if (dataKey.equals("Map")) {
                data = (Map) map.get(dataKey);
                bChk = true;
            }
            if (!bChk && dataKey.equals("List")) {
                data = (List) map.get(dataKey);
                bChk = true;
            }
            if (!bChk && dataKey.equals("List<Map<String, Object>>")) {
                data = (List<Map<String, Object>>) map.get(dataKey);
                bChk = true;
            }
            if (!bChk && dataKey.equals("HashMap")) {
                data = (HashMap) map.get(dataKey);
                bChk = true;
            } else if (!bChk && dataKey.indexOf("BindingResult") == -1) {
                data = map.get(dataKey);
                bChk = true;
            }

            if (data != null) {
                if (data instanceof Map) {
                    List<Map<String, Object>> lMultiData = (List<Map<String, Object>>) data;
                    try {
                        Map metaData = new HashMap();
                        metaData.put("recordId", "id");
                        metaData.put("recordState", "state");
                        metaData.put("dataSetId", "");
                        metaData.put("totalCount", lMultiData.size());
                        groupList.put("metaData", metaData);
                    } catch (Exception e) {
                    }
                    Map groupData = new HashMap();
                    for (int i = 0; i < lMultiData.size(); i++) {
                        Map records = new HashMap();
                        HashMap data2 = (HashMap)lMultiData.get(i);
                        Set dataKeySet = data2.keySet();
                        Iterator dataIterator = dataKeySet.iterator();
                        while (dataIterator.hasNext()) {
                            dataKey = dataIterator.next().toString();
                            records.put(dataKey, data2.get(dataKey));
                        }
                        groupData.put(i, records);
                    }
                    groupList.put("List<Map<String, Object>>", groupData);
                } else if (data instanceof HashMap) {
                    Map records = new HashMap();
                    HashMap data2 = (HashMap) data;
                    Set dataKeySet = data2.keySet();
                    Iterator dataIterator = dataKeySet.iterator();
                    while (dataIterator.hasNext()) {
                        dataKey = dataIterator.next().toString();
                        records.put(dataKey, data2.get(dataKey));
                    }
                    groupList.put("HashMap", records);
                } else if (data instanceof List) {
                    List list = (List) data;
                    for (int i = 0; i < list.size(); i++) {
                        List<Map<String, Object>> lMultiData = (List<Map<String, Object>>) list.get(i);
                        Map groupData = new HashMap();
                        for (int j = 0; j < lMultiData.size(); j++) {
                            Map records = new HashMap();
                            HashMap data2 = (HashMap)lMultiData.get(j);
                            Set dataKeySet = data2.keySet();
                            Iterator dataIterator = dataKeySet.iterator();
                            while (dataIterator.hasNext()) {
                                dataKey = dataIterator.next().toString();
                                records.put(dataKey, data2.get(dataKey));
                            }
                            groupData.put(j, records);
                        }
                        groupList.put(i, groupData);
                    }
                } else if (data instanceof Map) {
                    Map list = (Map) data;
                    Set dataKeySet = list.keySet();
                    Iterator dataIterator = dataKeySet.iterator();
                    while (dataIterator.hasNext()) {
                        dataKey = dataIterator.next().toString();
                        List<Map<String, Object>> lMultiData = (List<Map<String, Object>>) list.get(dataKey);
                        try {
                            Map metaData = new HashMap();
                            metaData.put("recordId", "id");
                            metaData.put("recordState", "state");
                            metaData.put("dataSetId", dataKey);
                            metaData.put("totalCount", lMultiData.size());
                            groupList.put("metaData", metaData);
                        } catch (Exception e) {
                        }
                        Map groupData = new HashMap();
                        for (int i = 0; i < lMultiData.size(); i++) {
                            Map records = new HashMap();
                            HashMap data2 = (HashMap)lMultiData.get(i);
                            dataKeySet = data2.keySet();
                            dataIterator = dataKeySet.iterator();
                            while (dataIterator.hasNext()) {
                                String dataKey2 = dataIterator.next().toString();
                                records.put(dataKey, data2.get(dataKey2));
                            }
                            groupData.put(i, records);
                        }
                        groupList.put(dataKey, groupData);
                    }
                } else {
                    groupList.put("Object", data);
                }
            }
        } catch (Exception e) {
            logger.error("getEntities error : " + e);
        }
        return groupList;
    }

    /**
     * <pre>
     * HashMap -> Entity
     * </pre>
     * @param HashMap
     *            source
     * @param Object
     *            target
     * @return Object
     */
    @SuppressWarnings("rawtypes")
    public static Object convertToEntity(Map source, Object target) throws Exception {
        String fieldName = "";
        try {
            Class c = target.getClass();
            Field[] field = c.getDeclaredFields();
            Method[] methods = c.getMethods();
            int methodSize = methods.length;
            for (int i = 0; i < field.length; i++) {
                fieldName = field[i].getName();
                String str = (fieldName.substring(0, 1)).toUpperCase();
                String tmpFieldName = str + fieldName.substring(1);

                String strMethod = "set" + tmpFieldName;
                int methodIndex = getMethodIndex(methods, strMethod, methodSize);

                Object[] strField = new Object[1];
                strField[0] = source.get(fieldName);
                if (source.containsKey(fieldName)) {
                    methods[methodIndex].invoke(target, strField);
                }
            }
        } catch (Exception e) {
            logger.error("convertToEntity error : fieldName(" + fieldName + ") " + e);
        }
        return target;
    }

    /**
     * <pre>
     * List<Map<String, Object>> -> Entity
     * </pre>
     * @param List<Map<String, Object>>
     *            source
     * @param Object
     *            target
     * @return ArrayList
     */
    @SuppressWarnings("rawtypes")
    public static ArrayList convertToMultiEntity(List<Map<String, Object>> source, Object target) throws Exception {
        ArrayList<Object> list = new ArrayList<Object>();
        String fieldName = "";
        try {
            Class c = target.getClass();
            Field[] field = c.getDeclaredFields();
            Method[] methods = c.getMethods();
            int methodSize = methods.length;
            int size = source.size();
            for (int i = 0; i < size; i++) {
                Object tmp = c.newInstance();
                for (int j = 0; j < field.length; j++) {
                    String fieldType = field[j].getType().getName();
                    fieldName = field[j].getName();
                    String str = (fieldName.substring(0, 1)).toUpperCase();
                    String tmpFieldName = str + fieldName.substring(1);
                    String strMethod = "set" + tmpFieldName;
                    int methodIndex = getMethodIndex(methods, strMethod, methodSize);

                    if (source.get(0).containsKey(fieldName)) {
                        Object[] strField = new Object[1];
                        strField[0] = source.get(i).get(fieldName);
                        if (fieldType.equals("java.lang.String")) {
                            methods[methodIndex].invoke(tmp, strField);
                        } else if (fieldType.equals("int")) {
                            methods[methodIndex].invoke(tmp, Integer.parseInt(strField[0].toString()));
                        } else if (fieldType.equals("double")) {
                            methods[methodIndex].invoke(tmp, Double.parseDouble(strField[0].toString()));
                        } else if (fieldType.equals("long")) {
                            methods[methodIndex].invoke(tmp, Long.parseLong(strField[0].toString()));
                        } else if (fieldType.equals("float")) {
                            methods[methodIndex].invoke(tmp, Float.parseFloat(strField[0].toString()));
                        } else if (fieldType.equals("boolean")) {
                            methods[methodIndex].invoke(tmp, Boolean.parseBoolean(strField[0].toString()));
                        } else {
                            methods[methodIndex].invoke(tmp, strField);
                        }
                    }
                }
                list.add(tmp);
            }
        } catch (Exception e) {
            logger.error("convertToMultiEntity error : fieldName(" + fieldName + ") " + e);
        }
        return list;
    }

    /**
     * <pre>
     * Entity -> List<Map<String, Object>>
     * </pre>
     * @param Object
     *            [] source
     * @return List<Map<String, Object>>
     */
    public static List<Map<String, Object>> convertObjToMap(Object[] source) throws Exception {
        Assert.notNull(source);
        List<Map<String, Object>> target = new ArrayList<Map<String, Object>>();
        String fieldName = "";
        if(source.length != 0) {
            try {
                for (int i = 0; i < source.length; i++) {
                    target.add(target.size(), convertToHashMap(source[i]));
                }
            } catch (Exception e) {
                throw new Exception("convertObjToList<Map<String, Object>> error : fieldName(" + fieldName + ") " + e);
            }
        }
        return target;
    }

    /**
     * <pre>
     * Entity -> HashMap
     * </pre>
     * @param Object
     *            source
     * @return HashMap
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static HashMap convertToHashMap(Object source) throws Exception {
        Assert.notNull(source);
        HashMap target = new HashMap();
        String fieldName = "";
        try {
            if (source instanceof Map) {
                Map mapData = (Map) source;
                if (!mapData.isEmpty()) {
                    Iterator dataIterator = mapData.keySet().iterator();
                    while (dataIterator.hasNext()) {
                        String mapDataKey = dataIterator.next().toString();
                        Object mapSubData = mapData.get(mapDataKey);
                        target.put(mapDataKey, mapSubData);
                    }
                }
            } else {
                List<Field> field = getAllFields(new LinkedList<Field>(), source.getClass());
                Class c = source.getClass();
                for (int i = 0; i < field.size(); i++) {
                    if (field.get(i).toGenericString().indexOf(" final ") > -1)
                        continue;
                    String fieldType = field.get(i).getType().getName();
                    fieldName = field.get(i).getName();
                    String tmpFieldName = "";
                    if (!StringUtil.isUpperCase(fieldName, 1)) {
                        tmpFieldName = StringUtil.upperFirst(fieldName);
                    } else {
                        tmpFieldName = fieldName;
                    }

                    Class[] classes = null;
                    String strMethod = "";
                    Method method = null;

                    try {
                        if (field.get(i).toGenericString().indexOf(" boolean ") > -1) {
                            strMethod = "is" + tmpFieldName;
                            try {
                                method = c.getMethod(strMethod, classes);
                            } catch (NoSuchMethodException e) {
                                strMethod = "get" + tmpFieldName;
                                method = c.getMethod(strMethod, classes);
                            }
                        } else {
                            strMethod = "get" + tmpFieldName;
                            method = c.getMethod(strMethod, classes);
                        }

                        Object[] strField = null;
                        if (fieldType.equals("java.lang.String")) {
                            target.put(fieldName, (String) method.invoke(source, strField));
                        } else if (fieldType.equals("int")) {
                            target.put(fieldName, ((Integer) method.invoke(source, strField)).intValue());
                        } else if (fieldType.equals("double")) {
                            target.put(fieldName, ((Double) method.invoke(source, strField)).doubleValue());
                        } else if (fieldType.equals("long")) {
                            target.put(fieldName, ((Long) method.invoke(source, strField)).longValue());
                        } else if (fieldType.equals("float")) {
                            target.put(fieldName, ((Float) method.invoke(source, strField)).floatValue());
                        } else if (fieldType.equals("boolean")) {
                            target.put(fieldName, ((Boolean) method.invoke(source, strField)).booleanValue());
                        } else {
                            target.put(fieldName, method.invoke(source, strField));
                        } // end if ( fieldtype.equals(".."))
                    } catch (Exception e) {
                        // 다음의 예외상황 발생시에 남은 변수들의 Getter 호출을 중단하지 않기 위해
                        // 루프를 빠져나가지 않는다
                        // - Argument Type Matching 예외상황
                        // - No Such Method 예외상황
                        // - Security(Private/Protected) 예외상황
                        logger.error("convertToEntity error : fieldName(" + fieldName + ") " + e);
                    }
                }
            }
        } catch (Exception e) {
            throw new Exception("convertToHashMap error : fieldName(" + fieldName + ") " + e);
        }
        return target;
    }

    /**
     * <pre>
     *  getMethodIndex(Method[] methods, String methodName, int methodSize)
     * </pre>
     * @param request
     * @param response
     */
    private static int getMethodIndex(Method[] methods, String methodName, int methodSize) {
        int i = 0;
        for (; i < methodSize; i++)
            if (methods[i].getName().equals(methodName))
                break;
        if (i == methodSize)
            return -1;
        else
            return i;
    }

    /**
     * <pre>
     *  List<Field> getAllFields(List<Field> fields, Class<?> type)
     * </pre>
     * @param request
     * @param response
     */
    public static List<Field> getAllFields(List<Field> fields, Class<?> type) {
        for (Field field: type.getDeclaredFields()) {
            fields.add(field);
        }

        if (type.getSuperclass() != null) {
            fields = getAllFields(fields, type.getSuperclass());
        }
        return fields;
    }
}
