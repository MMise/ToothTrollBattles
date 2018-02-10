package hammas.peikko;

public class Hygienisti {

    private int baseHP = 100;
    private int hp = baseHP;
    private int xp = 0;
    private int lvl = 1;

    private int baseDMGToothpaste = 15;
    private int baseDMGFloss = 25;
    private int baseDMGXylitol = 40;
    private int baseDMGMouthwash = 32;

    private int baseXPToothpaste = 10;
    private int baseXPFloss = 15;
    private int baseXPMouthwash = 25;
    private int baseXPXylitol = 30;

    private Ability toothpaste;
    private Ability floss;
    private Ability xylitol;
    private Ability mouthwash;

    public Hygienisti(){
        toothpaste = new Ability(baseDMGToothpaste, baseXPToothpaste);
        floss = new Ability(baseDMGFloss, baseXPFloss);
        xylitol = new Ability(baseDMGXylitol, baseXPXylitol);
        mouthwash = new Ability(baseDMGMouthwash, baseXPMouthwash);
    }

    //Getters and setters for xp and hp

    public int getHp(){
        return hp;
    }

    public void setHp(int newHp){
        hp += newHp;
    }

    private void setXp(int newXp) {
        xp += newXp;
    }

    public int getLvl(){
        return lvl;
    }

    public boolean levelUp(){
        if(xp >= 100){
            setHp((int) (baseHP * (lvl * 0.2 + 1)));
            lvl++;
            xp = 0;
            return true;
        }
        else{
            return false;
        }

    }

    public int useAbility(String name){
        int dmg = 0;
        switch (name){
            case "toothpaste":
                dmg = (int) ((toothpaste.getDmg() * -1) * (lvl * 0.65));
                setXp(toothpaste.getXp());
                break;
            case "floss":
                dmg = (int) ((floss.getDmg() * -1) * (lvl * (Math.random() * 1)));
                setXp(floss.getXp());
                break;
            case "mouthwash":
                dmg = (int) ((mouthwash.getDmg() * -1) * (lvl * (Math.random() * 1)));
                setXp(mouthwash.getXp());
                break;
            case "xylitol":
                dmg = (int) ((xylitol.getDmg() * -1) * (lvl * (Math.random() * 1)));
                setXp(xylitol.getXp());
                break;
        }
        return dmg;
    }
}

class Ability {

    private int dmg, xp;

    public Ability(int dmg, int xp){
        this.dmg = dmg;
        this.xp = xp;
    }

    public int getDmg(){
        return dmg;
    }

    public int getXp(){
        return xp;
    }
}
