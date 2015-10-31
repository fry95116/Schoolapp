package com.tomtootgb.schoolapp.model;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by tomtoo on 2015/10/28.
 */

//记录上课时间信息
//创建后无法更改
public class Schedule_item implements Cloneable{


    /*
    * 枚举体们
    * Weekday 表示星期
    * ClassIndex 表示上课节次
    * */

/*
    public static enum Weekday{
        Mon(1),Tue(2),Wed(3),Thu(4),Fri(5),Sat(6),Sun(7);
        private int value;

        private Weekday(int value){
            this.value = value;
        }

        public static Weekday valueOf(int value) {    //    手写的从int到enum的转换函数
            switch (value) {
                case 1:
                    return Mon;
                case 2:
                    return Tue;
                case 3:
                    return Wed;
                case 4:
                    return Thu;
                case 5:
                    return Fri;
                case 6:
                    return Sat;
                case 7:
                    return Sun;
                default:
                    return null;
            }
        }

        public int value() {
            return this.value;
        }
    }


    public static enum ClassIndex{
        AM_Fir(1),AM_Sec(2),PM_Fir(3),PM_Sec(4),Evening(5);

        private int value;

        private ClassIndex(int value){
            this.value = value;
        }

        public static ClassIndex valueOf(int value) {    //    手写的从int到enum的转换函数
            switch (value) {
                case 1:
                    return AM_Fir;
                case 2:
                    return AM_Sec;
                case 3:
                    return PM_Fir;
                case 4:
                    return PM_Sec;
                case 5:
                    return Evening;
                default:
                    return null;
            }
        }

        public int value() {
            return this.value;
        }
    }
*/
    private HashSet<Integer> weeks = null;
    private int weekday;
    private int class_index;
    private int hash = 0;


    public Schedule_item(int[] weeks, int weekday, int class_index) {
        this.weeks = new HashSet<Integer>();
        for(int i = 0;i < weeks.length;++i) {
            this.weeks.add(weeks[i]);
        }
        if(weekday>=1 && weekday <=7)this.weekday = weekday;
        else this.weekday = 1;
        if(class_index>=1 && class_index<=5)this.class_index = class_index;
        else this.class_index = 1;
    }

    //用于深拷贝
    protected Schedule_item(Schedule_item sch){
        this.class_index =sch.class_index;
        this.weekday = sch.weekday;

        this.weeks = new HashSet<Integer>();

        Iterator<Integer> weeks_iter = sch.weeks.iterator();
        while(weeks_iter.hasNext()){
            int i = weeks_iter.next();
            this.weeks.add(i);
        }
    }

    public int getWeekday() {
        return weekday;
    }

    public int getClass_index() {
        return class_index;
    }

    public int[] getWeeks(){
        int re[] = new int[this.weeks.size()];
        int i = 0;
        Iterator<Integer> iter = weeks.iterator();
        while(iter.hasNext()){
            re[i] = iter.next();
        }
        return re;
    }

    //深拷贝
    public Schedule_item deepClone() {
        return new Schedule_item(this);
    }

    @Override
    public int hashCode() {
        if(hash != 0) return hash;

        hash = weekday;
        hash = 31 * hash + class_index;

        Iterator<Integer> weeks_iter = weeks.iterator();
        while(weeks_iter.hasNext()){
            hash = 31 * hash + weeks_iter.next();
        }

        return hash;

    }

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
        if(weeks.size()!= sch.weeks.size())return false;

        Iterator<Integer> iter = weeks.iterator();
        while(iter.hasNext()){
            if(!sch.weeks.contains(iter.next())) return false;
        }
        return true;
    }
};
