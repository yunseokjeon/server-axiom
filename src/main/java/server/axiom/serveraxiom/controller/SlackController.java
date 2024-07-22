package server.axiom.serveraxiom.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    /*
    https://api.slack.com/apps/A05E4TZ0H9S/slash-commands

    Features >> Slash Commands >>
    Request URL :<URL>/slack/hello
     */

    @PostMapping("hello")
    public ResponseEntity<String> handleSlashCommand(@RequestParam Map<String, String> payload) {
        String command = payload.get("command");
        String text = payload.get("text");
        String userId = payload.get("user_id");
        String channelId = payload.get("channel_id");

        String responseText;
        if ("/hello".equals(command)) {
            responseText = "안녕하세요, <@" + userId + ">님!";
        } else if ("/echo".equals(command)) {
            responseText = "에코: " + text;
        } else {
            responseText = "알 수 없는 명령어입니다.";
        }

        // Slack에 응답
        return ResponseEntity.ok("{\"text\": \"" + responseText + "\"}");
    }

}
