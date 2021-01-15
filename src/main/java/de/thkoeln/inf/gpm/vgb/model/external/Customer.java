package de.thkoeln.inf.gpm.vgb.model.external;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Kunde")
public class Customer {

    @Id
    @Column(name = "kundennr")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long kundennr;

    private Date eintritt;
    private Long versicherter_id;

    public Customer(Long kundennr, Date eintritt, Long versicherter_id) {
        this.kundennr = kundennr;
        this.eintritt = eintritt;
        this.versicherter_id = versicherter_id;
    }

    public Long getKundennr() {
        return kundennr;
    }

    public void setKundennr(Long kundennr) {
        this.kundennr = kundennr;
    }

    public Date getEintritt() {
        return eintritt;
    }

    public void setEintritt(Date eintritt) {
        this.eintritt = eintritt;
    }

    public Long getVersicherter_id() {
        return versicherter_id;
    }

    public void setVersicherter_id(Long versicherter_id) {
        this.versicherter_id = versicherter_id;
    }
}
