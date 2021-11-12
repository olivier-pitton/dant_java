package io.dant.network.cours;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author Olivier Pitton <olivier@indexima.com> on 12/12/2020
 */

public class InetAddressExample {

	public static void main(String[] args) throws IOException, URISyntaxException {
		// localhost
		InetAddress localhost = InetAddress.getByName("localhost");
		System.out.println(localhost.getHostName() + " " + localhost.getHostAddress());
		// Microsoft
		InetAddress[] microsoftAddresses = InetAddress.getAllByName("www.microsoft.com");
		for (InetAddress microsoft : microsoftAddresses) {
			System.out.println(microsoft.getHostName() + " " + microsoft.getHostAddress());
		}

		// Obtenir des infos sur une URL et la lire
		URL google = new URL("https://google.com");
		System.out.println(google.getAuthority() + " " + google.getProtocol());
	}

}
