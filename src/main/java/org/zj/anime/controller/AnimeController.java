package org.zj.anime.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zj.anime.bean.Anime;
import org.zj.anime.bean.PlayHistory;
import org.zj.anime.bean.Upload;
import org.zj.anime.service.AnimeServiceImpl;
import org.zj.anime.service.IFieldService;
import org.zj.anime.service.PlayServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * be careful
 */
@Controller
@RequestMapping("/anime")
public class AnimeController {

    @Autowired
    IFieldService fieldService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;

    @Autowired
    AnimeServiceImpl animeService;

    @Autowired
    PlayServiceImpl playService;




    @RequestMapping("/upload")
    public void upload(){
        int animeID=Integer.parseInt(request.getParameter("anime_id"));
        System.out.println("upload:"+animeID);
        //save server
        System.out.println("fun");
        //insert db



    }

    @RequestMapping("/add")
    public void add(@RequestParam("name")String name,@RequestParam("category")String category,
                           @RequestParam("version")String version,@RequestParam("pic_link")String picLink,
                           @RequestParam("type")String type,@RequestParam("year")String year,
                           @RequestParam("language")String language,@RequestParam("loca")String loca,
                           @RequestParam("role")String role,@RequestParam("voice_actor")String voiceActor,
                           @RequestParam("alias")String alias,@RequestParam("info")String info,
                           @RequestParam("status")String status){

        /*String name=request.getParameter("name");
        String category=request.getParameter("category");
        String version=request.getParameter("version");
        String picLink=request.getParameter("pic_link");
        String type=request.getParameter("type");
        String year=request.getParameter("year");
        String language=request.getParameter("language");
        String loca=request.getParameter("loca");
        String role=request.getParameter("role");
        String voiceActor=request.getParameter("voice_actor");
        String alias=request.getParameter("alias");
        String info=request.getParameter("info");
        String status=request.getParameter("status");*/
        animeService.addAnime(new Anime(-1,category, name,  version,  picLink, type,  year,  language,  loca,  role,  voiceActor,  alias,  info,  status, new Date()));


        /**
         *  private String name;
         *     private String category;//分类 japan anime dimensional movie
         *     private String version;
         *     private String pic_link;//封面图片连接
         *     private String type;//剧情
         *     private String year;//年代
         *     private String language;//语言
         *     private String loca;//地区
         *     private String role;//角色
         *     private String voice_actor;//声优
         *     private String alias;//别名
         *     private String info;//信息
         *     private String status;//状态
          */


    }

    @RequestMapping("/{id}")
    public String getAllEpisode(@PathVariable("id")int id){

        System.out.println("run------------>");

        request.setAttribute("anime",animeService.getAnimeByID(id));
        request.setAttribute("episode_list",animeService.getEpisodeByAnimeID(id));
        request.setAttribute("related",animeService.getRelatedAnime(animeService.getAnimeByID(id).getName()));

        request.setAttribute("status",animeService.getAnimeStatusInfo(id));



        return "item";
    }

    @RequestMapping("/{id}/{episode}")
    public String getLinkByIDAndEpisode(@PathVariable("id")int id,@PathVariable("episode")int episode){

        request.setAttribute("anime",animeService.getAnimeByID(id));
        request.setAttribute("episode_list",animeService.getEpisodeByAnimeID(id));
        request.setAttribute("related",animeService.getRelatedAnime(animeService.getAnimeByID(id).getName()));


        return "play";
    }


    @RequestMapping("/search")
    public String search(){

        String category=request.getParameter("category");
        String type=request.getParameter("type");
        String year=request.getParameter("year");
        String version=request.getParameter("version");
        String loca=request.getParameter("loca");
        String orderField=request.getParameter("order_field");

        if(category!=null){
        if(category.equals("-1")){
            request.getSession().setAttribute("category","");
        }else{
            request.getSession().setAttribute("category",category);
        }
        }
        if(type!=null){
            if(type.equals("-1")){
                request.getSession().setAttribute("type","");
            }else{
                request.getSession().setAttribute("type",type);
            }
        }
        if(year!=null){
            if(year.equals("-1")){
                request.getSession().setAttribute("year","");
            }else{
                request.getSession().setAttribute("year",year);
            }
        }
        if(version!=null){
            if(version.equals("-1")){
                request.getSession().setAttribute("version","");
            }else{
                request.getSession().setAttribute("version",version);
            }
        }
        if(loca!=null){
            if(loca.equals("-1")){
                request.getSession().setAttribute("loca","");
            }else{
                request.getSession().setAttribute("loca",loca);
            }
        }
        if(orderField!=null){
            if(orderField.equals("-1")){
                request.getSession().setAttribute("order_field","");
            }else{
                request.getSession().setAttribute("order_field",orderField);
            }
        }

        String _category= (String) request.getSession().getAttribute("category");
        String _version= (String) request.getSession().getAttribute("version");
        String _type= (String) request.getSession().getAttribute("type");
        String _year=(String) request.getSession().getAttribute("year");
        String _loca=(String) request.getSession().getAttribute("loca");
        String _orderField=(String) request.getSession().getAttribute("order_field");


        request.setAttribute("anime_map",animeService.setStatusInfo(animeService.searchAnime(_category,_version,_type,_year,_loca,_orderField)));


        request.setAttribute("category_list",fieldService.getAllCategory());

        System.out.println(fieldService.getAllCategory().size()+"------" +
                "category<-------->");

        request.setAttribute("type_list",fieldService.getAllType());

        request.setAttribute("year_list",fieldService.getAllYeay());

        request.setAttribute("version_list",fieldService.getAllVersion());

        request.setAttribute("loca_list",fieldService.getAllLoca());



        return "search";
    }

    @RequestMapping("/search_page")
    public String searchPage(){

        String field=request.getParameter("name");

        request.setAttribute("category_list",fieldService.getAllCategory());

        System.out.println(fieldService.getAllCategory().size()+"------" +
                "category<-------->");

        request.setAttribute("type_list",fieldService.getAllType());

        request.setAttribute("year_list",fieldService.getAllYeay());

        request.setAttribute("version_list",fieldService.getAllVersion());

        request.setAttribute("loca_list",fieldService.getAllLoca());

        request.getSession().setAttribute("order_field","date");

        String _category= (String) request.getSession().getAttribute("category");
        String _version= (String) request.getSession().getAttribute("version");
        String _type= (String) request.getSession().getAttribute("type");
        String _year=(String) request.getSession().getAttribute("year");
        String _loca=(String) request.getSession().getAttribute("loca");
        String _orderField=(String) request.getSession().getAttribute("orderField");


        Map<Anime, String> animeStringMap = animeService.doFilteByName(animeService.setStatusInfo(animeService.searchAnime(_category, _version, _type, _year, _loca, _orderField)), field);
        request.setAttribute("anime_map",animeStringMap);
        request.setAttribute("info","与"+field+"相关共"+animeStringMap.size()+"条");

        return "search";
    }

    @RequestMapping("/up_play_count/{id}/{episode}")
    public void up(@PathVariable("id")int animeID,@PathVariable("episode")int episode){
        playService.addPlayHistory(new PlayHistory(animeID,episode,new Date()));
    }

    @RequestMapping("/upload_page")
    public String uploadPage(){

        request.setAttribute("anime_map",animeService.setStatusInfo(animeService.getAllAnime()));
        return "upload.html";
    }
}
