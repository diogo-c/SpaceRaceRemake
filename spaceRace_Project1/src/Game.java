import gameObjects.Obstacle;
import gameObjects.SpaceShip;
import gfx.Background;
import gfx.EndScreen;
import gfx.StartMenu;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import sound.Sound;

public class Game implements KeyboardHandler {

    private SpaceShip player1;
    private SpaceShip player2;
    private StartMenu startMenu;
    private EndScreen endScreen;
    private boolean started;
    private boolean gameOver;
    private boolean restarted;
    private boolean paused;
    private Background background;
    private Obstacle[] obstacles;
    private int maxScore;
    private Keyboard keyboard;


    public Game() {
        maxScore = 5;
        background = new Background();
        startMenu = new StartMenu();
        startMenu.drawStartMenuImage();
        started = false;
        gameOver = false;
        restarted = false;
        paused = false;
        initKeyboard();
    }

    public void initKeyboard() {
        keyboard = new Keyboard(this);

        KeyboardEvent pressW = new KeyboardEvent();
        KeyboardEvent pressS = new KeyboardEvent();
        KeyboardEvent pressO = new KeyboardEvent();
        KeyboardEvent pressL = new KeyboardEvent();

        pressW.setKey(KeyboardEvent.KEY_W);
        pressS.setKey(KeyboardEvent.KEY_S);
        pressO.setKey(KeyboardEvent.KEY_O);
        pressL.setKey(KeyboardEvent.KEY_L);

        KeyboardEvent pressW2 = new KeyboardEvent();
        KeyboardEvent pressS2 = new KeyboardEvent();
        KeyboardEvent pressO2 = new KeyboardEvent();
        KeyboardEvent pressL2 = new KeyboardEvent();
        KeyboardEvent pressSpace = new KeyboardEvent();
        KeyboardEvent pressR = new KeyboardEvent();

        pressW2.setKey(KeyboardEvent.KEY_W);
        pressS2.setKey(KeyboardEvent.KEY_S);
        pressO2.setKey(KeyboardEvent.KEY_O);
        pressL2.setKey(KeyboardEvent.KEY_L);
        pressSpace.setKey(KeyboardEvent.KEY_SPACE);
        pressR.setKey(KeyboardEvent.KEY_R);

        pressW.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        pressS.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        pressO.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        pressL.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        pressW2.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        pressS2.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        pressO2.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        pressL2.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        pressSpace.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        pressR.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        keyboard.addEventListener(pressW);
        keyboard.addEventListener(pressS);
        keyboard.addEventListener(pressO);
        keyboard.addEventListener(pressL);

        keyboard.addEventListener(pressW2);
        keyboard.addEventListener(pressS2);
        keyboard.addEventListener(pressO2);
        keyboard.addEventListener(pressL2);
        keyboard.addEventListener(pressSpace);
        keyboard.addEventListener(pressR);
    }

    public void pressStartBarMessage() {
        startMenu.drawSpaceBarMessage();
    }

    public void deleteStartBarMessage() {
        startMenu.deleteSpaceBarMessage();
    }

    public void showPlayersImage1() {
        startMenu.drawPlayer1Image1();
        startMenu.drawPlayer2Image1();
    }

    public void showPlayersImage2() {
        startMenu.drawPlayer1Image2();
        startMenu.drawPlayer2Image2();
    }

    public void showPlayersImage3() {
        startMenu.drawPlayer1Image3();
        startMenu.drawPlayer2Image3();
    }

    public void start() {
        startMenu.deleteStartMenuImage();
        player1 = new SpaceShip(344, 305, "resources/shipPlayer1.png");
        player2 = new SpaceShip(1151, 1305, "resources/shipPlayer2.png");

        obstacles = new Obstacle[23];
        int initialYPosition = -21 + Background.PADDING;
        for (int i = 0; i < obstacles.length; i++) {
            initialYPosition = initialYPosition + 32;               //each ship has 31px height. initialY will be incremented by 32.
            obstacles[i] = new Obstacle(initialYPosition);
        }
    }

    public void move() {
        player1.move();
        player2.move();

        for (Obstacle obs : obstacles) {
            obs.move();
            if ((player1.getX() < obs.getX() + Obstacle.astroWidth)
                    && (player1.getX() + SpaceShip.shipWidth > obs.getX())
                    && (player1.getY() < obs.getY() + Obstacle.astroHeight)
                    && (player1.getY() + SpaceShip.shipHeight > obs.getY())) {
                player1.setCrashed();
                Sound explosion = new Sound("/resources/explosion.wav");
                explosion.play(true);
            }
            if ((player2.getX() < obs.getX() + Obstacle.astroWidth)
                    && (player2.getX() + SpaceShip.shipWidth > obs.getX())
                    && (player2.getY() < obs.getY() + Obstacle.astroHeight)
                    && (player2.getY() + SpaceShip.shipHeight > obs.getY())) {
                player2.setCrashed();
                Sound explosion = new Sound("/resources/explosion.wav");
                explosion.play(true);
            }
        }
    }

    public void maxScoreCheck() {
        if (player1.getScore() == maxScore) {
            endScreen = new EndScreen(1);
            gameOver = true;
        } else if (player2.getScore() == maxScore) {
            endScreen = new EndScreen(2);
            gameOver = true;
        }
    }

    public void showEndScreen() {
        endScreen.drawEndScreen();
    }

    public void showVictoriousPlayerImage1() {
        endScreen.drawPlayerImage1();
    }

    public void showVictoriousPlayerImage2() {
        endScreen.drawPlayerImage2();
    }

    public void showVictoriousPlayerImage3() {
        endScreen.drawPlayerImage3();
    }

    public void showVictoriousMessage() {
        endScreen.drawPlayerMessage();
    }

    public void deleteVictoriousMessage() {
        endScreen.deletePlayerMessage();
    }

    public void deleteAllAstros() {
        for (Obstacle astro : obstacles) {
            astro.deleteAstro();
        }
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        if (started) {
            if (keyboardEvent.getKey() == KeyboardEvent.KEY_W) {
                player1.moveUp();
            }
            if (keyboardEvent.getKey() == KeyboardEvent.KEY_S) {
                player1.moveDown();
            }
            if (keyboardEvent.getKey() == KeyboardEvent.KEY_O) {
                player2.moveUp();
            }
            if (keyboardEvent.getKey() == KeyboardEvent.KEY_L) {
                player2.moveDown();
            }
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        if (!started) {
            if (keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE) {
                startMenu.deleteStartMenuPlayerImages();
                deleteStartBarMessage();
                started = true;
            }
        }
        if (started) {
            if (keyboardEvent.getKey() == KeyboardEvent.KEY_W) {
                player1.stopMoveUp();
            }
            if (keyboardEvent.getKey() == KeyboardEvent.KEY_S) {
                player1.stopMoveDown();
            }
            if (keyboardEvent.getKey() == KeyboardEvent.KEY_O) {
                player2.stopMoveUp();
            }
            if (keyboardEvent.getKey() == KeyboardEvent.KEY_L) {
                player2.stopMoveDown();
            }
            if (keyboardEvent.getKey() == KeyboardEvent.KEY_R) {
                player1.deleteShip();
                player2.deleteShip();
                deleteAllAstros();
                restarted = true;
                started = false;
            }
        }
        if (gameOver) {
            if (keyboardEvent.getKey() == KeyboardEvent.KEY_R) {
                player1.deleteShip();
                player2.deleteShip();
                endScreen.deleteAllEndScreenImages();
                restarted = true;
                started = false;
            }
        }
    }

    public boolean hasStarted() {
        return started;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean hasRestarted() {
        return restarted;
    }
}