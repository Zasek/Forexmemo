/*
*
* @author: Jiazhen Zhang
* @date: 2018/05/03
*
* */

package com.spitfire.forexmemo.dao;

import com.spitfire.forexmemo.domain.NewsPiece;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsPieceRepository extends MongoRepository<NewsPiece, String> {

    NewsPiece save(NewsPiece newsPiece);

    NewsPiece findAllById(String Id);
}
