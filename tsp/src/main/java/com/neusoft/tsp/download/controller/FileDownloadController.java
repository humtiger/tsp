package com.neusoft.tsp.download.controller;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.neusoft.tsp.common.util.Constants;
import com.neusoft.tsp.common.util.DownLoadFileUtil;

@Controller
public class FileDownloadController {

    /**
     * 文件路径
     */
    @Value("${file.download.path}")
    private String FILE_PATH;

    /**
     * 文件名
     */
    @Value("${file.download.name}")
    private String FILE_NAME;

    /**
     * 是否开放下载
     */
    @Value("${download.accessable}")
    private String ACCESSABLE;

    /**
     * 是否开放下载
     */
    @Value("${upload.check.code}")
    private String CHECK_CODE;

    /**
     * 根目錄,下載頁
     * 
     * @return
     */
    @RequestMapping(value = "/download")
    public String hello(Map<String, Object> model) {
        model.put("flag", Boolean.parseBoolean(ACCESSABLE));
        return "/download/index";
    }

    /**
     * 执行下载动作
     * 
     * @param id
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/download/download")
    public String download(Long[] id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 判斷是否開放下載
        if (Boolean.parseBoolean(ACCESSABLE)) {
            // 获取文件位置
            String file = FILE_PATH + File.separator + FILE_NAME;
            // 下载
            DownLoadFileUtil.download(request, response, file, Constants.CONTENT_TYPE, FILE_NAME);
            return "download/success";
        } else {
            return "download/wait";
        }
    }

    /**
     * 上传文件
     * 
     * @param code
     * @param file
     * @return
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody
    String handleFileUpload(@RequestParam("code") String code, @RequestParam("file") MultipartFile file) {
        if (!CHECK_CODE.equals(code)) {
            // 验证码有误,可能是非法上传
            return "请确认验证码无误再上传</br><a href=\"javascript:history.go(-1);\">后退</a>";
        }
        if (!file.isEmpty()) {
            String name = file.getOriginalFilename();
            String[] fullName = name.split("\\.");
            String extName = fullName[fullName.length - 1];
            if (!extName.equalsIgnoreCase("zip")) {
                // 上传文件不是zip
                return "请将所有文件打成一个zip压缩包再上传</br><a href=\"javascript:history.go(-1);\">后退</a>";
            }
            try {
                file.transferTo(new File(FILE_PATH + File.separator + FILE_NAME));
                return "You successfully uploaded " + FILE_PATH + File.separator + FILE_NAME;
            } catch (Exception e) {
                return "You failed to upload " + FILE_NAME + " => <br/>" + e.getMessage();
            }
        } else {
            return "You failed to upload " + FILE_NAME + " because the file was empty.";
        }
    }

    @RequestMapping("/uploadpage")
    public String uploadpage() {
        return "download/upload";
    }
}
