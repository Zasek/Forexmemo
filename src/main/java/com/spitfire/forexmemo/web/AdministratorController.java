package com.spitfire.forexmemo.web;

import com.spitfire.forexmemo.domain.Administrator;
import com.spitfire.forexmemo.manager.TokenManager;
import com.spitfire.forexmemo.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by H.W. on 5/4/2018.
 */
@RestController
@RequestMapping("/admin/authentication")
public class AdministratorController {

    private final AdministratorService administratorService;
    private final TokenManager tokenManager;

    @Autowired
    public AdministratorController(AdministratorService administratorService, TokenManager tokenManager) {
        this.administratorService = administratorService;
        this.tokenManager = tokenManager;
    }

    @PostMapping("")
    public String login(@RequestBody Administrator visitor) {
        Administrator expectedAdmin = administratorService.searchByAid(visitor.getAid());
        String response = "Failed";
        if (expectedAdmin != null && AdministratorService.checkUpwd(expectedAdmin.getPassword(), visitor.getPassword())) {
            String token = tokenManager.createToken(expectedAdmin.getAid());
            if (token != null) {
                response = token;
            } else {
                // failed to generate token
            }
        }
        return response;
    }
}
