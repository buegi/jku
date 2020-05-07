package swe2.ss19.pointsChecker;

import java.util.stream.DoubleStream;

public class PointsChecker {

	private static final int MIN_POSITIVE = 9;
	private static final int MIN_POINTS_POSITIVE = 8;
	private static final int MIN_POINTS = 12;
	private static final int MAX_POINTS = 24;
	private static final int MAX_POINTS_TEST = 42;
	private static final int N_EXERCISES = 11;

	public static void main(final String[] args) {
		final double[] points = { 21, 22, 23, 18, 24, 22, 22.50, 21, 18.50, 20.50, 0 };
		final double[] test = { 8.50, 13.00 };

		if (positive(points) < MIN_POSITIVE) {
			System.err.println("Durchgefallen wegen zu weniger Abgaben");
			return;
		}

		if (average(points) < MIN_POINTS) {
			System.err.println("Durchgefallen wegen zu weniger Punkte");
			return;
		}

		if (averageTest(test) < MAX_POINTS_TEST / 4.0) {
			System.err.println("Durchgefallen wegen zu schlechter Tests");
			return;
		}

		System.out.printf("Final Grade: %.2f", finalGrade(points, test));
	}

	private static long positive(final double... points) {
		return DoubleStream.of(points).filter(PointsChecker::isPositive).count();
	}

	private static boolean isPositive(final double points) {
		return points >= MIN_POINTS_POSITIVE;
	}

	private static double average(final double... points) {
		return DoubleStream.of(points).sorted().skip(N_EXERCISES - MIN_POSITIVE).average().orElse(0);
	}

	private static double averageTest(final double... test) {
		return DoubleStream.of(test).average().orElse(0);
	}

	private static double finalGrade(final double[] points, final double[] test) {
		final double exercises = average(points) * (positive(points) / (double) N_EXERCISES);
		final double t = DoubleStream.of(test).sum();
		return (exercises / MAX_POINTS + t / MAX_POINTS_TEST) * 50;
	}
}