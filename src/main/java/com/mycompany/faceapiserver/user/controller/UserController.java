package com.mycompany.faceapiserver.user.controller;

import com.mycompany.faceapiserver.Response;

import com.mycompany.faceapiserver.user.model.User;
import com.mycompany.faceapiserver.user.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    private final String UPLOAD_FOLDER = "d:/baidu/images/";


    //文件上传
    //https://www.cnblogs.com/ityouknow/p/8298344.html

    @RequestMapping(value="/user", method= RequestMethod.POST)
    public Response registerUser(User user, @RequestParam("photo") MultipartFile photo) {

        int ret = 1;
        System.out.println("userid = " + user.getUserID());

        System.out.println("filename = " + photo.getName());

        File photoDir = new File(UPLOAD_FOLDER);
        if (!photoDir.exists()) {
            photoDir.mkdir();
        }
        try {
            Path path = Paths.get(UPLOAD_FOLDER + user.getUserID() + ".jpg");

            Files.write(path, photo.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            ret = userService.insertUser(user);
        } catch(DataAccessException exp) {
            ret = 0;
            System.out.println(exp);
        }
        System.out.println("create user return ret is " + ret);



        Response response = new Response();
        response.setRetCode(ret);
        if (ret == 1) {
            response.setRetMsg("success");
        } else {
            response.setRetMsg("error");
        }

        return response;

    }




    @RequestMapping(value="/user/isUserExist", method=RequestMethod.GET)
    public Response isUserExist(String userid_phone_email){



        Response response = new Response();
        return response;
    }


    @RequestMapping(value="/user/login", method=RequestMethod.POST)
    public Response login(String userid_phone_email, String password) {

        Response response = new Response();

        User user = userService.login(userid_phone_email, password);
        if (user == null) {
            response.setRetCode(0);
            response.setRetMsg("error");
        } else {
            response.setRetCode(1);
            response.setRetMsg("success");
        }

        return response;
    }



    @RequestMapping(value="/user", method=RequestMethod.GET)
    public List<User> getUsers(){
        List<User> users = userService.selectAll();


        return users;

    }

    @RequestMapping(value="/user", params={"updateTime"}, method=RequestMethod.GET)
    public List<User> getUpdatedUsers(String updateTime){
        System.out.println("updateTime ==================================== " + updateTime);

        List<User> users = null;
        if (updateTime == "") {
            users = userService.getAllUsers();
        }            else {
            users = userService.getUpdatedUsers(updateTime);
        }


        //  List<Group> groups = groupService.selectAll();
        return users;
    }
}
