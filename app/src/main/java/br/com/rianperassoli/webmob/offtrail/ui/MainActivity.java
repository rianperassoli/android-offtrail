package br.com.rianperassoli.webmob.offtrail.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ListView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.WindowFeature;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.List;

import br.com.rianperassoli.webmob.offtrail.R;
import br.com.rianperassoli.webmob.offtrail.adapter.TrilheiroAdapter;
import br.com.rianperassoli.webmob.offtrail.model.Usuario;
import br.com.rianperassoli.webmob.offtrail.rest.CidadeClient;
import br.com.rianperassoli.webmob.offtrail.rest.Endereco;

@EActivity(R.layout.activity_main)
@Fullscreen
@WindowFeature(Window.FEATURE_NO_TITLE)
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @ViewById
    ListView lstTrilheiros;

    @Bean
    TrilheiroAdapter trilheiroAdapter;

    @Pref
    Configuracao_ configuracao;

    @RestService
    CidadeClient cidadeClient;


    @AfterViews
    public void inicializar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent telaTrilheiro = new Intent(MainActivity.this, TrilheiroActivity_.class);
                startActivity(telaTrilheiro);

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //pega o dado passado pelo putExtra na tela de login
        Usuario usuario = (Usuario) getIntent().getSerializableExtra("usuario");

        if (usuario != null) {
            Toast.makeText(this, "Seja bem-vindo " + usuario.getEmail(), Toast.LENGTH_SHORT).show();
        }

        View v = toolbar.getRootView();
        v.setBackgroundColor(configuracao.cor().get());

        Toast.makeText(this, configuracao.parametro().get(), Toast.LENGTH_SHORT).show();

        //editar as configuracoes
        //configuracao.edit().cor().put(Color.BLUE).apply();
    }

    @Override
    protected void onResume() {
        super.onResume();

        refreshListTrilheiros();
    }

    public  void refreshListTrilheiros(){
        lstTrilheiros.setAdapter(trilheiroAdapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_sincronizar) {
            consultaCidadePorNome("Sao Miguel Do Oeste");
        } else if (id == R.id.nav_preferencias) {
            //legal
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @UiThread
    public void mostrarResultado(String resultado){
        Toast.makeText(this, resultado, Toast.LENGTH_LONG).show();
    }

    @Background
    public void consultaCidadePorNome(String cidade){
        List<Endereco> enderecos = cidadeClient.getEndereco(cidade);

        if (enderecos != null && enderecos.size() > 0) {
            mostrarResultado(enderecos.get(0).toString());
        }
    }
}
