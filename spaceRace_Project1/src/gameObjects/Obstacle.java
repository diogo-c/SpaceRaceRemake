package gameObjects;

import gfx.Background;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Obstacle {

    private int initialX;
    private int initialY;
    private int maxX;
    private int minX;
    private int minY;
    public static int astroHeight = 31;
    public static int astroWidth = 30;
    private int direction;
    private Picture astro;
    private int speed;


    public Obstacle(int initialY) {
        minX = Background.minWidth;
        maxX = Background.maxWidth;
        minY = Background.minHeight;
        this.initialY = initialY;
        speed = 12;
        astroGenerator();
    }

    public void astroGenerator() {
        initialX = (int) (Math.random() * ((maxX - minX) + 1)) + minX;
        direction = (int) (Math.random() * 2);

        int astroGfx = (int) (Math.random() * 3);

        if (astroGfx == 1) {
            astro = new Picture(initialX, initialY, "resources/asteroid2.png");
        } else if (astroGfx == 2) {
            astro = new Picture(initialX, initialY, "resources/asteroid1.png");
        } else {
            astro = new Picture(initialX, initialY, "resources/star.png");
        }
        astro.draw();
    }

    public void move() {
        if (direction == 1) {
            astro.translate(-speed, 0);
            if (astro.getX() <= minX) {
                astro.delete();
                astro.translate(maxX - astro.getX(), 0);
                astro.draw();
            }
        } else {
            astro.translate(speed, 0);
            if (astro.getX() >= maxX) {
                astro.delete();
                astro.translate(-(maxX - minX), 0);
                astro.draw();
            }
        }
    }

    public void deleteAstro() {
        astro.delete();
    }

    public int getX() {
        return astro.getX();
    }

    public int getY() {
        return astro.getY();
    }
}


