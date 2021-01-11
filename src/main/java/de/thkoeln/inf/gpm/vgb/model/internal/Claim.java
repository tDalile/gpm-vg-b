package de.thkoeln.inf.gpm.vgb.model.internal;

import javax.persistence.*;

//Hibernate Framework? Forward engineering (Pojo -> DB)
@Entity
@Table(name = "antrag")
public class Claim {

    @Id
    @Column(name = "antrag_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer antrag_id;

    private String antragsklassifizierung_id;
    private String antragsdatum;
    private String versicherungspolice_id;
    private String krankenhistorie_id;

}
