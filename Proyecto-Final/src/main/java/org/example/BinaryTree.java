
package org.example;

import javax.swing.*;
import java.io.Serializable;

public class BinaryTree implements Serializable { private static final long serialVersionUID = 1L;
    private BinaryNode root;
    Object o;

    public BinaryTree() {
    }

    public BinaryNode getRoot() {
        return root;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void add(SolicitudCredito data) {
        root = addRecursive(root, data);
    }

    private BinaryNode addRecursive(BinaryNode node, SolicitudCredito data) {
        if (node == null) {
            return new BinaryNode(data);
        }
        SolicitudCredito nodeData = (SolicitudCredito) node.getData();
        if (data.getValor() < nodeData.getValor()) {
            node.setLeft(addRecursive(node.getLeft(), data));
        } else if (data.getValor() > nodeData.getValor()) {
            node.setRight(addRecursive(node.getRight(), data));
        } else {
            // Los valores duplicados son ignorados
            return node;
        }
        return node;
    }

    public void countNonRejectedLoansByType(Lista tipos, Lista counts) {
        if (root == null) {
            return;
        }
        countNonRejectedLoansByTypeRecursive(root, tipos, counts);
    }

    private void countNonRejectedLoansByTypeRecursive(BinaryNode node, Lista tipos, Lista counts) {
        if (node == null) {
            return;
        }
        if (node.getData() instanceof SolicitudCredito) {
            SolicitudCredito solicitud = (SolicitudCredito) node.getData();
            if (!"rechazado".equals(solicitud.getEstado())) {
                String tipoSolicitud = solicitud.getTipo().toLowerCase();
                Node tipoActual = tipos.getFirst();
                Node countActual = counts.getFirst();
                while (tipoActual != null && countActual != null) {
                    String tipoLista = tipoActual.getData().toString().toLowerCase();
                    if (tipoLista.equals(tipoSolicitud)) {
                        int currentCount = (int) countActual.getData();
                        countActual.setData(currentCount + 1);
                        break;
                    }
                    tipoActual = tipoActual.getLink();
                    countActual = countActual.getLink();
                }
            }
        }
    }


    // Metodo auxiliar para debug (si no lo tienes ya)
    public void inOrder() {
        inOrderRecursive(root);
    }

    private void inOrderRecursive(BinaryNode node) {
        if (node != null) {
            inOrderRecursive(node.getLeft());
            if (node.getData() instanceof SolicitudCredito) {
                SolicitudCredito solicitud = (SolicitudCredito) node.getData();
                System.out.println("Tipo: " + solicitud.getTipo() + ", Estado: " + solicitud.getEstado());
            }
            inOrderRecursive(node.getRight());
        }
    }


    public void revaluarSolicitudes(Lista listaClientes) {
        // Llama al metodo recursivo para revaluar solicitudes
        revaluarRec(root, listaClientes);
    }

    private void revaluarRec(BinaryNode node, Lista listaClientes) {
        if (node == null) {
            return; // Caso base: nodo nulo
        }

        // Revaluar nodo izquierdo
        revaluarRec(node.getLeft(), listaClientes);

        // Revaluar nodo actual
        SolicitudCredito solicitud = (SolicitudCredito) node.getData();
        Cliente cliente = solicitud.getTitular();
        double cuotaMensual = solicitud.calcularCuotaMensual();
        double totalCuotasExistentes = listaClientes.obtenerTotalCuotasCliente(cliente);
        double limiteCuotas = cliente.getSalarioMensual() * 0.5;

        // Verifica si la nueva cuota no excede el 50% del salario mensual del cliente
        if ((totalCuotasExistentes + cuotaMensual) <= limiteCuotas) {
            listaClientes.agregarSolicitudAprobada(solicitud);
            Delete(solicitud);
        }

        // Revaluar nodo derecho
        revaluarRec(node.getRight(), listaClientes);
    }


    private String PreOrder(BinaryNode aux)
    {
        if(aux!=null)
            return aux.getData()+ "\n" +
                    PreOrder(aux.getLeft()) +
                    PreOrder(aux.getRight());
        return "";
    }
    
    public String PreOrder()
    {
        return PreOrder(root);
    }
     private String InOrder(BinaryNode aux)
    {
        if(aux!=null)
            return  InOrder(aux.getLeft()) +
                    aux.getData()+ "\n" +
                    InOrder(aux.getRight());
        return "";
    }
    
    public String InOrder()
    {
        return InOrder(root);
    }
    
     private String PostOrder(BinaryNode aux)
    {
        if(aux!=null)
            return  PostOrder(aux.getLeft())  +
                    PostOrder(aux.getRight())+
                    aux.getData()+ "\n";
        return "";
    }
    
    public String PostOrder()
    {
        return PostOrder(root);
    }
    
    public int Size()
    {
        return Size(root);
    }
    
    private int Size(BinaryNode aux)
    {
        if(aux!=null)
            return 1 + Size(aux.getLeft()) + Size(aux.getRight());
        // return Size(aux.getLEft()) + 1 + Size(aux.getRight())  --InOrder
        // return Size(aux.getLeft()) + Size(aux.getRight()) + 1  -- postorder
        
        return 0;
    }
    
    public int Height()
    {
        return Height(getRoot());
    }
     private int Height(BinaryNode aux)
     {
         if(aux!=null)
             return 1 + Math.max(Height(aux.getLeft()), Height(aux.getRight()));
         
         return 0;
     }
     
     public boolean Search(String value)
     {
         return Search(value, getRoot());
     }
     
     private boolean Search(String value, BinaryNode aux)
     {
         if(aux!=null)
         {
             if(((String)aux.getData()).equals(value))
                 return true;
             else
                 return Search(value,aux.getLeft()) || Search(value,aux.getRight());
         }
         return false;
     }
     
     public BinaryNode SearchN(String value)
     {
         return SearchN(value, getRoot());
     }
     
     private BinaryNode SearchN(String value, BinaryNode aux)
     {
         BinaryNode resul=null;
         if(aux!=null)
         {
             if(((String)aux.getData()).equals(value))
                 return aux;
             else
             {
                 resul= SearchN(value,aux.getLeft());
                 if(resul== null)
                     resul = SearchN(value,aux.getRight());
             }             
         }
         return resul;
     }
    
     public BinaryNode getFather(String value)
     {
         if(!isEmpty())
         {
             if(((String)getRoot().getData()).equals(value))
                 return null;
             else
                 return getFather(value, getRoot());
         }
         return null;  
     }
     private BinaryNode getFather(String value, BinaryNode aux)
     {
         BinaryNode father=null;
         if(aux!=null)
         {
             if(aux.getLeft()!=null && ((String)aux.getLeft().getData()).equals(value)
                 || aux.getRight() !=null && ((String)aux.getRight().getData()).equals(value))
                 return aux;
         
            else
            {
                father = getFather(value, aux.getLeft());
                if(father == null)
                    father=getFather(value, aux.getRight());
            }
         }
         return father;
     }
     
     public Lista Successor(String value)
     {
         BinaryNode s = SearchN(value, root);
         if(s!=null)
             return Successor(value, s, new Lista());
         else 
             return null;
     }
     
     private Lista Successor(String value, BinaryNode aux, Lista list)
     {
        if(aux!=null)
        {
            if(!((String)aux.getData()).equals(value))
            {
                list.AddLast(aux.getData());
            }
                Successor(value, aux.getLeft(), list);
                Successor(value, aux.getRight(), list);
           
        }
        return list;
     }


    // Metodo público para eliminar una solicitud del árbol
    public boolean Delete(SolicitudCredito solicitud) {
        root = DeleteRec(root, solicitud);
        // Devuelve true si la raíz no es nula después de eliminar
        return root != null;
    }

    // Metodo recursivo para eliminar un nodo en el árbol
    private BinaryNode DeleteRec(BinaryNode node, SolicitudCredito solicitud) {
        // Caso base: el nodo es nulo, no se encuentra la solicitud
        if (node == null) {
            return null;
        }

        // Si el nodo actual contiene la solicitud que queremos eliminar
        if (solicitud.equals(node.getData())) {
            // Caso 1: El nodo no tiene hijos (es un nodo hoja)
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                // Caso 2: El nodo tiene un solo hijo (izquierdo)
                return node.getLeft();
            }

            // Caso 3: El nodo tiene dos hijos
            // Encontrar el sucesor (el menor en el subárbol derecho)
            node.setData(MinValue(node.getRight()));
            // Eliminar el sucesor del subárbol derecho
            node.setRight(DeleteRec(node.getRight(), (SolicitudCredito) node.getData()));
        }
        // Si la solicitud a eliminar tiene un valor menor, buscar en el subárbol izquierdo
        else if (solicitud.getValor() < ((SolicitudCredito) node.getData()).getValor()) {
            node.setLeft(DeleteRec(node.getLeft(), solicitud));
        } else {
            // Si la solicitud tiene un valor mayor, buscar en el subárbol derecho
            node.setRight(DeleteRec(node.getRight(), solicitud));
        }

        return node;
    }

