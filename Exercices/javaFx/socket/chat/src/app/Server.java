package app;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

class Server {

    private static double sum = 0;

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(8080);) {

            Socket s1, s2;
            final DataOutputStream out1, out2;

            s1 = server.accept();
            final ObjectInputStream in1 = new ObjectInputStream(s1.getInputStream());
            out1 = new DataOutputStream(s1.getOutputStream());

            (new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        @SuppressWarnings("unchecked")
                        List<Double> list = (ArrayList<Double>) in1.readObject();
                        Server.sum += list.stream().mapToDouble(v -> v).sum();

                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            })).start();

            s2 = server.accept();
            final ObjectInputStream in2 = new ObjectInputStream(s2.getInputStream());
            out2 = new DataOutputStream(s2.getOutputStream());

            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        @SuppressWarnings("unchecked")
                        List<Double> list = (ArrayList<Double>) in2.readObject();
                        Server.sum += list.stream().mapToDouble(v -> v).sum();

                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            t2.start();
            t2.join();

            out1.writeDouble(sum);
            out2.writeDouble(sum);

        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
