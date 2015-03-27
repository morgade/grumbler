package org.morgade.grumbler.entity;

import java.util.Date;

/**
 *
 */
public class Status {
    private Account sender;
    private String body;
    private Date timestamp;

    public Status() {
    }
    
    public Status(Account sender) {
        this.sender = sender;
    }
    
    public String getBody() {
        return body;
    }

    public void setBody(String text) {
        this.body = text;
    }

    public Account getSender() {
        return sender;
    }

    public void setSender(Account sender) {
        this.sender = sender;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    
}
