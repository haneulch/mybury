import Common from '../common.js';
import headerComp from './header_component.js';
import leftComp from './left_component.js';
import menu from './menu_component.js';
import './vue_filter.js';

new Vue({
	el: '#content',
	data() {
		return {
			user : {},
			history : [],
			menuName : '-',
			link: ''
		}
	},
	mounted() {
		var _vue = this;
		
		Common.fetch('/admin/json/findOneUser', Common.getParam(), function(data) {
			$.extend(_vue, data);
		})
	},
	created() {
		this.$on('changeDrawer', this.changeDrawer);
	},
	methods: {
		changeDrawer( data) {
			$.extend(this, {menuName : data});
		},
		toList() {
			var url = document.referrer;
			url = url.substr( url.indexOf('/admin'));
			
			if( url.indexOf('?') > -1) url = url.substr( 0, url.indexOf('?'));
			
			location.href = url + '?fromback=1';
		}
	},
	components : {
		'header-menu'	: headerComp,
		'left-menu'		: leftComp,
		'menu-name'		: menu,
	}
});