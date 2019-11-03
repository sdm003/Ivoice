package ivoice.helpers;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

//Класс-помощник для отправок разного рода Http запросов.

public class HttpHelper {

    public HttpHeaders headers;
    private RestTemplate rest;

    public HttpHelper() {
        this.headers = new HttpHeaders();
        this.rest = this.restTemplate();
    }

    public RestTemplate restTemplate() {
        return new RestTemplate(this.clientHttpRequestFactory());
    }

    private ClientHttpRequestFactory clientHttpRequestFactory() {

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(600000);
        factory.setConnectTimeout(60000);

        return factory;
    }

    public String sendRequest(String query, HttpMethod type, String params, MultiValueMap<String, String> headers) {

        if (headers != null) {
            this.headers.addAll(headers);
        }

        HttpEntity<String> requestEntity = new HttpEntity(params, this.headers);
        ResponseEntity<String> responseEntity = this.rest.exchange(query, type, requestEntity, String.class, new Object[0]);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return (String)responseEntity.getBody();
        } else {
            return "";
        }
    }
}


