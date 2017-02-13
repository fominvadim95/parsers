package ua.nure.parsers;

import ua.nure.entities.Teams;

import java.io.File;

public interface Marshaller {

    void marshall(Teams teams, File file);
}
