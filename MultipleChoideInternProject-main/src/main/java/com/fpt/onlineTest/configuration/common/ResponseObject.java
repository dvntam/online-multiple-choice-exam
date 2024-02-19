package com.fpt.onlineTest.configuration.common;

import com.fpt.onlineTest.model.Role;
import com.fpt.onlineTest.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseObject {
    private String status;
    private String message;
    private Object data;


}
