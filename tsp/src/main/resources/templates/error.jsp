<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<title>Error</title>
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
		<div id="created" th:text="${#dates.format(timestamp)}">July 11,
			2012 2:17:16 PM CDT</div>
		<div>
			There was an unexpected error (type=<span th:text="${error}">Bad</span>, status=<span th:text="${status}">500</span>).
		</div>
		<div th:text="${message}">Fake content</div>
		<div>
			Please contact the operator with the above information.
		</div>
	</div>
</body>
</html>
