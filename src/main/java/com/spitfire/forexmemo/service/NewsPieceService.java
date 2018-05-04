/*
 * Service类，处理来自NewsPieceController的操作请求
 * @author: Jiazhen Zhang
 * @version:0.0.1, 2018/05/04
 * */

package com.spitfire.forexmemo.service;

import com.spitfire.forexmemo.dao.NewsPieceRepository;
import com.spitfire.forexmemo.domain.NewsPiece;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsPieceService {

    @Autowired
    private NewsPieceRepository newsPieceRepository;

    /*
     * 保存新的NewsPiece
     * @param newsPiece 完整的NewsPiece对象
     * @return 被保存的NewsPiece对象
     * */
    public NewsPiece saveNewsPiece(NewsPiece np){
        return newsPieceRepository.save(np);
    }

    /*
     * 依据Id选出来指定的新闻，应该只返回一条NewsPiece
     * @param Id Mongodb中的_id字段，Java中用String 格式来处理
     * @return 被搜索到的NewsPiece
     * */
    public NewsPiece searchById(String Id){
        return newsPieceRepository.findNewsPieceById(Id);
    }

    /*
     * 删除依据Id选出来指定的新闻，暂时不用返回
     * @param Id Mongodb中的_id字段，Java中用String 格式来处理
     * @return void
     * */
    public String deleteById(String Id){
        newsPieceRepository.deleteNewsPieceById(Id);
        return "Delete "+Id+" success!";
    }

    /*
     * 更新指定document中的字段，进行一定的Validation，尽量保证有效并且不会被恶意利用
     * @param Id 待修改的NewsPiece的唯一标识 updateContent 新的内容 type 字段名
     * @return 暂定为一个代表成功还是失败的字符串
     * */
    public String partialUpdate(String Id, String updateContent, String type){
        if(updateContent.equals("")){
            return "Invalid input, content == ''";
        }
        if(type.equals("_id")){
            return "Invalid type";
        }
        return newsPieceRepository.partialUpdate(Id, updateContent, type);
    }
}
