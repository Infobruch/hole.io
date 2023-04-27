import GLOOP.*;
public class Floor {
    GLQuader floor;
    private final double length, width;
    GLVektor vPos;
    public Floor(double pX, double pZ, double pLength, double pWidth){
        length = pLength;
        width = pWidth;
        vPos = new GLVektor(pX, -0.5, pZ);
        floor = new GLQuader(vPos, width, 0.5, length);
        floor.setzeFarbe( 1, 1, 1);
    }
    public void reset(){

    }
    public GLVektor getPos(){
        return vPos;
    }
    public double getBackZBorder(){
        return this.getPos().z - length/2;
    }
    public double getFrontZBorder(){
        return this.getPos().z + length/2;
    }
    public double getLeftXBorder(){
        return this.getPos().x - width/2;
    }
    public double getRightXBorder(){
        return this.getPos().x + width/2;
    }
    public double getLength(){
        return length;
    }
    public double getWidth(){
        return width;
    }
}
