package xyz.ruankun.xiangchengliangbanji.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.ruankun.xiangchengliangbanji.entity.HealthEvaluation;

import java.util.List;

@Repository
public interface HealthEvaluationRepository extends JpaRepository<HealthEvaluation, Integer> {
    /**
     * 通过水系ID查询健康评价
     * @param waterSystemsId
     * @return
     */
    HealthEvaluation findByWaterSystemsId(Integer waterSystemsId);

    List<HealthEvaluation> findAllByRiverContaining(String river);

    List<HealthEvaluation> findAllByRegionContaining(String region);
}
