package xyz.ruankun.xiangchengliangbanji.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import xyz.ruankun.xiangchengliangbanji.entity.SolarIrStation;

@Repository
public interface SolarIrStationRepository extends JpaRepository<SolarIrStation, Integer> {

    List<SolarIrStation> findByRegionContaining(String region);

    List<SolarIrStation> findByRiverContaining(String river);
}