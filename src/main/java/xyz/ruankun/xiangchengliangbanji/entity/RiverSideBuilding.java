package xyz.ruankun.xiangchengliangbanji.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "riversidebuilding")
@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RiverSideBuilding {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name="";

    @Column(name = "intro")
    private String intro="";

    @Column(name = "region")
    private String region="";

    @Column(name = "river")
    private String river="";

    @Column(name = "type")
    private String type="";

    @Column(name = "x")
    private Double x = 0d;

    @Column(name = "y")
    private Double y = 0d;
}
