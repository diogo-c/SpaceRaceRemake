package gfx;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class StartMenu {

    private Picture startMenu;
    private Picture pressSpaceBarMessage;
    private Picture player1Image1;
    private Picture player1Image2;
    private Picture player1Image3;
    private Picture player2Image1;
    private Picture player2Image2;
    private Picture player2Image3;
    private int player1X;
    private int player2X;
    private int playersY;


    public StartMenu() {
        startMenu = new Picture(10, 10, "resources/screenMenu.jpg");
        pressSpaceBarMessage = new Picture(588, 300, "resources/pressSpaceBarToStart.png");
        player1X = 100;
        player2X = 1370;
        playersY = 550;
        player1Image1 = new Picture(player1X, playersY, "resources/player1_1.png");
        player1Image2 = new Picture(player1X, playersY, "resources/player1_2.png");
        player1Image3 = new Picture(player1X, playersY, "resources/player1_3.png");
        player2Image1 = new Picture(player2X, playersY, "resources/player2_1.png");
        player2Image2 = new Picture(player2X, playersY, "resources/player2_2.png");
        player2Image3 = new Picture(player2X, playersY, "resources/player2_3.png");
    }

    public void drawStartMenuImage() {
        startMenu.draw();
    }

    public void drawSpaceBarMessage() {
        pressSpaceBarMessage.draw();
    }

    public void drawPlayer1Image1() {
        player1Image3.delete();
        player1Image1.draw();
    }

    public void drawPlayer1Image2() {
        player1Image1.delete();
        player1Image2.draw();
    }

    public void drawPlayer1Image3() {
        player1Image2.delete();
        player1Image3.draw();
    }

    public void drawPlayer2Image1() {
        player2Image3.delete();
        player2Image1.draw();
    }

    public void drawPlayer2Image2() {
        player2Image1.delete();
        player2Image2.draw();
    }

    public void drawPlayer2Image3() {
        player2Image2.delete();
        player2Image3.draw();
    }

    public void deleteSpaceBarMessage() {
        pressSpaceBarMessage.delete();
    }

    public void deleteStartMenuImage() {
        startMenu.delete();
    }

    public void deleteStartMenuPlayerImages() {
        player1Image1.delete();
        player1Image2.delete();
        player1Image3.delete();
        player2Image1.delete();
        player2Image2.delete();
        player2Image3.delete();
    }
}
