<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>猫神</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="keywords"
	content="Ethos Login Form Responsive Templates, Iphone Widget Template, Smartphone login forms,Login form, Widget Template, Responsive Templates, a Ipad 404 Templates, Flat Responsive Templates" />
<script type="application/x-javascript">
	
	
	



</script>

<script type="text/javascript"
	src="http://127.0.0.1:8080/commonSpringMvc-webapp/js/baseJs/jquery-1.10.2.min.js"></script>
<script type="text/javascript"
	src="http://127.0.0.1:8080/commonSpringMvc-webapp/js/baseJs/json2.js"></script>
<script type="text/javascript"
	src="http://127.0.0.1:8080/commonSpringMvc-webapp/js/baseJs/ajaxupload.js"></script>
<script type="text/javascript"
	src="http://127.0.0.1:8080/commonSpringMvc-webapp/js/login/login.js"></script>

<link href="http://127.0.0.1:8080/commonSpringMvc-webapp/css/style.css"
	rel='stylesheet' type='text/css' />

</head>
<body>


	<div class="main">
		<div class="login">
			<h1>Ethos Login Form</h1>
			<div class="inset">

				<table>
					<tr>
						<c:forEach items="${jdxList}" var="item">
							<td>${item},</td>
						</c:forEach>
					</tr>
					<tr>
						<c:forEach items="${jdxMap}" var="item">
							<td>${item.value},</td>
						</c:forEach>
					</tr>
					<tr>
						<c:forEach items="${jdxListObject}" var="item">
							<td>${item.id},</td> 
						</c:forEach>
					</tr>
					<tr>
						<c:forEach items="${jdxMapObject}" var="item">
							<td>${item.value.id},</td>
							<td>${item.key},</td>
						</c:forEach>
					</tr>
				</table>
				<!--start-main-->

			</div>
		</div>
		<!--//end-main-->
	</div>
	<!--start-copyright-->
	<div class="copy-right">
		<p>
			&copy; 2015 Ethos Login Form. All Rights Reserved | Design by <a
				href="http://www.moke8.com/" target="_blank">moke8</a>
		</p>

	</div>
	<!--//end-copyright-->
</body>
</html>
