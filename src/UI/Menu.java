package UI;
import GLOOP.*;
public class Menu {
    GLTafel restart, quit, back;
    public Menu(){
        back = new GLTafel(0, 100, 0, 100, 75);
        back.setzeFarbe(0, 0, 0);
        back.setzeTextfarbe(1, 0, 0);
        back.setzeAutodrehung(true);
        back.setzeText("Press [ESC] to go BACK", 50);
        restart = new GLTafel(0, 100, -125, 100, 75);
        restart.setzeFarbe(0, 0, 0);
        restart.setzeTextfarbe(1, 0, 0);
        restart.setzeAutodrehung(true);
        restart.setzeText("Press [ENTER] to RESTART", 50);
        quit = new GLTafel(0, 100, 125, 100, 75);
        quit.setzeFarbe(0, 0, 0);
        quit.setzeTextfarbe(1, 0, 0);
        quit.setzeAutodrehung(true);
        quit.setzeText("Press [BACKSPACE] to QUIT", 50);
        this.hide();
    }
    public void show(){
        restart.setzeSichtbarkeit(true);
        quit.setzeSichtbarkeit(true);
        back.setzeSichtbarkeit(true);
    }
    public void hide(){
        restart.setzeSichtbarkeit(false);
        quit.setzeSichtbarkeit(false);
        back.setzeSichtbarkeit(false);
    }
}
