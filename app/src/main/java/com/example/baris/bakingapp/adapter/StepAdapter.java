package com.example.baris.bakingapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import com.example.baris.bakingapp.R;
import com.example.baris.bakingapp.model.Step;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepAdapterViewHolder>
{
    private List<Step> steps;

    public interface OnStepListItemClickListener {
        void onStepListItemClick(Step item);
    }
    private OnStepListItemClickListener onStepListItemClickListener;
    public StepAdapter(List<Step> steps, OnStepListItemClickListener onStepListItemClickListener) {
        this.steps = steps;
        this.onStepListItemClickListener = onStepListItemClickListener;
    }
    @NonNull
    @Override
    public StepAdapter.StepAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup vGroup, int i) {
        View v = LayoutInflater
                .from(vGroup.getContext())
                .inflate(R.layout.cv_step, vGroup, false);
        return new StepAdapterViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull final StepAdapterViewHolder holder, int position) {
        Step step = steps.get(position);
        int stepId = step.getID();
        String stepShortDescription = step.getShortDescription();
        if (stepId == 0) {
            holder.shortDescription.setText(stepShortDescription);
        } else {
            holder.shortDescription.setText("Step "+String.valueOf(stepId) + ": " + stepShortDescription);
        }
        holder.bind(steps.get(position), onStepListItemClickListener);
    }
    @Override
    public int getItemCount() {
        if (null == steps) return 0;
        return steps.size();
    }

    public void setSteps(ArrayList<Step> steps) {
        this.steps = steps;
        notifyDataSetChanged();
    }

    class StepAdapterViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView shortDescription;

        void bind(final Step item, final OnStepListItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onStepListItemClick(item);
                }
            });
        }

        StepAdapterViewHolder(final View item) {
            super(item);
            cardView = item.findViewById(R.id.step_cv);
            shortDescription = item.findViewById(R.id.step_shortDescription_tv);
        }

    }
}

