export default {
	template	: '#left-template',
	data() {
		return {
			menulist : [
				{name : 'home', url : '/admin/page/main', icon : 'home', isActive : false, dpYn : true}
				, {name : '사용자 관리', url : '/admin/page/list_user', icon : 'users', isActive : false, dpYn : true}
				, {name : '후원 아이템 관리', url : '/admin/page/list_support_item', icon : 'gift', isActive : false, dpYn : true}
				, {name : '결제 관리', url : '/admin/page/list_support_history', icon : 'shopping-cart', isActive : false, dpYn : true}
				, {name : '어드민 계정 관리', url : '/admin/page/list_admin_user', icon : 'settings', isActive : false, dpYn : true}
				, {name : '상세', parent : 1, url : '/admin/page/view_user', isActive : false, dpYn : false}
			]
		}
	},
	mounted() {
		let _vue = this;
		
		let pathname = location.pathname;
		
		let menuName = '-';
		for( var d of _vue.menulist) {
			if( pathname.indexOf(d.url) > -1) {
				
				if( d.dpYn === true) {
					
					d.isActive = true;
					menuName = d.name;
					
				} else {
					menuName = _vue.menulist[d.parent].name + ' > ' + d.name;
					_vue.menulist[d.parent].isActive = true;
				}
			}
		}
		
		_vue.$parent.$emit('changeDrawer', menuName);
		
		feather.replace();
	}
}
