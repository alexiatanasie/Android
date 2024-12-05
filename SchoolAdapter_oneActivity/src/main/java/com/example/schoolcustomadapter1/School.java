package com.example.schoolcustomadapter1;

import java.io.Serializable;
import java.util.Date;

public class School implements Serializable {

    private String materie;
    private Date dataTest;
    private float grade;
    private boolean isAbsolvent;

    public School(String materie, Date dataTest, float grade, boolean isAbsolvent) {
        this.materie = materie;
        this.dataTest = dataTest;
        this.grade = grade;
        this.isAbsolvent = isAbsolvent;
    }

    public String getMaterie() {
        return materie;
    }

    public void setMaterie(String materie) {
        this.materie = materie;
    }

    public Date getDataTest() {
        return dataTest;
    }

    public void setDataTest(Date dataTest) {
        this.dataTest = dataTest;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public boolean isAbsolvent() {
        return isAbsolvent;
    }

    public void setAbsolvent(boolean absolvent) {
        isAbsolvent = absolvent;
    }

    @Override
    public String toString() {
        return "School{" +
                "materie='" + materie + '\'' +
                ", dataTest=" + dataTest +
                ", grade=" + grade +
                ", isAbsolvent=" + isAbsolvent +
                '}';
    }
}
