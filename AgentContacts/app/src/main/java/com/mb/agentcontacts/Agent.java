package com.mb.agentcontacts;

/*
 * Submitted by: MB Jae Camacho
 * Date: March 18, 2015
 * Course Module: CMPP 264
 * Day 10 & 13 Assignment
 */

public class Agent {
    private int agentID;
    private String agentName;
    private String agentPhone;
    private String agentEmail;

    public Agent() {
        agentID = -1;
        agentName="";
        agentPhone="";
        agentEmail="";
    }

    public int agentID() {
        return agentID;
    }
    public void agentID(int agentID) {
        this.agentID = agentID;
    }

    public String agentName() {
        return agentName;
    }
    public void agentName(String agentName) {
        this.agentName = agentName;
    }

    public String agentPhone() {
        return agentPhone;
    }
    public void agentPhone(String agentPhone) {
        this.agentPhone = agentPhone;
    }

    public String agentEmail() {
        return agentEmail;
    }
    public void agentEmail(String agentEmail) {
        this.agentEmail = agentEmail;
    }
}
