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
    public void render(){
        pa.text(name, pa.width/2, pa.height/2);
    }
    public String toString(){
        return "Nematode " + name + ", length: " + length + ", limbs: " + limbs + ", gender: " + evalGender() ;
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
