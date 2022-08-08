package repository;

import DataSource.Data;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class RegistrationPlayer {

    private String login;
    private String password;

    public String fio;

    public int win;
    public int lose;

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
        this.win = win;
        this.lose = lose;
    }

    public RegistrationPlayer() {

    }

    public Player loginVerification(){
        Data data = new Data();
        RegistrationPlayer pl = new RegistrationPlayer();
        List<RegistrationPlayer> players = data.dataList();
        String log, pas;
        Scanner s = new Scanner(System.in);
        System.out.println("Enter login: ");
        log = s.nextLine();
        System.out.println("Enter password: ");
        pas = s.nextLine();
        RegistrationPlayer player = findLogin(log, players);

        if (player == null){
            System.out.println("You are not in the system. Registerpage: ");
            System.out.println("Enter fio: ");
            String fio = s.nextLine();
            data.addPlayer(log, pas, fio);
            System.out.println("You are registered. ");
        }
        else if (log.equals(player.login) && pas.equals(player.password)){
            Player pla = new Player(player.fio, player.win, player.lose);
            return pla;
        }
        return null;
    }
    //"" - list "-w" - list sorted by wins "-l" - list sorted by loses
    public void viewLiders(String command){
        Data data = new Data();
        List<RegistrationPlayer> players = data.dataList();
        
        boolean isNeededWins = false, isNeededLoses = false;
        
        if (command.equals("-w"))
            isNeededWins = true;
        else if (command.equals("-l"))
            isNeededLoses = true;

        if (isNeededWins) {
            players= players.stream()
                    .sorted(Comparator.comparingInt(RegistrationPlayer::getWin).reversed())
                    .collect(Collectors.toList());
        }

        if (isNeededLoses) {
            players= players.stream()
                    .sorted(Comparator.comparingInt(RegistrationPlayer::getLose).reversed())
                    .collect(Collectors.toList());
        }

        for(var g: players){
            System.out.println("Fio: " + g.fio + " Win: " + g.win + " Lose: " + g.lose);
        }
    }

    public int getWin() {
        return win;
    }

    public int getLose() {
        return lose;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

}
