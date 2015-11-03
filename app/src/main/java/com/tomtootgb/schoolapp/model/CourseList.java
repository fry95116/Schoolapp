package com.tomtootgb.schoolapp.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by tomtoo on 2015/10/27.
 */

/*
* 课程表
* 是个单例类
* */

public class CourseList extends Collection{

    private static CourseList instance = null;

    public CourseList getInstance(){
        if(instance == null) instance = new CourseList();
        return instance;
    }

    private Course static_courseList[] = null;
    private ArrayList<Course> myCourseList = new ArrayList<Course>();
    private OnModelChangedListener listener = null;

    private CourseList(){
        this.listener = new OnModelChangedListener() {
            @Override
            public void onchange(Model newval) {
                CourseList.this.notifyDataChanged(newval);
            }
        };
    }


    public boolean AddCourse(Course c){
        c.addOnModelChangedListener(listener);
        boolean succeed = myCourseList.add(c);
        if(succeed) notifyDataAdded(c);
        return succeed;
    }

    public Iterator<Course> getStaticCourseIterator(){
        return new StaticCourseIterator();
    }

    public Iterator<Course> getMyCourseIterator(){
        return new MyCourseIterator();
    }

    private class StaticCourseIterator implements Iterator<Course>{

        private int courrent_index = 0;

        @Override

        public boolean hasNext() {
            if(courrent_index < static_courseList.length) return true;
            return false;
        }

        @Override
        public Course next() {
            if(courrent_index < static_courseList.length) return static_courseList[courrent_index].deepClone();
            return null;
        }

        @Override
        public void remove() {}
    }

    private class MyCourseIterator implements Iterator<Course>{

        private Course rm_flag = null;
        private Iterator<Course> iter = iter = myCourseList.iterator();

        @Override

        public boolean hasNext() {
            return iter.hasNext();
        }

        @Override
        public Course next() {
            rm_flag = iter.next();
            return rm_flag;
        }

        @Override
        public void remove() {
            if(rm_flag != null) notifyDataRemoved(rm_flag);
            iter.remove();
        }
    }

    //attention:为了照顾性能，只是浅拷贝
    //不建议在 外部对实参指向的对象做修改
    //仅限本包访问

    void resetStaticCourseList(Course[] static_courseList) {
        this.static_courseList = static_courseList;
        notifyDataReseted();
    }

    void resetMyCourseList(ArrayList<Course> mcl){
        this.myCourseList = mcl;
        notifyDataReseted();
    }

}
