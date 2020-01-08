package socket;

import java.io.IOException;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Client extends Application {
	private Socket socketend = null;
	private InputStream input = null;
	private OutputStream output = null;

	// objet interface

	public Scene scene = null;
	private int windowWith = 900;
	private int windowheigth = 600;
	private Label lblcode;
	private TextField txtcode;
	private Button btnconnet;

	private BorderPane root;

	public Client() {
		try {
			socketend = new Socket("127.0.0.1", 3333);
			input = socketend.getInputStream();
			output = socketend.getOutputStream();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void getInfoCompt(int i) {
		// int num = Integer.parseInt(txtcode.getText());
		try {
			output.write(i);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	static Client c;

	public static void main(String[] args) throws UnknownHostException, IOException {

		/*
		 * System.out.println("Client..............."); try{
		 * System.out.println("demande de connexion au server");
		 * System.out.println("connexion �tablie avec le serveur");
		 * System.out.println("Programme client termin�");
		 * 
		 * }catch(Exception exp){ System.out.println(exp.toString()); } Client c=new
		 * Client(); c.getInfoCompt();
		 */
		c = new Client();
		Application.launch(args);
	}

	@Override
	public void start(Stage window) throws Exception {

		lblcode = new Label("code");
		txtcode = new TextField();
		btnconnet = new Button("envoi");

		btnconnet.setOnAction(v -> {
			System.out.println("click");
			c.getInfoCompt(Integer.parseInt(txtcode.getText()));
		});

		root = new BorderPane();
		root.setCenter(lblcode);
		root.setBottom(txtcode);
		root.setLeft(btnconnet);
		scene = new Scene(root);
		window.setWidth(windowWith);
		window.setHeight(windowheigth);
		window.setScene(scene);
		window.show();
	}

}
