package br.com.marcia.salesreport.entity;

import br.com.marcia.salesreport.eumeration.RegisterTypeEnum;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Entity(name = "register")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class RegisterEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "register_type")
    @Enumerated(EnumType.STRING)
    private RegisterTypeEnum registerType;
}
