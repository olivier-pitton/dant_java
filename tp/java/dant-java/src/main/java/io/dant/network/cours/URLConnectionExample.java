package io.dant.network.cours;

import org.apache.commons.io.IOUtils;

import java.io.DataInputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author Olivier Pitton <olivier@indexima.com> on 15/12/2020
 */

public class URLConnectionExample {

	public static void main(String[] args) {
		HttpURLConnection connexion = null;

		try {
			URL url = new URL("http://java.sun.com");
			//URL url = new URL("https://developpez.com");

			System.out.println("Connexion a l'url ...");
			connexion = (HttpURLConnection) url.openConnection();

			connexion.setAllowUserInteraction(true);
			DataInputStream in = new DataInputStream(connexion.getInputStream());

			if (connexion.getResponseCode() != HttpURLConnection.HTTP_OK) {
				System.err.println(connexion.getResponseMessage());
			} else {
				StringWriter output = new StringWriter();
				IOUtils.copy(in, output, StandardCharsets.UTF_8);
				System.out.println(output.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connexion.disconnect();
		}
	}

}
