package com.chariottask.activity.model;

public class ChartTypeModel {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;

    public  ChartTypeModel(String title)
    {
        this.title=title;
    }
}
