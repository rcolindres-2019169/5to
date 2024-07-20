<%-- 
    Document   : Servicio
    Created on : 12/08/2023, 05:25:46 PM
    Author     : rodro
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

            <title>Servicio</title>
            <style>

                body {
                    background-color:  #ffffffa0;
                }
                .card-form {
                    width: 750px;
                    height: 400;
                    left: 25%;
                    margin-right: -150;
                    margin-top: 15px;
                    background-color: #b8f4f4;
                }
                .card-table {
                    margin: 35px;
                }
                .action-btns {
                    display: flex;
                    justify-content: space-between;
                }
                .colorLabel{
                    color: rgb(0, 0, 0);
                }
                .table-hover{
                    text-align: center;

                }
                .btn{
                    background-color: #ffffff;
                    color: rgb(0, 0, 0);
                }


            </style>
        </head>
        <body>
            <div >
                <div class="card col-sm-11.5 card-form">
                    <div class="card-body">
                        <form acti action="Controlador?menu=Servicio"  method="POST">

                            <div class="form-group">
                                <label class="colorLabel" for="dato">Lugar Servicio</label>
                                <input type="text" id="" value="${servicio.getLugarServicio()}" name="txtLugarServicio" class="form-control">
                            </div>
                            <div class="form-group">
                                <label class="colorLabel" for="dato">Número Servicio</label>
                                <input type="text" id="" value="${servicio.getNumeroServicio()}" name="txtNumeroServicio" class="form-control">
                            </div>
                            <div class="form-group">
                                <label class="colorLabel" for="dato">Hora Servicio</label>
                                <input type="time" id="" value="${servicio.getHoraServicio()}" name="txtHoraServicio" class="form-control">
                            </div>
                            <div class="form-group">
                                <label class="colorLabel" for="dato">Fecha Servicio</label> 
                                <input type="date" id="" value="${servicio.getFechaServicio()}" name="txtFechaServicio" class="form-control">
                            </div>
                            <div class="form-group" >
                                <label class="colorLabel" for="dato">Código Tipo Servicio</label>
                                
                                <select name="cmbCodigoTipoServicio" class="form-control"   >
                                    <option disabled selected value="">Seleccione una opcion</option>
                                    <c:forEach var="tipoServicio" items="${tipoServicios}">
                                        <option >${tipoServicio.getCodigoTipoServicio()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="action-btns">
                                <input type="submit" name="accion" value="Agregar" class="btn btn-info">
                                <input type="submit" name="accion" value="Actualizar" class="btn btn-success">
                            </div>
                        </form>
                    </div>
                </div>
                <div class="col-sm-11.5 card-table">
                    <table class="table table-hover">
                        <thead class="thead thead-hover">
                            <tr>
                                <td ><strong>Código Servicio</strong></td>
                                <td><strong>Lugar Servicio</strong></td>
                                <td><strong>Número Servicio</strong></td>
                                <td><strong>Hora Servicio</strong></td>
                                <td><strong>Fecha Servicio</strong></td>                   
                                <td><strong>Código tipo Servicio</strong></td>                   
                                <td><strong>Acción</strong></td>                   
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="servicio" items="${servicios}">
                                <tr>
                                    <td>${servicio.getCodigoServicio()}</td>
                                    <td>${servicio.getLugarServicio()}</td>
                                    <td>${servicio.getNumeroServicio()}</td>
                                    <td>${servicio.getHoraServicio()}</td>
                                    <td>${servicio.getFechaServicio()}</td>
                                    <td>${servicio.getCodigoTipoServicio()}</td>
                                    <td>
                                        <a class="btn btn-warning"   href="Controlador?menu=Servicio&accion=Editar&codigoServicio=${servicio.getCodigoServicio()}">Editar</a>
                                        <a class="btn btn-danger" href="Controlador?menu=Servicio&accion=Eliminar&codigoServicio=${servicio.getCodigoServicio()}">Eliminar</a>
                                    </td>
                                </tr>
                            </c:forEach>   
                        </tbody>
                    </table>
                </div>
            </div>

            <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
        </body>
    </html>
