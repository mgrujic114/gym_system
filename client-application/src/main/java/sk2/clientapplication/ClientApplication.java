package sk2.clientapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sk2.clientapplication.view.LoginView;
import sk2.clientapplication.view.TrainingsView;

import javax.swing.*;
import java.awt.*;

@SpringBootApplication
public class ClientApplication extends JFrame {

	private String token;
	private LoginView loginView;
	private TrainingsView trainingsView;

	private ClientApplication() throws IllegalAccessException, NoSuchMethodException {
		this.setTitle("Client Application");
		this.setSize(1200, 1200);
		this.setLayout(new BorderLayout());

		loginView = new LoginView();
		this.add(loginView, BorderLayout.NORTH);

		trainingsView = new TrainingsView();
		this.add(trainingsView, BorderLayout.CENTER);

		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private static class InstanceHolder {
		private static ClientApplication instance;

		static {
			try {
				instance = new ClientApplication();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LoginView getLoginView() {
		return loginView;
	}

	public void setLoginView(LoginView loginView) {
		this.loginView = loginView;
	}

	public TrainingsView getTrainingsView() {
		return trainingsView;
	}

	public void setTrainingsView(TrainingsView trainingsView) {
		this.trainingsView = trainingsView;
	}

	public static ClientApplication getInstance() {
		return InstanceHolder.instance;
	}

}
