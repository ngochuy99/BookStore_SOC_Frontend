package Facebook;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class FaceBookUtils {

    public static String getToken(final String code) throws ClientProtocolException, IOException {
        String link = String.format(FbConstants.FACEBOOK_LINK_GET_TOKEN, FbConstants.FACEBOOK_APP_ID, FbConstants.FACEBOOK_APP_SECRET, FbConstants.FACEBOOK_REDIRECT_URL, code);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpPost = new HttpGet(link);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        JSONObject responseJson = new JSONObject(responseBody);
        return responseJson.getString("access_token");
    }

//    public static User getUserInfo(String accessToken) {
//        FacebookClient facebookClient = new DefaultFacebookClient(accessToken, Constants.FACEBOOK_APP_SECRET, Version.LATEST);
//        return facebookClient.fetchObject("me", User.class);
//    }
}