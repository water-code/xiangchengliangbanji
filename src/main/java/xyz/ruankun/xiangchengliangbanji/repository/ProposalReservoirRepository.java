package xyz.ruankun.xiangchengliangbanji.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import xyz.ruankun.xiangchengliangbanji.entity.ProposalReservoir;

@Repository
public interface ProposalReservoirRepository extends JpaRepository<ProposalReservoir, Integer> {
    List<ProposalReservoir> findByRegionContaining(String region);
    List<ProposalReservoir> findByRiverContaining(String river);
}