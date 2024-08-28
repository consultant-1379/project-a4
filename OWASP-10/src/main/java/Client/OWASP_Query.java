package Client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.sql.PreparedStatement;

public class OWASP_Query {

    private RestTemplate restT;

    public static void main(String [] args) throws IOException, JSONException { //String projName, String projRepo
        RestTemplateBuilder restBuilder = new RestTemplateBuilder();
        RestTemplate restT = restTemplate(restBuilder);
        String response = restT.getForObject("https://codeanalyzer2.internal.ericsson.com/api/issues/search?componentRoots=org.codehaus.sonar:sonar&owaspTop10=a1,a2,a3,a4,a5,a6,a7,a8,a9,a10", String.class);
        JSONObject json = new JSONObject(response);
        System.out.println(json);

    }

    public static RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.basicAuthentication("", "").build();
    }

    
}