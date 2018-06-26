package com.intigral.mobile.android.livestreaming.models;

import java.io.Serializable;

/**
 * Created by Prabhu on 6/26/18 LiveStreaming.
 */
public class AwayTeamModel implements Serializable {

    private String playerName;
    private String playerRole;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerRole() {
        return playerRole;
    }

    public void setPlayerRole(String playerRole) {
        this.playerRole = playerRole;
    }
}
