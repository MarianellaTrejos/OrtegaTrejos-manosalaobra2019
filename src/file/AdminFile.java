package file;

import domain.Admin;
import domain.Janitor;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class AdminFile {

    private static RandomAccessFile raf;
    private static int numRegister;
    private static int sizeReg = 60;

    public static void createFileAdmin(File file) throws IOException {
        if (file.exists() && !file.isFile()) {
            throw new IOException(file.getName() + " no es un archivo");
        }
        raf = new RandomAccessFile(file, "rw");
        numRegister = (int) Math.ceil((double) raf.length() / (double) sizeReg);
    }

    public static void close() throws IOException {
        raf.close();
    }

    public static boolean setAdmin(int i, Admin admin) throws IOException {
        if (i >= 0 && i <= getNumRegister()) {
            if (admin.size() > sizeReg) {
                System.out.println("\nTamaño de registro excedido.");
            } else {
                raf.seek(i * sizeReg);

                raf.writeUTF(admin.getName());
                raf.writeUTF(admin.getLastName());
                raf.writeDouble(admin.getSalary());
                raf.writeInt(admin.getId());
                raf.writeInt(admin.getEmployeeNumber());
                raf.writeBoolean(admin.isCollegeDegree());
                raf.writeInt(admin.getTelephoneNumber());
                raf.writeInt(admin.getCategoryType());
                raf.writeBoolean(admin.isActive());

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
            if (!getAdmin(i).isActive()) {
                return i;
            }
        }
        return -1;
    }

    public static boolean deleteAdmin(String aEliminar) throws IOException {
        int pos = searchRegister(aEliminar);
        if (pos == -1) {
            return false;
        }
        Admin adminEliminate = getAdmin(pos);
        adminEliminate.setActive(false);
        setAdmin(pos, adminEliminate);
        return true;
    }

    public static void compactFile(File file) throws IOException {
        createFileAdmin(file); // Abrimos el raf.
        Admin[] list = new Admin[numRegister];
        for (int i = 0; i < numRegister; ++i) {
            list[i] = getAdmin(i);
        }
        close(); // Cerramos el raf.
        file.delete(); // Borramos el archivo.

        File tempo = new File("./temporal.dat");
        createFileAdmin(tempo); // Como no existe se crea.
        for (Admin a : list) {
            if (a.isActive()) {
                addAdmin(a);
            }
        }
        close();

        tempo.renameTo(file); // Renombramos.
    }

    public static void addAdmin(Admin admin) throws IOException {
        int inactive = searchInactiveRegister();
        if (setAdmin(inactive == -1 ? numRegister : inactive, admin)) {
            numRegister++;
        }
    }

    public static int getNumRegister() {
        return numRegister;
    }

    public static Admin getAdmin(int i) throws IOException {
        if (i >= 0 && i <= getNumRegister()) {
            raf.seek(i * sizeReg);
            Admin a = new Admin();
            
            if(a.getName().equals("deleted")){
                return null;
            }else {
                return new Admin(raf.readUTF(),raf.readUTF(),raf.readDouble(),raf.readInt(),raf.readInt(),raf.readBoolean(),raf.readInt(),raf.readInt(),raf.readBoolean());
            }

        } else {
            System.out.println("\nNúmero de registro fuera de límites.");
            return null;
        }
    }

    public static int searchRegister(String search) throws IOException {
        Admin a;
        if (search == null) {
            return -1;
        }
        for (int i = 0; i < getNumRegister(); i++) {
            raf.seek(i * sizeReg);
            a = getAdmin(i);
            if (a.getName().equals(search) && a.isActive()) {
                return i;
            }
        }
        return -1;
    }

    public static Admin getAdminPru(int position) throws IOException {
        //validacion de la posicion
        if (position >= 0 && position <= numRegister) {
            //colocamos el puntero en el lugar 
            raf.seek(position * sizeReg);

            //instancia de person
            Admin myAdmin = new Admin();

            //llevamos a cabo las lecturas
            myAdmin.setName(raf.readUTF());
            myAdmin.setLastName(raf.readUTF());
            myAdmin.setSalary(raf.readDouble());

            myAdmin.setId(raf.readInt());
            myAdmin.setEmployeeNumber(raf.readInt());
            myAdmin.setCollegeDegree(raf.readBoolean());
            //myDriver.setPru(randomAccessFile.readInt());
            myAdmin.setTelephoneNumber(raf.readInt());
            myAdmin.setCategoryType(raf.readInt());
            myAdmin.setActive(raf.readBoolean());

            //si es delete no retorno
            if (myAdmin.getName().equalsIgnoreCase("deleted")) {
                return null;
            } else {
                return myAdmin;
            }

        } else {
            System.err.println("6001 position is out of bounds");
            return null;
        }
    }
    
    public static List<Admin> getAllAdmin() throws IOException {

        //variables a retornar
        List<Admin> admin = new ArrayList<Admin>();
        try {
            //recorro todos mis registros y los inserto en la lista
            for (int i = 0; i < numRegister; i++) {
                Admin adminTemp = AdminFile.getAdminPru(i);

                if (adminTemp != null) {
                    admin.add(adminTemp);
                }
            }

        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, "Error al leer los datos");
        }
        return admin;
    }//fin metodo

    public static boolean deleteRecord(String name) throws IOException {
        Admin myAdmin;

        //buscar el registro para la eliminacion
        for (int i = 0; i < numRegister; i++) {

            //obtengo a la persona de esa posicion
            myAdmin = AdminFile.getAdmin(i);

            //pregunto si es la persona que quiero eliminar
            if (myAdmin.getName().equalsIgnoreCase(name)) {

                //marcar esta persona como eliminada
                myAdmin.setName("deleted");

                return AdminFile.setAdmin(i, myAdmin);
            }
        }

        //si llega a este punto no encontro a la persona
        return false;
    }

}
