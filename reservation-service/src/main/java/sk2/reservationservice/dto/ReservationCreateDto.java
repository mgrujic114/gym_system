package sk2.reservationservice.dto;

public class ReservationCreateDto {

    private Long userId;
    private Long sessionId;

    public ReservationCreateDto() {
    }

    public ReservationCreateDto(Long userId, Long sessionId) {
        this.userId = userId;
        this.sessionId = sessionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }
}
