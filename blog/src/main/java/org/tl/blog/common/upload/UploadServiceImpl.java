package org.tl.blog.common.upload;

import org.springframework.stereotype.Service;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UploadServiceImpl implements UploadService {

    /**
     * content 文件url处理 非本服务的 统统下载
     * @param content
     * @return
     */
    @Override
    public String handlerOuterImage(String content) {
        String regex;
        List<String> list = new ArrayList<String>();
        regex = "src=\"(.*?)\"";
        Pattern pa = Pattern.compile(regex, Pattern.DOTALL);
        Matcher ma = pa.matcher(content);
        while (ma.find()) {
            list.add(ma.group());
        }
       /* list.stream().forEach(string -> {
            //System.out.println(string);
        });*/
        List<String> urls = new ArrayList<String>();
        List<ImageUpload> images = new ArrayList<ImageUpload>();
        urls = list.stream().map(string -> {
            ImageUpload imageUpload = new ImageUpload();
            imageUpload.setOriginUrl(string.substring(5, string.length() - 1));
            images.add(imageUpload);
            return string.substring(5, string.length() - 1);
        }).collect(Collectors.toList());

        //--------------------------- 下载图片+替换url ---------------------------------
        for (ImageUpload image : images) {
            downloadPicture(image, "/data/post_image/");
            content = content.replace(image.getOriginUrl(), "http://img.terrylam.cn" + image.getFileName());
        }
        //--------------------------- 替换url ---------------------------------
        return content;
    }

    /**
     * 下载图片
     * @param imageUpload
     * @param filePath 路径
     */
    private void downloadPicture(ImageUpload imageUpload, String filePath) {
        URL url = null;
        int imageNumber = 0;

        try {
            url = new URL(imageUpload.getOriginUrl());
            DataInputStream dataInputStream = new DataInputStream(url.openStream());

            String imageName = filePath + getImageName();
            imageUpload.setFileName(imageName);
            FileOutputStream fileOutputStream = new FileOutputStream(new File(imageName));
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length;

            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            // byte[] context=output.toByteArray();
            fileOutputStream.write(output.toByteArray());
            dataInputStream.close();
            fileOutputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private synchronized String getImageName() {
        long l = System.currentTimeMillis();
        return String.valueOf(l);
    }
}
