package file;

import java.io.File;
import domain.*;
import java.io.IOException;
import java.io.RandomAccessFile;

public class EmployeeFile1 {

    private static RandomAccessFile flujo;
    private static int numeroRegistros;
    private static int tamañoRegistro = 80;

    public static void crearFileAlumno(File archivo) throws IOException {
        if (archivo.exists() && !archivo.isFile()) {
            throw new IOException(archivo.getName() + " no es un archivo");
        }
        flujo = new RandomAccessFile(archivo, "rw");
        numeroRegistros = (int) Math.ceil(
                (double) flujo.length() / (double) tamañoRegistro);
    }

    public static void cerrar() throws IOException {
        flujo.close();
    }

    public static boolean setEmployee(int i, Employee employee) throws IOException {
        if(i >= 0 && i <= getNumeroRegistros()) {
            if(employee.size() > tamañoRegistro) {
                System.out.println("\nTamaño de registro excedido.");
            } else {
                flujo.seek(i*tamañoRegistro);
                flujo.writeUTF(employee.getName());
                flujo.writeInt(employee.getId());
                flujo.writeBoolean(employee.isCollegeDegree());
                return true;
            }
        } else {
            System.out.println("\nNúmero de registro fuera de límites.");
        }
        return false;
    }

    private static int buscarRegistroInactivo() throws IOException {
        String nombre;
        for(int i=0; i<getNumeroRegistros(); i++) 
        {
            flujo.seek(i * tamañoRegistro);
            if(!getEmployee(i).isCollegeDegree()) 
                return i;
        }
        return -1;        
    }
    
    public static boolean eliminarPersona(String aEliminar) throws IOException {
        int pos = buscarRegistro(aEliminar);
        if(pos == -1) return false;
        Employee personaEliminada = getEmployee(pos);
        personaEliminada.setCollegeDegree(false);
        setEmployee(pos, personaEliminada);
        return true;
    }
    
    public static void compactarArchivo(File archivo) throws IOException {
        crearFileAlumno(archivo); // Abrimos el flujo.
        Employee[] listado = new Employee[numeroRegistros];
        for(int i=0; i<numeroRegistros; ++i)
            listado[i] = getEmployee(i);
        cerrar(); // Cerramos el flujo.
        archivo.delete(); // Borramos el archivo.

        File tempo = new File("temporal.dat");
        crearFileAlumno(tempo); // Como no existe se crea.
        for(Employee e : listado)
            if(e.isCollegeDegree())
                añadirEmployee(e);
        cerrar();
        
        tempo.renameTo(archivo); // Renombramos.
    }
    
    public static void añadirEmployee(Employee employee) throws IOException {
        int inactivo = buscarRegistroInactivo();
        if(setEmployee(inactivo==-1?numeroRegistros:inactivo, employee)) 
            numeroRegistros++;        
    }

    public static int getNumeroRegistros() {
        return numeroRegistros;
    }

    public static Employee getEmployee(int i) throws IOException {
        if(i >= 0 && i <= getNumeroRegistros()) {
            flujo.seek(i * tamañoRegistro);
            return new Employee(flujo.readUTF(),flujo.readUTF(),flujo.readDouble(), flujo.readInt(),flujo.readInt(), flujo.readBoolean());
        } else {
            System.out.println("\nNúmero de registro fuera de límites.");
            return null;
        }
    }

    public static int buscarRegistro(String buscado) throws IOException {
        Employee e;
        if (buscado == null) {
            return -1;
        }
        for(int i=0; i<getNumeroRegistros(); i++) {
            flujo.seek(i * tamañoRegistro);
            e = getEmployee(i);
            if(e.getName().equals(buscado) && e.isCollegeDegree()) {
                return i;
            }
        }
        return -1;
    }
    
}