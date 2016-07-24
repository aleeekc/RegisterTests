import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;


@SuppressWarnings("deprecation")
public class StressTest extends Thread {

	public void run() {

		String response_string = "";
		String uuid = UUID.randomUUID().toString();
		uuid.replaceAll("-", "");
		uuid = uuid.substring(0, 6);
		
		
		HttpClient client = new DefaultHttpClient();
		HttpPost POST = new HttpPost("https://svejo.net/user");

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("utf8",
				"✓"));
		urlParameters.add(new BasicNameValuePair("authenticity_token",
				"WY2GsRRITxAle4u+33F9jZoaxMeG2Ga9e8QbeKe+qhs="));
		urlParameters.add(new BasicNameValuePair("user[email]", "asdasd@asd"
				+ uuid + ".bg"));
		System.out.println("EMAIL : " + "asdasd@asd" + uuid + ".bg");
		urlParameters.add(new BasicNameValuePair("user[username]", "asdasd"
				+ uuid));
		System.out.println("USERNAME  : " + "asdasd" + uuid);
		urlParameters.add(new BasicNameValuePair("user[password]",
				"parolata123"));
		System.out.println("PASSWORD  : " + "parolata123");
		urlParameters.add(new BasicNameValuePair("user[password_conformation]",
				"parolata123"));
		urlParameters.add(new BasicNameValuePair("commit", "Създай+профил"));
		
		
		try {
			POST.setEntity(new UrlEncodedFormEntity(urlParameters));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		try {

			System.out.println(POST.toString());
			HttpResponse response = client.execute(POST);
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));

			int response_code = response.getStatusLine().getStatusCode();
			System.out.println("RESPONSE: " + response_code);

			String line = "";
			while ((line = rd.readLine()) != null) {
				response_string = response_string + line;
			}

			System.out.println(response_string);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
