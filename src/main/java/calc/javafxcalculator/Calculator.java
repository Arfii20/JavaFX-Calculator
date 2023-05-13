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
    @FXML
    TextArea equationArea;
    @FXML
    TextArea symbolArea;

    @Override
    public void start(Stage stage) throws IOException {
        this.window = stage;
        this.window.setResizable(false);

        FXMLLoader fxmlLoader = new FXMLLoader(Calculator.class.getResource("Calculator.fxml"));
        fxmlLoader.setController(this);
        Scene scene = new Scene(fxmlLoader.load(), 400, 600);

        equationArea.setEditable(false);
        resultArea.setEditable(false);
        symbolArea.setEditable(false);
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
        if (!buttonText.equals(".") || !textArea.getText().contains(".")) {
            textArea.appendText(buttonText);
        }
    }

    public void addSubtract(MouseEvent event) {
        String num = textArea.getText();
        String num1 = resultArea.getText();
        String symbol = ((Button) event.getSource()).getText();
        symbolArea.setText(symbol);

        // Checking if a symbol is already selected
        String text = equationArea.getText().trim();
        if (text.endsWith("+") || text.endsWith("-") || text.endsWith("*") || text.endsWith("/") || text.equals("")){
            if (!num.equals("")){
                equationArea.appendText(num + symbol);
            }
        }
        else {
            equationArea.setText((Double.parseDouble(num) + Double.parseDouble(num1)) + symbol);
        }

        num = num.equals("") ? "0" : num;
        num1 = num1.equals("") ? "0" : num1;

        double total = Double.parseDouble(num) + Double.parseDouble(num1);
        resultArea.setText(Double.toString(total));
        textArea.setText("");
        symbolArea.setText(symbol);
    }

    public void multiplyDivide(MouseEvent event) {
        String num = textArea.getText();
        String num1 = resultArea.getText();
        String symbol = ((Button) event.getSource()).getText();

        String text = equationArea.getText().trim();
        if (text.endsWith("+") || text.endsWith("-") || text.endsWith("*") || text.endsWith("/") || text.equals("")){
            if (!num.equals("")){
                System.out.println(num);
                equationArea.appendText(num + symbol);
            }
        }
        else {
            equationArea.setText((Double.parseDouble(num) + Double.parseDouble(num1)) + symbol);
        }

        num = num.equals("") ? "0" : num;
        num1 = num1.equals("") ? "0" : num1;

        if (num1.equals("0") && symbol.equals("/")) {
            equationArea.setText("Math Error");
            resultArea.setText("");
            textArea.setText("");
            return;
        }
        double result = 0;
        if (symbol.equals("x")) {
            result = Double.parseDouble(num) * Double.parseDouble(num1);
        }
        else if (symbol.equals("/")) {
            result = Double.parseDouble(num1) / Double.parseDouble(num);
        }

        resultArea.setText(Double.toString(result));
        textArea.setText(symbol);
    }

    public void functions(MouseEvent event) {
        double num;
        String text = textArea.getText();
        try {
            num = Double.parseDouble(text);
        }
        catch (NumberFormatException e){
            num = 0.0;
        }
        String func = ((Button) event.getSource()).getText();

        switch (func) {
            case "sqrt" -> {
                equationArea.setText("sqrt(" + num + ")");
                num = Math.sqrt(num);
                resultArea.setText(Double.toString(num));
                textArea.setText("");
            }
            case "|x|" -> {
                equationArea.setText("|" + num + "|");
                num = Math.abs(num);
                resultArea.setText(Double.toString(num));
                textArea.setText("");
            }
            case "1/x" -> {
                equationArea.setText("1/" + num);
                if (num != 0.0) {
                    num = 1 / num;
                    resultArea.setText(Double.toString(num));
                    textArea.setText("");
                }
            }
            case "x^2" -> {
                equationArea.setText(num + "^2");
                num = Math.pow(num, 2.0);
                resultArea.setText(Double.toString(num));
                textArea.setText("");
            }
            case "ln" -> {
                equationArea.setText("ln(" + num + ")");
                num = Math.log(num);
                resultArea.setText(Double.toString(num));
                textArea.setText("");
            }
            case "log" -> {
                equationArea.setText("log(" + num + ")");
                num = Math.log10(num);
                resultArea.setText(Double.toString(num));
                textArea.setText("");
            }
            case "10^x" -> {
                equationArea.setText("10^" + num);
                num = 10*num;
                resultArea.setText(Double.toString(num));
                textArea.setText("");
            }
            case "n!" -> {
                equationArea.setText(num + "!");
                int factNum = 1;
                if (num >= 0) {
                    if (num !=0 ){
                        for (int i = 1; i <= (int) num; i++) {
                            factNum *= i;
                        }
                    }
                    resultArea.setText(String.valueOf(factNum));
                    textArea.setText("");
                }
            }
            case "CE" -> {
                equationArea.setText("");
                resultArea.setText("");
                textArea.setText("");
            }
            case "[X]" -> {
                if (text.length() > 0) {
                    textArea.setText(text.substring(0, text.length()-1));
                }
            }
            case "e" -> {
                equationArea.setText("e");
                if (text.length() > 1) {
                    textArea.setText(text.charAt(0) + String.valueOf(Math.E));
                }
                else {
                    textArea.appendText(String.valueOf(Math.E));
                }
            }
            case "π" -> {
                equationArea.setText("π");
                if (text.length() > 1) {
                    textArea.setText(text.charAt(0) + String.valueOf(Math.PI));
                }
                else {
                    textArea.appendText(String.valueOf(Math.PI));
                }
            }
        }
    }
}