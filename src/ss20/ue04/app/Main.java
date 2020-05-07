package ss20.ue04.app;

import ss20.ue04.data.Data;
import ss20.ue04.processing.Processor;
import ss20.ue04.processing.Processors;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Data data = Data.readFromFile("src/ue04/data/data.csv");
        Processor[] processors = {
                new Processors.PercentScaler(),
                Processors.scale(-20, 123),
                Processors.standardize(),
                Processors.clipLower(-1),
                Processors.clipUpper(1.1),
                Processors.clip(-0.7, 1)
        };
        for (Processor p : processors) {
            System.out.println(String.format("processing data with '%s'", p.getName()));
            System.out.println(String.format("  before: %s", data));
            data = p.process(data);
            System.out.println(String.format("   after: %s", data));
        }
        data.writeToFile("src/ue04/data/data_processed.csv");
    }
}