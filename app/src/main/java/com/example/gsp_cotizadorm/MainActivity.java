package com.example.gsp_cotizadorm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
        int cant, costototal=0, monetransaccion=0, poscmbmat, poscmbdije, poscmbtipo, poscmbmone;
        InputMethodManager imm;
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(cantidad.getWindowToken(),0);

        if (validar()) {
            cant = Integer.parseInt(cantidad.getText().toString());
            poscmbmone = combomonedas.getSelectedItemPosition();
            poscmbmat = combomateriales.getSelectedItemPosition();
            poscmbdije = combodijes.getSelectedItemPosition();
            poscmbtipo = combotipos.getSelectedItemPosition();
            switch (poscmbmone){
                case 0:
                    monetransaccion = 1;
                    break;
                case 1:
                    monetransaccion = 3200;
                    break;
            }
            if (poscmbmat == 0 && poscmbdije == 0){
                switch (poscmbtipo){
                    case 0:
                    case 1:
                        costototal = cant * 100 * monetransaccion;
                        break;
                    case 2:
                        costototal = cant * 80 * monetransaccion;
                        break;
                    case 3:
                        costototal = cant * 70 * monetransaccion;
                        break;
                }
            }
            if (poscmbmat == 0 && poscmbdije == 1){
                switch (poscmbtipo){
                    case 0:
                    case 1:
                        costototal = cant * 120 * monetransaccion;
                        break;
                    case 2:
                        costototal = cant * 100 * monetransaccion;
                        break;
                    case 3:
                        costototal = cant * 90 * monetransaccion;
                        break;
                }
            }
            if (poscmbmat == 1 && poscmbdije == 0){
                switch (poscmbtipo){
                    case 0:
                    case 1:
                        costototal = cant * 90 * monetransaccion;
                        break;
                    case 2:
                        costototal = cant * 70 * monetransaccion;
                        break;
                    case 3:
                        costototal = cant * 50 * monetransaccion;
                        break;
                }
            }
            if (poscmbmat == 1 && poscmbdije == 1){
                switch (poscmbtipo){
                    case 0:
                    case 1:
                        costototal = cant * 110 * monetransaccion;
                        break;
                    case 2:
                        costototal = cant * 90 * monetransaccion;
                        break;
                    case 3:
                        costototal = cant * 80 * monetransaccion;
                        break;
                }
            }
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
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
    }
}