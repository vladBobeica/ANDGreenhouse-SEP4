package com.example.greenhouse.repository.mock;

import com.example.greenhouse.model.GreenHouseModel;

import java.util.ArrayList;
import java.util.List;

public class MockGreenHouseRepository {
    private static final List<GreenHouseModel> greenHouses = new ArrayList<>();

    static {
        greenHouses.add(new GreenHouseModel(1, "Vlad's Garden", "Horsens, Gasvej 11"));
        greenHouses.add(new GreenHouseModel(2, "Alin's Potato Farm", "Vejle, Sundvej 22"));
        greenHouses.add(new GreenHouseModel(3, "Eggplant Farm", "Vejle, Sundvej 22"));
        greenHouses.add(new GreenHouseModel(4, "Chicken Farm", "Vejle, Sundvej 22"));
    }

    public static List<GreenHouseModel> getGreenHouses() {
        return new ArrayList<>(greenHouses);
    }

    public static GreenHouseModel addGreenHouse(GreenHouseModel greenHouseToCreate) {
        if (greenHouses.isEmpty()) {
            greenHouseToCreate.setId(1);
        } else {
            int lastGreenHouseId = greenHouses.get(greenHouses.size() - 1).getId();

            greenHouseToCreate.setId(lastGreenHouseId + 1);
        }

        greenHouses.add(greenHouseToCreate);

        return greenHouseToCreate;
    }

    public static boolean removeGreenHouse(int greenHouseId) {
        return greenHouses.removeIf(greenHouse -> greenHouse.getId() == greenHouseId);
    };
}
