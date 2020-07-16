<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
<title>Account</title>
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
			<jsp:include page="../ParentHeader.jsp" />
		</div>

		<!-- Sidebar -->
		<div class="sidebar">
			<jsp:include page="../ParentSidebar.jsp" />
		</div>
		<!-- End Sidebar -->

		<div class="main-panel">
			<div class="content">
				<div class="page-inner">
					<div class="page-header">
						<h4 class="page-title">Account</h4>
						<ul class="breadcrumbs">
							<li class="nav-home"><a href="#"> <i
									class="flaticon-home"></i>
							</a></li>
							<li class="separator"><i class="flaticon-right-arrow"></i></li>
							<li class="nav-item"><a href="#">Account</a></li>
							<li class="separator"><i class="flaticon-right-arrow"></i></li>
							<li class="nav-item"><a href="#">View</a></li>
						</ul>
					</div>
					<div class="row">
						<div class="col-md-8">
							<div class="card">
								<div class="card-header">
									<div class="d-flex align-items-center">
										<h4 class="card-title">View Account</h4>
									</div>
								</div>
								<div class="card-body">
										<div class="row mt-3">
											<div class="col-md-12">
												<div class="form-group ">
													<label>Email</label> <input type="text" disabled
														class="form-control" name="email" placeholder="Name"
														value="<c:out value="${parent.parentEmail }"/>">
												</div>
												<div class="form-group ">
												<label>Password</label><br>
													<button 
														class="btn btn-success "
														value="">Update Password</button>
												</div>
												<div class="form-group ">
													<label>Name</label> <input type="text" disabled
														class="form-control" name="name"
														value="<c:out value="${parent.parentName }"/>">
												</div>
												<div class="form-group ">
													<label>Address</label> <input type="text" disabled
														class="form-control" name="address"
														value="<c:out value="${parent.parentAddress }"/>">
												</div>
												<div class="form-group ">
													<label>Phone</label> <input type="text" disabled
														class="form-control" name="phone"
														value="<c:out value="${parent.parentPhone }"/>">
												</div>
											</div>
										</div>
										<div class="text-right mt-3 mb-3">
											<a class="btn btn-primary" href="javascript:history.back()">Back</a>
											<a class="btn btn-success" href="/e-JAWI/ParentController?action=editAccount">Update</a>
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
	<script src="https://cdn.ckeditor.com/4.14.0/full-all/ckeditor.js"></script>
	<script>
		CKEDITOR.plugins.addExternal('exportpdf',
				'/e-JAWI/notes/node_modules/ckeditor4/plugins/exportpdf/');
		CKEDITOR
				.replace(
						'notesContent',
						{
							skin : 'kama',
							extraPlugins : 'embed,autoembed,placeholder,emoji',
							width : 940,
							height : 700,

							// Load the default contents.css file plus customizations for this sample.

							// Setup content provider. See https://ckeditor.com/docs/ckeditor4/latest/features/media_embed
							embed_provider : '//ckeditor.iframe.ly/api/oembed?url={url}&callback={callback}',

						// Configure the Enhanced Image plugin to use classes instead of styles and to disable the
						// resizer (because image size is controlled by widget styles or the image takes maximum
						// 100% of the editor width).
						/* image2_alignClasses: ['image-align-left', 'image-align-center', 'image-align-right'],
						image2_disableResizer: true */
						});
	</script>
</body>
</html>

