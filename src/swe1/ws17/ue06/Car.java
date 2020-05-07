package swe1.ws17.ue06;

import java.util.Random;


public class Car {

    private double weight = 0.0;
    private int maxSpeed = 0;
    private int distance = 0;
    private Person owner = null;

    // +getMaxSpeed(): int
    public int getMaxSpeed() {
        return maxSpeed;
    }

    // +setMaxSpeed(int NewMaxSpeed): void
    public void setMaxSpeed(int newMaxSpeed) {
        maxSpeed = newMaxSpeed;
    }

    // +getweight(): int
    public double getWeight() {
        return weight;
    }

    // +setWeight(): double
    public void setWeight(double newWeight) {
        weight = newWeight;
    }

    // +getDistance(): int
    public int getDistance() {
        return distance;
    }

    // +setDistance(int newdistance): void
    public void setDistance(int newDistance) {
        distance = newDistance;
    }

    // +getOwner(): Person
    public Person getOwner() {
        return owner;
    }

    // +setOwner(Person newOwner): void
    public void setOwner(Person newOwner) {
        owner = newOwner;
    }

    // +getCarInformation: String
    public String getCarInformation() {
        return "Car with owner " + owner.getName() + " has driven " + distance + " km";
    }

    // +driveDistance(): void
    public void driveDistance() {
        Random rndGen = new Random(System.nanoTime());
        distance += rndGen.nextInt(maxSpeed) + 1;
    }
}