package com.giggle.api.model.component;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Size;

@Entity
@Table(name = "todo_messages")
public class TodoInfo {
    @Id
    @Column(name = "todo_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "message")
    @Size(min = 5)
    private String Message;

    @Column(name = "due_date", nullable = false)
    @Future
    private String Date;

    @Column(name = "Status")
    private String Status;
    @Column(name = "repeated_task")
    private Boolean isRepeated;

    public TodoInfo() {
        this.Status = "Not Completed";
        this.isRepeated = false;
    }

    public TodoInfo(String message, String date, Boolean isRepeated) {
        this.Message = message;
        Date = date;
        this.isRepeated = isRepeated;
        Status = "Not Completed";
    }

    public Boolean getRepeated() {
        return isRepeated;
    }

    public void setRepeated(Boolean repeated) {
        isRepeated = repeated;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public long getId() {
        return id;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        this.Message = message;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    @Override
    public String toString() {
        return String.format("%s, %s", this.Message, this.Date);
    }
}
