package org.zj.anime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.zj.anime.bean.Anime;
import org.zj.anime.bean.Upload;
import org.zj.anime.dao.IDeleteMapper;
import org.zj.anime.dao.IInsertMapper;
import org.zj.anime.dao.IQueryMapper;
import org.zj.anime.dao.IUpdateMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnimeServiceImpl implements IAnimeService {
    @Autowired
    IDeleteMapper deleteMapper;

    @Autowired
    IInsertMapper insertMapper;

    @Autowired
    IUpdateMapper updateMapper;

    @Autowired
    IQueryMapper queryMapper;

    @Autowired
    PlayServiceImpl playService;


    @Override
    public List<Anime> searchAnimeByName(String name) {
        List<Anime> result=new ArrayList<>();
        for(Anime anime:getAllAnime()){
            if(anime.getName()!=null&&anime.getName().contains(name)){
                result.add(anime);
            }
        }
        return result;
    }

    @Override
    public List<Anime> getTopNewAnimeByLoca(String category, int limit) {
        return queryMapper.selectAnimeByCategoryOrderByDate(category,limit);
    }

    @Override
    public boolean modifyAnime(Anime anime) {
        return updateMapper.updateAnime(anime);
    }

    @Override
    public boolean deleteAnimeByID(int id) {
        return deleteMapper.deleteAnimeByPrimaryKey(id);
    }

    @Override
    public boolean uploadEpisode(Upload upload) {
        return insertMapper.insertVideo(upload);
    }

    @Override
    public Map<Anime, String> setStatusInfo(List<Anime> animes) {
        HashMap<Anime,String> result=new HashMap<>();
        for(Anime anime:animes){
            result.put(anime,getAnimeStatusInfo(anime.getId()));
        }
        return result;
    }

    @Override
    public int getAnimeLastEpisode(int id) {
        int result=-1;
        for(Upload upload:getEpisodeByAnimeID(id)){
            if(upload.getEpisode()>result){
                result=upload.getEpisode();
            }
        }
        return result;
    }

    @Override
    public String getAnimeStatusInfo(int id) {
        System.out.println("get anime by id:----->"+id);
        Anime animeByID = getAnimeByID(id);
        if(animeByID.getStatus()!=null&&animeByID.getStatus().equals("over")){
            return "over";
        }
        if(animeByID.getStatus()!=null&&animeByID.getStatus().equals("update")){
            return "update to"+getAnimeLastEpisode(id);
        }
        return null;
    }

    @Override
    public List<Anime> getAllAnimeOrderByDateDesc() {
        return queryMapper.selectAnimeOrderByDate();
    }

    @Override
    public List<Anime> sortAnimeByDate(List<Anime> animes) {
        List<Anime> result=new ArrayList<>();
        for(Anime anime:getAllAnimeOrderByDateDesc()){
            for(int i=0;i<animes.size();i++){
                if(anime.getId()==animes.get(i).getId()){
                    result.add(animes.get(i));
                    animes.remove(i);
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public Upload getUploadByAnimeIDAndEpisode(int animeID, int episode) {
        for(Upload upload:getEpisodeByAnimeID(animeID)){
            if(upload.getEpisode()==episode){
                return upload;
            }
        }
        return null;
    }

    @Override
    public Anime getAnimeByID(int id) {
        return queryMapper.selectAnimeByID(id);
    }

    @Override
    public List<Upload> getEpisodeByAnimeID(int id) {
        return queryMapper.selectAllUploadByAnimeID(id);
    }

    /**
     *
     * @param category
     * @param top
     * @return
     */
    @Override
    public List<Anime> getAnimeByCategoryOrderByDate(String category, int top) {
        List<Anime> result=new ArrayList<>();
        for(Anime anime:queryMapper.selectAnimeOrderByDate()){
            System.out.println(anime.getCategory()+":"+category);
            if(anime.getCategory()!=null&&anime.getCategory().contains(category)){
                result.add(anime);
            }
        }
        System.out.println(category+"---->find size"+result.size());
        return result.subList(0,top>=result.size()?result.size():top);
    }

    @Override
    public List<Anime> sortAnimeByPlayCount(List<Anime> animes, int order) {
        List<Anime> result=new ArrayList<>();
        //先获得所有(根据播放次数排序)的动漫
        for(Anime anime:getTopPlayAnime(null,-1,order)){
            for(int i=0;i<animes.size();i++){
                //然后如果得劲儿了放进去
                if(animes.get(i).getId()==anime.getId()){
                    result.add(animes.get(i));
                    animes.remove(i);
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 获得相关动漫
     * @param name
     * @return
     */
    @Override
    public List<Anime> getRelatedAnime(String name) {
        List<Anime> result=new ArrayList<>();
        for(Anime anime:getAllAnime()){
            if(anime.getName().equals(name)){
                result.add(anime);
            }
        }
        return result;
    }

    @Override
    public List<Anime> getAllAnime() {
        System.out.println(queryMapper==null?"kong-----":"------not");
        System.out.println(queryMapper.selectAllAnime().size()+"find ---------->");
        return queryMapper.selectAllAnime();
    }

    @Override
    public List<Anime> getAnimeByCategory(String category) {
       List<Anime> result=new ArrayList<>();
        List<Anime> animes = queryMapper.selectAllAnime();
        for(Anime anime:animes){
            if(anime.getCategory().contains(category)){
                result.add(anime);
            }
        }
        return result;
    }

    @Override
    public boolean upload(int animeID) {
        return false;
    }

    @Override
    public boolean addAnime(Anime anime) {
        return insertMapper.insertAnime(anime);
    }

    /**
     * 搜索动漫 参数都可以为空
     * @param category china anime japan anime west anime
     * @param type action love fun
     * @param year 年代
     * @param loca 地区
     * @param orderField field
     * @return
     */
    @Override
    public List<Anime> searchAnime(String category, String version,String type, String year, String loca,String orderField) {

        System.out.println(category+"<->"+version+"<->"+type+"<->"+year+"<->"+loca+"<->"+orderField);

        List<Anime> result=queryMapper.selectAllAnime();
        if(version!=null&&version!=""){
            for(int i=0;i<result.size();i++){
                if(result.get(i).getVersion()!=null&&!result.get(i).getVersion().contains(version)){
                    result.remove(i);
                }
            }
        }

        if(category!=null&&category!=""){
            for(int i=0;i<result.size();i++){
                System.out.print("category--->"+category+" : "+result.get(i).getCategory());
                if(result.get(i).getCategory()!=null&&!result.get(i).getCategory().contains(category)){
                    System.out.println("  XXXXXXXXXX");
                    result.remove(i);
                }
            }
        }

        if(type!=null&&type!=""){
            for(int i=0;i<result.size();i++){
                if(result.get(i).getType()!=null&&!result.get(i).getType().contains(type)){
                    result.remove(i);
                }
            }
        }

        if(year!=null&&year!=""){
            for(int i=0;i<result.size();i++){
                System.out.print(year+":"+result.get(i).getYear()+" ");
                if(result.get(i).getYear()!=null&&!result.get(i).getYear().contains(year)){
                    System.out.println("  remove------->");
                    result.remove(i);
                }
            }
        }

        if(loca!=null&&loca!=""){
            for(int i=0;i<result.size();i++){
                if(result.get(i).getLoca()!=null&&!result.get(i).getLoca().contains(loca)){
                    result.remove(i);
                }
            }
        }


        if(orderField!=null&&orderField!="") {
            //按照播放次数进行降序排序
            if (orderField.equals("playCount")) {
                return sortAnimeByPlayCount(result, 0);
            }
            if (orderField.equals("date")) {
                return sortAnimeByDate(result);
            }
        }else{
            return sortAnimeByDate(result);
       }
        System.out.println("--=-=-=-==-=--==->"+result.size());

        for(Anime anime:result){
            System.out.println(anime.getId()+"    00");
        }


       return result;

    }

    @Override
    public List<Anime> getTopPlayAnime(String category, int n,int order) {
        List<Anime> result=new ArrayList<>();
        List<Map.Entry<Anime, Integer>> playCountEntryList = playService.getPlayCountEntryList(category);

        //检测是查看所有分类还是指定分类的
        if(category==null){
            playCountEntryList=playService.getPlayCountEntryList();
            System.out.println(playCountEntryList.size()+category+"<-----------");
        }
        if(order==0){
            //降序
            for(int i=0;i<playCountEntryList.size();i++){
                for(int j=0;j<i;j++){
                    if(playCountEntryList.get(i).getValue()<playCountEntryList.get(j).getValue()){
                        //交换
                        Map.Entry<Anime,Integer> temp=playCountEntryList.get(i);
                        playCountEntryList.set(i,playCountEntryList.get(j));
                        playCountEntryList.set(j,temp);
                    }

                }
            }

        }
        if(order==1){
            //升序
            for(int i=0;i<playCountEntryList.size();i++){
                for(int j=0;j<i;j++){
                    if(playCountEntryList.get(i).getValue()>playCountEntryList.get(j).getValue()){
                        //交换
                        Map.Entry<Anime,Integer> temp=playCountEntryList.get(i);
                        playCountEntryList.set(i,playCountEntryList.get(j));
                        playCountEntryList.set(j,temp);
                    }

                }
            }
        }
        for(Map.Entry<Anime,Integer> entry:playCountEntryList){
            result.add(entry.getKey());
        }
        System.out.println(category+":"+result.size()+"************");
        //检测切割长度
        if(n==-1){
            return result;
        }else{
            return result.subList(0,n>=result.size()?result.size():n);
        }
    }


    /**
     * remove not containt name's anime
     * @param map
     * @param name
     */
    public Map<Anime,String> doFilteByName(Map<Anime,String> map,String name){

        List<Anime> animes=new ArrayList<>();

        if(name!=null&&name!=""){
        for(Map.Entry<Anime,String> entry:map.entrySet()){
            if(!entry.getKey().getName().contains(name)){
                animes.add(entry.getKey());
            }
        }
        }

        for(Anime anime:animes){
            map.remove(anime);
        }

        return map;
    }

}
