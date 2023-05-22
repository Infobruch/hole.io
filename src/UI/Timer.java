package UI;
import GLOOP.*;
public class Timer{
    GLTafel timeBoard;
    private int fontSize;
    private double refferenceTime, timePassed;
    boolean runTimer = false;
    public Timer(double pX, double pY, double pZ, double pWidth, double pHeight, String pTexture, int pFontSize){
        fontSize = pFontSize;
        timeBoard = new GLTafel(pX, pY, pZ, pWidth, pHeight, pTexture);
        timeBoard.setzeAutodrehung(true);
        timeBoard.setzeTextfarbe(0,0.5, 0);
    }
    public void start(){
        runTimer = true;
        refferenceTime = System.currentTimeMillis();
    }
    public void pause(){
        runTimer = false;
    }
    public void resume(){
        runTimer = true;
        refferenceTime = System.currentTimeMillis();
    }
    public void reset(){
        runTimer = false;
        timePassed = 0;
        timeBoard.setzeText("Time passed in [ms]: " + timePassed, fontSize);
    }
    public void kill(){
        timeBoard.setzeSichtbarkeit(false);
    }
    public void update(){
        if(runTimer){
            timePassed = timePassed + System.currentTimeMillis() - refferenceTime;
            timeBoard.setzeText("Time passed: " + timePassed/1000 + "s", fontSize);
            refferenceTime = System.currentTimeMillis();
        }
    }
}

