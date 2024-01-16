package sk2.clientapplication.model;

import sk2.clientapplication.restclient.dto.TrainingDto;
import sk2.clientapplication.restclient.dto.TrainingListDto;

import javax.swing.table.DefaultTableModel;

public class TrainingTableModel extends DefaultTableModel {


    public TrainingTableModel() throws IllegalAccessException, NoSuchMethodException {
        super(new String[]{"Title", "Description"}, 0);
    }

    private TrainingListDto trainingListDto = new TrainingListDto();

    @Override
    public void addRow(Object[] row) {
        super.addRow(row);
        TrainingDto dto = new TrainingDto();
        dto.setTitle(String.valueOf(row[0]));
        dto.setDescription(String.valueOf(row[1]));
        dto.setId(Long.valueOf(String.valueOf(row[2])));
        trainingListDto.getContent().add(dto);
    }

    public TrainingListDto getTrainingListDto() {
        return trainingListDto;
    }

    public void setTrainingListDto(TrainingListDto trainingListDto) {
        this.trainingListDto = trainingListDto;
    }
}
