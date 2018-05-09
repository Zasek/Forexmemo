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

import java.util.List;


@Service
public class NewsPieceService {

    @Autowired
    private NewsPieceRepository newsPieceRepository;

    /*
     * 保存新的NewsPiece
     * @param newsPiece 完整的NewsPiece对象
     * @return 被保存的NewsPiece对象
     * */
    public String saveNewsPiece(NewsPiece np) {
        if(!validateSave(np)){
            return "Invalid NewsPiece";
        }
        return newsPieceRepository.save(np).toString();
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
        if(!validateUpdate(Id, updateContent, type)){
            return "Invalid Update Input";
        }
        return newsPieceRepository.partialUpdate(Id, updateContent, type);
    }

    /*
     * 对即将进行的save进行一定的Validation，尽量保证有效并且不会被恶意利用。暂定为正文不能为空。
     * @param np 待添加的NewsPiece
     * @return 布尔值，True为通过审查，False未通过
     * */
    public boolean validateSave(NewsPiece np){
        boolean result = true;
        if(np.getActualTime() == null||np.getContent().equals("")||np.getPostTime()==null) result = false;
        return result;
    }

    /*
     * 对即将进行的update进行一定的Validation，尽量保证有效并且不会被恶意利用
     * @param Id 待修改的NewsPiece的唯一标识 updateContent 新的内容 type 字段名
     * @return 布尔值，True为通过审查，False未通过
     * */
    public boolean validateUpdate(String Id, String updateContent, String type){
        boolean result = true;
        if(updateContent.equals("")){
            result = false;
        }
        if(type.equals("_id")){
            result = false;
        }
        NewsPiece np = newsPieceRepository.findNewsPieceById(Id);
        if(np == null) {
            result = false;
        }
        return result;
    }

    public List<NewsPiece> findByDate(String date){
        String startTime = date+" 00:00:00.000";
        String endTime = date+" 23:59:59.999";
        return newsPieceRepository.findByDate(startTime, endTime);
    }
}
