package file;

import domain.Admin;
import domain.Driver;
import domain.Employee;
import domain.Janitor;
import domain.vehicle;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class EmployeeFile {

    public RandomAccessFile randomAccessFile;
    public int regsQuantity;//me indica la cantidad de registros
    public int regSize;
    private String myFilePath;

    

    

    public void EmployeeFile(File fileEmployee) throws IOException {
        start(fileEmployee);
    }

    public void start(File file) throws IOException {
        //almaceno la ruta
        myFilePath = file.getPath();

        //tamanno maximo de los registros dentro de esta 
        //clase
        this.regSize = 600;

        //una validacion basica
        if (file.exists() && !file.isFile()) {
            throw new IOException(file.getName()
                    + " is an invalid file");
        } else {
            //crear la nueva instancia de randomAccessFile
            randomAccessFile = new RandomAccessFile(file, "rw");

            //necesitamos indicar cuantos registros tiene el archivo
            this.regsQuantity = (int) Math.ceil((double) randomAccessFile.length() / (double) regSize);
        }
    }//fin start

    public void close() throws IOException {
        randomAccessFile.close();
    }

    public boolean putValue(int position, Employee employee) throws IOException {
        //una pequenna validacion antes de insertar
        if (position >= 0 && position <= regsQuantity) {
            if (employee.size() > regSize) {
                System.err.print("7001 record size is out of bounds");
                return false;
            } else {
                //escribimos en archivo
                randomAccessFile.seek(position * regSize);
                randomAccessFile.writeUTF(employee.getName());
                randomAccessFile.writeUTF(employee.getLastName());
                randomAccessFile.writeInt(employee.getId());
                randomAccessFile.writeInt(employee.getEmployeeNumber());

                return true;
            }
        } else {
            System.err.print("7002 position is "
                    + "out of bounds of this file");
            return false;
        }

    }//fin metodo

    public boolean putValueAdmin(int position, Admin admin) throws IOException {
        //una pequenna validacion antes de insertar
        if (position >= 0 && position <= regsQuantity) {
            if (admin.size() > regSize) {
                System.err.print("7001 record size is out of bounds");
                return false;
            } else {
                //escribimos en archivo
                randomAccessFile.seek(position * regSize);
                randomAccessFile.writeUTF(admin.getName());
                randomAccessFile.writeUTF(admin.getLastName());
                randomAccessFile.writeInt(admin.getId());
                randomAccessFile.writeInt(admin.getEmployeeNumber());
                randomAccessFile.writeInt(admin.getTelephoneNumber());
                randomAccessFile.writeInt(admin.getCategoryType());
                randomAccessFile.writeBoolean(admin.isCollegeDegree());
                randomAccessFile.writeDouble(admin.getSalary());

                return true;
            }
        } else {
            System.err.print("7002 position is "
                    + "out of bounds of this file");
            return false;
        }

    }//fin metodo

    public boolean putValueDriver(int position, Driver driver) throws IOException {
        //una pequenna validacion antes de insertar
        if (position >= 0 && position <= regsQuantity) {
            if (driver.size() > regSize) {
                System.err.print("7001 record size is out of bounds");
                return false;
            } else {
                //escribimos en archivo
                randomAccessFile.seek(position * regSize);
                randomAccessFile.writeUTF(driver.getName());
                randomAccessFile.writeUTF(driver.getLastName());
                randomAccessFile.writeInt(driver.getId());
                randomAccessFile.writeInt(driver.getEmployeeNumber());
                randomAccessFile.writeBoolean(driver.isCollegeDegree());
                randomAccessFile.writeUTF(driver.getTurn());

                return true;
            }
        } else {
            System.err.print("7002 position is "
                    + "out of bounds of this file");
            return false;
        }

    }//fin metodo

    public boolean putValueJanitor(int position, Janitor janitor) throws IOException {
        //una pequenna validacion antes de insertar
        if (position >= 0 && position <= regsQuantity) {
            if (janitor.size() > regSize) {
                System.err.print("7001 record size is out of bounds");
                return false;
            } else {
                //escribimos en archivo
                randomAccessFile.seek(position * regSize);
                randomAccessFile.writeUTF(janitor.getName());
                randomAccessFile.writeUTF(janitor.getLastName());
                randomAccessFile.writeInt(janitor.getId());
                randomAccessFile.writeInt(janitor.getEmployeeNumber());
                randomAccessFile.writeBoolean(janitor.isCollegeDegree());
                randomAccessFile.writeInt(janitor.getAge());
                randomAccessFile.writeDouble(janitor.getYearsExperience());
                return true;
            }
        } else {
            System.err.print("7002 position is "
                    + "out of bounds of this file");
            return false;
        }

    }//fin metodo

    public boolean putValueVehicle(int position, vehicle Vehicle_) throws IOException {
        //una pequenna validacion antes de insertar
        if (position >= 0 && position <= regsQuantity) {
            if (Vehicle_.size() > regSize) {
                System.err.print("7001 record size is out of bounds");
                return false;
            } else {
                //escribimos en archivo
                randomAccessFile.seek(position * regSize);
                randomAccessFile.writeUTF(Vehicle_.getName());
                randomAccessFile.writeFloat(Vehicle_.getMileage());
                randomAccessFile.writeInt(Vehicle_.getYear());
                randomAccessFile.writeInt(Vehicle_.getSerie());

                return true;
            }
        } else {
            System.err.print("7002 position is "
                    + "out of bounds of this file");
            return false;
        }

    }//fin metodo

    public boolean addEndRecord(Employee employee) throws IOException {
        //insertar al final del archivo
        boolean success = putValue(regsQuantity, employee);

        if (success) {
            ++regsQuantity;
        }
        return success;
    }

    public boolean addEndRecordAdmin(Admin admin) throws IOException {
        //insertar al final del archivo
        boolean success = putValueAdmin(regsQuantity, admin);

        if (success) {
            ++regsQuantity;
        }
        return success;
    }

    public boolean addEndRecordDriver(Driver driver) throws IOException {
        //insertar al final del archivo
        boolean success = putValueDriver(regsQuantity, driver);

        if (success) {
            ++regsQuantity;
        }
        return success;
    }

    public boolean addEndRecordJanitor(Janitor janitor) throws IOException {
        //insertar al final del archivo
        boolean success = putValueJanitor(regsQuantity, janitor);

        if (success) {
            ++regsQuantity;
        }
        return success;
    }

    public boolean addEndRecordVehicle(vehicle vehicle_) throws IOException {
        //insertar al final del archivo
        boolean success = putValueVehicle(regsQuantity, vehicle_);

        if (success) {
            ++regsQuantity;
        }
        return success;
    }

    public Employee getEmployee(int position) throws IOException {
        //validacion de la posicion
        if (position >= 0 && position <= regsQuantity) {
            //colocamos el puntero en el lugar 
            randomAccessFile.seek(position * regSize);

            //instancia de person
            Employee myEmployee = new Employee();

            //llevamos a cabo las lecturas
            myEmployee.setName(randomAccessFile.readUTF());
            myEmployee.setLastName(randomAccessFile.readUTF());
            myEmployee.setId(randomAccessFile.readInt());
            myEmployee.setEmployeeNumber(randomAccessFile.readInt());

            //si es delete no retorno
            if (myEmployee.getName().equalsIgnoreCase("delete")) {
                return null;
            } else {
                return myEmployee;
            }

        } else {
            System.err.println("6001 position is out of bounds");
            return null;
        }
    }//fin de metodo

    public int searchEmployee(String search) throws IOException {
        String name;
        if (search == null) {
            return -1;
        }
        for (int i = 0; i < regsQuantity; i++) {
            randomAccessFile.seek(i * regSize);
            name = getEmployee(i).getName();
            if (name.equals(search)) {
                return i;
            }
        }
        return -1;
    }

    public List<Employee> getAllEmployees() throws IOException {

        //variables a retornar
        List<Employee> employee = new ArrayList<Employee>();
        try {
            //recorro todos mis registros y los inserto en la lista
            for (int i = 0; i < regsQuantity; i++) {
                Employee employeeTemp = this.getEmployee(i);

                if (employeeTemp != null) {
                    employee.add(employeeTemp);
                }
            }

        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, "Error al leer los datos");
        }
        return employee;
    }//fin metodo

    public boolean deleteRecord(String name) throws IOException {
        Employee myEmployee;

        //buscar el registro para la eliminacion
        for (int i = 0; i < regsQuantity; i++) {

            //obtengo a la persona de esa posicion
            myEmployee = this.getEmployee(i);

            //pregunto si es la persona que quiero eliminar
            if (myEmployee.getName().equalsIgnoreCase(name)) {

                //marcar esta persona como eliminada
                myEmployee.setName("deleted");

                return this.putValue(i, myEmployee);
            }
        }

        //si llega a este punto no encontro a la persona
        return false;
    }

}
