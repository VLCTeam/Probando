package es.binarycode.probando;

import android.app.Activity;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.TextView;


public class AboutActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        /**
         * Declaracion de Objetos
         */
        final TextView tv_iID = (TextView)findViewById(R.id.tv_iID);
        final TextView tv_iUGM = (TextView)findViewById(R.id.tv_iUGM);
        /**
         * Variables
         */
        final String AndroidId = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);
        final String AndroidUGM = Settings.Secure.getString(getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS);

        tv_iID.setText(AndroidId);
        tv_iUGM.setText(AndroidUGM);
    }
}
