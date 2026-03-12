package elice.people.api.monitoring.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ApiResDto {
    private int status;
    private String path;
    private String message;
    private LocalDateTime timestamp;
    private Long delayMs;

    public ApiResDto(int status, String path, String message, LocalDateTime timestamp) {
        this.status = status;
        this.path = path;
        this.message = message;
        this.timestamp = timestamp;
    }

    public ApiResDto(int status, String path, String message, LocalDateTime timestamp, Long delayMs) {
        this.status = status;
        this.path = path;
        this.message = message;
        this.timestamp = timestamp;
        this.delayMs = delayMs;
    }
}
