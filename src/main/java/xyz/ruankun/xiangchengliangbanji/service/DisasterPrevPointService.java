package xyz.ruankun.xiangchengliangbanji.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.ruankun.xiangchengliangbanji.repository.DisasterPreventionPointRepository;

@Service
public class DisasterPrevPointService {

    @Autowired
    DisasterPreventionPointRepository disasterPreventionPointRepository;
}
