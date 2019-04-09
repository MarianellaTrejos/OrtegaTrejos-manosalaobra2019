package InterfaceProyect;

import domain.Admin;
import domain.Driver;
import domain.Employee;
import domain.Janitor;
import domain.vehicle;
import file.AdminFile;
import file.DriverFile;
import file.EmployeeFile;
import file.EmployeeFile1;
import file.JanitorFile;

import file.VehicleFile;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.RandomAccess;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javax.swing.JOptionPane;

public class Interfaz1 {

    public static EmployeeFile employeeFile;
    VehicleFile v;
    EmployeeFile ef;
    private String nombre;
    Employee e = new Employee();
    VBox vbWindows, vbMain;
    File f1 = new File("Employee");
    public static JanitorFile janitorFile;
    public File fileJanitor;
    public static AdminFile adminfile;
    public File fileAdmin;
    public static DriverFile driverFile;
    public File fileDriver;
    public static VehicleFile vehiclefile;
    public File fileVehicle;

    public Interfaz1() throws IOException {

    }

    public GridPane getInterfaceEmployee() {

        Employee em[] = new Employee[10];
        GridPane GpInsert = new GridPane();
        GpInsert.setVgap(15);
        GpInsert.setHgap(15);
        GpInsert.setMinSize(600, 500);
        GpInsert.setStyle("-fx-background-color:pink;");
        GpInsert.setAlignment(Pos.CENTER);
        Label lblname = new Label("Nombre");
        GpInsert.add(lblname, 0, 1);
        TextField tfname = new TextField();
        GpInsert.add(tfname, 1, 1);

        Label lblLastName = new Label("Apellidos");
        GpInsert.add(lblLastName, 0, 3);
        TextField tfLastName = new TextField();
        GpInsert.add(tfLastName, 1, 3);

        Label lblId = new Label("ID");
        GpInsert.add(lblId, 0, 4);
        TextField tfId = new TextField();
        GpInsert.add(tfId, 1, 4);

        Label lblNumEmployee = new Label("Numero de Empleado");
        GpInsert.add(lblNumEmployee, 0, 5);
        TextField tfNumEmployee = new TextField();
        GpInsert.add(tfNumEmployee, 1, 5);
        Label lblCollegeDe = new Label("Titulo Universitario");
        GpInsert.add(lblCollegeDe, 0, 6);
        TextField tfCollegeDe = new TextField();
        GpInsert.add(tfCollegeDe, 1, 6);

        Label lblProfesion = new Label("Profesión");
        GpInsert.add(lblProfesion, 0, 7);
        TextField tfProfesion = new TextField();
        GpInsert.add(tfProfesion, 1, 7);

        Button btnAcept = new Button("Insertar");
        GpInsert.add(btnAcept, 0, 7);
        Label lblNotification = new Label("Empleado insertado con éxito");//, new ImageView(new Image("guardar.png")));
        lblNotification.setVisible(false);
        GpInsert.add(lblNotification, 0, 9);

        Button btnCancel = new Button("Cancelar");
        GpInsert.add(btnCancel, 3, 7);
        Label lblError = new Label();
        GpInsert.add(lblError, 0, 8);
        //ImageView imagenContacto=new ImageView(new Image("insertar.png"));
        // GpInsert.add(imagenContacto,3,0);
        btnAcept.setOnAction((event) -> {
            String nameInsert = tfname.getText();

            String lastName = tfLastName.getText();
            String id = tfId.getText();
            String numEmployee = tfNumEmployee.getText();
            int numempl = Integer.parseInt(numEmployee);
            String collegeDe = tfCollegeDe.getText();
            String profesion = tfProfesion.getText();
            int id2 = Integer.parseInt(id);
            if (!nameInsert.equals("") && !lastName.equals("") && !id.equals("") && !numEmployee.equals("")) {

                tfname.setText("");
                tfLastName.setText("");
                tfId.setText("");
                tfNumEmployee.setText("");
                tfCollegeDe.setText("");

                lblError.setText("");
                lblNotification.setVisible(true);
            } else {
                lblError.setStyle("-fx-text-fill: red;-fx-font-size: 12px; ");
                lblError.setText("Debe completar todos los elementos para guardar");
            }
        });
        btnCancel.setOnAction(((event) -> {
            tfname.setText("");
            tfLastName.setText("");
            tfId.setText("");
            tfNumEmployee.setText("");
            tfCollegeDe.setText("");

            lblError.setText("");
            vbWindows.getChildren().clear();
        }));

        return GpInsert;
    }
//Contenedor principal que contiene el menú y la entrada principal

    public Scene getSceneMenu() {
        vbMain = new VBox();
        vbMain.setStyle("-fx-background-image:url(logo.jpg);" + "-fx-background-position:center;" + "-fx-background-repeat:no-repeat");
        Scene Scene = new Scene(vbMain, 900, 500);
        Scene.setUserData("Bienvenido(a)");
        vbWindows = new VBox();
        MenuBar mBMenu = new MenuBar();
        //Menu Inicio
        Menu mStart = new Menu("Inicio");

        //SubMenu Inicio
        MenuItem mIStart = new MenuItem("Inicio");
        mIStart.setOnAction((event) -> {
            vbWindows.getChildren().clear();

        });

        //Menu Sistema
        Menu mExit = new Menu("Salir");

        MenuItem mIExit = new MenuItem("Salir");//, new ImageView(new Image("salir.png")));

        //agregarle un metodo rápido con teclas para accionarlo
        mIExit.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
        mIExit.setOnAction((event) -> Platform.exit());

        Menu mvehicle = new Menu("Vehículo");
        MenuItem mIVehicle = new MenuItem("Vehiculo");
        mIVehicle.setOnAction(((event) -> {
            vbWindows.getChildren().clear();
            vbWindows.getChildren().addAll(getInterfacevehicle());
        }));
        //Visualizar vehiculis en un tableView
        MenuItem mISeeV = new MenuItem("Visualizar vehiculo");
        mISeeV.setOnAction(((event) -> {
            vbWindows.getChildren().clear();

            try {
                vbWindows.getChildren().addAll(viewVehicle());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al abrir el archivo");
            }
        }));
        //Menú con pestaña para eliminar empleado
        MenuItem mIDelete = new MenuItem("Eliminar");
        mIDelete.setOnAction(((event) -> {
            vbWindows.getChildren().clear();
            vbWindows.getChildren().addAll(gettypeEmployeeDelete());
        }));

        MenuItem mIDeleteV = new MenuItem("Eliminar");
        mIDeleteV.setOnAction(((event) -> {
            vbWindows.getChildren().clear();
            vbWindows.getChildren().addAll(getVehicleDelete());
        }));
        //Menu Empleado
        Menu mEmployee = new Menu("Empleado");

        //SubMenu 
        MenuItem mIEmployee = new MenuItem("Agregar");//, new ImageView(new Image("agregar.png")));
        mIEmployee.setOnAction(((event) -> {
            vbWindows.getChildren().clear();
            vbWindows.getChildren().addAll(gettypeEmployee());

        }));

        MenuItem mIShow = new MenuItem("Mostrar empleados");
        mIShow.setOnAction(((event) -> {
            vbWindows.getChildren().clear();
            vbWindows.getChildren().addAll(getViewEmployee());
        }));

        MenuItem mIEmployee1 = new MenuItem("Visualizar");
        mIEmployee1.setOnAction(((event) -> {
            vbWindows.getChildren().clear();
            vbWindows.getChildren().addAll(getViewEmployee());
        }));

        mStart.getItems().addAll(mIStart);
        mEmployee.getItems().addAll(mIEmployee, mIEmployee1, mIDelete);
        mvehicle.getItems().addAll(mIVehicle);

        //Agregar los menuItem en el menu 
        mExit.getItems().addAll(mIExit);
        mvehicle.getItems().addAll(mISeeV, mIDeleteV);
        mBMenu.getMenus().addAll(mStart, mEmployee, mvehicle, mExit);
        ((VBox) Scene.getRoot()).getChildren().addAll(mBMenu, vbWindows);
        return Scene;
    }

