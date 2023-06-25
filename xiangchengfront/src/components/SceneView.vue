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
  </div>
  <div id="topbar">
    <button
        class="action-button esri-icon-measure-line"
        id="distanceButton"
        type="button"
        title="Measure distance between two points"
    ></button>
    <button
        class="action-button esri-icon-measure-area"
        id="areaButton"
        type="button"
        title="Measure area"
    ></button>
  </div>
  <div id="sketchPanel" class="esri-widget">
    <div id="startbuttons">
      <button id="point" data-type="point" class="esri-button starttool">
        兴趣点描绘
      </button>
      <button id="line" data-type="polyline" class="esri-button starttool">
        路线描绘
      </button>
      <button
          id="extrudedPolygon"
          data-type="polygon"
          class="esri-button starttool"
      >
        建筑物描绘
      </button>
      <button id="canoe" class="esri-button">电站模型加载</button>
    </div>
    <div id="actionbuttons">
      <button id="cancel" class="esri-button">取消</button>
      <button id="done" class="esri-button">完成</button>
    </div>
    <div id="edgeoperationbuttons">
      <br />选择边缘操作:
      <div class="update-options" id="edge">
        <button
            class="esri-widget--button edge-button"
            id="none-edge-button"
            value="none"
        >
          无
        </button>
        <button
            class="esri-widget--button edge-button edge-button-selected"
            id="split-edge-button"
            value="split"
        >
          分割
        </button>
        <button
            class="esri-widget--button edge-button"
            id="offset-edge-button"
            value="offset"
        >
          位移
        </button>
      </div>
      选择移动操作:
      <div class="update-options" id="shape">
        <button
            class="esri-widget--button shape-button"
            id="none-shape-button"
            value="none"
        >
          无
        </button>
        <button
            class="esri-widget--button shape-button shape-button-selected"
            id="move-shape-button"
            value="move"
        >
          移动
        </button>
      </div>
    </div>
  </div>
  <div id="configurationDiv" class="esri-widget">
    <table id="configurationTable">
      <tbody>
      <tr>
        <td>
          <label><b>提示/标签 </b></label>
        </td>
      </tr>
      <tr>
        <td>
          <label
              for="tooltipOptionscheckbox"
              id="tooltipOptionscheckboxlabel"
          >
            - 提示</label
          >
        </td>
        <td><input type="checkbox" id="tooltipOptionsheckbox" /></td>
      </tr>
      <tr>
        <td>
          <label for="labelOptionscheckbox" id="labelOptionscheckboxlabel">
            - 标签</label
          >
        </td>
        <td><input type="checkbox" id="labelOptionscheckbox" /></td>
      </tr>
      <tr>
        <td>
          <label for="enabledcheckbox" id="enabledcheckboxlabel"
          ><b
          >裁剪开启 (
            <div id="snappingctrlkey">CTRL-键</div>
            )</b
          ></label
          >
        </td>
        <td><input type="checkbox" id="enabledcheckbox" checked /></td>
      </tr>
      <tr>
        <td>
          <label for="selfsnappingcheckbox" id="selfsnappingcheckboxlabel">
            - 自动裁剪</label
          >
        </td>
        <td><input type="checkbox" id="selfsnappingcheckbox" /></td>
      </tr>
      <tr>
        <td>
          <label
              for="featuresnappingcheckbox"
              id="featuresnappingcheckboxlabel"
          >
            - 特征裁剪</label
          >
        </td>
        <td><input type="checkbox" id="featuresnappingcheckbox" /></td>
      </tr>
      </tbody>
    </table>
  </div>
  <div id="configurationInfoDiv" class="esri-widget">
    <b>打开3D绘画设置 <span>&#8594;</span></b>
  </div>
</template>
<script>
import SceneView from '@arcgis/core/views/SceneView'
import WebScene from '@arcgis/core/WebScene'

import Expand from '@arcgis/core/widgets/Expand'
import Weather from '@arcgis/core/widgets/Weather'
import Daylight from '@arcgis/core/widgets/Daylight'
import ElevationProfile from '@arcgis/core/widgets/ElevationProfile'

import esriConfig from '@arcgis/core/config'
import Legend from '@arcgis/core/widgets/Legend'

