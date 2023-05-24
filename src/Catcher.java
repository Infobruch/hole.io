import GLOOP.*;
public class Catcher {
    GLZylinder catcher;
    GLVektor vPos, vResetPos;
    Floor floor;
    private double radius;
    private double speed;
    public Catcher(double pX, double pZ, Floor pFloor, double pSpeed, double pRadius){
        floor = pFloor;
        speed = pSpeed;
        radius = pRadius;
        vPos = new GLVektor(pX, 0, pZ);
        vResetPos = new GLVektor(pX, 0, pZ);
        catcher = new GLZylinder(vPos, radius, 20);
        catcher.drehe(90, 0, 0);
        catcher.setzeMaterial(GLMaterial.GRÜNGLAS);
        catcher.setzeGlanz(0, 1, 0, 10);
        //catcher.setzeFarbe(0, 1, 0);
    }

    public void moveRight(){
        if(this.getPos().x + radius < floor.getRightXBorder()) {
            vPos.x += speed;
        }
    }
    public void moveLeft(){
        if(this.getPos().x - radius > floor.getLeftXBorder()) {
            vPos.x -= speed;
        }
    }
    public void moveUp(){
        if(this.getPos().z - radius > floor.getBackZBorder()) {
            vPos.z -= speed;
        }
    }
    public void moveDown(){
        if(this.getPos().z + radius < floor.getFrontZBorder()) {
            vPos.z += speed;
        }
    }
    public void updateCatcherPos(){
        catcher.setzePosition(vPos);
    }
    public void reset(){
        vPos = vResetPos;
        catcher.setzePosition(vPos);
    }

    public GLVektor getPos(){
        return vPos;
    }
    public double getRadius(){
        return radius;
    }
}
