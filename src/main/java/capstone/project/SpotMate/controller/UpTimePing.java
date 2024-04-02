package capstone.project.SpotMate.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ping")
public class UpTimePing {
    @GetMapping("/check")
    private String ping() {
        return "pong";
    }

    @GetMapping("/jenkins")
    private String jenkins() {
        return "pong";
    }
}

