package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin; // 跨域注解
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin // 允许跨域请求（前端和后端域名/端口不同时需要）
public class Controller {

    @PostMapping("/hello")
    public ResponseEntity<String> sayHello(@RequestBody User user) {
        // 修正：用对象调用方法（user.xxx()），不是类调用（User.xxx()）
        String name = user.getUsername();
        String pass = user.getPassword(); // 现在可以获取密码了

        // 这里可以添加密码验证逻辑（示例仅验证用户名）
        if ("张三".equals(name)) {
            return new ResponseEntity<>("用户名正确", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("用户名错误", HttpStatus.BAD_REQUEST);
        }
    }
}