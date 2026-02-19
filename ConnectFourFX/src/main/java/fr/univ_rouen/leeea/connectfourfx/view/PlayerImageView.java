package fr.univ_rouen.leeea.connectfourfx.view;

import fr.univ_rouen.leeea.connectfourfx.model.Player;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.EnumMap;

/**
 * ImageView that can show an image for each Player possible values
 */
public class PlayerImageView extends ImageView {

    private final EnumMap<Player, Image> images;

    /**
     * Load and set the images with prefix specified
     * @param prefix prefix for each image name
     */
    public PlayerImageView(String prefix) {
        images = new EnumMap<>(Player.class);
        images.put(Player.ONE, new Image(prefix + "_one.png"));
        images.put(Player.TWO, new Image(prefix + "_two.png"));
        images.put(Player.NONE, new Image(prefix + "_none.png"));
    }

    /**
     * Set the ImageView image
     * @param player player value
     */
    public void setPlayer(Player player) {
        setImage(images.get(player));
    }
}
