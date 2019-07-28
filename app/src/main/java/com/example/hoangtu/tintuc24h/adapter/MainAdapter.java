package com.example.hoangtu.tintuc24h.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hoangtu.tintuc24h.R;
import com.example.hoangtu.tintuc24h.model.News;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private Context context;
    private ArrayList<News> arrNew = new ArrayList<>();
    private OnItemMainClickListenr onItemMainClickListenr;

    public MainAdapter(Context context) {
        this.context = context;
        initData();
    }

    private void initData() {
        arrNew.clear();
        arrNew.add(new News(R.drawable.kinhdoanh,"Kinh Doanh","https://vnexpress.net/rss/kinh-doanh.rss"));
        arrNew.add(new News(R.drawable.car,"Xe++","https://vnexpress.net/rss/oto-xe-may.rss"));
        arrNew.add(new News(R.drawable.dulich,"Du lịch","https://vnexpress.net/rss/du-lich.rss"));
        arrNew.add(new News(R.drawable.giaoduc,"Gíao dục","https://vnexpress.net/rss/giao-duc.rss"));
        arrNew.add(new News(R.drawable.khoahoc,"Khoa Học","https://vnexpress.net/rss/khoa-hoc.rss"));
        arrNew.add(new News(R.drawable.phapluat,"Pháp Luật","https://vnexpress.net/rss/phap-luat.rss"));
        arrNew.add(new News(R.drawable.suckhoe,"Sức Khỏe","https://vnexpress.net/rss/suc-khoe.rss"));
        arrNew.add(new News(R.drawable.thethao,"Thể Thao","https://vnexpress.net/rss/the-thao.rss"));
        arrNew.add(new News(R.drawable.thoisu,"Thời Sự","https://vnexpress.net/rss/thoi-su.rss"));

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_new,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            News news = arrNew.get(position);
            holder.imvImage.setImageResource(news.getImgResource());
            holder.tvNews.setText(news.getTitle());

    }

    @Override
    public int getItemCount() {
        return arrNew.size();
    }
    public News getItem(int position) {
        return arrNew.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imvImage;
        TextView tvNews;
        LinearLayout llClick;

        public ViewHolder(View itemView) {
            super(itemView);
            imvImage = itemView.findViewById(R.id.imvImage);
            tvNews = itemView.findViewById(R.id.tvItemNew);
            llClick = itemView.findViewById(R.id.ll_click);
            llClick.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.ll_click:
                    if (onItemMainClickListenr!=null){
                        onItemMainClickListenr.onClickItemMain(getAdapterPosition());
                    }
                    break;
            }
        }
    }

    public interface OnItemMainClickListenr {
        void onClickItemMain(int position);
    }
    public void setOnItemCaterogyClickListenr(OnItemMainClickListenr OnItemMainClickListenr) {
        this.onItemMainClickListenr = OnItemMainClickListenr;
    }
}
