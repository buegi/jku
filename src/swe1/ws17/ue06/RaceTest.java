package swe1.ws17.ue06;

public class RaceTest {

    public static void main(String[] args) {

        // create race
        Race testrace = new Race();
        testrace.setName("TESTRACE");
        // String race = testrace.getName();

        // create person, test setter of Person
        Person testperson1 = new Person();
        testperson1.setAge(18);
        testperson1.setName("Max Mustermann");

        Person testperson2 = new Person();
        testperson2.setAge(17);
        testperson2.setName("Hans Maier");

        Person testperson3 = new Person();
        testperson3.setAge(24);
        testperson3.setName("Josef Huber");

        // create cars, test setter of Car
        Car testcar1 = new Car();
        testcar1.setOwner(testperson1);
        testcar1.setWeight(1250.5);
        testcar1.setMaxSpeed(100);

        Car testcar2 = new Car();
        testcar2.setOwner(testperson2);
        testcar2.setWeight(1800.0);
        testcar2.setMaxSpeed(80);

        Car testcar3 = new Car();
        testcar3.setOwner(testperson3);
        testcar3.setWeight(1400.0);
        testcar3.setMaxSpeed(50);
        // print registration status of all testdrivers
        System.out.println("Registration OK?: " + testrace.registerCar(testcar1) + " / " + testrace.registerCar(testcar2) + " / " + testrace.registerCar(testcar3));
        // simulate race, determine winner
        Car Winner = testrace.completeRace(3);
        // Print winner and winner-driver-name
        System.out.println("Winner Car: " + Winner);
        System.out.println("Winner Driver: " + Winner.getOwner().getName());

        // get car & person information and test getter
        System.out.println("Owner: " + testcar1.getOwner() + " / " + testcar2.getOwner() + " / " + testcar3.getOwner());
        System.out.println("Weight: " + testcar1.getWeight() + " / " + testcar2.getWeight() + " / " + testcar3.getWeight());
        System.out.println("Name: " + testperson1.getName() + " / " + testperson2.getName() + " / " + testperson3.getName());
        System.out.println("Age: " + testperson1.getAge() + " / " + testperson2.getAge() + " / " + testperson3.getAge());
        System.out.println("Distance: " + testcar1.getDistance() + " / " + testcar2.getDistance() + " / " + testcar3.getDistance());
        System.out.println("MaxSpeed: " + testcar1.getMaxSpeed() + " / " + testcar2.getMaxSpeed() + " / " + testcar3.getMaxSpeed());

        // check & print if registration was successful
        System.out.println(testcar1.getCarInformation() + " / " + testcar2.getCarInformation() + " / " + testcar3.getCarInformation());
        System.out.println(testrace.getRaceInformation());

        // Reset Race
        testrace.resetRace();
        System.out.println("Distance after reset: " + testcar1.getDistance() + " / " + testcar2.getDistance() + " / " + testcar3.getDistance());
        System.out.println();
    }
}