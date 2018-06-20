package com.rvfs.challenge.logprocessor.model;

/**
 * Basic Log object.
 */
public class LogObject {

    private Calendar dateTimestamp;
    private String threadId;
    private String userContext;
    private String uri;
    private String resourceName;
    private String payload;
    private long requestDurationInMillis;

    public LogObject(Calendar dateTimestamp, String threadId, String userContext, String uri, String resourceName, String payload, long requestDurationInMillis){
        this.dateTimestamp = dateTimestamp;
        this.threadId = threadId;
        this.userContext= userContext;
        this.uri = uri;
        this.resourceName = resourceName;
        this.payload = payload;
        this.requestDurationInMillis = requestDurationInMillis;
    }

    public LogObject(){

    }

    /**
     * @return the dateTimestamp
     */
    public Calendar getDateTimestamp() {
        return dateTimestamp;
    }

    /**
     * @param dateTimestamp the dateTimestamp to set
     */
    public void setDateTimestamp(Calendar dateTimestamp) {
        this.dateTimestamp = dateTimestamp;
    }

    /**
     * @return the threadId
     */
    public String getThreadId() {
        return threadId;
    }
    
    /**
     * @param threadId the threadId to set
     */
    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    /**
     * @return the userContext
     */
    public String getUserContext() {
        return userContext;
    }
    
    /**
     * @param userContext the userContext to set
     */
    public void setUserContext(String userContext) {
        this.userContext = userContext;
    }

    /**
     * @return the uri
     */
    public String getUri() {
        return uri;
    }

    /**
     * @param uri the uri to set
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * @return the resourceName
     */
    public String getResourceName() {
        return resourceName;
    }

    /**
     * @param resourceName the resourceName to set
     */
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    /**
     * @return the payload
     */
    public String getPayload() {
        return payload;
    }

    /**
     * @param payload the payload to set
     */
    public void setPayload(String payload) {
        this.payload = payload;
    }

    /**
     * @return the requestDurationInMillis
     */
    public long getRequestDurationInMillis() {
        return requestDurationInMillis;
    }
    
    /**
     * @param requestDurationInMillis the requestDurationInMillis to set
     */
    public void setRequestDurationInMillis(long requestDurationInMillis) {
        this.requestDurationInMillis = requestDurationInMillis;
    }
}