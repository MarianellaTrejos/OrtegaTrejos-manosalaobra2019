package file;

import domain.vehicle;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class VehicleFile {

    private static RandomAccessFile raf;
    private static int numRegister;
    private static int sizeReg = 60;

    public static void createFilevehicle(File file) throws IOException {
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

    public static boolean setvehicle(int i, vehicle Vehicle_) throws IOException {
        if (i >= 0 && i <= getNumRegister()) {
            if (Vehicle_.size() > sizeReg) {
                System.out.println("\nTamaño de registro excedido.");
            } else {
                raf.seek(i * sizeReg);
                raf.writeInt(Vehicle_.getSerie());
                raf.writeUTF(Vehicle_.getName());
                raf.writeInt(Vehicle_.getYear());
                raf.writeBoolean(Vehicle_.isAmerican());
                raf.writeFloat(Vehicle_.getMileage());
                raf.writeDouble(Vehicle_.getTotalSalary());
                raf.writeBoolean(Vehicle_.isActive());

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
            if (!getvehicle(i).isActive()) {
                return i;
            }
        }
        return -1;
    }

    public static boolean deletevehicle(String aEliminar) throws IOException {
        int pos = searchRegister(aEliminar);
        if (pos == -1) {
            return false;
        }
        vehicle Vehicle_Eliminate = getvehicle(pos);
        Vehicle_Eliminate.setActive(false);
        setvehicle(pos, Vehicle_Eliminate);
        return true;
    }

    public static void compactFile(File file) throws IOException {
        createFilevehicle(file); // Abrimos el raf.
        vehicle[] list = new vehicle[numRegister];
        for (int i = 0; i < numRegister; ++i) {
            list[i] = getvehicle(i);
        }
        close(); // Cerramos el raf.
        file.delete(); // Borramos el archivo.

        File tempo = new File("./temporal.dat");
        createFilevehicle(tempo); // Como no existe se crea.
        for (vehicle a : list) {
            if (a.isActive()) {
                addvehicle(a);
            }
        }
        close();

        tempo.renameTo(file); // Renombramos.
    }

    public static void addvehicle(vehicle Vehicle_) throws IOException {
        int inactive = searchInactiveRegister();
        if (setvehicle(inactive == -1 ? numRegister : inactive, Vehicle_)) {
            numRegister++;
        }
    }

    public static int getNumRegister() {
        return numRegister;
    }

    public static vehicle getvehicle(int i) throws IOException {
        if (i >= 0 && i <= getNumRegister()) {
            vehicle v = new vehicle();
            raf.seek(i * sizeReg);
            if (v.getName().equals("deleted")) {
                return null;
            } else {
                return new vehicle(raf.readInt(), raf.readUTF(), raf.readInt(), raf.readBoolean(), raf.readFloat(), raf.readDouble(), raf.readBoolean());
            }
        } else {
            System.out.println("\nNúmero de registro fuera de límites.");
            return null;
        }
    }

    public static int searchRegister(String search) throws IOException {
        vehicle a;
        if (search == null) {
            return -1;
        }
        for (int i = 0; i < getNumRegister(); i++) {
            raf.seek(i * sizeReg);
            a = getvehicle(i);
            if (a.getName().equals(search) && a.isActive()) {
                return i;
            }
        }
        return -1;
    }

    public static vehicle getVehiclePru(int position) throws IOException {
        //validacion de la posicion
        if (position >= 0 && position <= numRegister) {
            //colocamos el puntero en el lugar 
            raf.seek(position * sizeReg);

            //instancia de person
            vehicle myVehicle = new vehicle();

            //llevamos a cabo las lecturas
            myVehicle.setSerie(raf.readInt());
            myVehicle.setName(raf.readUTF());
            myVehicle.setYear(raf.readInt());
            myVehicle.setAmerican(raf.readBoolean());
            //myDriver.setPru(randomAccessFile.readInt());
            myVehicle.setMileage(raf.readFloat());
            myVehicle.setTotalSalary(raf.readDouble());
            myVehicle.setActive(raf.readBoolean());

            //si es delete no retorno
            if (myVehicle.getName().equalsIgnoreCase("deleted")) {
                return null;
            } else {
                return myVehicle;
            }

        } else {
            System.err.println("6001 position is out of bounds");
            return null;
        }
    }

    public static List<vehicle> getAllvehicle() throws IOException {

        //variables a retornar
        List<vehicle> Vehicle_ = new ArrayList<vehicle>();
        try {
            //recorro todos mis registros y los inserto en la lista
            for (int i = 0; i < numRegister; i++) {
                vehicle Vehicle_Temp = VehicleFile.getVehiclePru(i);

                if (Vehicle_Temp != null) {
                    Vehicle_.add(Vehicle_Temp);
                }
            }

        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, "Error al leer los datos");
        }
        return Vehicle_;
    }//fin metodo

    public static boolean deleteRecord(int serie) throws IOException {
        vehicle myVehicle;

        //buscar el registro para la eliminacion
        for (int i = 0; i < numRegister; i++) {

            //obtengo a la persona de esa posicion
            myVehicle = VehicleFile.getvehicle(i);

            //pregunto si es la persona que quiero eliminar
            if (myVehicle.getSerie() == (serie)) {

                //marcar esta persona como eliminada
                myVehicle.setName("deleted");

                return VehicleFile.setvehicle(i, myVehicle);
            }
        }

        //si llega a este punto no encontro a la persona
        return false;
    }

}
