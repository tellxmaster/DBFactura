package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private int id;
    private String cedula;
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;

    public Cliente(int id, String cedula, String nombre, String apellido, String telefono, String direccion) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente() {
    }

    public int getId() {
        return id;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Cliente> getClientes(){
        List<Cliente> clientes = new ArrayList<Cliente>();
        try{
            String statement = "SELECT * FROM cliente";
            PreparedStatement pst = Conexion.createConnection().prepareStatement(statement);
            ResultSet resultSet = pst.executeQuery();
            while(resultSet.next()){
                Cliente cliente = new Cliente(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6));
                clientes.add(cliente);
            }
        }catch (SQLException ex){
            System.out.println( "SELECT CLIENTES "+ex.getMessage());
        }

        return clientes;
    }
    public void create(Cliente cliente){
        try{
            String statement = "INSERT INTO cliente(id, cedula, nombre, apellido, telefono, direccion) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = Conexion.createConnection().prepareStatement(statement);
            pst.setInt(1, cliente.getId());
            pst.setString(2, cliente.getCedula());
            pst.setString(3, cliente.getNombre());
            pst.setString(4, cliente.getApellido());
            pst.setString(5, cliente.getTelefono());
            pst.setString(6, cliente.getDireccion());
            int result = pst.executeUpdate();
            System.out.println((result>0) ? "\nEl Cliente se almaceno existosamente" : "No se pudo almacenar el titulo");
        }catch (SQLException ex) {
            System.out.println( "CREATE CLIENTE "+ex.getMessage());
        }

    }

    public Cliente getClienteById(int id){
        Cliente cliente = new Cliente();
        try{
            String statement = "SELECT * FROM cliente WHERE id = ?";
            PreparedStatement pst = Conexion.createConnection().prepareStatement(statement);
            pst.setInt(1,id);
            ResultSet resultSet = pst.executeQuery();
            while(resultSet.next()){
                cliente.setId(resultSet.getInt(1));
                cliente.setCedula(resultSet.getString(2));
                cliente.setNombre(resultSet.getString(3));
                cliente.setApellido(resultSet.getString(4));
                cliente.setTelefono(resultSet.getString(5));
                cliente.setDireccion(resultSet.getString(6));
            }
        }catch (SQLException ex){
            System.out.println( "SELECT CLIENTES "+ex.getMessage());
        }
        return cliente;
    }

    public Cliente getLastRecord(){
        Cliente cliente = new Cliente();
        try{
            String statement = "SELECT * FROM cliente ORDER BY ID DESC LIMIT 1";
            PreparedStatement pst = Conexion.createConnection().prepareStatement(statement);
            ResultSet resultSet = pst.executeQuery();
            while(resultSet.next()){
                cliente.setId(resultSet.getInt(1));
                cliente.setCedula(resultSet.getString(2));
                cliente.setNombre(resultSet.getString(3));
                cliente.setApellido(resultSet.getString(4));
                cliente.setTelefono(resultSet.getString(5));
                cliente.setDireccion(resultSet.getString(6));
            }
        }catch (SQLException ex){
            System.out.println( "SELECT CLIENTES "+ex.getMessage());
        }
        return cliente;
    }
    public void update(int id){}
    public void delete(int id){}

    @Override
    public String toString() {
        return "Cliente " + id +
                "\n\t Cédula:    " + cedula +
                "\n\t Nombre:    " + nombre +" "+ apellido +
                "\n\t Teléfono:  " + telefono +
                "\n\t Direccion: " + direccion;
    }
}
