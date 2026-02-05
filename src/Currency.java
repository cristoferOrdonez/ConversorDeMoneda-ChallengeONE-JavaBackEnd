import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Currency {

    private String code, name;
    private static final String EXCHANGE_RATE_API_URL = "v6.exchangerate-api.com";
    private static final String EXCHANGE_RATE_API_VERSION = "v6";
    private static final String API_KEY = "21f4c2fcf4a2fb2488573e69";

    public Currency(String code, String name){
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double covertAmount(Currency target, double amount){

        String url = "https://" +
                EXCHANGE_RATE_API_URL + "/" +
                EXCHANGE_RATE_API_VERSION + "/" +
                API_KEY +
                "/pair/" +
                this.code + "/" + target.code + "/" + amount;

        HttpClient client;

        try {
             client = HttpClient.newHttpClient();
        } catch (UncheckedIOException e){
            System.out.println("Error at client creation: " + e.getMessage());
            return 0;
        }

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();
            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create();

            ExchangeRateAPIResponse JSONresponse = gson.fromJson(json, ExchangeRateAPIResponse.class);

            return JSONresponse.conversionResult();

        } catch (IOException | InterruptedException e) {

            System.out.println("Error at conversion: " + e.getMessage());
            return 0;

        }

    }

}
