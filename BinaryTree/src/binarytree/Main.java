package binarytree;

import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {
       BinaryTree bt = new BinaryTree();
       String menu[]={"Add","PreOrder", "InOrder", "PostOrder", "Size",
           "Height","Search","SearchNode","getFather","Succesor","Antecessor", 
           "Delete" ,"Exit"};
       String option, value;
       
       do{
           option=(String)JOptionPane.showInputDialog(null, "Selected",
                   "Main", 1,null, menu, menu[0]);
           switch(option)
           {
               case "Add":
                   value=JOptionPane.showInputDialog("Enter value");
                   bt.Add(value);
                   JOptionPane.showMessageDialog(null,"created node");
                   break;
               case "PreOrder":
                   JOptionPane.showMessageDialog(null, bt.PreOrder());
                   break;
                case "InOrder":
                   JOptionPane.showMessageDialog(null, bt.InOrder());
                   break;
                case "PostOrder":
                   JOptionPane.showMessageDialog(null, bt.PostOrder());
                   break;
                case "Size":
                    JOptionPane.showMessageDialog(null, "The size is: " + bt.Size());
                    break;
                case "Height":
                    JOptionPane.showMessageDialog(null, "The height is: " + bt.Height());
                    break;
                    case "Search":
                        value =JOptionPane.showInputDialog("Enter value");
                        if(bt.Search(value))
                            JOptionPane.showMessageDialog(null, "Value found ");
                        else
                            JOptionPane.showMessageDialog(null, "Value not found");                    
                    break;
                    
                    case "SearchN":
                        value =JOptionPane.showInputDialog("Enter value");
                        if(bt.SearchN(value)!= null)
                            JOptionPane.showMessageDialog(null, "Value found ");
                        else
                            JOptionPane.showMessageDialog(null, "Value not found");                    
                    break;
                    case "getFather":
                        value =JOptionPane.showInputDialog("Enter value");
                        BinaryNode father = bt.getFather(value);
                        if(father!= null)
                            JOptionPane.showMessageDialog(null, "The father of " 
                                    + value + " is: " + father.getData());
                        else
                            JOptionPane.showMessageDialog(null, "Element not found or "
                                    + "element is root");
                        break;
                    case "Successor":
                        value =JOptionPane.showInputDialog("Enter value");
                        List list=bt.Successor(value);
                        if(list==null)
                            JOptionPane.showMessageDialog(null,"element not found");
                        else
                           JOptionPane.showMessageDialog(null,"the successor of " + value + " are:" +
                                   list.toString());
                        break;
                    case "Delete":                        
                        value =JOptionPane.showInputDialog("Enter value"); 
                        if(bt.Delete(value))
                            JOptionPane.showMessageDialog(null,"Element deleted");
                        else
                            JOptionPane.showMessageDialog(null,"Element not found");
                        break;
                            
                
           }
       }while(!option.equals("Exit"));
    }
    
}
