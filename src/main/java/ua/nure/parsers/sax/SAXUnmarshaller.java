package ua.nure.parsers.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ua.nure.entities.*;
import ua.nure.parsers.TeamsUnmarshaller;
import ua.nure.utils.XMLTag;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class SAXUnmarshaller extends DefaultHandler implements TeamsUnmarshaller {

    private String currentElement;
    public Teams teams;
    public Team team;
    public Info info;

    public Players players;
    public Player player;
    public PlayerInfo playerInfo;

    public Coach coach;
    public CoachInfo coachInfo;

    public Sponsors sponsors;
    public Sponsor sponsor;
    public Info sponsorInfo;

    public Stadium stadium;
    public StadiumInfo stadiumInfo;

    @Override
    public Teams unmarshal(File file) {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        saxParserFactory.setNamespaceAware(true);
        SAXParser saxParser = null;
        try {
            saxParser = saxParserFactory.newSAXParser();
            saxParser.parse(file, this);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return teams;
    }

    @Override
    public void startElement(String uri, String nameLocal, String qName,
                             Attributes attributes) throws SAXException {

        currentElement = nameLocal;

        if (XMLTag.TEAMS.equals(currentElement)) {
            teams = new Teams();
            return;
        }

        if (XMLTag.TEAM.equals(currentElement)) {
            team = new Team();
            team.setId(resolveAttr(uri, attributes));
            return;
        }

        if (XMLTag.PLAYERS.equals(currentElement)) {
            players = new Players();
            return;
        }

        if (XMLTag.PLAYER.equals(currentElement)) {
            player = new Player();
            player.setId(resolveAttr(uri, attributes));
            return;
        }

        if (XMLTag.COACH.equals(currentElement)) {
            coach = new Coach();
            coach.setId(resolveAttr(uri, attributes));
            return;
        }

        if (XMLTag.SPONSORS.equals(currentElement)) {
            sponsors = new Sponsors();
            return;
        }

        if (XMLTag.SPONSOR.equals(currentElement)) {
            sponsor = new Sponsor();
            sponsor.setId(resolveAttr(uri, attributes));
            return;
        }

        if (XMLTag.STADIUM.equals(currentElement)) {
            stadium = new Stadium();
            stadium.setId(resolveAttr(uri, attributes));
            return;
        }

        if (XMLTag.GENERAL.equals(currentElement)) {
            if (player != null) {
                playerInfo = new PlayerInfo();
                return;
            }

            if (coach != null) {
                coachInfo = new CoachInfo();
                return;
            }

            if (sponsor != null) {
                sponsorInfo = new Info();
                return;
            }

            if (stadium != null) {
                stadiumInfo = new StadiumInfo();
                return;
            }

            if (team != null) {
                info = new Info();
                return;
            }
        }
    }

    @Override
    public void characters(char[] charsMassive, int start, int length)
            throws SAXException {

        String elementText = new String(charsMassive, start, length).trim();

        if (elementText.isEmpty()) {
            return;
        }

        if (XMLTag.NAME.equals(currentElement)) {
            if (info != null) {
                setName(info, elementText);
                return;
            }
            if (playerInfo != null) {
                setName(playerInfo, elementText);
                return;
            }
            if (coachInfo != null) {
                setName(coachInfo, elementText);
                return;
            }
            if (sponsorInfo != null) {
                setName(sponsorInfo, elementText);
                return;
            }
            if (stadiumInfo != null) {
                setName(stadiumInfo, elementText);
                return;
            }
        }

        if (XMLTag.COUNTRY.equals(currentElement)) {
            if (info != null) {
                setCountry(info, elementText);
                return;
            }
            if (playerInfo != null) {
                setCountry(playerInfo, elementText);
                return;
            }
            if (coachInfo != null) {
                setCountry(coachInfo, elementText);
                return;
            }
            if (sponsorInfo != null) {
                setCountry(sponsorInfo, elementText);
                return;
            }
            if (stadiumInfo != null) {
                setCountry(stadiumInfo, elementText);
                return;
            }
        }

        if (XMLTag.AGE.equals(currentElement)) {
            if (playerInfo != null) {
                playerInfo.setAge(Integer.valueOf(elementText));
                return;
            } else {
                coachInfo.setAge(Integer.valueOf(elementText));
                return;
            }
        }

        if (XMLTag.COST.equals(currentElement)) {
            player.setCost(Integer.valueOf(elementText));
            return;
        }

        if (XMLTag.POSITION.equals(currentElement)) {
            player.setPosition(Position.valueOf(elementText));
            return;
        }

        if (XMLTag.EXPERIENCE.equals(currentElement)) {
            coach.setExperience(Integer.valueOf(elementText));
            return;
        }
        if (XMLTag.YEAR.equals(currentElement)) {
            sponsor.setYear(Integer.valueOf(elementText));
            return;
        }

        if (XMLTag.CITY.equals(currentElement)) {
            stadiumInfo.setCity(elementText);
            return;
        }

        if (XMLTag.CAPACITY.equals(currentElement)) {
            stadium.setCapacity(Integer.valueOf(elementText));
            return;
        }
    }

    @Override
    public void endElement(String uri, String nameLocal, String qName)
            throws SAXException {

        if (XMLTag.TEAM.equals(nameLocal)) {
            teams.getTeam().add(team);
            team = null;
            return;
        }

        if (XMLTag.PLAYERS.equals(nameLocal)) {
            team.setPlayers(players);
            players = null;
            return;
        }

        if (XMLTag.PLAYER.equals(nameLocal)) {
            players.getPlayer().add(player);
            player = null;
            return;
        }

        if (XMLTag.COACH.equals(nameLocal)) {
            team.setCoach(coach);
            coach = null;
            return;
        }

        if (XMLTag.SPONSORS.equals(nameLocal)) {
            team.setSponsors(sponsors);
            sponsors = null;
            return;
        }

        if (XMLTag.SPONSOR.equals(nameLocal)) {
            sponsors.getSponsor().add(sponsor);
            sponsor = null;
            return;
        }

        if (XMLTag.STADIUM.equals(nameLocal)) {
            team.setStadium(stadium);
            stadium = null;
            return;
        }

        if (XMLTag.GENERAL.equals(nameLocal)) {
            if (player != null) {
                player.setGeneral(playerInfo);
                playerInfo = null;
                return;
            }

            if (coach != null) {
                coach.setGeneral(coachInfo);
                coachInfo = null;
                return;
            }

            if (sponsor != null) {
                sponsor.setGeneral(sponsorInfo);
                sponsorInfo = null;
                return;
            }

            if (stadium != null) {
                stadium.setGeneral(stadiumInfo);
                stadiumInfo = null;
                return;
            }

            if (team != null) {
                team.setGeneral(info);
                info = null;
                return;
            }
        }
    }

    private String resolveAttr(String uri, Attributes attr) {
        return attr.getValue(uri, "id");
    }

    private void setName(Info info, String name) {
        info.setName(name);
    }

    private void setCountry(Info info, String country) {
        info.setCountry(country);
    }
}
