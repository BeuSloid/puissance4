package fr.univ_rouen.leeea.connectfourfx.view;

import fr.univ_rouen.leeea.connectfourfx.model.Column;
import fr.univ_rouen.leeea.connectfourfx.model.ConnectFour;
import fr.univ_rouen.leeea.connectfourfx.model.Player;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import static fr.univ_rouen.leeea.connectfourfx.model.ConnectFour.HEIGHT;
import static fr.univ_rouen.leeea.connectfourfx.model.ConnectFour.NB_COLONNES;

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
        int columnNb = NB_COLONNES;
        int columnHeight = HEIGHT;
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
            final int columnIndex = i;
            fillButtons[i].setOnAction(e -> {
                if (connectFour.getWinner() == Player.NONE && !connectFour.isFull()) {
                    connectFour.fill(columnIndex);
                }
            });
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
        // Récupérer les colonnes du modèle
        Column[] columns = connectFour.getColumns();

        // Parcourir toutes les colonnes
        for (int col = 0; col < ConnectFour.NB_COLONNES; col++) {
            Column column = columns[col];
            int fillingLevel = column.getFillingLevel();

            // Parcourir toutes les lignes de cette colonne
            for (int row = 0; row < ConnectFour.HEIGHT; row++) {
                SpotView spotView = spotViews[col][row];

                if (row < fillingLevel) {
                    // Cette case contient un jeton
                    Player player = column.getSpot(row);
                    spotView.setDisc(player);
                } else {
                    // Cette case est vide
                    spotView.setDisc(Player.NONE);
                }
            }
        }

        // Mettre à jour les boutons de colonnes
        updateColumnButtons();

        // Mettre à jour le message d'information
        updateInfoText();

        // Mettre à jour l'affichage du gagnant ou du match nul si nécessaire
        if (connectFour.getWinner() != Player.NONE) {
            showWinner(connectFour.getWinner());
        } else if (connectFour.isFull()) {
            showDraw();
        }
    }

    /**
     * Updates column buttons (arrows) visibility and color
     */
    private void updateColumnButtons() {
        Player winner = connectFour.getWinner();
        boolean gameEnded = (winner != Player.NONE) || connectFour.isFull();

        if (gameEnded) {
            // Cacher toutes les flèches si la partie est terminée
            for (FillButton button : fillButtons) {
                button.setVisible(false);
            }
        } else {
            // Afficher les flèches selon l'état des colonnes et le joueur courant
            Player currentPlayer = getCurrentPlayer();

            for (int col = 0; col < ConnectFour.NB_COLONNES; col++) {
                FillButton button = fillButtons[col];
                boolean isColumnFull = connectFour.getColumns()[col].getFillingLevel() >= ConnectFour.HEIGHT;

                button.setPlayer(currentPlayer);
                button.setVisible(!isColumnFull);
            }
        }
    }

    /**
     * Updates information text based on game state
     */
    private void updateInfoText() {
        Player winner = connectFour.getWinner();

        if (winner != Player.NONE) {
            // Message de victoire
            infoText.setText("Player " + getPlayerNumber(winner) + " wins!");
            infoText.setStyle("-fx-fill: #2E8B57;"); // Vert pour la victoire
        } else if (connectFour.isFull()) {
            // Message de match nul
            infoText.setText("It's a draw! The board is full!");
            infoText.setStyle("-fx-fill: #FF8C00;"); // Orange pour le match nul
        } else {
            // Message de tour normal
            Player currentPlayer = getCurrentPlayer();
            String playerNumber = getPlayerNumber(currentPlayer);
            String colorStyle = (currentPlayer == Player.ONE) ? "-fx-fill: #DC143C;" : "-fx-fill: rgba(255,215,0,0.78);";

            infoText.setText("Player " + playerNumber + ": please click a column to insert a disc");
            infoText.setStyle(colorStyle);
        }
    }

    /**
     * Determines current player based on number of tokens
     */
    private Player getCurrentPlayer() {
        int totalTokens = 0;
        for (Column column : connectFour.getColumns()) {
            totalTokens += column.getFillingLevel();
        }
        return (totalTokens % 2 == 0) ? Player.ONE : Player.TWO;
    }

    /**
     * Converts Player enum to player number string
     */
    private String getPlayerNumber(Player player) {
        switch (player) {
            case ONE: return "1";
            case TWO: return "2";
            default: return "";
        }
    }

    /**
     * Shows winner message and hides all arrows
     * @param winner the winning player
     */
    public void showWinner(Player winner) {
        infoText.setText("Player " + getPlayerNumber(winner) + " wins!");
        infoText.setStyle("-fx-fill: #2E8B57;");

        // Cacher toutes les flèches
        for (FillButton button : fillButtons) {
            button.setVisible(false);
        }
    }

    /**
     * Shows draw message and hides all arrows
     */
    public void showDraw() {
        infoText.setText("It's a draw! The board is full!");
        infoText.setStyle("-fx-fill: #FF8C00;");

        // Cacher toutes les flèches
        for (FillButton button : fillButtons) {
            button.setVisible(false);
        }
    }

    /**
     * Updates current player display
     * @param player the current player
     */
    public void updateCurrentPlayer(Player player) {
        updateColumnButtons();
        updateInfoText();
    }

    /**
     * Resets the board display
     */
    public void resetBoard() {
        for (int col = 0; col < ConnectFour.NB_COLONNES; col++) {
            for (int row = 0; row < ConnectFour.HEIGHT; row++) {
                spotViews[col][row].setDisc(Player.NONE);
            }
        }

        // Réactiver tous les boutons
        for (FillButton button : fillButtons) {
            button.setVisible(true);
        }

        updateColumnButtons();
        updateInfoText();
    }

    /**
     * Updates a specific cell
     * @param row the row index
     * @param column the column index
     * @param player the player who placed the disc
     */
    public void updateCell(int row, int column, Player player) {
        if (column >= 0 && column < ConnectFour.NB_COLONNES &&
                row >= 0 && row < ConnectFour.HEIGHT) {
            spotViews[column][row].setDisc(player);
        }
    }
}