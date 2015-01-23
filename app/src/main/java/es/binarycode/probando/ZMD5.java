package es.binarycode.probando;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by cubel on 22/01/15.
 * Esta clase tendra cuatro funciones en su interior
 *
 * comprobarMD5 recibira dos string, la contraseña insertada por el usuario, y la contraseña recibida
 * desde la Base de Datos que ira codificada en MD5.
 * Retornara un true si coinciden, o un false si no coinciden
 *
 * generarMD5 recibira un string que sera la contraseña insertada por el usuario.
 * Retornara otro string donde estara el MD5 completo listo para guardar en la Base de Datos
 *
 * codificadorMD5 recibira un string y se encarga de apicarle una encriptacion MD5
 * Retornara un string encriptado en MD5
 *
 * generarSALT recibira un String (creo que deberia recibir el pass del usuario)
 * Retornara un string en este caso un SALT
 * NOTA: Como segunda opcion, sera una funcion que no reciba nada pero si que devolvera el SALT
 */
public class ZMD5 {

    public Boolean comprobarMD5 (String pass, String MD5){
        //El MD5 tiene la siguiente estructura "caracteres:caracteres"
        //Por lo tanto deberemos indicar que solo queremos el segundo grupo de caracteres
        //para poder hacer la comprobacion.
        int inicioCadena = MD5.indexOf(":");// Indicamos que ":" sera donde empezar a leer
        //Ahora deberemos juntar, los segundos caracteres, con el "pass" y encriptarlo, asi sabremos
        //si la contraseña es la misma que MD5 completo
        //Indicamos que tiene que coger el siguiente caracter al ":"
        String codificador = MD5.substring(inicioCadena + 1); //De esta forma ya tenemos el SALT
        //Creamos una nueva variable para juntar el pass + el SALT
        String passYsalt = pass + codificador;
        //Una vez junto, lo encriptamos en MD5 (Crearemos y metermos el MD5 en passMD5)
        String passMD5 = codificadorMD5(passYsalt); // le pasamos los datos a la funcion codificadorMD5
        //Ahora comprobaremos si el pass del Usuario codificado es el mismo al MD5 traido de la DB
        if (MD5.equals(passMD5 + ":" + codificador)) {
            //El pass SI es correcto, devolvemos True
            return true;
        } else {
            //El pass NO es correcto, devolvemos False
            return false;
        }
    }

    public String generarMD5 (String pass){

        return "MD5";
    }

    public String codificadorMD5(String codificador) {
        //Creamos una variable donde ira a parar el resultado de la encriptacion en MD5
        String MD5Final = "";
        try {
            // Create MD5 Hash
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] b = md.digest(codificador.getBytes());
            int size = b.length;
            StringBuffer h = new StringBuffer(size);
            //algoritmo y arreglo md5
            for (int i = 0; i < size; i++) {
                int u = b[i] & 255;
                if (u < 16) {
                    h.append("0" + Integer.toHexString(u));
                } else {
                    h.append(Integer.toHexString(u));
                }
            }
            //Metemos el resultado de la enciptacion en la variable de respuesta
            MD5Final = h.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        //clave encriptada, la enviamos de vuelta al solicitante
        return MD5Final;
    }

    public String generarSALT (String pass){

        return "MD5";
    }
}
