<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
			<%
				if (role.equalsIgnoreCase("Student")) {
			%>
			<jsp:include page="../StudentHeader.jsp" />
			<%
				} else if (role.equalsIgnoreCase("Admin")) {
			%>
			<jsp:include page="../AdminHeader.jsp" />
			<%
				} else if (role.equalsIgnoreCase("Teacher")) {
			%>
			<jsp:include page="../TeacherHeader.jsp" />
			<%
				}
			%>
		</div>

		<!-- Sidebar -->
		<div class="sidebar">
			<%
				if (role.equalsIgnoreCase("Student")) {
			%>
			<jsp:include page="../StudentSidebar.jsp" />
			<%
				} else if (role.equalsIgnoreCase("Admin")) {
			%>
			<jsp:include page="../AdminSidebar.jsp" />
			<%
				} else if (role.equalsIgnoreCase("Teacher")) {
			%>
			<jsp:include page="../TeacherSidebar.jsp" />
			<%
				}
			%>
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
							<li class="nav-item"><a href="#">View</a></li>
						</ul>
					</div>
					<div class="row">
						<div class="col-md-8">
							<div class="card">
								<div class="card-header">
									<div class="d-flex align-items-center">
										<h4 class="card-title">View Note</h4>
									</div>
								</div>
								<div class="card-body">
									<form action="/e-JAWI/NotesController" method="post">
										<input type="hidden" name="notesID"
											value="<c:out value="${notes.notesID}" />" />
										<div class="row mt-3">
											<div class="col-md-12">
												<div class="form-group form-group-default">
													<h3>
														<b>Note Title : <c:out value="${notes.notesTitle}"
																escapeXml="false" /></b>
													</h3>
												</div>
											</div>
										</div>
										<div class="row mt-3">
											<div class="col-md-12">
												<div class="form-group">
													<c:out value="${notes.notesContent}" escapeXml="false" />
												</div>
											</div>
										</div>


									</form>
								</div>
								<div class="card-footer">
									<div class="row">
										<div class="col-sm-12">
											<h4 class="page-title">Comment</h4>
										</div>
										<!-- /col-sm-12 -->
									</div>
									<c:if test="${currentSessionUserRole == 'Student' }">
									<div class="row">
										<div class="col-sm-7">
											<form action="/e-JAWI/NotesController" method="post">
											<input type="hidden" name="notesID"
											value="<c:out value="${notes.notesID}" />" />
												<div class="form-group">
													<textarea class="form-control" rows="7" name="comment" ${exist ? 'readonly' : '' } placeholder="Leave your comment here" id="exampleInputEmail1" required
														aria-describedby="emailHelp"></textarea>
												</div>
												<button type="submit" name="action" value="addComment" class="btn btn-success float-right mr-2 ${exist ? 'disabled' : '' }"  ${exist ? 'disabled' : '' }>Submit</button>
											</form>
										</div>
									</div>
									</c:if>

									<!-- /row -->
									<div class="row mt-3">

									<c:forEach items="${comments}" var="comment">
										<div class="col-sm-6">
											<div class="card rounded-5">
												<div class="card-header bg-secondary text-white "><b>${comment.studentName} | ${comment.timestamps}</b></div>
												<div class="card-body">
													<p class="card-text">${comment.comment}</p>

												</div>
											</div>
										</div>
									</c:forEach>

									</div>
									<!-- /row -->
									<hr>
									<!-- /container -->
									<div class="text-left mt-3 mb-3">
										<a class="btn btn-primary text-white"
											onclick="window.history.back()">Back</a>
										<!-- href="/e-JAWI/NotesController?action=listNotes" -->
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
		$('#notes').addClass("active");
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

