package com.microsoft.partneranalytics.sampleprogrammaticaccessMPN.helpers;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ScheduledDatasetObject {

    @SerializedName("datasetName")
    public String DatasetName;

    @SerializedName("selectableColumns")
    public List<String> SelectableColumns;

    @SerializedName("availableMetrics")
    public List<String> AvailableMetrics;

    @SerializedName("availableDateRanges")
    public List<String> AvailableDateRanges;

    public String getDatasetName() {
        return DatasetName;
    }

    public void setDatasetName(String datasetName) {
        DatasetName = datasetName;
    }

    public List<String> getSelectableColumns() {
        return SelectableColumns;
    }

    public void setSelectableColumns(List<String> selectableColumns) {
        SelectableColumns = selectableColumns;
    }

    public List<String> getAvailableMetrics() {
        return AvailableMetrics;
    }

    public void setAvailableMetrics(List<String> availableMetrics) {
        AvailableMetrics = availableMetrics;
    }

    public List<String> getAvailableDateRanges() {
        return AvailableDateRanges;
    }

    public void setAvailableDateRanges(List<String> availableDateRanges) {
        AvailableDateRanges = availableDateRanges;
    }
}