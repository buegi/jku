package swe1.ws17.ue07;

public class EstateListTest {
	public static void main(String args[]) {

		// TEST: create new estate list
		EstateList property = new EstateList();

		// set variables for selective Printing
		String streetname = "Pummererstrasse";
		int minsize = 800;
		int maxsize = 1000;

		// TEST: "buy" properties
		property.buyEstate("Altenbergerstrasse", 10, 900);
		property.buyEstate("Altenbergerstrasse", 11, 950);
		property.buyEstate("Freistaedterstrasse", 2, 500);
		property.buyEstate("Pummererstrasse", 1, 400);
		property.buyEstate("Summererstrasse", 4, 600);

		property.printEstateList();

		// TEST: selective Output of Estates
		property.printSelectiveEstateList(streetname, minsize, maxsize);

		// TEST: "sell" property
		property.sellEstate("Freistaedterstrasse", 2);

		// TEST: output normal estate list after selling and selective output
		property.printEstateList();

		// TEST: ordered insert into estate list
		property.orderedbuyEstate("Ordered Street", 8, 1000);

		// TEST: print estates after ordered insert
		property.printEstateList();

	}
}