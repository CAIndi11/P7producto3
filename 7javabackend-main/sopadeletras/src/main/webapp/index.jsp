<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Javeadores - Sopa de letras</title>
    
    <link rel="stylesheet" href="css/bootstrap.min.css">   
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
        		
    <script src="js/bootstrap.min.js"></script>     
</head>
<body>
<!-- Llamaremos al servlet usando jQuery AJAX.
Abajo está el Input donde dan el username. 
jQuery AJAX ejecutara nuestro servlet y procesará la respuesta-->
<div class="container">
	<c:if test ="${not empty param.username}">
		<div class="alert alert-danger text-center" role="alert">
			<P>El usuario <strong><c:out value="${param.username}"></c:out></strong> o la contraseña no es válido</P>
		</div>
	</c:if>

	<div class="d-flex justify-content-center h-100">
		<div class="card">
			<div class="card-header">
				<h3>Acceso a Juego</h3>
			</div>
			<div class="card-body">
				<form action="login" method="post">
					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-user"></i></span>
						</div>
						<input type="text" class="form-control" placeholder="username" name="username">
						
					</div>
					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-key"></i></span>
						</div>
						<input type="password" class="form-control" placeholder="password" name="password">
					</div>
					<div class="form-group text-center">
						<input type="submit" value="Entrar" class="btn login_btn">
					</div>
				</form>
			</div>
			<div class="card-footer">
				<div class="d-flex justify-content-center links">
					<h3>Sopa de letras</h3>
				</div>
				<div class="d-flex justify-content-center links">
					Equipo Javeadores
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>