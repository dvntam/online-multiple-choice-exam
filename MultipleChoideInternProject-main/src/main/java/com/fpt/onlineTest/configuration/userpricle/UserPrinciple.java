//package com.fpt.onlineTest.configuration.userpricle;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fpt.onlineTest.model.Admin;
//import com.fpt.onlineTest.model.Teacher;
//import com.fpt.onlineTest.model.User;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.Set;
//
//public class UserPrinciple implements UserDetails {
//
//
//
//    public Collection<? extends GrantedAuthority> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(Collection<? extends GrantedAuthority> roles) {
//        this.roles = roles;
//    }
//
//    public UserPrinciple(Integer id, String username, String password, Collection<? extends GrantedAuthority> roles) {
//        this.id = id;
//        this.username = username;
//        this.password = password;
//        this.roles = roles;
//    }
//
//    public UserPrinciple() {
//    }
//
//    private String viewName;
//    private Integer id;
//    private String username;
//    @JsonIgnore
//    private String password;
//    private Collection<? extends GrantedAuthority> roles;
//
//    private String[] roleArr;
//
//    public String getViewName() {
//        return this.viewName;
//    }
//
//    public static UserPrinciple build(User user, Admin admin, Teacher teacher, Set<GrantedAuthority> roles) {
//        Integer id = 1;
//        String name;
//        if (user != null) {
//            id = user.getUserId();
//            name = user.getUsername();
//        } else if (admin != null){
//            id = admin.getAdminId();
//            name = admin.getUsername();
//        }else {
//            id = teacher.getId();
//            name = "ahdsfkdsa";
//        }
//
//        UserPrinciple u = new UserPrinciple(Math.toIntExact(id), name,
//                "Hide", roles);
//        u.setRoles(roles);
//        return u;
//    }
//
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return roles;
//    }
//
//    @Override
//    public String getPassword() {
//        return this.password;
//    }
//
//    @Override
//    public String getUsername() {
//        return this.id + "";
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//}
