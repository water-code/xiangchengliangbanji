package xyz.ruankun.xiangchengliangbanji.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "solarirstation")
@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolarIrStation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name="";

    @Column(name = "comment")
    private String comment="";

    @Column(name = "lclr")
    private Integer lclr=0;

    @Column(name = "lwidth")
    private Double lwidth=0d;

    @Column(name = "lalpha")
    private Double lalpha=0d;

    @Column(name = "aalpha")
    private Double aalpha=0d;

    @Column(name = "aclr")
    private Integer aclr=0;

    @Column(name = "flag")
    private Boolean flag=false;

    @Column(name = "region")
    private String region="";

    @Column(name = "river")
    private String river="";

}
