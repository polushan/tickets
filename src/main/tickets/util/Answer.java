package util;

import java.util.List;
import java.util.ArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Answer {

    private List<String> to;
    private List<String> from;
    private List<String> title;
    private List<String> transportType;
    private List<String> arrival;
    private List<String> departure;

    public Answer(Document doc) {
        NodeList threads = doc.getElementsByTagName("threads");
        to = new ArrayList<String>();
        from = new ArrayList<String>();
        title = new ArrayList<String>();
        transportType = new ArrayList<String>();
        arrival = new ArrayList<String>();
        departure = new ArrayList<String>();
        Element element;
        if (threads.getLength() > 1) {
            for (int i = 0; i < threads.getLength(); i++) {
                element = (Element) threads.item(i);
                to.add(element.getElementsByTagName("to").item(0).getChildNodes().item(5).getTextContent());
                from.add(element.getElementsByTagName("from").item(0).getChildNodes().item(5).getTextContent());
                title.add(element.getElementsByTagName("thread").item(0).getChildNodes().item(3).getTextContent());
                transportType.add(element.getElementsByTagName("thread").item(0).getChildNodes().item(15)
                        .getTextContent());
                arrival.add(element.getElementsByTagName("arrival").item(0).getTextContent());
                departure.add(element.getElementsByTagName("departure").item(0).getTextContent());
            }
        }
    }

    public List<String> getTo() {
        return to;
    }

    public void setTo(List<String> to) {
        this.to = to;
    }

    public List<String> getFrom() {
        return from;
    }

    public void setFrom(List<String> from) {
        this.from = from;
    }

    public List<String> getTitle() {
        return title;
    }

    public void setTitle(List<String> title) {
        this.title = title;
    }

    public List<String> gettransportType() {
        return transportType;
    }

    public void transportType(List<String> transportType) {
        this.transportType = transportType;
    }

    public List<String> getArrival() {
        return arrival;
    }

    public void setArrival(List<String> arrival) {
        this.arrival = arrival;
    }

    public List<String> getDeparture() {
        return departure;
    }

    public void setDeparture(List<String> departure) {
        this.departure = departure;
    }
}
