package com.example.greenhouse.ui.fragment;

import android.app.AlertDialog;
import android.database.Cursor;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greenhouse.R;
import com.example.greenhouse.api.MockApiService;
import com.example.greenhouse.data.DbHelper;
import com.example.greenhouse.databinding.FragmentDashboardBinding;
import com.example.greenhouse.model.GreenHouseModel;
import com.example.greenhouse.repository.GreenHouseRepository;
import com.example.greenhouse.ui.adapter.CustomGreenhouseAdapter;
import com.example.greenhouse.ui.adapter.GreenHouseAdapter;
import com.example.greenhouse.ui.modal.AddGreenhouseModal;
import com.example.greenhouse.ui.modal.UpdateGreenhouseModal;
import com.example.greenhouse.ui.recyclerviewinterface.RecyclerViewInterfaceDashboard;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DashboardFragment extends Fragment implements RecyclerViewInterfaceDashboard {
    private static final String TAG = DashboardFragment.class.getSimpleName();
    private GreenHouseRepository repository;
    private GreenHouseAdapter adapter;
    private CustomGreenhouseAdapter customAdapter;
    private DbHelper dbHelper;
    ArrayList<String> greenhouse_id, greenhouse_name, greenhouse_address, greenhouse_userid;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);
        RecyclerView recyclerView = rootView.findViewById(R.id.greenHouseRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ItemTouchHelper itemTouchHelper= new ItemTouchHelper(simpleCallback);
        dbHelper = new DbHelper(requireContext());

        greenhouse_id = new ArrayList<>();
        greenhouse_name = new ArrayList<>();
        greenhouse_address = new ArrayList<>();

        customAdapter = new CustomGreenhouseAdapter(requireContext(), greenhouse_id, greenhouse_name, greenhouse_address);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(customAdapter);

        int currentUserId = MockApiService.getCurrentUserId();

        storeDataInArray(currentUserId);

        return rootView;
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            int position = viewHolder.getAdapterPosition();

            switch(direction ){
                case ItemTouchHelper.LEFT:

                    // Get the id of the greenhouse to be deleted from the list
                    String idToDelete = greenhouse_id.get(position);
                    // Remove the greenhouse from the local database
                    dbHelper.deleteGreenhouse(idToDelete);
                    // Remove the greenhouse from the lists
                    greenhouse_id.remove(position);
                    greenhouse_name.remove(position);
                    greenhouse_address.remove(position);
                    // Notify the adapter that an item has been removed
                    customAdapter.notifyItemRemoved(position);
                    break;

                case ItemTouchHelper.RIGHT:
                    String idToEdit = greenhouse_id.get(position);
                    String nameToEdit = greenhouse_name.get(position);
                    String addressToEdit = greenhouse_address.get(position);

                    // Show the edit dialog for the greenhouse
                    showEditGreenhouseDialog(idToEdit, nameToEdit, addressToEdit);

                    // Notify the adapter that an item has been changed
                    customAdapter.notifyItemChanged(position);
                    break;
            }

        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(requireContext(), R.color.red))
                    .addSwipeLeftActionIcon(R.drawable.baseline_delete_sweep_24)
                    .addSwipeRightBackgroundColor(ContextCompat.getColor(requireContext(), R.color.yellow))
                    .addSwipeRightActionIcon(R.drawable.baseline_edit_note_24)
                    .create()
                    .decorate();
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        repository = new GreenHouseRepository();

        //getAllGreenHouses();

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(v -> showAddGreenhouseDialog());
    }

    private void showEditGreenhouseDialog(String id, String name, String address) {
        UpdateGreenhouseModal updateGreenhouseModal = new UpdateGreenhouseModal(id, name, address);
        updateGreenhouseModal.show(getChildFragmentManager(), "UpdateGreenhouseModal");
    }


    void storeDataInArray(int loggedInUserId) {
        Cursor cursor = dbHelper.readAllData();
        if (cursor == null) {
            Toast.makeText(requireContext(), "No data.", Toast.LENGTH_SHORT).show();
            return;
        }

        greenhouse_id.clear();
        greenhouse_name.clear();
        greenhouse_address.clear();

        while (cursor.moveToNext()) {
            int userIdFromDB = cursor.getInt(3);
            if (userIdFromDB == loggedInUserId) {
                greenhouse_id.add(cursor.getString(0));
                greenhouse_name.add(cursor.getString(1));
                greenhouse_address.add(cursor.getString(2));
            }
        }

        cursor.close();
        customAdapter.notifyDataSetChanged();
    }




    private void showAddGreenhouseDialog() {
        AddGreenhouseModal addGreenhouseModal = new AddGreenhouseModal();
        addGreenhouseModal.show(getChildFragmentManager(), "AddGreenhouseModal");
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

    private void removeGreenHouseFromRepository(int greenhouseId, int position) {
        repository.removeGreenHouse(greenhouseId, new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    adapter.removeGreenHouse(greenhouseId);
                    getAllGreenHouses();
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



}