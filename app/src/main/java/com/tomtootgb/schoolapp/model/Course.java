package com.tomtootgb.schoolapp.model;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by tomtoo on 2015/10/27.
 */
public class Course extends Model {

    //构造器
    protected Course(Course c){
        this.name = c.name;
        this.teacher = c.teacher;
        this.schedule = new HashSet<Schedule_item>();
        for(Schedule_item i : c.schedule){
            this.schedule.add(i.deepClone());
        }
    }

    public Course(String name,String teacher){
        this.name = name != null ? name : "";
        this.teacher = teacher != null ? teacher : "";
        this.schedule = new HashSet<Schedule_item>();
    }

    public Course(String name, String teacher, Schedule_item[] sch){
        this.name = name != null ? name : "";
        this.teacher = teacher != null ? teacher : "";
        this.schedule = new HashSet<Schedule_item>();
        for(int i = 0;i < sch.length; ++i){
            this.schedule.add(sch[i].deepClone());
        }
    }


    //属性
    private String name;
    private String teacher;
    private HashSet<Schedule_item> schedule = null;

    public String getName() {
        return name;
    }
    public String getTeacher() {
        return teacher;
    }

    //触发onchange事件
    public void setTeacher(String teacher) {
        this.teacher = teacher != null ? teacher : "";
        notifyModelChanged();
    }

    //触发onchange事件
    public void setName(String name) {
        this.name = name != null ? name : "";
        notifyModelChanged();
    }

    //触发onchange事件
    public boolean addSchedule(Schedule_item sch){
        boolean succeed = schedule.add(sch);
        if(succeed) notifyModelChanged();
        return succeed;
    }

    public Iterator<Schedule_item> iterator(){
        return new SchIterator();
    }

    private class SchIterator implements Iterator<Schedule_item>{

        private Iterator<Schedule_item> iter = schedule.iterator();

        @Override
        public boolean hasNext() {
            return iter.hasNext();
        }

        @Override
        public Schedule_item next() {
            return iter.next();
        }

        @Override
        //触发onchange事件
        public void remove() {
            iter.remove();
            notifyModelChanged();
        }
    }

    public Course deepClone(){
        return new Course(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o ==null) return false;
        if (!(o instanceof Course)) return false;

        Course course = (Course) o;

        if (!name.equals(course.name)) return false;
        if (!teacher.equals(course.teacher)) return false;

        if (this.schedule.size() != course.schedule.size()) return false;
        Iterator<Schedule_item> i = this.schedule.iterator();
        while(i.hasNext()){
            if(!course.schedule.contains(i.next())) return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + teacher.hashCode();

        Iterator<Schedule_item> i = this.schedule.iterator();
        while(i.hasNext()){
            result = 31 * result + i.next().hashCode();
        }

        return result;
    }
}
