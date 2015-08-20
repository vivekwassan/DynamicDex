package com.dynamic.primary.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dynamic.callback.CallBackHandler;
import com.dynamic.callback.PaymentCallBackHandler;
import com.dynamic.dynamicdex.FlowListener;
import com.dynamic.dynamicdex.MainActivity;
import com.dynamic.dynamicdex.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OldShippingFragment extends Fragment {


    private FlowListener listener;

    public OldShippingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View fragmentView = inflater.inflate(R.layout.fragment_old_shipping, container, false);
        Button button = (Button) fragmentView.findViewById(R.id.old_shipping_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaymentCallBackHandler handler = new PaymentCallBackHandler();
            }
        });
        return fragmentView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        listener = new CallBackHandler();
    }

}
