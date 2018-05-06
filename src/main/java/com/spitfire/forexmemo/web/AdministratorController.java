package com.spitfire.forexmemo.web;

import com.spitfire.forexmemo.annotation.AdminRequired;
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
@RequestMapping("/admin")
public class AdministratorController {

    private final AdministratorService administratorService;
    private final TokenManager tokenManager;

    @Autowired
    public AdministratorController(AdministratorService administratorService, TokenManager tokenManager) {
        this.administratorService = administratorService;
        this.tokenManager = tokenManager;
    }

    @PostMapping("/auth")
    public String login(@RequestBody Administrator visitor) {
        Administrator expectedAdmin = administratorService.searchByName(visitor.getName());
        String response;
        if (expectedAdmin != null && AdministratorService.checkPassword(expectedAdmin.getPassword(), visitor.getPassword())) {
            String token = tokenManager.createToken(expectedAdmin.getAid());
            if (token != null) {
                response = token;
            } else {
                // failed to generate token
                throw new RuntimeException("Failed to generate token");
            }
        } else {
            throw new RuntimeException("Wrong admin name or password");
        }
        return response;
    }

    @PostMapping("")
    @AdminRequired
    public String addAdministrator(@RequestBody Administrator newAdmin) {
        return administratorService.saveAdministrator(newAdmin).toString();
    }
}
