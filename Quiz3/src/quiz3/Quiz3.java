
package quiz3;


import javax.swing.JOptionPane;




import javax.swing.JOptionPane;

public class Quiz3 {

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        Archivo archivo = new Archivo();
        String[] menu = {"Leer y almacenar", "PreOrdenInverso", "Cargo Auxiliar", "Lista", "Buscar Estudiante", "Salir"};
        String opcion;

        do {
            opcion = (String) JOptionPane.showInputDialog(null, "Seleccione:", "Menú", JOptionPane.QUESTION_MESSAGE, null, menu, menu[0]);

            switch (opcion) {
                case "Leer y almacenar":
                    String ruta = "empleados.txt";
                    tree = archivo.leerArchivo(ruta);
                    JOptionPane.showMessageDialog(null, "Datos leídos y almacenados.");
                    break;
                case "PreOrdenInverso":
                    JOptionPane.showMessageDialog(null, tree.preOrderInverse());
                    break;
                case "Cargo Auxiliar":
                    BinaryTree auxiliarTree = tree.filterByCargo("auxiliar");
                    JOptionPane.showMessageDialog(null, auxiliarTree.toString());
                    break;

                case "Lista":
                    int salario = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el salario mínimo:"));
                    List empleados = tree.listBySalary(salario);
                    JOptionPane.showMessageDialog(null, empleados.toString());
                    break;
                case "Buscar Estudiante":
                    String id = JOptionPane.showInputDialog("Ingrese el ID del estudiante:");
                    Empleado empleado = Archivo.buscarEmpleado(id, tree);
                    if (empleado != null) {
                        JOptionPane.showMessageDialog(null, empleado.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "Estudiante no encontrado.");
                    }
                    break;
                case "Salir":
                    JOptionPane.showMessageDialog(null, "Saliendo del programa.");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
            }
        } while (!"Salir".equals(opcion));
    }


}
