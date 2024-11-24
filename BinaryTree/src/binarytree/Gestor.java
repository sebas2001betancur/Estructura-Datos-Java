
package binarytree;

import javax.swing.JOptionPane;


public class Gestor {
    public static void main(String[] args) {
      String option, name, type;
      String menu[]={"Add","toString", "Percentage",
          "List", "Path", "Select Disk", "Calculate Space", "List All Documents","Copy", "arbol_Balanceado", "Exit"};
      String mtype[]={"docx", "pdf"};
      String disks[] = {"C", "D"};
      double size;
      BinaryTree gestor = new BinaryTree();
        BinaryTree gestorC = new BinaryTree(); // Disco C
        BinaryTree gestorD = new BinaryTree(); // Disco D
        BinaryTree selectedDisk = null;

      do {
          option = (String) JOptionPane.showInputDialog(null, "Selected",
                  "Menu", 1, null, menu, menu[0]);
          switch (option) {
              case "Add":
                  if (selectedDisk == null) {
                      JOptionPane.showMessageDialog(null, "Please select a disk first!");
                  } else {
                      // Agregar un archivo al disco seleccionado
                      name = JOptionPane.showInputDialog("Enter file name:");
                      type = (String) JOptionPane.showInputDialog(null, "Select file type", "Type", JOptionPane.QUESTION_MESSAGE, null, mtype, mtype[0]);
                      size = Math.random() * 80; // Genera un tamaño aleatorio para el archivo
                      File file = new File(name, type, size);
                      selectedDisk.Add(file); // Se añade el archivo al disco seleccionado
                      JOptionPane.showMessageDialog(null, "File created in disk " + (selectedDisk == gestorC ? "C" : "D"));
                  }
                  break;
              case "toString":
                  JOptionPane.showMessageDialog(null, gestor.PreOrder());
                  break;
              case "Percentage":
                  JOptionPane.showMessageDialog(null, "The Percentage is: " +
                          gestor.Amount(gestor.getRoot()) / (double) gestor.Size() * 100 + "%");
                  break;
              case "List":
                  name = JOptionPane.showInputDialog("Enter name");
                  JOptionPane.showMessageDialog(null, gestor.Files(name).toString());
                  break;
              case "Path":
                  name = JOptionPane.showInputDialog("Enter name");
                  type = (String) JOptionPane.showInputDialog(null, "Selected",
                          "Type", 1, null, mtype, mtype[0]);
                  String res = gestor.Path(name, type);
                  if (res.equals(""))
                      JOptionPane.showMessageDialog(null, "File not found");
                  else
                      JOptionPane.showMessageDialog(null, "the path is: " + res);
                  break;
              case "Select Disk":
                  // Selección del disco
                  String disk = (String) JOptionPane.showInputDialog(null, "Select Disk", "Disks", JOptionPane.QUESTION_MESSAGE, null, disks, disks[0]);
                  selectedDisk = disk.equals("C") ? gestorC : gestorD;
                  JOptionPane.showMessageDialog(null, "Disk " + disk + " selected");
                  break;
              case "Calculate Space":
                  // Permitir al usuario seleccionar el disco
                  disk = (String) JOptionPane.showInputDialog(null, "Select disk", "Disks", 1, null, disks, disks[0]);

                  if (disk.equals("C")) {
                      // Calcular el espacio total en el disco C
                      JOptionPane.showMessageDialog(null, "Total space used in Disk C: " + gestorC.calculateTotalSpace() + " MB");
                  } else if (disk.equals("D")) {
                      // Calcular el espacio total en el disco D
                      JOptionPane.showMessageDialog(null, "Total space used in Disk D: " + gestorD.calculateTotalSpace() + " MB");
                  }
                  break;

              case "List All Documents":
                  List combinedList = new List();

                  // Obtener todos los archivos del disco C y agregarlos con una etiqueta del disco y tipo de archivo
                  List filesC = gestorC.getAllFiles();
                  for (int i = 0; i < filesC.Size(); i++) {
                      File fileC = (File) filesC.get(i);
                      String fileDetailsC = fileC.getName() + " (" + fileC.getType() + ") (C)";

                      // Agregar a la lista combinado si no está repetido en el mismo disco
                      if (!combinedList.contains(fileDetailsC)) {
                          combinedList.AddLast(fileDetailsC);
                      }
                  }


                  List filesD = gestorD.getAllFiles();
                  for (int i = 0; i < filesD.Size(); i++) {
                      File fileD = (File) filesD.get(i);
                      String fileDetailsD = fileD.getName() + " (" + fileD.getType() + ")";

                      boolean isRepeated = combinedList.contains(fileDetailsD + " (C)");

                      // Si ya está en la lista (de C), marcarlo como repetido
                      if (isRepeated) {
                          combinedList.AddLast(fileDetailsD + " (D) - Repeated");
                      } else {
                          // Si no está en C, solo agregar como archivo de D
                          combinedList.AddLast(fileDetailsD + " (D)");
                      }
                  }

                  // Mostrar la lista combinada con las etiquetas de tipo y si es repetido
                  JOptionPane.showMessageDialog(null, "Documents in C and D:\n" + combinedList.toString());
                  break;

              case "Copy":
                  // Seleccionar el disco de origen
                  String diskSource = JOptionPane.showInputDialog("Select the disk to copy from (C/D):").toUpperCase();

                  // Validar selección del disco de origen
                  if (!diskSource.equals("C") && !diskSource.equals("D")) {
                      JOptionPane.showMessageDialog(null, "Invalid disk selection.");
                      break;
                  }

                  // Asignar gestores de disco de origen y destino
                  BinaryTree sourceGestor = diskSource.equals("C") ? gestorC : gestorD;
                  BinaryTree targetGestor = diskSource.equals("C") ? gestorD : gestorC;

                  // Listar archivos en el disco de origen
                  List filesSource = sourceGestor.getAllFiles();
                  if (filesSource.isEmpty()) {
                      JOptionPane.showMessageDialog(null, "No files available on disk " + diskSource);
                      break;
                  }

                  // Mostrar los archivos disponibles y seleccionar uno
                  StringBuilder fileOptions = new StringBuilder("Select a file to copy from " + diskSource + ":\n");
                  Node fileNode = filesSource.getFirst();
                  int fileIndex = 1;

                  while (fileNode != null) {
                      fileOptions.append(fileIndex + ". " + fileNode.getData().toString() + "\n");
                      fileNode = fileNode.getLink();
                      fileIndex++;
                  }

                  String fileSelection = JOptionPane.showInputDialog(fileOptions.toString());

                  // Convertir la selección del archivo a un índice
                  int fileIndexSelected;
                  try {
                      fileIndexSelected = Integer.parseInt(fileSelection) - 1; // Convertir a índice
                  } catch (NumberFormatException e) {
                      JOptionPane.showMessageDialog(null, "Invalid file input.");
                      break;
                  }

                  if (fileIndexSelected < 0 || fileIndexSelected >= filesSource.Size()) {
                      JOptionPane.showMessageDialog(null, "Invalid file selected.");
                      break;
                  }

                  // Buscar el archivo seleccionado
                  File fileToCopy = (File) filesSource.get(fileIndexSelected);
                  if (fileToCopy == null) {
                      JOptionPane.showMessageDialog(null, "File not found.");
                      break;
                  }

                  // Mostrar espacios disponibles en el disco destino
                  List availableSpaces = targetGestor.getAvailableSpaces();
                  if (availableSpaces.isEmpty()) {
                      JOptionPane.showMessageDialog(null, "No available spaces in the target disk.");
                      break;
                  }

                  // Mostrar las posiciones disponibles para copiar
                  StringBuilder positionOptions = new StringBuilder("Select a position to copy the file in the target disk (available spaces):\n");
                  Node spaceNode = availableSpaces.getFirst();
                  int positionIndex = 1;

                  while (spaceNode != null) {
                      positionOptions.append(positionIndex + ". Position " + spaceNode.getData().toString() + "\n");
                      spaceNode = spaceNode.getLink();
                      positionIndex++;
                  }

                  String positionInput = JOptionPane.showInputDialog(positionOptions.toString());
                  int positionSelectedIndex;

                  try {
                      positionSelectedIndex = Integer.parseInt(positionInput) - 1; // Convertir a índice
                  } catch (NumberFormatException e) {
                      JOptionPane.showMessageDialog(null, "Invalid position input.");
                      break;
                  }


                  if (positionSelectedIndex < 0 || positionSelectedIndex >= availableSpaces.Size()) {
                      JOptionPane.showMessageDialog(null, "Invalid position selected.");
                      break;
                  }

                  // Insertar archivo al disco destino en la posición seleccionada
                  String selectedPosition = (String) availableSpaces.get(positionSelectedIndex);
                  boolean inserted = targetGestor.insertAtPosition(fileToCopy, selectedPosition);
                  if (!inserted) {
                      JOptionPane.showMessageDialog(null, "File insertion failed.");
                      break;
                  }

                  // Eliminar el archivo del disco de origen
                  boolean deleted = sourceGestor.deleteFile(fileToCopy);
                  if (deleted) {
                      JOptionPane.showMessageDialog(null, "File successfully copied from " + diskSource + " and removed from the original disk.");
                  } else {
                      JOptionPane.showMessageDialog(null, "Failed to delete file from the original disk.");
                  }
                  break;
              case "arbol_Balanceado":
                  //gestor.createBalancedTree();
                  break;

          }
      }while(!option.equals("Exit"));

    }

}
