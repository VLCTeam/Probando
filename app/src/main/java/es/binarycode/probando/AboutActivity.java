package es.binarycode.probando;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;


public class AboutActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        /**
         * Para versiones de Android superiores a la 2.3.7 necesitamos agregar estas lineas
         * asi funcionara cualquier conexion exterior
         */
        if (android.os.Build.VERSION.SDK_INT > 10) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        // FIN PROBANDO COSAS
        /**
         * Declaracion de Objetos
         */
        final TextView tv_iID = (TextView) findViewById(R.id.tv_iID);
        final TextView tv_iUGM = (TextView) findViewById(R.id.tv_iUGM);
        final TextView tv_iAV = (TextView) findViewById(R.id.tv_iAV);
        Button btn_MostrarDatos = (Button) findViewById(R.id.btn_MostrarDatos);
        /**
         * Variables
         */
        final String AndroidId = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);
        final String AndroidUGM = Settings.Secure.getString(getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS);
        final Integer AndroidAPI = Integer.valueOf(Build.VERSION.SDK);
        /**
         * Funciones de botones
         */
        btn_MostrarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_iID.setText(AndroidId);//Muestra el ID del dispositivo
                //tv_iUGM.setText(AndroidUGM);//Muestra ALGO...
                /**
                 * Muestra la Version de Android
                 */
                switch (AndroidAPI) {
                    case 10:
                        tv_iAV.setText("Gingerbread - Android 2.3.3/2.3.7");
                        break;
                    case 11:
                        tv_iAV.setText("Honeycomb - Android 3.0");
                        break;
                    case 12:
                        tv_iAV.setText("Honeycomb - Android 3.1");
                        break;
                    case 13:
                        tv_iAV.setText("Honeycomb - Android 3.2.x");
                        break;
                    case 14:
                        tv_iAV.setText("Ice Cream Sandwich - Android 4.0.1/4.0.2");
                        break;
                    case 15:
                        tv_iAV.setText("Ice Cream Sandwich - Android 4.0.3/4.0.4");
                        break;
                    case 16:
                        tv_iAV.setText("Jelly Bean - Android 4.1.x");
                        break;
                    case 17:
                        tv_iAV.setText("Jelly Bean - Android 4.2.x");
                        break;
                    case 18:
                        tv_iAV.setText("Jelly Bean - Android 4.3.x");
                        break;
                    case 19:
                        tv_iAV.setText("KitKat - Android 4.4/4.4.4");
                        break;
                    case 21:
                        tv_iAV.setText("Lollipop - Android 5.0");
                        break;
                    default:
                        tv_iAV.setText(R.string.error_Version_Android);
                        break;
                }
                /**
                 * Consulta PHP
                 * Necesita de dos parametros en String
                 * 1 Que funciona hacer (dependera de las funciones disponibles en PHP
                 * 2 Cadena SQL completa
                 */
                tv_iUGM.setText(consultaPHP("Login", "Paco Cubel"));
            }
        });

    }

    /**
     * Funcion de para conectar con el PHP de prueba
     * le ponemos el String delante del nombre de la funcion para decir que el "Return" devolvera
     * un String
     * Metemos "String Consulta" y "String SQL" entre el parentesis para decir que debe recibir
     * dos string dicha funcion
     */
    public String consultaPHP(String consulta, String SQL) {
        //Declaramos Variables
        String respuesta = null;
        ZBaseDatos conectBD = new ZBaseDatos(); //Creamos una variable conectBD con la clase "ZBaseDatos"
        JSONObject cadena = new JSONObject(); //Creamos un objeto de tipo JSON
        try {
            cadena.put("consulta", consulta);//Le asignamos los datos que necesitemos
            cadena.put("sql", SQL);//Le asignamos los datos que necesitemos

        } catch (JSONException e) {
            e.printStackTrace();
        }
        cadena.toString(); //Para obtener la cadena de texto de tipo JSON
        /**
         * ENVIAMOS CONSULTA
         */
        // Enviamos la consulta y metemos lo recibido dentro de la variable respuesta
        respuesta = conectBD.consultaSQL(cadena);
        return respuesta;
    }
}
