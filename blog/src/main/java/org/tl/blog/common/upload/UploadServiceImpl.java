package org.tl.blog.common.upload;

import org.springframework.stereotype.Service;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UploadServiceImpl implements UploadService {

    /**
     * content 文件url处理 非本服务的 统统下载
     *
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
            if (!ma.group().contains("http://img.terrylam.cn")) {
                list.add(ma.group());
            }
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
     *
     * @param imageUpload
     * @param filePath    路径
     */
    private void downloadPicture(ImageUpload imageUpload, String filePath) {
        URL url = null;
        try {
            url = new URL(imageUpload.getOriginUrl());
            DataInputStream dataInputStream = new DataInputStream(url.openStream());
            String typeHexStr = toHexString(readFileTenBytes(new DataInputStream(url.openStream())));
            String imageName = filePath + getImageName() + getRealType(typeHexStr);
            imageUpload.setFileName(imageName);
            FileOutputStream fileOutputStream = new FileOutputStream(new File(imageName));
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
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

    /**
     * 读取文件的前10个字节
     *
     * @return 前10个字节
     */
    private static byte[] readFileTenBytes(DataInputStream dataInputStream) {
        byte[] buffer = new byte[10];
        try {
            DataInputStream dataInputStreamTemp = new DataInputStream(dataInputStream);
            //noinspection ResultOfMethodCallIgnored
            dataInputStreamTemp.read(buffer);
            dataInputStreamTemp.close();
            dataInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return buffer;
    }

    /**
     * 字节数组转成16进制表示格式的字符串
     *
     * @param byteArray 需要转换的字节数组
     * @return 16进制表示格式的字符串
     **/
    private static String toHexString(byte[] byteArray) {
        StringBuilder hexString = new StringBuilder();
        for (byte aByteArray : byteArray) {
            if ((aByteArray & 0xff) < 0x10) {
                hexString.append("0");
            }
            hexString.append(Integer.toHexString(0xFF & aByteArray));
        }
        return hexString.toString().toLowerCase();
    }

    /**
     * 通过十六进制魔数获取真实类型
     *
     * @param hexStr
     * @return
     */
    private static String getRealType(String hexStr){
        Map<String,String> typeMap = new HashMap<>();
        typeMap.put("424d",".bpm");
        typeMap.put("47494638",".gif");
        typeMap.put("ffd8ffe0",".jpg");
        typeMap.put("89504e47",".png");
        for(String key : typeMap.keySet()){
            if(hexStr.startsWith(key)){
                return typeMap.get(key);
            }
        }
       return "";
    }


}
