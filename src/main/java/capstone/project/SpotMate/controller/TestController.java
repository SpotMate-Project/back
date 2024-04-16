package capstone.project.SpotMate.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {
    @GetMapping("/test")
    public String test(){
        return "test는 성공적";
    }

    @GetMapping("/test2")
    public String test2() { return "jenkins 해결해줘"; }

    @GetMapping("/test3")
    public String test3() { return "최종 확인"; }

}
