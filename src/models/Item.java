package models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Item {
    private int id;
    private String nombre;
    private double precio;

    public Item(int id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    public Item() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    public List<Item> getItems(){
        List<Item> items = new ArrayList<Item>();
        try{
            String statement = "SELECT * FROM item";
            PreparedStatement pst = Conexion.createConnection().prepareStatement(statement);
            ResultSet resultSet = pst.executeQuery();
            while(resultSet.next()){
                Item item = new Item(resultSet.getInt(1),resultSet.getString(2),resultSet.getDouble(3));
                items.add(item);
            }
        }catch (SQLException ex){
            System.out.println( "SELECT ITEMS "+ex.getMessage());
        }

        return items;
    }
    public void create(Item item){
        try{
            String statement = "INSERT INTO item(id, nombre, precio) VALUES (?,?,?)";
            PreparedStatement pst = Conexion.createConnection().prepareStatement(statement);
            pst.setInt(1, item.getId());
            pst.setString(2, item.getNombre());
            pst.setDouble(3, item.getPrecio());
            int result = pst.executeUpdate();
            System.out.println((result>0) ? "\nEl Item se almaceno existosamente" : "No se pudo almacenar el item");
        }catch (SQLException ex) {
            System.out.println( "CREATE ITEM "+ex.getMessage());
        }

    }
    
    public Item getItemById(int id){
        Item item = new Item();
        try{
            String statement = "SELECT * FROM item WHERE id = ?";
            PreparedStatement pst = Conexion.createConnection().prepareStatement(statement);
            pst.setInt(1,id);
            ResultSet resultSet = pst.executeQuery();
            while(resultSet.next()){
                item.setId(resultSet.getInt(1));
                item.setNombre(resultSet.getString(2));
                item.setPrecio(resultSet.getDouble(3));
            }
        }catch (SQLException ex){
            System.out.println( "SELECT ITEM "+ex.getMessage());
        }
        return item;
    }

    public Item getLastRecord(){
        Item item = new Item();
        try{
            String statement = "SELECT * FROM item ORDER BY ID DESC LIMIT 1";
            PreparedStatement pst = Conexion.createConnection().prepareStatement(statement);
            ResultSet resultSet = pst.executeQuery();
            while(resultSet.next()){
                item.setId(resultSet.getInt(1));
                item.setNombre(resultSet.getString(2));
                item.setPrecio(resultSet.getDouble(3));
            }
        }catch (SQLException ex){
            System.out.println( "SELECT ITEM "+ex.getMessage());
        }
        return item;
    }

    @Override
    public String toString() {
        return "Item " + id +
                "\n\tNombre: " + nombre +
                "\n\tPrecio: " + precio ;
    }
}
