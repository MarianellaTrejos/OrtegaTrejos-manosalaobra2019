package file;

import domain.Driver;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DriverFile {

    private static RandomAccessFile raf;
    private static int numRegister;
    private static int sizeReg = 60;

    public static void createFileDriver(File file) throws IOException {
        if (file.exists() && !file.isFile()) {
            throw new IOException(file.getName() + " no es un archivo");
        }
        raf = new RandomAccessFile(file, "rw");
        numRegister = (int) Math.ceil((double) raf.length() / (double) sizeReg);
    }

    public static void close() throws IOException {
        raf.close();
    }

    public static boolean setDriver(int i, Driver driver) throws IOException {
        if (i >= 0 && i <= getNumRegister()) {
            if (driver.size() > sizeReg) {
                System.out.println("\nTamaño de registro excedido.");
            } else {
                raf.seek(i * sizeReg);

                raf.writeUTF(driver.getName());
                raf.writeUTF(driver.getLastName());
                raf.writeDouble(driver.getSalary());
                raf.writeInt(driver.getId());
                raf.writeInt(driver.getEmployeeNumber());
                raf.writeBoolean(driver.isCollegeDegree());
                raf.writeUTF(driver.getTurn());
                raf.writeInt(driver.getHours());
                raf.writeBoolean(driver.isActive());

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
            if (!getDriver(i).isActive()) {
                return i;
            }
        }
        return -1;
    }

    public static boolean deleteDriver(String aEliminar) throws IOException {
        int pos = searchRegister(aEliminar);
        if (pos == -1) {
            return false;
        }
        Driver driverEliminate = getDriver(pos);
        driverEliminate.setActive(false);
        setDriver(pos, driverEliminate);
        return true;
    }

    public static void compactFile(File file) throws IOException {
        createFileDriver(file); // Abrimos el raf.
        Driver[] list = new Driver[numRegister];
        for (int i = 0; i < numRegister; ++i) {
            list[i] = getDriver(i);
        }
        close(); // Cerramos el raf.
        file.delete(); // Borramos el archivo.

        File tempo = new File("./temporal.dat");
        createFileDriver(tempo); // Como no existe se crea.
        for (Driver a : list) {
            if (a.isActive()) {
                addDriver(a);
            }
        }
        close();

        tempo.renameTo(file); // Renombramos.
    }

    public static void addDriver(Driver driver) throws IOException {
        int inactive = searchInactiveRegister();
        if (setDriver(inactive == -1 ? numRegister : inactive, driver)) {
            numRegister++;
        }
    }

    public static int getNumRegister() {
        return numRegister;
    }

    public static Driver getDriver(int i) throws IOException {
        if (i >= 0 && i <= getNumRegister()) {
            Driver d = new Driver();
            raf.seek(i * sizeReg);
            if (d.getName().contains("deleted")) {
                return null;
            } else {

                return new Driver(raf.readUTF(), raf.readUTF(), raf.readDouble(), raf.readInt(), raf.readInt(), raf.readBoolean(), raf.readUTF(), raf.readInt(), raf.readBoolean());
            }
        } else {
            System.out.println("\nNúmero de registro fuera de límites.");
            return null;
        }
    }

    public static int searchRegister(String search) throws IOException {
        Driver a;
        if (search == null) {
            return -1;
        }
        for (int i = 0; i < getNumRegister(); i++) {
            raf.seek(i * sizeReg);
            a = getDriver(i);
            if (a.getName().equals(search) && a.isActive()) {
                return i;
            }
        }
        return -1;
    }

//////    
    public static Driver getDriverPru(int position) throws IOException {
        //validacion de la posicion
        if (position >= 0 && position <= numRegister) {
            //colocamos el puntero en el lugar 
            raf.seek(position * sizeReg);

            //instancia de person
            Driver myDriver = new Driver();

            //llevamos a cabo las lecturas
            myDriver.setName(raf.readUTF());
            myDriver.setLastName(raf.readUTF());
            myDriver.setSalary(raf.readDouble());

            myDriver.setId(raf.readInt());
            myDriver.setEmployeeNumber(raf.readInt());
            myDriver.setCollegeDegree(raf.readBoolean());
            //myDriver.setPru(randomAccessFile.readInt());
            myDriver.setTurn(raf.readUTF());
            myDriver.setHours(raf.readInt());
            myDriver.setActive(raf.readBoolean());

            //si es delete no retorno
            if (myDriver.getName().equalsIgnoreCase("deleted")) {
                return null;
            } else {
                return myDriver;
            }

        } else {
            System.err.println("6001 position is out of bounds");
            return null;
        }
    }//fin de metodo

    public static List<Driver> getAllDrivers() throws IOException {

        //variables a retornar
        List<Driver> driver = new ArrayList<Driver>();
        try {
            //recorro todos mis registros y los inserto en la lista
            for (int i = 0; i < numRegister; i++) {
                Driver driverTemp = DriverFile.getDriverPru(i);

                if (driverTemp != null) {
                    driver.add(driverTemp);
                }
            }

        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, "Error al leer los datos");
        }
        return driver;
    }//fin metodo

    public static boolean deleteRecord(String name) throws IOException {
        Driver myDriver;

        //buscar el registro para la eliminacion
        for (int i = 0; i < numRegister; i++) {

            //obtengo a la persona de esa posicion
            myDriver = DriverFile.getDriver(i);

            //pregunto si es la persona que quiero eliminar
            if (myDriver.getName().equalsIgnoreCase(name)) {

                //marcar esta persona como eliminada
                myDriver.setName("deleted");

                return DriverFile.setDriver(i, myDriver);
            }
        }

        //si llega a este punto no encontro a la persona
        return false;
    }

}
