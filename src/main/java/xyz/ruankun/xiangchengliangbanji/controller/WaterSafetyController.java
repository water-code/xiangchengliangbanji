package xyz.ruankun.xiangchengliangbanji.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.ruankun.xiangchengliangbanji.entity.WaterSafety;
import xyz.ruankun.xiangchengliangbanji.entity.WaterSafetyType;
import xyz.ruankun.xiangchengliangbanji.service.WaterSafetyService;

import java.util.List;

@RestController
@RequestMapping("/api/water-safety")
@CrossOrigin
public class WaterSafetyController {

    @Autowired
    WaterSafetyService waterSafetyService;

    @GetMapping
    public ResponseEntity<List<WaterSafety>> getAllWaterSafeties() {
        List<WaterSafety> waterSafeties = waterSafetyService.getAllWaterSafeties();
        return new ResponseEntity<>(waterSafeties, HttpStatus.OK);
    }

    @GetMapping("/by-name-type")
    public ResponseEntity<List<WaterSafety>> getWaterSafetiesByNameAndType(@RequestParam(value = "name") String name,@RequestParam(value = "type") String type) {

        List<WaterSafety> waterSafeties = waterSafetyService.findWaterSafetiesByNameAndType(name, type.equals("堤防")? WaterSafetyType.堤防:WaterSafetyType.护岸);
        return new ResponseEntity<>(waterSafeties, HttpStatus.OK);
    }

    @GetMapping("/by-type")
    public ResponseEntity<List<WaterSafety>> getWaterSystemsByType(@RequestParam(value = "type") String type) {
        List<WaterSafety> waterSafeties = waterSafetyService.findWaterSafetiesByType(type.equals("堤防")? WaterSafetyType.堤防:WaterSafetyType.护岸);
        return new ResponseEntity<>(waterSafeties, HttpStatus.OK);
    }


}
