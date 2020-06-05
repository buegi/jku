package swe2.ss20.ue11.demo.patterns.chainofresponsibility;

import java.util.List;
import java.util.ArrayList;

public class ParserTest {

    public static void main(String[] args) {

        List<String> fileList = new ArrayList<>();
        fileList.add("someFile.txt");
        fileList.add("otherFile.json");
        fileList.add("xmlFile.xml");
        fileList.add("csvFile.csv");
        fileList.add("csvFile.doc");

        Parser xmlParser = new XMLParser();
        Parser csvParser = new CSVParser(xmlParser);
        Parser jsonParser = new JSONParser(csvParser);
        Parser textParser = new TextParser(jsonParser);

        for (String fileName : fileList) {
            textParser.parse(fileName);
        }
    }
}