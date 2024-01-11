package sk2.reservationservice.dto;

public class ReservationCreateDto {

    private Long userId;
    private Long projectionId;

    public ReservationCreateDto() {
    }

    public ReservationCreateDto(Long userId, Long projectionId) {
        this.userId = userId;
        this.projectionId = projectionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProjectionId() {
        return projectionId;
    }

    public void setProjectionId(Long projectionId) {
        this.projectionId = projectionId;
    }
}
