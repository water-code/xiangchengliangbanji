<template>
  <div class="navbar">
    <div class="navbar-left">
      <img src="/images/logo.png" alt="Avatar">
      <h1>乡城县水利数字沙盘</h1>
    </div>
    <div class="navbar-right">
      <img src="/images/avatar.jpg" alt="Avatar">
      <p>格桑梅朵</p>
    </div>
  </div>
  <div ref="mapViewNode" style="height: calc(100vh - 40px);">
    <div ref="basemapGalleryNode"></div>
    <div class="measure-tools-wrapper">
      <div class="measure-tools">
        <div class="measure-tools-icon"  @click="toggleDirectLine()"><el-icon color="rgb(110,110,110)" :size="20"><EditPen /></el-icon></div>
        <div class="measure-tools-icon"  @click="toggleArea()"><el-icon color="rgb(110,110,110)" :size="20"><Edit /></el-icon></div>
      </div>
    </div>
  </div>
  
</template>
<script>
import SceneView from "@arcgis/core/views/SceneView";
import WebScene from "@arcgis/core/WebScene";
import esriConfig from "@arcgis/core/config";
import Legend from "@arcgis/core/widgets/Legend";
import MapImageLayer from "@arcgis/core/layers/MapImageLayer";
import DirectLineMeasurement3D from '@arcgis/core/widgets/DirectLineMeasurement3D';
import AreaMeasurement3D from '@arcgis/core/widgets/AreaMeasurement3D';
import BasemapGallery  from '@arcgis/core/widgets/BasemapGallery';
import { ElNotification } from 'element-plus'
import { h } from 'vue'

