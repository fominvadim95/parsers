package ua.nure.parsers;

import ua.nure.entities.Teams;

import java.io.File;

public interface TeamsMarshaller {

    void marshal(Teams teams, File file);

}
