package io.dant.network.cours;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * @author Olivier Pitton <olivier@indexima.com> on 15/12/2020
 */

public class NetworkInterfaceExample {

	public static void main(String[] args) throws SocketException {
		Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

		while (interfaces.hasMoreElements()) {
			NetworkInterface ni = interfaces.nextElement();

			System.out.println("Network interface : ");
			System.out.println("  nom court    = " + ni.getName());
			System.out.println("  désignation  = " + ni.getDisplayName());

			Enumeration<InetAddress> adresses = ni.getInetAddresses();
			while (adresses.hasMoreElements()) {
				InetAddress ia = adresses.nextElement();
				System.out.println("  adresse I.P. = " + ia);
			}
		}
	}

}
