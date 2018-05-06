package org.zj.anime.bean;

import java.util.Date;

public class Anime {
    /**
     * 妖精的尾巴连载至276集
     * 2016-03-19更新  状态：待更中...
     * 类型：日本动漫 热血 动作 神魔
     * 发音：日语  地区：日本  年代：2009
     * 角色：纳兹·多拉格尼尔 露西·哈特菲利亚
     * 声优：大原沙耶香 羽多野涉 佐藤聪美
     * 别名：妖精的尾巴第一季/妖精的尾巴第二季
     * 剧情：少女露西一直希望能加入云集众多厉害魔法师的名为“妖精尾巴”的公会，在纳兹的引导下，露西终于得偿所愿，随后，露西跟纳兹、格雷、艾尔莎和哈比组成“最强！？”队伍，二男二女一猫的旅程就此展开……展开
     */
    private int id;//id
    private String name;
    private String category;//分类 japan anime dimensional movie
    private String version;
    private String pic_link;//封面图片连接
    private String type;//剧情
    private String year;//年代
    private String language;//语言
    private String loca;//地区
    private String role;//角色
    private String voice_actor;//声优
    private String alias;//别名
    private String info;//信息
    private String status;//状态
    private Date createDate;

    public Anime() {
    }


    @Override
    public String toString() {
        return "Anime{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", version='" + version + '\'' +
                ", pic_link='" + pic_link + '\'' +
                ", plot='" + type + '\'' +
                ", year='" + year + '\'' +
                ", language='" + language + '\'' +
                ", loca='" + loca + '\'' +
                ", role='" + role + '\'' +
                ", voice_actor='" + voice_actor + '\'' +
                ", alias='" + alias + '\'' +
                ", info='" + info + '\'' +
                ", status='" + status + '\'' +
                ", createDate=" + createDate +
                '}';
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Anime(int id, String category, String name, String version, String pic_link, String type, String year, String language, String loca, String role, String voice_actor, String alias, String info, String status, Date createDate) {
       this.name=name;
        this.id = id;
        this.version=version;
        this.category = category;
        this.pic_link = pic_link;
        this.type = type;
        this.year = year;
        this.language = language;
        this.loca = loca;
        this.role = role;
        this.voice_actor = voice_actor;
        this.alias = alias;
        this.info = info;
        this.status = status;
        this.createDate=createDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPic_link() {
        return pic_link;
    }

    public void setPic_link(String pic_link) {
        this.pic_link = pic_link;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLoca() {
        return loca;
    }

    public void setLoca(String loca) {
        this.loca = loca;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getVoice_actor() {
        return voice_actor;
    }

    public void setVoice_actor(String voice_actor) {
        this.voice_actor = voice_actor;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
