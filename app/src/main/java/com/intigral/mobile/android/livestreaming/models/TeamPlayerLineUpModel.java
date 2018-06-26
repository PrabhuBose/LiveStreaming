package com.intigral.mobile.android.livestreaming.models;

import java.io.Serializable;

/**
 * Created by Prabhu on 6/26/18 LiveStreaming.
 */
public class HomeTeamModel implements Serializable {

    private String homeTeamPlayerName;
    private String homeTeamPlayerRole;

    private String awayTeamPlayerName;
    private String awayTeamPlayerRole;


    public String getHomeTeamPlayerName() {
        return homeTeamPlayerName;
    }

    public void setHomeTeamPlayerName(String homeTeamPlayerName) {
        this.homeTeamPlayerName = homeTeamPlayerName;
    }

    public String getHomeTeamPlayerRole() {
        return homeTeamPlayerRole;
    }

    public void setHomeTeamPlayerRole(String homeTeamPlayerRole) {
        this.homeTeamPlayerRole = homeTeamPlayerRole;
    }

    public String getAwayTeamPlayerName() {
        return awayTeamPlayerName;
    }

    public void setAwayTeamPlayerName(String awayTeamPlayerName) {
        this.awayTeamPlayerName = awayTeamPlayerName;
    }

    public String getAwayTeamPlayerRole() {
        return awayTeamPlayerRole;
    }

    public void setAwayTeamPlayerRole(String awayTeamPlayerRole) {
        this.awayTeamPlayerRole = awayTeamPlayerRole;
    }
}