    public GridPane getseeEmployee() {
        GridPane GpInsert = new GridPane();
        GpInsert.setVgap(15);
        GpInsert.setHgap(15);
        GpInsert.setMinSize(600, 500);
        GpInsert.setAlignment(Pos.CENTER);
//
//        Label lblTitle = new Label("Digite el nombre del empleado que desea visualizar(Marianella,Winston,Andrés)");
//        GpInsert.add(lblTitle, 0, 0);
//
//        TextField txtName = new TextField();
//        GpInsert.add(txtName, 0, 1);
//
//        TextArea ta = new TextArea();
//        Button btnAccept = new Button("Aceptar");
//        GpInsert.add(btnAccept, 0, 2);
//        Button btnCancel = new Button("Cancelar");
//        GpInsert.add(btnCancel, 1, 2);
//        btnCancel.setOnAction((event) -> {
//
//            txtName.setText("");
//            ta.setText("");
//            ta.setVisible(false);
//
//        });

//        btnAccept.setOnAction((event) -> {
//
//            if (txtName.getText().toLowerCase().equals("marianella")) {
//
//                ta.setVisible(true);
//                ta.setText("Nombre:Marianella\nApellido: Trejos\nID:305140324\nNúmero de empleado: 2 \nTitulo universitario: Sí\nPuesto:Conserje\n Salario: $700");
//                ta.setEditable(false);
//                ta.setMaxSize(200, 200);
//                GpInsert.add(ta, 0, 3);
//
//            } else if (txtName.getText().toString().equals("winston")) {
//
//                ta.setVisible(true);
//                ta.setText("Nombre:Winston\nApellido: Ortega\nID:117440877\nNúmero de empleado: 540 \nTitulo universitario: No\n Puesto:Conductor\nSalario: $300");
//                ta.setEditable(false);
//                ta.setMaxSize(200, 200);
//                GpInsert.add(ta, 0, 3);
//            } else if (txtName.getText().toLowerCase().equals("andres")) {
//
//                ta.setVisible(true);
//                ta.setText("Nombre:Andrés\nApellido: Rivera\nID:457811235\nNúmero de empleado: 1400 \nTitulo universitario: Si\nPuesto:Admin \nSalario: $150");
//                ta.setEditable(false);
//                ta.setMaxSize(200, 200);
//                GpInsert.add(ta, 0, 3);
//            }
//        });
        return GpInsert;
    }

    //metodo que solicita el tipo de empleado
    public GridPane getViewEmployee() {
        GridPane GpInsert = new GridPane();
        GpInsert.setVgap(15);
        GpInsert.setHgap(15);
        GpInsert.setMinSize(600, 500);
        GpInsert.setStyle("-fx-background-color:lightblue;");
        GpInsert.setAlignment(Pos.CENTER);
        Label lblTitle = new Label("VISUALIZAR EMPLEADO");

        lblTitle.setFont(Font.font("Cambria", 20));
        GpInsert.add(lblTitle, 1, 0);

        final ComboBox viewEmployee = new ComboBox();
        viewEmployee.setValue("--Seleccionar--");
        viewEmployee.getItems().addAll("Administrador", "Conserje", "Conductor");

        GpInsert.add(viewEmployee, 1, 1);
        Button btnAccept = new Button("Aceptar");
        GpInsert.add(btnAccept, 2, 2);

        btnAccept.setOnAction((event) -> {
            if (viewEmployee.getValue().toString().toLowerCase().equals("administrador")) {
                vbWindows.getChildren().clear();
                try {
                    vbWindows.getChildren().add(viewAdmin());
                } catch (IOException ex) {
                    System.out.println("Error al leer los datos");
                }

            } else if (viewEmployee.getValue().toString().toLowerCase().equals("conserje")) {

                vbWindows.getChildren().clear();
                try {
                    vbWindows.getChildren().add(viewJanitor());
                } catch (Exception ex) {
                    System.out.println("Error al leer los datos");
                }
            } else if (viewEmployee.getValue().toString().toLowerCase().equals("conductor")) {

                vbWindows.getChildren().clear();
                try {
                    vbWindows.getChildren().add(viewDriver());
                } catch (Exception ex) {
                    System.out.println("Error al leer los datos");
                }
            }
        });
        //falta llenarlo con los empleados
        return GpInsert;

    }

    public GridPane gettypeEmployee() {
        GridPane GpInsert = new GridPane();
        GpInsert.setVgap(15);
        GpInsert.setHgap(15);
        GpInsert.setMinSize(600, 500);
        GpInsert.setStyle("-fx-background-color:lightblue;");
        GpInsert.setAlignment(Pos.CENTER);
        Label lblTitle = new Label("AGREGAR EMPLEADO");

        lblTitle.setFont(Font.font("Cambria", 20));
        GpInsert.add(lblTitle, 1, 0);

        Label lblTitle1 = new Label("Digite el tipo de empleado que desea agregar");
        GpInsert.add(lblTitle1, 1, 3);
        final ComboBox typeEmployee = new ComboBox();
        typeEmployee.setValue("--Seleccionar--");
        typeEmployee.getItems().addAll(
                "Conductor",
                "Conserje",
                "Administrador"
        );

        GpInsert.add(typeEmployee, 1, 4);

//        
//        TextField txt_name=new TextField();
//        GpInsert.add(txt_name,0,1);
        TextArea ta = new TextArea();
        Button btnAccept = new Button("Aceptar");
        GpInsert.add(btnAccept, 4, 5);

////        Button btn_cancelar=new Button("Borrar datos");
////        GpInsert.add(btn_cancelar,1,2); 
////        btn_cancelar.setOnAction((event) -> {
////           
//            
//            ta.setText("");
//            ta.setVisible(false);
//            
//       });
//        
        btnAccept.setOnAction((event) -> {
            if (typeEmployee.getValue().toString().toLowerCase().equals("administrador")) {
                vbWindows.getChildren().clear();
                vbWindows.getChildren().add(getAdmin());

            } else if (typeEmployee.getValue().toString().toLowerCase().equals("conserje")) {

                vbWindows.getChildren().clear();
                vbWindows.getChildren().add(getJanitor());
            } else if (typeEmployee.getValue().toString().toLowerCase().equals("conductor")) {

                vbWindows.getChildren().clear();
                vbWindows.getChildren().add(getDriver());
            }
        });

        return GpInsert;
    }

