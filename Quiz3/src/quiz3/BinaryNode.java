package quiz3;

public class BinaryNode {
   private BinaryNode left;
   private Empleado data;
   private BinaryNode right;
  

    public BinaryNode(Empleado data) {
        this.data = data;
    }




    public BinaryNode getLeft() {
        return left;
    }

    public void setLeft(BinaryNode left) {
        this.left = left;
    }

    public Empleado getData() {
        return data;
    }

    public void setData(Empleado data) {
        this.data = data;
    }


    public BinaryNode getRight() {
        return right;
    }

    public void setRight(BinaryNode right) {
        this.right = right;
    }
}
