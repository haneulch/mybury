import Common from '../common.js'

export default {
	template : '#menu-template',
	props	 : ['menuName', 'isSearch', 'searchUrl'],
	data() {
		return {
			search : ''
		}
	},
	methods : {
		searchAction() {
			let _vue = this;
			
			sessionStorage.setItem('searchKeyword', _vue.search);
			
			Common.fetch( _vue.searchUrl, {page : 0, search : _vue.search}, function(data) {
				_vue.$parent.$emit('getSearchedData', data);
			});
		},
		setSearchKeyword( data) {
			if( data) {
				let el = document.getElementById('search');
				el.value = data;
				el.dispatchEvent(new Event('input'));
			}
		}
	}
}
