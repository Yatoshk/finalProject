package repository;

public class Player {

    private String fio;
    public int win;
    public int lose;
    public String move;
    public String sign;

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

    public String getFio() {
        return fio;
    }
}
