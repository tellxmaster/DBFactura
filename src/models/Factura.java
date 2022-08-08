package models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Factura {
    private int id;
    private Date fecha;
    private Cliente cliente;

    public Factura(int id, Date fecha, Cliente cliente) {
        this.id = id;
        this.fecha = fecha;
        this.cliente = cliente;
    }

    public Factura() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void create(Factura factura){
        try{
            String statement = "INSERT INTO factura(id, fecha, id_cliente) VALUES (?,?,?)";
            PreparedStatement pst = Conexion.createConnection().prepareStatement(statement);
            pst.setInt(1, factura.getId());
            pst.setDate(2, factura.getFecha());
            pst.setInt(3, factura.getCliente().getId());
            int result = pst.executeUpdate();
            System.out.println((result>0) ? "\nLa factura se almaceno existosamente" : "No se pudo almacenar la factura");
        }catch (SQLException ex) {
            System.out.println( "CREATE FACTURA "+ex.getMessage());
        }

    }
    
    public List<Factura> getFacturas(){
        List<Factura> facturas = new ArrayList<Factura>();
        Cliente cli = new Cliente();

        try{
            String statement = "SELECT * FROM factura";
            PreparedStatement pst = Conexion.createConnection().prepareStatement(statement);
            ResultSet resultSet = pst.executeQuery();
            while(resultSet.next()){
                Factura factura = new Factura(resultSet.getInt(1),resultSet.getDate(2), cli.getClienteById(resultSet.getInt(3)));
                facturas.add(factura);
            }
        }catch (SQLException ex){
            System.out.println( "SELECT FACTURAS "+ex.getMessage());
        }
        return facturas;
    }
    
    public Factura getFacturaById(int id){
        Factura factura = new Factura();
        Cliente cli = new Cliente();
        try{
            String statement = "SELECT * FROM factura WHERE id = ?";
            PreparedStatement pst = Conexion.createConnection().prepareStatement(statement);
            pst.setInt(1,id);
            ResultSet resultSet = pst.executeQuery();
            while(resultSet.next()){
                factura.setId(resultSet.getInt(1));
                factura.setFecha(resultSet.getDate(2));
                factura.setCliente(cli.getClienteById(resultSet.getInt(3)));
            }
        }catch (SQLException ex){
            System.out.println( "SELECT FACTURA "+ex.getMessage());
        }
        return factura;
    }

    public Factura getLastRecord(){
        Factura factura = new Factura();
        try{
            String statement = "SELECT * FROM factura ORDER BY ID DESC LIMIT 1";
            PreparedStatement pst = Conexion.createConnection().prepareStatement(statement);
            ResultSet resultSet = pst.executeQuery();
            while(resultSet.next()){
                factura.setId(resultSet.getInt(1));
                factura.setFecha(resultSet.getDate(2));
                factura.setCliente(cliente.getClienteById(resultSet.getInt(3)));
            }
        }catch (SQLException ex){
            System.out.println( "SELECT FACTURA "+ex.getMessage());
        }
        return factura;
    }
    
    public void update(int id){}
    public void delete(int id){}

    @Override
    public String toString() {
        return "\nFactura " + id +
                "\n\tFecha de Emision:  " + fecha +
                "\n\tNombre Cliente:    " + cliente.getNombre() +
                "\n\tCedula Cliente:    " + cliente.getCedula() +
                "\n\tDirección Cliente: " + cliente.getDireccion();
    }
}
