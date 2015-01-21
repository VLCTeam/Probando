package es.binarycode.probando;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

/**
 * Created by cubel on 19/01/15.
 */
public class ZBaseDatos {
    public String consultaSQL(JSONObject JSON) {
        Log.e("INFO", "Entra en SQL " + JSON);
        String sb2 = null;
        try {
            /**
             * CODIGO QUE TENIA EN LA APP DE ENFILA Y TALLA (HABRA QUE MEJORARLO)
             */

            // Crear un cliente por defecto
            HttpClient mClient = new DefaultHttpClient();
            //Añadimos un parametro para que no tarde mas de los milisegundos indicados
            HttpConnectionParams.setConnectionTimeout(mClient.getParams(),
                    10000); // Timeout Limit
            // Creamos una variable de respuesta
            HttpResponse response;
            //Creamos un objeto JSON que vendra con el llamamiento de la funcion
            JSONObject paqueteJSON = JSON;
            // Indicar la URL de Conexion
            String URLConnect = new String("http://www.tracciona.es/nweb/appAndroid/app.php");
            //Hacemos varias comprobaciones
            Log.e("URL:", URLConnect);
            Log.e("JSON:", paqueteJSON.toString());
            // Establecer la conexion despues de indicar la url
            HttpPost mpost = new HttpPost(URLConnect);
            //Preparamos el Envio del paquete por post
            //StringEntity se = new StringEntity("JSON: "+paqueteJSON.toString());
            StringEntity se = new StringEntity(paqueteJSON.toString());
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json"));
            mpost.setEntity(se);
            Log.e("SE",se.toString());
            //Enviamos el paquete
            response = mClient.execute(mpost);
            //Log.e("Response", response.toString());
            // Creamos el Objeto JSON (Que ya viene desde la funcion que lo llama)
            // Post the data:
           //// mpost.setHeader("json",JSON.toString());
           //// mpost.getParams().setParameter("jsonpost",mpost);
            ////StringEntity se = new StringEntity("json="+JSON.toString());
           //// Log.e("INFO", "JSON" + JSON.toString());
           //// mpost.addHeader("content-type", "application/x-www-form-urlencoded");
            ////mpost.setEntity(se);
           //// Log.e("INFO", "SE Entero: " + se);


            // NameValuePair : Es una clase simple que encapsula el nombre del atributo y el valor que contiene.
            // Creamos una lista de 2 atributos (Clave - Valor) (Esto no esta claro pero lo dejaremos asi)
           /* List nameValuepairs = new ArrayList(2);
            // Agregamos los elementos a la lista (Pasamos Nobre Variable para PHP y COnsulta SQL)
            nameValuepairs.add(new BasicNameValuePair("SQL", SQL.toString()));
            nameValuepairs.add(new BasicNameValuePair("Que", "Nombre2"));
            nameValuepairs.add(new BasicNameValuePair("Nombre", "Pakito"));
            nameValuepairs.add(new BasicNameValuePair("Edad", "25"));*/
            /*
            // UrlEncodedFormEntity : Codificamos la lista para el envio por post
            mpost.setEntity(new UrlEncodedFormEntity(nameValuepairs));
            // Ejecutamos la solicitud y obtenemos una respuesta
            HttpResponse responce = mClient.execute(mpost);
            //  Obtenemos el contenido de la respuesta
            HttpEntity entity = responce.getEntity();
            // Convertimos el stream a un String
            BufferedReader buf = new BufferedReader(new InputStreamReader(entity.getContent()));
            StringBuilder sb1 = new StringBuilder();
            String line = null;
            while ((line = buf.readLine()) != null) {
                sb1.append(line + "\r\n");
            }
            ;
            sb2 = sb1.toString();
            */
            /////Log.e("INFO", "Vamos a por la Respuesta");
            //Respuesta
            /////HttpResponse response = mClient.execute(mpost);
            String resFromServer = org.apache.http.util.EntityUtils.toString(response.getEntity());
            JSONObject jsonResponse=new JSONObject(resFromServer);
            Log.i("Response from server", jsonResponse.getString("consulta"));
            // De prueba
            sb2 = jsonResponse.getString("consulta") + " " + jsonResponse.getString("sql");
            //Log.e("INFO", "RESPUESTA: " + sb2);
            //Este if es para mostrar "x" dato pedido, o la cadena JSON entera
            ////if (mostarJSON){ //Si no ponemos nada, como es una variable boolean se entiende que nos referimos a TRUE
            //Esto es para cargar en una variable "SB2" la cadena de JSON entera
            ////sb2 = sb1.toString();

            ////} else {
            //Modificaciones cubel
            ////JSONArray jsonArray = new JSONArray(sb1.toString());
            ////for (int i = 0; i < jsonArray.length(); i++) {
            ////JSONObject jsonObject = jsonArray.getJSONObject(i);
            //Aqui sacaremos los datos del Array en modo (Clave Valor) las Claves son los nombres pasados en la
            //Cadena JSON
            ////sb2 = "";
            ////sb2 += jsonObject.getString("5");
            //sb2 += "\nUser: ";
            //sb2 += jsonObject.getString("user");
            //sb2 += "\nMail: ";
            //sb2 += jsonObject.getString("mail");
            //sb2 += "\nPass (Cifrado): ";
            //sb2 += jsonObject.getString("pass");
            ////};
            ////};


        } catch (Exception e) {
            //Si se produce un error, lo mostramos
            Log.w(" error ", e.toString());
        }
        ;

        return sb2;
    }

    ;
}
