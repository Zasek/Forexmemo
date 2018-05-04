package com.spitfire.forexmemo.manager;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.spitfire.forexmemo.dao.SecreteModelRepository;
import com.spitfire.forexmemo.domain.SecreteModel;
import com.spitfire.forexmemo.domain.TokenModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * Created by H.W. on 5/4/2018.
 */
@Component
public class MongoTokenManager implements TokenManager {

    private final SecreteModelRepository secreteModelRepository;

    @Autowired
    public MongoTokenManager(SecreteModelRepository secreteModelRepository) {
        this.secreteModelRepository = secreteModelRepository;
    }

    @Override
    public String createToken(String id) {
        String secrete;
        SecreteModel secreteModel = secreteModelRepository.findSecreteModelById(id);
        if (secreteModel != null) {
            secrete = secreteModel.getSecrete();
        } else {
            // generate a secrete
            secrete = UUID.randomUUID().toString().replace("-", "");
            secreteModel = new SecreteModel();
            secreteModel.setId(id);
            secreteModel.setSecrete(secrete);
        }
        String token = null;
        try {
            token = JWT.create()
                    .withAudience(id)
                    .sign(Algorithm.HMAC256(secrete)); // use upwd as the secret
        } catch (UnsupportedEncodingException uee) {
        }

        // stores it in Mongo
        secreteModelRepository.save(secreteModel);

        return token;
    }

    @Override
    public TokenModel decodeToken(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        String id;
        try {
            // extract id from encoded token
            id = decodedJWT.getAudience().get(0);
        } catch (JWTDecodeException e) {
            throw new RuntimeException("Failed to decode id in token.");
        }
        TokenModel ret = new TokenModel();
        ret.setId(id);
        ret.setToken(token);
        return ret;
    }

    @Override
    public boolean checkToken(TokenModel tokenModel) {
        if (tokenModel == null || tokenModel.getId() == null || tokenModel.getToken() == null) {
            return false;
        }

        // find visitor's secrete
        SecreteModel secreteModel = secreteModelRepository.findSecreteModelById(tokenModel.getId());
        if (secreteModel == null) {
            return false;
        }
        String secrete = secreteModel.getSecrete();

        // verify token
        try {
            Algorithm algorithm = Algorithm.HMAC256(secrete);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withAudience(tokenModel.getId())
                    .build();

            // verify signature
            DecodedJWT jwt = verifier.verify(tokenModel.getToken());

            // verify identity
            if (!tokenModel.getId().equals(jwt.getAudience().get(0))) {
                // token is not assigned to this visitor
                return false;
            }
        } catch (UnsupportedEncodingException exception){
            //UTF-8 encoding not supported
        } catch (JWTVerificationException e) {
            //Invalid signature/claims
            throw new RuntimeException("Invalid signature/claims");
        }
        return true;
    }

    @Override
    public void deleteToken(String id) {
        secreteModelRepository.deleteSecreteModelById(id);
    }
}
