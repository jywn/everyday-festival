package com.festival.everyday.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * 홈 화면으로 이동하는 컨트롤러.
 * 서비스의 홈페이지와 무관합니다.
 * 정상 실행시 흰 화면이 출력됩니다.
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index.html";
    }
}
