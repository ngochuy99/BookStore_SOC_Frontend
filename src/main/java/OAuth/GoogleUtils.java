package OAuth;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class GoogleUtils {
    public static String getToken(final String code) throws ClientProtocolException, IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(Constants.GOOGLE_LINK_GET_TOKEN);
        JSONObject body = new JSONObject();
        body.put("client_id",Constants.GOOGLE_CLIENT_ID);
        body.put("client_secret",Constants.GOOGLE_CLIENT_SECRET);
        body.put("redirect_uri",Constants.GOOGLE_REDIRECT_URI);
        body.put("code",code);
        body.put("grant_type",Constants.GOOGLE_GRANT_TYPE);
        StringEntity entity = new StringEntity(body.toString());
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        CloseableHttpResponse response = httpClient.execute(httpPost);
        String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        JSONObject responseJson = new JSONObject(responseBody);
        return responseJson.getString("access_token");
    }

    public static GooglePojo getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = Constants.GOOGLE_LINK_GET_USER_INFO + accessToken;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(link);
        CloseableHttpResponse response =  httpClient.execute(httpGet);
        String responseBody = EntityUtils.toString(response.getEntity(),StandardCharsets.UTF_8);
        JSONObject responseJson = new JSONObject(responseBody);
        GooglePojo googlePojo = new GooglePojo();
        googlePojo.setId(responseJson.getString("id"));
        googlePojo.setEmail(responseJson.getString("email"));
        googlePojo.setVerified_email(responseJson.getBoolean("verified_email"));
        googlePojo.setLink(responseJson.getString("picture"));
        return googlePojo;
    }
}