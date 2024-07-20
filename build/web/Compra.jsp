<%-- 
    Document   : Compra
    Created on : 16/08/2023, 01:48:52 PM
    Author     : abads
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

            <title>Compra</title>
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
                        <form action="Controlador?menu=Compra" method="POST">
                            

                            <div class="form-group">
                                <label class="colorLabel" for="dato">Costo compra</label>
                                <input type="number" placeholder="Q.0.00" step="0.01" value="${compra.getCostoCompra()}" name="txtCostoCompra" class="form-control">
                            </div>

                            <div class="form-group">
                                <label class="colorLabel" for="dato">Descripción compra</label>
                                <input type="text" value="${compra.getDescripcionCompra()}" name="txtDescripcionCompra" class="form-control">
                            </div>

                            <div class="form-group">
                                <label class="colorLabel" for="dato">Fecha compra</label>
                                <input type="date" value="${compra.getFechaCompra()}" name="txtFechaCompra" class="form-control">
                            </div>

                            <div class="form-group">
                                <label class="colorLabel" for="dato">Codigo Empresa</label>
                                <select name="cmbCodigoEmpresa" class="form-control">
                                    <option disabled selected value="">Seleccione una opción</option>
                                    <c:forEach var="empresa" items="${empresas}">
                                        <option value="${empresa.getCodigoEmpresa()}">${empresa.getCodigoEmpresa()} - ${empresa.getNombreEmpresa()}</option>
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
                                <td ><strong>Código compra</strong></td>
                                <td><strong>Costo compra</strong></td>
                                <td><strong>Descripción compra</strong></td>
                                <td><strong>Fecha compra</strong></td>                   
                                <td><strong>Código empresa</strong></td>                   
                                <td><strong>Acción</strong></td>                   
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="compra" items="${compras}">
                                <tr>
                                    <td>${compra.getCodigoCompra()}</td>
                                    <td>Q${compra.getCostoCompra()}</td>
                                    <td>${compra.getDescripcionCompra()}</td>
                                    <td>${compra.getFechaCompra()}</td>
                                    <td>${compra.getCodigoEmpresa()}</td>
                                    <td>
                                        <a class="btn btn-warning" href="Controlador?menu=Compra&accion=Editar&codigoCompra=${compra.getCodigoCompra()}">Editar</a>
                                        <a class="btn btn-danger" href="Controlador?menu=Compra&accion=Eliminar&codigoCompra=${compra.getCodigoCompra()}">Eliminar</a>
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
