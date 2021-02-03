package com.robertlevonyan.views.expandablesample;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.robertlevonyan.views.expandable.Expandable;

/**
 * Created by robertlevonyan on 10/17/17.
 */

public class ExpandableViewHolder extends RecyclerView.ViewHolder {
    private Expandable expandable;
    private LinearLayout header;
    private FrameLayout content;
    private TextView headerText;
    private TextView headerSubText;
    private TextView contentText;

    ExpandableViewHolder(View itemView) {
        super(itemView);
        expandable = itemView.findViewById(R.id.expandable);
        header = itemView.findViewById(R.id.header);
        content = itemView.findViewById(R.id.content);
        headerText = itemView.findViewById(R.id.header_text);
        headerSubText = itemView.findViewById(R.id.header_subtext);
        contentText = itemView.findViewById(R.id.content_text);
    }

    public Expandable getExpandable() {
        return expandable;
    }

    public LinearLayout getHeader() {
        return header;
    }

    public FrameLayout getContent() {
        return content;
    }

    public TextView getHeaderText() {
        return headerText;
    }

    public TextView getHeaderSubText() {
        return headerSubText;
    }

    public TextView getContentText() {
        return contentText;
    }
}
