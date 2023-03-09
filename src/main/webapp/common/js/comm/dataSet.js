if(Array.prototype.remove == undefined){
	Array.prototype.remove = function(from, to) {	//배열의 요소 삭제
		var rest = this.slice((to || from) + 1 || this.length);
		this.length = from < 0 ? this.length + from : from;
		return this.push.apply(this, rest);
	};
}

var DataSet = {};	// 데이터셋
DataSet.DataSetStatus = { // 데이터셋Row의 상태
	"NORMAL" : "NORMAL",
	"INSERT" : "INSERT",
	"UPDATE" : "UPDATE",
	"DELETE" : "DELETE"
};

DataSet.DataType = {};	// 데이터타입 -- 향후 추가
DataSet.DataSetRow = {};	// 데이터 Row
//DataSet.DataSetColumns = {}; //데이터 Column 향후 사용여부 고려


/**
 * @class 데이터셋
 * @param JSONArray<JSONObject>
 * @returns this{DataSet}
 * @author 권준호
 * @since  2013-03-28
 * @version 1.0
 */

DataSet = function(list, option) {	// 데이터셋
	this.log = alert; //로그를 찍을 대상을 지정함
	this._filter = null; // 필터할 함수를 담음.
	// this._column 컬럼정보 사용여부 고려
	this._rows = new Array();	// 실제 데이터 Rows
	this._deleteRow = new Array();	// 삭제된 데이터 Rows
	this._view = new Array();	// 바이딩 혹은 필터에 의해 보여질 가상 데이터 Row
	this._pos = -1;
	this._binder = new DataSet.Bind(this);
	this.option = {};	// 향후 이벤트 혹은 다양한 옵션을 지정할수 있게 처리

	this.isSetAllData = false;

	if(typeof(list) != "undefined"){
		if(list instanceof Array){
			this.setAllData(list);
			if(typeof(option) != "undefined")
				this.option = list;

		}else if(list instanceof Array){
			this.option = list;
		}
	}

	/*if(this.view().length > -1)
		this._pos = 0;*/
};

/**
 * 데이터셋에 Position을 설정함.
 * @param pos<int>
 * @author 권준호
 * @since  2013-03-28
 * @version 1.0
 */
DataSet.prototype.setPosition = function(pos, isMove) {
	isMove = typeof(isMove) == "undefined" ? false : isMove;
	if(this._pos == pos && !isMove)
		return;
	this._pos = pos;
	this._binder.posDrow(pos);
};

/**
 * 데이터셋에 Position을 출력함.
 * @return pos<int>
 * @author 권준호
 * @since  2013-03-28
 * @version 1.0
 */
DataSet.prototype.getPosition = function() {
	return this._pos;
};

/**
 * JSONArray 객체를 데이터셋에 임포트함.
 * @param Array<Object>
 * @author 권준호
 * @since  2013-03-28
 * @version 1.0
 */
DataSet.prototype.setAllData = function(list) {
	var startTime = (new Date()).getTime();
	this.clear();
	if(list == null) {
		return;
	}

	this.isSetAllData = true;

	for ( var i = 0; i < list.length; i++) {
		this.add(list[i]);
		this._rows[i].apply();
	}
	this.isSetAllData = false;
	//try{console.log("Set All Data Time : " + ((new Date()).getTime() - startTime));}catch(e){}
	this._filtering(true);
	this.setPosition(-1, true);
};

/**
 * JSONObject 객체를 받아 마지막 Row 다음에 Row를 추가함
 * @param obj(Object)
 * @returns index(int)
 * @author 권준호
 * @since  2013-03-28
 * @version 1.0
 */
DataSet.prototype.add = function(obj) {

	var index = this._view.length;
	this.insert(index, obj);

	return index;
};

/**
 * JSONObject 객체를 받아 임의의 지정된 위치에 Row를 추가함
 * @param obj(Object)
 * @returns index(int)
 * @author 권준호
 * @since  2013-03-28
 * @version 1.0
 */
DataSet.prototype.insert = function(index, obj) {
	var dataSetRow = (typeof (obj) != "undefined") ? new DataSet.DataSetRow(obj) : new DataSet.DataSetRow();
	var privDataSetRow;
	dataSetRow.setStatus("INSERT");

	if (index > this._view.length) {
		log("범위를 초과하였음.");
		return -1;
	}
	if(index < this._view.length){
		this._view.splice(index, 0, dataSetRow);
		privDataSetRow = this._view[index-1];
		var privIndex = -1;
		for(var i = 0 ; i < this._rows.length ; i++){
			if(privDataSetRow == this._rows[i]){
				privIndex = i;
				break;
			}
		}

		this._rows.splice((privIndex+1), 0, dataSetRow);
	}else{
		this._view.push(dataSetRow);
		this._rows.push(dataSetRow);
	}

	if(!this.isSetAllData){
		this._binder.posAdd(index, dataSetRow, privDataSetRow);
		this.setPosition(index, true);
	}

	return index;
};

/**
 * index를 받아 해당 데이터 로우를 삭제함.
 * (기존에 존재한 Row는 상태값을 DELETE로 변경후 deleteRow에 삽입하고, add된 데이터로우는 그냥 소멸시킴)
 * @param index(int)
 * @author 권준호
 * @since  2013-03-28
 * @version 1.0
 */
DataSet.prototype.remove = function(index) {
	var dataSetRow = null;
	var rowIndex = -1;

	if(index instanceof DataSet.DataSetRow){
		dataSetRow = index;
		for(var i = 0 ; i < this._view.length ; i++){
			if(this._view[i] == dataSetRow){
				index = i;
				break;
			}
		}
	}else{
		dataSetRow = this._view[index];
	}

	for(var i = 0 ; i < this._rows.length ; i++){
		if(this._rows[i] == dataSetRow){
			rowIndex = i;
			break;
		}
	}

	if (dataSetRow.getStatus() != "INSERT") {
		dataSetRow.remove();
		this._deleteRow.push(dataSetRow);
	}
	this._rows.remove(rowIndex, rowIndex);
	this._view.remove(index, index);
	if(rowIndex < 0)
		return;
	this._binder.posDel(index, dataSetRow);
	this._pos = this._pos < 1 ? 0 : this._pos;
	this.setPosition(this._pos - 1, true);
};

/**
 * 모든 데이터를 제거하고 데이터셋을 초기화함.
 * @author 권준호
 * @since  2013-03-28
 * @version 1.0
 */
DataSet.prototype.clear = function() {
	this._filter = null;
	this._rows = new Array();
	this._deleteRow = new Array();
	this._view = new Array();
	this._pos = -1;
	this._binder.drow();
};

