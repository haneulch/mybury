import Common from '../common.js';

new Vue({
	el : '#content'
	, mounted() {
		var obj = Common.getParam();
		console.log(obj);
		
		if( obj && obj.code) {
			if( obj.code == '1') {
				alert('사용자 아이디를 확인해주세요.');
			} else if( obj.code == '2') {
				alert('다시 시도해주세요.');
				
			}
		}
	}
});