package com.uet.http_client;

import com.google.gson.Gson;
import com.uet.config.UserConfig;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class OTHttpClient {
    private static final CloseableHttpClient httpClient = HttpClients.createDefault();

    public static List<OTFile> getAllFiles() throws IOException {
        HttpGet httpGet = new HttpGet("http://" + UserConfig.getInstance().getHost() + ":8080/files");
        HttpResponse httpResponse = httpClient.execute(httpGet);
        Scanner sc = new Scanner(httpResponse.getEntity().getContent());
        String content = sc.nextLine();
        String[] splits = content.split("//");
        Gson gson = new Gson();
        ArrayList<OTFile> files = new ArrayList<>();
        for (String s : splits) {
            OTFile otFile = gson.fromJson(s, OTFile.class);
            files.add(otFile);
        }
        return files;
    }

    public static OTFile createNewFile(String name) throws IOException {
        HttpPost httpPost = new HttpPost("http://" + UserConfig.getInstance().getHost() + ":8080/files?diagramName=" + name);
        HttpResponse httpResponse = httpClient.execute(httpPost);
        Scanner sc = new Scanner(httpResponse.getEntity().getContent());
        String
    }

    public static void main(String[] args) throws IOException {
        getAllFiles();
    }
}
