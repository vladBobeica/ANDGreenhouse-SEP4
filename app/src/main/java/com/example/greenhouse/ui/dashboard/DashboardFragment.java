package com.example.greenhouse.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greenhouse.R;
import com.example.greenhouse.adapter.GH_RecycleViewAdapter;
import com.example.greenhouse.databinding.FragmentDashboardBinding;
import com.example.greenhouse.model.GreenhouseModel;
import com.example.greenhouse.ui.components.GreenhouseComponent;
import com.example.greenhouse.ui.recyclerviewinterface.RecyclerViewInterface;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class DashboardFragment extends Fragment implements RecyclerViewInterface {

    private FragmentDashboardBinding binding;
    ArrayList<GreenhouseModel> greenhouseModel = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = root.findViewById(R.id.greenHouseRV);

        setUpGreenHouseModel();

        GH_RecycleViewAdapter adapter = new GH_RecycleViewAdapter(getContext(), greenhouseModel, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return root;


    }

    private void setUpGreenHouseModel(){
        String[] greenhouseNames = getResources().getStringArray(R.array.green_houses_name);

        for(int i = 0; i<greenhouseNames.length; i++){
            greenhouseModel.add(new GreenhouseModel(greenhouseNames[i]));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
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
}