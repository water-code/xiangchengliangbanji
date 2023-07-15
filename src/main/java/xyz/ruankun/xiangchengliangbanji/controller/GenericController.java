package xyz.ruankun.xiangchengliangbanji.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.ruankun.xiangchengliangbanji.entity.*;
import xyz.ruankun.xiangchengliangbanji.service.GeneralService;

import java.util.List;

@RestController
@RequestMapping("/api/generic")
@CrossOrigin
@ApiOperation("很简单,WaterNetPipe,SolarIrStation,SandSite,RiverSideBuilding,ProposalReservoir,IrrigationArea都写这里了")
public class GenericController {

    @Autowired
    GeneralService generalService;

    @GetMapping("/solarirstation")
    @ApiOperation("通过表名获取所有数据")
    public ResponseEntity<List<SolarIrStation>> getAllSolarIrStation() {
        List<SolarIrStation> solarIrStations = generalService.getAllSolarIrStation();
        return new ResponseEntity<>(solarIrStations, HttpStatus.OK);
    }
    @GetMapping("/sandsite")
    @ApiOperation("通过表名获取所有数据")
    public ResponseEntity<List<SandSite>> getAllSandSite() {
        List<SandSite> sandSites = generalService.getAllSandSite();
        return new ResponseEntity<>(sandSites, HttpStatus.OK);
    }
    @GetMapping("/riversidebuilding")
    @ApiOperation("通过表名获取所有数据")
    public ResponseEntity<List<RiverSideBuilding>> getAllRiverSideBuilding() {
        List<RiverSideBuilding> riverSideBuildings = generalService.getAllRiverSideBuilding();
        return new ResponseEntity<>(riverSideBuildings, HttpStatus.OK);
    }

    @GetMapping("/proposalreservoir")
    @ApiOperation("通过表名获取所有数据")
    public ResponseEntity<List<ProposalReservoir>> getAllProposalReservoir() {
        List<ProposalReservoir> proposalReservoirs = generalService.getAllProposalReservoir();
        return new ResponseEntity<>(proposalReservoirs, HttpStatus.OK);
    }

    @GetMapping("/irrigationarea")
    @ApiOperation("通过表名获取所有数据")
    public ResponseEntity<List<IrrigationArea>> getAllIrrigationArea() {
        List<IrrigationArea> irrigationAreas = generalService.getAllIrrigationArea();
        return new ResponseEntity<>(irrigationAreas, HttpStatus.OK);
    }

    @GetMapping("/waternetpipe")
    @ApiOperation("通过表名获取所有数据")
    public ResponseEntity<List<WaterNetPipe>> getAllWaterNetPipe() {
        List<WaterNetPipe> waterNetPipes = generalService.getAllWaterNetPipe();
        return new ResponseEntity<>(waterNetPipes, HttpStatus.OK);
    }

    @GetMapping("/waterresources")
    @ApiOperation("通过表名获取所有数据")
    public ResponseEntity<List<WaterResources>> getAllWaterResources() {
        List<WaterResources> waterResources = generalService.getAllWaterResources();
        return new ResponseEntity<>(waterResources, HttpStatus.OK);
    }

    @GetMapping("/waterprojects")
    @ApiOperation("通过表名获取所有数据")
    public ResponseEntity<List<WaterProjects>> getAllWaterProjects() {
        List<WaterProjects> waterProjects = generalService.getAllWaterProjects();
        return new ResponseEntity<>(waterProjects, HttpStatus.OK);
    }

    @GetMapping("/healthevaluation")
    @ApiOperation("通过表名获取所有数据")
    public ResponseEntity<List<HealthEvaluation>> getAllHealthEvaluation() {
        List<HealthEvaluation> healthEvaluations = generalService.getAllHealthEvaluation();
        return new ResponseEntity<>(healthEvaluations, HttpStatus.OK);
    }

    @GetMapping("/watersafety")
    @ApiOperation("通过表名获取所有数据")
    public ResponseEntity<List<WaterSafety>> getAllWaterSafety() {
        List<WaterSafety> waterSafeties = generalService.getAllWaterSafety();
        return new ResponseEntity<>(waterSafeties, HttpStatus.OK);
    }

