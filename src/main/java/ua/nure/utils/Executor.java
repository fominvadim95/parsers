package ua.nure.utils;

import ua.nure.entities.Teams;
import ua.nure.parsers.TeamsMarshaller;
import ua.nure.parsers.TeamsUnmarshaller;
import ua.nure.parsers.dom.DomUnmarshaller;
import ua.nure.parsers.jaxb.JAXBMarshaller;
import ua.nure.parsers.jaxb.JAXBUnmarshaller;

import java.io.File;

public class Executor {

    private static final String INPUT_FILE = "src/main/resources/teams.xml";
    private static final String OUTPUT_FILE = "src/main/resources/jaxb.xml";

    public static void main(String[] args) {

        TeamsUnmarshaller domUnmarshaller = new DomUnmarshaller();
        System.out.print(domUnmarshaller.unmarshal(new File(INPUT_FILE)));

        System.out.println("JAXB Parser");

        TeamsUnmarshaller jaxbUnmarshaller = new JAXBUnmarshaller();
        Teams teams = jaxbUnmarshaller.unmarshal(new File(INPUT_FILE));
        System.out.println(teams.getTeam());

        TeamsMarshaller jaxbMarshaller = new JAXBMarshaller();
        teams = domUnmarshaller.unmarshal(new File(INPUT_FILE));
        jaxbMarshaller.marshal(teams, new File(OUTPUT_FILE));
    }

}
