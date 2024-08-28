package Client;

import Client.User_Config;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

public interface IOWASP_query {

    RestTemplate createAuthenticationTemplate(User_Config user_Config);

    JSONObject getIssues(RestTemplate restTemplate) throws JSONException;


}
