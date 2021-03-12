package com.microsoft.partneranalytics.sampleprogrammaticaccessMPN.helpers;

public class ScheduledReportExecutionObject {
    public String ExecutionId;
    public String ReportId;
    public int RecurrenceInterval;
    public int RecurrenceCount;
    public boolean IsPublicInOrg;
    public String CallbackUrl;
    public String Format;
    public String ExecutionStatus;
    public String ReportLocation;
    public String ReportAccessSecureLink;
    public String ReportExpiryTime;
    public String ReportGeneratedTime;

    public String getExecutionId() {
        return ExecutionId;
    }

    public void setExecutionId(String executionId) {
        ExecutionId = executionId;
    }

    public String getReportId() {
        return ReportId;
    }

    public void setReportId(String reportId) {
        ReportId = reportId;
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

    public boolean isPublicInOrg() {
        return IsPublicInOrg;
    }

    public void setPublicInOrg(boolean publicInOrg) {
        IsPublicInOrg = publicInOrg;
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

    public String getExecutionStatus() {
        return ExecutionStatus;
    }

    public void setExecutionStatus(String executionStatus) {
        ExecutionStatus = executionStatus;
    }

    public String getReportLocation() {
        return ReportLocation;
    }

    public void setReportLocation(String reportLocation) {
        ReportLocation = reportLocation;
    }

    public String getReportAccessSecureLink() {
        return ReportAccessSecureLink;
    }

    public void setReportAccessSecureLink(String reportAccessSecureLink) {
        ReportAccessSecureLink = reportAccessSecureLink;
    }

    public String getReportExpiryTime() {
        return ReportExpiryTime;
    }

    public void setReportExpiryTime(String reportExpiryTime) {
        ReportExpiryTime = reportExpiryTime;
    }

    public String getReportGeneratedTime() {
        return ReportGeneratedTime;
    }

    public void setReportGeneratedTime(String reportGeneratedTime) {
        ReportGeneratedTime = reportGeneratedTime;
    }
}
