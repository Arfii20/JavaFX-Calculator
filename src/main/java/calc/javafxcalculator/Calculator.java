package calc.javafxcalculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Calculator extends Application {
    private Stage window;

    @Override
    public void start(Stage stage) throws IOException {
        this.window = stage;

        FXMLLoader fxmlLoader = new FXMLLoader(Calculator.class.getResource("Calculator.fxml"));
        fxmlLoader.setController(this);
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);





        this.window.setTitle("Hello!");
        this.window.setScene(scene);
        this.window.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void getValue(MouseEvent event) {
        String buttonText = ((Button) event.getSource()).getText();



    }
}