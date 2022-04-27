package ie.tudublin;

import java.util.Vector;

import processing.core.PApplet;
import processing.data.TableRow;

public class Nematode {
    public Nematode(String name, int length, int limbs, Gender gender, PApplet pa) {
        this.name = name;
        this.length = length;
        this.limbs = limbs;
        this.gender = gender;
        this.pa = pa;
    }
    public Nematode(TableRow tr, PApplet pa){
        
        this(tr.getString("name"), tr.getInt("length"), tr.getInt("limbs"), Gender.valueOf(tr.getString("gender")), pa);
    }
    public enum Gender{
        m,
        f,
        h,
        n
    }
    private String name;
    private int length;
    private int limbs;
    private Gender gender;
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
    public int getLimbs() {
        return limbs;
    }
    public void setLimbs(int limbs) {
        this.limbs = limbs;
    }
    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    
    Vector center;
    float size = 50; 
    public void render(){
        float x = pa.width / 2f;
        float y = ((pa.height - (size * length))/2);
        pa.pushMatrix();
        
        pa.textSize(20);
        pa.textAlign(PApplet.CENTER);
        pa.text(name, x, y + size/2);
        
        pa.strokeWeight(5);
        pa.translate(x, y + size/2);
        
        pa.noFill();
        for(int i = 0; i < length; i++){
            pa.stroke(39, 100, 100);
            pa.translate(0, size);
            pa.circle(0, 0, size);
        }

        for(int i = 0; i < limbs; i++){

        }
        switch (gender){

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