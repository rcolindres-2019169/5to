<%-- 
    Document   : index
    Created on : 9/08/2023, 05:28:18 PM
    Author     : Ricardo Colindres
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>  
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <title>Iniciar Sesión</title>
    <style>
            body{
                margin: 0;
                padding: 0;
                background: #fff;
                color: #fff;
                font-size: 12px;
            }

            .body{
                position: absolute;
                top: 1px;
                left: -1px;
                right: 1px;
                bottom: 0px;
                width: auto;
                height: auto;
                background-image: url("./img/fondo.jpg");
                background-size: cover;
                -webkit-filter: blur(5px);
                z-index: 0;
            }

            .grad{
                position: relative;
                top: -10px;
                left: -20px;
                right: -40px;
                bottom: -40px;
                width: auto;
                height: auto;
                background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(0,0,0,0)), color-stop(100%,rgba(0,0,0,0.65)));
                opacity: 0.7;
            }

            .header{
                position: absolute;
                top: calc(50% - 35px);
                left: calc(47% - 255px);
                z-index: 2;
            }

            .header div{
                float: left;
                color: #fff;
                font-family: 'Exo', sans-serif;
                font-size: 35px;
                font-weight: 200;
            }

            .header div span{
                color: #5379fa !important;
            }

            .login{
                position: absolute;
                top: calc(55% - 75px);
                left: calc(50% - 50px);
                height: 140px;
                width: 350px;
                padding: 5px;
                z-index: 2;
            }

            .login input[type=text]{
                width: 250px;
                height: 30px;
                border: 1px solid rgba(255,255,255,0.6);
                border-radius: 5px;
                color: black;
                font-family: 'Exo', sans-serif;
                font-size: 16px;
                font-weight: 400;
                padding: 4px;
            }

            .login input[type=password]{
                width: 250px;
                height: 30px;
                border: 1px solid rgba(255,255,255,0.6);
                border-radius: 5px;
                font-family: 'Exo', sans-serif;
                font-size: 16px;
                font-weight: 400;
                padding: 4px;
                margin-top: 10px;
            }

            .login input[type=submit]{
                width: 250px;
                height: 35px;
                background: #1rr5dd ;
                border: 1px solid  #1dd3dd ;
                cursor: pointer;
                border-radius: 20px;
                color: #000;
                font-family: 'Exo', sans-serif;
                font-size: 16px;
                font-weight: 400;
                padding: 6px;
                margin-top: 10px;
            }

            .login input[type=button]:hover{
                opacity: 0.8;
                border-radius: 20px;
            }

            .login input[type=button]:active{
                opacity: 0.6;
            }

            .login input[type=text]:focus{
                outline: none;
                border: 1px solid rgba(255,255,255,0.9);
            }

            .login input[type=password]:focus{
                outline: none;
                border: 1px solid rgba(255,255,255,0.9);
            }

            .login input[type=button]:focus{
                outline: none;
            }

            ::-webkit-input-placeholder{
                color: rgba(255,255,255,0.6);
            }

            ::-moz-input-placeholder{
                color: rgba(255,255,255,0.6);
            }
        </style>
    </head>
    
    <body>
    <div class="body"></div>
    <form class="form-sign" action="Validar" method="POST">
        <div class="grad"></div>

        <div class="header">

            <center><img src="img/logo2.png"alt="150" width="220" /></center>
        </div>
        <br>
        <div class="login">
            <input type="text" name="txtUser" placeholder="Usuario" class="form-control"><br>
            <input type="password" name="txtPass" placeholder="Contraseña"  class="form-control"><br>
            <br>
            <input type="submit" name="accion" value="Ingresar"  class="btn btn-info btn-lg ">
        </div>
    </form>
    
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
  </body>
</html>
