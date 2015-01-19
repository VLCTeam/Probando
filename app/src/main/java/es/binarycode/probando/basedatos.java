package es.binarycode.probando;

import android.util.Log;

/**
 * Created by cubel on 19/01/15.
 */
public class basedatos {
    public String consultaWeb (String SQL){
        try {
            /**
             * CODIGO QUE TENIA EN LA APP DE ENFILA Y TALLA (HABRA QUE MEJORARLO)
             */
        /*
        // Crear un cliente por defecto
				HttpClient mClient = new DefaultHttpClient();
				// Indicar la url
				StringBuilder sb=new StringBuilder("http://www.enfilaitalla.es/androidApp/fun.php");
				// Establecer la conexion despues de indicar la url
				HttpPost mpost = new HttpPost(sb.toString());
				// NameValuePair : Es una clase simple que encapsula el nombre del atributo y el valor que contiene.
				// Creamos una lista de 2 atributos (Clave - Valor) (Esto no esta claro pero lo dejaremos asi)
				List nameValuepairs = new ArrayList(2);
				// Agregamos los elementos a la lista (Pasamos Nobre Variable para PHP y COnsulta SQL)
				nameValuepairs.add(new BasicNameValuePair("SQL",SQL));
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
					sb1.append(line+"\r\n");
				};
				sb2 = sb1.toString();
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
         */

        } catch (Exception e) {
            //Si se produce un error, lo mostramos
            Log.w(" error ", e.toString());
        };

        return "";
    };
}