export default {
  name: 'SceneView',
  data() {
    return {
      sceneView: null,
      directLineVisible: true,
      areaVisible: true
    };
  },
  created() {
    document.body.style.marginTop = '40px';
  },
  mounted() {
    this.initializeMap();
    
  },
  methods: {
    async initializeMap() {
      try {
        esriConfig.apiKey = "AAPKfcfab4769ecd4082a0983c91ddb91a10qY1wTLqICXuld4YQGysCEGlH46-8nmNBS517S_kHqDUwYvk9P02AdG8B_gtG2UcR";
        let map = new WebScene({
          portalItem: {
            id: "5a392557cffb485f8fe004e668e9edc0"
          }
        });
        // Create the SceneView
        let view = new SceneView({
          map: map,
          container: "app",
          center: [99.80, 29.1],
          zoom: 10,
          environment:{
            lighting:null
          },
          popup: {
            actions: [],
            dockEnabled: true,
            dockOptions: {
              buttonEnabled: true,
              breakpoint: false
            }
          }
        });
        //添加图例
        let legend = new Legend({
          view: view,  // 地图视图对象
          container: "legendDiv",  // 指定容器元素的 ID
          style: {
            backgroundColor: "white",
            borderColor: "black",
            fontFamily: "Arial, sans-serif",
            fontSize: "14px",
            fontWeight: "normal",
            opacity: 0.8
          }
        });
        // 将图例组件添加到地图中
        view.ui.add(legend, "bottom-right");
        //加载map service
        let apiUrl = import.meta.env.VITE_MAP_SERVER_URL;
        let layer = new MapImageLayer({
          url: apiUrl,
          outFields: ["*"],
          sublayers: [
            {
              id: 0,
              visible: true,
              autoCloseEnabled: true,
              popupTemplate: {
                content:(e)=>{
                  console.log(e.graphic.attributes)
                  this.$emit('setFid', e.graphic.attributes) 
                }
              }
            },{
              id: 1,
              visible: true,
              autoCloseEnabled: true,
              popupTemplate: {
                content:(e)=>{
                  console.log(attributes)
                  this.$emit('shorelinePlanningClick', e.graphic.attributes)
                }
              }
            },{
              id: 2,
              visible: true,
              autoCloseEnabled: true,
              popupTemplate: {
                content:(e)=>{
                  console.log(e)
                  this.$emit('shorelinePlanningClick', e.graphic.attributes)
                }
              }
            },{
              id: 3,
              visible: true
            },{
              id: 4,
              visible: true
            },{
              id: 5,
              visible: true
            },{
              id: 6,
              visible: true
            },{
              id: 7,
              visible: true
            },{
              id: 8
            },{
              id: 9,
              visible: true
            },{
              id: 10,
              visible: true
            }
          ]
        });
        map.add(layer);

        // 创建直线测量工具
        let directLineMeasurement = new DirectLineMeasurement3D({
          view: view,
          visible: this.directLineVisible,
          label:"距离测量"
        });
        // 创建面积测量工具
        let areaMeasurement = new AreaMeasurement3D({
          view: view,
          visible: this.areaVisible,
          label:"面积测量"
        });

        // 添加到视图中
        view.ui.add(directLineMeasurement, 'bottom-left');
        // 添加到视图中
        view.ui.add(areaMeasurement, 'bottom-left');

        let basemapGallery = new BasemapGallery({
          view: view
        });
        view.ui.add(basemapGallery, {
          position: "top-right"
        });

        // 将 SceneView 对象保存到组件的 data 中
        this.sceneView = view;

        // 触发 "map-ready" 事件
        this.$emit('map-ready', this.view);
      
        // 监听全局事件进行定位操作
        this.$eventBus.on('location',(data)=>{
          view.when(function() {
            view.goTo({center:[data[0][0], data[0][1]],zoom: data[1]})

          }).catch(function(err) {
            console.error("SceneView rejected:", err);
          });
        })
      } catch (error) {
        console.error('地图初始化失败：', error);
      }
    },
    toggleDirectLine() {
      this.directLineVisible = !this.directLineVisible;
      if (this.directLineVisible) {
        document.getElementsByClassName('esri-direct-line-measurement-3d')[0].style.display='block'
        ElNotification({
          title: '提示',
          message: h('i', { style: 'color: teal' }, "开启距离测量"),
          duration:1000
        })
        // document.getElementsByClassName('measure-tools-icon')[0].style.backgroundColor = 'rgba(199, 199, 199, 0.5)'
      }else {
        document.getElementsByClassName('esri-direct-line-measurement-3d')[0].style.display='none'
        ElNotification({
          title: '提示',
          message: h('i', { style: 'color: teal' }, "关闭距离测量"),
          duration:1000
        })
        // document.getElementsByClassName('measure-tools-icon')[0].style.backgroundColor = 'rgba(199, 199, 199, 0)'
      }
    },
    toggleArea() {
      this.areaVisible = !this.areaVisible;
      if (this.areaVisible) {
        document.getElementsByClassName('esri-area-measurement-3d')[0].style.display='block'
        ElNotification({
          title: '提示',
          message: h('i', { style: 'color: teal' }, "开启面积测量"),
          duration:1000
        })
        // document.getElementsByClassName('measure-tools-icon')[1].style.backgroundColor = 'rgba(199, 199, 199, 0.5)'
      }else {
        document.getElementsByClassName('esri-area-measurement-3d')[0].style.display='none'
        ElNotification({
          title: '提示',
          message: h('i', { style: 'color: teal' }, "关闭面积测量"),
          duration:1000
        })
        // document.getElementsByClassName('measure-tools-icon')[1].style.backgroundColor = 'rgba(199, 199, 199, 0)'
      }
    }
  }
};
</script>
<style>
/* 控制popupTemplate显示 */
.esri-popup__main-container{
  display: none !important;
}
/* *设置图例大小 */
.esri-legend {
  height: 200px;
}
.esri-ui-corner .esri-component.esri-widget--panel{
  width: 150px !important;
}
.esri-basemap-gallery {
  max-width: 100px !important;
  max-height: 130px !important;
}
.esri-direct-line-measurement-3d {
  display: none;
}
.esri-area-measurement-3d {
  display: none;
}
.measure-tools-wrapper {
  position: fixed;
  display: flex;
  flex-direction: row;
  left: 40px;
  top: 250px;
  transform: translateX(-50%);
}
.measure-tools {
  justify-content: center;
}
.measure-tools-icon{
  background-color: #ffffff;
  width: 32px;
  height: 32px;
  display: flex;
  align-items: center;
  justify-content: center;
  margin: 10px 0;
}

/* 设置导航 */
.navbar {
  background-color: #4f8ee1 !important;
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 40px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  z-index: 1; /* 设置比地图视图层级高 */
}
.navbar-left img{
  display: inline;
  width: 30px;
  height: 30px;
  border-radius: 50%;
  border: 0.5px solid darkgray;
  margin-right: 10px;
}

.navbar-left {
  margin-left: 20px;
  display: flex;
  align-items: center;
}

.navbar-left h1 {
  font-size: 24px;
  font-weight: bold;
  color: #ffffff;
  margin: 0;
}

.navbar-right {
  margin-right: 20px;
  display: flex;
  align-items: center;
}

.navbar-right img {
  width: 30px;
  height: 30px;
  border-radius: 50%;
  border: 0.5px solid darkgray;
  margin-right: 10px;
}

.navbar-right p {
  font-size: 14px;
  color: #ffffff;
  margin: 0;
}
.navbar-right p:hover{
  cursor: pointer;
  color: yellowgreen;
}
.navbar-right img:hover{
  cursor: pointer;
  width: 35px;
  height: 35px;
  transition-duration: 0.4s;
}

.measure-tools-icon:hover {
  cursor: pointer;
  background-color: rgb(243,243,243);
}
</style>