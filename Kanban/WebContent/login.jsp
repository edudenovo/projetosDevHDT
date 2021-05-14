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
<title>Login Kanban</title>


    <style type="text/css">
        html,
        body {
            height: 100%;
        }
        
        html {
            display: table;
            margin: auto;
        }
        
        body {
            display: table-cell;
            vertical-align: middle;
        }
        
        .margin {
            margin: 0 !important;
        }
    </style>

    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body class="light-green lighten-3">

    <div class="container">
        <div class="row">
            <div class="col s12 m8 offset-m2">

                <form class="login-form" name="formulario" method="POST" action="login">
                    <div class="card">
                        <div class="card-image">
                            <img src="img/login_kanban.jpg">
                            <span class="card-title">
							
                        </span>
                        </div>
                        <div class="card-content">
                            <div class="input-field">
                                <input class="validate" id="usuario" name="usuario" type="text">
                                <label for="usuario">Usuário</label>
                            </div>

                            <div class="row">
                                <div class="col s12 m8 l9">
                                    <div class="input-field">
                                        <input id="password" name="password" type="password">
                                        <label for="password">Senha</label>
                                    </div>
                                </div>                                
                            </div>
                        </div>
                        <div class="card-action light-green lighten-1">
                            <div class="center-align">
                                <a onclick="login();" class="btn light-green lighten"><i class="material-icons left">vpn_key</i>Login</a>
                                <a onclick="kanban();" class="btn light-green lighten"><i class="material-icons left"></i>Painel Kanban</a>
                            </div>
                        </div>
                    </div>
                </form>

            </div>
        </div>
    </div>


    <!--Import jQuery before materialize.js-->
	<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>   
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.10/jquery.mask.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
	<script src="js/materialize.js"></script>
	<script src="js/script.js"></script>


<div class="hiddendiv common"></div></body></html>