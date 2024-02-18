package com.example.foodplanner.Models;

import com.example.foodplanner.SQLlite.PreferenceManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserDTO {
    private String id, name, email, password, lastSaturday;

    private static UserDTO user = null;

    private UserDTO() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = auth.getCurrentUser();
        if(firebaseUser != null) {
            this.id = firebaseUser.getUid();
            this.name = firebaseUser.getDisplayName();
            this.email = firebaseUser.getEmail();
            this.password = null;
        }
    }

    public static void setUserFromPreference(PreferenceManager preferenceManager){
        user = new UserDTO();
        user.id = preferenceManager.getUserId();
        user.name = preferenceManager.getUserName();
        user.email = preferenceManager.getUserEmail();
        user.lastSaturday = preferenceManager.getSaturday();
    }

    public static UserDTO getUser(){
        if(user == null)
            user = new UserDTO();
        return user;
    }

    public String getLastSaturday() {
        return lastSaturday;
    }

    public static void removeUser(){
        user = null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void updatePassword(String password) {
        this.password = password;
    }
}
