package ua.nure.parsers.jaxb;

import ua.nure.entities.ObjectFactory;
import ua.nure.entities.Teams;
import ua.nure.parsers.TeamsMarshaller;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class JAXBMarshaller implements TeamsMarshaller {

    @Override
    public void marshal(Teams teams, File file) {
        try (OutputStream os = new FileOutputStream(file)) {
            JAXBContext jc = JAXBContext.newInstance(ObjectFactory.class);
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION,
                    "../xsd/teams.xsd");
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(teams, os);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
