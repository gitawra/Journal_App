package tawra.technologies.Journals.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tawra.technologies.Journals.ApiResponce.WeatherResponse;
import tawra.technologies.Journals.Cache.AppCache;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String api_key;
//    private static final String API = "http://api.weatherapi.com/v1/current.json?key=API_KEY&q=INDIA&aqi=no";
    @Autowired
    private AppCache appCache;
    @Autowired
    private RestTemplate restTemplate;
    public WeatherResponse getWeather(String city){
        String finalAPI = appCache.APP_CACHE.get("weather_api").replace("<api_key>", api_key);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
        return response.getBody();
    }
}
