package repository;

public class Game {

    RegistrationPlayer pl = new RegistrationPlayer();
    Player player1 = pl.loginVerification();
    //Player player2 = pl.loginVerification();

    //вывод данных двух игроков
    public void  startGame() {


        System.out.println("         ");
        player1.view(player1);
        //player2.view(player2);
    }
    //выбор 0 Х
    //начало игры

    //вывод каждого хода + сохранение промежуточных результатов(после рестарта и рег тех же игроков
    // будет выбор продолжить игру или закончить ее)

    //обновление данных
}
