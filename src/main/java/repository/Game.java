package repository;

import DataSource.Data;

import java.util.Objects;
import java.util.Scanner;

public class Game {

    public int[][] playingField = new int[3][3];
    Data d = new Data();
    RegistrationPlayer pl = new RegistrationPlayer();
    Player player1 = pl.loginVerification();
    Player player2 = pl.loginVerification();

    //todo структура данных хранящая шаги игры для продолжения после перезапуска

    public void  startGame() {

        for (int i = 0; i < 3; i++){
            for (int j = 0;j < 3; j++) {
                playingField[i][j] = 2;
            }
        }

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
        System.out.println("0. - first and o ");
        System.out.println("1. - second and x");
        int turn;
        Scanner s = new Scanner(System.in);
        turn = s.nextInt();
        if (turn == 0){
            player1.sign = "o";
            player2.sign = "x";
            System.out.println("Turn player 1 == o, player 2 == x. ");
        }
        else if (turn == 1){
            player1.sign = "x";
            player2.sign = "o";
            System.out.println("Turn player 1 == x, player 2 == o. ");
        }

        System.out.println("choice of moves:");
        System.out.println(" a1  | a2 |  a3");
        System.out.println(" ---------------");
        System.out.println(" b1  | b2 |  b3");
        System.out.println(" ---------------");
        System.out.println(" c1  | c2 |  c3");
        boolean check;
        int flag, count = 0;
        String m1, m2;
        Scanner s1 = new Scanner(System.in);
        Scanner s2 = new Scanner(System.in);
        do{
            System.out.println("choice of moves pl1:");
            m1 = s1.nextLine();
            player1.move = m1;
            check = checkingMoves(player1, playingField, count);
            count++;
            flag = 1;
            if (check){
                System.out.println("choice of moves pl2:");
                m2 = s2.nextLine();
                player2.move = m2;
                check = checkingMoves(player2, playingField, count);
                count++;
                flag = 2;
            }
        } while (check);

        if (count == 8){
            System.out.println("steps is ended");
            d.updateData(player1, 0);
            d.updateData(player2, 0);
        } else {
            if (flag == 1){
                d.updateData(player1, 1);
                d.updateData(player2, 0);
            } else {
                d.updateData(player1, 0);
                d.updateData(player2, 1);
            }
        }

        System.out.println("Update data all players: ");
        pl.viewLiders("");


        //todo 1. вывод вместо 1, 0 - Х, О
        //todo 2. продумать случай повторного выбора клетки
        //todo 3. доделать случай заполнения всех клеток(83 стр)
    }

    private boolean  checkingMoves(Player player, int[][] playingField, int c){

        if (c == 8)
            return false;
        int t;
        if(Objects.equals(player.sign, "o"))
            t = 0;
        else
            t = 1;

        switch (player.move) {
            case "a1" -> playingField[0][0] = t;
            case "a2" -> playingField[0][1] = t;
            case "a3" -> playingField[0][2] = t;
            case "b1" -> playingField[1][0] = t;
            case "b2" -> playingField[1][1] = t;
            case "b3" -> playingField[1][2] = t;
            case "c1" -> playingField[2][0] = t;
            case "c2" -> playingField[2][1] = t;
            case "c3" -> playingField[2][2] = t;
        }

        //вывод ходов

        System.out.println(" " + playingField[0][0] + "  | " + playingField[0][1] + " |  " + playingField[0][2] + " ");
        System.out.println(" ---------------");
        System.out.println(" " + playingField[1][0] + "  | " + playingField[1][1] + " |  " + playingField[1][2] + " ");
        System.out.println(" ---------------");
        System.out.println(" " + playingField[2][0] + "  | " + playingField[2][1] + " |  " + playingField[2][2] + " ");

        //проверка на ходы todo улучшить
        // ---
        if (playingField[0][0] != 2 && playingField[0][0] == playingField[0][1] && playingField[0][1] == playingField[0][2])
        {
            System.out.println("Win: "+ player.getFio());
            return false;
        }
        if (playingField[1][0] != 2 && playingField[1][0] == playingField[1][1] && playingField[1][1] == playingField[1][2])
        {
            System.out.println("Win: "+ player.getFio());
            return false;
        }
        if (playingField[2][0] != 2 && playingField[2][0] == playingField[2][1] && playingField[2][1] == playingField[2][2])
        {
            System.out.println("Win: "+ player.getFio());
            return false;
        }
        //|||
        if (playingField[0][0] != 2 && playingField[0][0] == playingField[1][0] && playingField[1][0] == playingField[2][0])
        {
            System.out.println("Win: "+ player.getFio());
            return false;
        }
        if (playingField[0][1] != 2 && playingField[0][1] == playingField[1][1] && playingField[1][1] == playingField[2][1])
        {
            System.out.println("Win: "+ player.getFio());
            return false;
        }
        if (playingField[0][2] != 2 && playingField[0][2] == playingField[1][2] && playingField[1][2] == playingField[2][2])
        {
            System.out.println("Win: "+ player.getFio());
            return false;
        }
        //  X
        if (playingField[0][0] != 2 && playingField[0][0] == playingField[1][1] && playingField[1][1] == playingField[2][2])
        {
            System.out.println("Win: "+ player.getFio());
            return false;
        }
        if (playingField[2][0] != 2 && playingField[2][0] == playingField[1][1] && playingField[1][1] == playingField[0][2])
        {
            System.out.println("Win: "+ player.getFio());
            return false;
        }
        return true;
    }
    //вывод каждого хода + сохранение промежуточных результатов(после рестарта и рег тех же игроков
    // будет выбор продолжить игру или закончить ее)


}
