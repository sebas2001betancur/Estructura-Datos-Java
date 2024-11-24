
package doublelist;

import java.time.LocalDate;
import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {
       int num=100, code;
       String name, subject, id, theme, option, menu[]={"Add", "Delete", "Student", "Update", "Amount", "Exit"};
       int date;
       DoubleList consulting = new DoubleList();
       
       do{
           option=(String)JOptionPane.showInputDialog(null, "Select", "Main", 1, null, menu, menu[0]);
           switch(option)
           {
               case "Add":
                   id=JOptionPane.showInputDialog("enter id");
                   name= JOptionPane.showInputDialog("enter name");
                   subject = JOptionPane.showInputDialog("enter subject");
                   theme = JOptionPane.showInputDialog("enter theme");
                   String dat = (JOptionPane.showInputDialog("enter date (yy-mm-dd)"));
                   LocalDate datos = LocalDate.parse(dat);
                   Advice ad = new Advice(num, name, id, theme, subject, datos);
                   consulting.Add(ad);
                   num++;
                   JOptionPane.showMessageDialog(null, consulting.toString());                   
                   break;
               case "Delete":
                   theme=JOptionPane.showInputDialog("enter theme");
                   consulting.Delete(theme);
                   JOptionPane.showMessageDialog(null, consulting.toString()); 
                   break;
               case "Student":
                   name =JOptionPane.showInputDialog("enter name");
                   JOptionPane.showMessageDialog(null, consulting.List(name).toString());                   
                   break;
               case "Update":
                   code =Integer.parseInt(JOptionPane.showInputDialog("enter code"));
                   ad=consulting.Search(code);
                   if(ad!=null)
                   {
                       subject = JOptionPane.showInputDialog("Enter subject");
                       ad.setSubject(subject);
                       JOptionPane.showMessageDialog(null, "updated advice");  
                   }
                   else
                        JOptionPane.showMessageDialog(null, "Advice not found"); 
                       
                    break;
               case "Amount":
                   subject =JOptionPane.showInputDialog("enter subject");
                   JOptionPane.showMessageDialog(null, consulting.Amount(subject)); 
                   break;
               case "Exit":
                   break;
           }
       }while(!option.equals("Exit"));
       
    }
    
}
