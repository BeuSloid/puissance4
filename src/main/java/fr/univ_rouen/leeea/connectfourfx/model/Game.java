package fr.univ_rouen.leeea.connectfourfx.model;

import fr.univ_rouen.leeea.connectfourfx.model.ConnectFour;
import fr.univ_rouen.leeea.connectfourfx.model.Player;

import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        ConnectFour connectFour = new ConnectFour();
        Player current_player = Player.ONE;
        System.out.println(connectFour);
        while (!connectFour.isFull()) {
            System.out.println("Veuillez entrer un indice : ");
            Scanner scanner = new Scanner(System.in);
            int res = Integer.parseInt(scanner.nextLine());
            if(connectFour.isValidIndex(res)){
                connectFour.fill(res, current_player);
                System.out.println(connectFour);
                if(connectFour.checkVertical(res,current_player)){
                    System.out.println("Le joueur "+current_player+" a gagné !");
                    break;
                }
                if(connectFour.checkHorizontal(res,current_player)){
                    System.out.println("Le joueur "+current_player+" a gagné !");
                    break;
                }
                if(connectFour.checkDiagonalDown(res,current_player)){
                    System.out.println("Le joueur "+current_player+" a gagné !");
                    break;
                }
                if(connectFour.checkDiagonalUp(res,current_player)){
                    System.out.println("Le joueur "+current_player+" a gagné !");
                    break;
                }
                if (current_player == Player.ONE) {
                    current_player = Player.TWO;
                } else {
                    current_player = Player.ONE;
                }
            }
        }
    }
}
