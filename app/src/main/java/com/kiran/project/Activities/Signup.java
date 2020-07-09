package com.kiran.project.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kiran.project.R;
import com.kiran.project.Utils.DatabaseHandler;
import com.kiran.project.Utils.DatabaseHandler;

public class Signup extends AppCompatActivity {
    DatabaseHandler db;
    TextView tv_signup;
    EditText et_unm,et_pwd,et_con_pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        init();

        tv_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_unm.getText().length()==0)
                    Toast.makeText(getApplicationContext(),"Enter Username",Toast.LENGTH_LONG).show();
                else if (et_pwd.getText().length()==0)
                    Toast.makeText(getApplicationContext(),"Enter Password",Toast.LENGTH_LONG).show();
                else if (et_pwd.getText().length()<3)
                    Toast.makeText(getApplicationContext(),"Password must have atleast 3 characters",Toast.LENGTH_LONG).show();
                else if (et_con_pwd.getText().length()==0)
                    Toast.makeText(getApplicationContext(),"Enter confirm Password",Toast.LENGTH_LONG).show();
                else if (!et_pwd.getText().toString().trim().equals(et_con_pwd.getText().toString().trim()))
                    Toast.makeText(getApplicationContext(),getApplicationContext().getResources().getString(R.string.pwd_mismatch),Toast.LENGTH_LONG).show();
                else
                register();

            }
        });
//        ll_login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i=new Intent(Signup.this, Login.class);
//                startActivity(i);
//
//            }
//        });
    }
    void init()
    {
        db = new DatabaseHandler(this);
        et_con_pwd=findViewById(R.id.et_con_pwd);
        et_unm=findViewById(R.id.et_unm);
        et_pwd=findViewById(R.id.et_pwd);
        tv_signup=findViewById(R.id.tv_signup);
    }
    void register()
    {
//        if(db.checkUser())
//        {
//            Toast.makeText(getApplicationContext(), "USER ALREADY EXITS", Toast.LENGTH_LONG).show();
//        }
//        else
//        {
            Long result = db.insertUser(et_unm.getText().toString(),et_pwd.getText().toString());
            if(result>0)
            {
                Intent i=new Intent(Signup.this, Login.class);
                startActivity(i);
                finish();

            }
//        }




    }
}
