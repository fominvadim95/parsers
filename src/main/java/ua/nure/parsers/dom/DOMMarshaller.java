package ua.nure.parsers.dom;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ua.nure.entities.*;
import ua.nure.parsers.TeamsMarshaller;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static ua.nure.utils.XMLTag.*;

public class DOMMarshaller implements TeamsMarshaller {

    private static final String NAMESPACE = "xmlns:xsi";

    private static final String NAMESPACE_VALUE = "http://www.w3.org/2001/XMLSchema-instance";

    private static final String SCHEMA_LOCATION = "xsi:noNamespaceSchemaLocation";

    private static final String SCHEMA_LOCATION_VALUE = "xsd/teams.xsd";

    @Override
    public void marshal(Teams teams, File file) {

        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element root = document.createElement(TEAMS);
            root.setAttribute(NAMESPACE, NAMESPACE_VALUE);
            root.setAttribute(SCHEMA_LOCATION, SCHEMA_LOCATION_VALUE);
            document.appendChild(root);

            for (Team team : teams.getTeam()) {
                root.appendChild(getTeam(team, document));
            }

            Transformer tr = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            FileOutputStream fos = new FileOutputStream(file);
            StreamResult result = new StreamResult(fos);
            tr.transform(source, result);
        } catch (ParserConfigurationException | IOException | TransformerException e) {
            e.printStackTrace();
        }
    }

    private Element getTeam(Team team, Document document) {
        Element teamElement = document.createElement(TEAM);
        teamElement.setAttribute(ID, team.getId());
        teamElement.appendChild(getGeneralInfo(team.getGeneral(), document));

        Element playersElement = document.createElement(PLAYERS);
        for (Player player : team.getPlayers().getPlayer()) {
            Element playerElement = getPlayer(player, document);
            playersElement.appendChild(playerElement);
        }
        teamElement.appendChild(playersElement);

        teamElement.appendChild(getCoach(team.getCoach(), document));

        Element sponsorsElement = document.createElement(SPONSORS);
        for (Sponsor sponsor : team.getSponsors().getSponsor()) {
            Element sponsorElement = getSponsor(sponsor, document);
            sponsorsElement.appendChild(sponsorElement);
        }
        teamElement.appendChild(sponsorsElement);
        teamElement.appendChild(getStadium(team.getStadium(), document));

        return teamElement;
    }

    private Element getGeneralInfo(Info info, Document document) {
        Element infoElement = document.createElement(GENERAL);
        Element name = document.createElement(NAME);
        name.setTextContent(info.getName());
        Element country = document.createElement(COUNTRY);
        country.setTextContent(info.getCountry());
        infoElement.appendChild(name);
        infoElement.appendChild(country);
        return infoElement;
    }

    private Element getPlayerInfo(PlayerInfo info, Document document) {
        Element generalInfo = getGeneralInfo(info, document);
        Element age = document.createElement(AGE);
        age.setTextContent(String.valueOf(info.getAge()));
        generalInfo.appendChild(age);
        return generalInfo;
    }

    private Element getCoachInfo(CoachInfo info, Document document) {
        Element generalInfo = getGeneralInfo(info, document);
        Element age = document.createElement(AGE);
        age.setTextContent(String.valueOf(info.getAge()));
        generalInfo.appendChild(age);
        return generalInfo;
    }

    private Element getStadiumInfo(StadiumInfo info, Document document) {
        Element generalInfo = getGeneralInfo(info, document);
        Element city = document.createElement(CITY);
        city.setTextContent(info.getCity());
        generalInfo.appendChild(city);
        return generalInfo;
    }

    private Element getPlayer(Player player, Document document) {
        Element playerElement = document.createElement(PLAYER);
        playerElement.setAttribute(ID, player.getId());
        Element general = getPlayerInfo(player.getGeneral(), document);
        Element cost = document.createElement(COST);
        cost.setTextContent(String.valueOf(player.getCost()));
        Element position = document.createElement(POSITION);
        position.setTextContent(player.getPosition().value());
        playerElement.appendChild(general);
        playerElement.appendChild(cost);
        playerElement.appendChild(position);
        return playerElement;
    }

    private Element getCoach(Coach coach, Document document) {
        Element coachElement = document.createElement(COACH);
        coachElement.setAttribute(ID, coach.getId());
        Element general = getCoachInfo(coach.getGeneral(), document);
        Element experience = document.createElement(EXPERIENCE);
        experience.setTextContent(String.valueOf(coach.getExperience()));
        coachElement.appendChild(general);
        coachElement.appendChild(experience);
        return coachElement;
    }

    private Element getSponsor(Sponsor sponsor, Document document) {
        Element sponsorElement = document.createElement(SPONSOR);
        sponsorElement.setAttribute(ID, sponsor.getId());
        Element general = getGeneralInfo(sponsor.getGeneral(), document);
        Element year = document.createElement(YEAR);
        year.setTextContent(String.valueOf(sponsor.getYear()));
        sponsorElement.appendChild(general);
        sponsorElement.appendChild(year);
        return sponsorElement;
    }

    private Element getStadium(Stadium stadium, Document document) {
        Element stadiumElement = document.createElement(STADIUM);
        stadiumElement.setAttribute(ID, stadium.getId());
        Element general = getStadiumInfo(stadium.getGeneral(), document);
        Element capacity = document.createElement(CAPACITY);
        capacity.setTextContent(String.valueOf(stadium.getCapacity()));
        stadiumElement.appendChild(general);
        stadiumElement.appendChild(capacity);
        return stadiumElement;
    }
}
