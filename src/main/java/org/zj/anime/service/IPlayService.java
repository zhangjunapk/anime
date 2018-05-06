package org.zj.anime.service;

import org.zj.anime.bean.Anime;
import org.zj.anime.bean.PlayHistory;
import sun.nio.cs.HistoricallyNamedCharset;

import java.util.List;
import java.util.Map;

public interface IPlayService {

    List<PlayHistory> getAllPlayHistory();

    int getPlayCountByAnimeID(int id);

    /**]
     *  private int anime_id;
     *     private int episode;
     *     private Date date;
     */
    /**
     * 获得指定动漫的播放记录
     * @param animeID
     * @return
     */
    List<PlayHistory> getPlayHistoryByAnimeID(int animeID);


    /**
     * 添加播放记录
     * @param playHistory
     * @return
     */
    boolean addPlayHistory(PlayHistory playHistory);

    /**
     * 根据分类来获得保存了动漫和播放次数键值对的list
     * @param category
     * @return
     */
    List<Map.Entry<Anime,Integer>> getPlayCountEntryList(String category);


    List<Map.Entry<Anime,Integer>> getPlayCountEntryList();
}
