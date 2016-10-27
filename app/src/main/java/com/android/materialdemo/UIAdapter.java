package com.android.materialdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by chenfeiyue on 16/10/11.
 */

public class UIAdapter extends RecyclerView.Adapter<UIAdapter.ViewHolder> {
    private List<DemoDetails> mViewModels;
    private Context mContext;
    private OnRecycleViewListener onRecycleViewListener;

    public UIAdapter(Context context) {
        this.mContext = context;
    }

    public interface OnRecycleViewListener {
        void onItemClicked(int position);
    }

    public void setOnRecycleViewListener(OnRecycleViewListener onRecycleViewListener) {
        this.onRecycleViewListener = onRecycleViewListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.feature_ui, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        if (position > getItemCount()) {
            viewHolder.rootView.setVisibility(View.GONE);
            return;
        }

        DemoDetails viewModel = mViewModels.get(position);

        if (viewModel == null) {
            viewHolder.rootView.setVisibility(View.GONE);
            return;
        }
        viewHolder.title.setText(viewModel.getTitle());
        viewHolder.description.setText(viewModel.getDescription());
        viewHolder.rootView.setOnClickListener(v -> {
            if (onRecycleViewListener != null)
                onRecycleViewListener.onItemClicked(position);
        });

    }

    public void setViewModels(List<DemoDetails> mViewModels) {
        this.mViewModels = mViewModels;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mViewModels == null ? 0 : mViewModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;
        LinearLayout rootView;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            rootView = (LinearLayout) itemView.findViewById(R.id.root_view);
        }
    }
}