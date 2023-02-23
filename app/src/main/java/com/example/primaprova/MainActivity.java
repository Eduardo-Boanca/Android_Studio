package com.example.primaprova;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText importoEditText;
    private EditText cambioEditText;
    private TextView risultatoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        importoEditText = findViewById(R.id.importo_edittext);
        cambioEditText = findViewById(R.id.cambio_edittext);
        risultatoTextView = findViewById(R.id.risultato_textview);

        Button calcolaButton = findViewById(R.id.calcola_button);
        calcolaButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                double importo = Double.parseDouble(importoEditText.getText().toString());
                double cambio = Double.parseDouble(cambioEditText.getText().toString());
                double dollari = importo * cambio;
                double euro = importo / cambio;
                risultatoTextView.setText(String.format(Locale.getDefault(), "%.2f $ = %.2f €", dollari, euro));
            }
        });
    }
}

