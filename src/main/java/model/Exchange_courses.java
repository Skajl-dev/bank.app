package model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "exchange_courses")
public class Exchange_courses {
    @Id
    @Temporal(TemporalType.DATE)
    @Column(name = "creation_time")
    Date creation_time;

    @Column(name = "UAH_to_Dollar")
    double UAH_to_Dollar;

    @Column(name = "Dollar_to_UAH")
    double Dollar_to_UAH;

    @Column(name = "UAH_to_Euro")
    double UAH_to_Euro;

    @Column(name = "Euro_to_UAH")
    double Euro_to_UAH;

    public Exchange_courses() {
    }

    public Exchange_courses(double UAHtoDollar, double dollar_to_UAH, double UAH_to_Euro, double euro_to_UAH) {
        this.UAH_to_Dollar = UAHtoDollar;
        Dollar_to_UAH = dollar_to_UAH;
        this.UAH_to_Euro = UAH_to_Euro;
        Euro_to_UAH = euro_to_UAH;
        creation_time = new Date();
    }


    public Date getDate() {
        return creation_time;
    }

    public void setDate(Date date) {
        this.creation_time = date;
    }

    public double getUAHtoDollar() {
        return UAH_to_Dollar;
    }

    public void setUAHtoDollar(double UAHtoDollar) {
        this.UAH_to_Dollar = UAHtoDollar;
    }

    public double getDollar_to_UAH() {
        return Dollar_to_UAH;
    }

    public void setDollar_to_UAH(double dollar_to_UAH) {
        Dollar_to_UAH = dollar_to_UAH;
    }

    public double getUAH_to_Euro() {
        return UAH_to_Euro;
    }

    public void setUAH_to_Euro(double UAH_to_Euro) {
        this.UAH_to_Euro = UAH_to_Euro;
    }

    public double getEuro_to_UAH() {
        return Euro_to_UAH;
    }

    public void setEuro_to_UAH(double euro_to_UAH) {
        Euro_to_UAH = euro_to_UAH;
    }

    @Override
    public String toString() {
        return "Exchange_courses{" +
                "creation_time=" + creation_time +
                ", UAH_to_Dollar=" + UAH_to_Dollar +
                ", Dollar_to_UAH=" + Dollar_to_UAH +
                ", UAH_to_Euro=" + UAH_to_Euro +
                ", Euro_to_UAH=" + Euro_to_UAH +
                '}';
    }
}
