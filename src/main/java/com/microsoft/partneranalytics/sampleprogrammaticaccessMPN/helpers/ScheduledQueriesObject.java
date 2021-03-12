package com.microsoft.partneranalytics.sampleprogrammaticaccessMPN.helpers;

import com.google.gson.annotations.SerializedName;

public class ScheduledQueriesObject {

    @SerializedName("queryId")
    public String QueryId;

    @SerializedName("name")
    public String Name;

    @SerializedName("description")
    public String Description;

    @SerializedName("query")
    public String Query;

    @SerializedName("type")
    public String Type;

    @SerializedName("user")
    public String User;

    @SerializedName("createdTime")
    public String CreatedTime;

    @SerializedName("modifiedTime")
    public String ModifiedTime;

    public String getQueryId() {
        return QueryId;
    }

    public void setQueryId(String queryId) {
        QueryId = queryId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getQuery() {
        return Query;
    }

    public void setQuery(String query) {
        Query = query;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getCreatedTime() {
        return CreatedTime;
    }

    public void setCreatedTime(String createdTime) {
        CreatedTime = createdTime;
    }

    public String getModifiedTime() {
        return ModifiedTime;
    }

    public void setModifiedTime(String modifiedTime) {
        ModifiedTime = modifiedTime;
    }
}