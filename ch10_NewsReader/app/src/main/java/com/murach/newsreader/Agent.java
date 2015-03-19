package com.murach.newsreader;

/**
 * Created by Leisy Moliner
 */
public class Agent {
    private int mAgentId;
    private String mAgtFirstName;
    private String mAgtMiddleInitial;
    private String mAgtLastName;
    private String mAgtBusPhone;
    private String mAgtEmail;
    private String mAgtPosition;
    private int mAgencyId;

    public Agent(){}

    public Agent(int agentId, String agtFirstName, String agtMiddleInitial, String agtLastName,
                String agtBusPhone, String agtEmail, String agtPosition, int agencyId) {

        this.mAgentId = agentId;
        this.mAgtFirstName = agtFirstName;
        this.mAgtMiddleInitial = agtMiddleInitial;
        this.mAgtLastName = agtLastName;
        this.mAgtBusPhone = agtBusPhone;
        this.mAgtEmail = agtEmail;
        this.mAgtPosition = agtPosition;
        this.mAgencyId = agencyId;
    }

    public int getAgentId() {
        return mAgentId;
    }

    public void setAgentId(int agentId) {
        mAgentId = agentId;
    }

    public String getAgtFirstName() {
        return mAgtFirstName;
    }

    public void setAgtFirstName(String agtFirstName) {
        mAgtFirstName = agtFirstName;
    }

    public String getAgtMiddleInitial() {
        return mAgtMiddleInitial;
    }

    public void setAgtMiddleInitial(String agtMiddleInitial) {
        mAgtMiddleInitial = agtMiddleInitial;
    }

    public String getAgtLastName() {
        return mAgtLastName;
    }

    public void setAgtLastName(String agtLastName) {
        mAgtLastName = agtLastName;
    }

    public String getAgtBusPhone() {
        return mAgtBusPhone;
    }

    public void setAgtBusPhone(String agtBusPhone) {
        mAgtBusPhone = agtBusPhone;
    }

    public String getAgtEmail() {
        return mAgtEmail;
    }

    public void setAgtEmail(String agtEmail) {
        mAgtEmail = agtEmail;
    }

    public String getAgtPosition() {
        return mAgtPosition;
    }

    public void setAgtPosition(String agtPosition) {
        mAgtPosition = agtPosition;
    }

    public int getAgencyId() {
        return mAgencyId;
    }

    public void setAgencyId(int agencyId) {
        mAgencyId = agencyId;
    }
}