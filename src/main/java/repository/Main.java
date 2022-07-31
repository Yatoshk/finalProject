package repository;

import DataSource.ApplDataSource;
import DataSource.Data;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Data d = new Data();
        List<RegistrationPlayer> p = d.findAll().stream().toList();

        for(var g: p){
            System.out.println("Log: " + g.getLogin()+ " Pass: " +  g.getPassword() +
                    " Fio: " + g.fio + " Win: " + g.win + " Lose: " + g.lose);
        }

    }
}