package org.zj.anime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zj.anime.bean.Anime;
import org.zj.anime.dao.IQueryMapper;
import org.zj.anime.service.AnimeServiceImpl;
import org.zj.anime.service.PlayServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {
    @Autowired
    HttpServletRequest request;

    @Autowired
    AnimeServiceImpl animeService;

    @Autowired
    PlayServiceImpl playService;

    @Autowired
    IQueryMapper queryMapper;



    @RequestMapping("/index")
    public String index(){
        playService.tt();


        for(Anime anime:animeService.getAllAnime()){
            System.out.println(anime);
        }

        //hot
        //hashmap<Anime,String> anime,status info
        request.setAttribute("hot",animeService.setStatusInfo(animeService.getTopPlayAnime(null,10,0)));
        request.setAttribute("new_japan_anime",animeService.setStatusInfo(animeService.getTopNewAnimeByLoca("japan anime",8)));
        request.setAttribute("new_china_anime",animeService.setStatusInfo(animeService.getTopNewAnimeByLoca("china anime",8)));
        request.setAttribute("new_west_anime",animeService.setStatusInfo(animeService.getTopNewAnimeByLoca("west anime",8)));
        request.setAttribute("new_dimensional_anime",animeService.setStatusInfo(animeService.getTopNewAnimeByLoca("dimensional",8)));

        System.out.println("japan-------->"+animeService.setStatusInfo(animeService.getAnimeByCategoryOrderByDate("japan",8)).size());

        request.setAttribute("japen_top",animeService.setStatusInfo(animeService.getTopPlayAnime("japan",15,0)));
        request.setAttribute("china_top",animeService.setStatusInfo(animeService.getTopPlayAnime("china",15,0)));
        System.out.println("china top----->"+animeService.getTopPlayAnime("china",15,0).size());
        request.setAttribute("west_top",animeService.setStatusInfo(animeService.getTopPlayAnime("west",15,0)));
        request.setAttribute("dimensional_top",animeService.setStatusInfo(animeService.getTopPlayAnime("dimensional",15,0)));



        return "index";
    }
}
