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
					
						<menu-name :menu-name="menuName" :is-search="false" :search-url="link"></menu-name>
								
						<div class="col-md-8 order-md-1">
							<form class="needs-validation" novalidate>
								<div class="row">
									<div class="col-md-6 mb-3">
										<label for="id">ID</label>
										<input type="text" class="form-control" id="id" :value="user.id" required readonly>
									</div>
									<div class="col-md-6 mb-3">
										<label for="name">�̸�</label>
										<input type="text" class="form-control" id="name" :value="user.name" required readonly>
									</div>
								</div>
								
								<div class="row">
									<div class="col-md-6 mb-3">
										<label for="email">Email</label>
										<input type="text" class="form-control" id="email" :value="user.email" required readonly>
									</div>
									<div class="col-md-6 mb-3">
										<label for="lastLoginDt">���� �α��� �Ͻ�</label>
										<input type="text" class="form-control" id="lastLoginDt" :value="user.lastLoginDt | date" required readonly>
									</div>
								</div>
								
								<hr class="mb-4">
								<h4 class="mb-3">���� ����</h4>
				
								<div class="table-responsive">
									<table class="table table-striped text-center">
										<thead>
											<tr>
												<th>#</th>
												<th>token</th>
												<th>�����۸�</th>
												<th>��������</th>
												<th>��������</th>
												<th>�����Ͻ�</th>
											</tr>
										</thead>
										<tbody>
											<tr v-for="data in history">
												<td>{{data.seq}}</td>
												<td>{{data.token}}</td>
												<td class="text-left">[{{data.item.googleKey}}]&nbsp;{{data.item.itemName}}</td>
												<td>{{data.susYn}}</td>
												<td>{{data.delYn}}</td>
												<td>{{data.createdDt | datetime}}</td>
											</tr>
										</tbody>
									</table>
								</div>
				
								<hr class="mb-4">
								<a href="javascript:void(0)" class="btn btn-primary" @click="toList">�ڷ�</a>
							</form>
						</div>
					</main>
				</div>
			</div>
		</div>
		
		<script src="../../static/js/vue/vue.js"></script>
		<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
		
		<script src="../../static/js/bootstrap/bootstrap.bundle.min.js"></script>
				
		<script src="https://cdn.jsdelivr.net/npm/feather-icons/dist/feather.min.js"></script>

		<script type="module" src="../../static/js/vue/vue_user_detail.js"></script>
				
		<jsp:include page="./template_sources.jsp"/>
	</body>
	
	
</html>