package com.yincheng.eyepetizer.provider.models;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

public class Auth {

    private List<String> scopes;
    private String note;
    private String noteUrl;
    @SerializedName("client_id")
    private String clientId;
    @SerializedName("client_secret")
    private String clientSecret;

    public static Auth generate() {
        Auth model = new Auth();
        model.scopes = Arrays.asList("user", "repo", "gist", "notifications");
        model.note = "com.thirtydegreesray.openhub";
        model.clientId = "8f7213694e115df205fb";
        model.clientSecret = "82c57672382db5c7b528d79e283c398ad02e3c3f";
        model.noteUrl = "https://github.com/ThirtyDegreesRay/OpenHub/CallBack";
        return model;
    }

    public List<String> getScopes() {
        return scopes;
    }

    public String getNote() {
        return note;
    }

    public String getNoteUrl() {
        return noteUrl;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }
}

