package quiz3;

import java.util.StringJoiner;

public class BinaryTree {
    private BinaryNode root;

    public BinaryTree() {
    }

    public BinaryNode getRoot() {
        return root;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void Add(Empleado e) {
        root = addRecursive(root, e);
    }

    private BinaryNode addRecursive(BinaryNode current, Empleado empleado) {
        if (current == null) {
            return new BinaryNode(empleado);
        }
        if (empleado.getId().compareTo(((Empleado)current.getData()).getId()) < 0) {
            current.setLeft(addRecursive(current.getLeft(), empleado));
        } else if (empleado.getId().compareTo(((Empleado)current.getData()).getId()) > 0) {
            current.setRight(addRecursive(current.getRight(), empleado));
        }
        return current;
    }

    public String preOrderInverse() {
        StringBuilder output = new StringBuilder();
        preOrderInverseRecursive(root, output);
        return output.toString();
    }

    private void preOrderInverseRecursive(BinaryNode node, StringBuilder output) {
        if (node != null) {
            output.append(node.getData()).append("\n");
            preOrderInverseRecursive(node.getRight(), output);
            preOrderInverseRecursive(node.getLeft(), output);
        }
    }

    public BinaryTree filterByCargo(String cargo) {
        BinaryTree newTree = new BinaryTree();
        filterByCargoRecursive(root, newTree, cargo);
        return newTree;
    }

    private void filterByCargoRecursive(BinaryNode node, BinaryTree newTree, String cargo) {
        if (node != null) {
            if (((Empleado) node.getData()).getCargo().contains(cargo)) {
                newTree.Add((Empleado) node.getData());
            }
            filterByCargoRecursive(node.getLeft(), newTree, cargo);
            filterByCargoRecursive(node.getRight(), newTree, cargo);
        }
    }

    public List listBySalary(int salary) {
        List list = new List();
        listBySalaryRecursive(root, list, salary);
        return list;
    }

    private void listBySalaryRecursive(BinaryNode node, List list, int salary) {
        if (node != null) {
            if (((Empleado) node.getData()).getSalario() > salary) {
                list.AddLast(node.getData());
            }
            listBySalaryRecursive(node.getLeft(), list, salary);
            listBySalaryRecursive(node.getRight(), list, salary);
        }
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(", ");
        buildString(root, sj);
        return sj.toString();
    }

    private void buildString(BinaryNode node, StringJoiner sj) {
        if (node != null) {
            sj.add(node.getData().toString());
            buildString(node.getLeft(), sj);
            buildString(node.getRight(), sj);
        }
    }
}
