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
							<table class="table table-striped text-center text-vertical-middle">
								<thead>
									<tr>
										<th>����</th>
										<th>�̸�</th>
										<th>�����Ͻ�</th>
										<th>�ֱ� �α��� �Ͻ�</th>
										<th>otp ��߱�</th>
										<th>���� ����</th>
									</tr>
								</thead>
								<tbody>
									<tr v-for="item in content">
										<td>{{item.username}}</td>
										<td>{{item.name}}</td>
										<td>{{item.createdDt | datetime}}</td>
										<td>{{item.lastLoginDt | datetime}}</td>
										<td><a href="javascript:void(0)" class="btn btn-primary btn-sm" @click="initKey(item.username)">�ʱ�ȭ</a></td>
										<td><a href="javascript:void(0)" class="btn btn-danger btn-sm" @click="deleteUser(item.username)">����</a></td>
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
		
		<script type="module" src="../../static/js/vue/vue_admin_user.js"></script>
		
		<jsp:include page="./template_sources.jsp"/>
	</body>
	
	
</html>