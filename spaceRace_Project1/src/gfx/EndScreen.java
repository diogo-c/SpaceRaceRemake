package gfx;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class EndScreen {
    private Picture endScreen;
    private Picture playerImage1;
    private Picture playerImage2;
    private Picture playerImage3;
    private Picture playerMessage;
    private int victoriousPlayer;
    private int playerImageX;
    private int playerImageY;
    private int playerMessageX;
    private int playerMessageY;


    public EndScreen(int victoriousPlayer) {
        endScreen = new Picture(10, 10, "resources/endScreen.jpg");
        this.victoriousPlayer = victoriousPlayer;
        playerImageX = 713;
        playerImageY = 450;
        playerMessageX = 405;
        playerMessageY = 175;
        victoriousPlayerGFX();
    }

    public void victoriousPlayerGFX() {
        if (victoriousPlayer == 1) {
            playerImage1 = new Picture(playerImageX, playerImageY, "resources/player1_1.png");
            playerImage2 = new Picture(playerImageX, playerImageY, "resources/player1_2.png");
            playerImage3 = new Picture(playerImageX, playerImageY, "resources/player1_3.png");
            playerMessage = new Picture(playerMessageX,playerMessageY, "resources/player1winsText.png");
        } else {
            playerImage1 = new Picture(playerImageX, playerImageY, "resources/player2_1.png");
            playerImage2 = new Picture(playerImageX, playerImageY, "resources/player2_2.png");
            playerImage3 = new Picture(playerImageX, playerImageY, "resources/player2_3.png");
            playerMessage = new Picture(playerMessageX,playerMessageY, "resources/player2winsText.png");
        }
    }

    public void drawEndScreen() {
        endScreen.draw();
    }

    public void drawPlayerImage1() {
        playerImage3.delete();
        playerImage1.draw();
    }

    public void drawPlayerImage2() {
        playerImage1.delete();
        playerImage2.draw();
    }

    public void drawPlayerImage3() {
        playerImage2.delete();
        playerImage3.draw();
    }

    public void drawPlayerMessage() {
        playerMessage.draw();
    }

    public void deletePlayerMessage() {
        playerMessage.delete();
    }

    public void deleteAllEndScreenImages() {
        endScreen.delete();
        playerMessage.delete();
        playerImage1.delete();
        playerImage2.delete();
        playerImage3.delete();
    }
}
