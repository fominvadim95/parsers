package ua.nure.parsers;


import ua.nure.entities.Teams;

import java.io.File;

public interface Unmarshaller {

    Teams unmarshall(File file);
}
