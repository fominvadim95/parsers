package ua.nure.utils;


import ua.nure.entities.Teams;
import ua.nure.parsers.Marshaller;
import ua.nure.parsers.Unmarshaller;
import ua.nure.parsers.dom.DomMarshaller;
import ua.nure.parsers.dom.DomUnmarshaller;

import java.io.File;

public class Executor {

    private static final String INPUT_FILE = "src/main/resources/teams.xml";

    private static final String OUTPUT_FILE = "src/main/resources/output/dom.xml";

    public static void main(String[] args) {

        System.out.println("DOM Parser");
        Unmarshaller unmarshaller = new DomUnmarshaller();
        Marshaller marshaller = new DomMarshaller();
        Teams teams = unmarshaller.unmarshall(new File(INPUT_FILE));
        System.out.println(teams);
        marshaller.marshall(teams,new File(OUTPUT_FILE));


    }

}
