package com.intigral.mobile.android.livestreaming.adapter;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.intigral.mobile.android.livestreaming.R;
import com.intigral.mobile.android.livestreaming.models.TeamPlayerLineUpModel;
import com.intigral.mobile.android.livestreaming.utils.StreamingUtils;

/**
 * Created by Prabhu on 6/26/18 LiveStreaming.
 */
public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.ViewHolder> {
    private TeamPlayerLineUpModel teamPlayerLineUpModel;
    private String team;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView player;

        public ViewHolder(View v) {
            super(v);
            player = v.findViewById(R.id.player);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public PlayersAdapter(String team, TeamPlayerLineUpModel teamPlayerLineUpModel) {
        this.team = team;
        this.teamPlayerLineUpModel = teamPlayerLineUpModel;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public PlayersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_layout_for_palyers, parent, false);


        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        StreamingUtils.showLog("Team Name : ",team);

        if (team.equalsIgnoreCase("away")) {
            StreamingUtils.showLog("Player Name : Away team", teamPlayerLineUpModel.getAwayTeamModelList().get(position).getPlayerName());
            holder.player.setText(teamPlayerLineUpModel.getAwayTeamModelList().get(position).getPlayerName() + " ("+
                    teamPlayerLineUpModel.getAwayTeamModelList().get(position).getPlayerRole() +")");
        } else if (team.equalsIgnoreCase("home")) {
            StreamingUtils.showLog("Player Name : Home team", teamPlayerLineUpModel.getHomeTeamModelList().get(position).getPlayerName());
            holder.player.setText(teamPlayerLineUpModel.getHomeTeamModelList().get(position).getPlayerName() + " ("+
                    teamPlayerLineUpModel.getHomeTeamModelList().get(position).getPlayerRole() +")");
        }


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if (team.equalsIgnoreCase("away")) {
            return teamPlayerLineUpModel.getAwayTeamModelList().size();
        } else if (team.equalsIgnoreCase("home")) {
            return teamPlayerLineUpModel.getHomeTeamModelList().size();
        } else
            return 0;

    }
}