    // Metodo para encontrar el nodo con el menor valor en un subárbol
    private SolicitudCredito MinValue(BinaryNode node) {
        SolicitudCredito minValue = (SolicitudCredito) node.getData();
        while (node.getLeft() != null) {
            minValue = (SolicitudCredito) node.getLeft().getData();
            node = node.getLeft();
        }
        return minValue;
    }


    private void DeleteZero(BinaryNode delete)
     {
         BinaryNode father = getFather((String)delete.getData());
         if(father == null)
             root=null;
         else
         {
             if(father.getLeft()==delete)
                 father.setLeft(null);
             else
                 father.setRight(null);
         }
     }
     
     private void DeleteOne(BinaryNode delete)
     {
          BinaryNode father = getFather((String)delete.getData());
         if(father == null)
         {
             if(delete.getLeft()!=null)
                 root=delete.getLeft();
             else
                 root = delete.getRight();
         }
         else
         {
             if(father.getLeft()==delete)
             {
                 if(delete.getLeft()!=null)
                     father.setLeft(delete.getLeft());
                 else
                     father.setLeft(delete.getRight());
             }
             else
             {
                 if(delete.getLeft()!=null)
                     father.setRight(delete.getLeft());
                 else
                     father.setRight(delete.getRight());
             }
         }
     }
     
