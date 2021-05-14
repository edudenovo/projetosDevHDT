<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<!-- CSS  -->
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
	<link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
	<link href="css/painel.css" type="text/css" rel="stylesheet" media="screen,projection"/>
<title>Protocolo Agendamento</title>
</head>
<body>
	<div id="barra-brasil">
		<div id = "wrapper-barra-brasil">
			<div class="brasil-flag">
				<img src="img/brasil.svg" class="link-barra"/>
			</div>
		</div>
	</div> 
	<header id="banner" role="banner">	
		<div class="container"> 
			<div id="logo">
				<img alt="Portal Ebserh" height="60" src="http://www.ebserh.gov.br/image/layout_set_logo?img_id=14179&amp;t=1493120790262" width="177">
			</div>
		</div> 
	</header>
	<div id="header-bar"> 
		
		
			<div class = "container"><form name="form">
				<table class="striped" >
			        <thead>
			          <tr>
			              <th>Número Paciente</th>              
			              <th>Data da Consulta</th>
			              <th>Hora</th>
			              <th>Profissional</th>
			          </tr>
			        </thead>
			        <tbody style="cursor: pointer;">
			        <c:forEach var="consulta" items="${resultado}">
			          <tr >
			            <td ><c:out value = "${consulta.getNumero_paciente()}"/></td>            
			            <td ><c:out value = "${consulta.getData_consulta()}"/></td>
			            <td ><c:out value = "${consulta.getHora()}"/></td>
			            <td ><c:out value = "${consulta.getProfissional()}"/></td>
			          </tr>          
			          </c:forEach>
			        </tbody>
			      </table>	</form>		      
			</div>
		
		<div class="center-align">
        	<a onclick="voltar();" class="btn light-green lighten">Voltar</a>
       	</div>
  	</div>	

		
	
  <!--  Scripts-->
  <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>   
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.10/jquery.mask.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
  <script src="js/materialize.js"></script>
  <script src="js/script.js"></script>
</body>
</html>