package org.zj.anime.bean;

import java.util.Date;

public class Upload {
    private int anime_id;//动漫的ID
    private int episode;//这是第多少集
    private String link;//这个视频的链接
    private Date date;//上传日期

    public Upload() {
    }

    public int getAnime_id() {
        return anime_id;
    }

    public void setAnime_id(int anime_id) {
        this.anime_id = anime_id;
    }

    public int getEpisode() {
        return episode;
    }

    public void setEpisode(int episode) {
        this.episode = episode;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Upload(int anime_id, int episode, String link, Date date) {
        this.anime_id = anime_id;
        this.episode = episode;
        this.link = link;
        this.date = date;
    }
}
