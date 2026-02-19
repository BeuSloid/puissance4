package fr.univ_rouen.leeea.connectfourfx.model;

public class Column {
    private Player[] spots;
    private int fillingLevel;

    public Column(int height)
    {
        spots = new Player[height];
        for (int i = 0 ; i < height ; i++)
        {
            this.spots[i] = Player.NONE;
        }
        fillingLevel = 0;
    }

    public int getFillingLevel(){
        return fillingLevel;
    }
    public void setFillingLevel(int fillingLevel){
        this.fillingLevel = fillingLevel;
    }

    public void fill(Player disc){
        this.spots[fillingLevel] = disc;
        this.fillingLevel++;
    }

    /**
     * Resets the column to empty state
     */
    public void reset() {
        for (int i = 0; i < spots.length; i++) {
            this.spots[i] = Player.NONE;
        }
        this.fillingLevel = 0;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Column: filling level = ");
        sb.append(fillingLevel);
        sb.append('\n');
        for (int i = 0; i < spots.length; i++){
            sb.append("\n");
            sb.append(i);
            sb.append(" ");
            sb.append(spots[i].getDescription());
        }
        return sb.toString();
    }

    public Player[] getSpots() {
        return spots;
    }

    public Player getSpot(int index){
        return spots[index];
    }
}