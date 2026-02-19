package fr.univ_rouen.leeea.connectfourfx.model;

public enum Player {
    ONE('X'),
    TWO('O'),
    NONE(' ');
    private char description;
    private Player(char description){
        this.description = description;
    }
    public char getDescription(){
        return description;
    }
}
