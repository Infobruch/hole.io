import GLOOP.*;
public class Sphere {
    GLKugel sphere;
    GLVektor vPos, vOffPos, vColor;
    Floor floor;
    Catcher catcher;
    Sphere[] spheres;
    private int index;
    private double radius;
    private int sphereCollisionCounter;
    private int score;
    private double tempSpeedX, tempSpeedZ;

    private double speedX, speedZ;
    public Sphere(Sphere[] pSpheres, int pIndex, Catcher pCatcher, Floor pFloor, double pMaxSpeed, double pRadius){
        index = pIndex;
        spheres = pSpheres;
        catcher = pCatcher;
        floor = pFloor;
        speedX = Math.random()*pMaxSpeed*2 - pMaxSpeed;
        speedZ = speedX;
        radius = pRadius;
        vPos = new GLVektor(Math.random()*(floor.getWidth() - 2*pRadius) - (floor.getWidth() - 2*pRadius)/2, 0 + pRadius, Math.random()*(floor.getLength() - 2*pRadius) - (floor.getLength()- 2*pRadius)/2);
        vOffPos = new GLVektor(100000, 100000, 100000);
        vColor = new GLVektor(Math.random(), Math.random(), Math.random());
        sphere = new GLKugel(vPos, pRadius);
        sphere.setzeFarbe(vColor.x, vColor.y, vColor.z);
       do{
           this.checkForCollisionWithSpheresAtSpawn();
           if(this.checkForCollisionWithHole()){
               sphereCollisionCounter++;
               this.resetRandomPos();
           }
           System.out.println(sphereCollisionCounter);
       }while(sphereCollisionCounter > 0);
    }
    public void move(){
        if(this.getPos() != vOffPos){
            sphere.setzeSelbstleuchten(0, 0, 0);
            this.checkForCollisionWithSpheresDuringGame();
            if(this.getPos().x + radius >= floor.getRightXBorder() || this.getPos().x - radius <= floor.getLeftXBorder()){
                speedX = -speedX;
                sphere.setzeSelbstleuchten(vColor.x, vColor.y, vColor.z);
            }
            if(this.getPos().z + radius >= floor.getFrontZBorder() || this.getPos().z - radius <= floor.getBackZBorder()){
                speedZ = -speedZ;
                sphere.setzeSelbstleuchten(vColor.x, vColor.y, vColor.z);
            }
            vPos.x += speedX;
            vPos.z += speedZ;

            sphere.drehe(speedX, 0, speedZ);
            this.updateSpherePos();
        }
        this.gotCaught();

    }
    public void putAway(){
        vPos = vOffPos;
        this.updateSpherePos();
    }
    public void resetRandomPos(){
        vPos = new GLVektor(Math.random()*(floor.getWidth() - 2*radius) - (floor.getWidth() - 2*radius)/2, 0 + radius, Math.random()*(floor.getLength() - 2*radius) - (floor.getLength()- 2*radius)/2);
        sphere.setzePosition(vPos);
    }
    public void checkForCollisionWithSpheresAtSpawn() throws NullPointerException {
        try {
            for (int i = 0; i < spheres.length; i++) {
                if(i == 0){
                    sphereCollisionCounter = 0;
                    System.out.println("SphereCollisionCounter: " + sphereCollisionCounter + " | index: " + index);
                }
                if (i != index) {
                    double d = Math.sqrt(Math.pow( this.getPos().x- spheres[i].getPos().x, 2 ) + Math.pow( this.getPos().y- spheres[i].getPos().y, 2) + Math.pow( this.getPos().z- spheres[i].getPos().z, 2));
                    if (d < (spheres[i].getRadius() + 2* this.getRadius())) {
                        System.out.println(d + " < " + (spheres[i].getRadius() + 2 * this.getRadius()));
                        this.resetRandomPos();
                        sphereCollisionCounter++;
                    }
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Error: " + e);
        }
    }
    public void checkForCollisionWithSpheresDuringGame() throws NullPointerException {
        try {
            for (int i = 0; i < spheres.length; i++) {
                if (i != index) {
                    double d = Math.sqrt(Math.pow( this.getPos().x- spheres[i].getPos().x, 2 ) + Math.pow( this.getPos().y- spheres[i].getPos().y, 2) + Math.pow( this.getPos().z- spheres[i].getPos().z, 2));
                    if (d < (spheres[i].getRadius() + 2* this.getRadius())) {
                        System.out.println(d + " < " + (spheres[i].getRadius() + 2 * this.getRadius()));
                        tempSpeedX = speedX;
                        tempSpeedZ = speedZ;
                        this.speedX = spheres[i].getSpeedX();
                        this.speedZ = spheres[i].getSpeedZ();
                        spheres[i].setSpeedX(tempSpeedX);
                        spheres[i].setSpeedZ(tempSpeedZ);
                        sphere.setzeSelbstleuchten(vColor.x, vColor.y, vColor.z);

                    }
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Error: " + e);
        }
    }

    public boolean checkForCollisionWithHole(){
        double d = Math.sqrt(Math.pow( this.getPos().x- catcher.getPos().x, 2 ) + Math.pow( this.getPos().y- catcher.getPos().y, 2) + Math.pow( this.getPos().z- catcher.getPos().z, 2));
        if(d < (catcher.getRadius() + this.getRadius())){
            return true;
        }else {
            return false;
        }

    }
    public void gotCaught(){
        if(checkForCollisionWithHole()) {
            //this.resetRandomPos();
            this.putAway();
            score++;
        }
    }
    public void reset(){
        this.resetRandomPos();
        this.resetScore();
        do{
            this.checkForCollisionWithSpheresAtSpawn();
            if(this.checkForCollisionWithHole()){
                sphereCollisionCounter++;
                this.resetRandomPos();
            }
            System.out.println(sphereCollisionCounter);
        }while(sphereCollisionCounter > 0);
    }
    public void updateSpherePos(){
        sphere.setzePosition(vPos);
    }
    public int getScore(){
        return score;
    }
    public void resetScore(){
        score = 0;
    }
    public double getRadius(){
        return radius;
    }
    public GLVektor getPos(){
        return vPos;
    }
    public double getSpeedX(){
        return speedX;
    }
    public double getSpeedZ(){
        return speedZ;
    }
    public void setSpeedX(double pSpeedX){
        this.speedX = pSpeedX;
    }
    public void setSpeedZ(double pSpeedZ){
        this.speedZ = pSpeedZ;
    }
}
