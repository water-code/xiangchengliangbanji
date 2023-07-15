package xyz.ruankun.xiangchengliangbanji.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.ruankun.xiangchengliangbanji.entity.*;
import xyz.ruankun.xiangchengliangbanji.repository.*;

import java.util.List;
import java.util.Map;

@Service
public class GeneralService {
    @Autowired
    WaterNetPipeRepository waterNetPipeRepository;
    @Autowired
    SolarIrStationRepository solarIrStationRepository;
    @Autowired
    SandSiteRepository sandSiteRepository;
    @Autowired
    RiverSideBuildingRepository riverSideBuildingRepository;
    @Autowired
    ProposalReservoirRepository proposalReservoirRepository;
    @Autowired
    IrrigationAreaRepository irrigationAreaRepository;
    @Autowired
    WaterSafetyRepository waterSafetyRepository;
    @Autowired
    WaterProjectsRepository waterProjectsRepository;
    @Autowired
    WaterResourcesRepository waterResourcesRepository;
    @Autowired
    HealthEvaluationRepository healthEvaluationRepository;

    @Autowired
    DisasterPreventionPointRepository disasterPreventionPointRepository;

    //获取所有
    public List<WaterNetPipe> getAllWaterNetPipe(){
        return waterNetPipeRepository.findAll();
    }
    public List<SolarIrStation> getAllSolarIrStation(){
        return solarIrStationRepository.findAll();
    }
    public List<SandSite> getAllSandSite(){
        return sandSiteRepository.findAll();
    }
    public List<RiverSideBuilding> getAllRiverSideBuilding(){
        return riverSideBuildingRepository.findAll();
    }
    public List<ProposalReservoir> getAllProposalReservoir(){
        return proposalReservoirRepository.findAll();
    }
    public List<IrrigationArea> getAllIrrigationArea(){
        return irrigationAreaRepository.findAll();
    }

    public List<WaterSafety> getAllWaterSafety(){
        return waterSafetyRepository.findAll();
    }

    public List<WaterResources> getAllWaterResources(){
        return waterResourcesRepository.findAll();
    }

    public List<WaterProjects> getAllWaterProjects(){
        return waterProjectsRepository.findAll();
    }

    public List<HealthEvaluation> getAllHealthEvaluation(){
        return healthEvaluationRepository.findAll();
    }

    //通过river获取
    public List<WaterNetPipe> getAllWaterNetPipeByRiver(String river){
        return waterNetPipeRepository.findByRiverContaining(river);
    }

    public List<SolarIrStation> getAllSolarIrStationByRiver(String river){
        return solarIrStationRepository.findByRiverContaining(river);
    }

    public List<SandSite> getAllSandSiteByRiver(String river){
        return sandSiteRepository.findByRiverContaining(river);
    }
    public List<RiverSideBuilding> getAllRiverSideBuildingByRiver(String river){
        return riverSideBuildingRepository.findByRiverContaining(river);
    }
    public List<ProposalReservoir> getAllProposalReservoirByRiver(String river){
        return proposalReservoirRepository.findByRiverContaining(river);
    }
    public List<IrrigationArea> getAllIrrigationAreaByRiver(String river){
        return irrigationAreaRepository.findByRiverContaining(river);
    }

    public List<WaterSafety> getAllWaterSafetyByRiver(String river){
        return waterSafetyRepository.findAllByRiverContaining(river);
    }

    public List<WaterResources> getAllWaterResourcesByRiver(String river){
        return waterResourcesRepository.findAllByRiverContaining(river);
    }

    public List<WaterProjects> getAllWaterProjectsByRiver(String river){
        return waterProjectsRepository.findAllByRiverContaining(river);
    }

    public List<HealthEvaluation> getAllHealthEvaluationByRiver(String river){
        return healthEvaluationRepository.findAllByRiverContaining(river);
    }

    //通过region获取
    public List<WaterNetPipe> getAllWaterNetPipeByRegion(String region){
        return waterNetPipeRepository.findByRegionContaining(region);
    }
    public List<SolarIrStation> getAllSolarIrStationByRegion(String region){
        return solarIrStationRepository.findByRegionContaining(region);
    }
    public List<SandSite> getAllSandSiteByRegion(String region){
        return sandSiteRepository.findByRegionContaining(region);
    }
    public List<RiverSideBuilding> getAllRiverSideBuildingByRegion(String region){
        return riverSideBuildingRepository.findByRegionContaining(region);
    }
    public List<ProposalReservoir> getAllProposalReservoirByRegion(String region){
        return proposalReservoirRepository.findByRegionContaining(region);
    }
    public List<IrrigationArea> getAllIrrigationAreaByRegion(String region){
        return irrigationAreaRepository.findByRegionContaining(region);
    }

    public List<WaterSafety> getAllWaterSafetyByRegion(String region){
        return waterSafetyRepository.findAllByRegionContaining(region);
    }

    public List<WaterResources> getAllWaterResourcesByRegion(String region){
        return waterResourcesRepository.findAllByRegionContaining(region);
    }

    public List<WaterProjects> getAllWaterProjectsByRegion(String region){
        return waterProjectsRepository.findAllByRegionContaining(region);
    }

    public List<HealthEvaluation> getAllHealthEvaluationByRegion(String region){
        return healthEvaluationRepository.findAllByRegionContaining(region);
    }

    public List<DisasterPreventionPoint> getAllDisasterPreventionPointByAddress(String address) {
        return disasterPreventionPointRepository.findAllByAddressContaining(address);
    }

    public List<DisasterPreventionPoint> getAllDisasterPreventionPoint() {
        return disasterPreventionPointRepository.findAll();
    }
}
