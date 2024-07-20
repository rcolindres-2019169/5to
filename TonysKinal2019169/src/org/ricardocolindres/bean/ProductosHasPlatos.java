package org.ricardocolindres.bean;

public class ProductosHasPlatos {
    
    private int Productos_codigoProducto;
    private int codigoPlato;
    private int codigoProducto;
    
    public ProductosHasPlatos(){
        
    }

    public ProductosHasPlatos(int Productos_codigoProducto, int codigoPlato, int codigoProducto) {
        this.Productos_codigoProducto = Productos_codigoProducto;
        this.codigoPlato = codigoPlato;
        this.codigoProducto = codigoProducto;
    }

    public int getProductos_CodigoProducto() {
        return Productos_codigoProducto;
    }

    public void setProductos_CodigoProducto(int productos_CodigoProducto) {
        this.Productos_codigoProducto = productos_CodigoProducto;
    }

    public int getCodigoPlato() {
        return codigoPlato;
    }

    public void setCodigoPlato(int codigoPlato) {
        this.codigoPlato = codigoPlato;
    }

    public int getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }
    
    
    
}
