package app;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

class Client {

    public static void main(String[] args) {

        List<Double> list = new ArrayList<>(); // 18
        list.add(1.1);
        list.add(3.2);
        list.add(4.5);
        list.add(9.2);

        try (Socket socket = new Socket("127.0.0.1", 8080);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                DataInputStream in = new DataInputStream(socket.getInputStream());) {
            out.writeObject(list);
            double sum = in.readDouble();
            System.out.println("la somme de list est : " + sum);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}