/**
 * 특정 Row의 Column의 데이터를 변경함
 * @param index(int)
 * @param key(string)
 * @param value(object)
 * @author 권준호
 * @since  2013-03-28
 * @version 1.0
 */
DataSet.prototype.set = function(index, key, value) {
	var isChanged = (value != this.view()[index].get(key));

	try{
		this.view()[index].set(key, value);
		if(isChanged) this._binder.posDrow(index, key, value);
	}catch(e){

	}
};

/**
 * 특정 Row의 위치를 이동시킴.
 * @param from(int)
 * @param to(int)
 * @author 권준호
 * @since  2013-03-28
 * @version 1.0
 */
DataSet.prototype.move = function(from, to) {
	var fromViewRow = this.view()[from];
	var toViewRow = this.view()[to];
	var fromIndex = null;
	var toIndex = null;
	var rows = this._rows;

	for(var i = 0 ; i < rows.length ; i++){
		if(fromViewRow != null && toViewRow != null)
			break;

		if(fromViewRow == rows[i])
			fromIndex = i;
		else if(toViewRow == rows[i])
			toIndex = i;
	}
	this._move(this.view(), from, to);
	this._move(rows, fromIndex, toIndex);
	this._binder.drow();
	this.setPosition(to, true);
};

/**
 * 특정 Row의 위치를 이동시킴.
 * @param arr(Array)
 * @param from(int)
 * @param to(int)
 * @author 권준호
 * @since  2013-03-28
 * @version 1.0
 */
DataSet.prototype._move = function(arr,form, to) {
	var temp = arr[form];
	arr[form] = arr[to];
	arr[to] = temp;
};

/**
 * 특정 Row의 Column의 데이터를 출력함
 * @param index(int)
 * @param key(string)
 * @returns Dataset.DataSetRow
 * @author 권준호
 * @since  2013-03-28
 * @version 1.0
 */
DataSet.prototype.get = function(index, key) {
	if(typeof(index) == "undefined" || index < 0 || index >= this.view().length)
		throw "잘못된 Index입니다.";

	if (typeof (key) == "undefined")
		return this.view()[index].get();
	return this.view()[index].get(key);
};
DataSet.prototype.getStatus = function(index) {
	if(typeof(index) == "undefined" || index < 0 || index >= this.view().length)
		throw "잘못된 Index입니다.";

	return this.view()[index].getStatus();
};
DataSet.prototype.isUpdate = function() {
	for(var i = 0 ; i < this._rows.length ; i++){
		if(this._rows[i].getStatus() != "NORMAL")
			return true;
	}

	return false;
};

/**
 * 특정 Row의 Column의 데이터를 출력함
 * @param index(int)
 * @param key(string)
 * @returns Dataset.DataSetRow
 * @author 권준호
 * @since  2013-03-28
 * @version 1.0
 */
DataSet.prototype.clone = function(index) {
	if(typeof(index) == "undefined" || index < 0 || index >= this.view().length)
		throw "잘못된 Index입니다.";

	if (typeof (key) == "undefined")
		return this.view()[index].clone();
	return null;
};

/**
 * 특정 Row의 Column의 오리지널 데이터를 출력함
 * @param index(int)
 * @param key(string)
 * @returns Dataset.DataSetRow
 * @author 권준호
 * @since  2013-03-28
 * @version 1.0
 */
DataSet.prototype.getOrg = function(index, key) {
	if (typeof (key) == "undefined")
		return this.view()[index].getOrg();
	return this.view()[index].getOrg(key);
};;

/**
 * 생존해 있는 모든 데이터를 복사하여 출력함.
 * @returns rows(JSONArray)
 * @author 권준호
 * @since  2013-03-28
 * @version 1.0
 */
DataSet.prototype.getAll = function() {
	var rows = new Array();
	for ( var i = 0; i < this._rows.length; i++) {
		rows.push(DataSet.Util.objectClone(this._rows[i].get()));
	}
	return rows;
};

/**
 * 필터링된 모든 데이터를 복사하여 출력함.
 * @returns rows(JSONArray)
 * @author 권준호
 * @since  2013-03-28
 * @version 1.0
 */
DataSet.prototype.getViewAll = function() {
	var rows = new Array();
	for ( var i = 0; i < this._view.length; i++) {
		rows.push(DataSet.Util.objectClone(this._view[i].get()));
	}
	return rows;
};

/**
 * 모든 오리지널 데이터를 복사하여 출력함.
 * @returns rows(JSONArray)
 * @author 권준호
 * @since  2013-03-28
 * @version 1.0
 */
DataSet.prototype.getOrgAll = function() {
	var rows = new Array();
	for ( var i = 0; i < this._rows.length; i++) {
		rows.push(DataSet.Util.objectClone(this._rows[i].getOrg()));
	}
	for ( var i = 0; i < this._deleteRow.length; i++) {
		rows.push(DataSet.Util.objectClone(this._deleteRow[i].getOrg()));
	}
	return rows;
};

/**
 * 서버에 전송할때 사용할 데이터를 출력함
 * 		- type이 없을 경우 생존해 있는 모든 데이터를 NORMAL로 출력함.
 * 		- type이 "U"인 경우 현재 데이터 상태를 출력함.
 * @param type(string)
 * @returns rows(JSONArray)
 * @author 권준호
 * @since  2013-03-28
 * @version 1.0
 */
DataSet.prototype.getAllData = function(type) {
	var rows = new Array();
	var orgRows = new Array();
	var status = new Array();
	var rsl = {
		data : rows,
		orgData : orgRows,
		rowStatus : status
	};

	(function(that){
		if (typeof (type) == "undefined" || type == "" || type == "N") {
			for ( var i = 0; i < that._rows.length; i++) {
				var row = DataSet.Util.objectClone(that._rows[i].get());
				row["rowStatus"] = "NORMAL";
				rows.push(row);
				//status.push("NORMAL");
			}
		} else if (type.toUpperCase() == "U" || type.toUpperCase() == "A") {
			for ( var i = 0; i < that._deleteRow.length; i++) {
				var row = DataSet.Util.objectClone(that._deleteRow[i].getOrg());
				row["rowStatus"] = "DELETE";
				rows.push(row);
			}
			for ( var i = 0; i < that._rows.length; i++) {
				if (that._rows[i].getStatus() == "NORMAL" && type == "U")
					continue;

				var row = DataSet.Util.objectClone(that._rows[i].get());
				row["rowStatus"] = that._rows[i].getStatus();
				if(row["rowStatus"] == "UPDATE")
					row["jsOrignRowData"] = DataSet.Util.objectClone(that._rows[i].getOrg());
				rows.push(row);
			}
		} else if (type.toUpperCase() == "I") {
			for ( var i = 0; i < that._rows.length; i++) {
				var row = DataSet.Util.objectClone(that._rows[i].get());
				row["rowStatus"] = "INSERT";
				rows.push(row);
				//status.push("NORMAL");
			}
		}
	})(this);
	return rows;
};

