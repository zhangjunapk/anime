package org.zj.anime.bean;

import java.util.Date;

/**
 * 播放记录
 */
public class PlayHistory {
    private int anime_id;
    private int episode;
    private Date date;

    public PlayHistory() {
    }

    public PlayHistory(int anime_id, int episode, Date date) {
        this.anime_id = anime_id;
        this.episode = episode;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
