
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
						class="user-level">Student</span> <span class="caret"></span>
				</span>
				</a>
				<div class="clearfix"></div>

				<div class="collapse in" id="collapseExample">
					<ul class="nav">
						<li><a href="/e-JAWI/StudentController?action=viewAccount"> <span class="link-collapse">My
									Profile</span>
						</a></li>
						<li><a href="/e-JAWI/StudentController?action=editAccount"> <span class="link-collapse">Edit
									Profile</span>
						</a></li>
					</ul>
				</div>
			</div>
		</div>
		<ul class="nav">
			<li id="dashboard" class="nav-item"><a
				href="/e-JAWI/StudentDashboard.jsp"> <i class="fas fa-home"></i>
					<p>Dashboard</p>
			</a></li>
			<!-- <li class="nav-section"><span class="sidebar-mini-icon">
								<i class="fa fa-ellipsis-h"></i>
						</span>
							<h4 class="text-section">Components</h4></li> -->
			<li id="notes" class="nav-item"><a
				href="/e-JAWI/NotesController?action=listNotes"> <i
					class="fas fa-layer-group"></i>
					<p>Notes</p>
			</a></li>
			<li id="quizzes" class="nav-item"><a data-toggle="collapse"
				href="#quiz"> <i class="fas fa-pen-square"></i>
					<p>Quizes</p> <span class="caret"></span>
			</a>
				<div class="collapse" id="quiz">
					<ul class="nav nav-collapse">
						<li id="takequiz"><a href="/e-JAWI/QuizController?action=listQuiz"> <span
								class="sub-item">Take Quiz</span>
						</a></li>
						<li id="result"><a href="/e-JAWI/StudentController?action=listQuiz"> <span
								class="sub-item">Result</span>
						</a></li>
					</ul>
				</div></li>

		</ul>
	</div>
</div>