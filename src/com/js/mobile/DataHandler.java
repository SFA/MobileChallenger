package com.js.mobile;

import android.util.Base64;
import android.util.Log;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: JohnGyselinck
 * Date: 1/17/12
 * Time: 10:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class DataHandler {
//    private static String baseUrl = "http://10.0.2.2/mobilechallenger/";
    private static String baseUrl = "http://192.168.1.172/mobilechallenger/";
//    private static String baseUrl = "http://192.168.8.160/mobilechallenger/";

    private static Hashtable<String, User> users = new Hashtable<String, User>();

    public static boolean doLogin(String username, String password){
        String result = null;
        HttpPost request = null;

        try{
            String url = baseUrl + "loginService.php";
            request = new HttpPost(url);
//            request.setURI(new URI(url));
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("user", username));
            nameValuePairs.add(new BasicNameValuePair("pass", password));
            request.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        } catch (Exception ex){
            ex.printStackTrace();
        }

        result = doPostRequest(request);

        if(result.equalsIgnoreCase("TRUE")){
            return true;
        } else {
            return false;
        }

    }

    public static void retrieveUsers() {
        String result = null;
        HttpPost request = null;

        try{
//            String url = baseUrl + "getUsers.php";
            String url = baseUrl + "leaderboard.php";
            request = new HttpPost(url);
        } catch (Exception ex){
            ex.printStackTrace();
        }

        result = doPostRequest(request);

//        JSONObject myjson = null;
        JSONArray the_json_array = null;
        try {
//            myjson = new JSONObject(result);
//            the_json_array = myjson.getJSONArray("data");
            the_json_array = new JSONArray(result);
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        JSONObject mJsonObject = new JSONObject();
        try {
            for(int i=0; i < the_json_array.length(); i++) {
                mJsonObject = the_json_array.getJSONObject(i);
                String username = mJsonObject.getString("username");
                String fname = mJsonObject.getString("first_name");
                String lname = mJsonObject.getString("last_name");
                String email = mJsonObject.getString("email");
                int wins = mJsonObject.getInt("Wins");
                int losses = mJsonObject.getInt("Losses");
                User user = new User(username, lname, fname, email, wins, losses);
                users.put(username, user);
            }
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public static ArrayList<HashMap<String,String>> getAllUsers() {
        ArrayList<HashMap<String,String>> userNames = new ArrayList<HashMap<String,String>>();
        for(User u : users.values()) {
            HashMap<String, String> userMap = new HashMap<String, String>();
            userMap.put("username", u.getUserName());
            userMap.put("fname", u.getFirstName());
            userMap.put("lname", u.getLastName());
            userMap.put("wins", Integer.toString(u.getWins()));
            userMap.put("losses", Integer.toString(u.getLosses()));
            userNames.add(userMap);
        }
        return userNames;
    }

    public static User getUser(String userName) {
        return users.get(userName);
    }

    public static boolean doRegistration(String username, String password, String fname, String lname, String email){
        String result = null;
        HttpPost request = null;

        try{
            String url = baseUrl + "registerUser.php";
            request = new HttpPost(url);
            request.setURI(new URI(url));
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("user", username));
            nameValuePairs.add(new BasicNameValuePair("pass", password));
            nameValuePairs.add(new BasicNameValuePair("email", email));
            nameValuePairs.add(new BasicNameValuePair("fname", fname));
            nameValuePairs.add(new BasicNameValuePair("lname", lname));
            request.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            result = doPostRequest(request);
            return true;
        } catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
    
    public static boolean doChallenge(String challengee, String challenger) {
        String result = null;
        HttpPost request = null;

        try{
            String url = baseUrl + "submitChallenge.php";
            request = new HttpPost(url);
            request.setURI(new URI(url));
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("challengee", challengee));
            nameValuePairs.add(new BasicNameValuePair("challenger", challenger));
            request.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            result = doPostRequest(request);
            sendChallengeEmail(challengee, challenger);
            return true;
        } catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
    
    private static boolean sendChallengeEmail(String challengee, String challenger) {
        String result = null;
        HttpPost request = null;

        try{
            String url = baseUrl + "emailService.php";
            request = new HttpPost(url);
            request.setURI(new URI(url));
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("mailType", "challenge"));
            nameValuePairs.add(new BasicNameValuePair("challengee", challengee));
            nameValuePairs.add(new BasicNameValuePair("challenger", challenger));
            request.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            result = doPostRequest(request);
            return true;
        } catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
    
    private static String doPostRequest(HttpPost request){
        StringBuilder sb = new StringBuilder();
        try{
            HttpClient client = new DefaultHttpClient();

            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            InputStream is = entity.getContent();

            //convert response to string
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"));

            String line = null;
            while((line = reader.readLine()) != null){
                sb.append(line + "\n");
            }
            is.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        return (sb.toString()).replace("\n", "");
    }

    public static List<String> getChallenges(String userName) {
        String result = null;
        HttpPost request = null;

        try{
            String url = baseUrl + "getChallenges.php";
            request = new HttpPost(url);
            request.setURI(new URI(url));
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("username", userName));
            request.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            result = doPostRequest(request);
        } catch (Exception ex){
            ex.printStackTrace();
        }

        result = doPostRequest(request);

        JSONObject myjson = null;
        JSONArray the_json_array = null;
        try {
            myjson = new JSONObject(result);
            the_json_array = myjson.getJSONArray("data");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        List<String> challenges = new ArrayList<String>();

        JSONObject mJsonObject = new JSONObject();
        try {
            for(int i=0; i < the_json_array.length(); i++) {
                mJsonObject = the_json_array.getJSONObject(i);
                String id = mJsonObject.getString("id");
                String challengee = mJsonObject.getString("challengee");
                String challenger = mJsonObject.getString("challenger");
                challenges.add(id + " : " + challengee + " vs " + challenger);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        return challenges;
    }

    public static void addOutcome(String challenge_id, String challengee, String challenger,
                                  String challengeePassword, String challengeeScore,
                                  String challengerScore, String trashTalk) {
        String result = null;
        HttpPost request = null;

        try{
            String url = baseUrl + "submitOutcome.php";
            request = new HttpPost(url);
//            request.setURI(new URI(url));
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("challenge_id", challenge_id));
            nameValuePairs.add(new BasicNameValuePair("challenger_score", challengerScore));
            nameValuePairs.add(new BasicNameValuePair("challengee_score", challengeeScore));
            request.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            result = doPostRequest(request);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Deprecated
    public static boolean postMatchResults(String challenge_id, String challengee, String challenger,
                                           String challengeePassword, String challengeeScore,
                                           String challengerScore, String trashTalk) {
        boolean success = false;
        if (doLogin(challengee, challengeePassword)) {

            success = true;
        }

        return success;
    }
}
