import sound.Sound;

public class Main {

    public static void main(String[] args) {

        Game game = new Game();
        while (true) {
            Sound sound = new Sound("/resources/gameSound.wav");
            sound.play(true);
            sound.setLoop(10);
            while (!game.hasStarted()) {
                try {
                    game.pressStartBarMessage();
                    game.showPlayersImage1();
                    Thread.sleep(200);
                    if (game.hasStarted()) {
                        break;
                    }
                    game.deleteStartBarMessage();
                    game.showPlayersImage2();
                    Thread.sleep(200);
                    if (game.hasStarted()) {
                        break;
                    }
                    game.pressStartBarMessage();
                    game.showPlayersImage3();
                    Thread.sleep(200);
                    if (game.hasStarted()) {
                        break;
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

            game.start();

            while (!game.isGameOver() && game.hasStarted()) {
                try {
                    Thread.sleep(100);
                    game.move();
                    game.maxScoreCheck();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            sound.stop();
            if (game.isGameOver()) {
                game.showEndScreen();
            }
            Sound endSound = new Sound("/resources/victorySound.wav");
            endSound.play(true);
            endSound.setLoop(20);
            while (!game.hasRestarted()) {
                try {
                    game.showVictoriousPlayerImage1();
                    game.showVictoriousMessage();
                    Thread.sleep(200);
                    game.showVictoriousPlayerImage2();
                    game.deleteVictoriousMessage();
                    Thread.sleep(200);
                    game.showVictoriousPlayerImage3();
                    game.showVictoriousMessage();
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            endSound.stop();
            Game newGame = new Game();
            game = newGame;
        }
    }
}