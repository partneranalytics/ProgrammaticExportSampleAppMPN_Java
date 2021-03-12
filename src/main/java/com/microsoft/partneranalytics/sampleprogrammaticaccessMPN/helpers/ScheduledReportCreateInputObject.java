package com.microsoft.partneranalytics.sampleprogrammaticaccessMPN.helpers;

public class ScheduledReportCreateInputObject {

    public String ReportName;
    public String Description;
    public String QueryId;
    public String StartTime;
    public String Format;
    public String CallbackUrl;
    public int RecurrenceInterval;
    public int RecurrenceCount;

    public ScheduledReportCreateInputObject(String reportName, String queryId, String startTime, int recurrenceInterval, int recurrenceCount) {
        ReportName = reportName;
        QueryId = queryId;
        StartTime = startTime;
        RecurrenceInterval = recurrenceInterval;
        RecurrenceCount = recurrenceCount;
    }

    public String getReportName() {
        return ReportName;
    }

    public void setReportName(String reportName) {
        ReportName = reportName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getQueryId() {
        return QueryId;
    }

    public void setQueryId(String queryId) {
        QueryId = queryId;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }

    public String getCallbackUrl() {
        return CallbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        CallbackUrl = callbackUrl;
    }

    public int getRecurrenceInterval() {
        return RecurrenceInterval;
    }

    public void setRecurrenceInterval(int recurrenceInterval) {
        RecurrenceInterval = recurrenceInterval;
    }

    public int getRecurrenceCount() {
        return RecurrenceCount;
    }

    public void setRecurrenceCount(int recurrenceCount) {
        RecurrenceCount = recurrenceCount;
    }
}