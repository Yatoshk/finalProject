package repository;

import DataSource.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Game {

    public char[][] playingField = new char[3][3];
    public ArrayList<String> availableMoves = new ArrayList<>();
    public ArrayList<String> availableMovesCopy = new ArrayList<>();
    public ArrayList<String> madeMoves = new ArrayList<>();
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
                availableMovesCopy.add(i + ""+ j + "");
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
        int indx = findOldGame(player1.getFio(), player2.getFio(), listOldGames);
        if (indx != -1){
            System.out.println("Do you want to continue the old game? (y/n)");
            Scanner ans = new Scanner(System.in);
            String answer = ans.nextLine();
            if (answer.equals("y")){
                contGame = true;
                for (var v: availableMovesCopy){
                    if (listOldGames.get(indx).moves.contains(v)) {
                        count++;
                        madeMoves.add(v);
                        availableMoves.remove(v);
                    }
                }
                char k = ' ';
                if (Objects.equals(listOldGames.get(indx).sign1, "x")){
                    k = 'x';
                    System.out.println("Turn player 1 == x, player 2 == o. ");
                }
                else if (Objects.equals(listOldGames.get(indx).sign1, "o")){
                    k = 'o';
                    System.out.println("Turn player 1 == o, player 2 == x. ");
                }
                for(var v: madeMoves){
                    switch (v) {
                        case "a1" -> playingField[0][0] = k;
                        case "a2" -> playingField[0][1] = k;
                        case "a3" -> playingField[0][2] = k;
                        case "b1" -> playingField[1][0] = k;
                        case "b2" -> playingField[1][1] = k;
                        case "b3" -> playingField[1][2] = k;
                        case "c1" -> playingField[2][0] = k;
                        case "c2" -> playingField[2][1] = k;
                        case "c3" -> playingField[2][2] = k;
                    }
                    if (k == 'x')
                        k = 'o';
                    else
                        k = 'x';
                }
                System.out.println(" ");
                System.out.println(" " + playingField[0][0] + "  | " + playingField[0][1] + " |  " + playingField[0][2] + " ");
                System.out.println(" -----------");
                System.out.println(" " + playingField[1][0] + "  | " + playingField[1][1] + " |  " + playingField[1][2] + " ");
                System.out.println(" -----------");
                System.out.println(" " + playingField[2][0] + "  | " + playingField[2][1] + " |  " + playingField[2][2] + " ");
                //
                System.out.println("You make moves: ");
                for (var v: availableMoves){
                    System.out.print(v + " ");
                }
                System.out.println("");

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
        boolean check = true;
        int flag = 0;
        String m1, m2;
        Scanner s1 = new Scanner(System.in);
        Scanner s2 = new Scanner(System.in);
        do{
            if(count % 2 == 0 && check){
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
            }
            if (count % 2 != 0 && check){
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

        System.out.println(" " + playingField[0][0] + "  | " + playingField[0][1] + " |  " + playingField[0][2] + " ");
        System.out.println(" -----------");
        System.out.println(" " + playingField[1][0] + "  | " + playingField[1][1] + " |  " + playingField[1][2] + " ");
        System.out.println(" -----------");
        System.out.println(" " + playingField[2][0] + "  | " + playingField[2][1] + " |  " + playingField[2][2] + " ");

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

    private int findOldGame(String fio1, String fio2, List<SaveGame> list){
        int count = 0;
        for (var v: list){
            if (fio1.equals(v.fio1) && fio2.equals(v.fio2))
            {
                player1.sign = v.sign1;
                player2.sign = v.sign2;
                return count;
            }
            count++;
        }
        return -1;
    }
}
