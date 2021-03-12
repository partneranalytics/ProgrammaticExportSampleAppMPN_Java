package com.microsoft.partneranalytics.sampleprogrammaticaccessMPN.helpers;

import org.springframework.stereotype.Service;

@Service
public class CommonUtils {

    public boolean isNullOrBlank(String param) {
        return param == null || param.trim().length() == 0;
    }

}