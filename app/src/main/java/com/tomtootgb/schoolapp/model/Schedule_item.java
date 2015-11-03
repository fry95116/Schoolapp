package com.tomtootgb.schoolapp.model;

import android.support.v7.app.AlertDialog;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by tomtoo on 2015/10/28.
 */

//记录上课时间信息
//创建后无法更改
public class Schedule_item implements Cloneable{

    private int[] weeks;
    public final int weekday;
    public final int class_index;
    private final int hash;

    public static class Builder{
        private HashSet<Integer> weeks = null;
        private int weekday;
        private int class_index;

        public Builder(int weekday,int class_index){
            this.weekday = weekday;
            this.class_index = class_index;
            this.weeks = new HashSet<Integer>();
        }

        public void addWeeks(int... weeks) {
            for (int i : weeks) {
                this.weeks.add(i);
            }
        }
        public Schedule_item build(){
            return new Schedule_item(this);
        }
    }

    protected Schedule_item(Builder b){
        this.weekday = b.weekday;
        this.class_index = b.class_index;

        this.weeks = new int[b.weeks.size()];
        Iterator<Integer> iter = b.weeks.iterator();
        int i = 0;
        while(iter.hasNext()){
            this.weeks[i] = iter.next();
            ++i;
        }
        Arrays.sort(this.weeks);
        //hash
        int h = 0;
        h = weekday;
        h = 31 * h + class_index;
        for(i = 0; i < this.weeks.length; ++i){
            h = 31 * h + this.weeks[i];
        }
        this.hash = h;
    }

    //用于深拷贝
    protected Schedule_item(Schedule_item sch){
        this.class_index =sch.class_index;
        this.weekday = sch.weekday;
        this.weeks = weeks.clone();
        this.hash = sch.hash;
    }

    public int[] getWeeks(){
        return this.weeks.clone();
    }

    //深拷贝
    public Schedule_item deepClone() {
        return new Schedule_item(this);
    }

    @Override
    public int hashCode() {return hash;}

    @Override
    public boolean equals(Object o) {
        //same instance
        if (this == o) return true;
        //null instance
        if (o == null) return false;
        //different class
        if (!(o instanceof Schedule_item)) return false;
        //cast class
        Schedule_item sch = (Schedule_item)o;

        if(sch.weekday != weekday || sch.class_index != class_index) return false;

        //因为在构造函数中已经排序，此处不需要再次排序
        return Arrays.equals(this.weeks,sch.weeks);
    }
};
