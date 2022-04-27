package ie.tudublin;

import java.util.Vector;

import processing.core.PApplet;
import processing.data.TableRow;

public class Nematode {
    public Nematode(String name, int length, Boolean limbs, Gender gender, boolean eyes,PApplet pa) {
        setName(name);
        setLength(length);
        setLimbs(limbs);
        setGender(gender);
        setEyes(eyes);
        this.pa = pa;
    }
    public Nematode(TableRow tr, PApplet pa){
        
        this(tr.getString("name"), tr.getInt("length"), tr.getInt("limbs") == 1, Gender.valueOf(tr.getString("gender")), tr.getInt("eyes") == 1, pa);
    }
    public enum Gender{
        m,
        f,
        h,
        n
    }
    private String name;
    private int length;
    private boolean limbs;
    private Gender gender;
    private boolean eyes;
    private PApplet pa;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }
    public boolean getLimbs() {
        return limbs;
    }
    public void setLimbs(boolean limbs) {
        this.limbs = limbs;
    }
    public boolean getEyes() {
        return eyes;
    }
    public void setEyes(boolean eyes) {
        this.eyes = eyes;
    }
    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    
    Vector center;
    float size = 50; 
    float extremityLength = 15;
    private float movementX = 0;
    public float getMovementX(){
        return movementX;
    }
    public void setMovementX(float movementX){
        this.movementX = movementX;
    }
    public void render(){
        float x = pa.width / 2f;
        float y = ((pa.height - (size * length))/2);
        pa.pushMatrix();
        
        pa.textSize(20);
        pa.textAlign(PApplet.CENTER);
        
        pa.strokeWeight(3);
        pa.translate(x, y + size/2);
        pa.translate(movementX, 0);
        pa.text(name, 0,  -size/2);
        pa.noFill();
        for(int i = 0; i < length; i++){
            pa.translate(0, size);
            pa.stroke(pa.frameCount % 360 + 5*i, 150, 100);

            if(eyes && i == 0){
                float height = PApplet.sin(45) * (size/2) - 5;
                float width = size/2 - 5;
                pa.line(-width, -height, (-width) - extremityLength, -height - extremityLength);
                pa.circle(-width - extremityLength- size/8, -height - extremityLength - size/8, size/4);
                pa.stroke(pa.frameCount % 360+(10*i), 150, 100);

                pa.line(width, -height, (width) + extremityLength, -height - extremityLength);
                pa.stroke(pa.frameCount % 360+(20*i), 150, 100);

                pa.circle(width + extremityLength + size/8, -height - extremityLength - size/8, size/4);
                
            }
            pa.circle(0, 0, size);
            if(limbs){
                pa.stroke(pa.frameCount % 360-(10*i), 150, 100);

                pa.line(-size/2, 0, (-size/2) -30, 0);
                pa.line(size/2, 0, (size/2) + 30, 0);

            }
            
        }
        
        switch (gender){
            case m:
            pa.line(0, (size/2), 0, (size/2) + extremityLength);
            pa.circle(0, (size/2) + extremityLength + size/8, size/4);
            break;
            case f:
            pa.circle(0, 0, (size*2)/4);
            break;
            case h:
            pa.circle(0, 0, (size*2)/4);
            break;
            case n:
            break;

        }
        pa.popMatrix();
    }
    public String toString(){
        return "Nematode " + name + ", length: " + length + ", limbs: " + limbs + ", gender: " + evalGender();
    }
    public String evalGender(){
        switch (gender){
            case m:
            return "male";
            case f:
            return "female";
            case h:
            return "hermaphrodite";
            case n:
            return "no-gender";
        }
        return gender.toString();
    }
}