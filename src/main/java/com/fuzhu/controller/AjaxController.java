package com.fuzhu.controller;

import com.alibaba.fastjson.JSON;
import com.fuzhu.entity.GoodDetails;
import com.fuzhu.entity.User;
import com.fuzhu.service.GoodService;
import com.fuzhu.service.ParseExcel;
import com.fuzhu.service.UserService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by asus on 2017/5/25.
 */

@Controller
@RequestMapping("/ajax")
public class AjaxController {
    @Autowired
    private GoodService goodService;
    @Autowired
    private UserService userService;
    @Autowired
    private ParseExcel parseExcel;

    // 上传文件存储目录
    private static final String UPLOAD_DIRECTORY = "upload";

    // 上传配置
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB


    @RequestMapping(value = "/findGoods",produces="text/html;charset=UTF-8", method = {RequestMethod.GET,RequestMethod.POST})
    public String findGoodInfo(HttpServletRequest request,String location){
        List<GoodDetails>  goodlist =  goodService.getGoodList(location);
        request.setAttribute("goodslist",goodlist);
        return "testajax";
    }

    @ResponseBody
    @RequestMapping(value = "/findUserByProvince",produces="text/html;charset=UTF-8", method = {RequestMethod.GET,RequestMethod.POST})
    public String findUserByProvince(String location){
        List<User> userList = userService.findUserByProvince(location);
        System.out.println(JSON.toJSONString(userList));
        return JSON.toJSONString(userList);
    }

    @RequestMapping(value = "/getExcel",produces="text/html;charset=UTF-8", method = {RequestMethod.GET,RequestMethod.POST})
    public String getExcel(HttpServletRequest request){
        String location ="";
        List<GoodDetails>  goodlist =  goodService.getGoodList(location);
        request.setAttribute("resultList",goodlist);
        List<String> headList = new ArrayList<>();
        headList.add("商品名");
        headList.add("商品类型");
        headList.add("商品地址");
        headList.add("商品星级");
        headList.add("商品价格");
        request.setAttribute("headList",headList);
        return "doExcel";
    }

    @RequestMapping(value = "/uploadExcel",produces="text/html;charset=UTF-8", method = {RequestMethod.GET,RequestMethod.POST})
    public String uploadExcel(HttpServletRequest request ,@RequestParam(value = "filename", required = false) MultipartFile file){
        String path = request.getSession().getServletContext().getRealPath("uploadExcel");
        String fileName = file.getOriginalFilename();
        System.out.println(path);
        File targetFile = new File(path, fileName);
        System.out.println(targetFile);
        System.out.println(targetFile.toString());
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        //保存
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<GoodDetails> list = null;//拿到我们导入的list后就是往数据库批量插入了，这个就太简单了，我就不写了。
        if(fileName.endsWith(".xls")||fileName.endsWith(".xlsx")) {
            list = parseExcel.parseExcel((File) targetFile, targetFile.toString());
        }
        return "success";
    }

    //不暴露HttpServletResponse这样的j2ee接口
    @RequestMapping(value = "/downloadPoiExecl",produces="text/html;charset=UTF-8", method = {RequestMethod.GET,RequestMethod.POST})
    public ResponseEntity<byte[]> download() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "common.xls");
        //我们需要拿到准确的文件路径，一般是罗列给前端进行选择，选择好后就去文件服务器拿嘛
        File file = new File("E://工单信息表.xls");
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);
    }
}
