package fr.univ_rouen.leeea.connectfourfx.model;

import fr.univ_rouen.leeea.connectfourfx.view.ConnectFourPane;

public class ConnectFour {
    static final public int NB_COLONNES = 7;
    static final public int HEIGHT = 6;

    private Column[] columns;
    private Player winner = Player.NONE;
    private Player currentPlayer = Player.ONE;
    private ConnectFourPane connectFourPane;

    public void setConnectFourPane(ConnectFourPane connectFourPane) {
        this.connectFourPane = connectFourPane;
    }

    public void fill(int columnIndex){
        /*
          • l'appui sur un bouton déclenche l'ajout d'un disque dans la colonne correspondante
          • cette méthode appelle la mise à jour de connectFourPane
        */
        if (winner != Player.NONE || !isValidIndex(columnIndex)){
            return;
        }

        // Ajouter le disque dans la colonne
        columns[columnIndex].fill(currentPlayer);
        int row = columns[columnIndex].getFillingLevel() - 1;

        // Vérifier les conditions de victoire
        if (checkDiagonalDown(columnIndex, currentPlayer) ||
                checkDiagonalUp(columnIndex, currentPlayer) ||
                checkHorizontal(columnIndex, currentPlayer) ||
                checkVertical(columnIndex, currentPlayer)) {
            winner = currentPlayer;
        }

        // Mettre à jour l'interface graphique
        if (connectFourPane != null) {
            connectFourPane.updateCell(row, columnIndex, currentPlayer);

            if (winner != Player.NONE) {
                connectFourPane.showWinner(winner);
            } else if (isFull()) {
                connectFourPane.showDraw();
            } else {
                // Changer de joueur
                currentPlayer = (currentPlayer == Player.ONE) ? Player.TWO : Player.ONE;
                connectFourPane.updateCurrentPlayer(currentPlayer);
            }
        }
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Column[] getColumns() {
        return columns;
    }

    public Player getWinner() {
        return winner;
    }

    public void setColumns(Column[] columns) {
        this.columns = columns;
    }

    public void fill(int index, Player disc) {
        this.columns[index].fill(disc);
        if (checkVertical(index, disc)) {
            winner = disc;
        }
        if (checkHorizontal(index, disc)) {
            winner = disc;
        }
        if (checkDiagonalUp(index, disc)){
            winner = disc;
        }
        if (checkDiagonalDown(index, disc)){
            winner = disc;
        }
    }

    public ConnectFour() {
        columns = new Column[NB_COLONNES];
        for (int i = 0; i < NB_COLONNES; i++) {
            columns[i] = new Column(HEIGHT);
        }
    }

    public boolean isFull() {
        for (int i = 0; i < NB_COLONNES; i++) {
            if (columns[i].getFillingLevel() < HEIGHT) {
                return false;
            }
        }
        return true;
    }

    public boolean isValidIndex(int index) {
        if (index < 0 || index >= NB_COLONNES) {
            return false;
        }
        if (columns[index].getFillingLevel() == HEIGHT) {
            return false;
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = HEIGHT - 1; i >= 0; i--) {
            for (int j = 0; j < NB_COLONNES; j++) {
                sb.append("|");
                sb.append(columns[j].getSpot(i).getDescription());
            }
            sb.append("|\n");
        }
        for (int i = 0; i < NB_COLONNES; i++) {
            sb.append(" ");
            sb.append(i);
        }
        return sb.toString();
    }

    public boolean checkVertical(int columnIndex, Player player) {
        Column column = columns[columnIndex];
        int resultat = 0;
        for (int i = 0; i < column.getFillingLevel(); i++) {
            if (column.getSpot(i) == player) {
                resultat++;
                if (resultat >= 4) {
                    return true;
                }
            } else {
                resultat = 0;
            }
        }
        return false;
    }

    public boolean checkHorizontal(int columnIndex, Player player) {
        int currentRow = columns[columnIndex].getFillingLevel() - 1;
        if (currentRow < 0 || currentRow >= HEIGHT) {
            return false;
        }

        int count = 1;

        for (int col = columnIndex - 1; col >= 0; col--) {
            if (columns[col].getFillingLevel() <= currentRow ||
                    columns[col].getSpot(currentRow) != player) {
                break;
            }
            count++;
        }
        for (int col = columnIndex + 1; col < NB_COLONNES; col++) {
            if (columns[col].getFillingLevel() <= currentRow ||
                    columns[col].getSpot(currentRow) != player) {
                break;
            }
            count++;
        }
        return count >= 4;
    }

    public boolean checkDiagonalDown(int columnIndex, Player player) {
        int currentRow = columns[columnIndex].getFillingLevel() - 1;
        if (currentRow < 0 || currentRow >= HEIGHT) return false;

        int count = 1;

        // Haut-gauche (col--, row--)
        for (int col = columnIndex - 1, row = currentRow - 1;
             col >= 0 && row >= 0;
             col--, row--) {
            if (columns[col].getFillingLevel() > row &&
                    columns[col].getSpot(row) == player) {
                count++;
            } else {
                break;
            }
        }

        // Bas-droite (col++, row++)
        for (int col = columnIndex + 1, row = currentRow + 1;
             col < NB_COLONNES && row < HEIGHT;
             col++, row++) {
            if (columns[col].getFillingLevel() > row &&
                    columns[col].getSpot(row) == player) {
                count++;
            } else {
                break;
            }
        }
        return count >= 4;
    }

    public boolean checkDiagonalUp(int columnIndex, Player player) {
        int currentRow = columns[columnIndex].getFillingLevel() - 1;
        if (currentRow < 0 || currentRow >= HEIGHT) return false;

        int count = 1;

        // Bas-gauche (col--, row++)
        for (int col = columnIndex - 1, row = currentRow + 1;
             col >= 0 && row < HEIGHT;
             col--, row++) {
            if (columns[col].getFillingLevel() > row &&
                    columns[col].getSpot(row) == player) {
                count++;
            } else {
                break;
            }
        }

        // Haut-droite (col++, row--)
        for (int col = columnIndex + 1, row = currentRow - 1;
             col < NB_COLONNES && row >= 0;
             col++, row--) {
            if (columns[col].getFillingLevel() > row &&
                    columns[col].getSpot(row) == player) {
                count++;
            } else {
                break;
            }
        }

        return count >= 4;
    }
}