package ua.nure.utils;


import ua.nure.parsers.Unmarshaller;
import ua.nure.parsers.dom.DomUnmarshaller;

import java.io.File;

public class Executor {

    private static final String INPUT_FILE = "src/main/resources/teams.xml";

    public static void main(String[] args) {

        System.out.println("DOM Parser");
        Unmarshaller unmarshaller = new DomUnmarshaller();
        System.out.println(unmarshaller.unmarshall(new File(INPUT_FILE)));

    }

}
