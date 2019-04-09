
import domain.vehicle;
import file.VehicleFile;
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
public class testVehicle {

    public static VehicleFile vehiclefile;
    public static vehicle vehicleUno, vehicleDos, vehicleTres;
    public File fileVehicle;

    public testVehicle() throws IOException {
//          fileVehicle= new File("./Vehicle.dat");
//        vehiclefile = new VehicleFile(fileVehicle);
    }

    @BeforeClass
    public static void setUpClass() {

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
//     public void hello() {}
//    @Test 
    public void add() throws IOException {
        //se crea el archivo
        fileVehicle = new File("./Vehicle.dat");

        try {
                    VehicleFile.createFilevehicle(new File("Vehicle.dat"));
                    VehicleFile.addvehicle(new vehicle(12345, "Chevrolet", 2019,true, 654, 1000, true));
                    VehicleFile.close();
                } catch (IOException ioe) {
                    JOptionPane.showMessageDialog(null, "Error al ingresar los datos");
                }
    }

//    @Test
    public void printAll() throws IOException {
        try {
            VehicleFile.createFilevehicle(new File("Vehicle.dat"));

            System.out.println(VehicleFile.getAllvehicle().toString());
            VehicleFile.close();

        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, "Error en la búsqueda de registros.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Test
    public void deletePerson() throws IOException, InterruptedException {

        //eliminar
//        vehiclefile.deleteRecord(3);
        System.out.println();
        System.out.println();
        System.out.println();
        printAll();

    }
}
