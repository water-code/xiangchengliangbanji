package xyz.ruankun.xiangchengliangbanji.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import xyz.ruankun.xiangchengliangbanji.entity.IrrigationArea;

@Repository
public interface IrrigationAreaRepository extends JpaRepository<IrrigationArea, Integer> {
    List<IrrigationArea> findByRegionContaining(String region);
    List<IrrigationArea> findByRiverContaining(String river);
}