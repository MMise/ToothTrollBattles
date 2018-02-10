package hammas.peikko;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Mikko on 10.2.2018.
 */

public class EndScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_end);
    }

    public void quitClicked(View view){
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
    }
}
