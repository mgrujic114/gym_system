package sk2.clientapplication.restclient.dto;

import java.util.ArrayList;
import java.util.List;

public class TrainingListDto {

    private List<TrainingDto> content = new ArrayList<>();

    public TrainingListDto() {

    }

    public TrainingListDto(List<TrainingDto> content) {
        this.content = content;
    }

    public List<TrainingDto> getContent() {
        return content;
    }

    public void setContent(List<TrainingDto> content) {
        this.content = content;
    }
}
