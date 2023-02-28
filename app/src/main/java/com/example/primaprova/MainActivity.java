package com.example.primaprova;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // Dichiarazione delle variabili per i widget dell'interfaccia
    private EditText importoEditText, tassoDiCambioEditText;
    private RadioGroup radioGroup;
    private RadioButton euroToDollarRadioButton, dollarToEuroRadioButton;
    private Button calcolaButton;
    // Dichiarazione delle variabili per i tassi di cambio
    private final double euroToDollar = 1.06;
    private final double dollarToEuro = 0.95;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Associazione delle variabili ai widget dell'interfaccia tramite i relativi ID
        importoEditText = findViewById(R.id.importo_edittext);
        tassoDiCambioEditText = findViewById(R.id.tasso_di_cambio_edittext);
        radioGroup = findViewById(R.id.radio_group);
        euroToDollarRadioButton = findViewById(R.id.euro_to_dollar_radio_button);
        dollarToEuroRadioButton = findViewById(R.id.dollar_to_euro_radio_button);
        calcolaButton = findViewById(R.id.calcola_button);

        // Impostazione dell'azione da eseguire al click del bottone di calcolo
        calcolaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dichiarazione delle variabili per l'importo e il tasso di cambio
                double importo = 0;
                double tassoDiCambio = 0;

                // Controllo se l'importo inserito dall'utente è vuoto o meno, e in caso positivo lo converto in double
                if (!TextUtils.isEmpty(importoEditText.getText().toString())) {
                    importo = Double.parseDouble(importoEditText.getText().toString());
                }

                // Controllo quale radio button è selezionato, e imposto il tasso di cambio di conseguenza
                if (euroToDollarRadioButton.isChecked()) {
                    tassoDiCambio = euroToDollar;
                } else if (dollarToEuroRadioButton.isChecked()) {
                    tassoDiCambio = dollarToEuro;
                }

                // Controllo se l'importo e il tasso di cambio sono diversi da zero, e in caso contrario mostro un messaggio di errore
                if (importo == 0 || tassoDiCambio == 0) {
                    Toast.makeText(MainActivity.this, "Inserisci un importo", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Calcolo del risultato in base al tasso di cambio selezionato
                double risultato;
                if (euroToDollarRadioButton.isChecked()) {
                    // Conversione da Euro a Dollaro
                    risultato = importo * euroToDollar;
                } else {
                    // Conversione da Dollaro a Euro
                    risultato = importo * dollarToEuro;
                }

                // Creazione dell'intent per passare i dati alla seconda activity
                Intent intent = new Intent(MainActivity.this, RisultatoActivity.class);
                intent.putExtra("importo", importo);
                intent.putExtra("tasso_di_cambio", tassoDiCambio);
                startActivity(intent);
                finish();
            }
        });
    }
}
