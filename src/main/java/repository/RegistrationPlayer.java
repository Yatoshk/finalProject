package repository;

import DataSource.ApplDataSource;
import DataSource.Data;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class RegistrationPlayer {

    private static String login;
    private static String password;

    public static String fio;

    public String win;

    public String lose;

    private RegistrationPlayer findLogin(String login, List<RegistrationPlayer> players){
        for (var p: players){
            if (p.login.equals(login)) {
                return p;
            }
        }
        return null;
    }

    public RegistrationPlayer(String login, String password, String fio, int win, int lose) {
        this.login = login;
        this.password = password;
        this.fio = fio;
        this.win = String.valueOf(win);
        this.lose = String.valueOf(lose);
    }

    public RegistrationPlayer() {

    }

    public Player loginVerification(){
        Data data = new Data();
        RegistrationPlayer pl = new RegistrationPlayer();
        List<RegistrationPlayer> players = data.dataList();
        String log, pas;
        Scanner s = new Scanner(System.in);
        System.out.println("Введите логин: ");
        log = s.nextLine();
        System.out.println("Введите пароль: ");
        pas = s.nextLine();
        RegistrationPlayer player = findLogin(log, players);
        if (log.equals(player.login) && pas.equals(player.password)){
            Player player1 = new Player(player.fio, player.win, player.lose);
            return player1;
        }
        else {
            System.out.println("Вас нет в системе. Регистраниция: ");
            System.out.println("Введите fio: ");
            String fio = s.nextLine();
            data.addPlayer(log, pas, fio);
            System.out.println("Вы зарегестрированны: ");
            pl.loginVerification();
        }

        return null;
    }

    public void viewLiders(){
        //todo sort
        Data data = new Data();
        List<RegistrationPlayer> players = data.dataList();

        for(var g: players){
            System.out.println("Fio: " + g.fio + " Win: " + g.win + " Lose: " + g.lose);
        }
    }



    public static void setLogin(String login) {
        RegistrationPlayer.login = login;
    }

    public static void setPassword(String password) {
        RegistrationPlayer.password = password;
    }

    public static String getLogin() {
        return login;
    }

    public static String getPassword() {
        return password;
    }
}
