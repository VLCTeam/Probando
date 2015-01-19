package es.binarycode.probando;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class CodificadorMD5 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codificador_md5);
        /**
         * Declaracion de Objetos
         */
        final TextView tv_resultadoMD5 = (TextView) findViewById(R.id.tv_resultadoMD5);
        final EditText et_pass = (EditText) findViewById(R.id.et_pass);
        final EditText et_salt = (EditText) findViewById(R.id.et_salt);
        Button btn_codificar = (Button) findViewById(R.id.btn_codificar);

        btn_codificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passUser;
                String saltUser;
                String password_final;
                passUser = String.valueOf(et_pass.getText());
                saltUser = String.valueOf(et_salt.getText());
                password_final = md5(passUser + saltUser);
                tv_resultadoMD5.setText(password_final);
            }
        });
    }


    // FUNCION DE CODIFICACION EN MD5
    public String md5(String clear) {
        String aux = "";
        try {
            // Create MD5 Hash
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] b = md.digest(clear.getBytes());
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
            aux = h.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        //clave encriptada
        return aux;
    }

}