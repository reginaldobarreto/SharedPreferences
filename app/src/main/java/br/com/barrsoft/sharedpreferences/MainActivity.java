package br.com.barrsoft.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    private static final String FILE_REFERENCE = "file_preference";
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.textViewSaida)
    TextView textViewSaida;
    @BindView(R.id.textInputEditText)
    TextInputEditText inputEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        retornoSharedPreferences();

    }

    private void retornoSharedPreferences() {

        SharedPreferences sharedPreferences = getSharedPreferences(FILE_REFERENCE, 0); //mode 0 private

        if(sharedPreferences.contains("nome")){
            String retornoNome = sharedPreferences.getString("nome","Null");
            textViewSaida.setText("Olá, " + retornoNome);
        }else {
            textViewSaida.setText(getString(R.string.usuario_sem_registro));
        }


    }

    @OnClick(R.id.button)
    public void submit(View view) {
        // TODO submit data to server...

        SharedPreferences sharedPreferences = getSharedPreferences(FILE_REFERENCE, 0); //mode 0 private
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(inputEditText.getText().length() == 0 || inputEditText.getText().toString() == ""){

            inputEditText.setHint("Digite seu nome");
        }else{
            String nome = inputEditText.getText().toString();
            editor.putString("nome", nome);
            editor.commit();
            inputEditText.setText("");
            textViewSaida.setText("Olá, " + nome);
        }
    }


}
