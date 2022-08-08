package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LineaFactura {
    private int id;
    private double total;
    private Factura factura;
    private Item item;

    private int cantidad;

    public LineaFactura(int id, double total, Factura factura, Item item, int cantidad) {
        this.id = id;
        this.total = total;
        this.factura = factura;
        this.item = item;
        this.cantidad = cantidad;
    }

    public LineaFactura() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void create(LineaFactura lineaFactura){
        try{
            String statement = "INSERT INTO lineafactura(id, total, id_fact, id_item, cantidad) VALUES (?,?,?,?,?)";
            PreparedStatement pst = Conexion.createConnection().prepareStatement(statement);
            pst.setInt(1, lineaFactura.getId());
            pst.setDouble(2, lineaFactura.getTotal());
            pst.setInt(3, lineaFactura.getFactura().getId());
            pst.setInt(4, lineaFactura.getItem().getId());
            pst.setInt(5, lineaFactura.getCantidad());
            int result = pst.executeUpdate();
            System.out.println((result>0) ? "\nLa linea factura se almaceno existosamente" : "No se pudo almacenar la linea factura");
        }catch (SQLException ex) {
            System.out.println( "CREATE FACTURA "+ex.getMessage());
        }

    }
    
    public List<LineaFactura> getLineasFacturas(){
        List<LineaFactura> lineasFacturas = new ArrayList<LineaFactura>();
        Factura fact = new Factura();
        Item ite = new Item();

        try{
            String statement = "SELECT * FROM lineafactura";
            PreparedStatement pst = Conexion.createConnection().prepareStatement(statement);
            ResultSet resultSet = pst.executeQuery();
            while(resultSet.next()){
                LineaFactura lineaFactura = new LineaFactura(resultSet.getInt(1),resultSet.getDouble(2),fact.getFacturaById(resultSet.getInt(3)),ite.getItemById(resultSet.getInt(4)),resultSet.getInt(5));
                lineasFacturas.add(lineaFactura);
            }
        }catch (SQLException ex){
            System.out.println( "SELECT LINEA FACTURAS "+ex.getMessage());
        }
        return lineasFacturas;
    }

    @Override
    public String toString() {
        return "Linea Factura " + id +
                "\n\tTotal:   " + total +
                "\n\tFactura: " + factura +
                "\n\tItem:    " + item.getNombre() +
                "\n\tPrecio:  " + item.getPrecio();
    }
}
