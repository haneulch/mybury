
var Common = {

	get(url, _callBack) {
		let options = {
			method: 'GET',
			mode: 'cors',
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json;charset=UTF-8'
			}
		};

		fetch(url, options)
			.then( response => {
				if( response && response.ok) {
					response.text().then( data => {
						var obj = data ? JSON.parse(data) : {data : true};

						if( $.isFunction( _callBack)) {
							_callBack( obj);
						}
					})
				}
			});
	},

	fetch ( url, param, _callBack) {

		let options = {
			method: 'POST',
			mode: 'cors',
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json;charset=UTF-8'
			},
			body: param ? JSON.stringify( param) : null
		};


		fetch(url, options)
			.then( response => {
				if( response && response.ok) {
					response.text().then( data => {
						var obj = data ? JSON.parse(data) : {data : true};

						if( $.isFunction( _callBack)) {
							_callBack( obj);
						}
					})
				}
			});
	},

	getParam () {
		let obj = {};
		if( location.href.indexOf('?') > 0) {
			let params = location.href.substr(location.href.indexOf('?') + 1);
			let arr = params.split('&');

			for( var p of arr) {
				let singleArr = p.split('=');
				obj[singleArr[0]] = singleArr[1];
			}
		}
		return obj;
	}
};

Date.prototype.format = function(f) {
    if (!this.valueOf()) return " ";

    var weekName = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
    var d = this;

    return f.replace(/(yyyy|yy|MM|dd|E|hh|mm|ss|a\/p)/gi, function($1) {
        switch ($1) {
            case "yyyy": return d.getFullYear();
            case "yy": return (d.getFullYear() % 1000).zf(2);
            case "MM": return (d.getMonth() + 1).zf(2);
            case "dd": return d.getDate().zf(2);
            case "E": return weekName[d.getDay()];
            case "HH": return d.getHours().zf(2);
            case "hh": return ((h = d.getHours() % 12) ? h : 12).zf(2);
            case "mm": return d.getMinutes().zf(2);
            case "ss": return d.getSeconds().zf(2);
            case "a/p": return d.getHours() < 12 ? "오전" : "오후";
            default: return $1;
        }
    });
};

String.prototype.string = function(len){var s = '', i = 0; while (i++ < len) { s += this; } return s;};
String.prototype.zf = function(len){return "0".string(len - this.length) + this;};
Number.prototype.zf = function(len){return this.toString().zf(len);};

export default Common;
