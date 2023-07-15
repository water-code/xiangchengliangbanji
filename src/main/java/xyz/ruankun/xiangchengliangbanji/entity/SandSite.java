package xyz.ruankun.xiangchengliangbanji.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "sandsite")
@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SandSite {
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

}
