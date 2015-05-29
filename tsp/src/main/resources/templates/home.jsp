<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<title th:text="${title}">Title</title>
<link rel="stylesheet" th:href="@{/css/bootstrap.min.css}"
	href="../../css/bootstrap.min.css" />
</head>
<body>
	<div class="container">
		<div class="navbar">
			<div class="navbar-inner">
				<a class="brand" href="http://www.thymeleaf.org"> Thymeleaf -
					Plain </a>
				<ul class="nav">
					<li><a th:href="@{/}" href="home.html"> Home </a></li>
					<li><a th:href="@{/logout}" href="logout"> Logout </a></li>
				</ul>
			</div>
		</div>
		<h1 th:text="${title}">Title</h1>
		<div th:text="${message}">Fake content</div>
		<div id="created" th:text="${#dates.format(date)}">July 11,
			2012 2:17:16 PM CDT</div>
	</div>
</body>
</html>
