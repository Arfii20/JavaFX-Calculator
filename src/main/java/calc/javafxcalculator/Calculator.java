package calc.javafxcalculator;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Calculator extends Application {
    private Stage window;
    private ArrayList<Double> numbers = new ArrayList<Double>();
    private ArrayList<String> symbols = new ArrayList<String>();
    private Integer[] nums = new Integer[10];

    @FXML
    TextArea textArea;
    @FXML
    TextArea resultArea;

    @Override
    public void start(Stage stage) throws IOException {
        this.window = stage;
        this.window.setResizable(false);

        FXMLLoader fxmlLoader = new FXMLLoader(Calculator.class.getResource("Calculator.fxml"));
        fxmlLoader.setController(this);
        Scene scene = new Scene(fxmlLoader.load(), 400, 600);


        resultArea.setEditable(false);
        textArea.setEditable(false);

        this.window.setTitle("Calculator");
        this.window.setScene(scene);
        this.window.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void getSetValue(MouseEvent event) {
        String buttonText = ((Button) event.getSource()).getText();
        textArea.appendText(buttonText);
    }

    public void symbols(MouseEvent event) {
        String num = textArea.getText();
        String symbol = ((Button) event.getSource()).getText();

        numbers.add(Double.parseDouble(num));
        symbols.add(symbol);

        resultArea.appendText(num + symbol);
        textArea.setText("");
    }
}