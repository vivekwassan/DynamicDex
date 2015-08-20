package com.dynamic.callback;

import com.dynamic.dynamicdex.FlowListener;
import com.dynamic.dynamicdex.MainActivity;

/**
 * Created by vivek on 18/8/15.
 */
public class CallBackHandler implements FlowListener {

    private static MainActivity activity = null;

    public static void setMainActivity(MainActivity activity1){
        activity = activity1;
    }

    @Override
    public void callShipping(){
        activity.callShipping();
    }

    @Override
    public void callPayment(){
        activity.callPayment();
    }
}
