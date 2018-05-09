/*
* 针对新闻的数据库操作接口，继承MongoRepository和一些自定义操作
* @author: Jiazhen Zhang
* @version:0.0.1, 2018/05/04
* */

package com.spitfire.forexmemo.dao;

import com.spitfire.forexmemo.domain.NewsPiece;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsPieceRepository extends MongoRepository<NewsPiece, String>,
                                             CustomFind,
                                             PartialUpdate {
    /*
    * 保存新的NewsPiece
    * @param newsPiece 完整的NewsPiece对象
    * @return 被保存的NewsPiece对象
    * */
    NewsPiece save(NewsPiece newsPiece);

    /*
     * 依据Id选出来指定的新闻，应该只返回一条NewsPiece
     * @param Id Mongodb中的_id字段，Java中用String 格式来处理
     * @return 被搜索到的NewsPiece
     * */
    NewsPiece findNewsPieceById(String Id);

    /*
     * 删除依据Id选出来指定的新闻，暂时不用返回
     * @param Id Mongodb中的_id字段，Java中用String 格式来处理
     * @return void
     * */
    void deleteNewsPieceById(String Id);

}
