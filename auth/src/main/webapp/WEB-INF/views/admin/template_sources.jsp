<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<script type="text/x-template" id="header-template">
	<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
		<a class="navbar-brand col-md-3 col-lg-2 mr-0 px-3" href="/admin/main">Mybury</a>
		<button class="navbar-toggler position-absolute d-md-none collapsed" type="button" data-toggle="collapse" data-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<ul class="navbar-nav px-3">
			<li class="nav-item text-nowrap">
				<a class="nav-link" href="/admin/logout">Sign out</a>
			</li>
		</ul>
	</nav>
</script>

<script type="text/x-template" id="left-template">
	<nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
		<div class="sidebar-sticky pt-3">
			<ul class="nav flex-column">
				<li class="nav-item" v-for="item in menulist" v-if="item.dpYn">
					<a class="nav-link" v-bind:class="{active : item.isActive}" :href="item.url">
						<span :data-feather="item.icon"></span> {{item.name}} <span class="sr-only">(current)</span>
					</a>
				</li>
			</ul>
		</div>
	</nav>
</script>

<script type="text/x-template" id="menu-template">
	<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
		<h1 class="h2">{{menuName}}</h1>
		<div class="btn-toolbar mb-2 mb-md-0">
			<div class="btn-group me-2" v-bind:class="{'d-none' : isSearch == false}">
				<input class="form-control search-input" type="search" id="search" placeholder="Search" v-model="search" @keyup.enter="searchAction()" autocomplete="off">
				<button type="button" class="btn btn-dark" @click="searchAction()"><span data-feather="search"></span></button>
			</div>
		</div>
	</div>
</script>

<script type="text/x-template" id="pagination-template">
	<div id="pagination">
		<nav aria-label="Page navigation example">
			<ul class="pagination justify-content-center">
				<li class="page-item" v-for="i in pagearr" v-bind:key="i" v-bind:class="{active : i == number + 1}"><a class="page-link" @click="pageChange(i)">{{i}}</a></li>				
			</ul>
		</nav>
	</div>
</script>