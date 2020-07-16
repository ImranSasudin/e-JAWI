
<%
	String email = (String) session.getAttribute("currentSessionUser");
	String name = (String) session.getAttribute("currentSessionUserName");
%>
<div class="sidebar-background"></div>
<div class="sidebar-wrapper scrollbar-inner">
	<div class="sidebar-content">
		<div class="user">
			<div class="avatar-sm float-left mr-2">
				<img src="/e-JAWI/assets/img/profile.png" alt="..."
					class="avatar-img rounded-circle">
			</div>
			<div class="info">
				<a data-toggle="collapse" href="#collapseExample"
					aria-expanded="true"> <span> <%=name%> <span
						class="user-level">Parent</span> <span class="caret"></span>
				</span>
				</a>
				<div class="clearfix"></div>

				<div class="collapse in" id="collapseExample">
					<ul class="nav">
						<li><a href="/e-JAWI/ParentController?action=viewAccount"> <span class="link-collapse">My
									Profile</span>
						</a></li>
						<li><a href="/e-JAWI/ParentController?action=editAccount"> <span class="link-collapse">Edit
									Profile</span>
						</a></li>
					</ul>
				</div>
			</div>
		</div>
		<ul class="nav">
			<li id="dashboard" class="nav-item"><a
				href="/e-JAWI/AdminDashboard.jsp"> <i class="fas fa-home"></i>
					<p>Dashboard</p>
			</a></li>
			<li id="quizzes" class="nav-item"><a
				href="/e-JAWI/ParentController?action=childrenList"> <i class="fas fa-pen-square"></i>
					<p>Quizes</p>
			</a></li>

		</ul>
	</div>
</div>