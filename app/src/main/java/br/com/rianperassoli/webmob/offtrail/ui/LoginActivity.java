package br.com.rianperassoli.webmob.offtrail.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.WindowFeature;

import br.com.rianperassoli.webmob.offtrail.R;

@EActivity(R.layout.activity_login)
@Fullscreen
@WindowFeature(Window.FEATURE_NO_TITLE)
public class LoginActivity extends AppCompatActivity {

    @ViewById(R.id.edtLogin)
    EditText edtLogin;

    @ViewById(R.id.edtPassword)
    EditText edtPassword;

    public void entrar(View v){

        String login = edtLogin.getText().toString();
        String senha = edtPassword.getText().toString();

        if ((login != null) && (senha != null) && login.trim().equals("rian") && senha.trim().equals("rian")){

            Intent telaMain = new Intent(this, MainActivity.class);

            startActivity(telaMain);

            finish();
        } else {
            Toast.makeText(this, "Login inválido", Toast.LENGTH_LONG).show();
            edtLogin.setText("");
            edtPassword.setText("");
            edtLogin.requestFocus();
        }

    }

    public void sair(View v){

        finish();

        System.exit(0);
    }
}
