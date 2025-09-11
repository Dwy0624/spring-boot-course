package top.dwy.controller;


import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.dwy.service.OssService;

/**
 * @author dwy
 */
@RestController
@RequestMapping("/oss")
public class OssController {
    @Resource
    private OssService ossService;
    @RequestMapping("upload")
    public String upload(MultipartFile  file)
    {
        return ossService.upload(file);
    }
}