/**
 * 데이터를 처음 상태로 되돌림.
 * -- 주의사항 : 데이터의 처음 위치를 보장하진 않음.
 * -- 향후 상황에 따라 Array를 이용하여 위치정보를 담아두는 로직으로 변경하여 위치보장 가능함.
 * @author 권준호
 * @since  2013-03-28
 * @version 1.0
 */
DataSet.prototype.reset = function() {
	if(this._deleteRow.length > 0){
		this._rows = this._rows.concat(this._deleteRow);
		this._deleteRow = new Array();
	}

	for(var i = this._rows.length-1 ; i >= 0 ; i--){
		if(this._rows[i].getStatus() == "INSERT"){
			this._rows.remove(i, i);
		}else if(this._rows[i].getStatus() == "UPDATE"){
			this._rows[i].reset();
		}
	}
	this._filtering();
	this._binder.drow(true);
};

/**
 * Filtering 될 callBack 함수를 지정함.
 * @param filter(function)
 * @author 권준호
 * @since  2013-03-28
 * @version 1.0
 */
DataSet.prototype.filter = function(filter) {
	this._filter = (typeof (filter) == "undefined") ? null : filter;
	this._filtering();
};

/**
 * CallBack 함수에 의해 데이터가 Filtering 됨.
 * @author 권준호
 * @since  2013-03-28
 * @version 1.0
 */
DataSet.prototype._filtering = function(isLoad) {
	isLoad = typeof(isLoad) == "undefined" ? false: isLoad;

	var startTime = (new Date()).getTime();

	this._view = new Array();
	var tempRow = null;
	for ( var i = 0; i < this._rows.length; i++) {
		tempRow = this._rows[i];
		if (this._filter == null || this._filter(tempRow))
			this._view.push(this._rows[i]);
	}
	this._pos = (this._pos - 1) < 0? -1 : (this._pos - 1);
	//try{console.log("Data Filtering Time : " + ((new Date()).getTime() - startTime));}catch(e){}
	this._binder.drow(isLoad);
};

/**
 * 바이딩 혹은 필터에 의해 보여질 가상 데이터 Row 출력함.
 * 		-- 주의사항 : 실제 데이터셋의 데이터Row가 Call By Reference  --
 * @author 권준호
 * @since  2013-03-28
 * @version 1.0
 */
DataSet.prototype.view = function() {
	return this._view;
};

/**
 * 바이딩 혹은 필터에 의해 보여질 가상 데이터 Row 출력함.
 * @returns size(int)
 * @author 권준호
 * @since  2013-03-28
 * @version 1.0
 */
DataSet.prototype.size = function() {
	return this._view.length;
};

/**
 * 현재 데이터 상태를 오리지널 데이터로 변경.
 * @returns size(int)
 * @author 권준호
 * @since  2013-03-28
 * @version 1.0
 */
DataSet.prototype.apply = function() {
	this._filter = null;
	this._deleteRow = new Array();
	this._view = new Array();

	for(var i = 0 ; i < this._rows.length ; i++){
		this._rows[i].apply();
	}
	this._filtering();
	this.setPosition(-1, true);
};

/**
 * 검색할 컬럼의 값이 일치하는 Index.
 * 		-- 조회된 데이터가 없을시  -1을 출력함.
 * @param colNm(string)
 * @param value(string)
 * @returns index(int)
 * @author 권준호
 * @since  2013-03-28
 * @version 1.0
 */
DataSet.prototype.find = function(colNm, value) { //
	for(var i = 0 ; i < this.view().length; i++){
		if(this.view()[i].get(colNm) == value)
			return i;
	}
	return -1;
};

/**
 * 검색할 컬럼의 값이 일치하는 Row의 DataSetRow를 출력함.
 * 		-- 조회된 데이터가 없을시  'undefined'을 출력함.
 * @param colNm(string)
 * @param value(string)
 * @returns row(Dataset.DataSetRow)
 * @author 권준호
 * @since  2013-03-28
 * @version 1.0
 */
DataSet.prototype.findRow = function(colNm, value) { //
	for(var i = 0 ; i < this.view().length; i++){
		if(this.view()[i].get(colNm) == value)
			return this.view()[i];
	}
};

/**
 * 데이터셋에 HTML Element를 바인드 시킴
 * @param obj(HTMLElement)
 * @param option(JSONObject)
 * @author 권준호
 * @since  2013-03-28
 * @version 1.0
 */
DataSet.prototype.bind = function(obj, option){
	this._binder.add(obj, option);
};

/**
 * 데이터셋에 HTML Element를 바인드 해제시킴
 * @param obj(HTMLElement)
 * @param option(JSONObject)
 * @author 권준호
 * @since  2013-03-28
 * @version 1.0
 */
DataSet.prototype.unBind = function(obj){
	this._binder.remove(obj);
};


/**
 * @class 데이터셋 Row
 * @param JSONObject
 * @returns this{DataSet.DataSetRow}
 * @author 권준호
 * @since  2013-03-28
 * @version 1.0
 */
DataSet.DataSetRow = function(obj) {	// 데이터 Row
	this._data = {};
	this._orgData = null;
	this._rowStatus = "NORMAL";

	if (typeof (obj) != "undefined"){
		this._data = DataSet.Util.objectClone(obj);
		for(var key in this._data){
			if(this._data[key] == null) this._data[key] = "";
		}
	}
};

/**
 * 데이터Row의 특정 컬럼 값을 수정함
 * @param key(string)
 * @author 권준호
 * @since  2013-03-28
 * @version 1.0
 */
DataSet.DataSetRow.prototype.set = function(key, value) {
	if (value == this._data[key])
		return;
	else if (value != this._data[key] && this._orgData == null) {
		this._orgData = DataSet.Util.objectClone(this._data);
	}

	this._data[key] = value;

	this.setStatus("UPDATE");

	if (this.equals()) {
		this._orgData = null;
		this.setStatus("NORMAL");
	}

};

