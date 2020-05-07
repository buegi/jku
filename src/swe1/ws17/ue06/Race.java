package swe1.ws17.ue06;

public class Race {

    private String name = null;
    private Car[] cars = new Car[10];
    private int registeredCars = 0;

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
    }

    // registerCar
    public boolean registerCar(Car car) {
        if (car.getWeight() < 1500 && car.getOwner().getAge() >= 18 && registeredCars < 10) {
            cars[registeredCars] = car;
            registeredCars += 1;
            return true;
        } else
            return false;
    }

    // completeRace
    public Car completeRace(int rounds) {
        Car winner = null;
        int distance = 0;
        for (int i = 0; i < rounds; i++) {
            for (int j = 0; j < registeredCars; j++) {
                cars[j].driveDistance();
                if (cars[j].getDistance() >= distance) {
                    distance = cars[j].getDistance();
                    winner = cars[j];
                }
            }
        }

        return winner;
    }

    // resetRace
    public void resetRace() {
        for (int i = 0; i < registeredCars; i++) {
            cars[i].setDistance(0);
        }
    }

    // getRaceInformation
    public String getRaceInformation() {
        return "Race " + name + " with " + registeredCars + " registered cars.";
    }
}