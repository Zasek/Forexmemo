package com.spitfire.forexmemo.dao;

import com.spitfire.forexmemo.domain.Administrator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by H.W. on 5/4/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AdministratorRepositoryTest {

    @Autowired
    private AdministratorRepository administratorRepository;

    @Test
    public void successfulSaveAndFindTest() {
        String aid = "test_aid";
        String name = "test_name";
        Administrator expectedData = new Administrator();
        expectedData.setAid(aid);
        expectedData.setName(name);
        administratorRepository.save(expectedData);
        Administrator actualData = administratorRepository.findByAid(aid);
        Assert.assertEquals(expectedData, actualData);
    }

    @Test
    public void findNotExistTest() {
        // find an non-existing aid
        Administrator actualData = administratorRepository.findByAid("not_exist_aid");
        Assert.assertNull(actualData);
    }

    @Test
    public void deleteTest() {
        String aid = "test_aid";
        String name = "test_name";
        Administrator expectedData = new Administrator();
        expectedData.setAid(aid);
        expectedData.setName(name);
        administratorRepository.save(expectedData);

        // make sure data has been inserted
        Administrator actualData = administratorRepository.findByAid(aid);
        Assert.assertEquals(expectedData, actualData);

        administratorRepository.deleteAdministratorByAid(aid);
        // make sure data has been inserted
        actualData = administratorRepository.findByAid(aid);
        Assert.assertNull(actualData);
    }
}
