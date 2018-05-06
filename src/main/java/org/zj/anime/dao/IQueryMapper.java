package org.zj.anime.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zj.anime.bean.Anime;
import org.zj.anime.bean.PlayHistory;
import org.zj.anime.bean.Upload;

import java.util.List;

public interface IQueryMapper {

    @Select("select distinct version from anime")
    List<String> selectAllVersion();

    @Select("select type from anime")
    List<String> selectAllType();

    @Select("select distinct year from anime")
    List<String> selectAllYear();

    @Select("select distinct loca from anime")
    List<String> selectAllLoca();

    @Select("select distinct category from anime")
    List<String> selectAllCategory();

    @Select("select #{fieldName} from anime")
    List<String> selectAllValueByFieldName(@Param("fieldName") String fieldName);

    @Select("select * from anime where id=#{id}")
    Anime selectAnimeByID(int id);

    @Select("select * from anime order by createDate desc")
    List<Anime> selectAnimeOrderByDate();

    @Select("select * from anime")
    List<Anime> selectAllAnime();

    @Select("select * from upload where anime_id=#{id}")
    List<Upload> selectAllUploadByAnimeID(int id);

    @Select("select * from play_history")
    List<PlayHistory> selectPlayHistory();

    @Select("select * from anime where category like #{category} order by createDate desc limit #{limit}")
    List<Anime> selectAnimeByCategoryOrderByDate(@Param("category") String category, @Param("limit") int limit);


}