import BasemapGallery from '@arcgis/core/widgets/BasemapGallery'
import Camera from "@arcgis/core/Camera";
import SpatialReference from "@arcgis/core/geometry/SpatialReference";
import FeatureLayer from "@arcgis/core/layers/FeatureLayer";
import LayerList from "@arcgis/core/widgets/LayerList";

import Search from "@arcgis/core/widgets/Search";

import GraphicsLayer from '@arcgis/core/layers/GraphicsLayer'
import Point from '@arcgis/core/geometry/Point'
import Graphic from '@arcgis/core/Graphic'
import SimpleMarkerSymbol from '@arcgis/core/symbols/SimpleMarkerSymbol'
import { ElNotification } from 'element-plus'
import { h } from 'vue'
import { Check, Close } from '@element-plus/icons-vue'
import bus from '../utils/bus.js'
import SearchView from './SearchView.vue'
import Sketch from "@arcgis/core/widgets/Sketch.js";
import DirectLineMeasurement3D from "@arcgis/core/widgets/DirectLineMeasurement3D";
import AreaMeasurement3D from "@arcgis/core/widgets/AreaMeasurement3D";
import SketchViewModel from "@arcgis/core/widgets/Sketch/SketchViewModel.js";



export default {
  name: 'SceneView',
  components: { SearchView },
  data() {
    return {
      sceneView: null,
      directLineVisible: false,
      areaVisible: false, //一开始的面积测量和距离测量是关闭状态???
      FigureLayerVisible: false, //一开始的TF选项是隐藏的，只有点击后才是可见的
      FigureFigureLayerFont: ['水系', '定曲岸线规划', '许曲岸线规划', '水电站', '水文站', '县（区)界', '乡（镇）界', '晕线1', '晕线2', '乡镇面 (10)', '乡城县农田灌溉面积(水资源)'],
      FigureLayerInsideVisible: [true, false, false, true, true, false, false, false, false, false, false],
      Check,
      Close
    }
  },
  created() {
    document.body.style.marginTop = '40px'
  },
  mounted() {
    this.initializeMap()
  },
  methods: {
    async initializeMap() {
      try {
        esriConfig.apiKey = 'AAPKfcfab4769ecd4082a0983c91ddb91a10qY1wTLqICXuld4YQGysCEGlH46-8nmNBS517S_kHqDUwYvk9P02AdG8B_gtG2UcR'
        let map = new WebScene({
          portalItem: {
            id: '5a392557cffb485f8fe004e668e9edc0'
          }
        })
        let camera = new Camera({
          position: new Point({
            x: 99.82975225369145,
            y: 28.802280667091217,
            z: 3613.927701551467,
            spatialReference: new SpatialReference({ wkid: 4326 })
          }),
          heading: 1.962013105645539,
          tilt: 84.3710848755085
        });
        // Create the SceneView
        const view = new SceneView({
          map: map,
          container: 'app',
          qualityProfile: "high",
          camera:camera,
          environment: {
            weather: {
              type: "cloudy",
              cloudCover: 0.3
            }
          },
          zoom: 10
        })

        //加载map service
        let apiUrl = import.meta.env.VITE_MAP_SERVER_URL
        let layer0 = new FeatureLayer({
          url:apiUrl + "/0",
          outFields:['*'],
          visible:true
        })
        let layer1 = new FeatureLayer({
          url:apiUrl + "/1",
          outFields:['*'],
          visible:true
        })
        let layer2 = new FeatureLayer({
          url:apiUrl + "/2",
          outFields:['*'],
          visible:true
        })
        let layer3 = new FeatureLayer({
          url:apiUrl + "/3",
          outFields:['*'],
          visible:true
        })
        let layer4 = new FeatureLayer({
          url:apiUrl + "/4",
          outFields:['*'],
          visible:true
        })
        let layer5 = new FeatureLayer({
          url:apiUrl + "/5",
          outFields:['*'],
          visible:true
        })
        let layer6 = new FeatureLayer({
          url:apiUrl + "/6",
          outFields:['*'],
          visible:false
        })
        let layer7 = new FeatureLayer({
          url:apiUrl + "/7",
          outFields:['*'],
          visible:false
        })
        let layer8 = new FeatureLayer({
          url:apiUrl + "/8",
          outFields:['*'],
          visible:true
        })
        let layer9 = new FeatureLayer({
          url:apiUrl + "/9",
          outFields:['*'],
          visible:false
        })
        let layer10 = new FeatureLayer({
          url:apiUrl + "/10",
          outFields:['*'],
          visible:false
        })
        let layer11 = new FeatureLayer({
          url:apiUrl + "/11",
          outFields:['*'],
          visible:false
        })
        let layer12 = new FeatureLayer({
          url:apiUrl + "/12",
          outFields:['*'],
          visible:false
        })
        let layer13 = new FeatureLayer({
          url:apiUrl + "/13",
          outFields:['*'],
          visible:false
        })
        let layer14 = new FeatureLayer({
          url:apiUrl + "/14",
          outFields:['*'],
          visible:false
        })
        let layer15 = new FeatureLayer({
          url:apiUrl + "/15",
          outFields:['*'],
          visible:false
        })

        //用于作图的图层
        const graphicsLayer = new GraphicsLayer();
        map.add(graphicsLayer)
        map.add(layer15)
        map.add(layer14)
        map.add(layer13)
        map.add(layer12)
        map.add(layer11)
        map.add(layer10)
        map.add(layer9)
        map.add(layer8)
        map.add(layer7)
        map.add(layer6)
        map.add(layer5)
        map.add(layer4)
        map.add(layer3)
        map.add(layer2)
        map.add(layer1)
        map.add(layer0)

        let weatherExpand = new Expand({
          view: view,
          content: new Weather({
            view: view
          }),
          group: "top-right",
          expanded: false
        });

        let daylightExpand = new Expand({
          view: view,
          content: new Daylight({
            view: view
          }),
          group: "top-right"
        });

        let elevationProfileExpand = new Expand({
          view:view,
          content:new ElevationProfile({
            view: view,
            profiles: [
              {type: "ground"},
              {type: "view"}
            ],
            visibleElements: {
              selectButton: true
            }
          }),
          group: "top-right",
        })

        let basemapGalleryExpand = new Expand({
          view:view,
          content:new BasemapGallery({
            view: view
          }),
          group: "top-right"
        })
        const legendExpand = new Expand({
          content: new Legend({
            view: view,
            layerInfos: [
              {
                layer: layer0,
                title: "护岸"
              },{
                layer: layer1,
                title: "堤防"
              },{
                layer: layer2,
                title: "水资源"
              },{
                layer: layer3,
                title: "地质灾害点"
              },{
                layer: layer4,
                title: "水电站 "
              },{
                layer: layer5,
                title: "水文站"
              },{
                layer: layer6,
                title: "岸线规划"
              },{
                layer: layer7,
                title: "岸线规划"
              },{
                layer: layer8,
                title: "水系"
              },{
                layer: layer9,
                title: "县（区）界 "
              },{
                layer: layer10,
                title: "乡（镇）界"
              },{
                layer: layer15,
                title: "河湖划界"
              }
            ]
          }),
          view: view,
          expanded: false
        });
        const dizhizaihai = new FeatureLayer({
          url: "https://services8.arcgis.com/zjhyM0J5lo3dhx9k/arcgis/rest/services/xiangchengliangbanji/FeatureServer/3",
          popupTemplate: {
            title: "地质灾害点: {town}{county}{goudaoming}{name} </br>{type}, ({suggestion})",
            overwriteActions: true
          }
        });
        const shuidianzhan = new FeatureLayer({
          url: "https://services8.arcgis.com/zjhyM0J5lo3dhx9k/arcgis/rest/services/xiangchengliangbanji/FeatureServer/4",
          popupTemplate: {
            title: "水电站: {name}",
            overwriteActions: true
          }
        });
        const shuiwenzhan = new FeatureLayer({
          url: "https://services8.arcgis.com/zjhyM0J5lo3dhx9k/arcgis/rest/services/xiangchengliangbanji/FeatureServer/5",
          popupTemplate: {
            title: "水文站: {stnm}",
            overwriteActions: true
          }
        });
        const shuixi = new FeatureLayer({
          url: "https://services8.arcgis.com/zjhyM0J5lo3dhx9k/arcgis/rest/services/xiangchengliangbanji/FeatureServer/8",
          popupTemplate: {
            title: "水系: {rname}",
            overwriteActions: true
          }
        });

        let searchExpand = new Expand({
          view:view,
          content:new Search({
            view: view,
            allPlaceholder: "下拉选取搜索目标",
            includeDefaultSources: false,
            sources: [
              {
                layer: shuidianzhan,
                searchFields: ["name"],
                displayField: "name",
                exactMatch: false,
                outFields: ["*"],
                name: "水电站",
                placeholder: "水电站名称"
              },{
                layer: shuiwenzhan,
                searchFields: ["stnm"],
                displayField: "stnm",
                exactMatch: false,
                outFields: ["*"],
                name: "水文站",
                placeholder: "水文站名称"
              },{
                layer: shuixi,
                searchFields: ["rname"],
                displayField: "rname",
                exactMatch: false,
                outFields: ["*"],
                name: "水系",
                placeholder: "水系名称"
              },{
                layer: dizhizaihai,
                searchFields: ["town", "county", "name", "code", "type", "people", "goudaoming", "danger", "tip"],
                displayField: "name",
                exactMatch: false,
                outFields: ["*"],
                name: "地质灾害点位",
                placeholder: "键入地质灾害相关内容"
              }
            ]
          }),
          group: "top-right"
        })

        let layerListExpand = new Expand({
          view:view,
          content: new LayerList({view: view}),
          group: "top-right"
        })

        let sketchExpand = new Expand({
          view:view,
          content: new Sketch({
            view,
            layer: graphicsLayer,
            creationMode: "update"
          }),
          group: "top-right"
        })
        view.ui.add([legendExpand], "bottom-left");
        view.ui.add([weatherExpand, daylightExpand, elevationProfileExpand, basemapGalleryExpand, searchExpand, layerListExpand, sketchExpand], "top-right");
        view.ui.add("topbar", "top-right");


        //3D绘画技术
        const extrudedPolygon = {
          type: "polygon-3d",
          symbolLayers: [
            {
              type: "extrude",
              size: 10, // extrude by 10 meters
              material: {
                color: "white"
              },
              edges: {
                type: "solid",
                size: "3px",
                color: "blue"
              }
            }
          ]
        };
        // polyline symbol used for sketching routes
        const route = {
          type: "line-3d",
          symbolLayers: [
            {
              type: "line",
              size: "10px",
              material: {
                color: "white"
              }
            },
            {
              type: "line",
              size: "3px",
              material: {
                color: "blue"
              }
            }
          ]
        };

        // point symbol used for sketching points of interest
        const point = {
          type: "point-3d",
          symbolLayers: [
            {
              type: "icon",
              size: "30px",
              resource: { primitive: "kite" },
              outline: {
                color: "blue",
                size: "3px"
              },
              material: {
                color: "white"
              }
            }
          ]
        };
        // Set-up event handlers for buttons and click events
        const enabledcheckbox = document.getElementById("enabledcheckbox");
        const startbuttons = document.getElementById("startbuttons");
        const actionbuttons = document.getElementById("actionbuttons");
        const edgeoperationbuttons = document.getElementById(
            "edgeoperationbuttons"
        );
        const tooltipOptionsheckbox = document.getElementById(
            "tooltipOptionsheckbox"
        );
        const configurationInfoDiv = document.getElementById(
            "configurationInfoDiv"
        );
        const labelOptionscheckbox = document.getElementById(
            "labelOptionscheckbox"
        );
        const selfsnappingcheckbox = document.getElementById(
            "selfsnappingcheckbox"
        );
        const snappingctrlkey = document.getElementById("snappingctrlkey");
        const featuresnappingcheckbox = document.getElementById(
            "featuresnappingcheckbox"
        );
        // load the default value from the snapping checkbox
        let snappingcheckboxsavedstate = enabledcheckbox.checked ? true : false;

        // define the SketchViewModel and pass in the symbols for each geometry type
        // set the snappingOptions.selfEnabled to the default state
        const sketchViewModel = new SketchViewModel({
          layer: graphicsLayer,
          view: view,
          pointSymbol: point,
          polygonSymbol: extrudedPolygon,
          polylineSymbol: route,
          snappingOptions: {
            enabled: snappingcheckboxsavedstate,
            featureSources: [{ layer: graphicsLayer }]
          },
          tooltipOptions: { enabled: true },
          labelOptions: { enabled: true },
          defaultUpdateOptions: {
            tool: "reshape"
          }
        });
        // after drawing the geometry, enter the update mode to update the geometry
        // and the deactivate the buttons
        sketchViewModel.on("create", (event) => {
          if (event.state === "complete") {
            startbuttons.style.display = "inline";
            actionbuttons.style.display = "none";
            sketchViewModel.update(event.graphic);
          }
          if (event.state === "cancel") {
            startbuttons.style.display = "inline";
            actionbuttons.style.display = "none";
          }
        });

        sketchViewModel.on("update", (event) => {
          if (event.state === "start") {
            startbuttons.style.display = "none";
            actionbuttons.style.display = "inline";
            if (
                event.graphics[0].geometry.type === "polygon" ||
                event.graphics[0].geometry.type === "polyline"
            ) {
              edgeoperationbuttons.style.display = "inline";
            }
          }
          if (event.state === "complete") {
            startbuttons.style.display = "inline";
            actionbuttons.style.display = "none";
            edgeoperationbuttons.style.display = "none";
          }
        });

        /**********************************************
         * Drawing UI with configuration
         *********************************************/

        const drawButtons = Array.prototype.slice.call(
            document.getElementsByClassName("starttool")
        );
        const cancelBtn = document.getElementById("cancel");
        const doneBtn = document.getElementById("done");

        // set event listeners to activate sketching graphics
        drawButtons.forEach((btn) => {
          btn.addEventListener("click", (event) => {
            // to activate sketching the create method is called passing in the geometry type
            // from the data-type attribute of the html element
            sketchViewModel.create(event.target.getAttribute("data-type"));
            startbuttons.style.display = "none";
            actionbuttons.style.display = "inline";
          });
        });

        cancelBtn.addEventListener("click", (event) => {
          sketchViewModel.cancel();
        });
        doneBtn.addEventListener("click", (event) => {
          if (sketchViewModel.updateGraphics.length !== 0) {
            sketchViewModel.complete();
          } else {
            sketchViewModel.cancel();
          }
        });

        view.ui.add("sketchPanel", "top-right");

        // default values for edge/move operations
        let edgeType = "split";
        let shapeType = "move";

        // Handling the configuration for edge operation
        const noneEdgeBtn = document.getElementById("none-edge-button");
        const splitEdgeBtn = document.getElementById("split-edge-button");
        const offsetEdgeBtn = document.getElementById("offset-edge-button");
        noneEdgeBtn.onclick = edgeChangedClickHandler;
        splitEdgeBtn.onclick = edgeChangedClickHandler;
        offsetEdgeBtn.onclick = edgeChangedClickHandler;

        function edgeChangedClickHandler(event) {
          edgeType = event.target.value;

          // handle the buttons
          const buttons = document.getElementsByClassName("edge-button");
          for (const button of buttons) {
            button.classList.remove("edge-button-selected");
          }
          this.classList.add("edge-button-selected");
          restartUpdateMode({
            reshapeOptions: {
              edgeOperation: edgeType,
              shapeOperation: shapeType
            }
          });
        }

        // Handling the configuration for move operation
        const noneShapeButton = document.getElementById("none-shape-button");
        const moveShapeButton = document.getElementById("move-shape-button");
        noneShapeButton.onclick = shapeChangedClickHandler;
        moveShapeButton.onclick = shapeChangedClickHandler;

        function shapeChangedClickHandler(event) {
          shapeType = event.target.value;

          // handle the buttons
          const buttons = document.getElementsByClassName("shape-button");
          for (const button of buttons) {
            button.classList.remove("shape-button-selected");
          }
          this.classList.add("shape-button-selected");
          restartUpdateMode({
            reshapeOptions: {
              edgeOperation: edgeType,
              shapeOperation: shapeType
            }
          });
        }

        function restartUpdateMode(updateOptions) {
          sketchViewModel.defaultUpdateOptions = {
            ...sketchViewModel.defaultUpdateOptions,
            ...updateOptions
          };

          if (sketchViewModel.activeTool) {
            if (
                sketchViewModel.activeTool === "transform" ||
                sketchViewModel.activeTool === "move" ||
                sketchViewModel.activeTool === "reshape"
            ) {
              updateOptions.tool = sketchViewModel.activeTool;
              sketchViewModel.update(
                  sketchViewModel.updateGraphics.toArray(),
                  updateOptions
              );
            }
          }
        }

        /**********************************************
         * Configuration UI for snapping
         *********************************************/

        sketchViewModel.watch("snappingOptions.enabled", (newValue) => {
          enabledcheckbox.checked = newValue;
        });

        enabledcheckbox.checked = sketchViewModel.snappingOptions.enabled;
        enabledcheckbox.addEventListener("change", (event) => {
          sketchViewModel.snappingOptions.enabled = event.target.checked
              ? true
              : false;
        });

        tooltipOptionsheckbox.checked = sketchViewModel.tooltipOptions.enabled;
        tooltipOptionsheckbox.addEventListener("change", (event) => {
          sketchViewModel.tooltipOptions.enabled = event.target.checked
              ? true
              : false;
        });

        labelOptionscheckbox.checked = sketchViewModel.labelOptions.enabled;
        labelOptionscheckbox.addEventListener("change", (event) => {
          sketchViewModel.labelOptions.enabled = event.target.checked
              ? true
              : false;
        });

        selfsnappingcheckbox.checked =
            sketchViewModel.snappingOptions.selfEnabled;
        selfsnappingcheckbox.addEventListener("change", (event) => {
          sketchViewModel.snappingOptions.selfEnabled = event.target.checked
              ? true
              : false;
        });

        featuresnappingcheckbox.checked =
            sketchViewModel.snappingOptions.featureEnabled;
        featuresnappingcheckbox.addEventListener("change", (event) => {
          sketchViewModel.snappingOptions.featureEnabled = event.target.checked
              ? true
              : false;
        });

        const configurationExpand = new Expand({
          expandIcon: "gear",
          expandTooltip: "Show configuration",
          expanded: false,
          view: view,
          content: document.getElementById("configurationDiv")
        });

        // observe the if the CTRL-key got pressed to give the user a visual feedback
        // the logic itself for toggling snapping is in the SketchViewModel
        view.on(["key-down"], (ev) => {
          if (ev.key === "Control") {
            snappingctrlkey.style.fontWeight = "bold";
            snappingctrlkey.style.color = "royalblue";
          }
        });
        view.on(["key-up"], (ev) => {
          if (ev.key === "Control") {
            snappingctrlkey.style.fontWeight = "normal";
            snappingctrlkey.style.color = "black";
          }
        });

        view.ui.add(configurationExpand, "bottom-right");

        configurationInfoDiv.addEventListener("click", (event) => {
          configurationExpand.expand();
        });
        view.ui.add("configurationInfoDiv", "bottom-right");








        let activeWidget = null;
        document
            .getElementById("distanceButton")
            .addEventListener("click", (event) => {
              setActiveWidget(null);
              if (!event.target.classList.contains("active")) {
                setActiveWidget("distance");
              } else {
                setActiveButton(null);
              }
            });

        document
            .getElementById("areaButton")
            .addEventListener("click", (event) => {
              setActiveWidget(null);
              if (!event.target.classList.contains("active")) {
                setActiveWidget("area");
              } else {
                setActiveButton(null);
              }
            });

        function setActiveWidget(type) {
          switch (type) {
            case "distance":
              activeWidget = new DirectLineMeasurement3D({
                view: view
              });

              // skip the initial 'new measurement' button
              activeWidget.viewModel.start();

              view.ui.add(activeWidget, "top-right");
              setActiveButton(document.getElementById("distanceButton"));
              break;
            case "area":
              activeWidget = new AreaMeasurement3D({
                view: view
              });

              // skip the initial 'new measurement' button
              activeWidget.viewModel.start();

              view.ui.add(activeWidget, "top-right");
              setActiveButton(document.getElementById("areaButton"));
              break;
            case null:
              if (activeWidget) {
                view.ui.remove(activeWidget);
                activeWidget.destroy();
                activeWidget = null;
              }
              break;
          }
        }

        function setActiveButton(selectedButton) {
          // focus the view to activate keyboard shortcuts for sketching
          view.focus();
          const elements = document.getElementsByClassName("active");
          for (let i = 0; i < elements.length; i++) {
            elements[i].classList.remove("active");
          }
          if (selectedButton) {
            selectedButton.classList.add("active");
          }
        }

        //防止glb模型的代码
        const canoeBtn = document.getElementById("canoe");
        view.when(() => {
              // This sample uses the SketchViewModel to add points to a
              // GraphicsLayer. The points have 3D glTF models as symbols.
              const sketchVM = new SketchViewModel({
                layer: graphicsLayer,
                view: view
              });
              canoeBtn.addEventListener("click", () => {
                // reference the relative path to the glTF model
                // in the resource of an ObjectSymbol3DLayer
                sketchVM.pointSymbol = {
                  type: "point-3d",
                  symbolLayers: [
                    {
                      type: "object",
                      resource: {
                        href: "/glb/sjg.glb"
                      }
                    }
                  ]
                };
                deactivateButtons();
                sketchVM.create("point");
                canoeBtn.classList.add("esri-button--secondary");
              });

              sketchVM.on("create", (event) => {
                if (event.state === "complete") {
                  sketchVM.update(event.graphic);
                  deactivateButtons();
                }
              });
            })
            .catch(console.error);

        function deactivateButtons() {
          const elements = Array.prototype.slice.call(
              document.getElementsByClassName("esri-button")
          );
          elements.forEach((element) => {
            element.classList.remove("esri-button--secondary");
          });
        }







        // 将 SceneView 对象保存到组件的 data 中
        this.sceneView = view
        // 触发 "map-ready" 事件
        this.$emit('map-ready', this.view)
        // 定义变量保存上一次添加的闪烁图标
        let lastGraphic = null
        //将对象添加进入window 调试使用
        window.FigureLayerInsideVisible = this.FigureLayerInsideVisible
        window.view = view
        // 监听全局事件进行定位操作
        // 监听positioning事件实现对应河流的定位
        bus.on('location', data => {
          //当监听到location定位的时候,触发closePop事件关闭弹窗
          bus.emit('closePop', false)
          // 添加闪烁效果
          let gLayer = new GraphicsLayer()
          map.add(gLayer)
          let point = new Point(data[0][0], data[0][1])
          const markerSymbol = new SimpleMarkerSymbol({
            color: [255, 0, 0],
            outline: {
              color: [255, 255, 255],
              width: 2
            },
            size: 10
          })
          const graphic = new Graphic({
            geometry: point,
            symbol: markerSymbol
          })

          // 移除上一次添加的闪烁图标
          if (lastGraphic) {
            view.graphics.remove(lastGraphic)
          }

          // 将图形添加到地图视图中
          view.graphics.add(graphic)
          // 保存当前添加的闪烁图标
          lastGraphic = graphic
          // 创建闪烁效果
          let flashInterval = setInterval(() => {
            graphic.visible = !graphic.visible
          }, 500)

          // 停止闪烁效果
          setTimeout(() => {
            clearInterval(flashInterval)
            graphic.visible = false
          }, 10000)
        })
      } catch (error) {
        console.error('地图初始化失败：', error)
      }
    },
    toggleDirectLine() {
      this.directLineVisible = !this.directLineVisible
      if (this.directLineVisible) {
        document.getElementsByClassName('esri-direct-line-measurement-3d')[0].style.display = 'block'
        ElNotification({
          title: '提示',
          message: h('i', { style: 'color: teal' }, '开启距离测量'),
          duration: 1000
        })
      } else {
        document.getElementsByClassName('esri-direct-line-measurement-3d')[0].style.display = 'none'
        ElNotification({
          title: '提示',
          message: h('i', { style: 'color: teal' }, '关闭距离测量'),
          duration: 1000
        })
      }
    },
    toggleArea() {
      this.areaVisible = !this.areaVisible
      if (this.areaVisible) {
        document.getElementsByClassName('esri-area-measurement-3d')[0].style.display = 'block'
        ElNotification({
          title: '提示',
          message: h('i', { style: 'color: teal' }, '开启面积测量'),
          duration: 1000
        })
      } else {
        document.getElementsByClassName('esri-area-measurement-3d')[0].style.display = 'none'
        ElNotification({
          title: '提示',
          message: h('i', { style: 'color: teal' }, '关闭面积测量'),
          duration: 1000
        })
      }
    },
    //设置点击函数
    showFigureLayer() {
      this.FigureLayerVisible = !this.FigureLayerVisible
      // console.log(this);
      // console.log(this.initializeMap());
      console.log(this.FigureLayerVisible)
      // console.log(layer2.sublayers._items[0].visible);
    }
  },
  watch: {
    FigureLayerInsideVisible: {
      // immediate:true,
      deep: true,
      handler() {
        for(var i=0; i<FigureLayerInsideVisible.length; i++){
          //view.map.layers.items[0].allSublayers.items[i].visible 有没有其它办法调用这个变量
          view.map.layers.items[0].allSublayers.items[i].visible = FigureLayerInsideVisible[i]
        }
      }
    }
  }
}
</script>
<style>
/* *{
  margin: 0px;
} */
body {
  margin-bottom: 0px;
}
/* 控制popupTemplate显示 */
.esri-popup__main-container {
  display: none !important;
}
.esri-ui-corner .esri-component.esri-widget--panel {
  width: 150px !important;
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
.measure-tools-icon {
  background-color: #ffffff;
  width: 32px;
  height: 32px;
  display: flex;
  align-items: center;
  justify-content: center;
  margin: 10px 0;
}
/*图层按钮的样式*/
.map-FigureLayer {
  position: fixed;
  width: 60px;
  height: 40px;
  right: 22px;
  top: 200px;
  background-color: #ffffff;
  text-align: center;
  font-size: 1em;
  border-radius: 10px;
  border: 2px solid skyblue;
  /* box-shadow:  1px 1px 3px 3px black; */
}
.map-FigureLayer p {
  margin: 0 auto;
  line-height: 40px;
}
.map-FigureLayer-Font :hover {
  cursor: pointer;
  user-select: none;
}

.map-FigureLayer-TF {
  position: fixed;
  right: 22px;
  top: 260px;
  background-color: #4f8ee1;
  border-radius: 10px;
  height: 400px;
  color: white;
  font-weight: bold;
  width: 225px;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: stretch;
  overflow-y: scroll;
  opacity: 0.88;
  user-select: none;
}
.map-FigureLayer-TF p {
  width: 180px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.map-FigureLayer-TFdiv {
  display: flex;
}
.map-FigureLayer-TFdiv > div {
  line-height: 40px;
  flex-wrap: wrap;
  right: 20px;
  height: 40px;
  align-items: center;
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
.navbar-left img {
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
.navbar-right p:hover {
  cursor: pointer;
  color: yellowgreen;
}
.navbar-right img:hover {
  cursor: pointer;
  width: 35px;
  height: 35px;
  transition-duration: 0.4s;
}

.measure-tools-icon:hover {
  cursor: pointer;
  background-color: rgb(243, 243, 243);
}
/* 搜索功能 */
.searchTab {
  position: absolute;
  left: 80px;
  top: 55px;
}

/*测量工具*/
#topbar {
  background: #fff;
  padding: 10px;
}

.action-button {
  font-size: 16px;
  background-color: transparent;
  border: 1px solid #d3d3d3;
  color: #6e6e6e;
  height: 32px;
  width: 32px;
  text-align: center;
  box-shadow: 0 0 1px rgba(0, 0, 0, 0.3);
}

.action-button:hover,
.action-button:focus {
  background: #0079c1;
  color: #e4e4e4;
}

.active {
  background: #0079c1;
  color: #e4e4e4;
}

/*3D绘画工具*/
#sketchPanel {
  width: 200px;
  padding: 10px;
  background-color: rgba(255, 255, 255, 0.8);
}

.esri-button {
  margin: 2px;
}

#configurationDiv {
  padding: 10px;
  text-align: left;
  width: 250px;
}

#configurationInfoDiv {
  padding: 7px;
  text-align: left;
}

#snappingctrlkey {
  display: inline;
}

#actionbuttons,
#edgeoperationbuttons {
  display: none;
}

.update-options {
  display: flex;
  flex-direction: row;
}

.edge-button,
.shape-button {
  flex: 1;
  border-style: solid;
  border-width: 1px;
  border-image: none;
}

.edge-button-selected,
.shape-button-selected {
  background: #4c4c4c;
  color: #fff;
}

</style>