    @GetMapping("/waternetpipe/byriver")
    @ApiOperation("通过表名获取所有数据")
    public ResponseEntity<List<WaterNetPipe>> getAllWaterNetPipeByRiver(@RequestParam String river) {
        List<WaterNetPipe> waterNetPipes = generalService.getAllWaterNetPipeByRiver(river);
        return new ResponseEntity<>(waterNetPipes, HttpStatus.OK);
    }

    @GetMapping("/sandsite/byriver")
    @ApiOperation("通过river字段获取数据")
    public ResponseEntity<List<SandSite>> getSandSiteByRiver(@RequestParam String river) {
        List<SandSite> sandSites = generalService.getAllSandSiteByRiver(river);
        return new ResponseEntity<>(sandSites, HttpStatus.OK);
    }

    @GetMapping("/riversidebuilding/byriver")
    @ApiOperation("通过river字段获取数据")
    public ResponseEntity<List<RiverSideBuilding>> getRiverSideBuildingByRiver(@RequestParam String river) {
        List<RiverSideBuilding> riverSideBuildings = generalService.getAllRiverSideBuildingByRiver(river);
        return new ResponseEntity<>(riverSideBuildings, HttpStatus.OK);
    }

    @GetMapping("/proposalreservoir/byriver")
    @ApiOperation("通过river字段获取数据")
    public ResponseEntity<List<ProposalReservoir>> getProposalReservoirByRiver(@RequestParam String river) {
        List<ProposalReservoir> proposalReservoirs = generalService.getAllProposalReservoirByRiver(river);
        return new ResponseEntity<>(proposalReservoirs, HttpStatus.OK);
    }

    @GetMapping("/irrigationarea/byriver")
    @ApiOperation("通过river字段获取数据")
    public ResponseEntity<List<IrrigationArea>> getIrrigationAreaByRiver(@RequestParam String river) {
        List<IrrigationArea> irrigationAreas = generalService.getAllIrrigationAreaByRiver(river);
        return new ResponseEntity<>(irrigationAreas, HttpStatus.OK);
    }
    @GetMapping("/solarirstation/byriver")
    @ApiOperation("通过river字段获取数据")
    public ResponseEntity<List<SolarIrStation>> getSolarIrStationByRiver(@RequestParam String river) {
        List<SolarIrStation> solarIrStations = generalService.getAllSolarIrStationByRiver(river);
        return new ResponseEntity<>(solarIrStations, HttpStatus.OK);
    }

    @GetMapping("/waterresources/byriver")
    @ApiOperation("通过river字段获取数据")
    public ResponseEntity<List<WaterResources>> getWaterResourcesByRiver(@RequestParam String river) {
        List<WaterResources> waterResources = generalService.getAllWaterResourcesByRiver(river);
        return new ResponseEntity<>(waterResources, HttpStatus.OK);
    }

    @GetMapping("/waterprojects/byriver")
    @ApiOperation("通过river字段获取数据")
    public ResponseEntity<List<WaterProjects>> getWaterProjectsByRiver(@RequestParam String river) {
        List<WaterProjects> waterProjects = generalService.getAllWaterProjectsByRiver(river);
        return new ResponseEntity<>(waterProjects, HttpStatus.OK);
    }

    @GetMapping("/healthevaluation/byriver")
    @ApiOperation("通过river字段获取数据")
    public ResponseEntity<List<HealthEvaluation>> getHealthEvaluationByRiver(@RequestParam String river) {
        List<HealthEvaluation> healthEvaluations = generalService.getAllHealthEvaluationByRiver(river);
        return new ResponseEntity<>(healthEvaluations, HttpStatus.OK);
    }

    @GetMapping("/watersafety/byriver")
    @ApiOperation("通过river字段获取数据")
    public ResponseEntity<List<WaterSafety>> getWaterSafetyByRiver(@RequestParam String river) {
        List<WaterSafety> waterSafeties = generalService.getAllWaterSafetyByRiver(river);
        return new ResponseEntity<>(waterSafeties, HttpStatus.OK);
    }



