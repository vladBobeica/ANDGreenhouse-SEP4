package com.example.greenhouse.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greenhouse.R;
import com.example.greenhouse.databinding.FragmentDashboardBinding;
import com.example.greenhouse.model.GreenHouseModel;
import com.example.greenhouse.repository.GreenHouseRepository;
import com.example.greenhouse.ui.adapter.GreenHouseAdapter;
import com.example.greenhouse.ui.recyclerviewinterface.RecyclerViewInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DashboardFragment extends Fragment implements RecyclerViewInterface {
    private static final String TAG = DashboardFragment.class.getSimpleName();
    private GreenHouseRepository repository;
    private GreenHouseAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);
        RecyclerView recyclerView = rootView.findViewById(R.id.greenHouseRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new GreenHouseAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);
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
        GreenhouseComponent greenhouseComponent = new GreenhouseComponent();

        Bundle args = new Bundle();
        args.putInt("position", position);
        greenhouseComponent.setArguments(args);

        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigate(R.id.navigation_gh_component, args);
    }

    private void getAllGreenHouses() {
        repository.getAllGreenHouses(new Callback<List<GreenHouseModel>>() {
            @Override
            public void onResponse(Call<List<GreenHouseModel>> call, Response<List<GreenHouseModel>> response) {
                if (response.isSuccessful()) {
                    List<GreenHouseModel> greenhouses = response.body();
                    adapter.setGreenhouses(greenhouses);
                } else {
                    // Handle API error (e.g., display an error message)
                    Log.e(TAG, "Failed to get greenhouses. Error code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<GreenHouseModel>> call, Throwable t) {
                // Handle network or other errors (e.g., display an error message)
                Log.e(TAG, "Failed to get greenhouses. Error: " + t.getMessage());
            }
        });
    }
}