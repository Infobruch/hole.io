import GLOOP.*;
public class Floor {
    GLQuader floor;
    private double length, width;
    GLVektor vPos;
    public Floor(){

    }
    public void reset(){

    }
    public GLVektor getPos(){
        return vPos;
    }
    public double getFrontZBorder(){
        return this.getPos().z - length;
    }
    public double getBackZBorder(){
        return this.getPos().z + length;
    }
    public double getLeftXBorder(){
        return this.getPos().x - width;
    }
    public double getRightXBorder(){
        return this.getPos().x + width;
    }
}
