package com.example.jeevika.materialdesign;

public class PortfolioData {

    String stock;
    int nos;
    double avgprice;
    double currentprice;
    double profit;
    double profitp;         //profit/loss percentage


   public PortfolioData(String stock,int nos,double avgprice,double currentprice,double profit,double profitp)
    {
        this.stock=stock;
        this.nos=nos;
        this.avgprice=avgprice;
        this.currentprice=currentprice;
        this.profit=profit;
        this.profitp=profitp;
    }
}
