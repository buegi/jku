package ss19.ue05.app;

import inout.In;
import inout.Out;
import ss19.ue05.list.*;

public class CasinoApp {
    public static void main(String[] args) {
        // TODO
        List<GameResult> results = new LinkedList<>();
        In.open("casino.csv");
        while (In.available() > 0) {
            String csvLine = In.readLine();
            String[] csvLineSplitted = csvLine.split(";");
            // The game name is stored in csvLineSplitted[0], the loss/win is stored in
            // csvLineSplitted[1]
            // TODO: Add game result to list "results"
            results.add(new GameResult(csvLineSplitted[0], Double.parseDouble(csvLineSplitted[1])));
        }

        List<String> gameNames = results.map(s -> s.getName()).distinct();

        for (String gameName : gameNames) {

            int losses = results.filter(a -> a.getName().equals(gameName)).map(b -> b.getGewinn()).filter(c -> c < 0)
                    .reduce(0, (a, b) -> a + 1).intValue();
            // counting 0 as win because the result values of stefan (tutor) in discord
            // implied to do so, would have left out 0 otherwise, since its neither a win
            // nor a loss
            int wins = results.filter(a -> a.getName().equals(gameName)).map(b -> b.getGewinn()).filter(c -> c >= 0)
                    .reduce(0, (a, b) -> a + 1).intValue();
            double maxLoss = results.filter(a -> a.getName().equals(gameName)).map(b -> b.getGewinn()).reduce(0.0,
                    Math::min);
            double maxWin = results.filter(a -> a.getName().equals(gameName)).map(b -> b.getGewinn()).reduce(0.0,
                    Math::max);
            double sum = results.filter(a -> a.getName().equals(gameName)).map(b -> b.getGewinn()).reduce(0.0,
                    (a, b) -> a + b);
            Out.println(gameName);
            Out.println(String.format("  Anzahl Verluste: %d", losses));
            Out.println(String.format("  Anzahl Gewinne: %d", wins));
            Out.println(String.format("  Maximaler Verlust: %,.2f", maxLoss));
            Out.println(String.format("  Maximaler Gewinn: %,.2f", maxWin));
            Out.println(String.format("  Summe Verlust / Gewinn: %,.2f", sum));
        }
    }
}