package ua.nure.parsers.dom;


import ua.nure.entities.Teams;
import ua.nure.parsers.Unmarshaller;

import java.io.File;

public class DomUnmarshaller implements Unmarshaller {

    @Override
    public Teams unmarshall(File file) {
        return new Teams();
    }
}
