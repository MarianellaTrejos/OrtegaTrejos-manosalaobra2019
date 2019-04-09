import domain.Admin;
import domain.Admin;
import file.AdminFile;
import file.AdminFile;
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
public class testAdmin {
    //variables
    public static AdminFile adminfile;
    public static Admin adminPeter, adminNikki, 
            adminJohn;
    public File fileAdmin;
    public testAdmin() throws IOException {
        fileAdmin= new File("./Admin.dat");
//        adminfile = new AdminFile(fileAdmin);
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
    //@Test 
    public void add() throws IOException{
        //se crea el archivo
        fileAdmin = new File("./admin.dat");
        
//        //instanciamos las personas
//        adminJohn = new Admin("John", "Tarantusa", 700.000   ,3000000,32,true,120002200,1);
//        adminNikki = new Admin("Nikki", "Maliski",800.000  ,305140324,31,false,555255555,2);
//        adminPeter = new Admin("Peter", "Malwiisko",100.000 ,400000,30,true,333333333,3);
//        
//        //insertamos en el archivo
//        
//        adminfile.addEndRecord(adminJohn);
//        adminfile.addEndRecord(adminNikki);
//        adminfile.addEndRecord(adminPeter);
    }
    
//     @Test
    public void printAll() throws IOException{
        
        try {
            AdminFile.createFileAdmin(new File("Admin.dat"));

            System.out.println(AdminFile.getAllAdmin().toString());
            AdminFile.close();

        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, "Error en la búsqueda de registros.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
    
    @Test
    public void deletePerson() throws IOException, InterruptedException{
        
        //eliminar
//        adminfile.deleteRecord("Peter");
        
        try {
            AdminFile.createFileAdmin(new File("Admin.dat"));
            AdminFile.deleteRecord("Marianella");
            AdminFile.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error en la eliminación de registros.", "Error", JOptionPane.ERROR_MESSAGE);
        }        
        
    }
}
