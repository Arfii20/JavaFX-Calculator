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
        FXMLLoader fxmlLoader = new FXMLLoader(Calculator.class.getResource("Calculator.fxml"));
        fxmlLoader.setController(this);
        Scene scene = new Scene(fxmlLoader.load(), 400, 600);

        equationArea.setEditable(false);
        resultArea.setEditable(false);
        symbolArea.setEditable(false);
        textArea.setEditable(false);

        stage.setTitle("Calculator");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void getSetValue(MouseEvent event) {
        String buttonText = ((Button) event.getSource()).getText();
        if (!buttonText.equals(".") || !textArea.getText().contains(".")) {
            textArea.appendText(buttonText);
        }
    }

    public void addSubMulDiv(MouseEvent event) {
        String symbol;
        String newNum = textArea.getText();
        String previousNum = resultArea.getText();

        if (newNum.equals("") && previousNum.equals("")) {
            symbol = ((Button) event.getSource()).getText();
            symbol = symbol.matches("x|/|mod") ? "" : symbol;
            symbolArea.setText(symbol);
        }
        else if (newNum.equals("") && !previousNum.equals("")){
            symbol = ((Button) event.getSource()).getText();
            symbol = symbol.equals("mod") ? "%" : symbol;
            symbolArea.setText(symbol);
        }
        else if (previousNum.equals("")) {
            symbol = ((Button) event.getSource()).getText();
            symbol = symbol.equals("mod") ? "%" : symbol;
            symbolArea.setText(symbol);
            symbol = symbol.matches("x|/|mod") ? "" : symbol;
            resultArea.setText(symbol + newNum);
            equationArea.setText(symbol + newNum);
            textArea.setText("");
        }
        else {
            symbol = symbolArea.getText().equals("") ? ((Button) event.getSource()).getText() : symbolArea.getText();
            if (symbol.matches("x|/|mod")){
                equationArea.setText("(" + equationArea.getText() + ")");
            }
            equationArea.appendText(symbol + newNum);

            double result = switch (symbol) {
                case "+" -> Double.parseDouble(previousNum) + Double.parseDouble(newNum);
                case "-" -> Double.parseDouble(previousNum) - Double.parseDouble(newNum);
                case "x" -> Double.parseDouble(previousNum) * Double.parseDouble(newNum);
                case "/" -> Double.parseDouble(previousNum) / Double.parseDouble(newNum);
                case "mod" -> Double.parseDouble(previousNum) % Double.parseDouble(newNum);
                default -> 0.0;
            };

            result = result == -0.0 ? 0.0 : result;
            resultArea.setText(Double.toString(result));
            symbol = ((Button) event.getSource()).getText();
            symbol = symbol.equals("mod") ? "%" : symbol;
            symbolArea.setText(symbol);
            textArea.setText("");
        }
    }

    public void functions(MouseEvent event) {
        double num;
        String text = textArea.getText();
        String symbol = symbolArea.getText();
        equationArea.setText(symbol.equals("") ? "" : equationArea.getText() + symbol);
        try {
            num = Double.parseDouble(text);
        }
        catch (NumberFormatException e){
            num = 0.0;
        }
        String func = ((Button) event.getSource()).getText();

        switch (func) {
            case "sqrt" -> {
                equationArea.appendText("sqrt(" + num + ")");
                num = Math.sqrt(num);
                resultArea.setText(Double.toString(num));
                textArea.setText("");
            }
            case "|x|" -> {
                equationArea.appendText("|" + num + "|");
                num = Math.abs(num);
                resultArea.setText(Double.toString(num));
                textArea.setText("");
            }
            case "1/x" -> {
                equationArea.appendText("1/" + num);
                if (num != 0.0) {
                    num = 1 / num;
                    resultArea.setText(Double.toString(num));
                    textArea.setText("");
                }
            }
            case "x^2" -> {
                equationArea.appendText(num + "^2");
                num = Math.pow(num, 2.0);
                resultArea.setText(Double.toString(num));
                textArea.setText("");
            }
            case "ln" -> {
                equationArea.appendText("ln(" + num + ")");
                num = Math.log(num);
                resultArea.setText(Double.toString(num));
                textArea.setText("");
            }
            case "log" -> {
                equationArea.appendText("log(" + num + ")");
                num = Math.log10(num);
                resultArea.setText(Double.toString(num));
                textArea.setText("");
            }
            case "10^x" -> {
                equationArea.appendText("10^" + num);
                num = 10*num;
                resultArea.setText(Double.toString(num));
                textArea.setText("");
            }
            case "n!" -> {
                equationArea.appendText(num + "!");
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
                symbolArea.setText("");
            }
            case "[X]" -> {
                if (text.length() > 0) {
                    System.out.println(text.substring(0, text.length()-1));
                    textArea.setText(text.substring(0, text.length()-1));
                }
            }
            case "e" -> {
                equationArea.appendText("e");
                if (text.length() > 1) {
                    textArea.setText(text.charAt(0) + String.valueOf(Math.E));
                }
                else {
                    textArea.appendText(String.valueOf(Math.E));
                }
            }
            case "π" -> {
                equationArea.appendText("π");
                if (text.length() > 1) {
                    textArea.setText(text.charAt(0) + String.valueOf(Math.PI));
                }
                else {
                    textArea.appendText(String.valueOf(Math.PI));
                }
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