/**
 * 현재 데이터와 오리지널 데이터를 비교함
 * @returns equals(boolean)
 * @author 권준호
 * @since  2013-03-28
 * @version 1.0
 */
DataSet.DataSetRow.prototype.equals = function() {
	if (this._orgData == null)
		return true;
	for ( var key in this._data) {
		if (this._data[key] != this._orgData[key])
			return false;
	}
	return true;
};

/**
 * 데이터Row의 상태를 변경함 ADD된 데이터일 경우는 삭제 되기 전까지 INSERT임.
 * @param status(string)
 * @author 권준호
 * @since  2013-03-28
 * @version 1.0
 */
DataSet.DataSetRow.prototype.setStatus = function(status) {
	if (this._rowStatus != "INSERT")
		this._rowStatus = status;
};

/**
 * 데이터Row의 상태를 출력함.
 * @returns status(string)
 * @author 권준호
 * @since  2013-03-28
 * @version 1.0
 */
DataSet.DataSetRow.prototype.getStatus = function() {
	return this._rowStatus;
};

/**
 * 데이터Row의 특정 컬럼 값을 출력함.
 * @param key(string)
 * @returns value(Object)
 * @author 권준호
 * @since  2013-03-28
 * @version 1.0
 */
DataSet.DataSetRow.prototype.get = function(key) {
	if (typeof (key) == "undefined")
		return this._data;

	return this._data[key];
};

DataSet.DataSetRow.prototype.clone = function() {

	return DataSet.Util.objectClone(this._data);
};

/**
 * 데이터Row의 특정 컬럼의 오리지널 값을 출력함
 * @param key(string)
 * @returns value(Object)
 * @author 권준호
 * @since  2013-03-28
 * @version 1.0
 */
DataSet.DataSetRow.prototype.getOrg = function(key) {
	if (typeof (key) == "undefined")
		return (this._orgData == null) ? this._data : this._orgData;

	return (this._orgData == null) ? this._data[key] : this._orgData[key];
};

/**
 * 데이터 Row를 삭제함. 상태값만 변경
 * @author 권준호
 * @since  2013-03-28
 * @version 1.0
 */
DataSet.DataSetRow.prototype.remove = function() {
	this._rowStatus = "DELETE";
};

/**
 * 데이터 RowStatus를 초기화하고 오리지널 데이터를 삭제.
 * @author 권준호
 * @since  2013-03-28
 * @version 1.0
 */
DataSet.DataSetRow.prototype.apply = function() {
	this._rowStatus = "NORMAL";
	this._orgData = null;
};

/**
 * 데이터 Row를 처음 상태로 되돌림.
 * @author 권준호
 * @since  2013-03-28
 * @version 1.0
 */
DataSet.DataSetRow.prototype.reset = function() {
	if(this._rowStatus == "INSERT")
		return;

	this._rowStatus = "NORMAL";
	this._data  = this.getOrg();
	this._orgData = null;
};

/**
 * @class 데이터셋 Bind
 * @param dataSet(DataSet)
 * @return this{DataSet.Bind}
 * @author 권준호
 * @since  2013-03-28
 * @version 1.0
 */
DataSet.Bind = function(dataSet){
	this.dataSet = dataSet;
	this.objs = [];
	this.isDrow = true;
};

DataSet.Bind.prototype = {
	/**
	 * HTML Element를 데이터셋에 바인드 시키고 데이터를 해당 HTML Element에 출력함
	 * @param obj(HTMLElement)
	 * @param option(JSONObject)
	 * @author 권준호
	 * @since  2013-03-28
	 * @version 1.0
	 */
	add: function(obj, option){
		var bindObj = null;
		if(typeof(obj) == "undefined"){
			throw "객체가 올바르게 지정되지 않았습니다.";
		}

		for(var i = 0 ; i < this.objs.length; i++){
			if(this.objs[i].equals(obj))
				return;
		}

		for(var bind in DataSet.Bind){
			if(DataSet.Bind[bind].isBind(obj)){
				bindObj = new DataSet.Bind[bind](obj, this.dataSet, option);
				break;
			}
		}

		if(bindObj == null)
			throw "해당 객체에 대한 Bind가 존재 하지 않습니다.";

		this.objs.push(bindObj);
		bindObj.attach();
		bindObj.drow();
	},
	/**
	 * HTML Element를 데이터셋에 바인드 해제함.
	 * @param obj(HTMLElement)
	 * @author 권준호
	 * @since  2013-03-28
	 * @version 1.0
	 */
	remove: function(obj){
		var i;
		for(i = 0 ; i < this.objs.length; i++){
			if(this.objs[i].obj == obj)
				break;
		}
		if(this.objs.length == i)
			return;
		var bindObj = this.objs[i];
		bindObj.detach();
		this.objs.remove(i, i);
	},
	/**
	 * 바인드 된 HTML Element에 데이터를 출력함
	 * @author 권준호
	 * @since  2013-03-28
	 * @version 1.0
	 */
	drow: function(isLoad){
		var objs = this.objs;
		for(var i = 0 ; i < objs.length ; i++)
			objs[i].drow(isLoad);
	},
	/**
	 * 특정 DataSetRow가 변경 되었을때. 해당 Row의 데이터를 HTML Element에 출력함
	 * @param index(int)
	 * @param key(String)
	 * @param value(Object)
	 * @author 권준호
	 * @since  2013-03-28
	 * @version 1.0
	 */
	posDrow: function(index, key, value){
		if(this.isDrow == false)
			return ;

		this.isDrow = false;

		var objs = this.objs;
		try{
			for(var i = 0 ; i < objs.length ; i++){
				var startTime = (new Date()).getTime();
				//try{console.log(objs[i] + " : drow Start : ");}catch(e){}
				objs[i].posDrow(index, key, value);
				//try{console.log("drow Time : " + ((new Date()).getTime() - startTime));}catch(e){}
			}
		}catch(e){}
		this.isDrow = true;

	},
	/**
	 * 특정 DataSetRow가 추가 되었을때. 해당 Row의 데이터를 HTML Element에 출력함
	 * @param index(int)
	 * @param dataSetRow(DataSet.DataSetRow)
	 * @param privDataSetRow(DataSet.DataSetRow)
	 * @author 권준호
	 * @since  2013-03-28
	 * @version 1.0
	 */
	posAdd: function(index, dataSetRow, privDataSetRow){
		var objs = this.objs;
		for(var i = 0 ; i < objs.length ; i++)
			objs[i].posAdd(index, dataSetRow, privDataSetRow);
	},
	/**
	 * 특정 DataSetRow가 삭제 되었을때. 해당 Row의 데이터를 HTML Element에 출력함
	 * @param index(int)
	 * @param dataSetRow(DataSet.DataSetRow)
	 * @author 권준호
	 * @since  2013-03-28
	 * @version 1.0
	 */
	posDel: function(index, dataSetRow){
		var objs = this.objs;
		for(var i = 0 ; i < objs.length ; i++)
			objs[i].posDel(index, dataSetRow);
	},
	saveRows : function(){
		var objs = this.objs;
		for(var i = 0 ; i < objs.length ; i++){
			if(objs[i] instanceof DataSet.Bind.Grid){
				objs[i]._setSave();
			}
		}
	}
};

