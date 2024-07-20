<%-- 
    Document   : Venta
    Created on : 8/09/2023, 08:15:01 PM
    Author     : rodro
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

        <title>Ventas</title>
        <style>
            @media print{
                .parte1, .btn, .acciones{
                    display: none;
                }



            }
        </style>
    </head>
    <body>

        <div class="d-flex">
            <div class="col-sm-4 parte1" >
                <div class="card">
                    <form action="Controlador?menu=Venta" method="POST">
                        <div class="card-body">
                            <div class="form-group">
                                <label>Datos Empresa</label>
                            </div>
                            <div class="form-group d-flex">
                                <div class="col-sm-6 d-flex">
                                    <input type="text" name="txtEmpresa" value="${empresa.getCodigoEmpresa()}" class="form-control" placeholder="Codigo">
                                    <input type="submit" name="accion" value="Buscar" class="btn btn-outline-info">
                                </div>
                                <div class="col-sm-6">
                                    <input type="text" name="txtNombreEmpresa" value="${empresa.getNombreEmpresa()}" class="form-control" disabled placeholder="empresa">
                                </div>
                            </div>
                            <div class="form-group">
                                <label>Datos Servicio</label>
                            </div>
                            <div class="form-group d-flex">
                                <div class="col-sm-6 d-flex">
                                    <input type="text" name="txtCodigoServicio" value="${tipoServicio.getCodigoTipoServicio()}" class="form-control"  placeholder="Servicio">
                                    <button type="submit" name="accion" value="BuscarServicio" class="btn-outline-info">Buscar</button>
                                </div>
                                <div class="col-sm-6">
                                    <input type="text" name="txtNombreServicio" value="${tipoServicio.getTipoServicio()}"  class="form-control" placeholder="Nombre">
                                </div>
                            </div>
                            <div class="form-group d-flex">
                                <div class="col-sm-6 d-flex">
                                    <input type="text" name="txtPrecio" value="${tipoServicio.getPrecioTipoServicio()}"  class="form-control" placeholder="Q.00.00">
                                </div>


                            </div>
                            <div class="form-group">
                                <div>
                                    <button type="submit" name="accion" value="Agregar" class="btn-outline-info">Agregar al Carrito</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="col-sm-7">
                <div class="card">
                    <div class="card-body parte2">
                        <div class="d-flex col-sm-6 ml-auto">
                            <label>Serie No.</label>
                            <input type="text" name="txtNumber" value="${numeroSerie}" class="form-control" >
                        </div>
                        <div class="col-sm-6">
                            <label>Nombre:</label>
                            <input type="text" name="txtNombreEmpresa" value="${empresa.getNombreEmpresa()}" class="form-control" disabled>
                        </div>
                        <div class="col-sm-6">
                            <label>Dirección</label>
                            <input type="text" name="txtNombreEmpresa" value="${empresa.getDireccionEmpresa()}" class="form-control" disabled>

                        </div>
                        <div class="col-sm-6">
                            <label>Telefono:</label>
                            <input type="text" name="txtNombreEmpresa" value="${empresa.getTelefonoEmpresa()}" class="form-control" disabled>
                        </div>
                        <br>

                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <td><strong>NUMERO</strong></td>
                                    <td><strong>CODIGO</strong></td>
                                    <td><strong>DESCRIPCION</strong></td>
                                    <td><strong>PRECIO</strong></td>
                                </tr>
                            </thead>
                            <tbody> 
                                <c:forEach var="list" items="${lista}">
                                    <tr>
                                        <td>${list.getItem()}</td>
                                        <td>${list.getCodigoTipoServicio()}</td>
                                        <td>${list.getDescripcion()}</td>
                                        <td>${list.getPrecio()}</td>
  
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="card-footer d-flex">
                        <div class="col-sm-6">
                            <a href="Controlador?menu=Venta&accion=GenerarVenta" onclick="print()" class="btn btn-success">Generar Venta</a>
                            <a href="Controlador?menu=Venta&accion=Cancelar" type="submit" name="accion" value="Cancelar" class="btn btn-danger">Cancelar</a>
                        </div>
                        <div class="col-sm-4 ml-auto">
                            <input type="text" name="txtTotal" value="Q. ${totalPagar}0" class="form-control" disabled>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
        <script>
                                function venta() {
                                    print();
                                    location.reload();

                                    location.reload();

                                }
        </script>
    </body>
</html>
