package org.zj.anime.dao;

import org.apache.ibatis.annotations.Insert;
import org.zj.anime.bean.Anime;
import org.zj.anime.bean.PlayHistory;
import org.zj.anime.bean.Upload;

public interface IInsertMapper {
    /**
     *  private int animeID;//动漫的ID
     *     private int episodes;//这是第多少集
     *     private String link;//这个视频的链接
     *     private Date date;//上传日期
     * @param upload
     * @return
     */
    @Insert("insert into upload(anime_id,episode,link,date) values(#{anime_id},#{episode},#{link},#{date})")
    boolean insertVideo(Upload upload);

    /**
     * private int id;//id
     *     private String category;//分类
     *     private String pic_link;//封面图片连接
     *     private String plot;//剧情
     *     private String year;//年代
     *     private String language;//语言
     *     private String loca;//地区
     *     private String role;//角色
     *     private String voice_actor;//声优
     *     private String alias;//别名
     *     private String info;//信息
     *     private String status;//状态
     */
    @Insert("insert into anime(category,name,version,pic_link,type,year,language,loca,role,voice_actor,alias,info,status,createDate) values(#{category},#{name},#{version},#{pic_link},#{type},#{year},#{language},#{loca},#{role},#{voice_actor},#{alias},#{info},#{status},#{createDate})")
    boolean insertAnime(Anime anime);

    /**
     *  private int anime_id;
     *     private int episode;
     *     private Date date;
     * @param playHistory
     * @return
     */
    @Insert("insert into play_history(anime_id,episode,date) values(#{anime_id},#{episode},#{date})")
    boolean insertPlayHistory(PlayHistory playHistory);
}
