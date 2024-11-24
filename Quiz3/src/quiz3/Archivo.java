package quiz3;


import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Archivo {


        private File f;
        private FileReader fr;
        private FileWriter fw;
        private BufferedReader br;
        private BufferedWriter bw;

        public BinaryTree leerArchivo(String ruta) {
            BinaryTree bt = new BinaryTree();
            f = new File(ruta);
            String registro, campos[];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            try {
                fr = new FileReader(f);
                br = new BufferedReader(fr);
                while ((registro = br.readLine()) != null) {
                    campos = registro.split("\t");
                    try {
                        String id = campos[0];
                        String nombre = campos[1];
                        String tipoContrato = campos[2];
                        LocalDate fechaInicio = LocalDate.parse(campos[3], formatter);
                        LocalDate fechaFin = LocalDate.parse(campos[4], formatter);
                        int salario = Integer.parseInt(campos[5]);
                        String cargo = campos[6];
                        Empleado empleado = new Empleado(id, nombre, tipoContrato, fechaInicio, fechaFin, salario, cargo);
                        bt.Add(empleado);
                    } catch (DateTimeParseException e) {
                        System.err.println("Formato de fecha inválido en la línea: " + registro);
                    } catch (Exception e) {
                        System.err.println("Error al procesar la línea: " + registro);
                    }
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bt;
        }

        public void escribirArbol(BinaryTree arbol, String ruta) {
            f = new File(ruta);
            try {
                fw = new FileWriter(f);
                bw = new BufferedWriter(fw);
                escribirArbol(arbol.getRoot(), bw);
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void escribirArbol(BinaryNode aux, BufferedWriter bw) {
            try {
                if (aux != null) {
                    Empleado e = (Empleado) aux.getData();
                    bw.write(e.getId() + "\t" + e.getNombre() + "\t" + e.getTipoContrato() + "\t" + e.getFechaInicio() + "\t" + e.getFechaFinal() + "\t" + e.getSalario() + "\t" + e.getCargo() + "\n");
                    escribirArbol(aux.getLeft(), bw);
                    escribirArbol(aux.getRight(), bw);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public List leerLista(String ruta) {
            List lista = new List();
            f = new File(ruta);
            String registro, campos[];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            try {
                fr = new FileReader(f);
                br = new BufferedReader(fr);
                while ((registro = br.readLine()) != null) {
                    campos = registro.split("\t");
                    try {
                        String id = campos[0];
                        String nombre = campos[1];
                        String tipoContrato = campos[2];
                        LocalDate fechaInicio = LocalDate.parse(campos[3], formatter);
                        LocalDate fechaFin = LocalDate.parse(campos[4], formatter);
                        int salario = Integer.parseInt(campos[5]);
                        String cargo = campos[6];
                        Empleado empleado = new Empleado(id, nombre, tipoContrato, fechaInicio, fechaFin, salario, cargo);
                        lista.AddLast(empleado);
                    } catch (DateTimeParseException e) {
                        System.err.println("Formato de fecha inválido en la línea: " + registro);
                    } catch (Exception e) {
                        System.err.println("Error al procesar la línea: " + registro);
                    }
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return lista;
        }

        public void escribirLista(List lista, String ruta) {
            f = new File(ruta);
            try {
                fw = new FileWriter(f);
                bw = new BufferedWriter(fw);
                Node aux = lista.getFirst();
                while (aux != null) {
                    Empleado e = (Empleado) aux.getData();
                    bw.write(e.getId() + "\t" + e.getNombre() + "\t" + e.getTipoContrato() + "\t" + e.getFechaInicio() + "\t" + e.getFechaFinal() + "\t" + e.getSalario() + "\t" + e.getCargo() + "\n");
                    aux = aux.getLink();
                }
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    public static Empleado buscarEmpleado(String id, BinaryTree tree) {
        // Implementar búsqueda en el árbol binario
        return null;
    }
}
