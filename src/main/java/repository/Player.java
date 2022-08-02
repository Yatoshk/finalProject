package repository;

public class Player {

    private String fio;
    private String win;
    private String lose;

    public Player(String fio, String win, String lose) {
        this.fio = fio;
        this.win = win;
        this.lose = lose;
    }

    public Player() {
    }

    public void view(Player p){
        System.out.println("Фио: " + p.getFio() + " Победы: " + p.getWin() + " Поражения: " + p.getLose());
    }
    //очередь хода 0 - доступен ход 1 - ход противника
    //выбор клетки

    public String getFio() {
        return fio;
    }

    public String getWin() {
        return win;
    }

    public String getLose() {
        return lose;
    }
}
