package com.example.file.Controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("file")
@Slf4j
public class FileController {
    @Value("${file.upload.url}")
    private String uploadFilePath;
    @Value("${file.download.url}")
    private String downloadFilePath;

    @RequestMapping("/upload")
    public String httpUpload(@RequestParam("files") MultipartFile files[]){
        JSONObject object=new JSONObject();
        for(int i=0;i<files.length;i++){
            String fileName = files[i].getOriginalFilename();  // 文件名
            File dest = new File(uploadFilePath +'/'+ fileName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                files[i].transferTo(dest);
            } catch (Exception e) {
                log.error("{}",e);
                object.put("success",2);
                object.put("result","程序错误，请重新上传");
                return object.toString();
            }
        }
        object.put("success",1);
        object.put("result","文件上传成功");
        return object.toString();
    }

    @RequestMapping("/upload2")
    public String httpUpload(@RequestParam("file") MultipartFile file){
        JSONObject object=new JSONObject();
        String fileName = file.getOriginalFilename();  // 文件名
        File dest = new File(uploadFilePath +'/'+ fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (Exception e) {
            log.error("{}",e);
            object.put("success",2);
            object.put("result","程序错误，请重新上传");
            return object.toString();
        }

        object.put("success",1);
        object.put("result","文件上传成功");
        return object.toString();
    }

    @RequestMapping("/download")
    public String fileDownLoad(HttpServletResponse response, @RequestParam("fileName") String fileName) throws Exception {
        File file = new File(downloadFilePath +'/'+ fileName);
        if(!file.exists()){
            return "下载文件不存在";
        }
        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") );

        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));) {
            byte[] buff = new byte[1024];
            OutputStream os  = response.getOutputStream();
            int i = 0;
            while ((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);
                os.flush();
            }
            bis.close();
            os.close();
        } catch (IOException e) {
            log.error("{}",e);
            return "下载失败";
        }
        return "下载成功";
    }

    @RequestMapping("/getFileList")
    public List<String> getFileList() {
        List<String> fileList = new ArrayList<>();
        File file = new File(downloadFilePath + '/');
        //用数组把文件夹下的文件存起来
        File[] files = file.listFiles();
        for (File file1 : files) {
            fileList.add(file1.getName());
        }
        return fileList;
    }

}