package Client;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JSON_Parser {

    static JSONObject jsonObject;

    public JSON_Parser(JSONObject jsonObject){
        this.jsonObject = jsonObject;
    }

    public List<List> parse() throws JSONException {
        List<String> severities_list = new ArrayList<String>();
        List<String> types_list = new ArrayList<String>();
        //List<List> tags_list = new ArrayList<List>();
        List<JSONArray> tags_array = new ArrayList<>();
        JSONArray array = jsonObject.getJSONArray("issues");
        //String [] tag= new String[];
        for(int i = 0 ; i < array.length() ; i++){
            if(array.getJSONObject(i).has("severity")){
            severities_list.add(array.getJSONObject(i).getString("severity"));
            }else{
                severities_list.add("BLANK");
            }
            tags_array.add(array.getJSONObject(i).getJSONArray("tags"));
            types_list.add(array.getJSONObject(i).getString("type"));
            //tags_list.add((List) array.getJSONObject(i).getJSONArray("tags"));
        }
//        System.out.println(severities_list);
//        System.out.println(types_list);
//        System.out.println(tags_array);

        List<List> list = new ArrayList<>();
        list.add(severities_list);
        list.add(types_list);
        list.add(tags_array);

        return list;



        //System.out.println("DB working");

    }


}