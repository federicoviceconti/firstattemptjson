package com.example.personale.firstjsonattempt.controller.list;

import com.example.personale.firstjsonattempt.model.Student;

import java.util.ArrayList;

/**
 * Created by personale on 27/02/2017.
 */

public class StudentList {
    private ArrayList<Student> students;

    public StudentList(){
        students = new ArrayList<>();
    }

    public void addStudent(Student s){
        students.add(s);
    }

    public void editStudent(int pos, Student s){
        students.set(pos, s);
    }

    public void removeStudent(int pos){
        if(pos < students.size())
            students.remove(pos);
    }

    public ArrayList<Student> getStudents(){
        return students;
    }

    public Student getStudent(int pos){
        return students.get(pos);
    }

    public int getSize(){
        return students.size();
    }

    public void setDataSet(ArrayList<Student> students) {
        this.students = students;
    }
}
