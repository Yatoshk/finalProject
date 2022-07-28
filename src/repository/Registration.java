package repository;

public class Registration {

    private static String login;
    private static String password;

    public static String fio;

    public String win;

    public String lose;

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
        Registration.login = login;
    }

    public static void setPassword(String password) {
        Registration.password = password;
    }

    public static String getLogin() {
        return login;
    }

    public static String getPassword() {
        return password;
    }
}
