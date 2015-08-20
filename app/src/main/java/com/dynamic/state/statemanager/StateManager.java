package com.dynamic.state.statemanager;

import com.dynamic.state.AppState;

/**
 * Created by vivek on 18/8/15.
 */
public class StateManager {

    private static final StateManager manager = new StateManager();

    private StateManager(){

    }

    public static StateManager getInstance(){
        return manager;
    }

    public AppState getNextState(AppState current){
        if(current == AppState.CART){
            return AppState.SHIPPING;
        } else if (current == AppState.SHIPPING){
            return AppState.PAYMENT;
        }
        return AppState.CART;
    }
}
