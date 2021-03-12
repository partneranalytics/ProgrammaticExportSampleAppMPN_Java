package com.microsoft.partneranalytics.sampleprogrammaticaccessMPN.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.core.env.Environment;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

@Component
public class TokenGenerator {

    @Autowired
    private CommonUtils commonUtils;

    public long ExpiryTime = 0;
    public String AccessToken;

    public String GenerateToken(Environment env) throws Exception {

        MultiValueMap<String, String> postData = new LinkedMultiValueMap<String, String>();
        String finalTokenUrl = String.format(env.getProperty("AdTokenConfig.TokenGenerateUrl"), env.getProperty("AdTokenConfig.TenantId"));
        postData.add("resource", env.getProperty("AdTokenConfig.ResourceId"));
        postData.add("client_id", env.getProperty("AdTokenConfig.WebAppClientId"));
        postData.add("client_secret", env.getProperty("AdTokenConfig.WebAppClientSecret"));
        postData.add("username", env.getProperty("AdTokenConfig.Username"));
        postData.add("password", env.getProperty("AdTokenConfig.Password"));

        if(ExpiryTime == 0 || commonUtils.isNullOrBlank(AccessToken)){
            postData.add("grant_type", "password");
        }else{
            return AccessToken;
        }

        TokenGeneratorResponseObject tokenGeneratorResponseObject = null;
        tokenGeneratorResponseObject = PostFormUrlEncoded(finalTokenUrl, postData);
        if(commonUtils.isNullOrBlank(tokenGeneratorResponseObject.access_token)){
            throw new Exception("TOken generation returned no access token");
        }

        AccessToken = tokenGeneratorResponseObject.access_token;
        ExpiryTime = Long.parseLong(tokenGeneratorResponseObject.expires_on);
        return tokenGeneratorResponseObject.access_token;
    }

    private TokenGeneratorResponseObject PostFormUrlEncoded(String url, MultiValueMap<String, String> body){
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.postForObject(url, body, String.class);
        Gson gson = new Gson();
        TokenGeneratorResponseObject tokenObject = gson.fromJson(result, TokenGeneratorResponseObject.class);
        return tokenObject;
    }
}