/**
 * @class 데이터셋 Bind
 * @param grid(jQuery.jqGrid)
 * @param dataSet(DataSet)
 * @return this{DataSet.Bind.Grid}
 * @author 권준호
 * @since  2013-03-28
 * @version 1.0
 */
DataSet.Bind.Grid = function(grid, dataSet, option){
	this.grid = this.obj = grid;
	this.dataSet = dataSet;
	this.editRowId = null;
	this._oldRowId = null;
	this._curPage = 1;
	this.emptyBox = null;
	this.option = {
		isEmptyText : false,	// 조회된 데이터가 없을경우 메시지를 남길지 여부
		emptyText: "데이터가 존재하지 않습니다.",	// 조회된 데이터가 없을 경우 표시될 메시지
		editType : "doubleClick" // 에디터 모드로 TYPE : doubleClick, click
	};

	if(typeof(option) != "undefined"){
		for(var key in option){
			this.option[key] = option[key];
		}
	}
	this.emptyBox = $("<div style=\"width: 100%; text-align: center; padding-top: 20px;\">"+this.option.emptyText+"</div>");

	this._onSelectRow = null;
	this._onCellSelect = null;
	this._ondblClickRow = null;
	this._onPaging = null;
};
DataSet.Bind.Grid.isBind = function(obj){
	if(typeof(obj.jqGrid) == "function" )
		return true;
	return false;
};
DataSet.Bind.Grid.prototype = {
	equals: function(obj){
		try{
			return this.obj.get(0) == obj.get(0);
		}catch(e){
			return false;
		}
	},
	/**
	 * 데이터 바인딩을 위해 이벤트를 추가함.
	 * @author 권준호
	 * @since  2013-03-28
	 * @version 1.0
	 */
	attach: function(){
		var grid = this.grid;
		var _that = this;

		var gridParam = grid.jqGrid('getGridParam');
		var _onSelectRow = this._onSelectRow = gridParam.onSelectRow;
		var _onCellSelect = this._onCellSelect = gridParam.onCellSelect;
		var _ondblClickRow = this._ondblClickRow = gridParam.ondblClickRow;
		var _onPaging = this._onPaging = gridParam.onPaging;
		var _oldPos;

		this._oldRowId = null;

		var event = {
			onSelectRow: function(rowid, status, e){
				var selarrrow = gridParam.selarrrow;

				if(gridParam.multiselect){
					if(selarrrow.indexOf(rowid) > -1){
						_that._setDataPosition(rowid, status, e);
					}else if(selarrrow.indexOf(rowid) == -1 && selarrrow.length > 0){
						_that._setDataPosition(selarrrow[0]);
					}else if(selarrrow.length == 0 && _that.dataSet.getPosition() > -1){
						_that.dataSet.setPosition(-1);
					}else{
						return;
					}

					if(typeof(_onSelectRow) != "undefined" && _onSelectRow != null)
						_onSelectRow(rowid, status, e);
				}else{
					if(_that._oldRowId == rowid)
						return;

					_that._oldRowId = rowid;
					_that._setDataPosition(rowid);
					if(typeof(_onSelectRow) != "undefined" && _onSelectRow != null)
						_onSelectRow(rowid, status, e);
				}


			},
			onCellSelect : function(rowid, iCol, cellcontent, e){

				if(this.editRowId != rowid)
		    		_that._setSave();

		    	if(_that.option.editType == "oneClick")
		    		_that._setEditable(rowid);

				if(typeof(_onCellSelect) != "undefined" && _onCellSelect != null)
					_onCellSelect(rowid, iCol, cellcontent, e);
			},
		    ondblClickRow: function(rowid, iRow, iCol, e){
		    	if(_that.option.editType == "doubleClick")
		    		_that._setEditable(rowid, iRow, iCol, e);

				if(typeof(_ondblClickRow) != "undefined" && _ondblClickRow != null)
					_ondblClickRow(rowid, iRow, iCol, e);

		     },
		     onPaging: function(pgButton){
		    	 _that._curPage = grid.jqGrid("getGridParam", "page");
				if(typeof(_onPaging) != "undefined" && _onPaging != null)
					_onPaging(pgButton);
		     }
		}

		grid.jqGrid('setGridParam', event);

	},
	/**
	 * 데이터 바인딩을 위해 이벤트를 해제함.
	 * @author 권준호
	 * @since  2013-03-28
	 * @version 1.0
	 */
	detach: function(){
		var _onSelectRow = this._onSelectRow;
		var _onCellSelect = this._onCellSelect;
		var _ondblClickRow = this._ondblClickRow;
		var _onPaging = this._onPaging;

		grid.jqGrid('setGridParam', {
			onSelectRow: _onSelectRow,
			onCellSelect: _onCellSelect,
		    ondblClickRow: _ondblClickRow,
		    onPaging: _onPaging
		});

		this.grid = null;
		this.dataSet = null;
		this.editRowId = null;
		this._oldRowId = null;
		this._onSelectRow = null;
		this._ondblClickRow = null;
		this._onPaging = null;

	},
	/**
	 * 바인드 된 HTML Element에 데이터를 출력함
	 * @author 권준호
	 * @since  2013-03-28
	 * @version 1.0
	 */
	drow: function(isLoad){
		var grid = this.grid;
		var viewList = this.dataSet.view();
		var dataSet = this.dataSet;
		var that = this;
		var option = this.option;
		var isEmptyText = option.isEmptyText;

		this._oldRowId = null;

		var startTime = (new Date()).getTime();
		grid.parent().parent().find(this.emptyBox).remove();
		grid.clearGridData();  // 이전 데이터 삭제
		grid.removeData();
		grid.data("nextVal", 1);
		for(var i=0;i<viewList.length;i++){
			grid.data(""+grid.data("nextVal"), viewList[i]);
			grid.jqGrid('addRowData', grid.data("nextVal"), viewList[i].get());
			grid.data("nextVal", grid.data("nextVal")+1);
		}
		//try{console.log("Grid Data Drow Time : " + ((new Date()).getTime() - startTime));}catch(e){}
		if(grid.jqGrid("getGridParam", "pager") != "" && !isLoad){
			grid.jqGrid("setGridParam", "page", that._curPage);
			grid.jqGrid("setGridParam").trigger("reloadGrid");
		}else if(grid.jqGrid("getGridParam", "pager") != "" && isLoad){
			that._curPage = 1;
			grid.jqGrid("setGridParam", "page", that._curPage);
			grid.jqGrid("setGridParam").trigger("reloadGrid");
			that._setPosition();
		}else{
			that._setPosition();
		}

		if(dataSet.size() == 0 && isEmptyText){
			grid.parent().parent().append(this.emptyBox);
		}
	},
	/**
	 * 특정 DataSetRow가 변경 되었을때. 해당 Row의 데이터를 Grid에 출력함
	 * @param index(int)
	 * @param key(String)
	 * @param value(Object)
	 * @author 권준호
	 * @since  2013-03-28
	 * @version 1.0
	 */
	posDrow: function(index, key, value){
		//var selrow = $( "#testList" ).getGridParam( "selrow" );
		var grid = this.grid;
		var gridParam = grid.jqGrid('getGridParam');

		if(typeof(key) == "undefined"){
			if(index  < 0 ){
				this._oldRowId = null;
				//grid.getGridParam( "selrow" , null);
				grid.jqGrid('setSelection',null);
			}else{
				var view = this.dataSet.view()[index];
				var datas = grid.data();

				for(var rowId in datas){
					if(datas[rowId] == view && this.editRowId != rowId){
						var selarrrow = gridParam.selarrrow;
						if(gridParam.multiselect && selarrrow.indexOf(rowId) == -1){
							grid.jqGrid('setSelection', rowId);
						}else if(!gridParam.multiselect){
							grid.jqGrid('setSelection', rowId);
						}

						grid.jqGrid('setRowData', rowId, view.get());
						return;
					}
				}
			}
		}else{
			var view = this.dataSet.view()[index];
			var datas = grid.data();
			var rowId = null;
			for(rowId in datas){
				if(datas[rowId] == view)
					break;
			}
			grid.jqGrid('setCell', rowId, key, value);

		}
	},
	/**
	 * 특정 DataSetRow가 추가 되었을때. 해당 Row의 데이터를 Grid에 출력함
	 * @param index(int)
	 * @param dataSetRow(DataSet.DataSetRow)
	 * @param privDataSetRow(DataSet.DataSetRow)
	 * @author 권준호
	 * @since  2013-03-28
	 * @version 1.0
	 */
	posAdd: function(index, dataSetRow, privDataSetRow){
		this.drow();
	},
	/**
	 * 특정 DataSetRow가 삭제 되었을때. 해당 Row의 데이터를 HTML Element에 출력함
	 * @param index(int)
	 * @param dataSetRow(DataSet.DataSetRow)
	 * @author 권준호
	 * @since  2013-03-28
	 * @version 1.0
	 */
	posDel: function(index, dataSetRow){
		var grid = this.grid;
		var datas = grid.data();
		for(var rowId in datas){
			if(datas[rowId] == dataSetRow){
				grid.jqGrid('delRowData', rowId);
				break;
			}
		}
	},
	/**
	 * 선택된 Row의 위치를 DataSet의 위치와 동기화시킴.
	 * @param rowId(String)
	 * @param status(boolean)
	 * @param e(eventObject)
	 * @author 권준호
	 * @since  2013-03-28
	 * @version 1.0
	 */
	_setDataPosition: function(rowId, status, e){
		this._setPosition(rowId);
		//this.grid.jqGrid('editRow',rowid, keys, oneditfunc, successfunc, url, extraparam, aftersavefunc,errorfunc, afterrestorefunc);
	},
	/**
	 * 선택된 Row를 에디터 모드로 변경함.
	 * @param rowId(String)
	 * @param iRow(boolean)
	 * @param iCol(boolean)
	 * @param e(eventObject)
	 * @author 권준호
	 * @since  2013-03-28
	 * @version 1.0
	 */
	_setEditable: function(rowId, iRow, iCol, e){
		var _that = this;
		var grid = this.grid;
		this.editRowId = rowId;

		grid.jqGrid('editRow',rowId,{
			keys : true,
			url : "clientArray",
			aftersavefunc: function(rowId, flag, option){
				_that._setSave();
			}

		});
	},
	/**
	 * 에디터가 끝난후 데이터를 DataSet에 동기화함.
	 * @author 권준호
	 * @since  2013-03-28
	 * @version 1.0
	 */
	_setSave: function(){
		var grid = this.grid;
		var dataSet = this.dataSet;
		var viewList = this.dataSet.view();
		var editRowId = this.editRowId;
		this.editRowId = null;

		if(editRowId == null)
			return;

		grid.jqGrid('saveRow',editRowId,{
			url : "clientArray"
		});

		var rowDatas = grid.jqGrid("getRowData", editRowId);
		var dataSetRow = grid.data(editRowId);
		for(var key in rowDatas){
			dataSetRow.set(key, rowDatas[key]);
		}

		dataSet._binder.posDrow(viewList.indexOf(dataSetRow));
	},
	/**
	 * 선택된 Row의 위치를 DataSet의 위치와 동기화시킴.
	 * @param rowId(String)
	 * @author 권준호
	 * @since  2013-03-28
	 * @version 1.0
	 */
	_setPosition: function(rowId){
		var grid = this.grid;
		var dataSet = this.dataSet;
		var viewList = this.dataSet.view();
		var selrow = typeof(rowId) == "undefined" ? grid.getGridParam( "selrow" ): rowId;
		var gridParam = grid.jqGrid('getGridParam');

		if(this.editRowId != rowId && !gridParam.multiselect)
			this._setSave();

		if(selrow == null){
			dataSet.setPosition(-1);
		}else{
			var dataSetRow = grid.data(selrow);
			if(dataSet.getPosition() != viewList.indexOf(dataSetRow))
				dataSet.setPosition(viewList.indexOf(dataSetRow));
		}
	}
};

