package org.zj.anime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zj.anime.bean.Anime;
import org.zj.anime.service.AnimeServiceImpl;
import org.zj.anime.service.IAnimeService;

import java.util.List;

@RestController
@RequestMapping("/anime")
public class AnimeControllerJson {

    @Autowired
    IAnimeService animeService;

    @RequestMapping("/all")
    public List<Anime> getAllAnime(){
        return animeService.getAllAnime();
    }

    @RequestMapping("/search_name")
    public List<Anime> search(@RequestParam("name")String name){
        return animeService.searchAnimeByName(name);
    }
}
