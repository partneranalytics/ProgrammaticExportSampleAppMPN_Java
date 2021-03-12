package com.microsoft.partneranalytics.sampleprogrammaticaccessMPN.helpers;

public class ScheduledReportObject {

    public String ReportId;
    public String ReportName;
    public String Description;
    public String QueryId;
    public String User;
    public String CreatedTime;
    public String ModifiedTime;
    public String StartTime;
    public boolean IsPublicInOrg;
    public String ReportStatus;
    public String ExecutionStatus;
    public int RecurrenceInterval;
    public int RecurrenceCount;
    public String CallbackUrl;
    public String Format;

    public String getReportId() {
        return ReportId;
    }

    public void setReportId(String reportId) {
        ReportId = reportId;
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

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public boolean getIsPublicInOrg() {
        return IsPublicInOrg;
    }

    public void setIsPublicInOrg(boolean isPublicInOrg) {
        IsPublicInOrg = isPublicInOrg;
    }

    public String getReportStatus() {
        return ReportStatus;
    }

    public void setReportStatus(String reportStatus) {
        ReportStatus = reportStatus;
    }

    public String getExecutionStatus() {
        return ExecutionStatus;
    }

    public void setExecutionStatus(String executionStatus) {
        ExecutionStatus = executionStatus;
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

    public String getCallbackUrl() {
        return CallbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        CallbackUrl = callbackUrl;
    }

    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }
}