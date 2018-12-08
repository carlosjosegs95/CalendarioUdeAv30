package com.example.carlosjose95.calendarioudeav20;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Usuario persona = new Usuario();

    private FragmentManager fm;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CiclicoActivity.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Bundle args = getIntent().getExtras();

        if (args != null) {
            persona = args.getParcelable("usuario ingresado");
        }

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();

        InicioFragment inicioFragment = new InicioFragment();
        ft.add(R.id.frame, inicioFragment).commit();
        Bundle bundle = new Bundle();
        bundle.putParcelable("usuario", persona);
        inicioFragment.setArguments(bundle);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        ft = fm.beginTransaction();

        if(id == R.id.Inicio){
            InicioFragment inicioFragment = new InicioFragment();
            ft.replace(R.id.frame, inicioFragment).commit();
            Bundle bundle = new Bundle();
            bundle.putParcelable("usuario", persona);
            inicioFragment.setArguments(bundle);
        }else if (id == R.id.MiPerfil) {
            PerfilFragment perfilFragment = new PerfilFragment();
            ft.replace(R.id.frame, perfilFragment).commit();
            Bundle bundle = new Bundle();
            bundle.putParcelable("usuario", persona);
            perfilFragment.setArguments(bundle);
        }else if (id == R.id.Agenda) {
            AgendaFragment agendaFragment = new AgendaFragment();
            ft.replace(R.id.frame, agendaFragment).commit();
        }else if (id == R.id.Calendario) {
            CalendarioFragment calendarioFragment = new CalendarioFragment();
            ft.replace(R.id.frame, calendarioFragment).commit();
            Bundle bundle = new Bundle();
            bundle.putParcelable("usuario", persona);
            calendarioFragment.setArguments(bundle);
        }else if (id == R.id.Buscar) {
            BuscarFragment buscarFragment = new BuscarFragment();
            ft.replace(R.id.frame, buscarFragment).commit();
        }else if (id == R.id.Cerrar) {
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }
}
