package repository;

import DataSource.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Game {

    public char[][] playingField = new char[3][3];
    public ArrayList<String> availableMoves = new ArrayList<>();
    Data d = new Data();
    RegistrationPlayer pl = new RegistrationPlayer();
    Player player1 = pl.loginVerification();
    Player player2 = pl.loginVerification();
    List<SaveGame> listOldGames = d.dataListGame();

    public void  startGame() {

        for (int i = 0; i < 3; i++){
            for (int j = 0;j < 3; j++) {
                playingField[i][j] = ' ';
            }
        }

        for (char i = 'a'; i <= 'c'; i++){
            for (int j = 1; j <= 3; j++) {
                availableMoves.add(i + ""+ j + "");
            }
        }

        int count = 0;

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

        boolean contGame = false;

        if (findOldGame(player1.getFio(), player2.getFio(), listOldGames)){
            System.out.println("Do you want to continue the old game? (y/n)");
            Scanner ans = new Scanner(System.in);
            String answer = ans.nextLine();

            //todo при продолжении игры ошибки: 1. нужно вывести все шаги которые уже использовались + доступные 2. выбираются использованные шаги


            if (answer.equals("y")){
                contGame = true;
                for (var v: availableMoves){
                    if(listOldGames.contains(v)){
                        count++;
                        availableMoves.remove(v);
                    }
                }
            }
            else if (answer.equals("n"))
                d.deleteOldGame(player1.getFio(), player2.getFio());
        }
        if(!contGame) {
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

            d.addGame(player1.getFio(), player2.getFio(), player1.sign, player2.sign);

            System.out.println("choice of moves:");
            System.out.println(" a1  | a2 |  a3");
            System.out.println(" ---------------");
            System.out.println(" b1  | b2 |  b3");
            System.out.println(" ---------------");
            System.out.println(" c1  | c2 |  c3");
        }
        boolean check;
        int flag;
        String m1, m2;
        Scanner s1 = new Scanner(System.in);
        Scanner s2 = new Scanner(System.in);
        do{
            System.out.println("choice of moves pl1:");

            m1 = s1.nextLine();
            while (!checkAvailableMoves(m1, availableMoves)){
                System.out.println("the move has already been made, choose another:");
                m1 = s1.nextLine();
            }
            player1.move = m1;
            count++;
            check = checkingMoves(player1, playingField, count);
            flag = 1;
            if (check){
                System.out.println("choice of moves pl2:");
                m2 = s2.nextLine();
                while (!checkAvailableMoves(m2, availableMoves)){
                    System.out.println("the move has already been made, choose another:");
                    m2 = s1.nextLine();
                }
                player2.move = m2;
                count++;
                check = checkingMoves(player2, playingField, count);
                flag = 2;
            }
        } while (check);

        if (count == 9){
            System.out.println("steps is ended");
            d.updateData(player1, 0);
            d.updateData(player2, 0);
        } else if (count < 9){
            if (flag == 1){
                d.updateData(player1, 1);
                d.updateData(player2, 0);
            } else {
                d.updateData(player1, 0);
                d.updateData(player2, 1);
            }
        }
        d.deleteOldGame(player1.getFio(), player2.getFio());

        System.out.println("Update data all players: ");
        pl.viewLiders("");
    }

    private boolean  checkingMoves(Player player, char[][] playingField, int c){

        d.addGameMove(player.move, player1.getFio(), player2.getFio());
        char t;
        if(Objects.equals(player.sign, "o"))
            t = 'o';
        else
            t = 'x';

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
        System.out.println(" -----------");
        System.out.println(" " + playingField[1][0] + "  | " + playingField[1][1] + " |  " + playingField[1][2] + " ");
        System.out.println(" -----------");
        System.out.println(" " + playingField[2][0] + "  | " + playingField[2][1] + " |  " + playingField[2][2] + " ");

        //проверка на ходы todo улучшить
        // ---
        if (playingField[0][0] != ' ' && playingField[0][0] == playingField[0][1] && playingField[0][1] == playingField[0][2])
        {
            System.out.println("Win: "+ player.getFio());
            return false;
        }
        if (playingField[1][0] != ' ' && playingField[1][0] == playingField[1][1] && playingField[1][1] == playingField[1][2])
        {
            System.out.println("Win: "+ player.getFio());
            return false;
        }
        if (playingField[2][0] != ' ' && playingField[2][0] == playingField[2][1] && playingField[2][1] == playingField[2][2])
        {
            System.out.println("Win: "+ player.getFio());
            return false;
        }
        //|||
        if (playingField[0][0] != ' ' && playingField[0][0] == playingField[1][0] && playingField[1][0] == playingField[2][0])
        {
            System.out.println("Win: "+ player.getFio());
            return false;
        }
        if (playingField[0][1] != ' ' && playingField[0][1] == playingField[1][1] && playingField[1][1] == playingField[2][1])
        {
            System.out.println("Win: "+ player.getFio());
            return false;
        }
        if (playingField[0][2] != ' ' && playingField[0][2] == playingField[1][2] && playingField[1][2] == playingField[2][2])
        {
            System.out.println("Win: "+ player.getFio());
            return false;
        }
        //  X
        if (playingField[0][0] != ' ' && playingField[0][0] == playingField[1][1] && playingField[1][1] == playingField[2][2])
        {
            System.out.println("Win: "+ player.getFio());
            return false;
        }
        if (playingField[2][0] != ' ' && playingField[2][0] == playingField[1][1] && playingField[1][1] == playingField[0][2])
        {
            System.out.println("Win: "+ player.getFio());
            return false;
        }
        if (c == 9)
            return false;
        return true;
    }

    private boolean checkAvailableMoves(String m, ArrayList<String> availableMoves){
        for (var v: availableMoves){
            if (m.equals(v)){
                availableMoves.remove(v);
                return true;
            }
        }
        return false;
    }

    private boolean findOldGame(String fio1, String fio2, List<SaveGame> list){
        for (var v: list){
            if (fio1.equals(v.fio1) && fio2.equals(v.fio2))
            {
                player1.sign = v.sign1;
                player2.sign = v.sign2;
                return true;
            }
        }
        return false;
    }
}
