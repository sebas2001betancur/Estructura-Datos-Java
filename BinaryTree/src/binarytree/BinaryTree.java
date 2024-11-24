
package binarytree;

import javax.swing.JOptionPane;

public class BinaryTree {
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

    public void Add(Object data) {
        Add(data, getRoot());
    }

    private void Add(Object data, BinaryNode aux) {
        if (isEmpty())
            root = new BinaryNode(data);
        else {
            String side = JOptionPane.showInputDialog(null, "Enter left "
                    + "or right of " + aux.getData());
            if (side.equals("left")) {
                if (aux.getLeft() == null)
                    aux.setLeft(new BinaryNode(data));
                else
                    Add(data, aux.getLeft());
            } else {
                if (aux.getRight() == null)
                    aux.setRight(new BinaryNode(data));
                else
                    Add(data, aux.getRight());
            }
        }
    }

    private String PreOrder(BinaryNode aux) {
        if (aux != null)
            return aux.getData() + "\n" +
                    PreOrder(aux.getLeft()) +
                    PreOrder(aux.getRight());
        return "";
    }

    public String PreOrder() {
        return PreOrder(root);
    }

    private String InOrder(BinaryNode aux) {
        if (aux != null)
            return InOrder(aux.getLeft()) +
                    aux.getData() + "\n" +
                    InOrder(aux.getRight());
        return "";
    }

    public String InOrder() {
        return InOrder(root);
    }

    private String PostOrder(BinaryNode aux) {
        if (aux != null)
            return PostOrder(aux.getLeft()) +
                    PostOrder(aux.getRight()) +
                    aux.getData() + "\n";
        return "";
    }

    public String PostOrder() {
        return PostOrder(root);
    }

    public int Size() {
        return Size(root);
    }

    private int Size(BinaryNode aux) {
        if (aux != null)
            return 1 + Size(aux.getLeft()) + Size(aux.getRight());
        // return Size(aux.getLEft()) + 1 + Size(aux.getRight())  --InOrder
        // return Size(aux.getLeft()) + Size(aux.getRight()) + 1  -- postorder

        return 0;
    }

    public int Height() {
        return Height(getRoot());
    }

    private int Height(BinaryNode aux) {
        if (aux != null)
            return 1 + Math.max(Height(aux.getLeft()), Height(aux.getRight()));

        return 0;
    }

    public boolean Search(String value) {
        return Search(value, getRoot());
    }

    private boolean Search(String value, BinaryNode aux) {
        if (aux != null) {
            if (((String) aux.getData()).equals(value))
                return true;
            else
                return Search(value, aux.getLeft()) || Search(value, aux.getRight());
        }
        return false;
    }

    public BinaryNode SearchN(String value) {
        return SearchN(value, getRoot());
    }

    private BinaryNode SearchN(String value, BinaryNode aux) {
        BinaryNode resul = null;
        if (aux != null) {
            if (((String) aux.getData()).equals(value))
                return aux;
            else {
                resul = SearchN(value, aux.getLeft());
                if (resul == null)
                    resul = SearchN(value, aux.getRight());
            }
        }
        return resul;
    }

    public BinaryNode getFather(String value) {
        if (!isEmpty()) {
            if (((String) getRoot().getData()).equals(value))
                return null;
            else
                return getFather(value, getRoot());
        }
        return null;
    }

    private BinaryNode getFather(String value, BinaryNode aux) {
        BinaryNode father = null;
        if (aux != null) {
            if (aux.getLeft() != null && ((String) aux.getLeft().getData()).equals(value)
                    || aux.getRight() != null && ((String) aux.getRight().getData()).equals(value))
                return aux;

            else {
                father = getFather(value, aux.getLeft());
                if (father == null)
                    father = getFather(value, aux.getRight());
            }
        }
        return father;
    }

    public List Successor(String value) {
        BinaryNode s = SearchN(value, root);
        if (s != null)
            return Successor(value, s, new List());
        else
            return null;
    }

