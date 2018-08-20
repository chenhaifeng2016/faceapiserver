package com.mycompany.faceapiserver.group.controller;

import com.mycompany.faceapiserver.Response;
import com.mycompany.faceapiserver.group.dao.GroupDao;
import com.mycompany.faceapiserver.group.model.Group;
import com.mycompany.faceapiserver.group.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class GroupController {

    @Autowired
    GroupService groupService;


    @RequestMapping(value="/group", method=RequestMethod.POST)
    public Response createGroup(Group group){

        int ret = 1;
        try {
            ret = groupService.createGroup(group);

        } catch(DataAccessException exp) {
            ret = 0;
        }
        System.out.println("createGroup return ret is " + ret);


        Response response = new Response();
        response.setRetCode(ret);
        if (ret == 1) {
            response.setRetMsg("success");
        } else {
            response.setRetMsg("error");
        }

        return response;
    }


    @RequestMapping(value="/group", params={"updateTime"}, method=RequestMethod.GET)
    public List<Group> getUpdatedGroups(String updateTime){
        System.out.println("updateTime ==================================== " + updateTime);

        List<Group> groups = null;
        if (updateTime == "") {
            groups = groupService.selectAll();
        }            else {
            groups = groupService.getUpdatedGroups(updateTime);
        }


      //  List<Group> groups = groupService.selectAll();
        return groups;
    }

    @RequestMapping(value="/group", method=RequestMethod.GET)
    public List<Group> getGroups(){
        List<Group> groups = groupService.selectAll();

        for (Group group : groups) {
            System.out.println(group.getCreateTime());
        }

        return groups;

    }



    @RequestMapping(value="/group", method=RequestMethod.PUT)
    public Response updateGroup(Group group){
        Response response = new Response();
        return response;
    }


    @RequestMapping(value="/group/{groupId}", method=RequestMethod.DELETE)
    public Response delGroup(@PathVariable("groupId") Integer groupID){
        Response response = new Response();


        groupService.delGroup(String.valueOf(groupID));
        return response;
    }



}
