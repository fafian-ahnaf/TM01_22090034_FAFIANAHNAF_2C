package network;

import java.io.IOException;
import java.net.URL;

public class FirstConnect {
    public static void main(String[] args) throws IOException {
        ConnectURI koneksisaya = new ConnectURI();
        URL myaddress = koneksisaya.buildURL
                ("https://harber.mimoapps.xyz/api/getaccess.php");
        String response = koneksisaya.getResponseFromHttpUrl(myaddress);
        System.out.println("web dari web adalah :" + response);

        }
    }

