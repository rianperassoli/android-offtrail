package br.com.rianperassoli.webmob.offtrail.ui;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import br.com.rianperassoli.webmob.offtrail.R;

@EActivity(R.layout.activity_trilheiro)
public class TrilheiroActivity extends AppCompatActivity {

    @ViewById(R.id.edtTrilheiroNome)
    EditText edtNome;

    @ViewById(R.id.edtTrilheiroIdade)
    EditText edtIdade;

    @ViewById(R.id.spTrilheiroMoto)
    Spinner spMotos;

    public void salvar(View v){

        String nome = edtNome.getText().toString();
        String idade = edtIdade.getText().toString();

        Snackbar.make(v, "Salvando trilheiro " + nome + " de " + idade + " anos.", Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }

    public void cancelar(View v){

        finish();
    }
}
