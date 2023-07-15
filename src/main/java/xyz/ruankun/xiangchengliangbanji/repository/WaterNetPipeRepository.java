package xyz.ruankun.xiangchengliangbanji.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import xyz.ruankun.xiangchengliangbanji.entity.WaterNetPipe;

@Repository
public interface WaterNetPipeRepository extends JpaRepository<WaterNetPipe, Integer> {
    List<WaterNetPipe> findByRegionContaining(String region);
    List<WaterNetPipe> findByRiverContaining(String river);
}