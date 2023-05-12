module calc.javafxcalculator {
    requires javafx.controls;
    requires javafx.fxml;


    opens calc.javafxcalculator to javafx.fxml;
    exports calc.javafxcalculator;
}