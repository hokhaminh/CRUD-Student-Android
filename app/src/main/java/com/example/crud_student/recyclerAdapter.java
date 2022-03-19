package com.example.crud_student;


import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder> {
    private List<User> userList;
    private RecyclerViewClickListener listener;

    public void setData(List<User> list){
        this.userList = list;
        notifyDataSetChanged();
    }
    public recyclerAdapter(RecyclerViewClickListener listener){
        this.listener = listener;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, View.OnClickListener{
        private TextView nametxt;
        private TextView agetxt;
        private ImageView avatar;
        private Button btnDetail;
        private ConstraintLayout constraintLayout;

        public MyViewHolder(final View view) {
            super(view);
            nametxt = view.findViewById(R.id.name);
            agetxt = view.findViewById(R.id.age);
            avatar= view.findViewById(R.id.avatar);
            btnDetail = view.findViewById(R.id.buttondetail);
            constraintLayout = view.findViewById(R.id.CardView);
            constraintLayout.setOnCreateContextMenuListener(this);
            view.setOnClickListener(this);


        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.setHeaderTitle("Select an option");
            contextMenu.add(this.getAdapterPosition(), 122, 0, "Edit");
            contextMenu.add(this.getAdapterPosition(), 121, 1, "Delete");
        }


        @Override
        public void onClick(View view) {
listener.onClick(view, getAdapterPosition());
        }
    }



    @NonNull
    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MyViewHolder holder, int position) {
         User user = userList.get(position);
        if(user ==null){
            return;
        }
    holder.nametxt.setText(user.getName());
    holder.agetxt.setText(user.getAge());
    Picasso.get().load(user.getUrl()).into(holder.avatar);

    holder.btnDetail.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            listener.onClick(view,position);

        }
    });

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }


}