    //metodo que selecciona un tipo de empleado para eliminar
    public GridPane gettypeEmployeeDelete() {
        GridPane GpInsert = new GridPane();
        GpInsert.setVgap(15);
        GpInsert.setHgap(15);
        GpInsert.setMinSize(600, 500);
        GpInsert.setStyle("-fx-background-color:lightblue;");
        GpInsert.setAlignment(Pos.CENTER);
        Label lblTitle = new Label("ELIMINAR EMPLEADO");

        lblTitle.setFont(Font.font("Cambria", 20));
        GpInsert.add(lblTitle, 1, 0);

        Label lblTitle1 = new Label("Elija el tipo de empleado que desea eliminar");
        GpInsert.add(lblTitle1, 1, 3);
        final ComboBox typeEmployee = new ComboBox();
        typeEmployee.setValue("--Seleccionar--");
        typeEmployee.getItems().addAll(
                "Conductor",
                "Conserje",
                "Administrador"
        );

        GpInsert.add(typeEmployee, 1, 4);

        TextArea ta = new TextArea();
        Button btnAccept = new Button("Aceptar");
        GpInsert.add(btnAccept, 4, 5);

        btnAccept.setOnAction((event) -> {
            if (typeEmployee.getValue().toString().toLowerCase().equals("administrador")) {
                vbWindows.getChildren().clear();
                vbWindows.getChildren().add(getDeleteAdmin());

            } else if (typeEmployee.getValue().toString().toLowerCase().equals("conserje")) {

                vbWindows.getChildren().clear();
                vbWindows.getChildren().add(getDeleteJanitor());
            } else if (typeEmployee.getValue().toString().toLowerCase().equals("conductor")) {

                vbWindows.getChildren().clear();
                vbWindows.getChildren().add(getDeleteDriver());
            }
        });

        return GpInsert;
    }

    //metodo que agrega un administrador a la lista
    public GridPane getAdmin() {
        Admin a = new Admin();
        Employee em[] = new Employee[10];
        GridPane GpInsert = new GridPane();
        GpInsert.setVgap(15);
        GpInsert.setHgap(15);
        GpInsert.setMinSize(600, 500);
        GpInsert.setStyle("-fx-background-color:lightblue;");
        GpInsert.setAlignment(Pos.CENTER);
        Label lblTitle = new Label("AGREGAR ADMINISTRADOR");

        lblTitle.setFont(Font.font("Cambria", 20));
        GpInsert.add(lblTitle, 1, 0);

        Label lblName = new Label("Nombre");
        GpInsert.add(lblName, 0, 1);
        TextField tfName = new TextField();
        GpInsert.add(tfName, 1, 1);

        Label lblLastName = new Label("Apellidos");
        GpInsert.add(lblLastName, 0, 3);
        TextField tfLastName = new TextField();
        GpInsert.add(tfLastName, 1, 3);

        Label lblId = new Label("ID");
        GpInsert.add(lblId, 0, 4);
        TextField tfId = new TextField();
        GpInsert.add(tfId, 1, 4);

        Label lblNumEmployee = new Label("Numero de Empleado");
        GpInsert.add(lblNumEmployee, 0, 5);
        TextField tfNumEmployee = new TextField();
        GpInsert.add(tfNumEmployee, 1, 5);

        final ComboBox collegeDegree = new ComboBox();
        collegeDegree.setValue("--Seleccionar--");
        collegeDegree.getItems().addAll(
                "Sí",
                "No"
        );

        GpInsert.add(collegeDegree, 1, 6);

        Label lblCollegeDe = new Label("Titulo Universitario");
        GpInsert.add(lblCollegeDe, 0, 6);

        Label lblnumberP = new Label("Número de telefono");
        GpInsert.add(lblnumberP, 0, 8);
        TextField tfnumberP = new TextField();
        GpInsert.add(tfnumberP, 1, 8);

        Label lblCategory = new Label("Categoria");
        GpInsert.add(lblCategory, 0, 9);
        TextField tfCategory = new TextField();
        GpInsert.add(tfCategory, 1, 9);

        Button btnAccept = new Button("Insertar");
        GpInsert.add(btnAccept, 0, 11);
        Label lblNotification = new Label("Empleado insertado con éxito");
        lblNotification.setVisible(false);
        GpInsert.add(lblNotification, 0, 12);

        Button btnErase = new Button("Borrar Datos");
        GpInsert.add(btnErase, 2, 11);
        Button btnCancel = new Button("Cancelar");
        GpInsert.add(btnCancel, 3, 11);
        Label lblError = new Label();
        GpInsert.add(lblError, 0, 13);

        btnAccept.setOnAction((event) -> {
            if (!tfName.getText().equals("") && !tfLastName.getText().equals("") && !tfId.getText().equals("") && !tfNumEmployee.getText().equals("") && !collegeDegree.getValue().toString().equals("--Seleccionar--") && !tfnumberP.getText().equals("") && !tfCategory.getText().equals("")) {
                String NameInsert = tfName.getText();
                a.setName(NameInsert);
                String lastName = tfLastName.getText();
                a.setLastName(lastName);
                String id = tfId.getText();
                int id1 = Integer.parseInt(id);
                a.setId(id1);
                String numEmployee = tfNumEmployee.getText();
                int numempl = Integer.parseInt(numEmployee);
                a.setEmployeeNumber(numempl);
                String collegeDe = collegeDegree.getValue().toString();
                boolean coll = Boolean.parseBoolean(collegeDe);
                if (collegeDe.contains("Sí")) {
                    a.setCollegeDegree(true);
                    coll = true;
                } else {
                    a.setCollegeDegree(false);
                    coll = false;
                }

                String numberP = tfnumberP.getText();
                int numberP1 = Integer.parseInt(numberP);
                a.setTelephoneNumber(numberP1);
                String Category = tfCategory.getText();
                int categoryE = Integer.parseInt(Category);
                a.setCategoryType(categoryE);

                double sal = a.calculateSalary(categoryE, coll);
                a.setSalary(sal);

                try {

                    AdminFile.createFileAdmin(new File("Admin.dat"));
                    AdminFile.addAdmin(new Admin(NameInsert, lastName, sal, id1, numempl, coll, numberP1, categoryE, true));
                    AdminFile.close();

                } catch (IOException ioe) {
                    JOptionPane.showMessageDialog(null, "Error al ingresar los datos");
                }

                tfName.setText("");
                tfLastName.setText("");
                tfId.setText("");
                tfNumEmployee.setText("");
                collegeDegree.setValue("--Seleccionar--");
                tfCategory.setText("");

                tfnumberP.setText("");
                lblError.setText("");

                lblNotification.setVisible(true);
            } else {
                lblError.setVisible(true);
                lblError.setStyle("-fx-text-fill: red;-fx-font-size: 12px; ");
                lblError.setText("Debe completar todos los elementos para guardar");
            }

        });
        btnCancel.setOnAction(((event) -> {
            tfName.setText("");
            tfLastName.setText("");
            tfId.setText("");
            tfNumEmployee.setText("");
            collegeDegree.setValue("--Seleccionar--");
            lblError.setText("");
            vbWindows.getChildren().clear();
            vbWindows.getChildren().add(gettypeEmployee());
        }));
        btnErase.setOnAction(((event) -> {
            tfName.setText("");
            tfLastName.setText("");
            tfId.setText("");
            tfNumEmployee.setText("");
            collegeDegree.setValue("--Seleccionar--");
            lblError.setText("");
            tfnumberP.setText("");
            tfCategory.setText("");

        }));

        return GpInsert;

    }
//metodo que agrega un conserje a la lista

