package swe2.ss20.ue04.processing;

import swe2.ss20.ue04.data.Data;

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
        abstract double getMin();

        abstract double getMax();

        @Override
        public Data process(Data data) {
            double dataMin = DataUtil.min(data);
            double dataMax = DataUtil.max(data);
            double[] result = new double[data.size()];
            int counter = 0;

            for (double val : data) {
                double scaled = (val - dataMin) / (dataMax - dataMin);
                result[counter] = scaled * (this.getMax() - this.getMin()) + this.getMin();
                counter++;
            }
            return new Data(result);
        }

        public String getName() {
            return "Scaler(min=" + this.getMin() + ", max=" + this.getMax() + ")";
        }
    }


    // TODO: 2) oeffentliche, statische Methode "Processor scale(double min, double max)"
    public static Processor scale(double min, double max) {
        return new Processors.Scaler() {
            @Override
            double getMin() {
                return min;
            }

            @Override
            double getMax() {
                return max;
            }
        };
    }

    // TODO: 3) oeffentliche, statische Methode "Processor standardize()"
    public static Processor standardize() {
        return new Processor() {

            @Override
            public Data process(Data data) {
                double dataAvg = DataUtil.avg(data);
                double dataStd = DataUtil.std(data);
                double[] result = new double[data.size()];
                int counter = 0;

                for (double val : data) {
                    result[counter] = (val - dataAvg) / dataStd;
                    counter++;
                }
                return new Data(result);
            }

            @Override
            public String getName() {
                return "Standardizer";
            }
        };
    }


    // TODO: 4) oeffentliche, statische, innere Klasse "PercentScaler"
    public static class PercentScaler extends Scaler {
        private final double MIN = 0.0d;
        private final double MAX = 100.0d;

        @Override
        double getMin() {
            return this.MIN;
        }

        @Override
        double getMax() {
            return this.MAX;
        }
    }


    // TODO: 5) private, statische, innere Klasse "Clipper"
    private static class Clipper implements Processor {
        private boolean clipLower, clipUpper;
        private double lower, upper;

        Clipper(boolean clipLower, boolean clipUpper, double lower, double upper) {
            this.clipLower = clipLower;
            this.clipUpper = clipUpper;
            this.lower = lower;
            this.upper = upper;
        }

        @Override
        public Data process(Data data) {
            double[] result = new double[data.size()];

            if (clipUpper && clipLower) {
                int counter = 0;
                for (double val : data) {
                    result[counter] = (val < lower) ? lower : (val > upper) ? upper : val;
                    counter++;
                }
                return new Data(result);
            }

            if (clipUpper) {
                int counter = 0;
                for (double val : data) {
                    result[counter] = val > upper ? upper : val;
                    counter++;
                }
                return new Data(result);
            }

            if (clipLower) {
                int counter = 0;
                for (double val : data) {
                    result[counter] = val < lower ? lower : val;
                    counter++;
                }
                return new Data(result);
            }
            return data;
        }

        @Override
        public String getName() {

            if (clipLower && clipUpper) {
                return "Clipper(lower=" + lower + ", upper=" + upper + ")";
            }
            if (clipUpper) {
                return "Clipper(upper=" + upper + ")";
            }
            if (clipLower) {
                return "Clipper(lower=" + lower + ")";
            }
            return "Clipper(no clips defined)";
        }
    }

    // TODO: 6) oeffentliche, statische Methode "Processor clip(double lower, double upper)"
    public static Processor clip(double lower, double upper) {
        return new Processors.Clipper(true, true, lower, upper);
    }


    // TODO: 6) oeffentliche, statische Methode "Processor clipLower(double lower)"
    public static Processor clipLower(double lower) {
        return new Processors.Clipper(true, false, lower, 0.0d);
    }


    // TODO: 6) oeffentliche, statische Methode "Processor clipUpper(double upper)"
    public static Processor clipUpper(double upper) {
        return new Processors.Clipper(false, true, 0.0d, upper);
    }


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