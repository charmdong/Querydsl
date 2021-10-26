package study.querydsl.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import study.querydsl.dto.MultipartDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hell() {
        return "hello";
    }

    @PostMapping("/multipart")
    public ResponseEntity<Map<String, Boolean>> multipartTest(MultipartHttpServletRequest request) throws Exception {

        List<MultipartFile> files = request.getFiles("files");
        for (MultipartFile file : files) {
            System.out.println("file = " + file);
        }

        System.out.println();

        String data = request.getParameter("data");
        System.out.println("data = " + data);

        ObjectMapper mapper = new ObjectMapper();
        MultipartDto multipartDto = mapper.readValue(data, MultipartDto.class);
        System.out.println("multipartDto = " + multipartDto);

        System.out.println();

        Map<String, Boolean> result = new HashMap<>();
        result.put("isDup", false);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
