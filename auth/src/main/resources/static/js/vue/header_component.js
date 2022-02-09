export default {
	template	: '<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">\n' +
		'\t\t<a class="navbar-brand col-md-3 col-lg-2 mr-0 px-3" href="/admin/main">Mybury</a>\n' +
		'\t\t<button class="navbar-toggler position-absolute d-md-none collapsed" type="button" data-toggle="collapse" data-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false" aria-label="Toggle navigation">\n' +
		'\t\t\t<span class="navbar-toggler-icon"></span>\n' +
		'\t\t</button>\n' +
		'\t\t<ul class="navbar-nav px-3">\n' +
		'\t\t\t<li class="nav-item text-nowrap">\n' +
		'\t\t\t\t<a class="nav-link" href="/admin/logout">Sign out</a>\n' +
		'\t\t\t</li>\n' +
		'\t\t</ul>\n' +
		'\t</nav>',
	mouted() {
		console.log('header mounted');
	}
}
