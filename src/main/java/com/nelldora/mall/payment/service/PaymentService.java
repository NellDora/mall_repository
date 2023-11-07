package com.nelldora.mall.payment.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.nelldora.mall.order.domain.Order;
import com.nelldora.mall.payment.domain.PaymentDTO;
import com.nelldora.mall.payment.exception.PayFailedValidationException;
import com.nelldora.mall.payment.exception.PriceValidationException;
import com.nelldora.mall.payment.vo.StoreInfo;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PaymentService {

    public static void validationCheck(PaymentDTO payment, Order order) throws PayFailedValidationException, PriceValidationException {

        if(payment.getMerchantUid()!=order.getId()) {
            System.out.println("말도안되는 오류");
        }else if(payment.isSuccess()==false){
            throw new PayFailedValidationException("결제가 정상적으로 처리되지 않았습니다.");

        }else if(payment.getStatus().equals("paid") &&payment.getPaidAmount()!= order.getTotalPrice()) {
            throw new PriceValidationException("결제된 금액에 문제가 발생하였습니다");

        }else if(payment.getStatus().equals("paid")&& order.getTotalPrice() == payment.getPaidAmount()) {
            System.out.println("결제가 정상적으로 처리되었습니다.");
        }
    }

    //환불 및 결제 정보 조회를 위한 토큰 발행코드
    public String getToken(StoreInfo storeInfo) {

        final String tokenURL = "https://api.iamport.kr/users/getToken";
        String token = "";

        //Map<String , String> impInfo = new HashMap<>();
        //impInfo.put("imp_key", storeInfo.getImpkey());
        //impInfo.put("imp_secret", storeInfo.getSecretkey());

        try {
            HttpClient client = HttpClientBuilder.create().build();
            JsonObject myInfoJson = new JsonObject();
            //myInfoJson.addProperty("imp_key", storeInfo.getImpkey());
            //myInfoJson.addProperty("imp_secret", storeInfo.getSecretkey());

            List<BasicNameValuePair> myinfo = new ArrayList<>();
            myinfo.add(new BasicNameValuePair("imp_key", storeInfo.getImpkey()));
            myinfo.add(new BasicNameValuePair("imp_secret", storeInfo.getImpkey()));
            UrlEncodedFormEntity infoEncoding;
            infoEncoding = new UrlEncodedFormEntity(myinfo);

            //확인용 추가
            System.out.println(infoEncoding);

            HttpPost post = new HttpPost(tokenURL);
            post.setEntity(infoEncoding);

            HttpResponse httpRes = client.execute(post);

            // HttpEntity에서 JSON 문자열 추출
            HttpEntity entity = httpRes.getEntity();
            String jsonResponse = EntityUtils.toString(entity);

            System.out.println("entity의 값 : "+ jsonResponse);

            Gson gson = new Gson();
            String response = gson.fromJson(jsonResponse, Map.class).get("response").toString();
            token = gson.fromJson(response, Map.class).get("access_token").toString();


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }

        System.out.println("발급받은 토큰의 값은 : " +token);



        return token;
    }




    //환불 및 결제 정보 조회를 위한 토큰 발행코드
    public String getTokenV2(StoreInfo storeInfo) {

        final String tokenURL = "https://api.iamport.kr/users/getToken";
        String token = "";


        try {
            HttpClient client = HttpClientBuilder.create().build();
            JsonObject myInfoJson = new JsonObject();
            myInfoJson.addProperty("imp_key", storeInfo.getImpkey());
            myInfoJson.addProperty("imp_secret", storeInfo.getSecretkey());


            StringEntity entity = new StringEntity(myInfoJson.toString(), ContentType.APPLICATION_JSON);


            HttpPost post = new HttpPost(tokenURL);
            post.setEntity(entity);

            // HTTP 요청 보내기
            HttpResponse httpRes = client.execute(post);
            HttpEntity httpEntity = httpRes.getEntity();

            String tokenJson = EntityUtils.toString(httpEntity);
            System.out.println(tokenJson.toString());

            Gson gson = new Gson();
            String response = gson.fromJson(tokenJson,Map.class).get("response").toString();
            token = gson.fromJson(response, Map.class).get("access_token").toString();


        } catch (IOException e) {

            e.printStackTrace();
        }


        System.out.println("발급받은 토큰의 값은 : " +token);



        return token;

    }

}
