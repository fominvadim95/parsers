package ua.nure.parsers.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ua.nure.entities.*;
import ua.nure.parsers.TeamsUnmarshaller;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static ua.nure.utils.XMLTag.*;

public class DOMUnmarshaller implements TeamsUnmarshaller {

    @Override
    public Teams unmarshal(File file) {
        Teams teams = new Teams();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            Element root = document.getDocumentElement();
            NodeList teamsNodes = root.getChildNodes();
            for (int i = 0; i < teamsNodes.getLength(); i++) {
                if (teamsNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element teamElement = (Element) teamsNodes.item(i);
                    teams.getTeam().add(getTeam(teamElement));
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return teams;
    }

    private Team getTeam(Element teamElement) {
        Team team = new Team();
        Players players = new Players();
        Sponsors sponsors = new Sponsors();
        List<Player> playerList = new ArrayList<>();
        List<Sponsor> sponsorList = new ArrayList<>();

        Element general = (Element) teamElement.getElementsByTagName(GENERAL).item(0);
        NodeList playersNodeList = teamElement.getElementsByTagName(PLAYER);
        for (int i = 0; i < playersNodeList.getLength(); i++) {
            playerList.add(getPlayer((Element) playersNodeList.item(i)));
        }
        players.getPlayer().addAll(playerList);

        NodeList sponsorsNodeList = teamElement.getElementsByTagName(SPONSOR);
        for (int i = 0; i < sponsorsNodeList.getLength(); i++) {
            sponsorList.add(getSponsor((Element) sponsorsNodeList.item(i)));
        }
        sponsors.getSponsor().addAll(sponsorList);

        team.setId(teamElement.getAttributeNode(ID).getTextContent());
        team.setGeneral(getInfo(general));
        team.setCoach(getCoach((Element) teamElement.getElementsByTagName(COACH).item(0)));
        team.setPlayers(players);
        team.setSponsors(sponsors);
        team.setStadium(getStadium((Element) teamElement.getElementsByTagName(STADIUM).item(0)));

        return team;
    }

    private Info getInfo(Element infoElement) {
        String parentName = infoElement.getParentNode().getNodeName();
        String country = infoElement.getElementsByTagName(COUNTRY).item(0).getTextContent();
        String name = infoElement.getElementsByTagName(NAME).item(0).getTextContent();
        switch (parentName) {

            case COACH:
                CoachInfo coachInfo = new CoachInfo();
                int coachAge = Integer.parseInt(infoElement.getElementsByTagName(AGE).item(0).getTextContent());
                coachInfo.setCountry(country);
                coachInfo.setName(name);
                coachInfo.setAge(coachAge);
                return coachInfo;

            case PLAYER:
                PlayerInfo playerInfo = new PlayerInfo();
                int playerAge = Integer.parseInt(infoElement.getElementsByTagName(AGE).item(0).getTextContent());
                playerInfo.setCountry(country);
                playerInfo.setName(name);
                playerInfo.setAge(playerAge);
                return playerInfo;

            case STADIUM:
                StadiumInfo stadiumInfo = new StadiumInfo();
                String city = infoElement.getElementsByTagName(CITY).item(0).getTextContent();
                stadiumInfo.setCountry(country);
                stadiumInfo.setName(name);
                stadiumInfo.setCity(city);
                return stadiumInfo;

            default:
                Info info = new Info();
                info.setCountry(country);
                info.setName(name);
                return info;
        }
    }

    private Player getPlayer(Element playerElement) {
        Player player = new Player();
        player.setId(playerElement.getAttributeNode(ID).getTextContent());
        Element general = (Element) playerElement.getElementsByTagName(GENERAL).item(0);
        player.setGeneral((PlayerInfo) getInfo(general));
        player.setCost(Integer.parseInt(playerElement.getElementsByTagName(COST).item(0).getTextContent()));
        player.setPosition(Position.fromValue(playerElement.getElementsByTagName(POSITION).item(0).getTextContent()));
        return player;
    }

    private Coach getCoach(Element coachElement) {
        Coach coach = new Coach();
        coach.setId(coachElement.getAttributeNode(ID).getTextContent());
        Element general = (Element) coachElement.getElementsByTagName(GENERAL).item(0);
        coach.setGeneral((CoachInfo) getInfo(general));
        coach.setExperience(Integer.parseInt(coachElement.getElementsByTagName(EXPERIENCE).item(0).getTextContent()));
        return coach;
    }

    private Sponsor getSponsor(Element sponsorElement) {
        Sponsor sponsor = new Sponsor();
        sponsor.setId(sponsorElement.getAttributeNode(ID).getTextContent());
        Element general = (Element) sponsorElement.getElementsByTagName(GENERAL).item(0);
        sponsor.setGeneral(getInfo(general));
        sponsor.setYear(Integer.parseInt(sponsorElement.getElementsByTagName(YEAR).item(0).getTextContent()));
        return sponsor;
    }

    private Stadium getStadium(Element stadiumElement) {
        Stadium stadium = new Stadium();
        stadium.setId(stadiumElement.getAttributeNode(ID).getTextContent());
        Element general = (Element) stadiumElement.getElementsByTagName(GENERAL).item(0);
        stadium.setGeneral((StadiumInfo) getInfo(general));
        stadium.setCapacity(Integer.parseInt(stadiumElement.getElementsByTagName(CAPACITY).item(0).getTextContent()));
        return stadium;
    }

}