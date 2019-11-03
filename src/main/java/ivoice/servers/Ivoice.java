package ivoice.servers;
import ivoice.helpers.*;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.json.JSONArray;

public class Ivoice {

    private final static String serviceUrl = "https://odqa.demos.ivoice.online/model";
    private final static MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();

    //Главный метод для http запроса.
    public static String askQuestion(String question) {

            ReportHeaderBuild();
            String requestBody = ReportParamsBuild(question);

            String response = new HttpHelper().sendRequest(serviceUrl, HttpMethod.POST, requestBody, headers);

            return parseResponse(response);
    }

    //Установка HTTP headers
    private static MultiValueMap<String, String> ReportHeaderBuild(){

        headers.add("accept","application/json");
        headers.add("Content-Type","application/json");

        return headers;
    }

    // Парсинг ответа из JSONArray в String
    private static String parseResponse(String response){

        JSONArray jsonArray = new JSONArray(response);
        JSONArray jsonArray1 = jsonArray.getJSONArray(0);
        JSONArray jsonArray2 = jsonArray1.getJSONArray(0);

        response = jsonArray2.toString();
        response = response.replaceAll("\\[(.*)\\]", "$1").replace("\"", "");;

        return response;

    }

    //Формирование HTTP body
    private static String ReportParamsBuild(String question)  {

        String param = String.format( "{\"context\": [ \"%s\" ]}",question);

        return param;
    }

}
