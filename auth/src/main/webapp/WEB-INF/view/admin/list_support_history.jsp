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
					
						<menu-name :menu-name="menuName" :is-search="true" :search-url="link"></menu-name>
				        
						<div class="table-responsive">
							<table class="table table-striped text-center">
								<thead>
									<tr>
										<th>#</th>
										<th>ID</th>
										<th>TOKEN</th>
										<th>아이템명</th>
										<th>구매일자</th>
										<th>성공여부</th>
									</tr>
								</thead>
								<tbody>
									<tr v-for="item in content">
										<td>{{item.seq}}</td>
										<td><a href="javascript:void(0);" @click="toDetail(item.userId)">{{item.userId}}</a></td>
										<td>{{item.token}}</td>
										<td class="text-left">[{{item.item.googleKey}}]&nbsp;{{item.item.itemName}}</td>
										<td>{{item.createdDt | datetime}}</td>
										<td>
											<select class="form-select" v-model="item.susYn" @change="changeSusYn($event, item.seq)">
											  <option value="Y">Y</option>
											  <option value="N">N</option>
											</select>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<pagination @childs-event="parentMethod" :total-pages="totalPages" :number="number" :link="link"></pagination>
					</main>
				</div>
			</div>
		</div>
		
		<script src="../../static/js/vue/vue.js"></script>
		<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
		
		<script src="../../static/js/bootstrap/bootstrap.bundle.min.js"></script>
				
		<script src="https://cdn.jsdelivr.net/npm/feather-icons/dist/feather.min.js"></script>
		<!-- <script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js"></script> -->

		<script type="module" src="../../static/js/vue/vue_support_history.js"></script>
				
		<jsp:include page="./template_sources.jsp"/>
	</body>
	
	
</html>