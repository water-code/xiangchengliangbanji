package xyz.ruankun.xiangchengliangbanji.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import xyz.ruankun.xiangchengliangbanji.entity.SandSite;

@Repository
public interface SandSiteRepository extends JpaRepository<SandSite, Integer> {

    List<SandSite> findByRegionContaining(String region);

    List<SandSite> findByRiverContaining(String river);

}