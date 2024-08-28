package Client;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.client.RestTemplate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class OWASP_Client_App {

    private static String PROJECT_DETAILS_URL;
    private static String SECURITY_TOKEN = "29089de836a3a9385d0627d07092663db87c6859";
    private static User_Config user_config;

    public static void main(String[] args) throws JSONException, SQLException {
        ApplicationContext context = SpringApplication.run(OWASP_Client_App.class, args);
        OWASP_Component owasp_query_service = context.getBean(OWASP_Component.class);
        //user_config = start();
        user_config = new User_Config("","","https://codeanalyzer2.internal.ericsson.com","com.ericsson.oss.mediation.pm.handlers:apg-pm-handlers-code");
        RestTemplate rt = owasp_query_service.createAuthenticationTemplate(user_config);
        JSONObject jsonObject = owasp_query_service.getIssues(rt);
        System.out.println(jsonObject);
        JSON_Parser parser = new JSON_Parser(jsonObject);
        List<List> list = parser.parse();

        Database.PostgresJDBC postgresJDBC = new Database.PostgresJDBC();
        Connection c =postgresJDBC.connect();

        int end =2;


        postgresJDBC.createTable(c,"ImplementationTest7");
        postgresJDBC.populateTable(list.get(0),list.get(1),list.get(2),c,"ImplementationTest7");
        List<String> owasps = new ArrayList<>();
        owasps.add("a1 - Injection                                  ");
        owasps.add("a2 - Broken Authentication                      ");
        owasps.add("a3 - Sensitive Data Exposure                    ");
        owasps.add("a4 - XML External Entities (XXE)                ");
        owasps.add("a5 - Broken Access Control                      ");
        owasps.add("a6 - Security Misconfiguration                  ");
        owasps.add("a7 - Cross-Site Scripting (XSS)                 ");
        owasps.add("a8 - Insecure Deserialization                   ");
        owasps.add("a9 - Using Components with Known Vulnerabilities");
        owasps.add("a10 - Insufficient Logging & Monitoring         ");
        System.out.println("Category\t                                        Major\tMinor\tBlocker\tCritical\tInfo\tHotspots");
        for(int i = 0; i<owasps.size();i++){
            if(i==9){end =3;}
            int[] results = postgresJDBC.QueryDB(c,"ImplementationTest7",owasps.get(i).substring(0,end));
            System.out.println(owasps.get(i)+"\t"+results[1]+"\t"+results[2]+"\t"+results[3]+"\t"+results[4]+"\t"+results[5]+"\t"+results[0]);
        }


    }

    public static User_Config start() {
        Scanner in = new Scanner(System.in);
        //Console console = System.console(); //#Console to read password securely
        String username, password, projectUrl, projectKey = "";
        System.out.println("################-Login-################");
        System.out.println("Enter username or 'quit' :");
        username = in.nextLine();
        if (username.equalsIgnoreCase("quit") )
            System.exit(0);
        //char[] pwd = console.readPassword("Password : "); //#For future increase of security
        System.out.println("Enter password ");
        password = in.nextLine();
        System.out.println("\n\n\n\n\nEnter project url ");
        projectUrl = in.nextLine();
        System.out.println("Enter project key ");
        projectKey = in.nextLine();
        return new User_Config(username, password, projectUrl, projectKey);
    }

    /*@Bean
    public static RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.basicAuthentication(SECURITY_TOKEN, "").build(); //29089de836a3a9385d0627d07092663db87c6859
    }*/
}

