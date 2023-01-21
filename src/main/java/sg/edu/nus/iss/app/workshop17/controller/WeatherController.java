package sg.edu.nus.iss.app.workshop17.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.iss.app.workshop17.model.Weather;
import sg.edu.nus.iss.app.workshop17.service.WeatherService;

@Controller
@RequestMapping(path="/weather")
public class WeatherController {
    
    // Auto wire the weather service
    @Autowired
    private WeatherService weatherSvc;

    // Map get request to this method
    @GetMapping
    public String getWeather (@RequestParam(required=true) String city,
        @RequestParam(required=false) String units,
        Model model) throws IOException {
        System.out.println("units > " + units);
        // If units not set, set to metric
        if (units == null)
            units = "metric";
        // Get the weather data
        Optional<Weather> w= weatherSvc.getWeather(city, units);
        // Add the weather data as an attribute to the model
        model.addAttribute("weather", w.get());
        // Return the view name
        return "weather";
    }
}
