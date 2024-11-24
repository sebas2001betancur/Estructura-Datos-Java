
package grafo;

import javax.swing.JOptionPane;

public class Grafos {
    public static void main(String[] args) {
        Grafo g = new Grafo();
        String menu[]={"Crear Vertice", "Crear Arista",
            "Mostrar", "Salir"};
        String opcion, nombre;
        
        do{
            opcion=(String)JOptionPane.showInputDialog(null,"Seleccione:",
                    "Menu",1,null, menu, menu[0]);
            switch(opcion)
            {
                case "Crear Vertice":
                    nombre=JOptionPane.showInputDialog("ingrese nombre del vertice");
                    g.AdicionarVertice(nombre);
                    JOptionPane.showMessageDialog(null,"vertice creado");
                    break;
                case "Crear Arista":
                    nombre=JOptionPane.showInputDialog("Ingrese nombre "
                            + "del vertice inicial");
                    String nombre2=JOptionPane.showInputDialog("ingrese nombre"
                            + " del vertice final");
                    if(g.AdicionarArista(nombre,nombre2))
                        JOptionPane.showMessageDialog(null, "Arista creada");
                    else
                        JOptionPane.showMessageDialog(null, "Vertice no encontrado");
                    break;
                case "Mostrar":
                        JOptionPane.showMessageDialog(null, g.toString());
                    break;
                        
                    
            }
        }while(!opcion.equals("Salir"));
    }
}
