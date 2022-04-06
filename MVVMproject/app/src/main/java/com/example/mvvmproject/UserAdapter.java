package com.example.mvvmproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {
    private List<User> users;
    private OnItemClickListener listener;


    public UserAdapter(List<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.display_items, parent, false);
        return new UserHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        User currentUser = users.get(position);
        holder.name_surname.setText(currentUser.getName_surname());
        holder.phone_number.setText(currentUser.getPhoneNumber());
        holder.date_of_birth.setText(currentUser.getDateOfBirth());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setUsers(List<User> users) {
        this.users.clear();
        List<User> list = new ArrayList<>(users);
        this.users = list;

        notifyDataSetChanged();
    }

    public User getUserAt(int position) {
        return users.get(position);
    }

    public void removeItem(int position){
        users.remove(position);
        notifyItemRemoved(position);
    }

    class UserHolder extends RecyclerView.ViewHolder {

        private TextView name_surname;
        private TextView phone_number;
        private TextView date_of_birth;

        public UserHolder(@NonNull View itemView) {
            super(itemView);
            name_surname = itemView.findViewById(R.id.name_surname);
            phone_number = itemView.findViewById(R.id.phone_number);
            date_of_birth = itemView.findViewById(R.id.date_of_birth);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION)
                        listener.onItemClick(users.get(position));
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(User user);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
