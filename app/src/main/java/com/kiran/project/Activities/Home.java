package com.kiran.project.Activities;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kiran.project.Adapter.EntriesAdapter;
import com.kiran.project.R;
import com.kiran.project.Utils.DatabaseHandler;
import com.kiran.project.pojo.EntriesPojo;

import java.util.ArrayList;

@SuppressLint("Registered")
public class Home extends AppCompatActivity {
ImageView iv_add,iv_menu;
TextView tv_unm;
EntriesAdapter adapter;
LinearLayout drawer;
DrawerLayout drawer_main;
RecyclerView recycler_entries;
String unm;
ArrayList<EntriesPojo>data=new ArrayList<>();
    DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        getAllData();
        iv_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawer_main.isDrawerOpen(drawer))
                drawer_main.closeDrawer(drawer);
                else
                    drawer_main.openDrawer(drawer);
            }
        });
        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(getApplicationContext());
            }
        });
    }

    void getAllData()
    {
        data=db.getEntries();
        adapter = new EntriesAdapter(Home.this,data);
        recycler_entries.setLayoutManager((new LinearLayoutManager(Home.this, LinearLayoutManager.VERTICAL, false)));
        recycler_entries.setAdapter(adapter);

    }
    void init()
    {


        db = new DatabaseHandler(this);
        tv_unm=findViewById(R.id.tv_unm);
        iv_add=findViewById(R.id.iv_add);
        iv_menu=findViewById(R.id.iv_menu);
        drawer=findViewById(R.id.drawer);
        drawer_main=findViewById(R.id.drawer_main);
        recycler_entries=findViewById(R.id.recycler_entries);
        if (getIntent()!=null)
        {
            unm=getIntent().getStringExtra("name");
            tv_unm.setText(""+unm);

        }
        else {
            tv_unm.setText("");
        }
    }

    void showDialog(final Context context) {
        final Dialog sucessDialog;
        sucessDialog = new Dialog(Home.this);
        LayoutInflater inflater = Home.this.getLayoutInflater();
        View vv = inflater.inflate(R.layout.dialog_add_detail, null, false);

//        final Dialog sucessDialog = new Dialog(Home.this);
//        final LayoutInflater inflater = getLayoutInflater();
//        View vv = inflater.inflate(R.layout.dialog_add_detail, (ViewGroup) findViewById(R.id.ll_main), false);
        TextView tv_add=vv.findViewById(R.id.tv_add);
        final EditText et_title=vv.findViewById(R.id.et_title);
        final EditText et_pwd=vv.findViewById(R.id.et_pwd);
        final EditText et_desc=vv.findViewById(R.id.et_desc);

        tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (et_title.getText().length()==0)
                    Toast.makeText(context,"Please enter Title",Toast.LENGTH_LONG).show();
                else if (et_pwd.getText().length()==0)
                    Toast.makeText(context,"Please enter Password",Toast.LENGTH_LONG).show();
                else if (et_desc.getText().length()==0)
                    Toast.makeText(context,"Please enter Description",Toast.LENGTH_LONG).show();
                else
                {
                    Long result = db.insertEntry(et_title.getText().toString(),et_pwd.getText().toString(),et_desc.getText().toString());
                    if(result>0)
                    {
                        getAllData();
                        sucessDialog.dismiss();

                    }
                }

            }
        });
        sucessDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        sucessDialog.setContentView(vv);
        sucessDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        sucessDialog.show();
    }
}
