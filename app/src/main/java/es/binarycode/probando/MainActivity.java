package es.binarycode.probando;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * Declaracion de Objetos
         */
        final TextView tv_Saludo = (TextView) findViewById(R.id.tv_Saludo);
        final TextView tv_Version = (TextView) findViewById(R.id.tv_Version);

        /**
         * Variables
         */
        //int versionCode = BuildConfig.VERSION_CODE; //Codigo de Version de Android Studio
        String versionName = BuildConfig.VERSION_NAME; //Version de Play Store

        /**
         * Otras Funciones
         */
        tv_Version.setText(versionName);


    }


    /**
     * Inicialización del menú de acciones en la barra
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar, menu);

        //Configuración de la función de búsqueda
        /*SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));*/
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
            Intent i = new Intent(this, SettingsActivity.class);
            startActivity(i);
            // }
            return true;
        } else if (id == R.id.action_about) {
            /*Toast toast1 =Toast.makeText(getApplicationContext(),"Busqueda", Toast.LENGTH_SHORT);
            toast1.show();*/
            Intent i = new Intent(this, AboutActivity.class);
            startActivity(i);
            return true;
        } else if (id == R.id.action_codificador) {
            /*Toast toast1 =Toast.makeText(getApplicationContext(),"Codificador", Toast.LENGTH_SHORT);
            toast1.show();*/
            Intent i = new Intent(this, CodificadorMD5.class);
            startActivity(i);
            return true;
        } else if (id == R.id.action_login) {
            Intent i = new Intent(this, Login.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
