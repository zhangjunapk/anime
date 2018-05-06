package org.zj.anime.dao;

import org.apache.ibatis.annotations.Update;
import org.zj.anime.bean.Anime;
import org.zj.anime.bean.Upload;

public interface IUpdateMapper {

    /**
     * private String category;//分类
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
     * @param anime
     * @return
     */
    @Update("update anime set category=#{category},name=#{name},version=#{version},pic_link=#{pic_link},type=#{type},year=#{year},language=#{language},loca=#{loca},role=#{role},voice_actor=#{voice_actor},alias=#{alias},info=#{info},status=#{status} where id=#{id}")
    boolean updateAnime(Anime anime);

    /**
     * private int anime_id;//动漫的ID
     *     private int episode;//这是第多少集
     *     private String link;//这个视频的链接
     *     private Date date;//上传日期
     * @param uploadItem
     * @return
     */
    @Update("update upload set episode=#{episode},set link=#{link}")
    boolean updateEpisode(Upload uploadItem);
}
