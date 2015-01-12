package es.binarycode.probando;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * Declaracion de Objetos
         */
        final TextView tv_resultado = (TextView)findViewById(R.id.tv_resultado);
        Button btn_ID = (Button)findViewById(R.id.btn_ID);
        /**
         * Variables
         */
        final String AndroidId = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);
        /**
         * Funciones de botones
         */
        btn_ID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_resultado.setText("ID: "+AndroidId);
            }
        });
        /**
         * Otras Funciones
         */

    }


    /**
     * Inicialización del menú de acciones en la barra
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar, menu);

        //Configuración de la función de búsqueda
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Se llama a este método cuando el usuario hace clic en una acción de la barra
     */
         @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            /*Toast toast1 =Toast.makeText(getApplicationContext(),"Opciones", Toast.LENGTH_SHORT);
            toast1.show();*/
           // public void lanzar(View view) {
                Intent i = new Intent(this, SettingsActivity.class );
                startActivity(i);
           // }
            return true;
        } else if (id == R.id.action_about) {
            /*Toast toast1 =Toast.makeText(getApplicationContext(),"Busqueda", Toast.LENGTH_SHORT);
            toast1.show();*/
            Intent i = new Intent(this, AboutActivity.class );
            startActivity(i);
                return true;
            } else if (id == R.id.action_camera) {
            Toast toast1 =Toast.makeText(getApplicationContext(),"Camara", Toast.LENGTH_SHORT);
            toast1.show();
                return true;
            } else if (id == R.id.action_checkout) {
            Toast toast1 =Toast.makeText(getApplicationContext(),"Checkout", Toast.LENGTH_SHORT);
            toast1.show();
                return true;
            }

        return super.onOptionsItemSelected(item);
    }
}
