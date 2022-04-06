package com.example.idramproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ButtonAdapter extends RecyclerView.Adapter<ButtonAdapter.ViewHolder>{

    private List<ButtonClass> buttonList;

    public ButtonAdapter(List<ButtonClass>buttonList){

        this.buttonList = buttonList;
    }

    @NonNull
    @Override
    public ButtonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.button,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ButtonAdapter.ViewHolder holder1, int position1) {

        int image1 = buttonList.get(position1).getImage1();
        int image2 = buttonList.get(position1).getImage2();
        String text = buttonList.get(position1).getText();

        holder1.setData1(image1,image2,text);

    }

    @Override
    public int getItemCount() {
        return buttonList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageview1;
        private ImageView imageview2;
        private TextView textview;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            imageview1 = itemView.findViewById(R.id.image1);
            imageview2 = itemView.findViewById(R.id.image2);
            textview = itemView.findViewById(R.id.text);
        }


        public void setData1(int image1, int image2, String text) {
            imageview1.setImageResource(image1);
            imageview2.setImageResource(image2);
            textview.setText(text);
        }
    }


}
