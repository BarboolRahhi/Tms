package com.example.rahhi.teachingmanagmentsystem;

public class Student {


    private String mName;
    private String mImageUrl;

    public Student() {
        //empty constructor needed
    }

    public Student(String name, String imageUrl) {
        if (name.trim().equals("")) {
            name = "No Name";
        }

        mName = name;
        mImageUrl = imageUrl;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }


}
