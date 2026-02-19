package fr.univ_rouen.leeea.connectfourfx.view;

import fr.univ_rouen.leeea.connectfourfx.model.Player;

/**
 * ImageView for a board spot
 */
public class SpotView extends PlayerImageView {

    /**
     * Create a spot
     */
    public SpotView() {
        super("disc");
        setDisc(Player.NONE);
    }

    /**
     * Set the disc displayed corresponding to player value
     * @param player player value
     */
    public void setDisc(Player player) {
       setPlayer(player);
    }
}
