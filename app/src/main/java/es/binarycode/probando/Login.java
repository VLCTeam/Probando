package es.binarycode.probando;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;


public class Login extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        /**
         * Para versiones de Android superiores a la 2.3.7 necesitamos agregar estas lineas
         * asi funcionara cualquier conexion exterior
         */
        if (android.os.Build.VERSION.SDK_INT > 10) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        /**
         * Declaracion de Objetos
         */
        final TextView tv_respuesta = (TextView) findViewById(R.id.tv_respuesta);
        final EditText et_user = (EditText) findViewById(R.id.et_user);
        final EditText et_pass = (EditText) findViewById(R.id.et_pass);
        final Button btn_login = (Button) findViewById(R.id.btn_login);

        /**
         * Funciones de Botones
         */
        final Intent i = new Intent(this, PostLogin.class);//Esto lo ponemos aqui porque dentro del boton no funciona
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Nombre funcion recibira un True si es correcto o un false si no
                if (comprobarUsuario(String.valueOf(et_user.getText()), String.valueOf(et_pass.getText()))) {
                    //cargar siguiente layout

                    startActivity(i);
                } else {
                    tv_respuesta.setText(R.string.tv_User_Error);
                }
            }
        });
    }

    /**
     * Funcion para hacer la consulta de login
     */
    public Boolean comprobarUsuario(String user, String pass) {
        //Declaramos Variables
        JSONObject respuestaJSON = new JSONObject(); //Donde ira la respuesta
        ZBaseDatos conectBD = new ZBaseDatos(); //Creamos una variable conectBD con la clase "ZBaseDatos"
        JSONObject cadena = new JSONObject(); //Creamos un objeto de tipo JSON
        String respuesta = new String(); //Respuesta para saber si es OK o Error
        Boolean devovlerRespuesta = new Boolean(false); //Esto es lo que devolvera si es true o false
        try {
            cadena.put("tarea", "Login");//Le asignamos los datos que necesitemos
            cadena.put("datos", user);//Le asignamos los datos que necesitemos

        } catch (JSONException e) {
            e.printStackTrace();
        }
        cadena.toString(); //Para obtener la cadena de texto de tipo JSON
        /**
         * ENVIAMOS CONSULTA
         */
        // Enviamos la consulta y metemos lo recibido dentro de la variable respuesta
        respuestaJSON = conectBD.consultaSQL(cadena);
        Log.e("DATOS RECIBIDOS:", respuestaJSON.toString());
        try {
            //Ahora extraemos del JSON la parte "Respuesta" para saber si es un OK o un Error
            respuesta = respuestaJSON.getString("Respuesta");
            if (respuesta.equals("OK")) {
                //Si es un OK, llamaremos a las CLASES ZMD5 para hacer la comprobacion del Pass
                ZMD5 md5 = new ZMD5();
                //Tambien llamamos a la clase ZDatosTemporales para guardar los datos recibidos
                ZDatosTemporales datosUsuario = new ZDatosTemporales();
                if (md5.comprobarMD5(pass, respuestaJSON.getString("pass"))) {
                    Log.e("PASS", "Correcto");
                    //Al ser correcto el pass, metemos los datos en las variables de la clase ZDatosTemporales
                    datosUsuario.setIdUser(Integer.parseInt(respuestaJSON.getString("id")));
                    datosUsuario.setMailUser(respuestaJSON.getString("mail"));
                    datosUsuario.setNivelUser(respuestaJSON.getString("nivel"));
                    datosUsuario.setNombreUser(respuestaJSON.getString("nombre"));
                    datosUsuario.setPassUser(respuestaJSON.getString("pass"));
                    Log.e("NombreUSERCLASS", datosUsuario.getNombreUser());
                    devovlerRespuesta = true;
                } else {
                    Log.e("PASS", "Incorrecto");
                    devovlerRespuesta = false;
                }
            } else {
                devovlerRespuesta = false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return devovlerRespuesta;
    }

}
