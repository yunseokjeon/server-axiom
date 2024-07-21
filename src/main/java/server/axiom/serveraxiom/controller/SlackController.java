package server.axiom.serveraxiom.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/slack")
@RequiredArgsConstructor
public class SlackController {

    @GetMapping("/ngrok")
    public String ngrokTest() {
        /*
        ngrok.exe http {포트번호}
        ngrok-URL/slack/ngrok
         */
        return "ngrok";
    }
}
