import models.Cliente;
import models.Factura;
import models.Item;
import models.LineaFactura;

import javax.sound.sampled.Line;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BOLD = "\u001B[1m";
    public static final String DATE_FORMAT = "yyyy-MM-dd";


    public static void main(String[] args) throws InterruptedException {
        Scanner entrada = new Scanner(System.in);
        boolean continuar = true;
        while (continuar){
            showMenu();
            System.out.print("\nDeseas Realizar otra operacion [S/N]: ");
            String res = entrada.next();
            continuar = res.equals("S") || res.equals("s");
        }
        System.out.println("Programa Finalizado :D ");

    }

    public static void showMenu() throws InterruptedException {
        Scanner entrada = new Scanner(System.in);
        System.out.println("\t ::::::::::::::::::::::::::::::::::::::::");
        System.out.println(ANSI_CYAN+"\t      Sistema de Gestión de Ventas       "+ANSI_RESET);
        System.out.println("\t ::::::::::::::::::::::::::::::::::::::::");
        System.out.println(ANSI_BOLD+"\t   Entidad                          Id"+ANSI_RESET);
        System.out.println("\t ----------------------------------------");
        System.out.println(ANSI_GREEN+"\t   Cliente                          [1]");
        System.out.println("\t   Factura                          [2]");
        System.out.println("\t   Item                             [3]");
        System.out.println("\t   Linea Factura                    [4]"+ANSI_RESET);
        System.out.println("\t ----------------------------------------");
        System.out.println(ANSI_BOLD+ANSI_YELLOW+"\t\t\t Presion [0] para salir\n"+ANSI_RESET);
        System.out.print("Digite el id de la entidad: ");
        int opcion = entrada.nextInt();
        showSubMenu(opcion);

    }

    private static void showSubMenu(int opcion) throws InterruptedException {
        clrscr();
        String nombreEntidad = "";
        boolean isValid = true;
        Scanner entrada = new Scanner(System.in);
        switch (opcion){
            case 0:
                System.out.println("\t-------------------------------------------");
                System.out.println(ANSI_BOLD+ANSI_GREEN+"\t\tPrograma Finalizado con exito :D"+ANSI_RESET);
                System.out.println("\t-------------------------------------------");
                System.exit(0);
            case 1:
                nombreEntidad = "Cliente";
                break;
            case 2:
                nombreEntidad = "Factura";
                break;
            case 3:
                nombreEntidad = "Item";
                break;
            case 4:
                nombreEntidad = "Linea Factura";
                break;
            default:
                System.out.println("\t-------------------------------------------");
                System.out.println(ANSI_BOLD+ANSI_RED+"\n\t Advertencia: "+ANSI_RESET+"El valor ingresado no es valido,\n\t digite un valor valido\n");
                System.out.println("\t-------------------------------------------");
                isValid = false;
                TimeUnit.SECONDS.sleep(1);
                clrscr();
                showMenu();
                break;
        }
        if(isValid){
            System.out.println("\t ::::::::::::::::::::::::::::::::::::::::");
            System.out.println(ANSI_BOLD+ANSI_CYAN+"\t\t        Gestionar "+nombreEntidad+"s       "+ANSI_RESET);
            System.out.println("\t ::::::::::::::::::::::::::::::::::::::::");
            System.out.println(ANSI_BOLD+"\t   Operación                          Id"+ANSI_RESET);
            System.out.println("\t ----------------------------------------");
            System.out.println(ANSI_GREEN+"\t   Crear "+nombreEntidad+"                     [1]");
            System.out.println("\t   Listar "+nombreEntidad+"s                   [2]");
            System.out.println("\t   Editar "+nombreEntidad+"                    [3]");
            System.out.println("\t   Eliminar "+nombreEntidad+"                  [4]"+ANSI_RESET);
            System.out.println("\t ----------------------------------------");
            System.out.println(ANSI_BOLD+ANSI_YELLOW+"\t\t\t Presion [0] para regresar\n"+ANSI_RESET);
            System.out.print("Digite el id de la operación: ");
            int operacion = entrada.nextInt();
            if (operacion==0){
                inicializarOperacion(operacion, "back");
            }else {
                inicializarOperacion(operacion, nombreEntidad);
            }
        }

    }

    private static void inicializarOperacion(int operacion, String entidad) throws InterruptedException {

        switch (entidad){
            case "back":
                showMenu();
                break;
            case "Cliente":
                Cliente cliente = new Cliente();
                ejecutarOperacionCliente(operacion, cliente);
                break;
            case "Factura":
                Factura factura = new Factura();
                ejecutarOperacionFactura(operacion, factura);
                break;
            case "Item":
                Item item = new Item();
                ejecutarOperacionItem(operacion, item);
                break;
            case "Linea Factura":
                LineaFactura lineaFactura = new LineaFactura();
                ejecutarOperacionLineaFactura(operacion, lineaFactura);
                break;
            default:
                System.out.println("\t-------------------------------------------");
                System.out.println(ANSI_BOLD+ANSI_RED+"\n\t Advertencia: "+ANSI_RESET+"El valor ingresado no es valido,\n\t digite un valor valido\n");
                System.out.println("\t-------------------------------------------");
                TimeUnit.SECONDS.sleep(1);
                clrscr();
                break;
        }
    }

    private static void ejecutarOperacionCliente(int operacion, Cliente cliente) throws InterruptedException {
        Scanner entrada = new Scanner(System.in);
        switch (operacion){
            case 1:
                System.out.println("Creando Cliente");
                int id = 0;
                String cedula = "", nombre = "", apellido = "", telefono = "", direccion = "";
                do{
                    System.out.print("\nIngrese el id del cliente: ");
                    id = entrada.nextInt();
                    System.out.println((id > 0) ? ANSI_GREEN+"OK: "+ANSI_RESET+"Dato Registrado" : ANSI_RED+"ERROR: "+ANSI_RESET+"El numero debe ser un numero positivo entero");
                }while (id<=0);
                cliente.setId(id);

                do{
                    System.out.print("\nIngrese la cedula del cliente: ");
                    cedula = entrada.next();
                    System.out.println((cedula.length() == 10) ? ANSI_GREEN+"OK: "+ANSI_RESET+"Dato Registrado" : ANSI_RED+"ERROR: "+ANSI_RESET+"La cedula debe contener almenos 10 digitos");
                }while (cedula.length() != 10);
                cliente.setCedula(cedula);

                System.out.print("\nIngrese el nombre del cliente (Solo nombre): ");
                nombre = entrada.next();
                cliente.setNombre(nombre);

                System.out.print("\nIngrese el apellido del cliente: ");
                apellido = entrada.next();
                cliente.setApellido(apellido);

                do{
                    System.out.print("\nIngrese el teléfono del cliente: ");
                    telefono = entrada.next();
                    System.out.println((telefono.length() == 10) ? ANSI_GREEN+"OK: "+ANSI_RESET+"Dato Registrado" : ANSI_RED+"ERROR: "+ANSI_RESET+"La teléfono debe contener almenos 10 digitos");
                }while (telefono.length() != 10);
                cliente.setTelefono(telefono);

                System.out.print("Ingrese la dirección del cliente: ");
                direccion = entrada.next();
                cliente.setDireccion(direccion);

                System.out.println(cliente.toString());

                cliente.create(cliente);

                break;
            case 2:
                System.out.println("Mostrando Listado de Clientes\n");
                List<Cliente> clientes = cliente.getClientes();
                System.out.println("\tid\t   cedula\t nombre\t\t apellido\t telefono\t direccion\t");
                System.out.println("   ------------------------------------------------------------------");
                for (Cliente cli: clientes){
                    System.out.format("%5d\t %10s\t %-10s\t %-10s\t %-10s\t %-10s \n",cli.getId(),cli.getCedula(),cli.getNombre(),cli.getApellido(),cli.getTelefono(),cli.getDireccion());
                }
                break;
            case 3:
                System.out.println("Actualizando Cliente");
                break;
            case 4:
                System.out.println("Eliminando Cliente");
                break;
            default:
                System.out.println("\t-------------------------------------------");
                System.out.println(ANSI_BOLD+ANSI_RED+"\n\t Advertencia: "+ANSI_RESET+"El valor ingresado no es valido,\n\t digite un valor valido\n");
                System.out.println("\t-------------------------------------------");
                TimeUnit.SECONDS.sleep(1);
                clrscr();
                showSubMenu(1);
                break;
        }
    }

    private static void ejecutarOperacionFactura(int operacion, Factura factura) throws InterruptedException {
        Scanner entrada = new Scanner(System.in);
        String fechaEmision = "";
        boolean isValid = false;
        int idCliente, id;
        Date fechaEmisionFormated = null;
        java.sql.Date fecha = null;
        DateFormat df = new SimpleDateFormat(DATE_FORMAT);
        df.setLenient(false);
        switch (operacion){
            case 1:
                System.out.println("Crear Factura ");
                do{
                    System.out.print("\nIngrese el id de la factura: ");
                    id = entrada.nextInt();
                    System.out.println((id > 0) ? ANSI_GREEN+"OK: "+ANSI_RESET+"Dato Registrado" : ANSI_RED+"ERROR: "+ANSI_RESET+"El numero debe ser un numero positivo entero");
                }while (id<=0);
                factura.setId(id);

                do{
                    System.out.print("\nIngresa la fecha de Emisión de la factura ("+ANSI_BOLD+ANSI_CYAN+DATE_FORMAT+ANSI_RESET+"): ");
                    fechaEmision = entrada.next();
                    try{
                        fechaEmisionFormated = df.parse(fechaEmision);
                        fecha = new java.sql.Date(fechaEmisionFormated.getTime()); 
                        isValid = true;
                        System.out.println(ANSI_GREEN+"OK: "+ANSI_RESET+"Dato Registrado");
                    }catch(ParseException ex){
                        System.out.println(ANSI_RED+"ERROR:"+ANSI_RESET+" La fecha "+ANSI_BOLD+ANSI_CYAN+ fechaEmision+ANSI_RESET+ " esta en un formato invalido vuelve a intentarlo.");
                    }
                }while (!isValid);
                factura.setFecha(fecha);


                Cliente clist = new Cliente();
                List<Cliente> clientes = clist.getClientes();
                System.out.println("--------------------------------");
                System.out.println(ANSI_BOLD+"\tid\t   Cliente"+ANSI_RESET);
                System.out.println("--------------------------------");
                for (Cliente cli: clientes){
                    System.out.format("\t[%d]\t %s %s  \n",cli.getId(),cli.getNombre(),cli.getApellido());
                }
                System.out.println("\t[0]\t Crear Nuevo Cliente \n");
                System.out.print("Digite el id del cliente asignado a la factura: ");
                idCliente = entrada.nextInt();
                if(idCliente == 0){
                    ejecutarOperacionCliente(1, clist);
                    factura.setCliente(clist.getLastRecord());
                }else {
                    factura.setCliente(clist.getClienteById(idCliente));
                }
                factura.create(factura);
                System.out.println(factura.toString());

                break;
            case 2:
                System.out.println("Mostrando Listado de Facturas");
                List<Factura> facturas = factura.getFacturas();
                System.out.println("\tId\t   Fecha\t Cedula\t\t Dirección\t Nombre Cliente\t");
                System.out.println("   ------------------------------------------------------------------");
                for (Factura fac: facturas){
                    System.out.format("%5d\t %10s\t %s\t %-10s\t %s %-10s\t \n",fac.getId(),fac.getFecha(),fac.getCliente().getCedula(),fac.getCliente().getDireccion(),fac.getCliente().getNombre(), fac.getCliente().getApellido());
                }
                break;
            case 3:
                System.out.println("Actualizar Facturas ");
                break;
            case 4:
                System.out.println("Eliminar Facturas");
                break;
            default:
                System.out.println("\t-------------------------------------------");
                System.out.println(ANSI_BOLD+ANSI_RED+"\n\t Advertencia: "+ANSI_RESET+"El valor ingresado no es valido,\n\t digite un valor valido\n");
                System.out.println("\t-------------------------------------------");
                TimeUnit.SECONDS.sleep(1);
                clrscr();
                showSubMenu(2);
                break;
        }
    }


    private static void ejecutarOperacionLineaFactura(int operacion, LineaFactura lineaFactura) throws InterruptedException {
        Scanner entrada = new Scanner(System.in);
        int id, idFactura, idItem, cantidad;
        double total = 0.0;
        LineaFactura lfac = new LineaFactura();
        switch (operacion){
            case 1:
                System.out.println("Crear Linea Factura ");

                do{
                    System.out.print("\nIngrese el id de la linea factura: ");
                    id = entrada.nextInt();
                    System.out.println((id > 0) ? ANSI_GREEN+"OK: "+ANSI_RESET+"Dato Registrado" : ANSI_RED+"ERROR: "+ANSI_RESET+"El numero debe ser un numero positivo entero");
                }while (id<=0);
                lineaFactura.setId(id);

                Factura flist = new Factura();
                List<Factura> facturas = flist.getFacturas();
                System.out.println("--------------------------------");
                System.out.println(ANSI_BOLD+"\tid\t   Cabecera"+ANSI_RESET);
                System.out.println("--------------------------------");
                for (Factura fac: facturas){
                    System.out.format("%5d\t %10s\t %s\t %-10s\t %s %-10s\t \n",fac.getId(),fac.getFecha(),fac.getCliente().getCedula(),fac.getCliente().getDireccion(),fac.getCliente().getNombre(), fac.getCliente().getApellido());
                }
                System.out.println("\t[0]\t Crear Nueva Cabecera \n");
                System.out.print("Digite el id del cliente asignado a la cabecera factura: ");
                idFactura = entrada.nextInt();
                if(idFactura == 0){
                    ejecutarOperacionFactura(1, flist);
                    lineaFactura.setFactura(flist.getLastRecord());
                }else {
                    lineaFactura.setFactura(flist.getFacturaById(idFactura));
                }

                Item ilist = new Item();
                List<Item> items = ilist.getItems();
                System.out.println("--------------------------------");
                System.out.println(ANSI_BOLD+"\tid\t   Items"+ANSI_RESET);
                System.out.println("--------------------------------");
                for (Item ite: items){
                    System.out.format("%5d\t %10s\t %s\t \n",ite.getId(),ite.getNombre(),ite.getPrecio());
                }
                System.out.println("\t[0]\t Crear Nuevo Item \n");
                System.out.print("Digite el id del item asignado al item factura: ");
                idItem = entrada.nextInt();
                if(idItem == 0){
                    ejecutarOperacionItem(1, ilist);
                    lineaFactura.setItem(ilist.getLastRecord());
                }else {
                    lineaFactura.setItem(ilist.getItemById(idItem));
                }

                do{
                    System.out.print("\nIngrese la cantidad del item: ");
                    cantidad = entrada.nextInt();
                    System.out.println((cantidad > 0) ? ANSI_GREEN+"OK: "+ANSI_RESET+"Dato Registrado" : ANSI_RED+"ERROR: "+ANSI_RESET+"El numero debe ser un numero positivo entero");
                }while (cantidad<=0);

                lineaFactura.setCantidad(cantidad);

                lineaFactura.setTotal(calcularTotal(cantidad, lineaFactura.getItem().getPrecio()));


                lineaFactura.create(lineaFactura);
                System.out.println(lineaFactura.toString());

                break;
            case 2:
                System.out.println("Mostrando Listado de Lineas Facturas");
                List<LineaFactura> lfacturas = lfac.getLineasFacturas();
                System.out.println("\tId\t   Fecha\t Cedula\t\t Dirección\t Nombre Cliente\t N. Producto\t Precio\t\t Cantidad\t Total\t ");
                System.out.println("   ---------------------------------------------------------------------------------------------------------");
                for (LineaFactura fac: lfacturas){
                    System.out.format("%5d\t %10s\t %s\t %-10s\t %s %-10s\t %-10s\t %-10s\t %5d \t\t %.2f\t\n",fac.getId(),fac.getFactura().getFecha(),fac.getFactura().getCliente().getCedula(),fac.getFactura().getCliente().getDireccion(),fac.getFactura().getCliente().getNombre(), fac.getFactura().getCliente().getApellido(), fac.getItem().getNombre(), fac.getItem().getPrecio(), fac.getCantidad(), fac.getTotal());
                }

                break;
            case 3:
                System.out.println("Actualizar Linea Factura ");
                break;
            case 4:
                System.out.println("Eliminar Linea Factura");
                break;
            default:
                System.out.println("\t-------------------------------------------");
                System.out.println(ANSI_BOLD+ANSI_RED+"\n\t Advertencia: "+ANSI_RESET+"El valor ingresado no es valido,\n\t digite un valor valido\n");
                System.out.println("\t-------------------------------------------");
                TimeUnit.SECONDS.sleep(1);
                clrscr();
                showSubMenu(4);
                break;
        }
    }
        private static void ejecutarOperacionItem(int operacion, Item item) throws InterruptedException {
            Scanner entrada = new Scanner(System.in);
            switch (operacion){
                case 1:
                    System.out.println("Creando Item");
                    int id = 0;
                    String nombre = "";
                    double precio = 0.0;
                    do{
                        System.out.print("\nIngrese el id del item: ");
                        id = entrada.nextInt();
                        System.out.println((id > 0) ? ANSI_GREEN+"OK: "+ANSI_RESET+"Dato Registrado" : ANSI_RED+"ERROR: "+ANSI_RESET+"El numero debe ser un numero positivo entero");
                    }while (id<=0);
                    item.setId(id);

                    System.out.print("\nIngrese el nombre del item: ");
                    nombre = entrada.next();
                    System.out.println((nombre.length()>0) ? ANSI_GREEN+"OK: "+ANSI_RESET+"Dato Registrado" : ANSI_RED+"ERROR: "+ANSI_RESET+"El numero debe ser un numero positivo entero");
                    item.setNombre(nombre);

                    do{
                        System.out.print("\nIngrese el precio del item: ");
                        precio = entrada.nextDouble();
                        System.out.println((precio > 0.0) ? ANSI_GREEN+"OK: "+ANSI_RESET+"Dato Registrado" : ANSI_RED+"ERROR: "+ANSI_RESET+"El numero debe ser un numero positivo entero");
                    }while (precio<=0);
                    item.setPrecio(precio);
                    item.create(item);
                    System.out.println(item.toString());


                    break;
                case 2:
                    System.out.println("Mostrando Listado de Items");
                    List<Item> items = item.getItems();
                    System.out.println("\tid\t   nombre\t\t precio\t");
                    System.out.println(" ------------------------------");
                    for (Item ite: items){
                        System.out.format("%5d\t   %-10s\t\t %.2f\t \n",ite.getId(),ite.getNombre(), ite.getPrecio());
                    }
                    break;
                case 3:
                    System.out.println("Actualizar Item");
                    break;
                case 4:
                    System.out.println("Eliminando Item");
                    break;
                default:
                    System.out.println("\t-------------------------------------------");
                    System.out.println(ANSI_BOLD+ANSI_RED+"\n\t Advertencia: "+ANSI_RESET+"El valor ingresado no es valido,\n\t digite un valor valido\n");
                    System.out.println("\t-------------------------------------------");
                    TimeUnit.SECONDS.sleep(1);
                    clrscr();
                    showSubMenu(3);
                    break;
            }
        }

        public static double calcularTotal(int cantidad, double precio){
            return cantidad*precio;
        }

    public static void clrscr(){
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {}
    }
}