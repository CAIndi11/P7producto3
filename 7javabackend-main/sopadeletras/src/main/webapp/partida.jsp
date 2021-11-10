<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<% if (session.getAttribute("user")== null)
    {
%>
    <jsp:forward page="index.jsp"></jsp:forward>

<% }
if (request.getParameter("salir")!= null)
    {
    session.invalidate();
    %>
    <jsp:forward page="index.jsp"></jsp:forward>
   <% } %>

<head>
	<meta charset="UTF-8">
	<title>7J - Sopa de letras</title>
	<link rel="stylesheet" type="text/css" href="css/wordfind.css">	
    <link rel="stylesheet" href="css/bootstrap.min.css">   
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    		
    <script src="js/bootstrap.min.js"></script>
    
    <script type="text/javascript" src="js/jquery.min.js"></script>    
        <!-- Librerias para generar el juego -->
    <script type="text/javascript" src="js/wordfind.js"></script>
    <script type="text/javascript" src="js/wordfindgame.js"></script>
</head>
<body>

	<div class="alert alert-success text-center fade" role="alert" id="puntosAlert">
			<p>¡¡Enhorabuena!! Se ha guardado tu puntuación de <strong><span id="puntos"></span></strong></p>			
			<form><input type="submit" value="jugar de nuevo" class="btn btn-dark"></form>
	</div>

<div class="container">

	<div class="row w-100">
		<div class="col-md-4 text-center">
			<button type="button" id="solve" class="btn btn-warning btn-lg">Resolver</button>
		</div>
		<div class="col-md-4 text-center">
			<h1>Bienvenido <%= session.getAttribute("user") %></h1>
			<div id="timer">0</div>
		</div>
		<div class="col-md-4 text-center">
			<form><input type="submit" value="Salir" name="salir" class="btn btn-danger btn-lg"></form>
		</div>
	</div>
	<div class="row w-100">
		<div class="col-md-8">
			<div id='puzzle'></div>
		</div>
		<div class="col-md-4">
			<p>Estas son las palabras que debes encontrar</p>
			<div id='words'></div>	
		</div>
	</div>
</div>

<!-- <script language="javascript" type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script> -->
<!-- <script type="text/javascript" src="../src/wordfind.js"></script> -->
<!-- <script type="text/javascript" src="scripts/wordfindgame.js"></script> -->


	
<script>

var timer = setInterval(voyComprobando, 1000);

var t = 0;

function voyComprobando(){
	var l = document.getElementById("timer");
	l.innerHTML = t;
	if ($('.puzzleSquare').hasClass('complete')){
		hasAcertado();
	}
	t++
}

var hasAcertado = function(){	
	$.ajax({
		type: "POST",
		url: "partida",
		data: {tiempo: t},
		success: function(dataresult){
			$('#puntos').text(dataresult);    
			$("#puntosAlert").addClass("show"); 
		},
		error: function(jqXHR, exception){
			console.log('Error occured!!');
		}		
	})
	clearInterval(timer);	
};


 var words = [];
 
<c:forEach items="${listaPalabras}" var="diccionario"> 
	words.push("${diccionario.palabra}");
</c:forEach>

  
 var puzzle = wordfind.newPuzzle(words, {
    height: 10,
    width: 10,
    fillBlanks: false
});
   

var gamePuzzle = wordfindgame.create(words, '#puzzle', '#words');
  
 $('#solve').click(function() {
  clearInterval(timer);
     wordfindgame.solve(gamePuzzle, words);
 });

</script>
	
</body>
</html>