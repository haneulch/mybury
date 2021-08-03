
Vue.filter('currency', function (num) {
	if ( isNaN(num)) num = 0;
	return num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
});

Vue.filter('date', function( str) {
	return new Date(str).format("yyyy-MM-dd");
});

Vue.filter('datetime', function( str) {
	return new Date(str).format("yyyy-MM-dd HH:mm:ss");
});
export default {}