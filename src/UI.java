import GLOOP.*;
public class UI {
    GLTafel scoreBoard;
    private int fontSize;
    public void buildScoreBoard(double pX, double pY, double pZ, double pWidth, double pHeight, String pTexture, int pFontSize){
        scoreBoard = new GLTafel(pX, pY, pZ, pWidth, pHeight, pTexture);
        scoreBoard.setzeAutodrehung(true);
    }
    public void updateScoreBoard(int pScore){
        scoreBoard.setzeText("Score: " + pScore, fontSize);
        System.out.println("Score: " + pScore);
    }
}
