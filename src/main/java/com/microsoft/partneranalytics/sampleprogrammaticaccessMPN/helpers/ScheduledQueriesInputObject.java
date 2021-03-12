package com.microsoft.partneranalytics.sampleprogrammaticaccessMPN.helpers;

public class ScheduledQueriesInputObject {

    public String Name;

    public String Description;

    public String Query;

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

    public ScheduledQueriesInputObject(String name, String query) {
        Name = name;
        Query = query;
    }
}
