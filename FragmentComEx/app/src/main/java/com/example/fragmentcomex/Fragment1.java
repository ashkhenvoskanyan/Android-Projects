package com.example.fragmentcomex;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Fragment1 extends Fragment {

    private EditText editText;
    private Button button;
    private TextView textView;
    private Listener1 listener1;

    public interface Listener1{

        void sendData1(CharSequence input);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_1, container, false);
        editText = v.findViewById(R.id.editText2);
        textView = v.findViewById(R.id.textView1);
        button = v.findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener1.sendData1(editText.getText());
            }
        });
        return  v;
    }

    public void updateText(CharSequence input){
        textView.setText(input);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener1 = (Listener1) context;
    }

}