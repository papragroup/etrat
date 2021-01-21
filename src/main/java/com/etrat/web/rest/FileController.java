package com.etrat.web.rest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/asset")
public class FileController {
    @Autowired
    ResourceLoader resourceLoader;

    @RequestMapping(path = "/{name}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> download(@PathVariable String name) throws IOException {
        // ...

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("image/" + name);

        //        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(StreamUtils.copyToByteArray(inputStream));
    }
}
