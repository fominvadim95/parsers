package ua.nure.parsers;

import ua.nure.entities.Teams;

import java.io.File;

public interface TeamsUnmarshaller {

    Teams unmarshal(File file);

}
