package UI;
import GLOOP.*;
public class Menu {
    GLTafel restart, quit;
    public Menu(){
        restart = new GLTafel(0, 100, 10, 100, 75);
        restart.setzeFarbe(0, 0, 0);
        restart.setzeTextfarbe(1, 0, 0);
        restart.setzeAutodrehung(true);
        restart.setzeText("Press [ENTER] to Restart", 50);
        quit = new GLTafel(0, -100, 10, 100, 75);
        quit.setzeFarbe(0, 0, 0);
        quit.setzeTextfarbe(1, 0, 0);
        quit.setzeAutodrehung(true);
        quit.setzeText("Press [esc] to Quit", 50);
        this.hide();
    }
    public void show(){
        restart.setzeSichtbarkeit(true);
        quit.setzeSichtbarkeit(true);
    }
    public void hide(){
        restart.setzeSichtbarkeit(false);
        quit.setzeSichtbarkeit(false);
    }
}
