import Common from '../common.js'

export default {
	template : '\n' +
		'\t<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">\n' +
		'\t\t<h1 class="h2">{{menuName}}</h1>\n' +
		'\t\t<div class="btn-toolbar mb-2 mb-md-0">\n' +
		'\t\t\t<div class="btn-group me-2" v-bind:class="{\'d-none\' : isSearch == false}">\n' +
		'\t\t\t\t<input class="form-control search-input" type="search" id="search" placeholder="Search" v-model="search" @keyup.enter="searchAction()" autocomplete="off">\n' +
		'\t\t\t\t<button type="button" class="btn btn-dark" @click="searchAction()"><span data-feather="search"></span></button>\n' +
		'\t\t\t</div>\n' +
		'\t\t</div>\n' +
		'\t</div>',
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
