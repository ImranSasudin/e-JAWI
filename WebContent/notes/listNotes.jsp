<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String email = (String) session.getAttribute("currentSessionUser");
	String name = (String) session.getAttribute("currentSessionUserName");
	String role = (String) session.getAttribute("currentSessionUserRole");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>Notes</title>
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
		<% if(role.equalsIgnoreCase("Student")){ %>
			<jsp:include page="../StudentHeader.jsp" />
		<% } else if(role.equalsIgnoreCase("Admin")){  %>
			<jsp:include page="../AdminHeader.jsp" />
		<% } %>
		</div>

		<!-- Sidebar -->
		<div class="sidebar">
			<% if(role.equalsIgnoreCase("Student")){ %>
				<jsp:include page="../StudentSidebar.jsp" />
			<% } else if(role.equalsIgnoreCase("Admin")){  %>
				<jsp:include page="../AdminSidebar.jsp" />
			<% } %>
		</div>
		<!-- End Sidebar -->

		<div class="main-panel">
			<div class="content">
				<div class="page-inner">
					<div class="page-header">
						<h4 class="page-title">Notes</h4>
						<ul class="breadcrumbs">
							<li class="nav-home"><a href="#"> <i
									class="flaticon-home"></i>
							</a></li>
							<li class="separator"><i class="flaticon-right-arrow"></i></li>
							<li class="nav-item"><a href="#">Notes</a></li>
							<li class="separator"><i class="flaticon-right-arrow"></i></li>
							<li class="nav-item"><a href="#">List</a></li>
						</ul>
					</div>
					<div class="row">

						<div class="col-md-5">
							<div class="card">
								<div class="card-header">
									<div class="d-flex align-items-center">
										<h4 class="card-title">List Notes</h4>

									</div>
								</div>
								<div class="card-body">

									<div class="table-responsive">
										<table id="add-row"
											class="display table table-striped table-hover">
											<thead>
												<tr>
													<th>Title</th>
													<th>Created By</th>
													<th style="width: 10%">Action</th>
												</tr>
											</thead>
											<tfoot>
												<tr>
													<th>Title</th>
													<th>Created By</th>
													<th>Action</th>
												</tr>
											</tfoot>
											<tbody>
												<c:forEach var="notes" items="${notes}">
													<tr>
														<td><c:out value="${notes.notesTitle}" /></td>
														<td><c:out value="${notes.teacherName}" /></td>
														<td>
															<div class="form-button-action">
																<a
																	href="NotesController?action=viewNotes&notesID=<c:out value="${notes.notesID}" />"
																	data-toggle="tooltip" title=""
																	class="btn btn-link btn-primary btn-lg"
																	data-original-title="View Note"> <i
																	class="fas fa-eye"></i>
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
		$('#notes').addClass("active");
		$(document).ready(function() {
			$('#basic-datatables').DataTable({
			});

			$('#multi-filter-select').DataTable( {
				"pageLength": 5,
				initComplete: function () {
					this.api().columns().every( function () {
						var column = this;
						var select = $('<select class="form-control"><option value=""></option></select>')
						.appendTo( $(column.footer()).empty() )
						.on( 'change', function () {
							var val = $.fn.dataTable.util.escapeRegex(
								$(this).val()
								);

							column
							.search( val ? '^'+val+'$' : '', true, false )
							.draw();
						} );

						column.data().unique().sort().each( function ( d, j ) {
							select.append( '<option value="'+d+'">'+d+'</option>' )
						} );
					} );
				}
			});

			// Add Row
			$('#add-row').DataTable({
				"pageLength": 5,
			});
		});
	</script>
</body>
</html>