    private List Successor(String value, BinaryNode aux, List list) {
        if (aux != null) {
            if (!((String) aux.getData()).equals(value)) {
                list.AddLast(aux.getData());
            }
            Successor(value, aux.getLeft(), list);
            Successor(value, aux.getRight(), list);

        }
        return list;
    }


    public boolean Delete(String value) {
        BinaryNode delete = SearchN(value);
        if (delete != null) {
            if (delete.getLeft() == null && delete.getRight() == null)
                DeleteZero(delete);
            else {
                if (delete.getLeft() == null || delete.getRight() == null)
                    DeleteOne(delete);
                else
                    DeleteTwo(delete);
            }
            return true;
        }
        return false;
    }

    private void DeleteZero(BinaryNode delete) {
        BinaryNode father = getFather((String) delete.getData());
        if (father == null)
            root = null;
        else {
            if (father.getLeft() == delete)
                father.setLeft(null);
            else
                father.setRight(null);
        }
    }

    private void DeleteOne(BinaryNode delete) {
        BinaryNode father = getFather((String) delete.getData());
        if (father == null) {
            if (delete.getLeft() != null)
                root = delete.getLeft();
            else
                root = delete.getRight();
        } else {
            if (father.getLeft() == delete) {
                if (delete.getLeft() != null)
                    father.setLeft(delete.getLeft());
                else
                    father.setLeft(delete.getRight());
            } else {
                if (delete.getLeft() != null)
                    father.setRight(delete.getLeft());
                else
                    father.setRight(delete.getRight());
            }
        }
    }

    private void DeleteTwo(BinaryNode delete) {
        String value = (String) (MostLeft(delete).getData());
        Delete(value);
        delete.setData(value);
    }

    private BinaryNode MostLeft(BinaryNode aux) {
        if (aux.getLeft() != null)
            return MostLeft(aux.getLeft());
        return aux;
    }

    public void Add(File f) {
        if (isEmpty())
            root = new BinaryNode(f);
        else
            Add(f, root);  //llama al metodo recursivo
    }

    private void Add(File f, BinaryNode aux) {
        if (((File) aux.getData()).getName().compareTo(f.getName()) >= 0) {
            if (aux.getLeft() == null)
                aux.setLeft(new BinaryNode(f));
            else
                Add(f, aux.getLeft());
        } else {
            if (aux.getRight() == null)
                aux.setRight(new BinaryNode(f));
            else
                Add(f, aux.getRight());
        }

    }

    public int Amount(BinaryNode aux) {
        if (aux != null) {
            if (((File) aux.getData()).getSize() >= 20)
                return 1 + Amount(aux.getLeft()) + Amount(aux.getRight());
            else
                return Amount(aux.getLeft()) + Amount(aux.getRight());
        }
        return 0;
    }

    public List Files(String name) {
        return Files(name, root, new List());
    }

    private List Files(String name, BinaryNode aux, List list) {
        if (aux != null) {
            if (((File) aux.getData()).getName().contains(name))
                list.AddLast(aux.getData());

            Files(name, aux.getLeft(), list);
            Files(name, aux.getRight(), list);
        }
        return list;
    }

    public BinaryNode SearchN(String name, String type) {
        return SearchN(name, type, root);
    }

    private BinaryNode SearchN(String name, String type, BinaryNode aux) {
        BinaryNode resul = null;
        if (aux != null) {
            if (((File) aux.getData()).getName().equals(name) &&
                    ((File) aux.getData()).getType().equals(type))
                return aux;
            else {
                resul = SearchN(name, type, aux.getLeft());
                if (resul == null)
                    resul = SearchN(name, type, aux.getRight());
            }
        }
        return resul;
    }

    public String Path(String name, String type) {
        BinaryNode search = SearchN(name, type);
        if (search != null)
            return Path(search);

        return "";
    }

