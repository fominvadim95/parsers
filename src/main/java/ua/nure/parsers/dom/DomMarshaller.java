package ua.nure.parsers.dom;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ua.nure.entities.*;
import ua.nure.parsers.Marshaller;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static ua.nure.utils.XmlElements.*;

public class DomMarshaller implements Marshaller {

    @Override
    public void marshall(Teams teams, File file) {

        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element root = document.createElement(TEAMS);
            root.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            root.setAttribute("xsi:noNamespaceSchemaLocation", "../teams.xsd");
            document.appendChild(root);

            for (Team team : teams.getTeam()) {

                root.appendChild(teamElement);
            }

            Transformer tr = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            FileOutputStream fos = new FileOutputStream(file);
            StreamResult result = new StreamResult(fos);
            tr.transform(source, result);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }


    }

    private Element getTeam(Team team, Document document){
        Element teamElement = document.createElement(TEAM);
        Element
        teamElement.setAttribute(ID, team.getId());
        teamElement.appendChild(getGeneralInfo(team.getGeneral(), document));


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
        Element year = document.createElement(SPONSOR);
        year.setTextContent(String.valueOf(sponsor.getYear()));
        sponsorElement.appendChild(general);
        sponsorElement.appendChild(year);
        return sponsorElement;
    }

    private Element getStadium(Stadium stadium, Document document){
        Element stadiumEelement = document.createElement(STADIUM);
        Element general = getStadiumInfo(stadium.getGeneral(),document);
        Element capacity = document.createElement(CAPACITY);
        capacity.setTextContent(String.valueOf(stadium.getCapacity()));
        stadiumEelement.appendChild(general);
        stadiumEelement.appendChild(capacity);
        return stadiumEelement;
    }
}
