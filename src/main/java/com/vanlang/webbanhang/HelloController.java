package com.vanlang.webbanhang;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping ("/")
    public String hello(){
        return "Chúc mừng bạn đã đăng nhập thành công, vui lòng quay về trang chủ";
    }
}
