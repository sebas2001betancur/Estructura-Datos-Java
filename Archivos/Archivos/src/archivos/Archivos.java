/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivos;

import javax.swing.JOptionPane;

public class Archivos {

    public static void main(String[] args) {
        Archivo a = new Archivo();
        BinaryTree p = a.LeerArbol("profesores.txt");
        String nombre, titulo, menu[]={"Nuevo", "Mostrar", "Salir"};
        String opcion;
        int tiempo;
        do{
         opcion=(String)JOptionPane.showInputDialog(null,"Seleccione", 
                 "Menu", 1, null, menu, menu[0]);
         switch(opcion)
         {
             case "Nuevo":
                 nombre=JOptionPane.showInputDialog("Digite nombre");
                 titulo=JOptionPane.showInputDialog("Digite titulo");
                 tiempo=Integer.parseInt(JOptionPane.showInputDialog("Digite tiempo"));
                 p.Add(new Profesor(nombre, titulo, tiempo));
                 JOptionPane.showMessageDialog(null,"Profesor creado");
                 break;
             case "Mostrar":
                 JOptionPane.showMessageDialog(null,p.PreOrder());
                 break;
             case "Salir":
                 a.EscribirArbol(p, "profesores.txt");
                 JOptionPane.showMessageDialog(null,"Ejecucion terminada");
                 break;
         }
        }while(!opcion.equals("Salir"));
    }
    
    public static String toString(Stack pila)
    {
        Stack aux = new Stack();
        String text="";
        while(!pila.isEmpty())
            {
                Profesor p=(Profesor)pila.Pop();
                text = text + p.toString()+"\n";
                aux.Push(p);
            }
            while(!aux.isEmpty())
                pila.Push(aux.Pop());
            
            return text;
    }
    
}
