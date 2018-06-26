package com.intigral.mobile.android.livestreaming.parser;

import com.intigral.mobile.android.livestreaming.models.TeamPlayerLineUpModel;
import com.intigral.mobile.android.livestreaming.utils.StreamingUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Prabhu on 6/26/18 LiveStreaming.
 */
public class JsonParser {

    static String TAG = "Live Streaming : JsonParser ";

    public static TeamPlayerLineUpModel parseHomeTeamResponse(String response) throws JSONException {
        TeamPlayerLineUpModel teamPlayerLineUpModel = new TeamPlayerLineUpModel();
        JSONObject jsonObject = new JSONObject(response);

        JSONArray homePlayersArray = jsonObject.getJSONObject("data").getJSONObject("HomeTeam").getJSONArray("Players");
        for (int i = 0; i < homePlayersArray.length(); i++) {
            teamPlayerLineUpModel.setHomeTeamPlayerName(homePlayersArray.getJSONObject(i).getString("Name"));
            teamPlayerLineUpModel.setHomeTeamPlayerRole(homePlayersArray.getJSONObject(i).getString("Role"));

        }

        JSONArray awayTeamPlayers = jsonObject.getJSONObject("data").getJSONObject("HomeTeam").getJSONArray("Players");
        for (int i = 0; i < awayTeamPlayers.length(); i++) {
            teamPlayerLineUpModel.setAwayTeamPlayerName(awayTeamPlayers.getJSONObject(i).getString("Name"));
            teamPlayerLineUpModel.setAwayTeamPlayerRole(awayTeamPlayers.getJSONObject(i).getString("Role"));

        }

        return teamPlayerLineUpModel;
    }

}
