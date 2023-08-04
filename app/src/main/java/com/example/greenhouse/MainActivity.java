package com.example.greenhouse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.example.greenhouse.databinding.ActivityMainBinding;
import com.example.greenhouse.model.MeasurementModel;
import com.example.greenhouse.model.RecommendedMeasurementsModel;
import com.example.greenhouse.session.SessionManager;
import com.example.greenhouse.ui.modal.AddGreenhouseModal;
import com.example.greenhouse.utils.AlertManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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

        checkPermissionAndHandle();

        MeasurementModel liveMeasurements = new MeasurementModel(1, "2023-07-15", "20°C", "40%", "800 Lux");

        RecommendedMeasurementsModel recommendedValues = new RecommendedMeasurementsModel(
                "22°C", "26°C", "40%", "60%", "800 Lux", "1200 Lux"
        );

        AlertManager alertManager = new AlertManager(this);

        alertManager.checkAndSendAlerts(liveMeasurements, recommendedValues);
    }

    private void checkPermissionAndHandle() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.INTERNET)
                == PackageManager.PERMISSION_GRANTED) {

        } else {
            requestInternetPermission();
        }
    }

    private void requestInternetPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{android.Manifest.permission.INTERNET},
                AlertManager.INTERNET_PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == AlertManager.INTERNET_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            } else {
                Toast.makeText(this, "Permission denied. Cannot perform the operation.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static SessionManager getSessionManager() {
        return sessionManager;
    }
}
