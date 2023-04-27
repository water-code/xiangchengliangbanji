package xyz.ruankun.xiangchengliangbanji.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "water_resources")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WaterResources {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "water_systems_id")
    private Integer waterSystemsId;

    @Enumerated(EnumType.STRING)
    @Column(name = "resource_type")
    private ResourceType resourceType;

    @Column(name = "resource_volume")
    private Float resourceVolume;

    @Enumerated(EnumType.STRING)
    @Column(name = "resource_usage")
    private ResourceUsage resourceUsage;

    @Enumerated(EnumType.STRING)
    @Column(name = "resource_quality")
    private ResourceQuality resourceQuality;

    @Enumerated(EnumType.STRING)
    @Column(name = "resource_status")
    private ResourceStatus resourceStatus;

    @Column(name = "extraction_rate")
    private Float extractionRate;

    @Column(name = "data_year")
    @Temporal(TemporalType.DATE)
    private Date dataYear;

    @Enumerated(EnumType.STRING)
    @Column(name = "if_delete", nullable = false)
    private YesNoEnum ifDelete = YesNoEnum.是;

    @Column(name = "modify_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyTime = new Date();

    @Column(name = "create_time", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime = new Date();

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "extra_json", nullable = true, columnDefinition = "text")
    private String extraJson;
}

enum ResourceType {
    SURFACE_WATER("地表水"),
    GROUND_WATER("地下水"),
    RECYCLED_WATER("再生水");

    private final String name;

    ResourceType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

enum ResourceUsage {
    AGRICULTURE("农业"),
    INDUSTRY("工业"),
    LIFE("生活"),
    ECOLOGY("生态");

    private final String name;

    ResourceUsage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

enum ResourceQuality {
    EXCELLENT("优"),
    GOOD("良"),
    AVERAGE("中"),
    POOR("差");

    private final String name;

    ResourceQuality(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

enum ResourceStatus {
    ADEQUATE("充足"),
    TENSE("紧张"),
    EXHAUSTED("枯竭");

    private final String name;

    ResourceStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
