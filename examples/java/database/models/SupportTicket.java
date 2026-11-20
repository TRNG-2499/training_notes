package com.revature.database.models;

import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class SupportTicket {

    private ObjectId id;    // maps automatically to _id
    private String subject;
    private String description;
    private List<String> labels = new ArrayList<>();

    public SupportTicket() {}

    public SupportTicket(String subject, String description, List<String> labels) {
        this.subject = subject;
        this.description = description;
        if (labels != null) {
            this.labels = new ArrayList<>(labels);
        }
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    @Override
    public String toString() {
        return "SupportTicket[id=" + id +
                ", subject='" + subject +
                ", description='" + description +
                ", labels=" + labels + ']';
    }
}
