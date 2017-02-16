package ua.nure.utils;

import ua.nure.entities.Teams;
import ua.nure.parsers.TeamsMarshaller;
import ua.nure.parsers.TeamsUnmarshaller;
import ua.nure.parsers.dom.DOMMarshaller;
import ua.nure.parsers.dom.DOMUnmarshaller;
import ua.nure.parsers.jaxb.JAXBMarshaller;
import ua.nure.parsers.jaxb.JAXBUnmarshaller;
import ua.nure.parsers.sax.SAXUnmarshaller;

import java.io.File;

public class Executor {

    public static final String DOM = "DOM Parser";
    public static final String JAXB = "JAXB Parser";
    public static final String SAX = "SAX Parser";
    public static final String LINE = "-------------------------";

    private static final String INPUT_FILE = "src/main/resources/teams.xml";
    private static final String OUTPUT_JAXB = "src/main/resources/jaxb.xml";
    private static final String OUTPUT_DOM = "src/main/resources/dom.xml";


    public static void main(String[] args) {

        System.out.println(DOM);
        System.out.println(LINE);

        TeamsUnmarshaller unmarshaller = new DOMUnmarshaller();
        Teams teams = unmarshaller.unmarshal(new File(INPUT_FILE));
        System.out.println(teams);

        TeamsMarshaller marshaller = new DOMMarshaller();
        marshaller.marshal(teams, new File(OUTPUT_DOM));

        System.out.println(JAXB);
        System.out.println(LINE);

        TeamsUnmarshaller jaxbUnmarshaller = new JAXBUnmarshaller();
        teams = jaxbUnmarshaller.unmarshal(new File(INPUT_FILE));
        System.out.println(teams);

        TeamsMarshaller jaxbMarshaller = new JAXBMarshaller();
        EntityCreator creator = new EntityCreator();
        teams = creator.createTeams();
        jaxbMarshaller.marshal(teams, new File(OUTPUT_JAXB));

        System.out.println(SAX);
        System.out.println(LINE);

        TeamsUnmarshaller saxUnmarshaller = new SAXUnmarshaller();
        teams = saxUnmarshaller.unmarshal(new File(INPUT_FILE));
        System.out.println(teams);
    }

}
