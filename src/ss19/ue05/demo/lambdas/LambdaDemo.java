package ss19.ue05.demo.lambdas;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import javax.swing.JButton;

import inout.In;
import inout.Out;

public class LambdaDemo {

	// 3) --- Own functional interface
	@FunctionalInterface
	interface Transformer<A, B> {
		B transform(A a);

		default B checkNullAndTransform(A a) {
			if (a == null)
				return null;
			else
				return transform(a);
		}

	}

	public static void main(String[] args) {

		// 1) ---- Implement SAM interface (single abstract method interface /
		// functional interface) as lambda -------

		Runnable r = () -> {
			Out.println("Runnable executed");
		};

		Thread thread = new Thread(r);
		thread.run();

		// Iterator<String> it = () -> { } // not allowed since Iterator is not a SAM
		// interface

		// 2) ---- Implement Comperator<String> in different ways of different
		// complexity ----
		List<String> names = new ArrayList<String>();
		names.add("Hans");
		names.add("Susan");
		names.add("Ann");

		Comparator<String> stringComp = (s1, s2) -> s1.compareToIgnoreCase(s2);
		Collections.sort(names, stringComp);
		for (String n : names) {
			Out.println(n);
		}

		Collections.sort(names, (s1, s2) -> s1.compareToIgnoreCase(s2));

		// Alternative Schreibweisen f�r den gleichen Comperator:
		stringComp = new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				return s1.compareTo(s2);
			}
		};

		stringComp = (String s1, String s2) -> {
			return s1.compareToIgnoreCase(s2);
		};

		stringComp = (s1, s2) -> {
			return s1.compareToIgnoreCase(s2);
		};

		stringComp = (s1, s2) -> s1.compareToIgnoreCase(s2);

		stringComp = String::compareToIgnoreCase; // wird im Hintergrund zu: (target, param) ->
													// target.compareToIgnoreCase(param)

		// 3) ---- functional interfaces -------------------------------

		Transformer<String, String> trimmer = s -> {
			return s.trim();
		};

		String str = "  word  ";
		String trimmed = trimmer.transform(str);
		trimmed = trimmer.checkNullAndTransform(str);
		Out.println(trimmed);

		str = null;
		trimmed = trimmer.checkNullAndTransform(str);
		Out.println(trimmed);

		// 4) ---- functional interfaces from java.util.function -------

		Function<String, Integer> length = s -> {
			if (s == null)
				return 0;
			else
				return s.length();
		};
		System.out.println(length.apply("Wie lang ist dieser String?"));

		Predicate<String> notEmpty = s -> s.length() != 0;
		String notEmptyString = "I think, thus I am";
		System.out.println("Is the String >" + notEmptyString + "< not empty? --- " + notEmpty.test(notEmptyString));

		Consumer<String> writer = s -> {
			Out.println(s);
		};
		writer.accept("Please write me");

		Supplier<String> reader = () -> {
			System.out.println("Write something:");
			return In.readLine();
		};
		String nextLine = reader.get();
		System.out.println("You have written >" + nextLine + "<");

		// 5) ---- using the higher order function ---------------------

		doGetTestMapUse(() -> {
			Out.println("Input string: ");
			return In.readLine();
		}, s -> s.length() != 0, s -> s.length(), i -> Out.println("Length = " + i));

		// 6) ---- Show that functional interfaces can be implemented just like other
		// interfaces in anonymous class ------------

		Function<Integer, Integer> summer = new Function<Integer, Integer>() {
			private int sum = 0;

			@Override
			public Integer apply(Integer i) {
				sum += i;
				return sum;
			}
		};

		for (int i = 0; i < 3; i++) {
			doGetTestMapUse(() -> {
				Out.println("Input number: ");
				return In.readInt();
			}, x -> x != 0, summer, summed -> System.out.println("Sum = " + summed));
		}

		// 7) ---- Show why this is not working

		List<Integer> list = Arrays.asList(1, 2, 3);
		// this does not work
		int sum = 0;
		// list.forEach(x -> {sum = sum + 1;});
		Out.println(sum);

		// workaround durch heap Objekt (BoxedInt or Array)
		class BoxedInt {
			int x;
		}
		BoxedInt bSum = new BoxedInt();
		int[] aSum = new int[1];

		list.forEach(x -> {
			bSum.x = bSum.x + x;
			aSum[0] = aSum[0] + x;
		});
	}

	// 4) ---- writing a higher order function -------------------------

	static public <A, B> void doGetTestMapUse(Supplier<A> getter, Predicate<A> condition, Function<A, B> mapper,
			Consumer<B> user) {
		A a = getter.get();
		if (condition.test(a)) {
			B b = mapper.apply(a);
			user.accept(b);
		}
	}
}
