package com.start6.personalkit.controller;

import com.start6.personalkit.config.PersonalkitConfig;
import com.start6.personalkit.utils.ImageUtil;
import com.start6.personalkit.utils.Result;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

@RestController
@RequestMapping("/imageCompression")
public class ImageCompressionController {
    private final ImageUtil imageUtil;
    private final PersonalkitConfig personalkitConfig;

    public ImageCompressionController(ImageUtil imageUtil, PersonalkitConfig personalkitConfig) {
        this.imageUtil = imageUtil;
        this.personalkitConfig = personalkitConfig;
    }

    @GetMapping(value = "/compressImg/{name}")
    public void getCompressImg(HttpServletResponse response, @PathVariable String name) throws IOException {
        // 获取文件地址
        FileInputStream inputStream = new FileInputStream(new File(personalkitConfig.getCompressPath() + "/" + name));
        response.setContentType("application/force-download");
        response.addHeader("Content-disposition", "attachment;fileName=" + name);
        OutputStream out = response.getOutputStream();
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(bytes)) != -1) {
            out.write(bytes, 0, len);
        }
        out.close();
    }

    @PostMapping
    public Result<List<Map<String, Object>>> getImageCompression(@RequestPart("file") MultipartFile[] multipartFiles, float compressSchedule) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        Arrays.stream(multipartFiles).forEach(file -> {
            try {
                String url = imageUtil.compress(file, compressSchedule);
                HashMap<String, Object> map = new HashMap<>();
                map.put("title", file.getOriginalFilename());
                map.put("url", url);
                list.add(map);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return Result.success(list);
    }
}