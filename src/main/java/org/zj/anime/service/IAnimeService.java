package org.zj.anime.service;

import org.zj.anime.bean.Anime;
import org.zj.anime.bean.Upload;

import java.util.List;
import java.util.Map;

public interface IAnimeService {


    List<Anime> searchAnimeByName(String name);

    List<Anime> getTopNewAnimeByLoca(String loca,int limit);

    boolean modifyAnime(Anime anime);

    boolean deleteAnimeByID(int id);

    boolean uploadEpisode(Upload upload);

    Map<Anime,String> setStatusInfo(List<Anime> animes);

    int getAnimeLastEpisode(int id);

    String getAnimeStatusInfo(int id);

    List<Anime> getAllAnimeOrderByDateDesc();

    List<Anime> sortAnimeByDate(List<Anime> animes);

    Upload getUploadByAnimeIDAndEpisode(int animeID,int episode);

    Anime getAnimeByID(int id);

    List<Upload> getEpisodeByAnimeID(int id);

    List<Anime> getAnimeByCategoryOrderByDate(String category,int top);

    List<Anime> sortAnimeByPlayCount(List<Anime> animes,int order);

    List<Anime> getRelatedAnime(String name);

    List<Anime> getAllAnime();

    List<Anime> getAnimeByCategory(String category);

    /***
     * 上传动漫的集
     * @param animeID
     * @return
     */
    boolean upload(int animeID);

    /**
     * 添加动漫
     * @param anime
     * @return
     */
    boolean addAnime(Anime anime);


    /**
     * 搜索动漫
     * @param category 分类
     * @param plot 剧情
     * @param year 年代
     * @param loca 地区
     * @param order 排序方式
     * @return
     */
    List<Anime> searchAnime(String category,String version,String plot,String year,String loca,String orderField);


    /**
     * 获得指定分类，指定数量的动漫列表(按照播放次数进行排序)
     * @param category
     * @param n
     * @return
     */
    List<Anime> getTopPlayAnime(String category,int n,int order);
}
