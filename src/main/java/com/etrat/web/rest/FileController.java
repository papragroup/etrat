package com.etrat.web.rest;

import java.io.FileInputStream;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/asset")
public class FileController {
    @Autowired
    ResourceLoader resourceLoader;

    @RequestMapping(path = "/download/{name}", method = RequestMethod.GET)
    public ResponseEntity<Resource> download(@PathVariable String name) throws IOException {
        // ...

        //        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        Resource resource1 = resourceLoader.getResource("classpath:" + name);
        return ResponseEntity.ok().contentLength(resource1.getFile().length()).contentType(MediaType.IMAGE_PNG).body(resource1);
    }
}
