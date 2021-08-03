<%@ page language="java" contentType="text/html; charset=EUC-KR"
		pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>	
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Mybury Admin</title>
		<link href="../../static/css/bootstrap/bootstrap.min.css" rel="stylesheet">
		<link href="../../static/css/bootstrap/dashboard.css" rel="stylesheet">
		
	</head>
	<body>
		<div id="content">
			<header-menu></header-menu>
			
		
			<div class="container-fluid">
				<div class="row">
					<left-menu></left-menu>
					
					<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4 pt-3">
						<!-- <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
							<h1 class="h2">Dashboard</h1>
							<div class="btn-toolbar mb-2 mb-md-0">
								<div class="btn-group mr-2">
									<button type="button" class="btn btn-sm btn-outline-secondary">Share</button>
									<button type="button" class="btn btn-sm btn-outline-secondary">Export</button>
								</div>
								<button type="button" class="btn btn-sm btn-outline-secondary dropdown-toggle">
									<span data-feather="calendar"></span>
									This week
								</button>
							</div>
						</div> -->
			
						<!-- <canvas class="my-4 w-100" id="myChart" width="900" height="380"></canvas> -->
						<h2>{{menuName}}</h2>
						??
					</main>
				</div>
			</div>
		</div>
		
		<script src="../../static/js/vue/vue.js"></script>
		<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
		
		<script src="../../static/js/bootstrap/bootstrap.bundle.min.js"></script>
				
		<script src="https://cdn.jsdelivr.net/npm/feather-icons/dist/feather.min.js"></script>
		<!-- <script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js"></script> -->
		
		<script type="module" src="../../static/js/vue/vue_main.js"></script>
		
		<jsp:include page="./template_sources.jsp"/>
	</body>
	
	
</html>