package com.example.greenhouse.repository.mock;

import com.example.greenhouse.model.GreenHouseModel;
import com.example.greenhouse.model.RecommendedMeasurementsModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MockGreenHouseRepository {
    private static final List<GreenHouseModel> greenHouses = new ArrayList<>();

    static {
        greenHouses.add(new GreenHouseModel(1, "Vlad's Garden", "Horsens, Gasvej 11", new RecommendedMeasurementsModel(1,"15", "25", "103", "110", "12", "20")));
        greenHouses.add(new GreenHouseModel(2, "Alin's Potato Farm", "Vejle, Sundvej 22", new RecommendedMeasurementsModel(2,"15", "25", "103", "110", "12", "20")));
        greenHouses.add(new GreenHouseModel(3, "Eggplant Farm", "Vejle, Sundvej 22", new RecommendedMeasurementsModel(3,"15", "25", "103", "110", "12", "20")));
        greenHouses.add(new GreenHouseModel(4, "Chicken Farm", "Vejle, Sundvej 22", new RecommendedMeasurementsModel(4,"15", "25", "103", "110", "12", "20")));
    }

    public static List<GreenHouseModel> getGreenHouses() {
        return new ArrayList<>(greenHouses);
    }

    public static GreenHouseModel addGreenHouse(GreenHouseModel greenHouseToCreate) {
        int newId = generateNewId();

        greenHouseToCreate.setId(newId);
        greenHouseToCreate.getRecommendedMeasurementsModel().setId(newId);

        greenHouses.add(greenHouseToCreate);

        return greenHouseToCreate;
    }

    public static boolean removeGreenHouse(int greenHouseId) {
        return greenHouses.removeIf(greenHouse -> greenHouse.getId() == greenHouseId);
    };

    public static Optional<GreenHouseModel> getGreenHouseById(int id) {
        for (GreenHouseModel greenhouse:greenHouses ) {
            if (id == greenhouse.getId()) {
                return Optional.of(greenhouse);
            }
        }

        return Optional.empty();
    }

    private static int generateNewId() {
        if (greenHouses.isEmpty()) return  1;

        int lastGreenHouseId = greenHouses.get(greenHouses.size() - 1).getId();
        return lastGreenHouseId + 1;
    }
}