    public GridPane getJanitor() {
        Janitor j = new Janitor();
        Employee em[] = new Employee[10];
        GridPane GpInsert = new GridPane();
        GpInsert.setVgap(15);
        GpInsert.setHgap(15);
        GpInsert.setMinSize(600, 500);
        GpInsert.setStyle("-fx-background-color:lightblue;");
        GpInsert.setAlignment(Pos.CENTER);
        Label lblTitle = new Label("AGREGAR CONSERJE");

        lblTitle.setFont(Font.font("Cambria", 20));
        GpInsert.add(lblTitle, 1, 0);

        Label lblName = new Label("Nombre");
        GpInsert.add(lblName, 0, 1);
        TextField tfName = new TextField();
        GpInsert.add(tfName, 1, 1);

        Label lblLastName = new Label("Apellidos");
        GpInsert.add(lblLastName, 0, 3);
        TextField tfLastName = new TextField();
        GpInsert.add(tfLastName, 1, 3);

        Label lblId = new Label("ID");
        GpInsert.add(lblId, 0, 4);
        TextField tfId = new TextField();
        GpInsert.add(tfId, 1, 4);

        Label lblNumEmployee = new Label("Numero de Empleado");
        GpInsert.add(lblNumEmployee, 0, 5);
        TextField tfNumEmployee = new TextField();
        GpInsert.add(tfNumEmployee, 1, 5);
        Label lblCollegeDe = new Label("Título Universitario");
        GpInsert.add(lblCollegeDe, 0, 6);
        final ComboBox collegeDegree = new ComboBox();
        collegeDegree.setValue("--Seleccionar--");
        collegeDegree.getItems().addAll(
                "Sí",
                "No"
        );

        GpInsert.add(collegeDegree, 1, 6);

        Label lblYearsExp = new Label("Años de experiencia");
        GpInsert.add(lblYearsExp, 0, 7);
        TextField tfYearsExp = new TextField();
        GpInsert.add(tfYearsExp, 1, 7);

        Label lblAge = new Label("Edad");
        GpInsert.add(lblAge, 0, 8);
        TextField tfAge = new TextField();
        GpInsert.add(tfAge, 1, 8);

        Label lblextrahours = new Label("Horas extra trabajadas");
        GpInsert.add(lblextrahours, 0, 9);
        TextField tfextrahours = new TextField();
        GpInsert.add(tfextrahours, 1, 9);

        Button btnAccept = new Button("Insertar");
        GpInsert.add(btnAccept, 0, 11);
        Label lblNotification = new Label("Empleado insertado con éxito");//, new ImageView(new Image("guardar.png")));
        lblNotification.setVisible(false);
        GpInsert.add(lblNotification, 0, 12);

        Button btnErase = new Button("Borrar Datos");
        GpInsert.add(btnErase, 2, 11);

        Button btnCancel = new Button("Cancelar");
        GpInsert.add(btnCancel, 3, 11);
        Label lblError = new Label();
        GpInsert.add(lblError, 0, 12);

        //accion del boton aceptar
        btnAccept.setOnAction((event) -> {

            String NameInsert = tfName.getText();
            j.setName(NameInsert);
            String LastName = tfLastName.getText();
            j.setLastName(LastName);
            String Id = tfId.getText();
            int id = Integer.parseInt(Id);
            j.setId(id);
            String numEmpleado = tfNumEmployee.getText();
            int numempl = Integer.parseInt(numEmpleado);
            j.setEmployeeNumber(numempl);
            String collegeDe = collegeDegree.getValue().toString();
            Boolean coll = Boolean.parseBoolean(collegeDe);
            if (collegeDe.toLowerCase().equals("sí")) {
                j.setCollegeDegree(coll);
                coll = true;
            } else {
                j.setCollegeDegree(coll);
                coll = false;
            }
            String yearE = tfYearsExp.getText();
            double yearEx = Double.parseDouble(yearE);
            j.setYearsExperience(yearEx);
            String age = tfAge.getText();
            int ageE = Integer.parseInt(age);
            j.setAge(ageE);
            String extra = tfextrahours.getText();
            int hours = Integer.parseInt(extra);
            double sal = j.calculateSalary(hours, coll);
            j.setSalary(sal);
            if (!NameInsert.equals("") && !LastName.equals("") && !Id.equals("") && !numEmpleado.equals("") && !collegeDe.equals("--Seleccionar--") && !tfYearsExp.equals("") && !tfAge.equals("")) {
                try {
                    JanitorFile.createFileJanitor(new File("Janitor.dat"));
                    JanitorFile.addJanitor(new Janitor(NameInsert, LastName, sal, id, numempl, coll, yearEx, ageE, true));
                    JanitorFile.close();

                } catch (IOException ioe) {
                    JOptionPane.showMessageDialog(null, "Error al ingresar los datos");
                }

                tfName.setText("");
                tfLastName.setText("");
                tfId.setText("");
                tfNumEmployee.setText("");
                collegeDegree.setValue("--Seleccionar--");
                tfYearsExp.setText("");
                tfAge.setText("");
                tfextrahours.setText("");

                lblError.setText("");
                lblNotification.setVisible(true);
            } else {
                lblError.setStyle("-fx-text-fill: red;-fx-font-size: 12px; ");
                lblError.setText("Debe completar todos los elementos para guardar");
            }
        });
        btnCancel.setOnAction(((event) -> {
            tfName.setText("");
            tfLastName.setText("");
            tfId.setText("");
            tfNumEmployee.setText("");
            collegeDegree.setValue("--Seleccionar--");
            tfYearsExp.setText("");
            tfAge.setText("");

            lblError.setText("");
            vbWindows.getChildren().clear();
            vbWindows.getChildren().add(gettypeEmployee());
        }));

        btnErase.setOnAction(((event) -> {
            tfName.setText("");
            tfLastName.setText("");
            tfId.setText("");
            tfNumEmployee.setText("");
            collegeDegree.setValue("--Seleccionar--");
            tfYearsExp.setText("");
            tfAge.setText("");

            lblError.setText("");
        }));

        return GpInsert;

    }

