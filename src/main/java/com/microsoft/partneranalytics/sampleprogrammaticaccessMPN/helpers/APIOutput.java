package com.microsoft.partneranalytics.sampleprogrammaticaccessMPN.helpers;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class APIOutput<T> {

    @SerializedName("value")
    public List<T> Value;

    @SerializedName("nextLink")
    public String NextLink;

    @SerializedName("totalCount")
    public long TotalCount;

    @SerializedName("message")
    public String Message;

    @SerializedName("statusCode")
    public int StatusCode;

    @SerializedName("dataRedacted")
    public boolean DataRedacted;

    public APIOutput()
    {
        this.Value = new ArrayList<T>();
    }
}