/**
 * @class 데이터셋 Bind
 * @param form(HTMLFormElement)
 * @param dataSet(DataSet)
 * @return this{DataSet.Bind.Form}
 * @author 권준호
 * @since  2013-03-28
 * @version 1.0
 */
DataSet.Bind.Form = function(form, dataSet){
	this.form = this.obj = form;
	this.dataSet = dataSet;
	this.elms = [];
};
DataSet.Bind.Form.isBind = function(obj){
	if(typeof(obj.tagName) != "undefined" && obj.tagName.toLowerCase() == "form")//obj instanceof HTMLFormElement)
		return true;
	return false;
};
DataSet.Bind.Form.prototype = {
	equals: function(obj){
		return this.obj == obj;
	},
	/**
	 * 데이터 바인딩을 위해 이벤트를 추가함.
	 * @author 권준호
	 * @since  2013-03-28
	 * @version 1.0
	 */
	attach: function(){
		var form = this.form;
		var inputs = form.getElementsByTagName("input");
		var textareas = form.getElementsByTagName("textarea");
		var selects = form.getElementsByTagName("select");
		var elms = this.elms = [];  //inputs.concat(textareas).concat(selects);
		//var pos = this.dataSet.getPosition();
		//var dataObj;// = this.dataSet.get(pos); //
		//var cols = []; //향후 컬럼 정보가 추가 되면 DataSetColumn으로 대체한다.;
		var _that = this;

		for(var i = 0 ; i < inputs.length ; i++)
			if(inputs[i].type != "button")elms.push(inputs[i]);
		for(var i = 0 ; i < selects.length ; i++)
			elms.push(selects[i]);
		for(var i = 0 ; i < textareas.length ; i++)
			elms.push(textareas[i]);

		/*for(var key in dataObj){
			cols.push(key);
		}*/

		for(var i = 0 ; i < elms.length ; i++){
			elm = elms[i];
			elmNm = elm.name;
			//if(cols.indexOf(elmNm) < 0) continue;
			$(elm).bind("change", function(e){_that._changedValue(e);});
		}

	},
	/**
	 * 데이터 바인딩을 위해 이벤트를 해제함.
	 * @author 권준호
	 * @since  2013-03-28
	 * @version 1.0
	 */
	detach: function(){
		var _that = this;
		var elms = this.elms;

		for(var i = 0 ; i < elms.length ; i++){
			tagName = elms[i].tagName.toLowerCase();
			elm = elms[i];
			elmNm = elm.name;

			$(elm).unbind("change", function(e){_that._changedValue(e);});
		}
		this.form = null;
		this.dataSet = null;
		this.elms = null;
	},
	/**
	 * 바인드 된 HTML Element에 데이터를 출력함
	 * @author 권준호
	 * @since  2013-03-28
	 * @version 1.0
	 */
	drow: function(){
		var elms = this.elms;  //inputs.concat(textareas).concat(selects);
		var elm, elmNm;
		var pos = this.dataSet.getPosition();
		var dataObj;
		var cols = []; //향후 컬럼 정보가 추가 되면 DataSetColumn으로 대체한다.;

		try{
			dataObj = this.dataSet.get(pos);
		}catch(e){
			dataObj = null;
		}

		for(var key in dataObj){
			cols.push(key);
		}

		for(var i = 0 ; i < elms.length ; i++){
			tagName = elms[i].tagName.toLowerCase();
			elm = elms[i];
			elmNm = elm.name;
			if(cols.indexOf(elmNm) < 0) continue;

			if(dataObj != null)
				this._setValue(elm, dataObj[elmNm]);
			else
				this._setValue(elm, "");
		}
	},
	/**
	 * 특정 DataSetRow가 변경 되었을때. 해당 Row의 데이터를 Form.child에 출력함
	 * @param index(int)
	 * @param key(String)
	 * @param value(Object)
	 * @author 권준호
	 * @since  2013-03-28
	 * @version 1.0
	 */
	posDrow: function(index, key, value){
		var elms = this.elms;
		var cols = []; //향후 컬럼 정보가 추가 되면 DataSetColumn으로 대체한다.
		var elm, elmNm;

		if(typeof(key) == "undefined"){
			if(index > -1){
				var dataObj = this.dataSet.get(index);
				for(var key in dataObj){
					cols.push(key);
				}
				for(var i = 0 ; i < elms.length ; i++){
					elm = elms[i];
					elmNm = elm.name;

					this._setValue(elm, dataObj[elmNm]);
				}
			}else{
				if(typeof(index) == "undefined" || this.dataSet.size() < 1)
					return;
				var dataObj = this.dataSet.get(0);
				for(var key in dataObj){
					cols.push(key);
				}
				for(var i = 0 ; i < elms.length ; i++){
					elm = elms[i];
					elmNm = elm.name;

					this._setValue(elm, "");
				}
			}

		}else{

			for(var i = 0 ; i < elms.length ; i++){
				if(elms[i].name == key){
					this._setValue(elms[i], value);
				}
			}
		}
	},
	/**
	 * 특정 DataSetRow가 추가 되었을때. 해당 Row의 데이터를 Form.child에 출력함
	 * @param index(int)
	 * @param dataSetRow(DataSet.DataSetRow)
	 * @param privDataSetRow(DataSet.DataSetRow)
	 * @author 권준호
	 * @since  2013-03-28
	 * @version 1.0
	 */
	posAdd: function(index, dataSetRow){
	},
	/**
	 * 특정 DataSetRow가 삭제 되었을때. 해당 Row의 데이터를 HTML Element에 출력함
	 * @param index(int)
	 * @param dataSetRow(DataSet.DataSetRow)
	 * @author 권준호
	 * @since  2013-03-28
	 * @version 1.0
	 */
	posDel: function(index, dataSetRow){
	},
	/**
	 * 바인드 된 Element의 값이 변경 되었을 경우. 해당 Row의 데이터를 동기화함
	 * @param e(eventObject)
	 * @author 권준호
	 * @since  2013-03-28
	 * @version 1.0
	 */
	_changedValue: function(e){
		if(this.form == null){
			return;
		}

		var elm = e.target;
		var pos = this.dataSet.getPosition();
		var value = elm.value;
		var elmNm = elm.name;
		var type = elm.type.toLowerCase();

		if(pos < 0)
			return;
		else if(this.dataSet.get(pos).hasOwnProperty(elmNm) == false)
			return;


		if(type != "checkbox"){
			if(this.dataSet.get(pos, elmNm) == value)
				return;
			this.dataSet.set(pos, elmNm, value);
		}else{
			var values = new Array();
			for(var i = 0 ; i < this.elms.length ; i++){
				if(this.elms[i].name == elmNm && this.elms[i].checked)
					values.push(this.elms[i].value);
			}
			this.dataSet.set(pos, elmNm, values.toString());
		}
	},
	/**
	 * 데이터셋의 값이 변경되었을 경우 바인드된 Element의 값을 동기화함.
	 * @param e(eventObject)
	 * @author 권준호
	 * @since  2013-03-28
	 * @version 1.0
	 */
	_setValue: function(elm, value ){
		var tagName = elm.tagName.toLowerCase();
		if(tagName == "select"){
			if(elm.selectedIndex > -1 && elm.options[elm.selectedIndex].value != value){
				gf_SelectedValue(elm, value);
				$(elm).trigger("change");
			}
		}else if(elm.type == "radio"){
			if(elm.checked == (elm.value == value))
				return;
			elm.checked = (elm.value == value);

			if(elm.checked) $(elm).trigger("change");
		}else if(elm.type == "checkbox"){
			var values = value.split(",");
			if(elm.checked == (values.indexOf(elm.value) > -1))
				return;
			elm.checked = (values.indexOf(elm.value) > -1);
			//if(elm.checked) $(elm).trigger("change");
		}else{
			if(elm.value === value)
				return;
			elm.value = value;
			$(elm).trigger("change");
		}
	}
};

