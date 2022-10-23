package com.giggle.api.model.component;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "todo_messages")
public class TodoInfo {
    @Id
    @Column(name = "todo_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "message")
    @Size(min = 5)
    private String Message;

    @Column(name = "due_date", nullable = false)
    private String Date;

    @Column(name = "Status")
    private String Status;

    public TodoInfo() {
        Status = "Not Completed";
    }

    public TodoInfo(String message, String date) {
        this.Message = message;
        Date = date;
        Status = "Not Completed";
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
