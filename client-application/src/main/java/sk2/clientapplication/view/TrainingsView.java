package sk2.clientapplication.view;

import sk2.clientapplication.model.TrainingTableModel;
import sk2.clientapplication.restclient.TrainingServiceRestClient;
import sk2.clientapplication.restclient.dto.TrainingListDto;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class TrainingsView extends JPanel {

    private TrainingTableModel trainingTableModel;
    private JTable trainingTable;
    private TrainingServiceRestClient trainingServiceRestClient;
    private JButton jButton;

    public TrainingsView() throws IllegalAccessException, NoSuchMethodException {
        super();
        this.setSize(400, 400);

        trainingTableModel = new TrainingTableModel();
        trainingServiceRestClient = new TrainingServiceRestClient();
        trainingTable = new JTable(trainingTableModel);
        this.setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(trainingTable);
        this.add(scrollPane, BorderLayout.NORTH);

        jButton = new JButton("Create Order");
        this.add(jButton, BorderLayout.CENTER);

        jButton.addActionListener((event) -> {
            System.out.println(trainingTableModel.getTrainingListDto().getContent().get(trainingTable.getSelectedRow()).getId());
        });

        setVisible(false);
    }

    public void init() throws IOException {
        this.setVisible(true);

        TrainingListDto trainingListDto = trainingServiceRestClient.getTrainings();
        trainingListDto.getContent().forEach(trainingDto -> {
            System.out.println(trainingDto);
            trainingTableModel.addRow(new Object[]{trainingDto.getTitle(), trainingDto.getDescription(), trainingDto.getId()});
        });

    }

    public TrainingTableModel getTrainingTableModel() {
        return trainingTableModel;
    }

    public JTable getTrainingTable() {
        return trainingTable;
    }
}