    //Metodo que agrega un conductor a la lista
    public GridPane getDriver() {
        Driver d = new Driver();
        Employee em[] = new Employee[10];
        GridPane GpInsert = new GridPane();
        GpInsert.setVgap(15);
        GpInsert.setHgap(15);
        GpInsert.setMinSize(600, 500);
        GpInsert.setStyle("-fx-background-color:lightblue;");
        GpInsert.setAlignment(Pos.CENTER);

        Label lblTitle = new Label("AGREGAR CONDUCTOR");

        lblTitle.setFont(Font.font("Cambria", 20));
        GpInsert.add(lblTitle, 1, 0);

        Label lblName = new Label("Nombre");
        GpInsert.add(lblName, 0, 1);
        TextField tfName = new TextField();
        GpInsert.add(tfName, 1, 1);

        Label lblLastName = new Label("Apellidos");
        GpInsert.add(lblLastName, 0, 3);
        TextField tfLastName = new TextField();
        GpInsert.add(tfLastName, 1, 3);

        Label lblId = new Label("ID");
        GpInsert.add(lblId, 0, 4);
        TextField tfId = new TextField();
        GpInsert.add(tfId, 1, 4);

        Label lblNumEmployee = new Label("Numero de Empleado");
        GpInsert.add(lblNumEmployee, 0, 5);
        TextField tfNumEmployee = new TextField();
        GpInsert.add(tfNumEmployee, 1, 5);
        Label lblCollegeDe = new Label("Título Universitario");
        GpInsert.add(lblCollegeDe, 0, 6);
        final ComboBox collegeDegree = new ComboBox();
        collegeDegree.setValue("--Seleccionar--");
        collegeDegree.getItems().addAll(
                "Sí",
                "No"
        );

        GpInsert.add(collegeDegree, 1, 6);

        Label lblmail = new Label("Correo Electrónico");
        GpInsert.add(lblmail, 0, 7);
        TextField tfmail = new TextField();
        GpInsert.add(tfmail, 1, 7);

        Label lblturn = new Label("Turno");
        GpInsert.add(lblturn, 0, 8);
        final ComboBox turn_ = new ComboBox();
        turn_.setValue("--Seleccionar--");
        turn_.getItems().addAll(
                "Día",
                "Noche"
        );
        GpInsert.add(turn_, 1, 8);

        Label lblhours = new Label("Horas laboradas");
        GpInsert.add(lblhours, 0, 9);
        TextField tfhours = new TextField();
        GpInsert.add(tfhours, 1, 9);
 

        Button btnAccept = new Button("Insertar");
        GpInsert.add(btnAccept, 0, 11);
        Label lblNotification = new Label("Empleado insertado con éxito");
        lblNotification.setVisible(false);
        GpInsert.add(lblNotification, 1, 11);
        Button btnErase = new Button("Borrar Datos");
        GpInsert.add(btnErase, 2, 11);
        Button btnCancel = new Button("Cancelar");
        GpInsert.add(btnCancel, 3, 11);
        Label lblError = new Label();
        GpInsert.add(lblError, 0, 12);

        btnAccept.setOnAction((event) -> {
            String NameInsert = tfName.getText();
            d.setName(NameInsert);

            String apellidos = tfLastName.getText();
            d.setLastName(apellidos);

            String id = tfId.getText();
            int id_ = Integer.parseInt(id);
            d.setId(id_);

            String numEmpleado = tfNumEmployee.getText();
            int numempl = Integer.parseInt(numEmpleado);
            d.setEmployeeNumber(numempl);

            String collegeDe = collegeDegree.getValue().toString();
            boolean coll = Boolean.parseBoolean(collegeDe);
            if (collegeDe.toLowerCase().equals("sí")) {
                d.setCollegeDegree(true);
                coll = true;
            } else {
                d.setCollegeDegree(false);
                coll = false;
            }

            String turn = turn_.getValue().toString();

            d.setTurn(turn);
            String hour = tfhours.getText();
            int ho = Integer.parseInt(hour);
            d.setHours(ho);
            double sal = d.calculateSalary(ho, turn, coll);
            d.setSalary(sal);
            if (!NameInsert.equals("") && !apellidos.equals("") && !id.equals("") && !numEmpleado.equals("") && !collegeDegree.equals("--Seleccionar--") && !turn.equals("--Seleccionar--") && !tfhours.equals("")) {

                try {
                    DriverFile.createFileDriver(new File("Driver.dat"));
                    DriverFile.addDriver(new Driver(NameInsert, apellidos, sal, id_, numempl, coll, turn, ho, true));
                    DriverFile.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error en la escritura de datos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                tfName.setText("");
                tfLastName.setText("");
                tfId.setText("");
                tfNumEmployee.setText("");
                tfhours.setText("");
                tfmail.setText("");
                collegeDegree.setValue("--Seleccionar--");
                turn_.setValue("--Seleccionar--");
              
                lblError.setText("");

                lblNotification.setVisible(true);
            } else {
                lblError.setStyle("-fx-text-fill: red;-fx-font-size: 12px; ");
                lblError.setText("Debe completar todos los elementos para guardar");
            }
        });
        btnCancel.setOnAction(((event) -> {
            tfName.setText("");
            tfLastName.setText("");
            tfId.setText("");
            tfNumEmployee.setText("");
            collegeDegree.setValue("--Seleccionar--");
            turn_.setValue("--Seleccionar--");
            lblError.setText("");
            tfhours.setText("");
            tfmail.setText("");
            vbWindows.getChildren().clear();
            vbWindows.getChildren().add(gettypeEmployee());
        }));

        btnErase.setOnAction(((event) -> {
            tfName.setText("");
            tfLastName.setText("");
            tfId.setText("");
            tfNumEmployee.setText("");
            collegeDegree.setValue("--Seleccionar--");
            lblError.setText("");
            tfhours.setText("");
            tfmail.setText("");
            turn_.setValue("--Seleccionar--");

        }));

        return GpInsert;

    }

    //metodo que agrega un vehiculo
    public GridPane getInterfacevehicle() {
        vehicle ve = new vehicle();
        Employee em[] = new Employee[10];
        GridPane GpInsert = new GridPane();
        GpInsert.setVgap(15);
        GpInsert.setHgap(15);
        GpInsert.setMinSize(600, 500);
        GpInsert.setStyle("-fx-background-color:lightblue;");
        GpInsert.setAlignment(Pos.CENTER);
        //nombre (20 caracteres), año (entero), kilometraje (flotante), americano (booleano) y serie (entero). 
        Label lblName = new Label("Nombre");
        GpInsert.add(lblName, 0, 1);
        TextField tfName = new TextField();
        GpInsert.add(tfName, 1, 1);

        Label lblYear = new Label("Año");
        GpInsert.add(lblYear, 0, 2);
        TextField tfYear = new TextField();
        GpInsert.add(tfYear, 1, 2);

        Label lblMileage = new Label("Kilometraje");
        GpInsert.add(lblMileage, 0, 3);
        TextField tfMileage = new TextField();
        GpInsert.add(tfMileage, 1, 3);

        Label lblAmerican = new Label("Automóvil americano");
        GpInsert.add(lblAmerican, 0, 4);
        final ComboBox american1 = new ComboBox();
        american1.setValue("--Seleccionar--");
        american1.getItems().addAll(
                "Sí",
                "No"
        );

        GpInsert.add(american1, 1, 4);

        Label lblLicense = new Label("Placa");
        GpInsert.add(lblLicense, 0, 5);
        TextField tfLicense = new TextField();
        GpInsert.add(tfLicense, 1, 5);

        Label lblSerie = new Label("Serie");
        GpInsert.add(lblSerie, 0, 6);
        TextField tfSerie = new TextField();
        GpInsert.add(tfSerie, 1, 6);

        Label lblSalary = new Label("Salario por hora");
        GpInsert.add(lblSalary, 0, 7);
        TextField tfSalary = new TextField();
        GpInsert.add(tfSalary, 1, 7);

        Button btnAccept = new Button("Insertar");
        Button btnErase = new Button("Borrar Datos");

        GpInsert.add(btnAccept, 0, 8);
        Label lblNotification = new Label("Empleado insertado con éxito");
        lblNotification.setVisible(false);
        GpInsert.add(lblNotification, 0, 9);

        GpInsert.add(btnErase, 1, 8);
        Button btnCancel = new Button("Cancelar");
        GpInsert.add(btnCancel, 2, 8);
        Label lblError = new Label();
        GpInsert.add(lblError, 0, 9);

        btnAccept.setOnAction((event) -> {
            String license = tfLicense.getText();

            String name = tfName.getText();
            ve.setName(name);
            String Year = tfYear.getText();
            int year_ = Integer.parseInt(Year);
            ve.setYear(year_);
            String mileage = tfMileage.getText();
            float mileage_ = Float.parseFloat(mileage);
            ve.setMileage(mileage_);

            String american = american1.getValue().toString();
            boolean american_ = Boolean.parseBoolean(american);
            if (american.toLowerCase().equals("sí")) {
                ve.setAmerican(true);
                american_ = true;
            } else {
                ve.setAmerican(false);
                american_ = false;
            }

            String serie = tfSerie.getText();
            int serie_ = Integer.parseInt(serie);
            ve.setSerie(serie_);
            int license1 = Integer.parseInt(license);

            String salary = tfSalary.getText();
            double salary_ = Double.parseDouble(salary);
            ve.setTotalSalary(salary_);

            if (!name.equals("") && !Year.equals("") && !american1.getValue().equals("--Seleccionar--") && !mileage.equals("") && !salary.equals("")) {
                try {
                    VehicleFile.createFilevehicle(new File("Vehicle.dat"));
                    VehicleFile.addvehicle(new vehicle(serie_, name, year_, american_, mileage_, salary_, true));
                    VehicleFile.close();
                } catch (IOException ioe) {
                    JOptionPane.showMessageDialog(null, "Error al ingresar los datos");
                }

                tfLicense.setText("");
                tfName.setText("");
                tfYear.setText("");
                american1.setValue(("--Seleccionar--"));
                tfMileage.setText("");
                tfSerie.setText("");
                tfSalary.setText("");
                lblError.setText("");
                lblNotification.setVisible(true);
            } else {
                lblError.setStyle("-fx-text-fill: red;-fx-font-size: 12px; ");
                lblError.setText("Debe completar todos los elementos para guardar");
            }
        });
        btnCancel.setOnAction(((event) -> {
            tfLicense.setText("");
            tfName.setText("");
            tfYear.setText("");
            american1.setValue((""));
            tfMileage.setText("");
            tfSerie.setText("");
            tfSalary.setText("");
            lblError.setText("");
            lblNotification.setVisible(true);

            lblError.setText("");
            vbWindows.getChildren().clear();
        }));
        btnErase.setOnAction(((event) -> {
            tfLicense.setText("");

            tfName.setText("");
            tfYear.setText("");
            american1.setValue((""));
            tfMileage.setText("");
            tfSerie.setText("");
            tfSalary.setText("");
            lblError.setText("");
            lblNotification.setVisible(true);
        }));

        Button btnTest = new Button();
        btnTest.setOnAction((event) -> {

        });

        return GpInsert;

    }

    public VBox viewAdmin() throws IOException {
        VBox vb_ReporteAgenda = new VBox();
        Label lb_Agenda = new Label("Administrador");
        lb_Agenda.setFont(new Font("Arial", 18));
        TableView<Admin> tv_Agenda = new TableView<>();
        // tv_Agenda.setDisable(true);

        tv_Agenda.setEditable(false);
        tv_Agenda.setSelectionModel(null);
        TextField search = new TextField();
        search.setPromptText("Buscar...");
        FilteredList<Admin> filteredData = new FilteredList<>(getListAdmin(), p -> true);
        search.setOnKeyReleased(e -> {
            search.textProperty().addListener((observableValue, oldvalue, newvalue) -> {
                filteredData.setPredicate((Predicate<? super Admin>) Admin -> {
                    if (newvalue.equals("")) {
                        return true;
                    }
                    String lowerCaseFilter = newvalue.toLowerCase();

                    if (Admin.getName().toLowerCase().contains(newvalue) || Admin.getName().toUpperCase().contains(newvalue)) {

                        return true;
                    }

                    return false;
                });
            });
            SortedList<Admin> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(tv_Agenda.comparatorProperty());
            tv_Agenda.setItems(sortedData);
        });
        search.setFocusTraversable(false);
        HBox.setHgrow(search, Priority.ALWAYS);
        HBox searchBar = new HBox();
        searchBar.getChildren().add(search);

        //Carga los datos al TableView
        ObservableList<Admin> datos = getListAdmin();
        tv_Agenda.setItems(datos);
        //tv_Agenda.setItems(datos);
        if (datos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Actualmente no hay datos ingresados en la agenda");
        } else {
            //Crear la columna nombre
            TableColumn tc_NombreColum = new TableColumn("Nombre");

            tc_NombreColum.setEditable(false);

            tc_NombreColum.setCellValueFactory(new PropertyValueFactory<Admin, String>("name"));

            //Crear la columna fecha
            TableColumn tcLastName = new TableColumn("Apellidos");
            tcLastName.setCellValueFactory(new PropertyValueFactory<Admin, String>("lastName"));

            TableColumn tcSalary = new TableColumn("Salario");
            tcSalary.setCellValueFactory(new PropertyValueFactory<Admin, Double>("salary"));

            TableColumn tcId = new TableColumn("Id");
            tcId.setCellValueFactory(new PropertyValueFactory<Admin, Integer>("id"));

            TableColumn tcEmployeeNum = new TableColumn("Número de Empleado");
            tcEmployeeNum.setCellValueFactory(new PropertyValueFactory<Admin, Integer>("employeeNumber"));

            TableColumn tcCollegue = new TableColumn("Título Universitario");
            tcCollegue.setCellValueFactory(new PropertyValueFactory<Admin, Boolean>("collegeDegree"));

            TableColumn tcTelNum = new TableColumn("Número Telefónico");
            tcTelNum.setCellValueFactory(new PropertyValueFactory<Admin, Integer>("telephoneNumber"));

            TableColumn tcCategory = new TableColumn("Categoria");
            tcCategory.setCellValueFactory(new PropertyValueFactory<Admin, Integer>("categoryType"));

            //Button bTn_Eliminar1 = new Button("Cancelar");
            //  StackPane.setAlignment(bTn_Eliminar1, Pos.BOTTOM_RIGHT);
            tv_Agenda.getColumns().addAll(tc_NombreColum, tcLastName, tcSalary, tcId, tcEmployeeNum, tcCollegue, tcTelNum, tcCategory);

            vb_ReporteAgenda.getChildren().addAll(lb_Agenda, search, searchBar, tv_Agenda);
        }
        return vb_ReporteAgenda;

    }

    public ObservableList<Admin> getListAdmin() throws IOException {
        ArrayList array = new ArrayList();
        AdminFile.createFileAdmin(new File("Admin.dat"));
        List<Admin> arrayContacts = AdminFile.getAllAdmin();

        for (int j = 0; j < arrayContacts.size(); j++) {
            //System.out.print(arrayContacts.get(j).getApellidos());
            array.add(arrayContacts.get(j));
        }
        ObservableList<Admin> olListAdmin = FXCollections.observableArrayList(array);
        AdminFile.close();
        return olListAdmin;
    }

    public VBox viewDriver() throws Exception {
        VBox vbDriver = new VBox();
        Label lbDriver = new Label("Conductor");
        lbDriver.setFont(new Font("Arial", 18));
        TableView<Driver> tvDriver = new TableView<>();
        // tv_Agenda.setDisable(true);

        tvDriver.setEditable(false);
        tvDriver.setSelectionModel(null);
        TextField search = new TextField();
        search.setPromptText("Buscar...");
        FilteredList<Driver> filteredData = new FilteredList<>(getListDriver(), p -> true);
        search.setOnKeyReleased(e -> {
            search.textProperty().addListener((observableValue, oldvalue, newvalue) -> {
                filteredData.setPredicate((Predicate<? super Driver>) Driver -> {
                    if (newvalue.equals("")) {
                        return true;
                    }
                    String lowerCaseFilter = newvalue.toLowerCase();

                    if (Driver.getName().toLowerCase().contains(newvalue) || Driver.getName().toUpperCase().contains(newvalue)) {

                        return true;
                    }

                    return false;
                });
            });
            SortedList<Driver> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(tvDriver.comparatorProperty());
            tvDriver.setItems(sortedData);
        });
        search.setFocusTraversable(false);
        HBox.setHgrow(search, Priority.ALWAYS);
        HBox searchBar = new HBox();
        searchBar.getChildren().add(search);

        //Carga los datos al TableView
        ObservableList<Driver> data = getListDriver();
        tvDriver.setItems(data);
        //tv_Agenda.setItems(datos);
        if (data.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Actualmente no hay datos ingresados en tabla");
        } else {
            //Crear la columna nombre
            TableColumn tcNameColum = new TableColumn("Nombre");

            tcNameColum.setEditable(false);

            tcNameColum.setCellValueFactory(new PropertyValueFactory<Driver, String>("name"));

            //Crear la columna fecha
            TableColumn tcLastName = new TableColumn("Apellidos");
            tcLastName.setCellValueFactory(new PropertyValueFactory<Driver, String>("lastName"));

            TableColumn tcSalary = new TableColumn("Salario");
            tcSalary.setCellValueFactory(new PropertyValueFactory<Driver, Double>("salary"));

            TableColumn tcId = new TableColumn("Id");
            tcId.setCellValueFactory(new PropertyValueFactory<Driver, Integer>("id"));

            TableColumn tcEmployeeNum = new TableColumn("Número de Empleado");
            tcEmployeeNum.setCellValueFactory(new PropertyValueFactory<Driver, Integer>("employeeNumber"));

            TableColumn tcCollegue = new TableColumn("Título Universitario");
            tcCollegue.setCellValueFactory(new PropertyValueFactory<Driver, Boolean>("collegeDegree"));

            TableColumn tcTurn = new TableColumn("Turno");
            tcTurn.setCellValueFactory(new PropertyValueFactory<Driver, Boolean>("turn"));

            TableColumn tcHours = new TableColumn("Horas trabajadas");
            tcHours.setCellValueFactory(new PropertyValueFactory<Driver, Integer>("hours"));

            //Button bTn_Eliminar1 = new Button("Cancelar");
            //  StackPane.setAlignment(bTn_Eliminar1, Pos.BOTTOM_RIGHT);
            tvDriver.getColumns().addAll(tcNameColum, tcLastName, tcSalary, tcId, tcEmployeeNum, tcCollegue, tcTurn, tcHours);

            vbDriver.getChildren().addAll(lbDriver, search, searchBar, tvDriver);
        }
        return vbDriver;

    }

    public ObservableList<Driver> getListDriver() throws IOException {
        ArrayList array = new ArrayList();
        DriverFile.createFileDriver(new File("Driver.dat"));
        List<Driver> arrayContacts = DriverFile.getAllDrivers();

        for (int j = 0; j < arrayContacts.size(); j++) {
            //System.out.print(arrayContacts.get(j).getApellidos());
            array.add(arrayContacts.get(j));
        }
        ObservableList<Driver> olListDriver = FXCollections.observableArrayList(array);
        DriverFile.close();
        return olListDriver;
    }

    public VBox viewJanitor() throws Exception {
        VBox vbJanitor = new VBox();
        Label lbJanitor = new Label("Conserje");
        lbJanitor.setFont(new Font("Arial", 18));
        TableView<Janitor> tvJanitor = new TableView<>();
        // tv_Agenda.setDisable(true);

        tvJanitor.setEditable(false);
        tvJanitor.setSelectionModel(null);
        TextField search = new TextField();
        search.setPromptText("Buscar...");
        FilteredList<Janitor> filteredData = new FilteredList<>(getListJanitor(), p -> true);
        search.setOnKeyReleased(e -> {
            search.textProperty().addListener((observableValue, oldvalue, newvalue) -> {
                filteredData.setPredicate((Predicate<? super Janitor>) Janitor -> {
                    if (newvalue.equals("")) {
                        return true;
                    }
                    String lowerCaseFilter = newvalue.toLowerCase();

                    if (Janitor.getName().toLowerCase().contains(newvalue) || Janitor.getName().toUpperCase().contains(newvalue)) {

                        return true;
                    }

                    return false;
                });
            });
            SortedList<Janitor> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(tvJanitor.comparatorProperty());
            tvJanitor.setItems(sortedData);
        });
        search.setFocusTraversable(false);
        HBox.setHgrow(search, Priority.ALWAYS);
        HBox searchBar = new HBox();
        searchBar.getChildren().add(search);

        //Carga los datos al TableView
        ObservableList<Janitor> data = getListJanitor();
        tvJanitor.setItems(data);
        //tv_Agenda.setItems(datos);
        if (data.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Actualmente no hay datos ingresados en tabla");
        } else {
            //Crear la columna nombre
            TableColumn tcNameColum = new TableColumn("Nombre");

            tcNameColum.setEditable(false);

            tcNameColum.setCellValueFactory(new PropertyValueFactory<Janitor, String>("name"));

            //Crear la columna fecha
            TableColumn tcLastName = new TableColumn("Apellidos");
            tcLastName.setCellValueFactory(new PropertyValueFactory<Janitor, String>("lastName"));

            TableColumn tcSalary = new TableColumn("Salario");
            tcSalary.setCellValueFactory(new PropertyValueFactory<Janitor, Double>("salary"));

            TableColumn tcId = new TableColumn("Id");
            tcId.setCellValueFactory(new PropertyValueFactory<Janitor, Integer>("id"));

            TableColumn tcEmployeeNum = new TableColumn("Número de Empleado");
            tcEmployeeNum.setCellValueFactory(new PropertyValueFactory<Janitor, Integer>("employeeNumber"));

            TableColumn tcCollegue = new TableColumn("Título Universitario");
            tcCollegue.setCellValueFactory(new PropertyValueFactory<Janitor, Boolean>("collegeDegree"));

            TableColumn tcYears = new TableColumn("Años de Experiencia");
            tcYears.setCellValueFactory(new PropertyValueFactory<Janitor, Double>("yearsExperience"));

            TableColumn tcAge = new TableColumn("Edad");
            tcAge.setCellValueFactory(new PropertyValueFactory<Janitor, Integer>("age"));

            //Button bTn_Eliminar1 = new Button("Cancelar");
            //  StackPane.setAlignment(bTn_Eliminar1, Pos.BOTTOM_RIGHT);
            tvJanitor.getColumns().addAll(tcNameColum, tcLastName, tcSalary, tcId, tcEmployeeNum, tcCollegue, tcYears, tcAge);

            vbJanitor.getChildren().addAll(lbJanitor, search, searchBar, tvJanitor);
        }
        return vbJanitor;

    }

    public ObservableList<Janitor> getListJanitor() throws IOException {
        ArrayList array = new ArrayList();
        JanitorFile.createFileJanitor(new File("Janitor.dat"));
        List<Janitor> arrayContacts = JanitorFile.getAllJanitors();

        for (int j = 0; j < arrayContacts.size(); j++) {
            //System.out.print(arrayContacts.get(j).getApellidos());
            array.add(arrayContacts.get(j));
        }
        ObservableList<Janitor> olListJanitor = FXCollections.observableArrayList(array);
        JanitorFile.close();
        return olListJanitor;
    }

    public VBox viewVehicle() throws Exception {
        VBox vbVehicle = new VBox();
        Label lbvehicle = new Label("Vehiculo");
        lbvehicle.setFont(new Font("Arial", 18));
        TableView<vehicle> tvvehicle = new TableView<>();
        // tv_Agenda.setDisable(true);

        tvvehicle.setEditable(false);
        tvvehicle.setSelectionModel(null);
        TextField search = new TextField();
        search.setPromptText("Buscar...");
        FilteredList<vehicle> filteredData = new FilteredList<>(getListvehicle(), p -> true);
        search.setOnKeyReleased(e -> {

            search.textProperty().addListener((observableValue, oldvalue, newvalue) -> {
                filteredData.setPredicate((Predicate<? super vehicle>) vehicle -> {
                    String serie = "" + vehicle.getSerie();
                    if (newvalue.equals("")) {
                        return true;
                    }
                    String lowerCaseFilter = newvalue.toLowerCase();

                    if (serie.contains(newvalue)) {

                        return true;
                    }

                    return false;
                });
            });
            SortedList<vehicle> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(tvvehicle.comparatorProperty());
            tvvehicle.setItems(sortedData);
        });
        search.setFocusTraversable(false);
        HBox.setHgrow(search, Priority.ALWAYS);
        HBox searchBar = new HBox();
        searchBar.getChildren().add(search);

        //Carga los datos al TableView
        ObservableList<vehicle> data = getListvehicle();
        tvvehicle.setItems(data);
        //tv_Agenda.setItems(datos);
        if (data.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Actualmente no hay datos ingresados en tabla");
        } else {
            //Crear la columna nombre
            TableColumn tcSerieColum = new TableColumn("Serie");

            tcSerieColum.setEditable(false);

            tcSerieColum.setCellValueFactory(new PropertyValueFactory<vehicle, Integer>("serie"));

            //Crear la columna fecha
            TableColumn tcName = new TableColumn("Nombre");
            tcName.setCellValueFactory(new PropertyValueFactory<vehicle, String>("name"));

            TableColumn tcyear = new TableColumn("Año");
            tcyear.setCellValueFactory(new PropertyValueFactory<vehicle, Integer>("year"));

            TableColumn tcAmerican = new TableColumn("Americano");
            tcAmerican.setCellValueFactory(new PropertyValueFactory<vehicle, Boolean>("american"));

            TableColumn tcMileage = new TableColumn("Kilometraje");
            tcMileage.setCellValueFactory(new PropertyValueFactory<vehicle, Float>("mileage"));

            TableColumn tctotalSalary = new TableColumn("Salario total");
            tctotalSalary.setCellValueFactory(new PropertyValueFactory<vehicle, Double>("totalSalary"));

            tvvehicle.getColumns().addAll(tcSerieColum, tcName, tcyear, tcAmerican, tcMileage, tctotalSalary);

            vbVehicle.getChildren().addAll(lbvehicle, search, searchBar, tvvehicle);
        }
        return vbVehicle;

    }

    public ObservableList<vehicle> getListvehicle() throws IOException {
        ArrayList array = new ArrayList();
        VehicleFile.createFilevehicle(new File("Vehicle.dat"));
        List<vehicle> arrayContacts = VehicleFile.getAllvehicle();

        for (int j = 0; j < arrayContacts.size(); j++) {
            //System.out.print(arrayContacts.get(j).getApellidos());
            array.add(arrayContacts.get(j));
        }
        ObservableList<vehicle> olListVehicle = FXCollections.observableArrayList(array);
        VehicleFile.close();
        return olListVehicle;
    }

    public GridPane getDeleteAdmin() {
        GridPane GpInsert = new GridPane();
        GpInsert.setVgap(15);
        GpInsert.setHgap(15);
        GpInsert.setMinSize(600, 500);
        GpInsert.setStyle("-fx-background-color:lightblue;");
        GpInsert.setAlignment(Pos.CENTER);

        Label lblName = new Label("Nombre a eliminar");
        GpInsert.add(lblName, 0, 1);
        TextField tfName = new TextField();
        GpInsert.add(tfName, 1, 1);
        Button btnDelete = new Button("Eliminar");
        GpInsert.add(btnDelete, 1, 3);
        btnDelete.setOnAction((event) -> {
            String delete = tfName.getText();
            try {

                AdminFile.createFileAdmin(new File("Admin.dat"));
                AdminFile.deleteRecord(delete);
                AdminFile.close();
                tfName.setText("");
                JOptionPane.showMessageDialog(null, "Administrador " + delete + " eliminado con éxito");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error en la eliminación de registros.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return GpInsert;
    }

    public GridPane getDeleteDriver() {
        GridPane GpInsert = new GridPane();
        GpInsert.setVgap(15);
        GpInsert.setHgap(15);
        GpInsert.setMinSize(600, 500);
        GpInsert.setStyle("-fx-background-color:lightblue;");
        GpInsert.setAlignment(Pos.CENTER);

        Label lblName = new Label("Nombre a eliminar");
        GpInsert.add(lblName, 0, 1);
        TextField tfName = new TextField();
        GpInsert.add(tfName, 1, 1);
        Button btnDelete = new Button("Eliminar");
        GpInsert.add(btnDelete, 1, 3);
        btnDelete.setOnAction((event) -> {
            String delete = tfName.getText();
            try {

                DriverFile.createFileDriver(new File("Driver.dat"));
                DriverFile.deleteRecord(delete);
                DriverFile.close();
                tfName.setText("");
                JOptionPane.showMessageDialog(null, "Conductor" + delete + " eliminado con éxito");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error en la eliminación de registros.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return GpInsert;
    }
//metodo que elimina un conserje

    public GridPane getDeleteJanitor() {
        GridPane GpInsert = new GridPane();
        GpInsert.setVgap(15);
        GpInsert.setHgap(15);
        GpInsert.setMinSize(600, 500);
        GpInsert.setStyle("-fx-background-color:lightblue;");
        GpInsert.setAlignment(Pos.CENTER);

        Label lblName = new Label("Nombre a eliminar");
        GpInsert.add(lblName, 0, 1);
        TextField tfName = new TextField();
        GpInsert.add(tfName, 1, 1);
        Button btnDelete = new Button("Eliminar");
        GpInsert.add(btnDelete, 1, 3);
        btnDelete.setOnAction((event) -> {
            String delete = tfName.getText();
            try {

                JanitorFile.createFileJanitor(new File("Janitor.dat"));
                JanitorFile.deleteRecord(delete);
                JanitorFile.close();
                tfName.setText("");
                JOptionPane.showMessageDialog(null, "Conserje " + delete + " eliminado con éxito");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error en la eliminación de registros.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return GpInsert;
    }

    //metodo que elimina un vehiculo
    public GridPane getVehicleDelete() {
        GridPane GpInsert = new GridPane();
        GpInsert.setVgap(15);
        GpInsert.setHgap(15);
        GpInsert.setMinSize(600, 500);
        GpInsert.setStyle("-fx-background-color:lightblue;");
        GpInsert.setAlignment(Pos.CENTER);

        Label lblName = new Label("Nombre a eliminar");
        GpInsert.add(lblName, 0, 1);
        TextField tfSerie = new TextField();
        GpInsert.add(tfSerie, 1, 1);
        Button btnDelete = new Button("Eliminar");
        GpInsert.add(btnDelete, 1, 3);
        btnDelete.setOnAction((event) -> {
            int delete = Integer.parseInt(tfSerie.getText());
            try {

                VehicleFile.createFilevehicle(new File("Vehicle.dat"));
                VehicleFile.deleteRecord(delete);
                VehicleFile.close();
                tfSerie.setText("");
                JOptionPane.showMessageDialog(null, "Serie " + delete + " eliminada con éxito");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error en la eliminación de registros.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return GpInsert;
    }
}
