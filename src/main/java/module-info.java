module calc.javafxcalculator {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.scripting;


    opens calc.javafxcalculator to javafx.fxml;
    exports calc.javafxcalculator;
}