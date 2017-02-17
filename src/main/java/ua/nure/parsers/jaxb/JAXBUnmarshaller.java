package ua.nure.parsers.jaxb;

import ua.nure.entities.ObjectFactory;
import ua.nure.entities.Teams;
import ua.nure.parsers.TeamsUnmarshaller;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class JAXBUnmarshaller implements TeamsUnmarshaller {

    @Override
    public Teams unmarshal(File file) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (Teams) jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

}