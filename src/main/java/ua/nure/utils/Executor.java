package ua.nure.utils;

import ua.nure.entities.Teams;
import ua.nure.parsers.TeamsMarshaller;
import ua.nure.parsers.TeamsUnmarshaller;
import ua.nure.parsers.dom.DOMMarshaller;
import ua.nure.parsers.dom.DOMUnmarshaller;
import ua.nure.parsers.jaxb.JAXBMarshaller;
import ua.nure.parsers.jaxb.JAXBUnmarshaller;
import ua.nure.parsers.sax.SAXUnmarshaller;
import ua.nure.transformers.XSLTransformer;

import java.io.File;

public class Executor {

    private static final String DOM = "DOM Parser";
    private static final String JAXB = "JAXB Parser";
    private static final String SAX = "SAX Parser";
    private static final String LINE = "-------------------------";

    private static final String XML = "src/main/resources/xml/teams.xml";
    private static final String XSL = "src/main/resources/xsl/players.xsl";

    private static final String OUTPUT_JAXB = "src/main/resources/xml/jaxb.xml";
    private static final String OUTPUT_DOM = "src/main/resources/xml/dom.xml";
    private static final String HTML = "src/main/resources/html/result.html";

    public static void main(String[] args) {
        Executor ex = new Executor();
        ex.htmlGeneration();
    }

    public void htmlGeneration() {
        XSLTransformer transformer = new XSLTransformer();
        transformer.parse(XML, XSL, HTML);
    }

    public void parsing() {
        System.out.println(DOM);
        System.out.println(LINE);

        TeamsUnmarshaller unmarshaller = new DOMUnmarshaller();
        Teams teams = unmarshaller.unmarshal(new File(XML));
        System.out.println(teams);

        TeamsMarshaller marshaller = new DOMMarshaller();
        marshaller.marshal(teams, new File(OUTPUT_DOM));

        System.out.println(JAXB);
        System.out.println(LINE);

        TeamsUnmarshaller jaxbUnmarshaller = new JAXBUnmarshaller();
        teams = jaxbUnmarshaller.unmarshal(new File(XML));
        System.out.println(teams);

        TeamsMarshaller jaxbMarshaller = new JAXBMarshaller();
        EntityCreator creator = new EntityCreator();
        teams = creator.createTeams();
        jaxbMarshaller.marshal(teams, new File(OUTPUT_JAXB));

        System.out.println(SAX);
        System.out.println(LINE);

        TeamsUnmarshaller saxUnmarshaller = new SAXUnmarshaller();
        teams = saxUnmarshaller.unmarshal(new File(XML));
        System.out.println(teams);
    }

}
