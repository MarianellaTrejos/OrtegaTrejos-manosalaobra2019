

import domain.Driver;
import file.DriverFile;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author maria
 */
public class testDriver {
    //variables
    public static DriverFile driverFile;
    public static Driver driverPeter, driverNikki, 
            driverJohn;
    public File fileDriver;
    public testDriver() throws IOException {
//        fileDriver = new File("./driver.dat");
//        driverFile = new DriverFile(fileDriver);
    }
    
    @BeforeClass
    public static void setUpClass() throws IOException {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
//    @Test 
    public void add() throws IOException{
//        //se crea el archivo
//        fileDriver = new File("./driver.dat");
//        
//        
//        
//        
//        
//        
//        //instanciamos las personas
//        driverJohn = new Driver("John", "Tarantusa", 700.000   ,3000000,32,true,true,1);
//        driverNikki = new Driver("Nikki", "Maliski",800.000  ,305140324,31,false,false,2);
//        driverPeter = new Driver("Peter", "Malwiisko",100.000 ,400000,30,true,true,3);
//        
//        //insertamos en el archivo
//        
//        driverFile.addEndRecord(driverJohn);
//        driverFile.addEndRecord(driverNikki);
//        driverFile.addEndRecord(driverPeter);
    }
    
     @Test
    public void printAll() throws IOException{
         try {
            DriverFile.createFileDriver( new File("Driver.dat") );
            
             System.out.println(DriverFile.getAllDrivers().toString()+"\n");
            DriverFile.close();
    
         } catch(IOException ioe) {
            JOptionPane.showMessageDialog(null, "Error en la búsqueda de registros.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    //@Test
    public void deletePerson() throws IOException, InterruptedException{
        
//        //eliminar
//        driverFile.deleteRecord("John");
//        
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        printAll();
        
    }
    
//    @Test 
    public void searchDriver() throws IOException {
        try {
            DriverFile.createFileDriver( new File("Driver.dat") );
            int i = DriverFile.searchRegister("Hola");
            if(i==-1) {
                JOptionPane.showMessageDialog(null, "Ningún registro coincide con los datos de búsqueda.", "Advertencia", JOptionPane.WARNING_MESSAGE);             
                return;
            }
            JOptionPane.showMessageDialog(null, "La primera coincidencia indica: "+DriverFile.getDriver(i), "Notificación", JOptionPane.INFORMATION_MESSAGE);
            DriverFile.close();
        } catch(IOException e) {
            JOptionPane.showMessageDialog(null, "Error en la búsqueda de registros.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
