module FlyOutJavaFX {
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;

    // linha 6 permite o package model utilizar javafx.base
    opens model to javafx.base;
    
	opens application to javafx.graphics, javafx.fxml;
	opens controller to javafx.fxml;
}
