package cf.main.engines;

import cf.main.entities.ChickenStatus;
import cf.main.entities.Equipments;

import java.util.HashMap;

public class CharacterManager {

    private String chickenName;
    private HashMap<ChickenStatus, HashMap<Integer, Integer>> chickenStatus = new HashMap<>();
    private HashMap<Equipments, HashMap<Integer, Integer>> chickenEquipments = new HashMap<>();

    public void setChickenName(String chickenName) {
        this.chickenName = chickenName;
    }

    public String getChickenName() {
        return chickenName;
    }
}
