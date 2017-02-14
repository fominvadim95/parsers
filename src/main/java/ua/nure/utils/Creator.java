package ua.nure.utils;

import ua.nure.entities.*;

import java.util.List;

public class Creator {

    public Teams createTeamsList() {
        Teams teams = new Teams();
        teams.getTeam().add(createTeam());
        return teams;
    }

    public PlayerInfo createPlayerInfo(){
        PlayerInfo info = new PlayerInfo();
        info.setAge(32);
        info.setCountry("Portugal");
        info.setName("Ronaldo");
        return info;
    }

    public CoachInfo createCoachInfo(){
        CoachInfo info = new CoachInfo();
        info.setAge(44);
        info.setCountry("France");
        info.setName("Zidane");
        return info;
    }

    public Info createSponsorInfo(){
        Info info = new Info();
        info.setCountry("UAE");
        info.setName("Emirates Airlines");
        return info;
    }

    public StadiumInfo createStadiumInfo(){
        StadiumInfo info = new StadiumInfo();
        info.setCountry("Spain");
        info.setCity("Madrid");
        info.setName("Santiago Bernabeu");
        return info;
    }

    public Info createTeamInfo(){
        Info info = new Info();
        info.setCountry("Spain");
        info.setName("Real Madrid");
        return info;
    }

    public Player createPlayer(){
        Player player = new Player();
        player.setId("p1");
        player.setGeneral(createPlayerInfo());
        player.setCost(150);
        player.setPosition(Position.FB);
        return player;
    }

    public Coach createCoach(){
        Coach coach = new Coach();
        coach.setId("c1");
        coach.setGeneral(createCoachInfo());
        coach.setExperience(7);
        return coach;
    }

    public Sponsor createSponsor(){
        Sponsor sponsor = new Sponsor();
        sponsor.setId("sp1");
        sponsor.setGeneral(createSponsorInfo());
        sponsor.setYear(2020);
        return sponsor;
    }

    public Sponsors createSponsorList(){
        Sponsors sponsors = new Sponsors();
        List<Sponsor> list = sponsors.getSponsor();
        list.add(createSponsor());
        sponsors.getSponsor().addAll(list);
        return sponsors;
    }

    public Players createPlayerList(){
        Players players = new Players();
        List<Player> list = players.getPlayer();
        list.add(createPlayer());
        players.getPlayer().addAll(list);
        return players;
    }

    public Stadium createStadium(){
        Stadium stadium = new Stadium();
        stadium.setId("st1");
        stadium.setGeneral(createStadiumInfo());
        stadium.setCapacity(81044);
        return stadium;
    }

    public Team createTeam(){
        Team team = new Team();
        team.setId("t1");
        team.setGeneral(createTeamInfo());
        team.setCoach(createCoach());
        team.setStadium(createStadium());
        team.setPlayers(createPlayerList());
        team.setSponsors(createSponsorList());
        return team;
    }

}
