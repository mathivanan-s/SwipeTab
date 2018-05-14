package com.example.mathi.swipetab;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Tab2 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View android =inflater.inflate(R.layout.tab2, container, false);
        TextView tv=(TextView)android.findViewById(R.id.tab2text);

        return android;

    }
}