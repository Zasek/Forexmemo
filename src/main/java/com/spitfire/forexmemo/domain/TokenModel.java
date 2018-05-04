package com.spitfire.forexmemo.domain;

/**
 * Created by H.W. on 5/4/2018.
 */
public class TokenModel {

    /**
     * visitor's id.
     */
    private String id;

    /**
     * A randomly generated UUID(without '-')
     */
    private String token;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
