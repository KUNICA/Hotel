package com.services.compare.ui;

import com.entity.UsersFildsEntity;
import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 08.08.2016.
 */
public class  SearchFields {
   public static List getFlatsList(UsersFildsEntity compare){
       List flatsArray = new ArrayList();
       if(compare!=null && !Strings.isNullOrEmpty(compare.getCompare())){
           String fieldsString = compare.getCompare();
           String[] fields = fieldsString.split(" ");
           for (String it:fields) {
               Long field = new Long(it);
               flatsArray.add(field);
           }
       }
       return flatsArray;
   }
}
