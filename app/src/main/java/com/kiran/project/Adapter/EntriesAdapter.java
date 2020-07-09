package com.kiran.project.Adapter;


import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.kiran.project.R;
import com.kiran.project.pojo.EntriesPojo;

import java.util.ArrayList;


public class EntriesAdapter extends RecyclerView.Adapter<EntriesAdapter.MyViewHolder> {
    private LayoutInflater inflater;
    Activity context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String from;
    MyViewHolder holder;
    public ArrayList<EntriesPojo> data = new ArrayList<EntriesPojo>();

    public EntriesAdapter(Activity context, ArrayList<EntriesPojo> data) {
        this.context = context;
        this.from=from;
        inflater = context.getLayoutInflater();
        this.data = data;
    }

    public void delete(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        System.out.println("con=" + context);
        view = inflater.inflate(R.layout.item_recycler, parent, false);
        holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

            holder.tv_title.setText(data.get(position).getTitle());
            holder.ll_main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
showDialog(context,data.get(position).getTitle(),data.get(position).getPassword(),data.get(position).getDescription());
                }
            });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        LinearLayout ll_main;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv_title =  itemView.findViewById(R.id.tv_title);
            ll_main=itemView.findViewById(R.id.ll_main);
        }
    }
    void showDialog(Activity context,String tit,String pwd,String desc) {
        final Dialog sucessDialog = new Dialog(context);
        sucessDialog.setCancelable(true);
        final LayoutInflater inflater = context.getLayoutInflater();
        View vv = inflater.inflate(R.layout.dialog_show_detail, (ViewGroup) context.findViewById(R.id.ll_main), false);
        TextView tv_ok = vv.findViewById(R.id.tv_ok);
        TextView tv_title = vv.findViewById(R.id.tv_title);
        TextView tv_pwd = vv.findViewById(R.id.tv_pwd);
        TextView tv_desc = vv.findViewById(R.id.tv_desc);
        tv_title.setText("Title :"+tit);
        tv_pwd.setText("Password :"+pwd);
        tv_desc.setText("Desciption :"+desc);
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sucessDialog.dismiss();
            }
        });
        sucessDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        sucessDialog.setContentView(vv);
        sucessDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        sucessDialog.show();
    }
}
