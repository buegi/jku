package ss20.ue02.test;

import inout.Out;
import ss20.ue02.transport.*;
import ss20.ue02.exceptions.*;

import static ss20.ue02.transport.Country.*;

public class TransportTest {

    public static void main(String[] args) {

        Location linz = new Location("Linz", 0, 0, Austria);
        Location paris = new Location("Paris", 300, 400, France);
        Location la = new Location("LA", 12000, 1000, USA);
        Location london = new Location("London", 2000, -100, GreatBritain);
        Location ny = new Location("NY", 8000, 0, USA);

        Transporter plane = new CargoPlane("plane", 1000, 20000, london);
        Transporter tankTruck = new TankTruck("tank", 80, 10000, linz);
        Transporter containerTruck = new ContainerTruck("truck", 600, 8000, linz);

        Cargo solid_15 = new Cargo(CargoType.SOLID, "solid_15", 15000);
        Cargo solid_5 = new Cargo(CargoType.SOLID, "solid_5", 5000);
        Cargo liquid_15 = new Cargo(CargoType.LIQUID, "liquid_15", 15000);
        Cargo liquid_5 = new Cargo(CargoType.LIQUID, "liquid_5", 5000);

        double cost = 0;

        // plane

        try {
            cost += plane.goTo(paris);
            Out.println("Plane flight to Paris ok: " + plane.toString());

            plane.load(solid_15);
            Out.println("Loaded solid_15 on plane ok: " + plane.toString());

            cost += plane.goTo(ny);
            Out.println("Plane flight to NY ok: " + plane.toString());

            plane.unload();
            Out.println("Plane unload ok: " + plane.toString());
        } catch (TransportException e) {
            Out.println("++ERROR++: Unexpected exception: " + e.toString());
        }

        Out.println("Cost for plane is: " + cost);
        Out.println();

        try {
            plane.load(liquid_15);
            Out.println("++ERROR++: InvalidCargoException expected: " + plane);
        } catch (InvalidCargoException e) {
            Out.println("Expected exception is: " + e.toString());
        } catch (TransportException e) {
            Out.println("++ERROR++: Unexpected exception: " + e.toString());
        }

        // Tank truck

        Out.println();

        cost = 0;
        try {

            cost += tankTruck.goTo(paris);
            Out.println("Tank truck go to Paris ok: " + tankTruck.toString());

            tankTruck.load(liquid_5);
            Out.println("Tank truck loading liquid 5000 ok: " + tankTruck.toString());

            cost += tankTruck.goTo(linz);
            Out.println("Tank truck go to Linz ok: " + tankTruck.toString());

            tankTruck.unload();
            Out.println("Tank truck unloading ok: " + tankTruck.toString());

        } catch (TransportException e) {
            Out.println("++ERROR++: Unexpected exception: " + e.toString());
        }

        Out.println("Cost for tank truck is: " + cost);
        Out.println();

        try {
            tankTruck.load(solid_5);
            Out.println("++ERROR++: InvalidCargoException expected: " + tankTruck);
        } catch (InvalidCargoException e) {
            Out.println("Expected exception is: " + e.toString());
        } catch (TransportException e) {
            Out.println("++ERROR++: Unexpected exception: " + e.toString());
        }

        try {
            tankTruck.load(liquid_15);
            Out.println("++ERROR++: OverloadedException expected: " + tankTruck);
        } catch (OverloadedException e) {
            Out.println("Expected exception is: " + e.toString());
        } catch (TransportException e) {
            Out.println("++ERROR++: Unexpected exception: " + e.toString());
        }

        // Container truck

        Out.println();
        cost = 0;
        try {

            cost += containerTruck.goTo(paris);
            Out.println("Container truck go to Paris ok: " + containerTruck.toString());

            containerTruck.load(solid_5);
            Out.println("Container truck loading solid 5000 ok: " + containerTruck.toString());

            cost += containerTruck.goTo(linz);
            Out.println("Container truck go to Linz ok: " + containerTruck.toString());

            containerTruck.unload();
            Out.println("Container truck unloading ok: " + containerTruck.toString());

        } catch (TransportException e) {
            Out.println("++ERROR++: Unexpected exception: " + e.toString());
        }

        Out.println("Cost for container truck is: " + cost);
        Out.println();

        try {
            containerTruck.load(liquid_5);
            Out.println("++ERROR++: InvalidCargoException expected: " + containerTruck);
        } catch (InvalidCargoException e) {
            Out.println("Expected exception is: " + e.toString());
        } catch (TransportException e) {
            Out.println("++ERROR++: Unexpected exception: " + e.toString());
        }

        try {
            containerTruck.load(solid_15);
            Out.println("++ERROR++: OverloadedException expected: " + containerTruck);
        } catch (OverloadedException e) {
            Out.println("Expected exception is: " + e.toString());
        } catch (TransportException e) {
            Out.println("++ERROR++: Unexpected exception: " + e.toString());
        }

        // added test for unreachable
        Out.println();
        try {
            containerTruck.goTo(ny);
            Out.println("++ERROR++: Cannot go from: " + containerTruck.getActualLocation() + " to " + ny + "on land");
        } catch (UnreachableLocationException e) {
            Out.println("Expected exception is: " + e.toString());
        } catch (TransportException e) {
            Out.println("++ERROR++: Unexpected exception: " + e.toString());
        }
    }
}
