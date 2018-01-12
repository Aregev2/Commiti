package com.aregev2.commiti;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

public class VolunteerFragment extends Fragment {

    private ProgressBar progressBar;

    private TextView textView;

    private int progressMax = 60;
    private int progress = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_volunteer, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = view.findViewById(R.id.fragment_volunteer_progressBar_hours);
        progressBar.setMax(progressMax);
        progressBar.setProgress(progress);

        textView = view.findViewById(R.id.fragment_volunteer_textView_progress);
        textView.setText("You have made " + progress + " out of " + progressMax + " hours.");

    }
}
