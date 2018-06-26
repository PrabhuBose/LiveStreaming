package com.intigral.mobile.android.livestreaming.parser;

import com.intigral.mobile.android.livestreaming.models.AwayTeamModel;
import com.intigral.mobile.android.livestreaming.models.HomeTeamModel;
import com.intigral.mobile.android.livestreaming.models.TeamPlayerLineUpModel;
import com.intigral.mobile.android.livestreaming.utils.StreamingUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prabhu on 6/26/18 LiveStreaming.
 */
public class JsonParser {

    static String TAG = "Live Streaming : JsonParser ";

    public static TeamPlayerLineUpModel parseHomeTeamResponse(String response) throws JSONException {

       TeamPlayerLineUpModel teamPlayerLineUpModel = new TeamPlayerLineUpModel();
        List<AwayTeamModel> awayTeamModelList = new ArrayList<>();
        List<HomeTeamModel> homeTeamModelList = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(response);

        JSONArray homePlayersArray = jsonObject.getJSONObject("Lineups").getJSONObject("Data").getJSONObject("HomeTeam").getJSONArray("Players");
        for (int i = 0; i < homePlayersArray.length(); i++) {
            HomeTeamModel homeTeamModel = new HomeTeamModel();
            homeTeamModel.setPlayerName(homePlayersArray.getJSONObject(i).getString("Name"));
            homeTeamModel.setPlayerRole(homePlayersArray.getJSONObject(i).getString("Role"));
            homeTeamModelList.add(homeTeamModel);
        }

        JSONArray awayTeamPlayers = jsonObject.getJSONObject("Lineups").getJSONObject("Data").getJSONObject("AwayTeam").getJSONArray("Players");
        for (int i = 0; i < awayTeamPlayers.length(); i++) {
            AwayTeamModel awayTeamModel = new AwayTeamModel();
            awayTeamModel.setPlayerName(awayTeamPlayers.getJSONObject(i).getString("Name"));
            awayTeamModel.setPlayerRole(awayTeamPlayers.getJSONObject(i).getString("Role"));
            awayTeamModelList.add(awayTeamModel);
        }

        teamPlayerLineUpModel.setHomeTeamModelList(homeTeamModelList);
        teamPlayerLineUpModel.setAwayTeamModelList(awayTeamModelList);

        return teamPlayerLineUpModel;
    }

}
