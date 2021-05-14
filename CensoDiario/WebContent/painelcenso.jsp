<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<!-- CSS  -->
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
	<link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
	<link href="css/painel.css" type="text/css" rel="stylesheet" media="screen,projection"/>
<title>Censo MV x AGHU</title>
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
	<div id="header-bar"> <div class="container"></div> </div>	
	<div class = "container">
      <table>
        <thead>
          <tr class="<c:out value = "${cabecalho_painel}"/>">
              <th>Prontuario MV</th>
              <th>Leito MV</th>
              <th>Paciente MV</th>
              <th>Prontuario AGHU</th>
              <th>Leito AGHU</th>
              <th>Paciente AGHU</th>
          </tr>
        </thead>
        <tbody>
        <c:forEach var="censo" items="${painelCenso}">
          <tr class="<c:out value = "${censo.getCss()}"/>">
            <td><c:out value = "${censo.getProntuario_mv()}"/></td>
            <td><c:out value = "${censo.getLeito_mv()}"/></td>
            <td><c:out value = "${censo.getNome_mv()}"/></td>
            <td><c:out value = "${censo.getProntuario_aghu()}"/></td>
            <td><c:out value = "${censo.getLeito_aghu()}"/></td>
            <td><c:out value = "${censo.getNome_aghu()}"/></td>
          </tr>          
          </c:forEach>
        </tbody>
      </table>
    </div>
	<!-- Utilizando a tag core do JSTL -->
		<div id="footer-bar"> <!-- conteudo editavel no rodape --> 
		<div class = "container">
			<div id="selo-acesso-informacao" style="display: inline-block; width: 22%; float: left; "> 
				<a href="http://www.acessoainformacao.gov.br/" target="_blank"> 
					<img id ="info" alt="Acesso � Informa��o" src="http://www.ebserh.gov.br/documents/733616/733882/Selo+acesso+a+informa%C3%A7%C3%A3o.png/021d9b9b-1326-47c1-af9d-8edac8b7e2b9?t=1441206694243"> 
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