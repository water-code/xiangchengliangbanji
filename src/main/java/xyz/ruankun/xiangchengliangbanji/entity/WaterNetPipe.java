package xyz.ruankun.xiangchengliangbanji.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "waternetpipe")
@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WaterNetPipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name="";

    @Column(name = "comment")
    private String comment="";

    @Column(name = "region")
    private String region="";

    @Column(name = "river")
    private String river="";

    @Column(name = "stype")
    private Integer stype=0;

    @Column(name = "ttype")
    private Integer ttype=0;

    @Column(name = "ltype")
    private Integer ltype=0;

    @Column(name = "lalpha")
    private Double lalpha=0d;

    @Column(name = "lwidth")
    private Double lwidth=0d;

    @Column(name = "lclr")
    private Double lclr=0d;

}
