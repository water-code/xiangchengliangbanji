package xyz.ruankun.xiangchengliangbanji.repository;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.ruankun.xiangchengliangbanji.entity.WaterSafety;
import xyz.ruankun.xiangchengliangbanji.entity.WaterSafetyType;
import xyz.ruankun.xiangchengliangbanji.entity.WaterSystems;

import java.util.List;

@Repository
public interface WaterSafetyRepository extends JpaRepository<WaterSafety, Integer> {
    List<WaterSafety> findWaterSafetyByType(WaterSafetyType type);

    List<WaterSafety> findWaterSafetyByNameContainingAndType(String name, WaterSafetyType type);



}