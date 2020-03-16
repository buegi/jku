package ue02;

public class Cargo {

    private String description;
    private CargoType type;
    private int weight;

    private enum CargoType {
        SOLID, LIQUID
    }

    void setDescription(String description) {
        this.description = description;
    }

    String getDescription() {
        return this.description;
    }
    void setCargoType(CargoType type) {
        this.type = type;
    }
    CargoType getCargotype() {
        return this.type;
    }

}