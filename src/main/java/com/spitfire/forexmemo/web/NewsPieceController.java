/*
 * RESTful，关于News的HTTP请求由这个类Handle
 * @author: Jiazhen Zhang
 * @version:0.0.1, 2018/05/04
 * */

package com.spitfire.forexmemo.web;

import com.spitfire.forexmemo.domain.NewsPiece;
import com.spitfire.forexmemo.service.NewsPieceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class NewsPieceController {

    @Autowired
    private NewsPieceService newsPieceService;

    @RequestMapping(value="/news", method = RequestMethod.POST)
    public String addNews(@RequestBody NewsPiece np){
        return newsPieceService.saveNewsPiece(np).toString();
    }

    @RequestMapping(value="/news/{Id}", method = RequestMethod.GET)
    public String searchId(@PathVariable("Id") String Id){
        return newsPieceService.searchById(Id).toString();
    }

    @RequestMapping(value="/news/{Id}", method = RequestMethod.DELETE)
    public String deleteId(@PathVariable("Id") String Id){
        return newsPieceService.deleteById(Id);
    }

    @RequestMapping(value="/news/{Id}/content", method = RequestMethod.PUT)
    public String updateContent(@PathVariable("Id") String Id, @RequestBody String updateContent){
        return newsPieceService.partialUpdate(Id, updateContent, "content");
    }

    @RequestMapping(value="/news/{Id}/source", method = RequestMethod.PUT)
    public String updateSource(@PathVariable("Id") String Id, @RequestBody String updateContent){
        return newsPieceService.partialUpdate(Id, updateContent, "source");
    }

    @RequestMapping(value="/news/{Id}/labels", method = RequestMethod.PUT)
    public String updateLabels(@PathVariable("Id") String Id, @RequestBody String updateContent){
        return newsPieceService.partialUpdate(Id, updateContent, "labels");
    }

    @RequestMapping(value="/news/{Id}/originLang", method = RequestMethod.PUT)
    public String updateOriginLang(@PathVariable("Id") String Id, @RequestBody String updateContent){
        return newsPieceService.partialUpdate(Id, updateContent, "originLang");
    }

    @RequestMapping(value="/news/{Id}/actualTime", method = RequestMethod.PUT)
    public String updateActualTime(@PathVariable("Id") String Id, @RequestBody String updateContent){
        return newsPieceService.partialUpdate(Id, updateContent, "actualTime");
    }

    @RequestMapping(value="/news/{Id}/postTime", method = RequestMethod.PUT)
    public String updatePostTime(@PathVariable("Id") String Id, @RequestBody String updateContent){
        return newsPieceService.partialUpdate(Id, updateContent, "postTime");
    }

}
