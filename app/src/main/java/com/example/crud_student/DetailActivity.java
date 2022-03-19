package com.example.crud_student;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    private  User user;
    private TextView name;
    private TextView age;
    private ImageView avatar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle extras = getIntent().getExtras();
        user = (User) getIntent().getExtras().get("object_user");

        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        avatar= findViewById(R.id.avatar);

        name.setText(user.getName());
        age.setText(user.getAge());
        Picasso.get().load(user.getUrl()).into(avatar);
    }
}