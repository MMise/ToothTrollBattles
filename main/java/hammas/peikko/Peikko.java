package hammas.peikko;

public class Peikko {

    private int hp;
    private int lvl = 0;
    private int baseDMGability1 = 18;
    private int baseDMGability2 = 26;
    private int baseDMGability3 = 31;
    private double multiplier = 0.7;

    public Peikko(){
        lvl++;
        hp = lvl * 40;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp += hp;
    }

    public int getLvl() {
        return lvl;
    }

    public void nextLvl() {
        lvl++;
        int newHP = lvl * 50;
        setHp(newHP);
    }

    public int kyky1(){
        int dmg = (int) ((baseDMGability1 * -1) * (lvl * multiplier));
        return dmg;
    }

    public int kyky2(){
        int dmg = (int) ((baseDMGability2 * -1) * (lvl * multiplier));
        return dmg;
    }

    public int kyky3(){
        int dmg = (int) ((baseDMGability3 * -1) * (lvl * multiplier));
        return dmg;
    }


}
