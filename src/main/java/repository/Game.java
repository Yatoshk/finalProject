package repository;

import java.util.Scanner;

public class Game {

    public int[][] playingField = new int[3][3];
    RegistrationPlayer pl = new RegistrationPlayer();
    Player player1 = pl.loginVerification();
    Player player2 = pl.loginVerification();

    //вывод данных двух игроков
    public void  startGame() {


        System.out.println("         ");
        if (player1 == null) {
            player1 = pl.loginVerification();
            System.out.println("         ");
        }
        if (player2 == null){
            player2 = pl.loginVerification();
            System.out.println("         ");
        }

        player1.view(player1);
        player2.view(player2);

        System.out.println("Turn player 1: ");
        System.out.println("0. - o ");
        System.out.println("1. - x ");
        int turn;
        Scanner s = new Scanner(System.in);
        turn = s.nextInt();
        if (turn == 0){
            player1.turn = 0;
            player2.turn = 1;
            System.out.println("Turn player 1 == o, player 1 == x. ");
        }
        else if (turn == 1){
            player1.turn = 1;
            player2.turn = 0;
            System.out.println("Turn player 1 == x, player 1 == o. ");
        }

        System.out.println("choice of moves:");
        System.out.println(" a1  | a2 |  a3");
        System.out.println(" ---------------");
        System.out.println(" b1  | b2 |  b3");
        System.out.println(" ---------------");
        System.out.println(" c1  | c2 |  c3");

        /*while(checkingMoves(Player player1, Player player2, int[][] playingField) == true){
            //выбор игроками двух ходов
            String m1, m2;
            System.out.println("choice of moves pl1:");
            m1 = s.nextLine();
            System.out.println("choice of moves pl2:");
            m2 = s.nextLine();
            player1.move = m1;
            player2.move = m2;
            //todo
        }*/

    }

    private /*boolean*/void  checkingMoves(Player player1, Player player2, int[][] playingField){



        player1.swapTurn();
        player2.swapTurn();
        //todo
    }
    //вывод каждого хода + сохранение промежуточных результатов(после рестарта и рег тех же игроков
    // будет выбор продолжить игру или закончить ее)

    //обновление данных
}
