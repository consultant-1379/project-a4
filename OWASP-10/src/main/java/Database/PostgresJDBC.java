package Database;

import org.json.JSONArray;
import org.json.JSONException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

import java.util.logging.Logger;
public class PostgresJDBC {

    public static void main(String[] args) {

        System.out.println("Opened database successfully");
    }

    private Logger logger
            = Logger.getLogger(
            PostgresJDBC.class.getName());

    public Connection connect(){
        Connection c = null;
        String pass = "admin";
        try{
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://postgres:5432/testdb","postgres", pass);


        } catch (ClassNotFoundException | SQLException e) {
            logger.log(Level.INFO, String.valueOf(e));
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        return c;
    }

    private String getDBPassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter DB password");
        return scanner.nextLine();
    }


    public  void createTable(Connection c, String tableName){
        try  {
            Statement stmt = c.createStatement();
            String sql = "CREATE TABLE tablename (CATEGORIES TEXT[], SEVERITY TEXT, TYPE TEXT);";
            sql = sql.replace("tablename",tableName);
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            logger.log(Level.INFO, String.valueOf(e));
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Table Created Successfully");
    }



    public void populateTable(List severities, List types, List tags, Connection c, String table) throws SQLException {
        Statement stmt = null;
        try{
            stmt = c.createStatement();
            String sql = "";
            JSONArray b = null;
            for(int i = 0; i<severities.size();i++){
                String a ="{";
                b = (JSONArray) tags.get(i);
                for (int j=0;j<b.length();j++){
                    if(j>0){a+=",";}
                    a = a+ '"'+b.get(j)+'"';
                }

                a+="}";
                sql = "INSERT INTO "+table.toUpperCase()+"  VALUES ('"+a+"','"+severities.get(i)+"','"+types.get(i)+"');";
                //System.out.println(sql);
                stmt.executeUpdate(sql);
            }
            stmt.close();
            //c.commit();
        } catch (SQLException e) {
            logger.log(Level.INFO, String.valueOf(e));
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        } catch (JSONException e) {
            logger.log(Level.INFO, String.valueOf(e));
        }finally{if(stmt!= null){stmt.close();}}
        System.out.println("Inserted Successfully");

    }

    public int[] QueryDB(Connection c, String table, String owasp_type){
        int hotspots = 0,major=0,minor=0,blocker=0,critical=0,info =0;
        int[] res = new int [6];
        try {
            Statement stmt = c.createStatement();
            String sql = ("SELECT * FROM table WHERE 'owasp-type' = ANY(categories);");
            String rsql = sql.replace("table",table);
            rsql = rsql.replace("type",owasp_type);
            ResultSet rs = stmt.executeQuery(rsql);
            while ( rs.next() ) {
                Array categories = rs.getArray("categories");
                String  severity = rs.getString("severity");
                String  type = rs.getString("type");
//                System.out.println( "categories = " + categories );
//                System.out.println( "severity = " + severity );
//                System.out.println( "type = " + type );
//                System.out.println();
                if(type.equals("SECURITY_HOTSPOT")){hotspots++;}
                switch (severity) {
                    case "MAJOR":
                        major++;
                        break;
                    case "MINOR":
                        minor++;
                        break;
                    case "BLOCKER":
                        blocker++;
                        break;
                    case "CRITICAL":
                        critical++;
                        break;
                    case "INFO":
                        info++;
                        break;
                }

            }
            rs.close();
            stmt.close();
            res[0]=hotspots;res[1]=major;res[2]=minor;res[3]=blocker;res[4]=critical;res[5]=info;

        } catch (SQLException e) {
            logger.log(Level.INFO, String.valueOf(e));
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        return res;
    }

}
