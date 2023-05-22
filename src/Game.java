import GLOOP.*;
import UI.Menu;
import UI.ScoreBoard;
import UI.Timer;

public class Game {
    private Catcher catcher;
    private Sphere[] spheres;
    private Floor floor;
    private GLKamera camera;
    private GLTastatur keyboard;
    private GLLicht light;
    private GLHimmel sky;
    private ScoreBoard scoreboard;
    private Menu menu;
    private Timer timer;
    private int score;
    private double menuStartingTimeMillis, runStartingTimeMillis;
    double FloorLength = 1000, FloorWidth = 1000;
    private final char moveLeftKey = 'a', moveRightKey = 'd', moveUpKey = 'w', moveDownKey = 's';
    private boolean menuLoop = false, runGameLoop = true;

    public Game(){

        menu = new Menu();
       floor = new Floor(0, 0, FloorLength, FloorWidth);
       catcher = new Catcher(0, 0, floor, 1, 50);
       camera = new GLKamera(1080, 1080);
       if(FloorWidth> FloorLength){
           camera.setzePosition(0, FloorWidth*1, 1000);
         }else{
           camera.setzePosition(0, FloorLength*1, 1000);
         }
       keyboard = new GLTastatur();
       light = new GLLicht();
       sky = new GLHimmel("src/img/sky.png");
       spheres = new Sphere[200];
         for(int i = 0; i < spheres.length; i++){
              spheres[i] = new Sphere(spheres , i , catcher, floor, 0.5, 5);
         }
        scoreboard = new ScoreBoard(floor.getPos().x, 20, floor.getBackZBorder() - 20, 100, 100, "src/img/invisible.png", 50);
        timer = new Timer(floor.getPos().x, 20, floor.getBackZBorder() - 100, 100, 100, "src/img/invisible.png", 50);
    }
    public void run(){
        timer.start();
        while(true){
            runGameLoop = true;
            runStartingTimeMillis = System.currentTimeMillis();
            while(runGameLoop){
                this.checkForMovementInput();
                for(int i = 0; i < spheres.length; i++){
                    spheres[i].move();
                    score += spheres[i].getScore();
                    spheres[i].resetScore();
                    scoreboard.updateScoreBoard(score);
                }
                if(maxScoreReached() || (keyboard.esc() && System.currentTimeMillis() - runStartingTimeMillis > 100)){
                    runGameLoop = false;
                    timer.pause();
                    System.out.println("Game Over");
                }
                timer.update();
                Sys.warte();
            }
            this.menuLoop();
        }
    }

    private void checkForMovementInput(){
        if(keyboard.istGedrueckt(moveLeftKey)){
            catcher.moveLeft();
        }
        if(keyboard.istGedrueckt(moveRightKey)){
            catcher.moveRight();
        }
        if(keyboard.istGedrueckt(moveUpKey)){
            catcher.moveUp();
        }
        if(keyboard.istGedrueckt(moveDownKey)){
            catcher.moveDown();
        }
        catcher.updateCatcherPos();
    }
    public boolean maxScoreReached(){
        return score == spheres.length;
    }
public void resetScore(){
        score = 0;
        scoreboard.updateScoreBoard(score);
    }
    public void resetGame(){
        this.resetScore();
        catcher.reset();
        for(int i = 0; i < spheres.length; i++){
            spheres[i].reset();
        }
    }
    public void menuLoop(){
        menuLoop = true;
        menuStartingTimeMillis = System.currentTimeMillis();
        menu.show();
        while(menuLoop){
            timer.update();
            if(keyboard.enter()){
                menuLoop = false;
                menu.hide();
                this.resetGame();
                timer.reset();
                timer.start();
            }
            if(keyboard.backspace()){
                menu.hide();
                timer.kill();
                Sys.beenden();
            }
            if(keyboard.esc() && System.currentTimeMillis() - menuStartingTimeMillis > 100){
                menu.hide();
                menuLoop = false;
                timer.resume();
            }
            Sys.warte();
        }
    }
}
