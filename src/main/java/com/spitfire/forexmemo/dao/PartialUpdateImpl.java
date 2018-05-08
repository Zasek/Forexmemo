/*
 * PartialUpdate 的实现类
 * @author: Jiazhen Zhang
 * @version:0.0.1, 2018/05/04
 * */
package com.spitfire.forexmemo.dao;

import com.spitfire.forexmemo.domain.NewsPiece;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PartialUpdateImpl implements PartialUpdate {

    @Autowired
    MongoTemplate mongoTemplate;

    /*
     * 更新指定document中的字段，分别处理时间和普通字段，因为需要parse Date格式
     * @param Id 待修改的NewsPiece的唯一标识 updateContent 新的内容 type 字段名
     * @return 暂定为一个代表成功还是失败的字符串
     * */
    @Transactional
    public String partialUpdate(String Id, String updateContent, String type){
        if(!type.equals("postTime") && !type.equals("actualTime")){
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(Id));
            Update update = new Update();
            update.set(type, updateContent);
            mongoTemplate.updateFirst(query, update, NewsPiece.class);
        } else {
            SimpleDateFormat s1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try{
                Date newDate = s1.parse(updateContent);
                Query query = new Query();
                query.addCriteria(Criteria.where("_id").is(Id));
                Update update = new Update();
                update.set(type, newDate);
                mongoTemplate.updateFirst(query, update, NewsPiece.class);
            } catch (Exception e){
                return "Update Date Fail!";
            }
        }
        return "Update Success!";
    }
}
