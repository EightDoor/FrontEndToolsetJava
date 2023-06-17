package com.start6.personalkit.utils;

import com.start6.personalkit.config.PersonalkitConfig;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Component
public class ImageUtil {
    private final PersonalkitConfig personalkitConfig;

    public ImageUtil(PersonalkitConfig personalkitConfig) {
        this.personalkitConfig = personalkitConfig;
    }

    /**
     * 压缩文件
     *
     * @param multipartFile
     * @param outputQuality
     * @return
     */
    public String compress(MultipartFile multipartFile, float outputQuality) throws IOException {
        String filename = multipartFile.getOriginalFilename();
        InputStream inputStream = multipartFile.getInputStream();
        File file1 = new File(personalkitConfig.getCompressPath(), filename);
        String resultUrl = "/api/imageCompression/compressImg/" + filename;
        Thumbnails.of(inputStream)
                .scale(1f)  // 图片大小（长宽）压缩比例 从0-1，1表示原图
                .outputQuality(outputQuality) // 图片质量压缩比例 从0-1，越接近1质量越好
                .toFile(file1);
        return resultUrl;
    }
}