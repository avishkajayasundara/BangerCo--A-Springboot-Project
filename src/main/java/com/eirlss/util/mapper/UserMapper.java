package com.eirlss.util.mapper;

import com.eirlss.dto.ModelUser;
import com.eirlss.model.User;

import java.util.HashMap;
import java.util.List;

public class UserMapper {
    public static HashMap<Long, ModelUser> userListToModelUserMap(List<User> users){
        HashMap<Long, ModelUser> map = new HashMap<>();
        for (User u : users){
            ModelUser modelUser = new ModelUser();
            modelUser.setUserid(u.getUserid());
            modelUser.setAddress(u.getAddress());
            modelUser.setFirstName(u.getFirstName());
            modelUser.setLastName(u.getLastName());
            modelUser.setEmail(u.getEmail());
            modelUser.setDateOfBirth(u.getDateOfBirth());
            modelUser.setState(u.getState());

            map.put(u.getUserid(), modelUser);
        }
        return map;
    }

    public static ModelUser userToModelUser(User user){
        ModelUser modelUser = new ModelUser();
        modelUser.setAddress(user.getAddress());
        modelUser.setDateOfBirth(user.getDateOfBirth());
        modelUser.setEmail(user.getEmail());
        modelUser.setFirstName(user.getEmail());
        modelUser.setLastName(user.getLastName());
        modelUser.setUserid(user.getUserid());
        modelUser.setDrivingLinence(user.getDrivingLinence());
        modelUser.setUtilityBill(user.getUtilityBill());

        return modelUser;
    }

}
