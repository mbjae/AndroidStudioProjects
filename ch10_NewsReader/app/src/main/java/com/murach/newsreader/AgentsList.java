package com.murach.newsreader;

import java.util.ArrayList;

/**
 * Created by 677958 on 3/15/2015.
 */
public class AgentsList {
    private ArrayList<Agent> mAgentsList;
    private int id;
    private String name;

    public AgentsList() {}

    public AgentsList(String name) {
        this.name = name;
    }

    public AgentsList(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /*public AgentsList() {
        mAgentsList = new ArrayList<Agent>();
    }*/

    public ArrayList<Agent> getAgentsList() {
        return mAgentsList;
    }

    public void setAgentsList(ArrayList<Agent> agentsList) {
        mAgentsList = agentsList;
    }

    public Agent getAgent(int index) {
        return mAgentsList.get(index);
    }

    public int addAgent(Agent agent) {
        mAgentsList.add(agent);
        return mAgentsList.size();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

