package repository;

import DataSource.ApplDataSource;
import DataSource.Data;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Data data = new Data();
        List<RegistrationPlayer> players = data.dataList();

        for(var g: players){
            System.out.println("log: " +g.getLogin() +" Pass: " + g.getPassword()+"Fio: " + g.fio + " Win: " + g.win + " Lose: " + g.lose);
        }

    }
}