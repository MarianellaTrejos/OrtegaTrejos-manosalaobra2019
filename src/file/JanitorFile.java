package file;

import domain.Driver;
import domain.Janitor;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class JanitorFile {

    private static RandomAccessFile raf;
    private static int numRegister;
    private static int sizeReg = 60;

    public static void createFileJanitor(File file) throws IOException {
        if (file.exists() && !file.isFile()) {
            throw new IOException(file.getName() + " no es un archivo");
        }
        raf = new RandomAccessFile(file, "rw");
        numRegister = (int) Math.ceil(
                (double) raf.length() / (double) sizeReg);
    }

    public static void close() throws IOException {
        raf.close();
    }

    public static boolean setJanitor(int i, Janitor janitor) throws IOException {
        if (i >= 0 && i <= getNumRegister()) {
            if (janitor.size() > sizeReg) {
                System.out.println("\nTamaño de registro excedido.");
            } else {
                raf.seek(i * sizeReg);

                raf.writeUTF(janitor.getName());
                raf.writeUTF(janitor.getLastName());
                raf.writeDouble(janitor.getSalary());
                raf.writeInt(janitor.getId());
                raf.writeInt(janitor.getEmployeeNumber());
                raf.writeBoolean(janitor.isCollegeDegree());
                raf.writeDouble(janitor.getYearsExperience());
                raf.writeInt(janitor.getAge());
                raf.writeBoolean(janitor.isActive());

                return true;
            }
        } else {
            System.out.println("\nNúmero de registro fuera de límites.");
        }
        return false;
    }

    private static int searchInactiveRegister() throws IOException {
        String name;
        for (int i = 0; i < getNumRegister(); i++) {
            raf.seek(i * sizeReg);
            if (!getJanitor(i).isActive()) {
                return i;
            }
        }
        return -1;
    }

    public static boolean deleteJanitor(String aEliminar) throws IOException {
        int pos = searchRegister(aEliminar);
        if (pos == -1) {
            return false;
        }
        Janitor janitorEliminate = getJanitor(pos);
        janitorEliminate.setActive(false);
        setJanitor(pos, janitorEliminate);
        return true;
    }

    public static void compactFile(File file) throws IOException {
        createFileJanitor(file); // Abrimos el raf.
        Janitor[] list = new Janitor[numRegister];
        for (int i = 0; i < numRegister; ++i) {
            list[i] = getJanitor(i);
        }
        close(); // Cerramos el raf.
        file.delete(); // Borramos el archivo.

        File tempo = new File("./temporal.dat");
        createFileJanitor(tempo); // Como no existe se crea.
        for (Janitor a : list) {
            if (a.isActive()) {
                addJanitor(a);
            }
        }
        close();

        tempo.renameTo(file); // Renombramos.
    }

    public static void addJanitor(Janitor janitor) throws IOException {
        int inactive = searchInactiveRegister();
        if (setJanitor(inactive == -1 ? numRegister : inactive, janitor)) {
            numRegister++;
        }
    }

    public static int getNumRegister() {
        return numRegister;
    }

    public static Janitor getJanitor(int i) throws IOException {
        if (i >= 0 && i <= getNumRegister()) {
            Janitor j = new Janitor();
            raf.seek(i * sizeReg);
            if (j.getName().contains("deleted")) {
                return null;
            } else {
                return new Janitor(raf.readUTF(), raf.readUTF(), raf.readDouble(), raf.readInt(), raf.readInt(), raf.readBoolean(), raf.readDouble(), raf.readInt(), raf.readBoolean());
            }
        } else {
            System.out.println("\nNúmero de registro fuera de límites.");
            return null;
        }
    }

    public static int searchRegister(String search) throws IOException {
        Janitor a;
        if (search == null) {
            return -1;
        }
        for (int i = 0; i < getNumRegister(); i++) {
            raf.seek(i * sizeReg);
            a = getJanitor(i);
            if (a.getName().equals(search) && a.isActive()) {
                return i;
            }
        }
        return -1;
    }

    public static Janitor getJanitorPru(int position) throws IOException {
        //validacion de la posicion
        if (position >= 0 && position <= numRegister) {
            //colocamos el puntero en el lugar 
            raf.seek(position * sizeReg);

            //instancia de person
            Janitor myJanitor = new Janitor();

            //llevamos a cabo las lecturas
            myJanitor.setName(raf.readUTF());
            myJanitor.setLastName(raf.readUTF());
            myJanitor.setSalary(raf.readDouble());

            myJanitor.setId(raf.readInt());
            myJanitor.setEmployeeNumber(raf.readInt());
            myJanitor.setCollegeDegree(raf.readBoolean());
            //myDriver.setPru(randomAccessFile.readInt());
            myJanitor.setYearsExperience(raf.readDouble());
            myJanitor.setAge(raf.readInt());
            myJanitor.setActive(raf.readBoolean());

            //si es delete no retorno
            if (myJanitor.getName().equalsIgnoreCase("deleted")) {
                return null;
            } else {
                return myJanitor;
            }

        } else {
            System.err.println("6001 position is out of bounds");
            return null;
        }
    }
    
    public static List<Janitor> getAllJanitors() throws IOException {

        //variables a retornar
        List<Janitor> janitor = new ArrayList<Janitor>();
        try {
            //recorro todos mis registros y los inserto en la lista
            for (int i = 0; i < numRegister; i++) {
                Janitor janitorTemp = JanitorFile.getJanitorPru(i);

                if (janitorTemp != null) {
                    janitor.add(janitorTemp);
                }
            }

        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, "Error al leer los datos");
        }
        return janitor;
    }//fin metodo

    public static boolean deleteRecord(String name) throws IOException {
        Janitor myJanitor;

        //buscar el registro para la eliminacion
        for (int i = 0; i < numRegister; i++) {

            //obtengo a la persona de esa posicion
            myJanitor = JanitorFile.getJanitor(i);

            //pregunto si es la persona que quiero eliminar
            if (myJanitor.getName().equalsIgnoreCase(name)) {

                //marcar esta persona como eliminada
                myJanitor.setName("deleted");

                return JanitorFile.setJanitor(i, myJanitor);
            }
        }

        //si llega a este punto no encontro a la persona
        return false;
    }

}
