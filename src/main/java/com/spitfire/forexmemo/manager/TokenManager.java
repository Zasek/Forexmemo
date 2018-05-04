package com.spitfire.forexmemo.manager;

import com.spitfire.forexmemo.domain.TokenModel;

/**
 * Created by H.W. on 5/4/2018.
 */
public interface TokenManager {

    /**
     * This method generates a secrete using UUID first.
     * Then creates a token using this secrete String for the given uid.
     * Finally stores <id, secrete> in Redis.
     * @param id Visitor's id.
     * @return TokenModel Created {id, token}.
     */
    String createToken(String id);

    /**
     * This method checks if the token is valid.
     * @param tokenModel Contains token and id.
     * @return boolean Whether the token is valid.
     */
    boolean checkToken(TokenModel tokenModel);

    /**
     * This method decodes token provided to server.
     * @param token Token provided by client.
     * @return TokenModel Decoded {id, token}.
     */
    TokenModel decodeToken(String token);

    /**
     * Delete token record.
     * @param id Visitor's id.
     */
    void deleteToken(String id);
}
