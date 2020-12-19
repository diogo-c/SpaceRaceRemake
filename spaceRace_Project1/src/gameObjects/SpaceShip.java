package gameObjects;

import gfx.Background;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import sound.Sound;

public class SpaceShip {

    private int score;
    private boolean crashed;
    private boolean movingUp;
    private boolean movingDown;
    private int shipX;
    private int shipY;
    private int speed;
    public static int shipHeight = 123;
    public static int shipWidth = 114;
    private Picture spaceShip;
    private String picturePath;
    private Text scoreDisplay;
    private int scoreLocationX;
    private int scoreLocationY;
    private double textSize;
    private Picture explosion1;


    public SpaceShip(int shipX, int scoreLocationX, String picturePath) {
        score = 0;
        crashed = false;
        movingUp = false;
        movingDown = false;
        this.shipX = shipX;
        shipY = 777;
        this.picturePath = picturePath;
        speed = 25;
        textSize = 20;
        this.scoreLocationX = scoreLocationX;
        scoreLocationY = 820;
        gfxRepresentation();
    }

    public void gfxRepresentation() {
        spaceShip = new Picture(shipX, shipY, picturePath);
        spaceShip.draw();
        scoreDisplay = new Text(scoreLocationX, scoreLocationY, Integer.toString(score));
        scoreDisplay.setColor(Color.WHITE);
        scoreDisplay.grow(textSize, textSize);
        scoreDisplay.draw();
        explosion1 = new Picture(shipX, 0, "resources/explosionLarge.png");
    }

    public void move() {

        if (movingUp && !crashed) {
            spaceShip.translate(0, -speed);
            if (spaceShip.getY() <= Background.PADDING) {
                Sound scoreSound = new Sound("/resources/scored.wav");
                scoreSound.play(true);
                score++;
                scoreDisplay.setText(Integer.toString(score));
                if (score == 10) {
                    scoreDisplay.grow(textSize * 1.01, 0);
                }
                scoreDisplay.draw();
                spaceShip.translate(0, shipY - spaceShip.getY());
            }
        }
        if (movingDown && !crashed) {
            if (spaceShip.getY() <= shipY && spaceShip.getY() + shipHeight < Background.maxHeight) {
                spaceShip.translate(0, speed);
            }
        }
        if (crashed) {
            crashed = false;
            explosion1.translate(0, getY());
            try {
                explosion1.draw();
                Thread.sleep(50);
                explosion1.translate(0, - getY());
                explosion1.delete();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spaceShip.translate(0, shipY - spaceShip.getY());
        }
    }

    public void moveUp() {
        movingUp = true;
    }

    public void moveDown() {
        movingDown = true;
    }

    public void stopMoveUp() {
        movingUp = false;
    }

    public void stopMoveDown() {
        movingDown = false;
    }

    public void deleteShip() {
        spaceShip.delete();
        scoreDisplay.delete();
    }

    public int getScore() {
        return score;
    }

    public int getX() {
        return spaceShip.getX();
    }

    public int getY() {
        return spaceShip.getY();
    }

    public void setCrashed() {
        crashed = true;
    }
}


