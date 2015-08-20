package com.dynamic.primary.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dynamic.dynamicdex.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OldPaymentFragment extends Fragment {


    public OldPaymentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_old_payment, container, false);
    }


}
