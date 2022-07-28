package repository;

public class Player {

    private static String fio;
    private String win;
    private String lose;

    //очередь хода 0 - доступен ход 1 - ход противника
    //выбор клетки

    public static String getFio() {
        return fio;
    }

    public String getWin() {
        return win;
    }

    public String getLose() {
        return lose;
    }
}
