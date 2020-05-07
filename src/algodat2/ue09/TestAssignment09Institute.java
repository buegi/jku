package algodat2.ue09;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestAssignment09Institute {

	private Roadmap map = null;
	private int linz = 0, stpoelten = 0, wien = 0, innsbruck = 0, bregenz = 0, eisenstadt = 0, graz = 0, klagenfurt = 0, salzburg = 0, washington = 0;
	
	private void initRoadmap() {
		map = new Roadmap();
		
		linz = map.insertVertex(new Station("Linz"));
		stpoelten = map.insertVertex(new Station("St.Poelten"));
		wien = map.insertVertex(new Station("Wien"));
		innsbruck = map.insertVertex(new Station("Innsbruck"));
		bregenz = map.insertVertex(new Station("Bregenz"));
		eisenstadt = map.insertVertex(new Station("Eisenstadt"));
		graz = map.insertVertex(new Station("Graz"));
		klagenfurt = map.insertVertex(new Station("Klagenfurt"));
		salzburg = map.insertVertex(new Station("Salzburg"));
		washington = map.insertVertex(new Station("Washington"));
		
		map.insertEdge(linz, stpoelten, 140);
		map.insertEdge(stpoelten, wien, 80);
		map.insertEdge(linz, wien, 200);
		map.insertEdge(wien, eisenstadt, 100);
		map.insertEdge(wien, graz, 190);
		map.insertEdge(graz, klagenfurt, 160);
		map.insertEdge(klagenfurt, salzburg, 210);
		map.insertEdge(linz, salzburg, 150);
		map.insertEdge(salzburg, innsbruck, 250);
		map.insertEdge(klagenfurt, innsbruck, 300);
		map.insertEdge(bregenz, innsbruck, 200);
	}
	
	@Test
	public void TestWienBregenz() {
		initRoadmap();
		System.out.println("-----------------------------");
		System.out.println("Output solution: ");
		System.out.println("Shortest distance from Wien to Bregenz: 800");
		System.out.println("    over Linz: 200");
		System.out.println("    over Salzburg: 350");
		System.out.println("    over Innsbruck: 600");
		System.out.println("-----------------------------");
		System.out.println("Your output: ");
		assertEquals(800,map.printShortestDistance(wien,bregenz));
		System.out.println("-----------------------------");
	}
	
	@Test
	public void TestInnsbruckEisenstadt() {
		
		initRoadmap();
		System.out.println("-----------------------------");
		System.out.println("Output solution: ");
		System.out.println("Shortest distance from Innsbruck to Eisenstadt: 700");
		System.out.println("    over Salzburg: 250");
		System.out.println("    over Linz: 400");
		System.out.println("    over Wien: 600");
		System.out.println("-----------------------------");
		System.out.println("Your output: ");
		assertEquals(700,map.printShortestDistance(innsbruck,eisenstadt));
		System.out.println("-----------------------------");
	}
	
	@Test
	public void TestWienKlagenfurt() {
	
		initRoadmap();
		System.out.println("-----------------------------");
		System.out.println("Output solution: ");
		System.out.println("Shortest distance from Wien to Klagenfurt: 350");
		System.out.println("    over Graz: 190");
		System.out.println("-----------------------------");
		System.out.println("Your output: ");
		assertEquals(350,map.printShortestDistance(wien,klagenfurt));
		System.out.println("-----------------------------");
	}
	
	@Test
	public void TestAllDistances() {
		initRoadmap();
		int [] distances = map.printShortestDistances(wien);
		
		assertEquals(200, distances[linz]);
		assertEquals(80, distances[stpoelten]);
		assertEquals(0, distances[wien]);
		assertEquals(600, distances[innsbruck]);
		assertEquals(800, distances[bregenz]);
		assertEquals(100, distances[eisenstadt]);
		assertEquals(190, distances[graz]);
		assertEquals(350, distances[klagenfurt]);
		assertEquals(350, distances[salzburg]);
		assertEquals(-1, distances[washington]);
	}

	// ...
	// ...
}
