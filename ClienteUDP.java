/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javax.swing.JOptionPane;

/**
 *
 * @author Richard
 */
public class ClienteUDP {

    private static final int SERVER_PORT = 9091;

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {

        String serverAddress = JOptionPane.showInputDialog("Enter IP Address of a machine that is\n"
                + "running the date service on port " + SERVER_PORT + ":");

        try ( //Send packet (Request)
                DatagramSocket clientSocket = new DatagramSocket()) {
            byte bufferSend[] = serverAddress.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(bufferSend, bufferSend.length, InetAddress.getByName(serverAddress), SERVER_PORT);
            clientSocket.send(sendPacket);

            //Receive packet
            byte bufferReceive[] = new byte[128];
            DatagramPacket receivePacket = new DatagramPacket(bufferReceive, bufferReceive.length);
            clientSocket.receive(receivePacket);

            //Transforma bytes a String
            InputStream myInputStream = new ByteArrayInputStream(receivePacket.getData());
            BufferedReader input = new BufferedReader(new InputStreamReader(myInputStream));
            String answer = input.readLine();

            //Despliega mensaje
            JOptionPane.showMessageDialog(null, answer);
        }
        System.exit(0);
    }

}
