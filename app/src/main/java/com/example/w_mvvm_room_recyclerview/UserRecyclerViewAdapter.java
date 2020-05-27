package com.example.w_mvvm_room_recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserRecyclerViewAdapter.UserViewHolder> {
    private List<User> mData = new ArrayList<>();


    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item,parent,false);
        UserViewHolder userViewHolder = new UserViewHolder(view);
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
            holder.txtTitle.setText(mData.get(position).getTitle());
            holder.txtDescription.setText(mData.get(position).getDescription());
            holder.txtPriority.setText(String.valueOf(mData.get(position).getPriority()));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setUsers(List<User> users){
        this.mData = users;
        notifyDataSetChanged();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder{
        private TextView txtTitle,txtDescription,txtPriority;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            txtPriority = itemView.findViewById(R.id.txtPriority);
        }
    }
}
