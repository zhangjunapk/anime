package org.zj.anime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zj.anime.bean.Anime;
import org.zj.anime.bean.PlayHistory;
import org.zj.anime.bean.Upload;
import org.zj.anime.service.AnimeServiceImpl;
import org.zj.anime.service.PlayServiceImpl;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnimeApplicationTests {

    @Autowired
    AnimeServiceImpl animeService;

    @Test
    public void contextLoads() {
    }


    //insert anime
    @Test
    public void insertAnime() {

        /**
         * 2016-03-19更新  状态：待更中...
         * 类型：日本动漫 热血 动作 神魔
         * 发音：日语  地区：日本  年代：2009
         * 角色：纳兹·多拉格尼尔 露西·哈特菲利亚
         * 声优：大原沙耶香 羽多野涉 佐藤聪美
         * 别名：妖精的尾巴第一季/妖精的尾巴第二季
         * 剧情：少女露西一直希望能加入云集众多厉害魔法师的名为“妖精尾巴”的公会，在纳兹的引导下，露西终于得偿所愿，随后，露西跟纳兹、格雷、艾尔莎和哈比组成“最强！？”队伍，二男二女一猫的旅程就此展开……
         * 妖精的尾巴在线播放高清视频资源由动漫岛[www.cosdao.com]收集整理,本作是热血,动作,神魔类中一部很不错的作品,值得各位漫友娱乐观看.后续动漫岛还会继续收集妖精的尾巴全集最新资源并整合发布，努力让各位漫友观看到高清晰画质、速度流畅的视频资源. ^_^ 加油！
         */
        Anime anime = new Anime();
        anime.setName("妖精的尾巴");
        anime.setLanguage("日语");
        anime.setType("action");
        anime.setRole("纳兹 露西");
        anime.setVoice_actor("大原沙耶香 羽多野涉 佐藤聪美 ");
        anime.setAlias("妖精的尾巴第一季/妖精的尾巴第二季");
        anime.setInfo("少女露西一直希望能加入云集众多厉害魔法师的名为“妖精尾巴”的公会，在纳兹的引导下，露西终于得偿所愿，随后，露西跟纳兹、格雷、艾尔莎和哈比组成“最强！？”队伍，二男二女一猫的旅程就此展开……\n" +
                "          妖精的尾巴在线播放高清视频资源由动漫岛[www.cosdao.com]收集整理,本作是热血,动作,神魔类中一部很不错的作品,值得各位漫友娱乐观看.后续动漫岛还会继续收集妖精的尾巴全集最新资源并整合发布，努力让各位漫友观看到高清晰画质、速度流畅的视频资源. ^_^ 加油");
        anime.setYear("2009");
        anime.setCategory("japan anime");
        anime.setPic_link("/upload/a.jpg");
        animeService.addAnime(anime);
        anime.setCategory("west anime");
        anime.setStatus("update");
        animeService.addAnime(anime);

        anime.setCategory("china anime");
        animeService.addAnime(anime);

        anime.setYear("2010");
        animeService.addAnime(anime);

        anime.setYear("2019");
        anime.setType("action<->fun");
        animeService.addAnime(anime);

        anime.setStatus("over");
        animeService.addAnime(anime);

        anime.setStatus("continue");
        animeService.addAnime(anime);

        anime.setLanguage("english");
        animeService.addAnime(anime);

        anime.setLanguage("china");
        anime.setType("love<->fun");
        animeService.addAnime(anime);

        anime.setCategory("china anime");
        animeService.addAnime(anime);

        anime.setCategory("dimensional");
        animeService.addAnime(anime);
    }

    @Test
    public void upload(){
        Upload upload=new Upload();
        upload.setAnime_id(1);
        upload.setEpisode(1);
        upload.setLink("/upload/1.rmvb");
        upload.setDate(new Date());
        animeService.uploadEpisode(upload);
    }


   @Test
    public void showAll(){
        for(Anime anime:animeService.getAllAnime()){
            System.out.println(anime);
        }
   }

    @Test
    public void insertEpisode(){
        new PlayServiceImpl().addPlayHistory(new PlayHistory(23,1,new Date()));
    }


    @Test
    public void getTop(){
        new PlayServiceImpl().getPlayCountEntryList();
        /*for(Anime anime:animeService.getTopPlayAnime("日本动漫",10,0)){
            System.out.println(anime);
        }*/
   }


}
