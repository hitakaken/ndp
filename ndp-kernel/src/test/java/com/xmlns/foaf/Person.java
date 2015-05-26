package com.xmlns.foaf;

import java.util.Set;

/**
 * Created by hp on 2015/5/26.
 */
public class Person extends Agent {
    protected String firstName;
    protected String lastName;
    protected Set<Project> currentProjects;
    protected Set<Project> pastProjects;

    public Person() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Project> getCurrentProjects() {
        return currentProjects;
    }

    public void setCurrentProjects(Set<Project> currentProjects) {
        this.currentProjects = currentProjects;
    }

    public Set<Project> getPastProjects() {
        return pastProjects;
    }

    public void setPastProjects(Set<Project> pastProjects) {
        this.pastProjects = pastProjects;
    }
}
