import Common from '../common.js';
import headerComp from './header_component.js';
import leftComp from './left_component.js';

new Vue({
	el: '#content',
	data() {
		return {
			items : [],
			menuName : '-'
		}
	},
	mounted() {
		var _vue = this;
		Common.fetch('/admin/json/findSupportItem', null, function(data) {
			$.extend(_vue, {items : data});
		});
	},
	created() {
		this.$on('changeDrawer', this.changeDrawer)
	},
	methods: {
		changeDrawer( data) {
			$.extend(this, {menuName : data});
		}
	},
	components : {
		'header-menu'	: headerComp,
		'left-menu'		: leftComp
	}
});