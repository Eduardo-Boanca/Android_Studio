package com.example.primaprova;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class RisultatoActivity extends AppCompatActivity {
    // Dichiarazione delle variabili per i widget dell'interfaccia
    private TextView tassoDiCambioTextView, importoInseritoTextView, importoConvertitoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risultato);

        // Imposta la barra delle azioni con un pulsante "Indietro"
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Associazione delle variabili ai widget dell'interfaccia tramite i relativi ID
        tassoDiCambioTextView = findViewById(R.id.tasso_di_cambio_textview);
        importoInseritoTextView = findViewById(R.id.importo_textview);
        importoConvertitoTextView = findViewById(R.id.risultato_textview);

        // Recupero dei dati passati dalla prima activity
        Intent intent = getIntent();
        double importo = intent.getDoubleExtra("importo", 0);
        double tassoDiCambio = intent.getDoubleExtra("tasso_di_cambio", 0);

        // Controllo se i dati sono stati passati correttamente, e in caso contrario mostro un messaggio di errore
        if (importo == 0 || tassoDiCambio == 0) {
            tassoDiCambioTextView.setText("Errore: tasso di cambio non valido");
            importoInseritoTextView.setText("Errore: importo non valido");
            importoConvertitoTextView.setText("Errore: impossibile calcolare l'importo convertito");
        } else {
            // Calcolo dell'importo convertito
            double importoConvertito = importo * tassoDiCambio;

            // Impostazione dei valori dei widget dell'interfaccia
            tassoDiCambioTextView.setText("Tasso di cambio: 1 EUR = " + tassoDiCambio + " USD");
            importoInseritoTextView.setText("Importo inserito: " + importo + " EUR");
            importoConvertitoTextView.setText("Importo convertito: " + String.format("%.2f", importoConvertito) + " USD");
        }
    }

    // Gestisce il clic sul pulsante "Indietro" della barra delle azioni
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Torna alla MainActivity
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
