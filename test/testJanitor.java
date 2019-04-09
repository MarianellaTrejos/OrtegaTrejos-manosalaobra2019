
import domain.Janitor;
import file.JanitorFile;
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
public class testJanitor {

    //variables
    public static JanitorFile janitorFile;
    public static Janitor janitorPeter, janitorNikki,
            janitorJohn;
    public File fileJanitor;

    public testJanitor() throws IOException {
//        fileJanitor = new File("./Employee.dat");
//        janitorFile = new JanitorFile(fileJanitor);
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
    // @Test 
    public void add() throws IOException {
        //se crea el archivo
        fileJanitor = new File("./Employee.dat");

        //instanciamos las personas
//        janitorJohn = new Janitor("John", "Tarantusa", 700.000   ,3000000,32,true,3,27);
//        janitorNikki = new Janitor("Nikki", "Maliski",800.000  ,305140324,31,false,1,30);
//        janitorPeter = new Janitor("Peter", "Malwiisko",100.000 ,400000,30,true,8,35);
//        
//        //insertamos en el archivo
//        
//        janitorFile.addEndRecord(janitorJohn);
//        janitorFile.addEndRecord(janitorNikki);
//        janitorFile.addEndRecord(janitorPeter);
    }

    @Test
    public void printAll() throws IOException {
        try {
            JanitorFile.createFileJanitor(new File("Janitor.dat"));

            System.out.println(JanitorFile.getAllJanitors().toString());
            JanitorFile.close();

        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, "Error en la búsqueda de registros.", "Error", JOptionPane.ERROR_MESSAGE);
        }

//        List<Janitor> janitor = janitorFile.getAllJanitors();
//        
//        //recorremos con un foreach
//        for(Janitor currentJanitor: janitor){
//            System.out.println(currentJanitor.toString());
//        }
    }

    //@Test
    public void deletePerson() throws IOException, InterruptedException {

        //eliminar
//        janitorFile.deleteRecord("John");
        System.out.println();
        System.out.println();
        System.out.println();
        printAll();

    }
}
