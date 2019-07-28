package com.example.hoangtu.tintuc24h.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.hoangtu.tintuc24h.R;
import com.example.hoangtu.tintuc24h.model.Item;

import java.util.ArrayList;

public class NewAdapter extends RecyclerView.Adapter<NewAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private ArrayList<Item> arrItem;
    private OnItemNewsClickListener onItemNewsClickListener;

    public NewAdapter(ArrayList<Item> arrItem, Context context) {
        this.arrItem=arrItem;
        layoutInflater=LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.layout_item_new,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Item item = arrItem.get(position);
            holder.bindData(item);
    }

    @Override
    public int getItemCount() {
        return arrItem.size();
    }
    public Item getItem(int position) {
        return arrItem.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView im_news;
        private TextView tv_title;
        private TextView tv_decs;
        private TextView tv_date;
        private LinearLayout lclick;
        public ViewHolder(View itemView) {
            super(itemView);
            im_news = itemView.findViewById(R.id.im_news);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_decs = itemView.findViewById(R.id.tv_desc);
            tv_date=itemView.findViewById(R.id.tv_date);
            lclick=itemView.findViewById(R.id.lclick);
            lclick.setOnClickListener(this);
        }
        public void bindData(Item item) {
            Log.e(getClass().getName(), item.getImg() + "--");
            Glide.with(itemView.getContext())
                    .load(item.getImg())
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(im_news);
            tv_title.setText(item.getTitle());
            tv_date.setText(item.getPubDate());
            tv_decs.setText(item.getDesc());
        }

        @Override
        public void onClick(View view) {
            if (onItemNewsClickListener != null) {
                onItemNewsClickListener.onClickItemNews(getAdapterPosition());
            }

        }
    }



    public interface OnItemNewsClickListener {
        void onClickItemNews(int position);
    }

    public void setOnItemNewsClickListener(OnItemNewsClickListener onItemNewsClickListener) {
        this.onItemNewsClickListener = onItemNewsClickListener;
    }
}
