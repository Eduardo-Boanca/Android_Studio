package com.example.primaprova;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private EditText importoEditText;
    private TextView risultatoTextView;
    private double tassoDiCambio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        importoEditText = findViewById(R.id.importo_edittext);
        risultatoTextView = findViewById(R.id.risultato_textview);

        RadioGroup radioGroup = findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = group.findViewById(checkedId);
                String tassoDiCambioString = radioButton.getTag().toString();
                tassoDiCambio = Double.parseDouble(tassoDiCambioString);
            }
        });

        Button calcolaButton = findViewById(R.id.calcola_button);
        calcolaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double importo = Double.parseDouble(importoEditText.getText().toString());
                    double convertedAmount = tassoDiCambio * importo;
                    String result = String.format(Locale.getDefault(), "%.2f", convertedAmount);
                    risultatoTextView.setText(result);
                } catch (NumberFormatException e) {
                    risultatoTextView.setText("Inserisci un importo valido");
                }
            }
        });
    }

}