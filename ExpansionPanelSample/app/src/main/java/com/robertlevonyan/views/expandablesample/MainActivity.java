package com.robertlevonyan.views.expandablesample;

import androidx.core.content.ContextCompat;
import androidx.core.util.Pair;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        light();

        RecyclerView list = findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(new ExpandableAdapter(this, getModels(), getColors()));
    }

    private ArrayList<Pair<Integer, Integer>> getColors() {
        ArrayList<Pair<Integer, Integer>> colors = new ArrayList<>();

        colors.add(Pair.create(ContextCompat.getColor(this, R.color.colorRed1), ContextCompat.getColor(this, R.color.colorRed2)));
        colors.add(Pair.create(ContextCompat.getColor(this, R.color.colorPurple1), ContextCompat.getColor(this, R.color.colorPurple2)));
        colors.add(Pair.create(ContextCompat.getColor(this, R.color.colorIndigo1), ContextCompat.getColor(this, R.color.colorIndigo2)));
        colors.add(Pair.create(ContextCompat.getColor(this, R.color.colorBlue1), ContextCompat.getColor(this, R.color.colorBlue2)));
        colors.add(Pair.create(ContextCompat.getColor(this, R.color.colorCyan1), ContextCompat.getColor(this, R.color.colorCyan2)));
        colors.add(Pair.create(ContextCompat.getColor(this, R.color.colorTeal1), ContextCompat.getColor(this, R.color.colorTeal2)));
        colors.add(Pair.create(ContextCompat.getColor(this, R.color.colorGreen1), ContextCompat.getColor(this, R.color.colorGreen2)));
        colors.add(Pair.create(ContextCompat.getColor(this, R.color.colorLime1), ContextCompat.getColor(this, R.color.colorLime2)));
        colors.add(Pair.create(ContextCompat.getColor(this, R.color.colorAmber1), ContextCompat.getColor(this, R.color.colorAmber2)));
        colors.add(Pair.create(ContextCompat.getColor(this, R.color.colorOrange1), ContextCompat.getColor(this, R.color.colorOrange2)));
        colors.add(Pair.create(ContextCompat.getColor(this, R.color.colorBrown1), ContextCompat.getColor(this, R.color.colorBrown2)));
        colors.add(Pair.create(ContextCompat.getColor(this, R.color.colorRed1), ContextCompat.getColor(this, R.color.colorRed2)));
        colors.add(Pair.create(ContextCompat.getColor(this, R.color.colorPurple1), ContextCompat.getColor(this, R.color.colorPurple2)));
        colors.add(Pair.create(ContextCompat.getColor(this, R.color.colorIndigo1), ContextCompat.getColor(this, R.color.colorIndigo2)));
        colors.add(Pair.create(ContextCompat.getColor(this, R.color.colorBlue1), ContextCompat.getColor(this, R.color.colorBlue2)));
        colors.add(Pair.create(ContextCompat.getColor(this, R.color.colorCyan1), ContextCompat.getColor(this, R.color.colorCyan2)));
        colors.add(Pair.create(ContextCompat.getColor(this, R.color.colorTeal1), ContextCompat.getColor(this, R.color.colorTeal2)));
        colors.add(Pair.create(ContextCompat.getColor(this, R.color.colorGreen1), ContextCompat.getColor(this, R.color.colorGreen2)));
        colors.add(Pair.create(ContextCompat.getColor(this, R.color.colorLime1), ContextCompat.getColor(this, R.color.colorLime2)));
        colors.add(Pair.create(ContextCompat.getColor(this, R.color.colorAmber1), ContextCompat.getColor(this, R.color.colorAmber2)));
        colors.add(Pair.create(ContextCompat.getColor(this, R.color.colorOrange1), ContextCompat.getColor(this, R.color.colorOrange2)));
        colors.add(Pair.create(ContextCompat.getColor(this, R.color.colorBrown1), ContextCompat.getColor(this, R.color.colorBrown2)));

        return colors;
    }

    private ArrayList<ExpandableModel> getModels() {
        String[] header = getResources().getStringArray(R.array.headers);
        String[] subHeaders = getResources().getStringArray(R.array.sub_headers);
        String[] contents = getResources().getStringArray(R.array.contents);
        ArrayList<ExpandableModel> expandableModels = new ArrayList<>();
        for (int i = 0; i < header.length; i++) {
            ExpandableModel model = new ExpandableModel();

            model.setHeaderText(header[i]);
            model.setHeaderSubText(subHeaders[i]);
            model.setContentText(contents[i]);

            expandableModels.add(model);
        }
        return expandableModels;
    }

    private void light() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            getWindow().getDecorView().getRootView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            );
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark23));
        }
    }
}
