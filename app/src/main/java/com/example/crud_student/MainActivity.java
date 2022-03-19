package com.example.crud_student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crud_student.database.UserDatabase;
import com.google.android.material.snackbar.Snackbar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

private List<User> userList;
private RecyclerView recyclerView;
private recyclerAdapter adapter;
private Menu menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUi();

        adapter = new recyclerAdapter(new recyclerAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                viewDetail(v, position);
            }
        });

        userList = new ArrayList<>();

        loadData();


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }



    private void viewDetail(View v, int position) {
        Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_user",userList.get(position));
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void initUi(){

        recyclerView = findViewById(R.id.recyclerView);
    }

    //dấu cộng trên thanh action
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main,menu);
        this.menu=menu;
//        inflater.inflate(R.menu.menu_activity_main, menu);
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id== R.id.create_user){
            Intent intent = new Intent(this, AddActivity.class);
            startActivity(intent);

        }
        return true;
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 121:
//                adapter.removeStudent(item.getGroupId());
                UserDatabase.getInstance(this).userDAO().deleteUser(userList.get(item.getGroupId()));
                displayMessage("Student is deleted!!!");
                loadData();
                return true;
            case 122:
                Intent intent = new Intent(this, EditActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("object_user",userList.get(item.getGroupId()));
                intent.putExtras(bundle);
                startActivity(intent);
                return true;
            default:
                return super.onContextItemSelected(item);
        }



    }

    private void displayMessage(String message){
        Snackbar.make(findViewById(R.id.rootView),message,Snackbar.LENGTH_SHORT).show();
    }
    private void loadData(){
        userList=UserDatabase.getInstance(this).userDAO().getListUser();
        adapter.setData(userList);
    }
}