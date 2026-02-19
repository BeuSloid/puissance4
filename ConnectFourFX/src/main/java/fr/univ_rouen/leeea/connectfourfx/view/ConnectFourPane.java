package fr.univ_rouen.leeea.connectfourfx.view;

import fr.univ_rouen.leeea.connectfourfx.model.ConnectFour;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Board for "Connect Four" game:
 * <ul>
 *  <li> buttons to fill columns
 *  <li> spots
 *  <li> information text
 * </ul>
 */
public class ConnectFourPane extends GridPane {

    private final SpotView[][] spotViews;
    private final FillButton[] fillButtons;

    private final Text infoText;

    private final ConnectFour connectFour;

    /**
     * Create the board with connectFour model
     * @param connectFour model
     */
    public ConnectFourPane(ConnectFour connectFour) {
        this.connectFour = connectFour;

        // modifiez les valeurs avec les constantes définies dans la classe ConnectFour
        int columnNb = 3;
        int columnHeight = 2;
        // fin des modifications

        spotViews = new SpotView[columnNb][columnHeight];
        for (int i = 0; i < columnNb; i++) {
            for (int h = 0; h < columnHeight; h++) {
                spotViews[i][h] = new SpotView();
                add(spotViews[i][h], i, columnHeight - h);
            }
        }

        fillButtons = new FillButton[columnNb];
        for (int i = 0; i < columnNb; i++) {
            fillButtons[i] = new FillButton();
            add(fillButtons[i], i, 0);
            GridPane.setHalignment(fillButtons[i], HPos.CENTER);
        }

        infoText = new Text("Informations");
        infoText.setFont(Font.font ("Verdana", 20));
        add(infoText, 0, columnHeight + 1, columnNb, 1);
        GridPane.setMargin(infoText, new Insets(5, 10, 5, 10));

        update();

    }

    /**
     * Getter for filling buttons
     * @return filling buttons
     */
    public Button[] getButtons() {
        return fillButtons;
    }

    /**
     * Updates the board regarding model data
     */
    public void update() {
        // mise à jour de la vue du plateau de jeu
    }


}
