
package main;

import InterfaceProyect.Interfaz1;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class OrtegaTrejosManosAlaObra2019 extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
       Interfaz1 i1 = new Interfaz1();
       primaryStage.setTitle("Empleados");
        primaryStage.setScene(i1.getSceneMenu());
        primaryStage.show();
    }

    
    public static void main(String[] args) {
        launch(args);
    }
    
}
