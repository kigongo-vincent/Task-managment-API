package com.tasks.tasks.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import com.tasks.tasks.config.appProps;
import java.io.File;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@CrossOrigin
@RestController
@RequestMapping("")
public class HomeController {

    final appProps props;

    public HomeController(appProps props){
        this.props = props;
    }

    @Value("${app.name}")
    String appName;
   
    @GetMapping("")
    public String home() {
        return appName;
    }
    
    @GetMapping("/props")
    public appProps getProps() {
        return props;
    }

    @PostMapping(value = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public void uploads(@RequestParam MultipartFile file) {
        
        String fileName = file.getOriginalFilename();

        try {
           file.transferTo(new File(System.getProperty("user.dir") + "/public/uploads/" + fileName )); 
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.toString());
        }
    }
    
    
    
    
}
