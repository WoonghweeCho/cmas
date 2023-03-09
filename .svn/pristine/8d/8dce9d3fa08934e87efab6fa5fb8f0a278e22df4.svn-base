package com.dwenc.cmas.common.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jcf.data.GridData;
import jcf.data.RowStatus;
import jcf.data.RowStatusCallback;
import jcf.sua.dataset.DataSet;
import jcf.sua.dataset.DataSetColumn;
import jcf.sua.dataset.DataSetImpl;
import jcf.sua.dataset.DataSetRow;
import jcf.sua.dataset.DataSetRowImpl;
import jcf.sua.exception.RowStatusException;


public class GridUtils {
	
	public List<DataSetColumn> getColumns(Class clazz){
		List<DataSetColumn> cols = new ArrayList<DataSetColumn>();
		BeanInfo info;
		try {
			info = Introspector.getBeanInfo(clazz);
		    for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
		        Method reader = pd.getReadMethod();
		        if (reader != null && !pd.getName().equals("class"))
		        	cols.add(new DataSetColumn(pd.getName(),  pd.getPropertyType()));
		    }
		} catch (IntrospectionException e) {
		} catch (IllegalArgumentException e) {
		}

		return cols;
	}
	
	public DataSetRow baenToDataSetRow(Object obj, Class clazz){
		DataSetRow dataSetRow = new DataSetRowImpl();
		BeanInfo info;
		try {
			info = Introspector.getBeanInfo(clazz);
		    for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
		        Method reader = pd.getReadMethod();
		        if (reader != null && !pd.getName().equals("class"))
		        	dataSetRow.add(pd.getName(), reader.invoke(obj));
		    }
		} catch (IntrospectionException e) {
		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		} catch (InvocationTargetException e) {
			throw new RuntimeException("bean -> map Mapping Error");
		}
		return dataSetRow;
	}
	
	public <E> GridData<E> toGridData(List<E> list){
		
		return toGridData(list, null, null);
	}
	
	public <E> GridData<E> toGridData(List<E> list, List<E> orgList, String[] rowStatus ){
		if(list == null)
			return null;
		else if(list.size() < 1)
			return new TempGridData<E>(null, null);
		else if(orgList != null && orgList.size() > 0 && list.size() != orgList.size())
			throw new RuntimeException("list == orgList 크기가 동일하지 않습니다.");
		else if(rowStatus != null && rowStatus.length > 0 && list.size() != rowStatus.length)
			throw new RuntimeException("list == rowStatus 크기가 동일하지 않습니다.");
		
		Object obj = list.get(0);
		Class clazz = obj.getClass();
		List<DataSetColumn> cols = getColumns(clazz);
		List<DataSetRow> rows = new ArrayList<DataSetRow>();
		DataSet dataSet; 
		boolean isOrg = orgList != null? true : false;
		boolean isStatus = rowStatus != null? true : false;
		
		for(int i = 0 ; i < list.size(); i++){
			
			obj = list.get(i);
			clazz = obj.getClass();
			DataSetRow dataSetRow = baenToDataSetRow(obj, clazz);
			if(isStatus)
				dataSetRow.setRowStatus(rowStatus[i]);
			else
				dataSetRow.setRowStatus("NORMAL");

			if(isOrg){
				obj = orgList.get(i);
				DataSetRow orgDataSetRow = null;
				if(obj != null){
					clazz = obj.getClass();
					orgDataSetRow = baenToDataSetRow(obj, clazz);
					dataSetRow.setOrgDataSetRow(orgDataSetRow);
				}
			}
			
			rows.add(dataSetRow);
		}

		dataSet = new DataSetImpl(null, cols, rows);
		
		return (new TempGridData<E>(dataSet, clazz));
	}

	public <E> GridData<E> toGridData(List<Map<String, Object>> list, Class<E> clazz ){
		if(list == null)
			return null;
		else if(list.size() < 1)
			return new TempGridData<E>(null, null);
		
		String[] columns = (String[]) list.get(0).keySet().toArray(new String[list.get(0).size()]);
		List<DataSetColumn> cols = new ArrayList<DataSetColumn>();
		List<DataSetRow> rows = new ArrayList<DataSetRow>();
		DataSet dataSet; 
		for(String col : columns){
			Object val = list.get(0).get(col);
			cols.add(new DataSetColumn(col, (val != null) ? val.getClass() : String.class));
		}
		
		for(Map<String, Object> row : list){
			DataSetRow dataSetRow = new DataSetRowImpl();
			dataSetRow.setRowStatus("NORMAL");
			for(String col : columns){
				Object val = row.get(col);
				if(col.equals("ROW_STATUS"))
					dataSetRow.setRowStatus((String) val);
				else if(col.equals("ORG_DATA_ROW"))
					dataSetRow.setOrgDataSetRow(getMapToDataSetRow(columns, (Map<String, Object>) val));
				else
					dataSetRow.add(col, val);
			}
			rows.add(dataSetRow);
		}
		
		
		dataSet = new DataSetImpl(null, cols, rows);
		
		return (new TempGridData<E>(dataSet, clazz));
	}
	
	private DataSetRow getMapToDataSetRow(String[] columns, Map<String, Object> map) {
		DataSetRow dataSetRow = new DataSetRowImpl();
		for(String col : columns){
			Object val = map.get(col);
			dataSetRow.add(col, val);
		}
		return dataSetRow;
	}

	class TempGridData<T> implements GridData<T>{
		private List<T> list;
		private List<T> orgList;
		private List<RowStatus> rowStatusList;
		
		public TempGridData(DataSet dataSet, Class<T> clazz) {
			this.list = new ArrayList<T>();
			this.orgList = new ArrayList<T>();
			this.rowStatusList = new ArrayList<RowStatus>();
			
			if (dataSet != null)
			  for (int i = 0; i < dataSet.getRowCount(); ++i) {
			    Object bean = dataSet.getBean(clazz, i);
			
			
			    this.list.add((T) bean);
			    this.orgList.add(dataSet.getOrgDataBean(clazz, i));
			
			    RowStatus rowStatus = dataSet.getRowStatus(i);
			
			    if ((rowStatus == null) && 
			      (rowStatus == null)) {
			      throw new RowStatusException("DataSet={" + dataSet.getId() + "}의 rowStatus가 누락 되었습니다.");
			    }
			
			    this.rowStatusList.add(dataSet.getRowStatus(i));
			  }
		}
		
		@Override
		public T get(int paramInt) {
			return this.list.get(paramInt);
		}

		@Override
		public List<T> getList() {
			return this.list;
		}

		@Override
		public RowStatus getStatusOf(int paramInt) {
			return ((RowStatus)this.rowStatusList.get(paramInt));
		}

		@Override
		public int size() {
			return ((this.list == null) ? 0 : this.list.size());
		}

		@Override
		public void forEachRow(RowStatusCallback<T> callback) {
			
			for (int i = 0; i < this.list.size(); ++i){
				if(rowStatusList.get(i).ordinal() == RowStatus.INSERT.ordinal()){
					  callback.insert(get(i), i);
				}else if(rowStatusList.get(i).ordinal() == RowStatus.DELETE.ordinal()){
					   callback.delete(get(i), i);
				}else if(rowStatusList.get(i).ordinal() == RowStatus.UPDATE.ordinal()){
					   callback.update(get(i), this.orgList.get(i), i);
				}else{
					   callback.normal(get(i), i);
				}
			}
		}
		
	}
}
