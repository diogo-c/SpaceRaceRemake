package gfx;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Background {

    public static int PADDING;
    private Picture background;
    public static int minHeight;
    public static int maxHeight;
    public static int minWidth;
    public static int maxWidth;

    public Background() {
        PADDING = 10;
        minHeight = PADDING + 15;
        maxHeight = 900;
        minWidth = PADDING + 20;
        maxWidth = 1555;
        background = new Picture(PADDING, PADDING, "resources/background.jpg");
        background.draw();
    }
}


