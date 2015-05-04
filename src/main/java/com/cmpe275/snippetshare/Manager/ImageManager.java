package com.cmpe275.snippetshare.Manager;

import java.io.File;
import java.io.FileInputStream;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.cmpe275.snippetshare.Model.Image;
import com.cmpe275.snippetshare.Utility.MongoConfig;

public class ImageManager {
	
    public static void insert(){
    	byte b[] = null;
        try
        {
        	String filename = "C://Users//harshit//Downloads//2323.jpg";
            String empname ="harshit";
            FileInputStream f = new FileInputStream(new File(filename));
 
            b = new byte[f.available()];
            f.read(b);
            
            Image test = new Image();
            test.setEmpName(empname);
            test.setImage(b);
  
            MongoConfig.getMongoOperationsObj().save(test);
            /*System.out.println("My Data"+b.toString()+"Try"+b);
 
            Binary data = new Binary(b);
            System.out.println("DATA:"+data);
            BasicDBObject o = new BasicDBObject();
            o.append("name",empname).append("photo",data);*/
          /*  collection.insert(o);
            System.out.println("Inserted record.");
 */
            f.close();
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
    public static Image retrieve(String name, String filename)
    {
    	Image test = null;
        try
        {
        	
        	Query query = new Query();
    		query.addCriteria(Criteria.where("id").is("553d6bc41f017eaaeba0010b"));
    		
    		test = MongoConfig.getMongoOperationsObj().findOne(query, Image.class);
    		
    		System.out.println("Emp name"+ test.getEmpName()+"Byte Array: "+test.getImage());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return test;
    }
	
}
