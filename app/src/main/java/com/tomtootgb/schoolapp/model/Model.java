package com.tomtootgb.schoolapp.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by tomtoo on 2015/10/27.
 */
public abstract class Model {
    private final int OBS_COUNT = 16;
    protected ArrayList<OnModelChangedListener> obs = new ArrayList<OnModelChangedListener>(OBS_COUNT);

    public boolean addOnModelChangedListener(OnModelChangedListener ob){
        if(obs.size() < OBS_COUNT){
            obs.add(ob);
            return true;
        }
        Log.d("com.tomtootgb.schoolapp.model","obs list is full");
        return false;
    }

    public boolean removeOnModelChangedListener(OnModelChangedListener ob){
        if(obs.remove(ob)) return true;
        else {
            Log.d("com.tomtootgb.schoolapp.model", "cannot find ob ");
            return false;
        }
    }

    protected void notifyModelChanged(){
        Iterator<OnModelChangedListener> iter = obs.iterator();
        while(iter.hasNext()){
            iter.next().onchange(this);
        }
    }

}
