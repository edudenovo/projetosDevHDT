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
	<link href="css/painel.css" type="text/css" rel="stylesheet" media="screen,projection"/>
<title>Kanban</title>
</head>
<style>
tr, td{

padding: 0;
}

.card-panelKO {

    transition: box-shadow .25s;
    padding: 0px;
    margin: 0.5rem 0 1rem 0;
    border-radius: 2px;
    background-color: #fff;

}
</style>
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
	<div id="header-bar"> <div class="container"></div> </div>	
    <div class = "containerP">
      <table class="striped">
        <thead>
          <tr class="<c:out value = "${cabecalho_painel}"/>">
              <th>Leito</th>              
              <th>Nome</th>
              <th>Idade</th>
              <th>Especialidade</th>
              <th>Admissão</th>
              <th>Dias</th>
              <th>Kanban</th>
              <th>Precaução</th>
              <th>Observação</th>
              <th>Fugulin</th>
          </tr>
        </thead>
        <tbody>
        <c:forEach var="kanban" items="${resultado}">
          <tr >
            <td><h10><c:out value = "${kanban.getLeito()}"/></h10></td>            
            <td><h10><c:out value = "${kanban.getNome()}"/></h10></td>
            <td><h10><c:out value = "${kanban.getIdade()}"/></h10></td>
            <td><h10><c:out value = "${kanban.getEspecialidade()}"/></h10></td>
            <td><h10><c:out value = "${kanban.getAdmissao()}"/></h10></td>
            <td><h10><c:out value = "${kanban.getDias()}"/></h10></td>
            <td class="<c:out value = "${kanban.getKanban()}"/>"><h10></h10></td>
            <td><h10><c:out value = "${kanban.getObs()}"/></h10></td>
            <td><h10><c:out value = "${kanban.getObservacao()}"/></h10></td>
            <td><h10><c:out value = "${kanban.getFugulin()}"/></h10></td>
          </tr>          
          </c:forEach>
        </tbody>
      </table>


   
   		 <div id="legenda">     
			<table>
				<thead>
					<tr >
						<th>Legenda</th>					
						<th>Criança</th>
						<th>Adulto</th>					
					</tr>
				</thead>
				<tr>
					<td class="card-panelKO green lighten-2"></td>
					<td>Até 9 dias</td>
					<td>Até 12 dias </td>
				</tr>
				<tr>
					<td class="card-panelKO yellow lighten-2"></td>
					<td>De 10 a 12 dias</td>
					<td>De 13 a 15 dias</td>
				</tr>
				<tr>
					<td class="card-panelKO red lighten-2"></td>
					<td>Após 13 dias</td>
					<td>Após 16 dias</td>
				</tr>
			</table>
		</div>
		</div>
	<!-- Utilizando a tag core do JSTL -->

	<div id="footer-bar"> <!-- conteudo editavel no rodape --> 
		<div class = "container">
			<div id="selo-acesso-informacao" style="display: inline-block; width: 22%; float: left; "> 
				<a href="http://www.acessoainformacao.gov.br/" target="_blank"> 
					<img id ="info" alt="Acesso à Informação" src="http://www.ebserh.gov.br/documents/733616/733882/Selo+acesso+a+informa%C3%A7%C3%A3o.png/021d9b9b-1326-47c1-af9d-8edac8b7e2b9?t=1441206694243"> 
				</a> 
			</div> 
			<div id="texto-rodape" style="color: #ffffff; font-size: 12px; text-align: center; display: inline-block; width: 50%; margin: 0 auto;"> 
				${rodape}
			</div> 
			<div id="texto-rodape" style="color: #ffffff; font-size: 12px; text-align: center; display: inline-block; width: 50%; margin: 0 auto;"> 
				Desenvolvido por SGII HDT-UFT <c:out value = "${so}"/>
			</div>			 
		</div> 
	</div>
	
  <!--  Scripts-->
  <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script src="js/materialize.js"></script>
</body>
</html>