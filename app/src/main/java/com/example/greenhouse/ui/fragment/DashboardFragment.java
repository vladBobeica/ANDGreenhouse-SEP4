package com.example.greenhouse.ui.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greenhouse.R;
import com.example.greenhouse.model.GreenHouseModel;
import com.example.greenhouse.model.RecommendedMeasurementsModel;
import com.example.greenhouse.repository.GreenHouseRepository;
import com.example.greenhouse.ui.adapter.GreenHouseAdapter;
import com.example.greenhouse.ui.recyclerviewinterface.RecyclerViewInterfaceDashboard;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DashboardFragment extends Fragment implements RecyclerViewInterfaceDashboard {
    private static final String TAG = DashboardFragment.class.getSimpleName();
    private GreenHouseRepository repository;
    private GreenHouseAdapter adapter;

    private EditText greenHouseName;
    private EditText greenHouseAddress;

    private EditText temperatureMinEditText;
    private EditText temperatureMaxEditText;

    private EditText humidityMinEditText;
    private EditText humidityMaxEditText;

    private EditText lightMinEditText;
    private EditText lightMaxEditText;

    private Button applyRecommendedSettingsButton;

    private static final String PREF_NAME = "GreenHousePrefs";
    private static final String KEY_GREENHOUSES = "greenhouses";
    private static final long CACHE_DURATION_MS = 5 * 60 * 1000; // 5 minutes
    private SharedPreferences sharedPreferences;
    private long lastCacheTime = 0;

    private List<GreenHouseModel> cachedGreenhouses = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);
        RecyclerView recyclerView = rootView.findViewById(R.id.greenHouseRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new GreenHouseAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        repository = new GreenHouseRepository();
        sharedPreferences = requireContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        // Load the greenhouses from cache if available and the cache is still valid
        Log.d("GreenHouses", "Fetching green houses");
        if (isCacheValid() && false) {
            loadGreenHousesFromCache();
        } else {
            // Fetch greenhouses from the API if the cache is not valid
            getAllGreenHouses();
        }

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(view1 -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
            builder.setTitle("Add New Greenhouse");
            View dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.modal_add_greenhouse, null);
            builder.setView(dialogView);

            EditText nameEditText = dialogView.findViewById(R.id.greenhouseNameEditText);
            EditText addressEditText = dialogView.findViewById(R.id.greenhouseAddressEditText);

            EditText temperatureMinEditText = dialogView.findViewById(R.id.temperatureMinEditText);
            EditText temperatureMaxEditText = dialogView.findViewById(R.id.temperatureMaxEditText);

            EditText humidityMinEditText = dialogView.findViewById(R.id.humidityMinEditText);
            EditText humidityMaxEditText = dialogView.findViewById(R.id.humidityMaxEditText);

            EditText lightMinEditText = dialogView.findViewById(R.id.lightMinEditText);
            EditText lightMaxEditText = dialogView.findViewById(R.id.lightMaxEditText);

            Button applyRecommendedSettingsButton = dialogView.findViewById(R.id.applyRecommendedSettingsButton);

            applyRecommendedSettingsButton.setOnClickListener(v -> {
                Log.d("GreenHouse", "Apply button tapped");
                temperatureMinEditText.setText("15");
                temperatureMaxEditText.setText("25");

                humidityMinEditText.setText("40");
                humidityMaxEditText.setText("60");

                lightMinEditText.setText("1000");
                lightMaxEditText.setText("5000");
            });

            builder.setPositiveButton("Add", (dialog, which) -> {
                String name = nameEditText.getText().toString().trim();
                String address = addressEditText.getText().toString().trim();

                String temperatureMin = temperatureMinEditText.getText().toString().trim();
                String temperatureMax = temperatureMaxEditText.getText().toString().trim();

                String humidityMin = humidityMinEditText.getText().toString().trim();
                String humidityMax = humidityMaxEditText.getText().toString().trim();

                String lightMin = lightMinEditText.getText().toString().trim();
                String lightMax = lightMaxEditText.getText().toString().trim();

                if (!name.isEmpty() && !address.isEmpty() && !temperatureMin.isEmpty() && !temperatureMax.isEmpty() && !humidityMin.isEmpty() && !humidityMax.isEmpty() && !lightMin.isEmpty() && !lightMax.isEmpty()) {
                    addNewGreenHouse(name, address, new RecommendedMeasurementsModel(temperatureMin, temperatureMax, humidityMin, humidityMax, lightMin, lightMax));
                } else {
                    // Show a toast or error message indicating that both fields are required
                    Toast.makeText(requireContext(), "Please, provide values for all the fields.", Toast.LENGTH_SHORT).show();
                }
            });

            builder.setNegativeButton("Cancel", null);

            AlertDialog dialog = builder.create();
            dialog.show();
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onItemClick(int position) {
        GreenhouseComponent greenhouseComponent = new GreenhouseComponent();

        Bundle args = new Bundle();
        args.putInt("greenhouse_id", position);
        greenhouseComponent.setArguments(args);

        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigate(R.id.navigation_gh_component, args);
    }

    @Override
    public void onItemLongClick(int position) {
        Log.d(TAG, "Long clicked on position: " + position + ", greenhouseId: " + adapter.getGreenHouse(position).getId());
        GreenHouseModel selectedGreenHouse = adapter.getGreenHouse(position);
        if (selectedGreenHouse != null) {
            int greenhouseId = selectedGreenHouse.getId();
            Log.d(TAG, "Long clicked on position: " + position + ", greenhouseId: " + greenhouseId);


            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
            builder.setTitle("Confirm");
            builder.setMessage("Do you want to remove this greenhouse?");
            builder.setPositiveButton("Yes", (dialog, which) -> {
                removeGreenHouseFromRepository(greenhouseId, position);
            });
            builder.setNegativeButton("No", (dialog, which) -> {

            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
    private boolean isCacheValid() {
        long currentTime = System.currentTimeMillis();
        return (currentTime - lastCacheTime) < CACHE_DURATION_MS;
    }

    private void loadGreenHousesFromCache() {
        String jsonGreenhouses = sharedPreferences.getString(KEY_GREENHOUSES, null);
        if (jsonGreenhouses != null) {
            Type type = new TypeToken<List<GreenHouseModel>>() {}.getType();
            List<GreenHouseModel> greenhouses = new Gson().fromJson(jsonGreenhouses, type);
            if (greenhouses != null) {
                adapter.setGreenhouses(greenhouses);
            }
        }
    }

    private void saveGreenHousesToCache(List<GreenHouseModel> greenhouses) {
        String jsonGreenhouses = new Gson().toJson(greenhouses);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_GREENHOUSES, jsonGreenhouses);
        editor.apply();
        lastCacheTime = System.currentTimeMillis();
    }

    private void getAllGreenHouses() {
        if (cachedGreenhouses.isEmpty()) {
            repository.getAllGreenHouses(new Callback<List<GreenHouseModel>>() {
                @Override
                public void onResponse(Call<List<GreenHouseModel>> call, Response<List<GreenHouseModel>> response) {
                    if (response.isSuccessful()) {
                        List<GreenHouseModel> greenhouses = response.body();
                        adapter.setGreenhouses(greenhouses);
                        cachedGreenhouses.addAll(greenhouses);
                    } else {
                        Log.e(TAG, "Failed to get greenhouses. Error code: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<List<GreenHouseModel>> call, Throwable t) {
                    Log.e(TAG, "Failed to get greenhouses. Error: " + t.getMessage());
                }
            });
        } else {
            // If data is already cached, use it directly without making an API call
            adapter.setGreenhouses(cachedGreenhouses);
        }
    }

    private void removeGreenHouseFromRepository(int greenhouseId, int position) {
        repository.removeGreenHouse(greenhouseId, new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Remove the greenhouse from the adapter
                    adapter.removeGreenHouse(greenhouseId);

                    // Remove the greenhouse from the cached list
                    GreenHouseModel removedGreenhouse = removeGreenhouseFromCachedList(greenhouseId);
                    if (removedGreenhouse != null) {
                        // Save the updated list to the SharedPreferences cache
                        saveGreenHousesToCache(cachedGreenhouses);
                    }
                } else {
                    Log.e(TAG, "Failed to remove greenhouse. Error code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e(TAG, "Failed to remove greenhouse. Error: " + t.getMessage());
            }
        });
    }

    private GreenHouseModel removeGreenhouseFromCachedList(int greenhouseId) {
        for (int i = 0; i < cachedGreenhouses.size(); i++) {
            GreenHouseModel greenhouse = cachedGreenhouses.get(i);
            if (greenhouse.getId() == greenhouseId) {
                cachedGreenhouses.remove(i);
                return greenhouse;
            }
        }
        return null;
    }

    private void addNewGreenHouse(String name, String address, RecommendedMeasurementsModel recommendedMeasurementsModel) {
        GreenHouseModel newGreenhouse = new GreenHouseModel(name, address, recommendedMeasurementsModel);

        repository.createGreenHouse(newGreenhouse, new Callback<GreenHouseModel>() {
            @Override
            public void onResponse(Call<GreenHouseModel> call, Response<GreenHouseModel> response) {
                if (response.isSuccessful()) {
                    // The new greenhouse was successfully created and added to the server
                    GreenHouseModel createdGreenhouse = response.body();
                    if (createdGreenhouse != null) {
                        // Add the new greenhouse to the adapter
                        adapter.addGreenHouse(createdGreenhouse);
                        // Add the new greenhouse to the cache
                        cachedGreenhouses.add(createdGreenhouse);
                        System.out.println("");
                    }
                } else {
                    Log.e(TAG, "Failed to create greenhouse. Error code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<GreenHouseModel> call, Throwable t) {
                Log.e(TAG, "Failed to create greenhouse. Error: " + t.getMessage());
            }
        });
    }

    private void applyRecommendedSettings() {
        // Set predefined values for min and max fields
        temperatureMinEditText.setText("15");
        temperatureMaxEditText.setText("25");

        humidityMinEditText.setText("40");
        humidityMaxEditText.setText("60");

        lightMinEditText.setText("1000");
        lightMaxEditText.setText("5000");
    }
}