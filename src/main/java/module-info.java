module fr.univ_rouen.leeea.connectfourfx {
    requires javafx.controls;
    requires javafx.fxml;


    exports fr.univ_rouen.leeea.connectfourfx.controller;
    opens fr.univ_rouen.leeea.connectfourfx.controller to javafx.fxml;
}