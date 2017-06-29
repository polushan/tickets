package model;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "request")
public class Request {

    @Id
    @GenericGenerator(name="gen",strategy="increment")
    @GeneratedValue(generator="gen")
    @Column(name = "request_id")
    private long requestId;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="from", nullable = false, referencedColumnName = "name")
    private City from;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="to", nullable = false, referencedColumnName = "name")
    private City to;
    @Column(name = "date")
    @Temporal(value = TemporalType.DATE)
    private Date date;
    @Column(name = "transport", nullable = false)
    private String transportType;


    public Request() {

    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public User getUserId() {
        return user;
    }

    public void setUserId(User userId) {
        this.user = userId;
    }

    public City getFrom() {
        return from;
    }

    public void setFrom(City from) {
        this.from = from;
    }

    public City getTo() {
        return to;
    }

    public void setTo(City to) {
        this.to = to;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        if ("plane".equals(transportType) || "train".equals(transportType) || "bus".equals(transportType)
                || "all".equals(transportType)) {
            this.transportType = transportType;
        }
    }

}