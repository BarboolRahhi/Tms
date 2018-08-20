package com.example.rahhi.teachingmanagmentsystem;

public class Model {



    String batchName, timeTable ,placeName, startDate;




    public Model(String batchName, String timeTable, String placeName, String startDate) {
        this.batchName = batchName;
        this.timeTable = timeTable;
        this.placeName = placeName;
        this.startDate = startDate;

    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(String timeTable) {
        this.timeTable = timeTable;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Model(){

    }


}


