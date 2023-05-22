package UI;

import GLOOP.*;
public class ScoreBoard{
    GLTafel scoreBoard;
    private int fontSize;
    public ScoreBoard(double pX, double pY, double pZ, double pWidth, double pHeight, String pTexture, int pFontSize){
        fontSize = pFontSize;
        scoreBoard = new GLTafel(pX, pY, pZ, pWidth, pHeight, pTexture);
        scoreBoard.setzeAutodrehung(true);
        scoreBoard.setzeTextfarbe(0,0, 0.5);
    }
    public void updateScoreBoard(int pScore){
        scoreBoard.setzeText("Score: " + pScore, fontSize);
        System.out.println("Score: " + pScore);
    }
}
