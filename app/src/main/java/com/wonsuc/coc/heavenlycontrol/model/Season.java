package com.wonsuc.coc.heavenlycontrol.model;

public class Season {

    public int id;
    public String startDate;
    public String endDate;

    public Season(){}

    public Season(String startDate) {
        super();
        this.startDate = startDate;
    }

    public Season(String startDate, String endDate) {
        super();
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Season [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + "]";
    }
}
