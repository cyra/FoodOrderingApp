package edu.curtin.foodapp;

public class RestaurantViewModel {
    private String sname;
    private String sdetails;
    private int id;

    public RestaurantViewModel(String sname, String sdetails) {
        this.sname = sname;
        this.sdetails = sdetails;
    }

    public String getSname(){
        return sname;
    }
    public void setSname(String sname){
        this.sname = sname;
    }

    public String getSdetails() {
        return sdetails;
    }

    public void setSdetails(String sdetails) {
        this.sdetails = sdetails;
    }
}
