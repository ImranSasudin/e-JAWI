<%
	String email = (String) session.getAttribute("currentSessionUser");
	String name = (String) session.getAttribute("currentSessionUserName");
%>
<!-- Logo Header -->
<div class="logo-header">

	<a href="index.html" class="logo"> <img
		src="/e-JAWI/assets/img/ejawi (4).png" alt="navbar brand"
		class="navbar-brand">
	</a>
	<button class="navbar-toggler sidenav-toggler ml-auto" type="button"
		data-toggle="collapse" data-target="collapse" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"> <i class="fa fa-bars"></i>
		</span>
	</button>
	<button class="topbar-toggler more">
		<i class="fa fa-ellipsis-v"></i>
	</button>
	<div class="navbar-minimize">
		<button class="btn btn-minimize btn-rounded">
			<i class="fa fa-bars"></i>
		</button>
	</div>
</div>
<!-- End Logo Header -->

<!-- Navbar Header -->
<nav class="navbar navbar-header navbar-expand-lg">

	<div class="container-fluid">

		<ul class="navbar-nav topbar-nav ml-md-auto align-items-center">

			<li class="nav-item dropdown hidden-caret"><a
				class="dropdown-toggle profile-pic" data-toggle="dropdown" href="#"
				aria-expanded="false">
					<div class="avatar-sm">
						<img src="/e-JAWI/assets/img/profile.png" alt="..."
							class="avatar-img rounded-circle">
					</div>
			</a>
				<ul class="dropdown-menu dropdown-user animated fadeIn">
					<li>
						<div class="user-box">
							<div class="avatar-lg">
								<img src="/e-JAWI/assets/img/profile.png" alt="image profile"
									class="avatar-img rounded">
							</div>
							<div class="u-text">
								<h4><%=name %></h4>
								<p class="text-muted"><%=email %></p>
								<a href="/e-JAWI/TeacherController?action=viewProfile" class="btn btn-rounded btn-danger btn-sm">View
									Profile</a>
							</div>
						</div>
					</li>
					<li>
						<div class="dropdown-divider"></div> 
						<a class="dropdown-item" href="/e-JAWI/TeacherController?action=viewProfile">My Profile</a> 
						<div class="dropdown-divider"></div> 
						<a class="dropdown-item" href="/e-JAWI/LoginController?action=Logout">Logout</a>
					</li>
				</ul></li>

		</ul>
	</div>
</nav>
<!-- End Navbar -->