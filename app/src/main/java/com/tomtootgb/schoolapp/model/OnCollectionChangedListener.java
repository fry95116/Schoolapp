package com.tomtootgb.schoolapp.model;

/**
 * Created by tomtoo on 2015/10/27.
 */
public interface OnCollectionChangedListener {
    public void onchange(Collection c, Model newval);
    public void onadd(Collection c, Model newval);
    public void onremove(Collection c, Model removeval);
    public void onReset(Collection c);
}