     private void DeleteTwo(BinaryNode delete)
     {
         SolicitudCredito minSolicitud = (SolicitudCredito) (MostLeft(delete.getRight()).getData());
             Delete(minSolicitud);
     }
     
     private BinaryNode MostLeft(BinaryNode aux)
     {
         if(aux.getLeft()!=null)
             return MostLeft(aux.getLeft());
         return aux;
     }
     

     
//     public int Amount(BinaryNode aux)
//     {
//         if(aux!=null)
//         {
//             if(((File)aux.getData()).getSize()>=20)
//                 return 1 + Amount(aux.getLeft())+ Amount(aux.getRight());
//             else
//                 return Amount(aux.getLeft())+ Amount(aux.getRight());
//         }
//         return 0;
//     }
     
//     public List Files(String name)
//     {
//         return Files(name, root, new List());
//     }     
//     private List Files(String name, BinaryNode aux, List list)
//     {
//         if(aux!=null)
//         {
//             if(((File)aux.getData()).getName().contains(name))
//                 list.AddLast(aux.getData());
//             
//             Files(name,aux.getLeft(),list);
//             Files(name,aux.getRight(), list);
//         }
//         return list;
//     }
     
//     public BinaryNode SearchN(String name, String type)
//     {
//         return SearchN(name, type, root);
//     }
//     
//     private BinaryNode SearchN(String name, String type, BinaryNode aux)
//     {
//         BinaryNode resul = null;
//         if(aux!=null)
//         {
//             if(((File)aux.getData()).getName().equals(name)  && 
//                     ((File)aux.getData()).getType().equals(type))
//                 return aux;
//             else
//             {
//                 resul = SearchN(name, type, aux.getLeft());
//                 if(resul == null)
//                     resul = SearchN(name, type, aux.getRight());
//             }             
//         }
//         return resul;
//     }
//     
//     public String Path(String name, String type)
//     {
//         BinaryNode search = SearchN(name,type);
//         if(search!=null)
//           return Path(search);
//         
//         return "";         
//     }     
//     private String Path(BinaryNode aux)
//     {
//         if(aux!=null)
//             return Path(getFather(aux, root)) + "\\" +
//                     ((File)aux.getData()).getName();
//         
//         return "";
//     }
//     
//     private BinaryNode getFather(BinaryNode value, BinaryNode aux)
//     {
//         BinaryNode father=null;
//         if(aux!=null)
//         {
//             if(aux.getLeft()!=null && aux.getLeft()==value
//                 || aux.getRight() !=null && aux.getRight()==value)
//                 return aux;
//         
//            else
//            {
//                father = getFather(value, aux.getLeft());
//                if(father == null)
//                    father=getFather(value, aux.getRight());
//            }
//         }
//         return father;
//     }
}
