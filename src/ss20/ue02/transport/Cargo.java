package ss20.ue02.transport;

public class Cargo {

    private String description;
    private CargoType type;
    private int weight;

    public Cargo(CargoType type, String description, int weight) {
        this.description = description;
        this.type = type;
        this.weight = weight;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setType(CargoType type) {
        this.type = type;
    }

    public CargoType getType() {
        return this.type;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return this.weight;
    }

    @Override
    public String toString() {
        return this.getDescription() + ", Type:" + this.getType() + ", Weight:" + this.getWeight();
    }
}