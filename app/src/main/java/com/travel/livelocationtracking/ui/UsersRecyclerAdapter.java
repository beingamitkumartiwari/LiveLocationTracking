package com.travel.livelocationtracking.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.travel.livelocationtracking.R;
import com.travel.livelocationtracking.db.UserEntity;

import java.util.List;

public class UsersRecyclerAdapter extends RecyclerView.Adapter<UsersRecyclerAdapter.UserViewHolder> {

    private List<UserEntity> listUsers;
    private final LayoutInflater mInflater;

    UsersRecyclerAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_recycler, parent, false);

        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.textViewName.setText(listUsers.get(position).getUserName());
        holder.textViewEmail.setText(listUsers.get(position).getUserEmail());
        holder.textViewPassword.setText(listUsers.get(position).getUserPassword());
    }

    @Override
    public int getItemCount() {
        if (listUsers != null)
            return listUsers.size();
        else return 0;
    }

    void setWords(List<UserEntity> userEntities) {
        listUsers = userEntities;
        notifyDataSetChanged();
    }

    /**
     * ViewHolder class
     */
    class UserViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView textViewName;
        AppCompatTextView textViewEmail;
        AppCompatTextView textViewPassword;

        UserViewHolder(View view) {
            super(view);
            textViewName = view.findViewById(R.id.textViewName);
            textViewEmail = view.findViewById(R.id.textViewEmail);
            textViewPassword = view.findViewById(R.id.textViewPassword);
        }
    }


}
