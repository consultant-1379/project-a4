package Client;

import Database.PostgresJDBC;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;


@Component
public class OWASP_Component implements IOWASP_query {


    private RestTemplate restTemplate;
    private static String SECURITY_TOKEN; //= "29089de836a3a9385d0627d07092663db87c6859";
    private static String USER_NAME;
    private static User_Config user;
    private Logger logger
            = Logger.getLogger(
            PostgresJDBC.class.getName());

    @Override
    public RestTemplate createAuthenticationTemplate(User_Config user_Config) {
        user = user_Config;
        SECURITY_TOKEN = user.getPassword();
        USER_NAME = user.getUsername();
        RestTemplateBuilder rt = new RestTemplateBuilder();
        return restTemplate = rt.basicAuthentication(USER_NAME, SECURITY_TOKEN).build();
    }

    @Override
    public JSONObject getIssues(RestTemplate restTemplate) {

        try{
            String response = restTemplate.getForObject(
                    user.getProjectUrl()+"/api/issues/search?componentKeys="+user.getProjectKey()+"&owaspTop10=a1,a2,a3,a4,a5,a6,a7,a8,a9,a10", String.class);
            JSONObject json = new JSONObject(response);
            return json;
        } catch (JSONException e) {
            logger.log(Level.WARNING, String.valueOf(e));
        }
        return null;
    }
}
