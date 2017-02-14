package ua.nure.utils;

import ua.nure.entities.Teams;
import ua.nure.parsers.TeamsMarshaller;
import ua.nure.parsers.TeamsUnmarshaller;
import ua.nure.parsers.dom.DomUnmarshaller;
import ua.nure.parsers.jaxb.JAXBMarshaller;
import ua.nure.parsers.jaxb.JAXBUnmarshaller;

import java.io.File;

public class Executor {

    public static final String DOM = "DOM Parser";
    public static final String JAXB = "JAXB Parser";
    public static final String SAX = "SAX Parser";
    public static final String LINE = "-------------------------";

    private static final String INPUT_FILE = "src/main/resources/teams.xml";
    private static final String OUTPUT_JAXB = "src/main/resources/jaxb.xml";

    public static void main(String[] args) {

        System.out.println(DOM);
        System.out.println(LINE);

        TeamsUnmarshaller unmarshaller = new DomUnmarshaller();
        Teams teams = unmarshaller.unmarshal(new File(INPUT_FILE));
        System.out.println(teams);

        System.out.println(JAXB);
        System.out.println(LINE);

        TeamsUnmarshaller jaxbUnmarshaller = new JAXBUnmarshaller();
        teams = jaxbUnmarshaller.unmarshal(new File(INPUT_FILE));
        System.out.println(teams);

        TeamsMarshaller jaxbMarshaller = new JAXBMarshaller();
        EntityCreator creator = new EntityCreator();
        teams = creator.createTeams();
        jaxbMarshaller.marshal(teams, new File(OUTPUT_JAXB));

//        System.out.println(SAX);
//        System.out.println(LINE);
//        Uliana's code
    }

}
