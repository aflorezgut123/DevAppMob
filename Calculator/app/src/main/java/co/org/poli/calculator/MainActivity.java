package co.org.poli.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity {

    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.input);
        display.setShowSoftInputOnFocus(false);

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(getString(R.string.display).equals(display.getText().toString())){
                    display.setText("");
                }
            }
        });
    }

    private void actualizarTexto(String valorAdicionar){
        String valorAnterior = display.getText().toString();
        int posCursor = display.getSelectionStart();
        String stringIzq = valorAnterior.substring(0, posCursor);
        String stringDer = valorAnterior.substring(posCursor);
        if(getString(R.string.display).equals(display.getText().toString())){
            display.setText(valorAdicionar);
            display.setSelection(posCursor + 1);
        }else {
            display.setText(String.format("%s%s%s", stringIzq, valorAdicionar, stringDer));
            display.setSelection(posCursor + 1);
        }

    }

    public void exponenteBTN(View view){
        actualizarTexto("^");
    }

    public void nueveBTN(View view){
        actualizarTexto("9");
    }

    public void seisBTN(View view){
        actualizarTexto("6");
    }

    public void tresBTN(View view){
        actualizarTexto("3");
    }

    public void puntoBTN(View view){
        actualizarTexto(".");
    }

    public void divisionBTN(View view){
        actualizarTexto("÷");
    }

    public void multiplicacionBTN(View view){
        actualizarTexto("×");
    }

    public void restaBTN(View view){
        actualizarTexto("-");
    }

    public void sumaBTN(View view){
        actualizarTexto("+");
    }

    public void igualBTN(View view){
        String expUsuario = display.getText().toString();

        expUsuario = expUsuario.replaceAll("÷", "/");
        expUsuario = expUsuario.replaceAll("×", "*");

        Expression exp = new Expression(expUsuario);

        String resultado = String.valueOf(exp.calculate());

        display.setText(resultado);
        display.setSelection(resultado.length());
    }

    public void borrarBTN(View view){
        int posCursor = display.getSelectionStart();
        int tamTexto =  display.getText().length();

        if(posCursor != 0 && tamTexto != 0){
            SpannableStringBuilder seleccion = (SpannableStringBuilder) display.getText();
            seleccion.replace(posCursor - 1, posCursor, "");
            display.setText(seleccion);
            display.setSelection(posCursor - 1);
        }
    }

    public void limpiarBTN(View view){
        display.setText("");
    }

    public void parentesisBTN(View view){
        int posCursor = display.getSelectionStart();
        int iniciaParentesis = 0;
        int cierraParentesis = 0;
        int textLen = display.getText().length();
        for (int i = 0; i < posCursor; i++){
            if (display.getText().toString().substring(i, i+1).equals("(")){
                iniciaParentesis += 1;
            }
            if (display.getText().toString().substring(i, i+1).equals(")")){
                cierraParentesis += 1;
            }
        }

        if (iniciaParentesis == cierraParentesis || display.getText().toString().substring(textLen-1, textLen).equals("(")) {
            actualizarTexto("(");
            display.setSelection(posCursor + 1);
        }
        if (cierraParentesis < iniciaParentesis && !display.getText().toString().substring(textLen-1, textLen).equals("(")) {
            actualizarTexto(")");
        }
        display.setSelection(posCursor + 1);
    }

    public void sieteBTN(View view){
        actualizarTexto("7");
    }

    public void ochoBTN(View view){
        actualizarTexto("8");
    }

    public void cuatroBTN(View view){
        actualizarTexto("4");
    }

    public void cincoBTN(View view){
        actualizarTexto("5");
    }

    public void unoBTN(View view){
        actualizarTexto("1");
    }

    public void dosBTN(View view){
        actualizarTexto("2");
    }

    public void plusMinusBTN(View view){
        actualizarTexto("-");
    }

    public void zeroBTN(View view){
        actualizarTexto("0");
    }

}