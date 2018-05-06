package org.zj.anime.dao;

import org.apache.ibatis.annotations.Delete;

public interface IDeleteMapper {

    @Delete("delete from anime where id=#{id}")
    boolean deleteAnimeByPrimaryKey(int id);

    @Delete("delete from upload where anime_id=#{animeID} and episode=#{episodesID}")
    boolean deleteEpisodesByPrimaryKey(int animeID,int episodesID);
}
