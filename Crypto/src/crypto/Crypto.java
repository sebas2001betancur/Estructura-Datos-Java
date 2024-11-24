
package crypto;

import javax.swing.JOptionPane;

public class Crypto {

    public static void main(String[] args) {
       Stack p = new Stack();
       Queue q = new Queue();
       String menu[]={"Numbers", "Words", "Message","Exit"};
       String option, message;
       String word;
       String number;
       int num, pos;
       
       do{
           option=(String)JOptionPane.showInputDialog(null, "Selected","Menu",
                   1, null, menu, menu[0]);
           switch(option)
           {
               case "Words":
                   word = JOptionPane.showInputDialog("Enter words separated by spaces");
                   String w[]=word.split(" ");
                   for (int i = 0; i < w.length; i++) {
                       p.Push(w[i]);
                   }
                   JOptionPane.showInputDialog("created stack");
                   break;
                   case "Numbers":
                    number= JOptionPane.showInputDialog("Enter numbers separated by spaces");
                    String n[]=number.split(" ");
                    for (int i = 0; i < n.length; i++) {
                        q.Enqueue(Integer.parseInt(n[i]));
                    }
                    JOptionPane.showInputDialog("created queue");
                    break;
                   case "Message":
                       Queue auxq=new Queue();
                       Stack auxs = new Stack();
                       message="";
                       while(!q.isEmpty())
                       {
                           num = (int)q.Dequeue();
                           //devolvemos la pila para trabajar con las posiciones correctas
                           while(!p.isEmpty())
                               auxs.Push(p.Pop());
                           //iniciamos la posicion y guardamos en la pila original
                           pos=1;
                           while(!auxs.isEmpty())
                           {
                               word = (String)auxs.Pop();
                               if(pos==num)
                                   message = message + word + " ";
                               
                               pos++;
                               p.Push(word);
                           }
                           auxq.Enqueue(num);
                       }
                       while(!auxq.isEmpty())
                           q.Enqueue(auxq.Dequeue());
                       JOptionPane.showMessageDialog(null, message);
                       break;
                       
           }
       }while(!option.equals("Exit"));
    }
    
}
