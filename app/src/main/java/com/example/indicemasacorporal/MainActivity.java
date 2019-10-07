package com.example.indicemasacorporal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.indicemasacorporal.R.id.desIMC;
import static com.example.indicemasacorporal.R.id.start;

public class MainActivity extends AppCompatActivity {

    private static final String URL_IMC = "https://es.wikipedia.org/wiki/%C3%8Dndice_de_masa_corporal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //debo sobrrescribir este método para cargar mi menú
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();//este objeto será el encargado de cargar/inlfar mi menú
        mi.inflate(R.menu.menu_ayuda, menu);
        return super.onCreateOptionsMenu(menu);
    }


    //ESTE MÉTODO SE INVOCA AL TOCAR UNA OPCIÓN DEL MENÚ
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.d("MIAPP", "Se ha tocado un elemento de la barra/menú");

        switch (item.getItemId())
        {
            case android.R.id.home:
                Log.d("MIAPP", "Ha tocado la flecha de para atrás");
                finish();
                break;
            case R.id.ayuda:
                Log.d("MIAPP", "Ha tocado la opción de ayuda");
                mostrarWikiIMC();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    private void mostrarWikiIMC() {
        Intent intent = new Intent();
        intent.setData(Uri.parse(URL_IMC));
        intent.setAction(Intent.ACTION_VIEW);
        if (intent.resolveActivity(getPackageManager())!= null)
        {
            startActivity(intent);
        }
        return;

    }

    public void calcularIMC(View view) {
        EditText pesoTecleado = findViewById(R.id.peso);
        EditText estaturaTecleada = findViewById(R.id.estatura);

        String pesoS = pesoTecleado.getText().toString();
        Log.d(  "APP IMC","PESO = " + pesoS);

        String estaturaS = estaturaTecleada.getText().toString();
        Log.d(  "APP IMC","ESTATURA = " + estaturaS);

        Double pesoDouble = Double.parseDouble(pesoS);
        Double estaturaDouble = Double.parseDouble(estaturaS);

        Double imc = (pesoDouble) / ((estaturaDouble) * (estaturaDouble));

        TextView numIMC = findViewById(R.id.numIMC);
        numIMC.setText(String.valueOf(imc));


        TextView desIMC = findViewById(R.id.desIMC);
        if (imc < 16) {
            desIMC.setText("DESNUTRIDO");
        }
        else {
            if ((16 <= imc) && (imc < 18)) {
                desIMC.setText("DELGADO");
            }
            else {
                if ((18 <= imc) && (imc < 25)) {
                    desIMC.setText("IDEAL/NORMAL");
                }
                else {
                    if ((25 <= imc) && (imc < 31)) {
                        desIMC.setText("SOBREPESO");
                    } else {
                        if (31 <= imc) {
                            desIMC.setText("OBESIDAD");
                        }
                    }
                }
            }
        }
    }
}
