package com.paopao.newservlet.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.paopao.newservlet.R;
import com.paopao.newservlet.domain.NewBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * 作者：paopao on 2018/12/5 20:37
 * <p>
 * 作用:ListView每条新闻的适配器
 */
public class NewsListAdapter extends BaseAdapter {
    private List<NewBean.DataBean> data;
    private Context context;

    public NewsListAdapter(List<NewBean.DataBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    public void setData(List<NewBean.DataBean> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        MyViewHolder holder;
        if(convertView==null){
            view=View.inflate(context, R.layout.item_new,null);
            holder=new MyViewHolder();
            holder.ivIcon=view.findViewById(R.id.iv_icon);
            holder.txTitle=view.findViewById(R.id.tv_title);
            holder.tvFrom=view.findViewById(R.id.tv_from);
            holder.tvDate=view.findViewById(R.id.tv_date);
            view.setTag(holder);
        }else {//复用convertView
            view=convertView;
            holder= (MyViewHolder) view.getTag();
        }

        NewBean.DataBean dataBean=data.get(position);
        holder.txTitle.setText(dataBean.getTitle());
        if(dataBean.getAuthor_name()==null){

            holder.tvFrom.setText("新闻来源");
        }else{
            holder.tvFrom.setText(dataBean.getAuthor_name());
        }
        holder.tvDate.setText(dataBean.getDate());
        if (!TextUtils.isEmpty(dataBean.getThumbnail_pic_s())){
            Picasso.with(context)
                    .load(dataBean.getThumbnail_pic_s())
                    .placeholder(R.drawable.loading_fail)
                    .error(R.drawable.loading_fail)
                    .into(holder.ivIcon);//图片
        }


        return view;
    }


    private static class MyViewHolder{
        ImageView ivIcon;
        TextView txTitle,tvFrom,tvDate;
    }
}
