package fr.univ_rouen.leeea.connectfourfx.controller;

import fr.univ_rouen.leeea.connectfourfx.model.ConnectFour;
import fr.univ_rouen.leeea.connectfourfx.view.ConnectFourPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ConnectFourApplication extends Application {
    @Override
    public void start(Stage stage) {

        // création du modèle
        ConnectFour connectFour = new ConnectFour();
        // création de la vue en lien avec le modèle
        ConnectFourPane connectFourPane = new ConnectFourPane(connectFour);
        // le modèle reçoit une référence vers la vue
        connectFour.setConnectFourPane(connectFourPane);

        // action déclenchée au clic d'un bouton
        Button[] buttons = connectFourPane.getButtons();
        for (int i = 0; i < buttons.length; i++) {
            final int columnIndex = i;
            buttons[columnIndex].setOnAction(event -> {
                // bloc déclenché au clic d'un bouton
                System.out.println("Button #" + columnIndex + " clicked!");
                connectFour.fill(columnIndex);
                // fin du bloc
            });
        }

        // création et affichage de la fenêtre graphique
        Scene scene = new Scene(connectFourPane);
        stage.setTitle("Connect four");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}