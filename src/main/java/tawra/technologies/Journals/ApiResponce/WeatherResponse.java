package tawra.technologies.Journals.ApiResponce;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class WeatherResponse {
    public Current current;

    @Getter
    @Setter
    public class Current{
        @JsonProperty("temp_c")
        public double tempC;
        public int humidity;
        public int cloud;
        @JsonProperty("feelslike_c")
        public int feelslike;

    }
}
