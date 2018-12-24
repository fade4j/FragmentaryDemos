package com.spacecowboy.fragmentarydemos.customview.avatar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.spacecowboy.fragmentarydemos.R;

/**
 * @author: SpaceCowboy
 * @date: 2018/1/13
 * @description:
 */

public class AvatarPagerFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_avatar_fragment, container, false);
        TextView textView = (TextView) view.findViewById(R.id.tv_position);
        textView.setText(""+getArguments().getInt("POS"));
        return view;
    }
}
