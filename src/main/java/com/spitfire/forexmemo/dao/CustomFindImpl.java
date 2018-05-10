package com.spitfire.forexmemo.dao;

import com.spitfire.forexmemo.domain.NewsPiece;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CustomFindImpl implements CustomFind{

    @Autowired
    MongoTemplate mongoTemplate;

    public List<NewsPiece> findByDate(String startTime, String endTime){

        List<NewsPiece> nlist = null;

        SimpleDateFormat s1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("actualTime").gte(s1.parse(startTime)).lt(s1.parse(endTime)));
            query.with(new Sort(Sort.Direction.ASC, "actualTime"));
            nlist = mongoTemplate.find(query, NewsPiece.class);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return nlist;
    }
}
