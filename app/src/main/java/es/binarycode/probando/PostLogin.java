package es.binarycode.probando;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class PostLogin extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_login);
        /**
         * Declaracion de Objetos
         */
        final TextView tv_pID = (TextView) findViewById(R.id.tv_pID);
        final TextView tv_pnombre = (TextView) findViewById(R.id.tv_pnombre);
        final TextView tv_pmail = (TextView) findViewById(R.id.tv_pmail);
        final TextView tv_pnivel = (TextView) findViewById(R.id.tv_pnivel);
        Button btn_MostrarDatos = (Button) findViewById(R.id.btn_MostrarDatos);

        btn_MostrarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZDatosTemporales DU = (ZDatosTemporales) getApplicationContext();
                tv_pID.setText(DU.getIdUser());
                tv_pnombre.setText(DU.getNombreUser());
                tv_pmail.setText(DU.getMailUser());
                tv_pnivel.setText(DU.getNivelUser());
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.action_bar_private, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
