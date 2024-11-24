
package bank;

import javax.swing.JOptionPane;

public class Bank {


    public static void main(String[] args) {
        List bank = new List();
        String menu[]={"Create", "Deposit","WithDraw","Delete","Total","toString", "Exit"};
        String option, number, name, key;
        double amount;
        int count=1000;
        
        do{
            option=(String)JOptionPane.showInputDialog(null, "Select",
                    "Main menu",1,null, menu,menu[0]);
            switch(option)
            {
                case "Create":
                    name=JOptionPane.showInputDialog("Enter name");
                    key = JOptionPane.showInputDialog("Enter key");
                    number=String.valueOf(count);//""+count
                    SavingsAccount sa= new SavingsAccount(number, name, key);
                    bank.Add(sa);
                    JOptionPane.showMessageDialog(null, "SavingsAccount created");
                    count++;
                    JOptionPane.showMessageDialog(null,bank.toString());
                    break;
                case "Deposit":
                    do{
                        amount=Double.parseDouble(JOptionPane.showInputDialog("Enter amount"));
                    }while(amount<=0);
                    String num =JOptionPane.showInputDialog("Enter number");
                     sa = bank.Search(num);
                     if(sa!=null)
                     {
                         sa.Deposit(amount);
                         JOptionPane.showMessageDialog(null,"deposit approved " + sa.getValue());
                     }
                     else
                         JOptionPane.showMessageDialog(null,"savingsaccount not found");
                     break;
                     
                case "Delete":
                    num =JOptionPane.showInputDialog("Enter number");
                    if(bank.Delete(num))
                        JOptionPane.showMessageDialog(null,"deleted savingsaccount");
                    else
                        JOptionPane.showMessageDialog(null,"Savingsaccount not found");
                    break;
                    
                case "toString":
                    JOptionPane.showMessageDialog(null,bank.toString());
                    break;
                
                
                case "Total":
                    JOptionPane.showMessageDialog(null,bank.Total());
                    break;
                         
                    
            }
        }while(!option.equals("Exit"));

    }

}
