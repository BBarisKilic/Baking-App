package com.example.baris.bakingapp;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.view.LayoutInflater;

import com.example.baris.bakingapp.model.RP;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

import com.example.baris.bakingapp.helper.RecipeData;
import com.example.baris.bakingapp.helper.idlingResource;
import com.example.baris.bakingapp.model.Step;

public class StepFragment extends Fragment {

    private String recipeName;
    private TextView chosen_recipeName;
    private ArrayList<Step> steps;
    private TextView stepDescription;
    private SimpleExoPlayer simpleExoPlayer;
    private SimpleExoPlayerView simpleExoPlayerView;
    private View rootView;
    private View secondView;
    private ImageView thumbnailView;
    boolean playWhenReady;
    long videoPos = 0;
    int current = 0;
    private static final String TAG = "StepFragment";
    Uri videoUri;

    @Nullable
    private idlingResource idlingResource;

    public StepFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        rootView = inflater.inflate(R.layout.fragment_step, container, false);
        secondView = inflater.inflate(R.layout.activity_details, container,false);
        stepDescription = rootView.findViewById(R.id.step_description_tv);
        simpleExoPlayerView = rootView.findViewById(R.id.exoplayer_view);
        thumbnailView = rootView.findViewById(R.id.video_iv);
        chosen_recipeName = rootView.findViewById(R.id.chosen_recipe_name_step_tv);

        idlingResource = getIdlingResource();
        idlingResource.setIdleState(false);

        RP selectedRecipe = RecipeData.recipe;

        if (savedInstanceState == null) {
            Bundle args = getArguments();
            if (getArguments() != null) {
                steps = args.getParcelableArrayList("STEPS");
                recipeName = selectedRecipe.getRecipeName();
                updateStepView(RecipeData.index);
            }
        } else {
            steps = savedInstanceState.getParcelableArrayList("STEPS");
            videoPos = savedInstanceState.getLong("VIDEO_POSITION");
            playWhenReady = savedInstanceState.getBoolean("PLAY_WHEN_READY");
            recipeName = savedInstanceState.getString("RECIPE_NAME_SAVED");
            updateStepView(RecipeData.index);
        }
        return rootView;
    }

    public void updateStepView(int position) {
        Step step = steps.get(position);
        stepDescription.setText(step.getDescription());

        if(secondView.findViewById((R.id.twoPane)) == null) {
            chosen_recipeName.setText(recipeName);
        }

        if (step.getVideoURL().equals("")) {
            simpleExoPlayerView.setVisibility(View.GONE);
            thumbnailView.setVisibility(View.VISIBLE);
            Context context = rootView.getContext();

            String recipe_img_name = "empty";
            int resID = -1;
            switch (recipeName) {
                case "Nutella Pie":
                    recipe_img_name = "nutella_pie";
                    resID = context.getResources().getIdentifier(recipe_img_name, "drawable", context.getPackageName());
                    Picasso.with(context)
                            .load(resID)
                            .into(thumbnailView);
                    break;
                case "Brownies":
                    recipe_img_name = "brownies";
                    resID = context.getResources().getIdentifier(recipe_img_name, "drawable", context.getPackageName());
                    Picasso.with(context)
                            .load(resID)
                            .into(thumbnailView);
                    break;
                case "Yellow Cake":
                    recipe_img_name = "yellow_cake";
                    resID = context.getResources().getIdentifier(recipe_img_name, "drawable", context.getPackageName());
                    Picasso.with(context)
                            .load(resID)
                            .into(thumbnailView);
                    break;
                case "Cheesecake":
                    recipe_img_name = "cheesecake";
                    resID = context.getResources().getIdentifier(recipe_img_name, "drawable", context.getPackageName());
                    Picasso.with(context)
                            .load(resID)
                            .into(thumbnailView);
                    break;
                default:
                    thumbnailView.setImageDrawable(context.getResources().getDrawable(R.drawable.brownies));
                    break;
            }
        } else {
            simpleExoPlayerView.setVisibility(View.VISIBLE);
            thumbnailView.setVisibility(View.GONE);
            videoUri = Uri.parse(step.getVideoURL());
            initializePlayer(videoUri);
        }
        RecipeData.index = position;
    }

    public void setListIndex(int index) {
        RecipeData.index = index;
    }

    public void initializePlayer(Uri mediaUri) {
        if (simpleExoPlayer == null) {
            LoadControl loadControl = new DefaultLoadControl();
            TrackSelector trackSelector = new DefaultTrackSelector();
            simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(rootView.getContext(), trackSelector, loadControl);
            simpleExoPlayerView.setPlayer(simpleExoPlayer);
            String userAgent = Util.getUserAgent(rootView.getContext(), "BakingApp");
            MediaSource mediasource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
                    rootView.getContext(), userAgent), new DefaultExtractorsFactory(), null, null);
            simpleExoPlayer.prepare(mediasource);
            simpleExoPlayer.seekTo(current, videoPos);
            simpleExoPlayer.setPlayWhenReady(playWhenReady);
            assert idlingResource != null;
            idlingResource.setIdleState(true);
        }
    }
    private void releasePlayer() {
        videoPos = simpleExoPlayer.getCurrentPosition();
        playWhenReady = simpleExoPlayer.getPlayWhenReady();
        current = simpleExoPlayer.getCurrentWindowIndex();
        simpleExoPlayer.stop();
        simpleExoPlayer.release();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (current == 0) {
            initializePlayer(videoUri);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (simpleExoPlayer != null) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        simpleExoPlayer = null;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (simpleExoPlayer != null) {
            bundle.putLong("VIDEO_POSITION", videoPos);
            bundle.putBoolean("PLAY_WHEN_READY", playWhenReady);
            bundle.putParcelableArrayList("STEPS", steps);
            bundle.putString("RECIPE_NAME_SAVED", recipeName);
        }
    }

    @VisibleForTesting
    @NonNull
    public idlingResource getIdlingResource() {
        if (idlingResource == null) {
            idlingResource = new idlingResource();
        }
        return idlingResource;
    }
}