package com.example.crud_student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crud_student.database.UserDatabase;

import java.util.ArrayList;

public class EditActivity extends AppCompatActivity {
    private User editUser;
    private TextView name;
    private TextView age;
    private TextView url;
    private Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Bundle extras = getIntent().getExtras();

        name = findViewById(R.id.editname);
        age = findViewById(R.id.editage);
        url = findViewById(R.id.editurl);
        btnUpdate= findViewById(R.id.update);

        editUser = (User) getIntent().getExtras().get("object_user");
        if(editUser!=null){
            name.setText(editUser.getName());
            age.setText(editUser.getAge());
            url.setText(editUser.getUrl());
        }
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUser();
            }
        });
    }

    private void updateUser() {
        String strUsername = name.getText().toString().trim();
        String strAge = age.getText().toString().trim();
        String strUrl = url.getText().toString().trim();

        if(TextUtils.isEmpty((strUsername)) || TextUtils.isEmpty((strAge)) ||TextUtils.isEmpty((strUrl))){
            return;
        }
        editUser.setName(strUsername);
        editUser.setAge(strAge);
        editUser.setUrl(strUrl);
        UserDatabase.getInstance(this).userDAO().updateUser(editUser);
        Toast.makeText(this,"User updated",Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);


    }

}