package com.spitfire.forexmemo.web;

import com.spitfire.forexmemo.domain.NewsPiece;
import com.spitfire.forexmemo.service.NewsPieceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class NewsPieceController {

    @Autowired
    private NewsPieceService newsPieceService;

    @RequestMapping(value="/addnews", method = RequestMethod.POST)
    public String addNews(@RequestBody NewsPiece np){
        return newsPieceService.saveNewsPiece(np).toString();
    }

    @RequestMapping(value="/search/{Id}", method = RequestMethod.GET)
    public String searchId(@PathVariable("Id") String Id){
        return newsPieceService.searchById(Id).toString();
    }

    @RequestMapping(value="/delete/{Id}", method = RequestMethod.DELETE)
    public String deleteId(@PathVariable("Id") String Id){
        return newsPieceService.deleteById(Id);
    }

}
