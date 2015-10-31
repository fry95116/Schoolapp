package com.tomtootgb.schoolapp.model;

import android.app.DatePickerDialog;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by tomtoo on 2015/10/27.
 */
public abstract class Collection{

    private final int OBS_COUNT = 16;
    protected ArrayList<OnCollectionChangedListener> obs = new ArrayList<OnCollectionChangedListener>(OBS_COUNT);

    protected void notifyDataChanged(Model newval){
        Iterator<OnCollectionChangedListener> iter = obs.iterator();
        while(iter.hasNext()){
            iter.next().onchange(this, newval);
        }
    }

    protected void notifyDataAdded(Model newval){
        Iterator<OnCollectionChangedListener> iter = obs.iterator();
        while(iter.hasNext()){
            iter.next().onadd(this,newval);
        }
    }
    protected void notifyDataRemoved(Model removeval){
        Iterator<OnCollectionChangedListener> iter = obs.iterator();
        while(iter.hasNext()){
            iter.next().onremove(this,removeval);
        }
    }
    protected void notifyDataReseted(){
        Iterator<OnCollectionChangedListener> iter = obs.iterator();
        while(iter.hasNext()){
            iter.next().onReset(this);
        }
    }

    public boolean addOnCollectionChangedListener(OnCollectionChangedListener ob){
        if(obs.size() < OBS_COUNT){
            obs.add(ob);
            return true;
        }
        Log.d("com.tomtootgb.schoolapp.model", "obs list is full");
        return false;
    }

    public boolean removeOnCollectionChangedListener(OnCollectionChangedListener ob){
        if(obs.remove(ob)) return true;
        else {
            Log.d("com.tomtootgb.schoolapp.model", "cannot find ob ");
            return false;
        }
    }

}
