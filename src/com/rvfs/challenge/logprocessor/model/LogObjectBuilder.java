package com.rvfs.challenge.logprocessor.model;

import java.time.LocalDateTime;

/**
 * Log object builder.
 */
public class LogObjectBuilder {
    
    private LocalDateTime dateTimestamp;
    private String threadId;
    private String userContext;
    private String uri;
    private String resourceName;
    private String payload;
    private long requestDurationInMillis;

    /**
     * Default constructor
     * 
     */
    public LogObjectBuilder(){
    }

    /**
     * @param dateTimestamp the dateTimestamp to set
     */
    public LogObjectBuilder setDateTimestamp(LocalDateTime dateTimestamp) {
        this.dateTimestamp = dateTimestamp;
        return this;
    }
    /**
     * @param payload the payload to set
     */
    public LogObjectBuilder setPayload(String payload) {
        this.payload = payload;
        return this;
    }
    
    /**
     * @param requestDurationInMillis the requestDurationInMillis to set
     */
    public LogObjectBuilder setRequestDurationInMillis(long requestDurationInMillis) {
        this.requestDurationInMillis = requestDurationInMillis;
        return this;
    }
    
    /**
     * @param resourceName the resourceName to set
     */
    public LogObjectBuilder setResourceName(String resourceName) {
        this.resourceName = resourceName;
        return this;
    }
    
    /**
     * @param threadId the threadId to set
     */
    public LogObjectBuilder setThreadId(String threadId) {
        this.threadId = threadId;
        return this;
    }
    
    /**
     * @param uri the uri to set
     */
    public LogObjectBuilder setUri(String uri) {
        this.uri = uri;
        return this;
    }
    
    /**
     * @param userContext the userContext to set
     */
    public LogObjectBuilder setUserContext(String userContext) {
        this.userContext = userContext;
        return this;
    }

    /**
     * Create a log object.
     */
    public LogObject createLogObject(){
        return new LogObject(dateTimestamp, threadId, userContext, uri, resourceName, payload, requestDurationInMillis);
    }
    
}