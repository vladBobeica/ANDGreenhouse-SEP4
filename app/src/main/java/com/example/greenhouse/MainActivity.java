package com.example.greenhouse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.greenhouse.databinding.ActivityMainBinding;
import com.example.greenhouse.session.SessionManager;
import com.example.greenhouse.ui.modal.AddGreenhouseModal;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private static SessionManager sessionManager;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sessionManager = new SessionManager(getApplicationContext());

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                 R.id.navigation_dashboard, R.id.navigation_notifications,  R.id.navigation_settings)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(navView, navController);

     //   FloatingActionButton fab = findViewById(R.id.fab);
       // fab.setOnClickListener(v -> {
         //   showAddGreenhouseDialog();
        //});
    }

    public static SessionManager getSessionManager() {
        return sessionManager;
    }

    private void showAddGreenhouseDialog() {
        AddGreenhouseModal dialogFragment = new AddGreenhouseModal();
        dialogFragment.show(getSupportFragmentManager(), "AddGreenhouseModal");
    }
}

