import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Stack p = new Stack();
        Queue q = new Queue();
        String menu[]={"Registrar_barco", "Desembarque_contenedores", "Despachar_contenedores", "Mostrar_información_cola","Estadistica","Exit"};
        String option, message;
        int num=0, pos;
        String codigo, nombre, capitan, paisO;
        int cantContenedores;

        String descrip, tipoProduct;
        int cantidadProduct;
        double valorEst;
        String paisProce;
        Date fechaCadu;
        double tempMin;
        double tempMax;

        do{
            option=(String) JOptionPane.showInputDialog(null, "Selected","Menu",
                    1, null, menu, menu[0]);
            switch(option)
            {
                case "Registrar_barco":
                    codigo= JOptionPane.showInputDialog("ingrese el codigo del barco");
                    nombre = JOptionPane.showInputDialog("ingrese el nombre del barco");
                    capitan = JOptionPane.showInputDialog("ingrese el capitan del barco");
                    paisO = JOptionPane.showInputDialog("ingrese el pais de origen del barco");
                    cantContenedores = Integer.parseInt(JOptionPane.showInputDialog("ingrese la cantidad de contenedores que lleva el barco"));

                    Barco barco = new Barco(codigo, nombre, capitan, paisO, cantContenedores);
                    q.Enqueue(barco);
                    JOptionPane.showMessageDialog(null, q.toString());
                    num++;
                    break;
                case "Desembarque_contenedores":
                    try {
                        // Recibir los datos del barco y el contenedor
                        codigo = JOptionPane.showInputDialog("Ingrese el código del barco");
                        descrip = JOptionPane.showInputDialog("Ingrese la descripción del barco");
                        tipoProduct = JOptionPane.showInputDialog("Ingrese el tipo de producto");
                        cantidadProduct = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de productos"));
                        valorEst = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el valor estimado de la carga"));
                        paisProce = JOptionPane.showInputDialog("Ingrese el país de procedencia");

                        String fechaCaduInput = JOptionPane.showInputDialog("Ingrese la fecha de caducidad (dd/MM/yyyy)");
                        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                        fechaCadu = formato.parse(fechaCaduInput);
                        tempMin = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la temperatura mínima"));
                        tempMax = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la temperatura máxima"));


                        Contenedor contenedor = new Contenedor(codigo, descrip, tipoProduct, cantidadProduct, valorEst, paisProce, fechaCadu, tempMin, tempMax);
                        Puerto pu = new Puerto();

                        JOptionPane.showMessageDialog(null, p.DesembarcarContenedores(contenedor, pu));
                        num++;
                    } catch (NumberFormatException | ParseException e) {
                        JOptionPane.showMessageDialog(null, "Error en la entrada de datos: " + e.getMessage());
                    }
                    break;
                case "Despachar_contenedores":
                    try {
                        String codigoContenedor = JOptionPane.showInputDialog("Ingrese el código del contenedor a despachar");
                        p.despacharContenedor(codigoContenedor);
                        JOptionPane.showMessageDialog(null, "Contenedor despachado exitosamente.");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error al despachar el contenedor: " + e.getMessage());
                    }
                    break;
                case "Mostrar_información_cola":

                    break;
                case "Estadistica":

                    break;
            }
        }while(!option.equals("Exit"));
    }

}