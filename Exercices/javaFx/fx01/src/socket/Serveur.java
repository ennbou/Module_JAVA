package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Serveur {

	private ServerSocket serversocket = null;
	private InputStream input = null;
	private OutputStream output = null;
	private Socket socketEnd;

	public Serveur() {
		try {
			System.out.println("serveur.................");
			serversocket = new ServerSocket(3333);
			System.out.println("satrted ............");

		} catch (Exception e) {

			System.out.println(e.getMessage());
		}
	}

	public void sendInfo() {
		try {
			int num = input.read();
			System.out.println(num);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void accepter() {
		try {
			System.out.println("attedre............");
			socketEnd = serversocket.accept();
			System.out.println("connexion........");
			input = socketEnd.getInputStream();
			output = socketEnd.getOutputStream();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public static void main(String[] args) throws IOException {
		Serveur sr = new Serveur();
		sr.accepter();
		sr.sendInfo();
	}
}
