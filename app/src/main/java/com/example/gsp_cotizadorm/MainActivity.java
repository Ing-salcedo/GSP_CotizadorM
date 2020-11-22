package com.example.gsp_cotizadorm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText cantidad;
    private TextView costo;
    private Spinner combomateriales, combodijes, combotipos, combomonedas;
    private String materiales[], dijes[], tipos[], monedas[];
    private ArrayAdapter<String> adapter1, adapter2, adapter3, adapter4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cantidad = findViewById(R.id.txtCantidad);
        costo = findViewById(R.id.lblCosto);
        combomateriales = findViewById(R.id.cmbMaterial);
        combodijes = findViewById(R.id.cmbDije);
        combotipos = findViewById(R.id.cmbTipo);
        combomonedas = findViewById(R.id.cmbMoneda);

        materiales = getResources().getStringArray(R.array.materiales);
        dijes = getResources().getStringArray(R.array.dijes);
        tipos = getResources().getStringArray(R.array.tipos);
        monedas = getResources().getStringArray(R.array.monedas);

        adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, materiales);
        adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dijes);
        adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tipos);
        adapter4 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, monedas);

        combomateriales.setAdapter(adapter1);
        combodijes.setAdapter(adapter2);
        combotipos.setAdapter(adapter3);
        combomonedas.setAdapter(adapter4);
    }

    public void calcularcosto(View v){
        int cant, costototal;
        if (validar()) {
            cant = Integer.parseInt(cantidad.getText().toString());
            costototal = cant;
            costo.setText(getString(R.string.encabezado_costo) + costototal);
        }
    }

    public boolean validar(){
        if(cantidad.getText().toString().isEmpty()){
            cantidad.setError(getString(R.string.mensaje_error_cantidad));
            cantidad.requestFocus();
            return false;
        }
        return true;
    }

    public void limpiar(View v){
        cantidad.setText("");
        costo.setText("");
        cantidad.requestFocus();
    }
}