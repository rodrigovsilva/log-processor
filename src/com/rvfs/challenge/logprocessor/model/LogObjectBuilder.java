package com.rvfs.challenge.logprocessor.model;

import com.rvfs.challenge.logprocessor.model.LogObject;

/**
 * Log object builder.
 */
public class LogObjectBuilder {
    
    private Calendar dateTimestamp;
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
    public LogObjetBuilder(){
    }

    /**
     * @param dateTimestamp the dateTimestamp to set
     */
    public void setDateTimestamp(Calendar dateTimestamp) {
        this.dateTimestamp = dateTimestamp;
    }
    /**
     * @param payload the payload to set
     */
    public void setPayload(String payload) {
        this.payload = payload;
    }
    
    /**
     * @param requestDurationInMillis the requestDurationInMillis to set
     */
    public void setRequestDurationInMillis(long requestDurationInMillis) {
        this.requestDurationInMillis = requestDurationInMillis;
    }
    
    /**
     * @param resourceName the resourceName to set
     */
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
    
    /**
     * @param threadId the threadId to set
     */
    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }
    
    /**
     * @param uri the uri to set
     */
    public void setUri(String uri) {
        this.uri = uri;
    }
    
    /**
     * @param userContext the userContext to set
     */
    public void setUserContext(String userContext) {
        this.userContext = userContext;
    }

    /**
     * Create a log object.
     */
    public LogObject createLogObject(){
        return new LogObject(dateTimestamp, threadId, userContext, uri, resourceName, payload, requestDurationInMillis);
    }
    
}