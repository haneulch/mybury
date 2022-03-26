import Common from '../common.js';
import headerComp from './header_component.js';
import leftComp from './left_component.js';
import menu from './menu_component.js';
import pageComp from './page_component.js';
import './vue_filter.js';

new Vue({
	el: '#content',
	data() {
		return {
			content : [],
			menuName : '-',
			link: '/admin/json/findSupportHistory',
			totalPages: 0,
			totalElements: 0,
			number: 0
		}
	},
	mounted() {
		var _vue = this;

		var param = {page : 0};
		var obj = Common.getParam();
		if( obj != null && obj.fromback == '1') {
			
			let searchKeyword = sessionStorage.getItem('searchKeyword');
			
			if( searchKeyword) {
				param.search = searchKeyword;
				menu.methods.setSearchKeyword(searchKeyword);
			}
		}
		
		Common.fetch('/admin/json/findSupportHistory', param, function(data) {
			$.extend(_vue, data);
		})
	},
	created() {
		this.$on('changeDrawer', this.changeDrawer);
		this.$on('getSearchedData', this.getSearchedData);
	},
	methods: {
		changeDrawer( data) {
			$.extend(this, {menuName : data});
		},
		parentMethod(content, number) {
			this.content = content;
			this.number = number;
		},
		getSearchedData( data) {
			$.extend(this, data);
		},
		toDetail( seq) {
			location.href = '/admin/page/view_user?userId=' + seq;
		},
		changeSusYn( e, seq) {
			let susYn = e.target.value;
			
			if( confirm(`[${seq}]번 구매내역의 성공여부를 변경하시겠습니까?`)) {
				var param = {
					seq		: seq
					, susYn	: susYn
				};
				
				try {
					Common.fetch('/admin/json/updateSusYn', param, function() {
						alert('변경되었습니다.');
					})
				} catch( e) {
					alert('변경중 오류가 발생했습니다.');
				}
			} else {
				setTimeout( function() {
					e.target.value = susYn == 'Y' ? 'N' : 'Y';
				}, 10)
			}
		}
	},
	components : {
		'header-menu'	: headerComp,
		'left-menu'		: leftComp,
		'menu-name'			: menu,
		'pagination'	: pageComp
	}
});