package com.uet.http_client;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
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
import java.util.Arrays;
import java.util.Scanner;

public class OTHttpClient {
    private static final CloseableHttpClient httpClient = HttpClients.createDefault();

    public static void getAllFiles() throws IOException {
        HttpGet httpGet = new HttpGet("http://localhost:8080/api/files");
        HttpResponse httpResponse = httpClient.execute(httpGet);
        Scanner sc = new Scanner(httpResponse.getEntity().getContent());
        System.out.println(httpResponse.getStatusLine());
        while (sc.hasNext()) {
            System.out.println(sc.nextLine());
        }
    }

    public static void uploadFile(File file) throws IOException {
        HttpEntity data = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                .addBinaryBody("file", file, ContentType.DEFAULT_BINARY, file.getName()).build();
        HttpUriRequest request = RequestBuilder.post("http://localhost:8080/api/files").setEntity(data).build();

        System.out.println("Executing request " + request.getRequestLine());
        ResponseHandler<String> responseHandler = response -> {
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        };
        String responseBody = httpClient.execute(request, responseHandler);
        System.out.println("----------------------------------------");
        System.out.println(responseBody);
    }

    public static void downloadFile(String id) throws IOException {
        HttpGet httpGet = new HttpGet("http://localhost:8080/api/files/" + id);
        HttpResponse httpResponse = httpClient.execute(httpGet);
        HttpEntity entity = httpResponse.getEntity();
        if (entity != null) {
            try (InputStream inputStream = entity.getContent()) {
                Files.copy(inputStream, Paths.get(httpResponse.getFirstHeader("fileName").getValue()), StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        getAllFiles();
//        File file = new File("question08.txt");
//        uploadFile(file);
        downloadFile("5ee0f1443f41674792588ad4");
    }
}
