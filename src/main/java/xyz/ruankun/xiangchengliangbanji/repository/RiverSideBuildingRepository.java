package xyz.ruankun.xiangchengliangbanji.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import xyz.ruankun.xiangchengliangbanji.entity.RiverSideBuilding;

@Repository
public interface RiverSideBuildingRepository extends JpaRepository<RiverSideBuilding, Integer> {
    List<RiverSideBuilding> findByRegionContaining(String region);
    List<RiverSideBuilding> findByRiverContaining(String river);
}