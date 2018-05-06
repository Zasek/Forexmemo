package com.spitfire.forexmemo.service;

import com.google.common.hash.Hashing;
import com.spitfire.forexmemo.dao.AdministratorRepository;
import com.spitfire.forexmemo.domain.Administrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

/**
 * Created by H.W. on 5/4/2018.
 */
@Service
public class AdministratorService {

    private static String encrypt(String password) {
        return Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();
    }

    public static boolean checkPassword(String expected, String actual) {
        return (expected != null && actual != null)
                && expected.equals(encrypt(actual));
    }


    private final AdministratorRepository administratorRepository;

    @Autowired
    public AdministratorService(AdministratorRepository administratorRepository) {
        this.administratorRepository = administratorRepository;
    }

    public Administrator saveAdministrator(Administrator inputData) {
        Administrator newAdmin = new Administrator();

        // TODO data validation
        // check if an admin with the same name exists
        if (administratorRepository.findByName(inputData.getName()) != null) {
            throw new RuntimeException("Account exists");
        }
        newAdmin.setName(inputData.getName());
        newAdmin.setPassword(encrypt(inputData.getPassword()));

        return administratorRepository.save(newAdmin);
    }

    public Administrator searchByAid(String aid) {
        return administratorRepository.findByAid(aid);
    }

    public Administrator searchByName(String name) {
        return administratorRepository.findByName(name);
    }

    /**
     * Delete an Administrator record in DB.
     * Need a way to show if operation succeeded.
     * Currently by returning a boolean.
     * @param aid id of Administrator record
     * @return boolean Whether the record is deleted.
     */
    public boolean deleteByAid(String aid) {
        administratorRepository.deleteAdministratorByAid(aid);
        return true;
    }
}
