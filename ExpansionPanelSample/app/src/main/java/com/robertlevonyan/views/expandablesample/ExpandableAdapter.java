package com.robertlevonyan.views.expandablesample;

import android.content.Context;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.robertlevonyan.views.expandable.Expandable;

import java.util.ArrayList;

/**
 * Created by robertlevonyan on 10/17/17.
 */

public class ExpandableAdapter extends RecyclerView.Adapter<ExpandableViewHolder> {
    private Context context;
    private ArrayList<ExpandableModel> models;
    private ArrayList<Pair<Integer, Integer>> colors;

    public ExpandableAdapter(Context context, ArrayList<ExpandableModel> models, ArrayList<Pair<Integer, Integer>>  colors) {
        this.context = context;
        this.models = models;
        this.colors = colors;
    }

    @Override
    public ExpandableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_expandable, parent, false);
        return new ExpandableViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ExpandableViewHolder holder, int position) {
        ExpandableModel expandableModel = models.get(holder.getAdapterPosition());
        Expandable expandable = holder.getExpandable();
        LinearLayout header = holder.getHeader();
        FrameLayout content = holder.getContent();
        TextView headerText = holder.getHeaderText();
        TextView headerSubText = holder.getHeaderSubText();
        TextView contentText = holder.getContentText();

        Pair<Integer, Integer> color = colors.get(holder.getAdapterPosition());

        expandable.setExpandingListener(new Expandable.ExpandingListener() {
            @Override
            public void onExpanded() {
                Log.e("View", "Expanded");
            }

            @Override
            public void onCollapsed() {
                Log.e("View", "Collapsed");
            }
        });

        expandable.setAnimateExpand(true);
        header.setBackgroundColor(color.first);
        content.setBackgroundColor(color.second);
        headerText.setText(expandableModel.getHeaderText());
        headerSubText.setText(expandableModel.getHeaderSubText());
        contentText.setText(expandableModel.getContentText());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
