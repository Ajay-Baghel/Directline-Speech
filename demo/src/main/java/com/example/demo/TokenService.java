package com.example.demo;

import com.example.demo.constants.Constants;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class TokenService {

    private String authorizationToken;

    public String getAuthorizationToken() {
        return authorizationToken;
    }

    public void setAuthorizationToken(String authorizationToken) {
        this.authorizationToken = authorizationToken;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    private String region;
    public TokenService(){
//        System.out.println(env.getProperty("speechService.key"));
        HttpClient httpclient = HttpClients.createDefault();

        try
        {
            URIBuilder builder = new URIBuilder("https://centralus.api.cognitive.microsoft.com/sts/v1.0/issueToken");


            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Ocp-Apim-Subscription-Key", Constants.key);


            // Request body
            StringEntity reqEntity = new StringEntity("{body}");
            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();


            if (entity != null)
            {
                this.authorizationToken = EntityUtils.toString(entity);
                this.region = "centralus";
//                System.out.println(authorizationToken);
            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

}
