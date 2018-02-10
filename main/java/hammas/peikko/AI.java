package hammas.peikko;

/**
 * Created by Mikko on 31.1.2018.
 */

public class AI extends Peikko {
    public AI() {
        super();
    }

    public int arvoKyky(){
        int kykyNumero = (int) (Math.random()*2) + 1;
        return kykyNumero;
    }
}
