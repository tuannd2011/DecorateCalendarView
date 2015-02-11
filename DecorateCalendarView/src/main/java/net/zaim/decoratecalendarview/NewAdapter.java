package net.zaim.decoratecalendarview;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NewAdapter extends RecyclerView.Adapter<NewAdapter.ViewHolder> {

    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_ITEM = 1;

    private static final String[] DAY_OF_WEEK = {"日","月","火","水","木","金","土"};

    private Context mContext;
    private LayoutInflater mInflater;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mWeekTextView;
        private TextView mDayTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mWeekTextView = (TextView) itemView.findViewById(R.id.week_text);
            mDayTextView = (TextView) itemView.findViewById(R.id.day_text);
        }

        public TextView getWeekTextView() {
            return mWeekTextView;
        }

        public TextView getDayTextView() {
            return mDayTextView;
        }
    }

    public NewAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutResId = viewType == VIEW_TYPE_HEADER ? R.layout.week_item : R.layout.day_item;
        View itemView = LayoutInflater.from(parent.getContext()).inflate(layoutResId, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case VIEW_TYPE_HEADER:
                holder.getWeekTextView().setText(DAY_OF_WEEK[position]);
                break;
            case VIEW_TYPE_ITEM:
                holder.getDayTextView().setText(String.valueOf(position - 7 + 1));
                if (position % 7 == 0) holder.getDayTextView().setTextColor(Color.RED);
                if (position % 7 == 6) holder.getDayTextView().setTextColor(Color.BLUE);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return (position / 7 == 0) ? VIEW_TYPE_HEADER : VIEW_TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return 31 + 7;
    }
}
