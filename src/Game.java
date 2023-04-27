import GLOOP.*;
public class Game {
    private Catcher catcher;
    private Sphere[] spheres;
    private Floor floor;
    private GLKamera camera;
    private GLTastatur keyboard;
    private GLLicht light;
    private GLHimmel sky;
    private UI scoreboard;
    private int score;
    double FloorLength = 1000, FloorWidth = 1000;
    private final char moveLeftKey = 'a', moveRightKey = 'd', moveUpKey = 'w', moveDownKey = 's';

    public Game(){
       floor = new Floor(0, 0, FloorLength, FloorWidth);
       catcher = new Catcher(0, 0, floor, 1, 20);
       camera = new GLKamera(1440, 1440);
       if(FloorWidth> FloorLength){
           camera.setzePosition(0, FloorWidth*1, 1);
         }else{
           camera.setzePosition(0, FloorLength*1, 1);
         }
       keyboard = new GLTastatur();
       light = new GLLicht();
       sky = new GLHimmel("src/img/sky.png");
       spheres = new Sphere[500];
         for(int i = 0; i < spheres.length; i++){
              spheres[i] = new Sphere(spheres , i , catcher, floor, 0.5, 5);
         }
        scoreboard = new UI();
        scoreboard.buildScoreBoard(floor.getPos().x, 20, floor.getPos().z - 30, 100, 100, "src/img/invisible.png", 50);
    }
    public void run(){
        while(!keyboard.esc()){
            this.checkForMovementInput();
            for(int i = 0; i < spheres.length; i++){
                spheres[i].move();
                score += spheres[i].getScore();
                spheres[i].resetScore();
                scoreboard.updateScoreBoard(score);
            }
            Sys.warte();
        }
        Sys.beenden();
    }
    public void reset(){

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
}
