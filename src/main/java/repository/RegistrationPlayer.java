package repository;

import java.util.Set;

public class RegistrationPlayer {

    private static String login;
    private static String password;

    public static String fio;

    public String win;

    public String lose;

    public RegistrationPlayer(String login, String password, String fio, int win, int lose) {
        this.login = login;
        this.password = password;
        this.fio = fio;
        this.win = String.valueOf(win);
        this.lose = String.valueOf(lose);
    }

    public void loginVerification(String login){
        //todo
        /*если логин уже есть в бд, то добавить пользователя к игрокам,
        * если нет, то пользователь вности данные(фио, win = 0, lose = 0) далее заносится в бд
        * и добавляется к игрокам*/

    }

    public void viewLiders(){
        //todo
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
