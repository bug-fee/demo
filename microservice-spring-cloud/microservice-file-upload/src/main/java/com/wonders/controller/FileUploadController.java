package com.wonders.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class FileUploadController {

    /**
     * 文件上传
     *
     * @param file 待上传的文件
     * @return 服务器上的绝对路径
     * @author Wang si rui
     * @version 1.0
     * @lastAuthor 最后修改人
     * @lastDate最后时间
     * @Rewrite record 修改记录：
     * 1、 <br>
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody
    String handleFileUpload(@RequestParam(value = "file", required = true) MultipartFile file) throws IOException {
        //该示例比较简单，没有做IO异常、文件大小、文件非空等处理
        byte[] bytes = file.getBytes();
        //获取文件名称以文件名称作为保存路径，默认会保存到当前工作空间
        File fileTosave = new File(file.getOriginalFilename());
        //直接将字节保存到指定路径生成文件(默认将保存到当前工作空间)
        FileCopyUtils.copy(bytes, fileTosave);
        //获取该文件在磁盘上的绝对路径
        return fileTosave.getAbsolutePath();
    }

}
