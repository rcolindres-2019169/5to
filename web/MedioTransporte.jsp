<%-- 
    Document   : MedioTransporte
    Created on : 16/08/2023, 04:35:21 PM
    Author     : Carlos Altán
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

        <title>MedioTransporte</title>
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
                    <form action="Controlador?menu=MedioTransporte" method="POST">

                        <div class="form-group">
                            <label class="colorLabel" for="dato">Placa</label>
                            <input type="text" value="${medioTransporte.getPlaca()}" id="placa" name="txtPlaca" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="colorLabel" for="dato">TipoVehículo</label>
                            <input type="text"  value="${medioTransporte.getTipoVehiculo()}" id="tipoVehiculo" name="txtTipoVehiculo" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="colorLabel" for="dato">Marca</label>
                            <input type="text" value="${medioTransporte.getMarca()}" id="marca" name="txtMarca" class="form-control">
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
                            <td ><strong>CódigoTransporte</strong></td>
                            <td><strong>Placa</strong></td>
                            <td><strong>TipoVehículo</strong></td>
                            <td><strong>Marca</strong></td>
                            <td><strong>Acciones</strong></td>                   
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="medioTransporte" items="${medioTransportes}">
                            <tr>
                                <td>${medioTransporte.getCodigoTransporte()}</td>
                                <td>${medioTransporte.getPlaca()}</td>
                                <td>${medioTransporte.getTipoVehiculo()}</td>
                                <td>${medioTransporte.getMarca()}</td>
                                <td>
                                    <a class="btn btn-warning" href="Controlador?menu=MedioTransporte&accion=Editar&codigoTransporte=${medioTransporte.getCodigoTransporte()}"><span>Editar</span></a>
                                    <a class="btn btn-danger" href="Controlador?menu=MedioTransporte&accion=Eliminar&codigoTransporte=${medioTransporte.getCodigoTransporte()}" data-toggle="modal" data-target="#deleteModal-${medioTransporte.getCodigoTransporte()}"><span>Eliminar</a>
                                    <div class="modal fade" id="deleteModal-${medioTransporte.getCodigoTransporte()}" tabindex="-1" role="dialog" aria-labelledby="deleteModalTitle-${medioTransporte.getCodigoTransporte()}" aria-hidden="true">
                                        <div class="modal-dialog modal-dialog-centered" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="deleteModalTitle-${medioTransporte.getCodigoTransporte()}">Advertencia</h5>
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body">
                                                    ¿Esta seguro que desea eliminar el elemento?
                                                </div>
                                                <div class="modal-footer">
                                                    <a class="btn btn-danger" href="#" data-dismiss="modal">Cancelar</a>
                                                    <a class="btn btn-info" href="Controlador?menu=MedioTransporte&accion=Eliminar&codigoTransporte=${medioTransporte.getCodigoTransporte()}&Confirmar=Aceptar">Eliminar</a>
                                                    <div class="modal fade" id="deleteModal-${medioTransporte.getCodigoTransporte()}" tabindex="-1" role="dialog" aria-labelledby="deleteModalTitle-${medioTransporte.getCodigoTransporte()}" aria-hidden="true">

                                                        <div class="modal-dialog modal-dialog-centered" role="document">

                                                            <div class="modal-content">

                                                                <div class="modal-header">

                                                                    <h5 class="modal-title" id="deleteModalTitle-${medioTransporte.getCodigoTransporte()}">Advertencia</h5>

                                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">

                                                                        <span aria-hidden="true">&times;</span>

                                                                    </button>

                                                                </div>

                                                                <div class="modal-body">

                                                                    ¿Esta seguro que desea eliminar el elemento?

                                                                </div>

                                                                <div class="modal-footer">

                                                                    <a class="btn btn-danger" href="#" data-dismiss="modal">Cancelar</a>

                                                                    <a class="btn btn-info" href="Controlador?menu=MedioTransporte&accion=Eliminar&codigoTransporte=${medioTransporte.getCodigoTransporte()}&Confirmar=Aceptar">Eliminar</a>

                                                                </div>

                                                            </div>

                                                        </div>

                                                    </div>  </td>
                                                    </tr>
                                                </c:forEach>        
                                                </tbody>
                                                </table>



                                                <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
                                                <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
                                                <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
                                                </body>
                                                </html>