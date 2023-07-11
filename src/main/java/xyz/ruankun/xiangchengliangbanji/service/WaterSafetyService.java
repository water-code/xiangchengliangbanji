package xyz.ruankun.xiangchengliangbanji.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.ruankun.xiangchengliangbanji.entity.WaterSafety;
import xyz.ruankun.xiangchengliangbanji.entity.WaterSafetyType;
import xyz.ruankun.xiangchengliangbanji.repository.WaterSafetyRepository;

import java.util.List;

@Service
public class WaterSafetyService {

    @Autowired
    WaterSafetyRepository waterSafetyRepository;

    public List<WaterSafety> getAllWaterSafeties() {
        return waterSafetyRepository.findAll();
    }

    public List<WaterSafety> findWaterSafetiesByType(WaterSafetyType type) {
        return waterSafetyRepository.findWaterSafetyByType(type);
    }

    public List<WaterSafety> findWaterSafetiesByNameAndType(String name, WaterSafetyType type) {
        return waterSafetyRepository.findWaterSafetyByNameContainingAndType(name, type);
    }

}
