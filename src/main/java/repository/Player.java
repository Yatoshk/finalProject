package repository;

public class Player {

    private String fio;
    public int win;
    public int lose;

    public Player(String fio, int win, int lose) {
        this.fio = fio;
        this.win = win;
        this.lose = lose;
    }

    public Player() {
    }

    public void view(Player p){
        System.out.println("Fio: " + p.getFio() + " Wins: " + p.win + " Loses: " + p.lose);
    }
    //очередь хода 0 - доступен ход 1 - ход противника
    //выбор клетки

    public String getFio() {
        return fio;
    }
    /*
    public int getWin() {
        return win;
    }

    public int getLose() {
        return lose;
    }
    */
}
