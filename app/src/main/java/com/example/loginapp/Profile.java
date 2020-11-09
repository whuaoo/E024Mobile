package com.example.loginapp;

public class Profile {
    private Float cGPA;
    private Float pGPA;
    private Integer accCredits;
    private Integer semCredits;

    Profile(Float cGPA, Float pGPA, Integer accCredits, Integer semCredits){
        this.cGPA = cGPA;
        this.pGPA = pGPA;
        this.accCredits = accCredits;
        this.semCredits = semCredits;
    }

    public Float getcGPA() {
        return cGPA;
    }

    public void setcGPA(Float cGPA) {
        this.cGPA = cGPA;
    }

    public Float getpGPA() {
        return pGPA;
    }

    public void setpGPA(Float pGPA) {
        this.pGPA = pGPA;
    }

    public Integer getAccCredits() {
        return accCredits;
    }

    public void setAccCredits(Integer accCredits) {
        this.accCredits = accCredits;
    }

    public Integer getSemCredits() {
        return semCredits;
    }

    public void setSemCredits(Integer semCredits) {
        this.semCredits = semCredits;
    }
}

