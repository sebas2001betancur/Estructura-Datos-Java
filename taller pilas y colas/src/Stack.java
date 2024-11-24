import javax.swing.*;
import java.util.List;

public class Stack {

    private Queue barcosEnEspera;
    private Node top;

    public Stack() {
        barcosEnEspera = new Queue();
    }

    public boolean isEmpty() {
        return top == null;
    }


    public void Push(Object data) {
        if (isEmpty())
            top = new Node(data);
        else {
            Node n = new Node(data);
            n.setLink(top);
            top = n;
        }
    }

    public Object DesembarcarContenedores(Contenedor contenedor, Puerto puerto) {
        if (barcosEnEspera.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay barcos en espera.");
        } else {
            Barco barco = (Barco) barcosEnEspera.Dequeue();
            JOptionPane.showMessageDialog(null, "Desembarcando contenedores del barco: " + barco);


            for (int i = 0; i < barco.getCantContenedores(); i++) {
                if (!isEmpty()) {
                    contenedor = (Contenedor) Pop();
                } else {
                    JOptionPane.showMessageDialog(null, "No hay contenedores para desembarcar.");
                    break;
                }

                // Clasificar el contenedor en función del tipo de producto
                switch (contenedor.getTipoProduct()) {
                    case "Sin Alimentos":
                        puerto.getSinAlimentos().Push(contenedor);
                        break;
                    case "Alimentos No Perecederos":
                        puerto.getAlimentosNoPerecederos().Push(contenedor);
                        break;
                    case "Alimentos Perecederos":
                        puerto.getAlimentosPerecederos().Push(contenedor);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Tipo de producto desconocido.");
                        break;
                }
            }
        }
        return "Desembarque completado.";
    }

    public void despacharContenedor(String codigo) {
        Puerto puerto = new Puerto();
        despacharContenedorMuelle(puerto.getSinAlimentos(), codigo);
        despacharContenedorMuelle(puerto.getAlimentosNoPerecederos(), codigo);
        despacharContenedorMuelle(puerto.getAlimentosPerecederos(), codigo);
    }

    private void despacharContenedorMuelle(Stack muelle, String codigo) {
        Stack tempStack = new Stack();
        while (!muelle.isEmpty()) {
            Contenedor contenedor = (Contenedor) muelle.Pop();
            if (contenedor.getCodigo().equals(codigo)) {
                // Contenedor encontrado y eliminado
                return;
            } else {
                tempStack.Push(contenedor);
            }
        }

        while (!tempStack.isEmpty()) {
            muelle.Push(tempStack.Pop());
        }
    }


    public Object Pop()
    {
        Object data=null;
        if(!isEmpty())
        {
            data=top.getData();
            top=top.getLink();
            return data;
        }
        return null;
    }

}

