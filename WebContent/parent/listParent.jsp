<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>Parents</title>
<meta content='width=device-width, initial-scale=1.0, shrink-to-fit=no'
	name='viewport' />
<link rel="icon" href="/e-JAWI/assets/img/icon.ico" type="image/x-icon" />

<!-- Fonts and icons -->
<script src="/e-JAWI/assets/js/plugin/webfont/webfont.min.js"></script>
<script>
	WebFont.load({
		google : {
			"families" : [ "Open+Sans:300,400,600,700" ]
		},
		custom : {
			"families" : [ "Flaticon", "Font Awesome 5 Solid",
					"Font Awesome 5 Regular", "Font Awesome 5 Brands" ],
			urls : [ '/e-JAWI/assets/css/fonts.css' ]
		},
		active : function() {
			sessionStorage.fonts = true;
		}
	});
</script>

<!-- CSS Files -->
<link rel="stylesheet" href="/e-JAWI/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="/e-JAWI/assets/css/azzara.min.css">

<!-- CSS Just for demo purpose, don't include it in your project -->
<link rel="stylesheet" href="/e-JAWI/assets/css/demo.css">
</head>
<body data-background-color="bg3">
	<div class="wrapper">
		<!--
			Tip 1: You can change the background color of the main header using: data-background-color="blue | purple | light-blue | green | orange | red"
		-->
		<div class="main-header" data-background-color="light-blue">
			<jsp:include page="../AdminHeader.jsp" />
		</div>

		<!-- Sidebar -->
		<div class="sidebar">
			<jsp:include page="../AdminSidebar.jsp" />
		</div>
		<!-- End Sidebar -->

		<div class="main-panel">
			<div class="content">
				<div class="page-inner">
					<div class="page-header">
						<h4 class="page-title">Parents</h4>
						<ul class="breadcrumbs">
							<li class="nav-home"><a href="#"> <i
									class="flaticon-home"></i>
							</a></li>
							<li class="separator"><i class="flaticon-right-arrow"></i></li>
							<li class="nav-item"><a href="#">Users</a></li>
							<li class="separator"><i class="flaticon-right-arrow"></i></li>
							<li class="nav-item"><a href="#">Parent</a></li>
						</ul>
					</div>
					<div class="row">

						<div class="col-md-10">
							<button class="btn btn-primary btn-round ml-auto"
								data-toggle="modal" data-target="#addRowModal">
								<i class="fa fa-plus"></i> Add Parent
							</button>
							<br> <br>
							<div class="card">
								<div class="card-header">
									<div class="d-flex align-items-center">
										<h4 class="card-title">List Parents</h4>

									</div>
								</div>
								<div class="card-body">

									<!-- Modal -->
									<div class="modal fade" id="addRowModal" tabindex="-1"
										role="dialog" aria-hidden="true">
										<div class="modal-dialog" role="document">
											<div class="modal-content">
												<form action="ParentController" method="post">
													<div class="modal-header no-bd">
														<h5 class="modal-title">
															<span class="fw-mediumbold"> Add</span> <span
																class="fw-light"> Parent </span>
														</h5>
														<button type="button" class="close" data-dismiss="modal"
															aria-label="Close">
															<span aria-hidden="true">&times;</span>
														</button>
													</div>
													<div class="modal-body">
														<!-- <p class="small">Create a new row using this form, make
														sure you fill them all</p> -->

														<div class="row">
															<div class="col-sm-12">
																<div class="form-group form-group-default">
																	<label>Email</label> <input id="" type="email" required
																		class="form-control" name="email" placeholder="">
																</div>
																<div class="form-group form-group-default">
																	<label>Name</label> <input id="" type="text" required
																		class="form-control" name="name" placeholder="">
																</div>
																<div class="form-group form-group-default">
																	<label>Password</label> <input id="" type="password" required
																		class="form-control" name="password" placeholder="">
																</div>
																<div class="form-group form-group-default">
																	<label>Address</label>
																	<textarea id="" class="form-control" name="address"
																		placeholder=""></textarea>
																</div>
																<div class="form-group form-group-default">
																	<label>Phone</label> <input id="" type="number" required
																		class="form-control" name="phone" placeholder="">
																</div>
															</div>
														</div>

													</div>
													<div class="modal-footer no-bd">
														<button type="submit" id="addRowButton" name="action"
															value="Register" class="btn btn-primary">Add</button>
														<button type="button" class="btn btn-danger"
															data-dismiss="modal">Close</button>
													</div>
												</form>
											</div>
										</div>
									</div>

									<div class="table-responsive">
										<table id="add-row"
											class="display table table-striped table-hover">
											<thead>
												<tr>
													<th>Email</th>
													<th>Name</th>
													<th>Address</th>
													<th>Phone</th>
													<th style="width: 10%">Action</th>
												</tr>
											</thead>
											<tfoot>
												<tr>
													<th>Email</th>
													<th>Name</th>
													<th>Address</th>
													<th>Phone</th>
													<th>Action</th>
												</tr>
											</tfoot>
											<tbody>
												<c:forEach var="parent" items="${parents}">
													<tr>
														<td><c:out value="${parent.parentEmail}" /></td>
														<td><c:out value="${parent.parentName}" /></td>
														<td><c:out value="${parent.parentAddress}" /></td>
														<td><c:out value="${parent.parentPhone}" /></td>
														<td>
															<div class="form-button-action">
																<a
																	href="ParentController?action=updateAccount&id=<c:out value="${parent.id}" />"
																	data-toggle="tooltip" title=""
																	class="btn btn-link btn-primary btn-lg"
																	data-original-title="Edit"> <i
																	class="fa fa-edit"></i>
																</a>
															</div>
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
	<!--   Core JS Files   -->
	<script src="/e-JAWI/assets/js/core/jquery.3.2.1.min.js"></script>
	<script src="/e-JAWI/assets/js/core/popper.min.js"></script>
	<script src="/e-JAWI/assets/js/core/bootstrap.min.js"></script>

	<!-- jQuery UI -->
	<script
		src="/e-JAWI/assets/js/plugin/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
	<script
		src="/e-JAWI/assets/js/plugin/jquery-ui-touch-punch/jquery.ui.touch-punch.min.js"></script>

	<!-- jQuery Scrollbar -->
	<script
		src="/e-JAWI/assets/js/plugin/jquery-scrollbar/jquery.scrollbar.min.js"></script>

	<!-- Moment JS -->
	<script src="/e-JAWI/assets/js/plugin/moment/moment.min.js"></script>

	<!-- Chart JS -->
	<script src="/e-JAWI/assets/js/plugin/chart.js/chart.min.js"></script>

	<!-- jQuery Sparkline -->
	<script
		src="/e-JAWI/assets/js/plugin/jquery.sparkline/jquery.sparkline.min.js"></script>

	<!-- Chart Circle -->
	<script src="/e-JAWI/assets/js/plugin/chart-circle/circles.min.js"></script>

	<!-- Datatables -->
	<script src="/e-JAWI/assets/js/plugin/datatables/datatables.min.js"></script>

	<!-- Bootstrap Notify -->
	<script
		src="/e-JAWI/assets/js/plugin/bootstrap-notify/bootstrap-notify.min.js"></script>

	<!-- Bootstrap Toggle -->
	<script
		src="/e-JAWI/assets/js/plugin/bootstrap-toggle/bootstrap-toggle.min.js"></script>

	<!-- jQuery Vector Maps -->
	<script src="/e-JAWI/assets/js/plugin/jqvmap/jquery.vmap.min.js"></script>
	<script src="/e-JAWI/assets/js/plugin/jqvmap/maps/jquery.vmap.world.js"></script>

	<!-- Google Maps Plugin -->
	<script src="/e-JAWI/assets/js/plugin/gmaps/gmaps.js"></script>

	<!-- Sweet Alert -->
	<script src="/e-JAWI/assets/js/plugin/sweetalert/sweetalert.min.js"></script>

	<!-- Azzara JS -->
	<script src="/e-JAWI/assets/js/ready.min.js"></script>

	<script>
		$('#User').addClass("active");
		$('#User').addClass("submenu");
		$('#user').addClass("show");
		$('#Parent').addClass("active");
		$(document)
				.ready(
						function() {
							$('#basic-datatables').DataTable({});

							$('#multi-filter-select')
									.DataTable(
											{
												"pageLength" : 5,
												initComplete : function() {
													this
															.api()
															.columns()
															.every(
																	function() {
																		var column = this;
																		var select = $(
																				'<select class="form-control"><option value=""></option></select>')
																				.appendTo(
																						$(
																								column
																										.footer())
																								.empty())
																				.on(
																						'change',
																						function() {
																							var val = $.fn.dataTable.util
																									.escapeRegex($(
																											this)
																											.val());

																							column
																									.search(
																											val ? '^'
																													+ val
																													+ '$'
																													: '',
																											true,
																											false)
																									.draw();
																						});

																		column
																				.data()
																				.unique()
																				.sort()
																				.each(
																						function(
																								d,
																								j) {
																							select
																									.append('<option value="'+d+'">'
																											+ d
																											+ '</option>')
																						});
																	});
												}
											});

							// Add Row
							$('#add-row').DataTable({
								"pageLength" : 5,
								"columnDefs" : [ {
									"width" : "15%",
									"targets" : 3,
								}, {
									"width" : "10%",
									"targets" : 4
								} ],

							});
						});
	</script>
</body>
</html>