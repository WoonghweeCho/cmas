if (typeof window.JSON !== 'object') {
	window.JSON = {};
}


(function() {

    var cx = /[\u0000\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,
        escapable = /[\\\"\x00-\x1f\x7f-\x9f\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,
        gap,
        indent,
        meta = {    // table of character substitutions
            '\b': '\\b',
            '\t': '\\t',
            '\n': '\\n',
            '\f': '\\f',
            '\r': '\\r',
            '"' : '\\"',
            '\\': '\\\\'
        },
        rep;

    function quote(string) {

        escapable.lastIndex = 0;
        return escapable.test(string) ? '"' + string.replace(escapable, function (a) {
            var c = meta[a];
            return typeof c === 'string'
                ? c
                : '\\u' + ('0000' + a.charCodeAt(0).toString(16)).slice(-4);
        }) + '"' : '"' + string + '"';
    }

	function str(key, holder) {
		var i, // The loop counter.
		k, // The member key.
		v, // The member value.
		length, mind = gap, partial, value = holder[key];
		if (value && typeof value === 'object'
				&& typeof value.toJSON === 'function') {
			value = value.toJSON(key);
		}
		if (typeof rep === 'function') {
			value = rep.call(holder, key, value);
		}
		switch (typeof value) {
		case 'string': return quote(value);
		case 'number': return isFinite(value) ? String(value) : 'null';
		case 'boolean':
		case 'null': return String(value);
		case 'object': if (!value) {
				return 'null';
			}

			gap += indent;
			partial = [];

			if (Object.prototype.toString.apply(value) === '[object Array]') {

				length = value.length;
				for (i = 0; i < length; i += 1) {
					partial[i] = str(i, value) || 'null';
				}

				v = partial.length === 0 ? '[]' : gap ? '[\n' + gap
						+ partial.join(',\n' + gap) + '\n' + mind + ']' : '['
						+ partial.join(',') + ']';
				gap = mind;
				return v;
			}

			if (rep && typeof rep === 'object') {
				length = rep.length;
				for (i = 0; i < length; i += 1) {
					if (typeof rep[i] === 'string') {
						k = rep[i];
						v = str(k, value);
						if (v) {
							partial.push(quote(k) + (gap ? ': ' : ':') + v);
						}
					}
				}
			} else {

				for (k in value) {
					if (Object.prototype.hasOwnProperty.call(value, k)) {
						v = str(k, value);
						if (v) {
							partial.push(quote(k) + (gap ? ': ' : ':') + v);
						}
					}
				}
			}

			v = partial.length === 0 ? '{}' : gap ? '{\n' + gap
					+ partial.join(',\n' + gap) + '\n' + mind + '}' : '{'
					+ partial.join(',') + '}';
			gap = mind;
			return v;
		}
	}

	window.JSON.stringify = function(value, replacer, space) {

		var i;
		gap = '';
		indent = '';

		if (typeof space === 'number') {
			for (i = 0; i < space; i += 1) {
				indent += ' ';
			}
		} else if (typeof space === 'string') {
			indent = space;
		}
		rep = replacer;
		if (replacer
				&& typeof replacer !== 'function'
				&& (typeof replacer !== 'object' || typeof replacer.length !== 'number')) {
			throw new Error('JSON.stringify');
		}
		return str('', {
			'' : value
		});
	};

    JSON.parse = function (text, reviver) {
        var j;

        function walk(holder, key) {

            var k, v, value = holder[key];
            if (value && typeof value === 'object') {
                for (k in value) {
                    if (Object.prototype.hasOwnProperty.call(value, k)) {
                        v = walk(value, k);
                        if (v !== undefined) {
                            value[k] = v;
                        } else {
                            delete value[k];
                        }
                    }
                }
            }
            return reviver.call(holder, key, value);
        }

        text = String(text);
        cx.lastIndex = 0;
        if (cx.test(text)) {
            text = text.replace(cx, function (a) {
                return '\\u' +
                    ('0000' + a.charCodeAt(0).toString(16)).slice(-4);
            });
        }

        if (/^[\],:{}\s]*$/
                .test(text.replace(/\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g, '@')
                    .replace(/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g, ']')
                    .replace(/(?:^|:|,)(?:\s*\[)+/g, ''))) {
            j = eval('(' + text + ')');
            return typeof reviver === 'function'
                ? walk({'': j}, '')
                : j;
        }
        throw new SyntaxError('JSON.parse');
    };
}());

function cf_GetBody(){
	var body = document.getElementsByTagName("body")[0];
	return body.innerHTML;
}

var WebBrowserPrint = null;
function cf_Print(){

	var OLECMDID = 7;
	var PROMPT = 1; // 2 DONTPROMPTUSER
	var agt=navigator.userAgent.toLowerCase();

	self.focus();
	if (agt.indexOf("msie") != -1){
    	/*if(WebBrowserPrint == null){
    		WebBrowserPrint = $('<OBJECT WIDTH=0 HEIGHT=0 CLASSID="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></OBJECT>');
    		$("body").append(WebBrowserPrint[0]);
    	}
    	WebBrowserPrint[0].ExecWB(OLECMDID, PROMPT);
    	WebBrowserPrint.html("");*/

    	if(WebBrowserPrint == null){
	    	var WebBrowserPrintHtml = '<OBJECT ID="printPrev" WIDTH=0 HEIGHT=0 CLASSID="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></OBJECT>';

	    	// Place Object on page
	    	document.body.insertAdjacentHTML('beforeEnd', WebBrowserPrintHtml);
	    	WebBrowserPrint = printPrev;
    	}

    	// Execute Object
    	WebBrowserPrint.ExecWB(OLECMDID, PROMPT);

	}else{
		self.print();
	}

	return null;
}

var SignClientMsg = function(url, parentWin) {
	this.server = window.top;
	this.serverUrl = url;

	if(typeof(parentWin) != "undefined")
		this.server = parentWin;
};
SignClientMsg.prototype.callbacks = [ "cf_LinkForm", "cf_SignCheck", "cf_Print", "cf_GetBody" ];
SignClientMsg.prototype.connect = function() {
	var serverUrl = this.serverUrl;
	var callbacks = this.callbacks;
	var server = this.server;

	var _loadFunc = function(){
		var execFuncs = "";
		var rsls = {func:"load"};
		for(var i = 0 ; i < callbacks.length ; i++){
			if(typeof( window[callbacks[i]] ) == "undefined" )
				continue;

			if(execFuncs != "") execFuncs += ",";
			execFuncs += callbacks[i];
		}
		rsls.rsl = execFuncs;
		var rslStr = JSON.stringify(rsls);
		server.postMessage(rslStr, serverUrl);
	};

	if ( document.readyState === "complete" ) {
		// Handle it asynchronously to allow scripts the opportunity to delay
		// ready
		_loadFunc();
	}else{
		var _that = this;
		_that.addEvent("load", function(){_loadFunc(); });
	}

};
SignClientMsg.prototype.postMsg = function(str) {
	this.server.postMessage(str, this.serverUrl);
};
SignClientMsg.prototype.parentClose = function() {
	rsls = {
			func : "cf_Close"
		};
	var rslStr = JSON.stringify(rsls);
	this.server.postMessage(rslStr, this.serverUrl);
};
SignClientMsg.prototype.parentRefresh = function() {
	rsls = {
			func : "cf_Refresh"
		};
	var rslStr = JSON.stringify(rsls);
	this.server.postMessage(rslStr, this.serverUrl);
};

SignClientMsg.prototype.onMsg = function(e) {
	if(this.server != e.source)
		return;

	var param = JSON.parse(e.data);
	var func = param.func;
	var args = param.args;
	var callbackName = param.callbackName;
	var rsls = {};
	var rsl = this.runMsgFunc(func, args);
	rsls = {
		func : func,
		rsl : rsl,
		callbackName : callbackName
	};
	var rslStr = JSON.stringify(rsls);
	this.server.postMessage(rslStr, this.serverUrl);

};
SignClientMsg.prototype.runMsgFunc = function(func, arg) {
	var rsl = eval(func)(arg);
	return rsl;
};
SignClientMsg.prototype.addEvent = function(event, func){
	if (window.addEventListener) {
		window.addEventListener(event, func);
	} else if (window.attachEvent) {
		window.attachEvent("on" + event, func);
	} else if (window["on" + event]) {
		window["on" + event] = func;
	}
};
SignClientMsg.prototype.setMsg = function() {
	var _that = this;
	if ( document.readyState === "complete" ) {
		// Handle it asynchronously to allow scripts the opportunity to delay
		// ready
		this.addEvent("message", function(e){_that.onMsg(e);});
	}else{
		_that.addEvent("load"
				, function(){_that.addEvent("message", function(e){_that.onMsg(e);});}
		);
	}
};