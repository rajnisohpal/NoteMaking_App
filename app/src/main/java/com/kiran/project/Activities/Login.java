package com.kiran.project.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kiran.project.R;
import com.kiran.project.Utils.DatabaseHandler;


public class Login extends AppCompatActivity {
Button btn_login;
DatabaseHandler db;
EditText et_unm,et_pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(db.getUsers())
                if(et_unm.getText().length()==0)
                    Toast.makeText(Login.this, "Please enter Username ", Toast.LENGTH_SHORT).show();
                else if (et_pwd.getText().length()==0)
                    Toast.makeText(Login.this, "Please enter Password ", Toast.LENGTH_SHORT).show();
                else if (!db.IsUserExist(et_unm.getText().toString(),et_pwd.getText().toString()))
                    Toast.makeText(Login.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                else
                {
                Intent i=new Intent(Login.this, Home.class);
                i.putExtra("name",et_unm.getText().toString());
                startActivity(i);
                finish();
                }



            }
        });
    }
    void init()
    {
        db = new DatabaseHandler(this);
        btn_login=findViewById(R.id.btn_login);
        et_unm=findViewById(R.id.et_unm);
        et_pwd=findViewById(R.id.et_pwd);
    }
}
