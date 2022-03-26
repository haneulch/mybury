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
			link: '/admin/json/findAdminUser',
			totalPages: 0,
			totalElements: 0,
			number: 0,
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
		
		Common.fetch('/admin/json/findAdminUser', param, function(data) {
			$.extend(_vue, data);
		});
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
		initKey( username) {
			if( confirm('otp 인증키를 초기화 하시겠습니까?')) {
				Common.fetch('/admin/json/initializeOtpKey', {username : username}, function(data) {
					alert('변경된 인증키를 google otp에 등록하세요.\n[' + data.otpKey + ']');
				});
			}
		},
		deleteUser( username) {
			if( confirm('정말로 계정을 삭제하시겠습니까?')) {
				Common.fetch('/admin/json/deleteAdminUser', {username : username});
			};
		}
	},
	components : {
		'header-menu'	: headerComp,
		'left-menu'		: leftComp,
		'menu-name'		: menu,
		'pagination'	: pageComp
	}
});