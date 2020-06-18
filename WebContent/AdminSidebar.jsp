
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
						class="user-level">Admin</span> <span class="caret"></span>
				</span>
				</a>
				<div class="clearfix"></div>

				<div class="collapse in" id="collapseExample">
					<ul class="nav">
						<li><a href="#profile"> <span class="link-collapse">My
									Profile</span>
						</a></li>
						<li><a href="#edit"> <span class="link-collapse">Edit
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
			<li id="User" class="nav-item"><a data-toggle="collapse"
				href="#user"> <i class="fas fa-users"></i>
					<p>Users</p> <span class="caret"></span>
			</a>
				<div class="collapse" id="user">
					<ul class="nav nav-collapse">
						<li id="Teacher"><a
							href="/e-JAWI/TeacherController?action=ListTeacher"> <span
								class="sub-item">Teacher</span>
						</a></li>
						<li id="Student"><a
							href="/e-JAWI/StudentController?action=listQuiz"> <span
								class="sub-item">Student</span>
						</a></li>
						<li id="Parent"><a
							href="/e-JAWI/StudentController?action=listQuiz"> <span
								class="sub-item">Parent</span>
						</a></li>
					</ul>
				</div></li>
			<li id="notes" class="nav-item"><a
				href="/e-JAWI/NotesController?action=listNotes"> <i
					class="fas fa-layer-group"></i>
					<p>Notes</p>
			</a></li>
			<li id="quizzes" class="nav-item"><a
				href="#"> <i class="fas fa-pen-square"></i>
					<p>Quizes</p>
			</a></li>

		</ul>
	</div>
</div>