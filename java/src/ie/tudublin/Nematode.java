package ie.tudublin;

import processing.data.TableRow;

public class Nematode {
    public Nematode(String name, int length, int limbs, Gender gender) {
        this.name = name;
        this.length = length;
        this.limbs = limbs;
        this.gender = gender;
    }
    public Nematode(TableRow tr){
        
        this(tr.getString("name"), tr.getInt("length"), tr.getInt("limbs"), Gender.valueOf(tr.getString("gender")));
    }
    public enum Gender{
        m,
        f,
        h,
        n
    }
    private String name;
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
    private int length;
    private int limbs;
    private Gender gender;

    public String toString(){
        return "Nematode " + name + ", length: " + length + ", limbs: " + limbs + ", gender: " + evalGender() ;
    }
    public String evalGender(){
        switch gender{}
    }

}
