package com.dynamic.dynamicdex;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.Toast;

import com.dynamic.callback.CallBackHandler;
import com.dynamic.dynamicdex.utils.DynamicDexUtils;
import com.dynamic.primary.fragment.CartFragment;
import com.dynamic.primary.fragment.OldPaymentFragment;
import com.dynamic.primary.fragment.OldShippingFragment;
import com.dynamic.state.AppState;


public class MainActivity extends Activity {

    private static boolean isDynamicShippingEnabled = true;
    private static boolean isDynamicPaymentEnabled = true;

    private static String NEW_SHIPPING_PAGE = "com.dynamic.seconddex.NewShippingFragment";
    private static String NEW_PAYMENT_PAGE = "com.dynamic.seconddex.NewPaymentFragment";


    private AppState currentState;

    public AppState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(AppState currentState) {
        this.currentState = currentState;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CallBackHandler.setMainActivity(this);
        CartFragment cartFragment = new CartFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, cartFragment);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
        setCurrentState(AppState.CART);

    }

    public void callShipping() {
        if (isDynamicShippingEnabled) {
            Fragment fragment = DynamicDexUtils.loadSecondaryDex(this, NEW_SHIPPING_PAGE);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, fragment);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
            Toast.makeText(getApplicationContext(), "New Shipping", Toast.LENGTH_LONG).show();
            setCurrentState(AppState.SHIPPING);
        } else {
            Toast.makeText(getApplicationContext(), "Old Shipping", Toast.LENGTH_LONG).show();
            OldShippingFragment oldShippingFragment = new OldShippingFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, oldShippingFragment);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
            setCurrentState(AppState.SHIPPING);
        }
    }

    public void callPayment() {
        if (isDynamicPaymentEnabled) {
            Fragment fragment = DynamicDexUtils.loadSecondaryDex(this, NEW_PAYMENT_PAGE);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, fragment);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
            Toast.makeText(getApplicationContext(), "New Payment", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(getApplicationContext(), "Old Payment", Toast.LENGTH_LONG).show();
            OldPaymentFragment oldPaymentFragment = new OldPaymentFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, oldPaymentFragment);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
            setCurrentState(AppState.PAYMENT);

        }
    }
}
