package com.intigral.mobile.android.livestreaming.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prabhu on 6/26/18 LiveStreaming.
 */
public class TeamPlayerLineUpModel implements Serializable {

    List<AwayTeamModel> awayTeamModelList = new ArrayList<>();

    List<HomeTeamModel> homeTeamModelList = new ArrayList<>();


    public List<AwayTeamModel> getAwayTeamModelList() {
        return awayTeamModelList;
    }

    public void setAwayTeamModelList(List<AwayTeamModel> awayTeamModelList) {
        this.awayTeamModelList = awayTeamModelList;
    }

    public List<HomeTeamModel> getHomeTeamModelList() {
        return homeTeamModelList;
    }

    public void setHomeTeamModelList(List<HomeTeamModel> homeTeamModelList) {
        this.homeTeamModelList = homeTeamModelList;
    }
}
