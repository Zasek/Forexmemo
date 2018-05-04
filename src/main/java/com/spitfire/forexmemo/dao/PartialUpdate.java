/*
 * 自定义的接口，提供更新指定document的指定字段的值得方法
 * @author: Jiazhen Zhang
 * @version:0.0.1, 2018/05/04
 * */
package com.spitfire.forexmemo.dao;

public interface PartialUpdate {

    /*
     * 更新指定document中的字段
     * @param Id 待修改的NewsPiece的唯一标识 updateContent 新的内容 type 字段名
     * @return 暂定为一个代表成功还是失败的字符串
     * */
    String partialUpdate(String Id, String updateContent, String type);

}
