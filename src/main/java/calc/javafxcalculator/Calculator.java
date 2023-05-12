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
        if (buttonText.equals(".") || !textArea.getText().contains(".")) {
            textArea.appendText(buttonText);
        }
    }

    public void addSubtract(MouseEvent event) {
        String num = textArea.getText();
        String num1 = resultArea.getText();
        String symbol = ((Button) event.getSource()).getText();

        // Checking if a symbol is already selected
        try {
            Double.parseDouble(num);
        }
        catch (NumberFormatException e) {
            textArea.setText(symbol);
            return;
        }

        if (num.equals("") && num1.equals("")) {
            num = "0";
            num1 = "0";
        }
        else if (num.equals("")) {
            num = "0";
        }
        else if (num1.equals("")) {
            num1 = "0";
        }
        double total = Double.parseDouble(num) + Double.parseDouble(num1);

        resultArea.setText(Double.toString(total));
        textArea.setText(symbol);
    }

    public void multiplyDivide(MouseEvent event) {
        String num = textArea.getText();
        String symbol = ((Button) event.getSource()).getText();

        // Checking if a symbol is already selected
        try {
            Double.parseDouble(num);
        }
        catch (NumberFormatException e) {
            textArea.setText(symbol);
            return;
        }

        if (num.equals("")) {
            num = "0";
        }

        resultArea.setText(num);
        textArea.setText(symbol);
    }

    public void functions(MouseEvent event) {
        Double num;
        try {
            num = Double.parseDouble(textArea.getText());
        }
        catch (NumberFormatException e){
            num = 0.0;
        }
        String func = ((Button) event.getSource()).getText();

        switch (func) {
            case "sqrt" -> {
                num = Math.sqrt(num);
                resultArea.setText(num.toString());
                textArea.setText("");
            }
            case "|x|" -> {
                num = Math.abs(num);
                resultArea.setText(num.toString());
                textArea.setText("");
            }
            case "1/x" -> {
                if (num != 0.0) {
                    num = 1 / num;
                    resultArea.setText(num.toString());
                    textArea.setText("");
                }
            }
            case "x^2" -> {
                num = Math.pow(num, 2.0);
                resultArea.setText(num.toString());
                textArea.setText("");
            }
            case "n!" -> {
                int factNum = 1;
                if (num >= 0) {
                    if (num !=0 ){
                        for (int i = 1; i <= num.intValue(); i++) {
                            factNum *= i;
                        }
                    }
                    resultArea.setText(String.valueOf(factNum));
                    textArea.setText("");
                }
            }
        }
    }
}