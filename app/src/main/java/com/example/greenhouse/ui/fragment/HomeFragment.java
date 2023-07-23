package com.example.greenhouse.ui.fragment;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.greenhouse.R;
import com.example.greenhouse.model.GreenHouseModel;
import com.example.greenhouse.model.RecommendedMeasurementsModel;
import com.example.greenhouse.repository.GreenHouseRepository;
import com.example.greenhouse.ui.adapter.HomeGreenHouseAdapter;
import com.example.greenhouse.ui.adapter.RecommendedMeasurementsAdapter;
import com.example.greenhouse.ui.recyclerviewinterface.RecyclerViewInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements RecyclerViewInterface {

    private static final String TAG = HomeFragment.class.getSimpleName();
    private GreenHouseRepository repository;
    private HomeGreenHouseAdapter adapter;

    private RecommendedMeasurementsAdapter recommendedAdapter;

    private List<RecommendedMeasurementsModel> recommendedMeasurements;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recommendedMeasurements = getHardcodedRecommendedMeasurements();
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

       //greenhouse list
        RecyclerView recyclerView = rootView.findViewById(R.id.horizontalGreenHouseRV);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new HomeGreenHouseAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        //Recommended values
        RecyclerView recommendedRecyclerView = rootView.findViewById(R.id.horizontalRecomValuesRV);
        LinearLayoutManager recommendedLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recommendedRecyclerView.setLayoutManager(recommendedLayoutManager);

        List<RecommendedMeasurementsModel> hardcodedRecommendedMeasurements = getHardcodedRecommendedMeasurements();
        recommendedAdapter = new RecommendedMeasurementsAdapter( hardcodedRecommendedMeasurements, this);
        recommendedRecyclerView.setAdapter(recommendedAdapter);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        repository = new GreenHouseRepository();

        getAllGreenHouses();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onItemClick(int position) {
        RecyclerView recyclerView = requireView().findViewById(R.id.horizontalRecomValuesRV);
        if (recyclerView.getId() == R.id.horizontalGreenHouseRV) {
            GreenhouseComponent greenhouseComponent = new GreenhouseComponent();

            Bundle args = new Bundle();
            args.putInt("position", position);
            greenhouseComponent.setArguments(args);

            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.navigation_gh_component, args);
        } else if (recyclerView.getId() == R.id.horizontalRecomValuesRV) {
            RecommendedMeasurementsModel selectedMeasurement = recommendedMeasurements.get(position);
            String measurementType = selectedMeasurement.getTitle();

            switch (measurementType) {
                case "Temperature":
                showMeasurementAlertDialog(measurementType);
                    break;
                case "Humidity":
                showMeasurementAlertDialog(measurementType);
                    break;
                case "Light":
                  showMeasurementAlertDialog(measurementType);
                    break;
                default:
                    // Handle the default case or do nothing if needed
                    break;
            }
        }
    }


    private void getAllGreenHouses() {
        repository.getAllGreenHouses(new Callback<List<GreenHouseModel>>() {
            @Override
            public void onResponse(Call<List<GreenHouseModel>> call, Response<List<GreenHouseModel>> response) {
                if (response.isSuccessful()) {
                    List<GreenHouseModel> greenhouses = response.body();
                    adapter.setGreenhouses(greenhouses);
                } else {
                    Log.e(TAG, "Failed to get greenhouses. Error code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<GreenHouseModel>> call, Throwable t) {
                Log.e(TAG, "Failed to get greenhouses. Error: " + t.getMessage());
            }
        });
    }

    private List<RecommendedMeasurementsModel> getHardcodedRecommendedMeasurements() {
        List<RecommendedMeasurementsModel> recommendedMeasurements = new ArrayList<>();

        recommendedMeasurements.add(new RecommendedMeasurementsModel("Temperature"));
        recommendedMeasurements.add(new RecommendedMeasurementsModel("Humidity"));
        recommendedMeasurements.add(new RecommendedMeasurementsModel("Light"));

        return recommendedMeasurements;
    }

    private void showMeasurementAlertDialog(String measurementType) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());

        String message;
        String title;
        switch (measurementType) {
            case "Temperature":
                title = "Temperature values";
                message = "Daytime temperature: <b>20°C to 30°C</b><br>" +
                        "Nighttime temperature: <b>15°C to 20°C</b><br>" +
                        "Temperature during germination: <b>25°C to 30°C</b><br>" +
                        "Temperature during flowering: <b>18°C to 24°C</b>";
                break;
            case "Humidity":
                title = "Humidity values";
                message = "Relative humidity: <b>60% to 80%</b><br>" +
                        "Higher humidity is generally required during the early growth stages, and it can be reduced as the plants mature.";
                break;
            case "Light":
                title = "Light values";
                message = "Light intensity: Varies based on the type of plants, but typically around <b>1000 to 1500 µmol/m²/s</b> for full sun plants. Shade-tolerant plants may require lower light levels.<br>" +
                        "Light duration: Generally, plants need around <b>12 to 16 hours</b> of light per day for optimal growth. Some plants might have specific photoperiod requirements.";
                break;
            default:
                title = "Recommended values";
                message = "You clicked on: " + measurementType;
                break;
        }

        builder.setMessage(Html.fromHtml(message, Html.FROM_HTML_MODE_COMPACT));
        builder.setTitle(title);
        builder.setPositiveButton("OK", null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


}