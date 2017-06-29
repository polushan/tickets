package util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import model.Request;

public class RequestSender {

    private static final String apiKey = "b13b8a87-53d2-4b5a-832b-7d6d7e1cf59a";
    private static final String yandex = "https://api.rasp.yandex.net/v1.0/search/?";
    private static final String tail = "&system=iata";

    public String checkTransport(String transport) {
        if ("bus".equals(transport)) {
            return "&transport_type=bus";
        } else if ("train".equals(transport)) {
            return "&transport_type=train";
        } else if ("plane".equals(transport)) {
            return "&transport_type=plane";
        } else {
            return "";
        }
    }

    public Document sendRequest(Request userRequest, String date) throws IOException {
        String from = userRequest.getFrom().getId().trim();
        String to = userRequest.getTo().getId().trim();
        String transportType = checkTransport(userRequest.getTransportType());
        if (date != null) {
            date = "&date=" + date;
        } else {
            date = "";
        }
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(false);
        Document doc = null;
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            URL url = new URL(yandex + "apikey=" + apiKey + "&format=xml" + "&from=" + from + "&to=" + to + date
                    + transportType + tail);
            URLConnection urlConnection = url.openConnection();
            doc = builder.parse(urlConnection.getInputStream());
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return doc;
    }
}
