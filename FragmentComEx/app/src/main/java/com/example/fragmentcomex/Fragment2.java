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

public class Fragment2 extends Fragment {

    private EditText editText;
    private Button button;
    private TextView textView;
    private Listener2 listener2;

    public interface Listener2 {

        void sendData2(CharSequence input);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_2, container, false);
        editText = v.findViewById(R.id.editText2);
        textView = v.findViewById(R.id.textView2);
        button = v.findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener2.sendData2(editText.getText());
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
        listener2 = (Listener2) context;
    }

}