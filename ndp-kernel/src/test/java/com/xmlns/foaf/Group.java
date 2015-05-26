package com.xmlns.foaf;

import java.util.Set;

/**
 * Created by hp on 2015/5/26.
 */
public class Group extends Agent{
    Set<Agent> members;

    public Group() {
    }

    public Set<Agent> getMembers() {
        return members;
    }

    public void setMembers(Set<Agent> members) {
        this.members = members;
    }
}