    @GetMapping("/solarirstation/byregion")
    @ApiOperation("通过river字段获取数据")
    public ResponseEntity<List<SolarIrStation>> getSolarIrStationByRegion(@RequestParam String region) {
        List<SolarIrStation> solarIrStations = generalService.getAllSolarIrStationByRegion(region);
        return new ResponseEntity<>(solarIrStations, HttpStatus.OK);
    }

    @GetMapping("/sandsite/byregion")
    @ApiOperation("通过region字段获取数据")
    public ResponseEntity<List<SandSite>> getSandSiteByRegion(@RequestParam String region) {
        List<SandSite> sandSites = generalService.getAllSandSiteByRegion(region);
        return new ResponseEntity<>(sandSites, HttpStatus.OK);
    }

    @GetMapping("/riversidebuilding/byregion")
    @ApiOperation("通过region字段获取数据")
    public ResponseEntity<List<RiverSideBuilding>> getRiverSideBuildingByRegion(@RequestParam String region) {
        List<RiverSideBuilding> riverSideBuildings = generalService.getAllRiverSideBuildingByRegion(region);
        return new ResponseEntity<>(riverSideBuildings, HttpStatus.OK);
    }

    @GetMapping("/proposalreservoir/byregion")
    @ApiOperation("通过region字段获取数据")
    public ResponseEntity<List<ProposalReservoir>> getProposalReservoirByRegion(@RequestParam String region) {
        List<ProposalReservoir> proposalReservoirs = generalService.getAllProposalReservoirByRegion(region);
        return new ResponseEntity<>(proposalReservoirs, HttpStatus.OK);
    }

    @GetMapping("/irrigationarea/byregion")
    @ApiOperation("通过region字段获取数据")
    public ResponseEntity<List<IrrigationArea>> getIrrigationAreaByRegion(@RequestParam String region) {
        List<IrrigationArea> irrigationAreas = generalService.getAllIrrigationAreaByRegion(region);
        return new ResponseEntity<>(irrigationAreas, HttpStatus.OK);
    }

    @GetMapping("/waternetpipe/byregion")
    @ApiOperation("通过region字段获取数据")
    public ResponseEntity<List<WaterNetPipe>> getWaterNetPipeByRegion(@RequestParam String region) {
        List<WaterNetPipe> waterNetPipes = generalService.getAllWaterNetPipeByRegion(region);
        return new ResponseEntity<>(waterNetPipes, HttpStatus.OK);
    }

    @GetMapping("/watersafety/byregion")
    @ApiOperation("通过region字段获取数据")
    public ResponseEntity<List<WaterSafety>> getWaterSafetyByRegion(@RequestParam String region) {
        List<WaterSafety> waterSafeties = generalService.getAllWaterSafetyByRegion(region);
        return new ResponseEntity<>(waterSafeties, HttpStatus.OK);
    }

    @GetMapping("/waterresources/byregion")
    @ApiOperation("通过region字段获取数据")
    public ResponseEntity<List<WaterResources>> getWaterResourcesByRegion(@RequestParam String region) {
        List<WaterResources> waterResources = generalService.getAllWaterResourcesByRegion(region);
        return new ResponseEntity<>(waterResources, HttpStatus.OK);
    }

    @GetMapping("/waterprojects/byregion")
    @ApiOperation("通过region字段获取数据")
    public ResponseEntity<List<WaterProjects>> getWaterProjectsByRegion(@RequestParam String region) {
        List<WaterProjects> waterProjects = generalService.getAllWaterProjectsByRegion(region);
        return new ResponseEntity<>(waterProjects, HttpStatus.OK);
    }

    @GetMapping("/healthevaluation/byregion")
    @ApiOperation("通过region字段获取数据")
    public ResponseEntity<List<HealthEvaluation>> getHealthEvaluationByRegion(@RequestParam String region) {
        List<HealthEvaluation> healthEvaluations = generalService.getAllHealthEvaluationByRegion(region);
        return new ResponseEntity<>(healthEvaluations, HttpStatus.OK);
    }
}
