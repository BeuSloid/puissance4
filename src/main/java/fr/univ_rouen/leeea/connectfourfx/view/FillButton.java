package fr.univ_rouen.leeea.connectfourfx.view;

import fr.univ_rouen.leeea.connectfourfx.model.Player;
import javafx.scene.control.Button;

/**
 * Button used to fill a column with a disc
 */
public class FillButton extends Button {

    private final PlayerImageView playerImageView;

    /**
     * Button with arrow image
     */
    public FillButton() {
        playerImageView = new PlayerImageView("fill");
        setGraphic(playerImageView);
        setPlayer(Player.NONE);
    }

    /**
     * set button image corresponding to the player
     * @param player player value
     */
    public void setPlayer(Player player) {
        playerImageView.setPlayer(player);
    }
}