    private String Path(BinaryNode aux) {
        if (aux != null)
            return Path(getFather(aux, root)) + "\\" +
                    ((File) aux.getData()).getName();

        return "";
    }

    private BinaryNode getFather(BinaryNode value, BinaryNode aux) {
        BinaryNode father = null;
        if (aux != null) {
            if (aux.getLeft() != null && aux.getLeft() == value
                    || aux.getRight() != null && aux.getRight() == value)
                return aux;

            else {
                father = getFather(value, aux.getLeft());
                if (father == null)
                    father = getFather(value, aux.getRight());
            }
        }
        return father;
    }

    public double calculateTotalSpace() {
        return calculateTotalSpace(root);
    }

    private double calculateTotalSpace(BinaryNode node) {
        if (node == null) {
            return 0;
        }

        // tamaño del archivo actual
        File file = (File) node.getData();
        double currentFileSize = file.getSize();


        return currentFileSize + calculateTotalSpace(node.getLeft()) + calculateTotalSpace(node.getRight());
    }


    ////////////////////////

    public List getAllFiles() {
        List fileList = new List();
        getAllFiles(root, fileList);
        return fileList;
    }

    private void getAllFiles(BinaryNode node, List fileList) {
        if (node != null) {
            fileList.AddLast(node.getData());  // Aquí se agregan los archivos
            getAllFiles(node.getLeft(), fileList);
            getAllFiles(node.getRight(), fileList);
        }
    }


    public File searchFile(String fileName) {
        return searchFile(root, fileName);
    }

    private File searchFile(BinaryNode node, String fileName) {
        if (node == null) {
            return null;
        }

        File file = (File) node.getData();
        if (file.getName().equals(fileName)) {
            return file;
        }

        File leftSearch = searchFile(node.getLeft(), fileName);
        if (leftSearch != null) {
            return leftSearch;
        }

        return searchFile(node.getRight(), fileName);
    }


    public List getAvailableSpaces() {
        List positions = new List();
        getAvailablePositions(root, "", positions);
        return positions;
    }

    private void getAvailablePositions(BinaryNode node, String currentPath, List positions) {
        if (node == null) {
            positions.AddLast(currentPath);
            return;
        }
        getAvailablePositions(node.getLeft(), currentPath + "L", positions);
        getAvailablePositions(node.getRight(), currentPath + "R", positions);
    }


    public boolean insertAtPosition(File file, String position) {
        BinaryNode newNode = new BinaryNode(file);
        if (position.isEmpty()) {
            root = newNode;
            return true;
        }

        BinaryNode current = root;
        for (int i = 0; i < position.length() - 1; i++) {
            if (position.charAt(i) == 'L') {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }

        if (position.charAt(position.length() - 1) == 'L') {
            current.setLeft(newNode);
        } else {
            current.setRight(newNode);
        }

        return true; // Inserción exitosa
    }


    //Metodo para encontrar el nodo con el valor mínimo

    private BinaryNode findMin(BinaryNode node) {
        if (node == null) {
            return null;
        } else if (node.getLeft() == null) {
            return node;  // El nodo más a la izquierda es el mínimo
        } else {
            return findMin(node.getLeft());
        }
    }


    public boolean deleteFile(File file) {
        root = deleteFile(root, file);
        return root != null;
    }

    private BinaryNode deleteFile(BinaryNode node, File file) {
        if (node == null) {
            return null;
        }
        File nodeFile = (File) node.getData();
        if (nodeFile.equals(file)) {
            // Lógica para eliminar el archivo
            if (node.getLeft() == null && node.getRight() == null) {
                return null;
            } else if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            } else {
                BinaryNode minNode = findMin(node.getRight());
                node.setData(minNode.getData());
                node.setRight(deleteFile(node.getRight(), (File) minNode.getData()));
            }
        } else {
            node.setLeft(deleteFile(node.getLeft(), file));
            node.setRight(deleteFile(node.getRight(), file));
        }
        return node;
    }

}
