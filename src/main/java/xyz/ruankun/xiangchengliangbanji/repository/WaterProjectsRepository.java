package xyz.ruankun.xiangchengliangbanji.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.ruankun.xiangchengliangbanji.entity.WaterProjects;

import java.util.List;

@Repository
public interface WaterProjectsRepository extends JpaRepository<WaterProjects, Integer> {

    List<WaterProjects> findAllByRiverContaining(String river);

    List<WaterProjects> findAllByRegionContaining(String region);
}
