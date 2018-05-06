package org.zj.anime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zj.anime.bean.Anime;
import org.zj.anime.bean.PlayHistory;
import org.zj.anime.dao.IDeleteMapper;
import org.zj.anime.dao.IInsertMapper;
import org.zj.anime.dao.IQueryMapper;
import org.zj.anime.dao.IUpdateMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PlayServiceImpl implements IPlayService {

    @Autowired
    IQueryMapper queryMapper;

    @Autowired
    IInsertMapper insertMapper;

    @Autowired
    AnimeServiceImpl animeService;

    /**
     * 获得所有动漫记录
     * @return
     */
    @Override
    public List<PlayHistory> getAllPlayHistory() {
        return queryMapper.selectPlayHistory();
    }

    /**
     * 获得指定动漫id的播放次数
     * @param id
     * @return
     */
    @Override
    public int getPlayCountByAnimeID(int id) {
        return getPlayHistoryByAnimeID(id).size();
    }

    /**
     * 获得指定动漫id的播放记录
     * @param animeID
     * @return
     */
    @Override
    public List<PlayHistory> getPlayHistoryByAnimeID(int animeID) {
        List<PlayHistory> result=new ArrayList<>();
        List<PlayHistory> playHistories = queryMapper.selectPlayHistory();
        for(PlayHistory playHistory:playHistories){
            if(playHistory.getAnime_id()==animeID){
                result.add(playHistory);
            }
        }
        return result;
    }



    @Override
    public boolean addPlayHistory(PlayHistory playHistory) {
        return insertMapper.insertPlayHistory(playHistory);
    }

    /**
     * 获得指定分类动漫的播放次数Entry
     * @param category
     * @return
     */
    @Override
    public List<Map.Entry<Anime, Integer>> getPlayCountEntryList(String category) {
        List<Map.Entry<Anime,Integer>> result=new ArrayList<>();
        for(Map.Entry<Anime,Integer> entry:getPlayCountEntryList()){
            if(entry.getKey().getCategory()!=null&&(category==null||entry.getKey().getCategory().contains(category))){
                result.add(entry);
            }
        }
        return result;
    }

public void tt(){
        animeService.getAllAnime();
    System.out.println("ttttttttttttttttttttttttttttttttttttttttttttttttttttt");
}


    /**
     * 获得所有动漫的实体和次数的键值对
     * @return
     */
    @Override
    public List<Map.Entry<Anime, Integer>> getPlayCountEntryList() {

        List<Map.Entry<Anime,Integer>> result=new ArrayList<>();
        //通过分类获得所有动漫

        // TODO: 18-4-29
        //然后遍历所有动漫,通过动漫id来获得播放次数
        List<Anime> allAnime = queryMapper.selectAllAnime();
        for(Anime anime:allAnime){
            result.add(new Map.Entry<Anime, Integer>() {
                @Override
                public Anime getKey() {
                    return anime;
                }

                @Override
                public Integer getValue() {
                    return getPlayCountByAnimeID(anime.getId());
                }

                @Override
                public Integer setValue(Integer value) {
                    return value;
                }
            });
        }
        return result;
    }
}