/**
 * @class 데이터셋 Bind
 * @param form(HTMLSelectElement)
 * @param dataSet(DataSet)
 * @return this{DataSet.Bind.Select}
 * @author 권준호
 * @since  2013-03-28
 * @version 1.0
 */
DataSet.Bind.Select = function(select, dataSet, option){
	this.select = this.obj = select;
	this.dataSet = dataSet;
	this.option = option;
};
DataSet.Bind.Select.isBind = function(obj){
	if(typeof(obj.tagName) != "undefined" && obj.tagName.toLowerCase() == "select")//obj instanceof HTMLSelectElement)
		return true;
	return false;
};
DataSet.Bind.Select.prototype = {
	equals: function(obj){
		return this.obj == obj;
	},
	/**
	 * 데이터 바인딩을 위해 이벤트를 추가함.
	 * @author 권준호
	 * @since  2013-03-28
	 * @version 1.0
	 */
	attach: function(){
	},
	/**
	 * 데이터 바인딩을 위해 이벤트를 해제함.
	 * @author 권준호
	 * @since  2013-03-28
	 * @version 1.0
	 */
	detach: function(){
		this.select = null;
		this.dataSet = null;
		this.option = null;
	},
	/**
	 * 바인드 된 HTML Element에 데이터를 출력함
	 * @author 권준호
	 * @since  2013-03-28
	 * @version 1.0
	 */
	drow: function(){
		var valNm = this.option.val;
		var textNm = this.option.text;
		var val, text;
		var options = this.select.options;

		options.length = 0; //options Clear
		for(var i = 0 ; i < this.dataSet.size(); i++){
			val = this.dataSet.get(i, valNm);
			text = this.dataSet.get(i, textNm);
			var option = new Option(text, val);
			options.add(option);
		}
	},
	/**
	 * 특정 DataSetRow가 변경 되었을때. 해당 Row의 데이터를 Select에 출력함
	 * @param index(int)
	 * @param key(String)
	 * @param value(Object)
	 * @author 권준호
	 * @since  2013-03-28
	 * @version 1.0
	 */
	posDrow: function(index, key, value){
		var valNm = this.option.val;
		var textNm = this.option.text;
		var val, text;
		var option;

		if(index < 0) return;

		option = this.select.options[index];

		if(typeof(key) == "undefined" && index > -1){

			val = this.dataSet.get(index, valNm);
			text = this.dataSet.get(index, textNm);
			option.value = val;
			option.text = text ;
		}else{
			if(valNm == key)
				option.value = value;
			else if(textNm == key)
				option.text = value ;
		}
	},
	/**
	 * 특정 DataSetRow가 추가 되었을때. 해당 Row의 데이터를 Select에 출력함
	 * @param index(int)
	 * @param dataSetRow(DataSet.DataSetRow)
	 * @param privDataSetRow(DataSet.DataSetRow)
	 * @author 권준호
	 * @since  2013-03-28
	 * @version 1.0
	 */
	posAdd: function(index, dataSetRow){
		this.drow();
	},
	/**
	 * 특정 DataSetRow가 삭제 되었을때. 해당 Row의 데이터를 HTML Element에 출력함
	 * @param index(int)
	 * @param dataSetRow(DataSet.DataSetRow)
	 * @author 권준호
	 * @since  2013-03-28
	 * @version 1.0
	 */
	posDel: function(index, dataSetRow){
		this.select.remove(index);
	}
};

