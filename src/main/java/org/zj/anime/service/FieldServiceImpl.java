package org.zj.anime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zj.anime.dao.IQueryMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class FieldServiceImpl implements IFieldService {


    @Autowired
    IQueryMapper queryMapper;


    private List<String> getAllFieldValue(String fieldName) {
        List<String> result=new ArrayList<>();
        System.out.println("getallfieldvalue"+fieldName+"<---->"+queryMapper.selectAllValueByFieldName(fieldName).size());

        for(String str:queryMapper.selectAllValueByFieldName(fieldName)){
            System.out.println(str+"(-----------)");

           /* for(String s:str.split("<->")){
                System.out.println(s+"<->"+fieldName);
                if(!result.contains(s)){
                    result.add(s);
                }
            }*/

        }


        return result;
    }



    @Override
    public List<String> getAllCategory() {
        return queryMapper.selectAllCategory();
    }

    @Override
    public List<String> getAllType() {
        List<String> result=new ArrayList<>();
        List<String> strings = queryMapper.selectAllType();
        for(String str:strings){
            String[] split = str.split("<->");
            for(String s:split) {

                if (!(result.contains(s))) {
                    //be careful
                    result.add(s);
                }
            }
        }
        return result;
    }

    @Override
    public List<String> getAllYeay() {
        return queryMapper.selectAllYear();
    }

    @Override
    public List<String> getAllVersion() {
        return queryMapper.selectAllVersion();
    }

    @Override
    public List<String> getAllLoca() {
        return queryMapper.selectAllLoca();
    }
}
