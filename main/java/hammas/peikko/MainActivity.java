package hammas.peikko;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

    Button buttonFloss;
    Button buttonToothpaste;
    Button buttonXylitol;
    Button buttonMouthwash;

    private Hygienisti hygienisti;
    private AI peikko;
    boolean flag = false;

    ProgressBar progressBarHygienisti;
    ProgressBar progressBarPeikko;

    TextView textViewGameInfo;
    TextView textViewGameInfo2;
    TextView textViewLvl;
    TextView textViewLvlT;
    TextView textViewDmgToTroll;
    TextView textViewDmgToPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hygienisti = new Hygienisti();
        peikko = new AI();

        buttonToothpaste = findViewById(R.id.buttonFluori);
        buttonFloss = findViewById(R.id.buttonHammaslanka);
        buttonMouthwash = findViewById(R.id.buttonMouthwash);
        buttonXylitol = findViewById(R.id.buttonXylitol);

        textViewGameInfo = findViewById(R.id.textViewGameInfo);
        textViewGameInfo2 = findViewById(R.id.textViewGameInfo2);
        textViewLvl = findViewById(R.id.textViewLvl);
        textViewLvlT = findViewById(R.id.textViewLvlT);
        textViewDmgToPlayer = findViewById(R.id.textViewDmgToPlayer);
        textViewDmgToTroll = findViewById(R.id.textViewDmgToTroll);

        progressBarHygienisti = findViewById(R.id.progressBarHygienisti);
        progressBarHygienisti.setProgress(hygienisti.getHp());

        progressBarPeikko = findViewById(R.id.progressBarPeikko);
        progressBarPeikko.setMax(peikko.getHp()); //Asetetaan peikon elämäpistepalkin maksimiksi peikon aloituspisteet
        progressBarPeikko.setProgress(peikko.getHp());

        textViewLvl.setText(String.valueOf(hygienisti.getLvl()));
        textViewLvlT.setText(String.valueOf(peikko.getLvl()));
    }

    private void setDmgTextToTroll(int value){
        textViewDmgToTroll.setText(String.valueOf(value));
    }

    private void setDmgTextToPlayer(int value){
        textViewDmgToPlayer.setText(String.valueOf(value));
    }

    public void onClickToothpaste(View view){
        int dmg = hygienisti.useAbility("toothpaste");
        peikko.setHp(dmg);
        progressBarPeikko.setProgress(peikko.getHp());
        textViewGameInfo.setText(R.string.hygienist_ability_text_1);
        setDmgTextToTroll(dmg);
        trollIsDead();
    }

    public void onClickFloss(View view){
        int dmg = hygienisti.useAbility("floss");
        peikko.setHp(dmg);
        progressBarPeikko.setProgress(peikko.getHp());
        textViewGameInfo.setText(R.string.hygienist_ability_text_2);
        setDmgTextToTroll(dmg);
        trollIsDead();
    }

    public void onClickMouthwash(View view){
        int dmg = hygienisti.useAbility("mouthwash");
        peikko.setHp(dmg);
        progressBarPeikko.setProgress(peikko.getHp());
        textViewGameInfo.setText(R.string.hygienist_ability_text_3);
        setDmgTextToTroll(dmg);
        trollIsDead();
    }

    public void onClickXylitol(View view){
        int dmg = hygienisti.useAbility("xylitol");
        peikko.setHp(dmg);
        progressBarPeikko.setProgress(peikko.getHp());
        textViewGameInfo.setText(R.string.hygienist_ability_text_4);
        setDmgTextToTroll(dmg);
        trollIsDead();
    }

    private void hygienistLvlUp(){
        if(hygienisti.levelUp()){
            textViewGameInfo2.setText(R.string.hygienist_gains_lvl);
            progressBarHygienisti.setMax(hygienisti.getHp());
            progressBarHygienisti.setProgress(hygienisti.getHp());
            textViewLvl.setText(String.valueOf(hygienisti.getLvl()));
        }
    }

    private void toggleButtons(){
        //Metodi asettaa painikkeen päälle ja pois lipun tilan mukaan
        if(!flag) {
            buttonFloss.setEnabled(false);
            buttonToothpaste.setEnabled(false);
            buttonMouthwash.setEnabled(false);
            buttonXylitol.setEnabled(false);
            textViewDmgToPlayer.setText("");
        }
        else {
            buttonFloss.setEnabled(true);
            buttonToothpaste.setEnabled(true);
            buttonMouthwash.setEnabled(true);
            buttonXylitol.setEnabled(true);
            textViewDmgToTroll.setText("");
        }
        flag = !flag;
        hygienistLvlUp();
    }

    public void trollIsDead(){
        if(peikko.getHp() <= 0){
            CountDownTimer timer = new CountDownTimer(2000,100) {
                @Override
                public void onTick(long l) {

                }

                @Override
                public void onFinish() {
                    peikko.nextLvl();
                    progressBarPeikko.setMax(peikko.getHp()); //Asetetaan peikon elämäpistepalkin maksimiksi peikon aloituspisteet
                    progressBarPeikko.setProgress(peikko.getHp());
                    textViewGameInfo.setText(R.string.troll_gains_lvl);
                    progressBarHygienisti.setProgress(hygienisti.getHp());
                    textViewLvlT.setText(String.valueOf(peikko.getLvl()));
                }
            }.start();
        }
        else{
            trollTurn();
        }
    }

    public boolean playerIsDead(){
        //Tarkastetaan pelaajan elämäpisteet. Jos ne ovat nollassa, siirrytään lopetusruutuun
        if(hygienisti.getHp() <= 0){
            Intent intent = new Intent(this, EndScreen.class);
            startActivity(intent);
            this.finish();
            return true;
        }
        return false;
    }

    public void trollTurn(){

        /*
            Tämä funktio sisältää logiikan, jolla arvotaan peikon käyttämä kyky.
            Kyvyn käytön jälkeen päivitetään pelaajan elämäpisteet palkkiin.
            Jos pelaaja kuolee, siirrytään uuteen näkymään
        */
        toggleButtons();
        CountDownTimer timer = new CountDownTimer(2000, 100) {
            @Override
            public void onTick(long l) {
                //Odotetaan timeria
            }

            @Override
            public void onFinish() {
                //Timer laski loppuun
                int kykyNro = peikko.arvoKyky();
                int dmg;
                //Arvotaan käytettävä kyky

                switch (kykyNro){
                    case 1:
                        dmg = peikko.kyky1();
                        hygienisti.setHp(dmg);
                        progressBarHygienisti.setProgress(hygienisti.getHp());
                        textViewGameInfo.setText(R.string.troll_ability_text_1);
                        setDmgTextToPlayer(dmg);
                        //Peikko käytti kykyä 1
                        break;
                    case 2:
                        dmg = peikko.kyky2();
                        hygienisti.setHp(dmg);
                        progressBarHygienisti.setProgress(hygienisti.getHp());
                        textViewGameInfo.setText(R.string.troll_ability_text_2);
                        setDmgTextToPlayer(dmg);
                        //Peikko käytti kykyä 2
                        break;
                    case 3:
                        dmg = peikko.kyky3();
                        hygienisti.setHp(dmg);
                        progressBarHygienisti.setProgress(hygienisti.getHp());
                        textViewGameInfo.setText(R.string.troll_ability_text_3);
                        setDmgTextToPlayer(dmg);
                        //Peikko käytti kykyä 3
                        break;
                }
                playerIsDead();
                toggleButtons();
                textViewGameInfo2.setText("");
            }
        }.start();
    }
}