/**
 * @class 데이터셋에서 필요한 .
 * @author 권준호
 * @since  2013-03-28
 * @version 1.0
*/

DataSet.Util = {};	// 데이터셋에서 사용할 Util

/**
 * 데이터 출력혹은 원본을 유지해야 할경우 데이터를 복사함.
 * @param obj(JSONObject)
 * @return clone(JSONObject)
 * @author 권준호
 * @since  2013-03-28
 * @version 1.0
 */
DataSet.Util.objectClone = function(obj) {
	var clone = {};
	var value = null;
	var key = null;

	for (key in obj) {
		value = obj[key];

		if (value == null) {
			clone[key] = value;
		}else if(typeof(value.tagName) == "undefined"){
			clone[key] = value;
		}else if (typeof value == 'object') {
			clone[key] = this.objectClone(value);
		} else {
			/*if(typeof(encode) != "undefined" && encode)
				clone[key] = encodeURIComponent( value);
			else*/
				clone[key] = value;
		}
	}

	return clone; //$.extend(true,{},obj);//
};

/**
 * 객체에 이벤트를 추가함.
 * @param elm(HTMLElement)
 * @param eventNm(String)
 * @param handler(function)
 * @author 권준호
 * @since  2013-03-28
 * @version 1.0
 */
DataSet.Util.addEvent = function(elm, eventNm, handler) {
	DataSet.Util.removeEvent(elm, eventNm, handler);

	if (elm.addEventListener) {
		elm.addEventListener(eventNm, handler);
	} else if (elm.attachEvent) {
		elm.attachEvent("on" + eventNm, handler);
	}
};

/**
 * 객체에 이벤트를 제거함.
 * @param elm(HTMLElement)
 * @param eventNm(String)
 * @param handler(function)
 * @author 권준호
 * @since  2013-03-28
 * @version 1.0
 */
DataSet.Util.removeEvent = function(elm, eventNm, handler) {
	if (elm.removeEventListener) {
		elm.removeEventListener(eventNm, handler);
	} else if (elm.detachEvent) {
		elm.detachEvent("on" + eventNm, handler);
	}
};
