package elice.people.api.monitoring.controller;

import elice.people.api.monitoring.dto.ApiResDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class MonitoringController {

    @GetMapping("/success")
    public ResponseEntity<ApiResDto> success() {
        ApiResDto response = new ApiResDto(200, "/success", "success", LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/delay")
    public ResponseEntity<ApiResDto> delay(
            @RequestParam(defaultValue = "5000") long ms
    ) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        ApiResDto response = new ApiResDto(200, "/delay", "delayed response", LocalDateTime.now(), ms);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/error")
    public ResponseEntity<ApiResDto> error() {
        ApiResDto response = new ApiResDto(500, "/error", "intentional server error", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}