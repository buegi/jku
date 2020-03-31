package ue04.processing;

import ue04.data.Data;

/**
 * This static-only class provides several factory methods and classes for creating {@link
 * Processor} objects.
 */
public class Processors {

    // static only class that should not be instantiated; hide constructor
    private Processors() {
    }

    // TODO: 1) oeffentliche, abstrakte, statische, innere Klasse "Scaler"
    public static abstract class Scaler implements Processor {

        private double min, max;


        abstract double getMin();

        abstract double getMax();

        @Override
        Data process(Data data) {
            // TODO


            double scaled = (val - dataMin) / (dataMax - dataMin);
            double newVal = scaled * (this.getMax() - this.getMin()) + this.getMin();


            return null;
        }

        @Override
        public String getName() {
            return "[" + this.getMin() + ", " + this.getMax() + "]";
        }
    }

    // TODO: 2) oeffentliche, statische Methode "Processor scale(double min, double max)"
    Processor scale(double min, double max) {
        // TODO
        return null;
    }


    // TODO: 3) oeffentliche, statische Methode "Processor standardize()"
    public static Processor standardize() {
        // TODO
        return null;
    }


    // TODO: 4) oeffentliche, statische, innere Klasse "PercentScaler"
    public static class PercentScaler() extends Scaler {

        final double MIN = 0.0;
        final double MAX = 100.0;

        @Override
        double getMin() {
            return MIN;
        }

        @Override
        double getMax() {
            return MAX;
        }


    }



    // TODO: 5) private, statische, innere Klasse "Clipper"

    // TODO: 6) oeffentliche, statische Methode "Processor clip(double lower, double upper)"

    // TODO: 6) oeffentliche, statische Methode "Processor clipLower(double lower)"

    // TODO: 6) oeffentliche, statische Methode "Processor clipUpper(double upper)"


    // static helper class for statistical measures of Data objects
    private static class DataUtil {

        /**
         * Returns the minimum of the specified <code>Data</code> object.
         */
        public static double min(Data data) {
            double min = Double.POSITIVE_INFINITY;
            for (double d : data) {
                if (d < min) {
                    min = d;
                }
            }
            return min;
        }

        /**
         * Returns the maximum of the specified <code>Data</code> object.
         */
        public static double max(Data data) {
            double max = Double.NEGATIVE_INFINITY;
            for (double d : data) {
                if (d > max) {
                    max = d;
                }
            }
            return max;
        }

        /**
         * Returns the average (mean) of the specified <code>Data</code> object.
         */
        public static double avg(Data data) {
            double sum = 0;
            for (double d : data) {
                sum += d;
            }
            return sum / data.size();
        }

        /**
         * Returns the standard deviation of the specified <code>Data</code> object.
         */
        public static double std(Data data) {
            double avg = avg(data);
            double sum = 0;
            for (double d : data) {
                double deviation = d - avg;
                sum += deviation * deviation;
            }
            return Math.sqrt(sum / data.size());
        }

    }

}
