package com.example.greenhouse.model.mock;

import com.example.greenhouse.model.GreenHouseModel;

import java.util.ArrayList;
import java.util.List;

public class MockGreenHouseRepository {
    private static final List<GreenHouseModel> greenHouses = new ArrayList<>();

    static {
        greenHouses.add(new GreenHouseModel(1, "My Garden", "Horsens, Gasvej 11"));
        greenHouses.add(new GreenHouseModel(2, "Potato Farm", "Vejle, Sundvej 22"));
    }

    public static List<GreenHouseModel> getGreenHouses() {
        return greenHouses;
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
