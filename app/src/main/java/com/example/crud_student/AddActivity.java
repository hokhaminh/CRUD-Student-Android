package com.example.crud_student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crud_student.database.UserDatabase;

import java.util.List;

public class AddActivity extends AppCompatActivity {
    private TextView name;
    private TextView age;
    private TextView url;
    private Button btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initUi();

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUser();
            }
        });


    }

    private void initUi() {
        name = findViewById(R.id.addname);
        age= findViewById(R.id.addage);
        url =findViewById(R.id.addurl);
        btnCreate = findViewById(R.id.create);

    }

    private void addUser(){
        String username = name.getText().toString().trim();
        String userage = age.getText().toString().trim();
        String userurl = url.getText().toString().trim();

        if(TextUtils.isEmpty((username)) || TextUtils.isEmpty((userage)) ||TextUtils.isEmpty((userurl))){
            return;
        }

        User user = new User(username,userage,userurl);
        if(isUseExist(user)){
            Toast.makeText(this,"User exist",Toast.LENGTH_SHORT).show();
            return;
        }

        UserDatabase.getInstance(this).userDAO().insertUser(user);

        Toast.makeText(this,"Add user successfully",Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private boolean isUseExist(@NonNull User user){
        List<User> list = UserDatabase.getInstance(this).userDAO().checkUser(user.getName());
        return list!=null && !list.isEmpty();
    }
}