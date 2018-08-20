package com.mycompany.faceapiserver.group.service;

import com.mycompany.faceapiserver.group.dao.GroupDao;
import com.mycompany.faceapiserver.user.dao.UserDao;
import com.mycompany.faceapiserver.group.model.Group;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

//事务
@Service
public class GroupService {

    @Resource
    GroupDao groupDao;

    @Resource
    UserDao userDao;

    public int createGroup(Group group) throws DataAccessException {
        return groupDao.createGroup(group);
    }

    public List<Group> selectAll() {
        return groupDao.selectAll();
    }

    public List<Group> getUpdatedGroups(String updateTime) {
        return groupDao.getUpdatedGroups(updateTime);
    }

    public int delGroup(String groupID) {

        int ret = 0;

        ret = groupDao.delGroup(groupID);

        ret = userDao.delUserByGroupID(groupID);

        return ret;
    }

}
