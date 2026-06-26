package com.sms.sensor_measurement_system;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpSender {

    public static void main(String[] args) throws Exception {

        DatagramSocket socket = new DatagramSocket();

        String message = "sensor_id=t1;value=40";

        DatagramPacket packet = new DatagramPacket(

                message.getBytes(),

                message.length(),

                InetAddress.getByName("127.0.0.1"),

                13346

        );

        socket.send(packet);

        socket.close();

        System.out.println("UDP Packet Sent");

    }

}