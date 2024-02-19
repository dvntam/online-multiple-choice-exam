//package com.fpt.onlineTest.configuration.userpricle;
//
//import com.fpt.onlineTest.model.Admin;
//import com.fpt.onlineTest.model.Teacher;
//import com.fpt.onlineTest.model.User;
//import com.fpt.onlineTest.service.AdminService;
//
//import com.fpt.onlineTest.service.TeacherService;
//import com.fpt.onlineTest.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Service
//public class UserDetailService implements UserDetailsService {
//
//    @Autowired
//    AdminService adminService;
//
//    @Autowired
//    UserService userService;
//
//    @Autowired
//    TeacherService teacherService;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//        if(username.contains("admin")){
//            Admin admin = adminService.findByUsername(username);
//            grantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));
//            UserPrinciple userPrinciple = UserPrinciple.build(null, admin,null, grantedAuthorities);
//            return userPrinciple;
//        }
//        else if (username.contains("user")){
//            User user = userService.findByUsername(username);
//            grantedAuthorities.add(new SimpleGrantedAuthority("USER"));
//            UserPrinciple userPrinciple = UserPrinciple.build(user,null,null, grantedAuthorities);
//            return userPrinciple;
//        }
//        else {
//            Teacher teacher = teacherService.findByUsername(username);
//            grantedAuthorities.add(new SimpleGrantedAuthority("TEACHER"));
//            UserPrinciple userPrinciple = UserPrinciple.build(null,null,teacher, grantedAuthorities);
//            return userPrinciple;
//        }
//
//    }
//}