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
<title>Lista Telefonica</title>
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
		<form class="col s12" name="formulario"  action="incluir_obs" method="post">
		</br>
		</br>
		</br>
		</br>
		<div class="center-align">
		Usuário sem permissão de acesso, procurar o seu gestor para solicitar acesso!
		</div>
		</br>
		</br>
		</br>
		</br>
        <div>
            <div class="center-align">
                <a onclick="sair();" class="btn light-green lighten"><i class="material-icons left"></i>Sair</a>
            </div>
        </div>
		
  	</form>
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
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.10/jquery.mask.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
  <script src="js/materialize.js"></script>
  <script src="js/script.js"></script>
</body>
</html>