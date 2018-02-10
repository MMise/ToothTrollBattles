package hammas.peikko;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

/**
 * Created by Mikko on 10.2.2018.
 */

public class Menu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_menu);
    }

    public void buttonStart(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
