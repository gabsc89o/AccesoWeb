package com.example.usuari.accesoweb;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
    TextView pag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pag = (TextView) this.findViewById(R.id.tvPagina);
    }
    public void cargar(View v){
        ComunicacionTask ct = new ComunicacionTask();
        ct.execute("http://www.google.es");
    }
    private class ComunicacionTask extends AsyncTask<String,Void,String>{

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pag.setText(s);
        }

        @Override
        protected String doInBackground(String... strings) {
            String resultado="";
            try {
                URL url = new URL(strings[0]);
                URLConnection con = url.openConnection();
                String s;
                InputStream is = con.getInputStream();
                BufferedReader bf = new BufferedReader(new InputStreamReader(is));
                while((s=bf.readLine())!=null){
                    resultado +=s;
                }
            }catch (MalformedURLException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return resultado;
        }
    }
}
