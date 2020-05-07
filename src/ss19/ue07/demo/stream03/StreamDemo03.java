package ss19.ue07.demo.stream03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import inout.Out;

public class StreamDemo03 {
	
	public static void main(String[] args) {
		
		// ==== generating streams ============================================

		// from collections

		List<String> list = new ArrayList<String>();
		list.add("Ann");
		list.add("Mike");
		list.add("Pat");
		list.add("Mary");
		list.add("John");
		list.add("Joe");

		Stream<String> names = list.stream();

		// count 
		
		Out.println("\ncount: count all distinct sports of all persons");
		names = list.stream(); 
		long nSports = 
				names
					.flatMap(name -> sports.containsKey(name) ? 
										sports.get(name).stream() : 
										Stream.empty())
					.distinct()
					.count();
		Out.println("nSports = " + nSports);
		
		// sum
		
		Out.println("\nsum: sum of 100 rands");
		final Random r = new Random();
		IntStream randStream = IntStream.generate(() -> r.nextInt(100));
		int sum = 
				randStream
					.limit(100)
					.sum(); 
		Out.println("Sum of 100 rands = " + sum); 
		
		// find
		
		Out.println("\nfind: find first prime");
		randStream = IntStream.generate(() -> r.nextInt(100));
		OptionalInt optPrime = 
				randStream
					.filter(x -> isPrime(x))
					.findFirst(); 
		Out.println("first prime = " + optPrime.orElse(-1));
		
		// max, min
		
		Out.println("\nmax: find maximum");
		randStream = IntStream.generate(() -> r.nextInt(100));
		OptionalInt optMax = 
				randStream
					.limit(100)
					.max(); 
		Out.println("max = " + optMax.orElse(-1));

		// reduce 

		Out.println("\nreduce: sum of 100 rands");
		randStream = IntStream.generate(() -> r.nextInt(100));
		sum =  randStream
					.limit(100)
					.reduce(0, (pSum, x) -> pSum + x); 
		Out.println("Sum of 100 rands = " + sum); 
		
		Out.println("\nreduce: product of 1 / x_2");
		double product = 
				IntStream.range(1, 10)
					.mapToDouble(x -> x)
					.reduce(1.0, (pp, x) -> pp * (1 / (x * x))); 
		Out.println("Product of 1 / x_2 = " + product); 
		
		// collect 
		
		Out.println("\nmap: List of first characters of names:");
		names = list.stream(); 
		List<Character> initials = 
				names
					.map(n -> n.length() != 0 ? n.charAt(0) : ' ')
					.collect(() -> new ArrayList<Character>(),
							(l, c) -> l.add(c), 
							(l1, l2) -> l1.addAll(l2));
		initials.forEach(a -> Out.println(a));

		// collect with Collector
		
		Out.println("\nmap: List with Collector:");
		names = list.stream(); 
		initials = 
				names
					.map(n -> n.length() != 0 ? n.charAt(0) : ' ')
					.collect(Collectors.toList());
		initials.forEach(a -> Out.println(a));
		
		// grouping 
		
		Out.println("\ngrouping: Group by initial character ");
		names = list.stream(); 
		Map<Character, List<String>> nameMap = 
				names.collect(Collectors.groupingBy(name -> Character.toLowerCase(name.charAt(0)))); 
		for (char c : nameMap.keySet()) {
			Out.print(c + ": ");
			for (String name: nameMap.get(c)) {
				Out.print(name + " ");
			}
			Out.println(); 
		}
		
		// joining 
		
		Out.println("\njoining: Group by initial character ");
		names = list.stream(); 	
		String namesJoined = 
			names
				.sorted()
				.collect(Collectors.joining(", ")); 
		Out.println("Names joined = " + namesJoined);
		
		// averaging
		
		names = list.stream(); 
		double avrg1 = names.mapToInt(name -> name.length()).average().orElse(Double.NaN); 
		Out.println("Average length = " + avrg1);
		
		names = list.stream(); 
		double avrg2 = names.collect(Collectors.averagingDouble(name -> name.length())); 
		Out.println("Average length = " + avrg2);
		
		// anyMatch 
		
		names = list.stream(); 
		boolean allLonger2 = names.anyMatch(name -> name.length() > 2); 
		Out.println("All names longer 2 = " + allLonger2);
		
		names = list.stream(); 
		boolean noneStartsWithX = names.noneMatch(name -> name.charAt(0) == 'X'); 
		Out.println("No names start with X = " + noneStartsWithX);
		
		
	}
	
	static enum Sport {
		TENNIS, GOLF, SKIING, SOCCER, SWIMMING
	}
	
	private static Map<String, List<Sport>> sports = new HashMap<String, List<Sport>>(); 
	
	static {
		sports.put("Ann", Arrays.asList(Sport.TENNIS, Sport.SKIING));
		sports.put("Pat", Arrays.asList(Sport.SOCCER, Sport.GOLF));
		sports.put("Mary", Arrays.asList(Sport.SOCCER, Sport.TENNIS));
		sports.put("Joe", Arrays.asList(Sport.SKIING, Sport.GOLF));
		sports.put("John", Arrays.asList(Sport.GOLF));
	}
	
	private static boolean isPrime(int n) {
		for (int i = 2; i < n / 2; i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

}
