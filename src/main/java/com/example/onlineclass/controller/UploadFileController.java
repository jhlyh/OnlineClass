package com.example.onlineclass.controller;

import com.example.onlineclass.props.CommonProps;
import com.example.onlineclass.service.CommonDetailImp;
import com.example.onlineclass.util.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author jhlyh
 */
@RestController
@RequestMapping("/common")
public class UploadFileController {

    private final CommonDetailImp commonDetailImp;
    private final CommonProps commonProps;

    public UploadFileController(CommonDetailImp commonDetailImp, CommonProps commonProps) {
        this.commonDetailImp = commonDetailImp;
        this.commonProps = commonProps;
    }

    @PostMapping("/uploadImage")
    public Result<?> uploadImage(@RequestBody MultipartFile file) {
        return commonDetailImp.uploadFile(file, commonProps.getFtpImagePathName());
    }

    @PostMapping("/uploadVideo")
    public Result<?> uploadVideo(@RequestBody MultipartFile file) {
        return commonDetailImp.uploadFile(file, commonProps.getFtpVideoPathName());
    }
}
