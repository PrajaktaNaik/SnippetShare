package com.cmpe275.snippetshare.Manager;

import java.io.File;
import java.io.FileInputStream;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.cmpe275.snippetshare.Model.ImageTest;
import com.cmpe275.snippetshare.Utility.MongoConfig;

public class ImageManager {
	
    public static void insert(){
    	byte b[] = null;
        try
        {
        	String filename = "C://Users//Kunal//Google Drive//Docs//General//Leaving Certificate.jpg";
            String empname ="ABC";
            FileInputStream f = new FileInputStream(new File(filename));
 
            b = new byte[f.available()];
            f.read(b);
            
            ImageTest test = new ImageTest();
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
     
    public static ImageTest retrieve(String name, String filename)
    {
    	ImageTest test = null;
        try
        {
        	
        	Query query = new Query();
    		query.addCriteria(Criteria.where("id").is("553d6bc41f017eaaeba0010b"));
    		
    		test = MongoConfig.getMongoOperationsObj().findOne(query, ImageTest.class);
    		
    		System.out.println("Emp name"+ test.getEmpName()+"Byte Array: "+test.getImage());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return test;
    }
	
}