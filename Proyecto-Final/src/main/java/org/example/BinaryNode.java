package org.example;

import java.io.Serializable;

public class BinaryNode implements Serializable { private static final long serialVersionUID = 1L;
   private BinaryNode left;
   private Object data;
   private BinaryNode right;

    public BinaryNode(Object data) {
        this.data = data;
    }

    public BinaryNode getLeft() {
        return left;
    }

    public void setLeft(BinaryNode left) {
        this.left = left;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public BinaryNode getRight() {
        return right;
    }

    public void setRight(BinaryNode right) {
        this.right = right;
    }
   
   
}
