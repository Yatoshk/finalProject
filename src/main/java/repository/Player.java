package repository;

public class Player {

    private String fio;
    public int win;
    public int lose;

    public int turn;
    public String move;

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

    public void swapTurn(){
        if (this.turn == 0)
            this.turn = 1;
        else
            this.turn = 0;
    }

    //выбор клетки

    public String getFio() {
        return fio;
    }
}
