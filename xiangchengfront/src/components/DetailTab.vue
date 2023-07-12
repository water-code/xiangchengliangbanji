<template>
  <div class="detailTabContainer">
    <el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick">
      <el-tab-pane class="basicInfo" label="基本信息" name="basic">
        <div class="propsTable">
          <el-descriptions :border="true" :column="2" size="large">
            <el-descriptions-item label="名称">{{ shuixiData.riverName }}</el-descriptions-item>
            <el-descriptions-item label="类型">{{ shuixiData.waterType }}</el-descriptions-item>
            <el-descriptions-item label="代码">{{ shuixiData.surveyCode }}</el-descriptions-item>
            <el-descriptions-item label="长度">{{ shuixiData.totalLength }}</el-descriptions-item>
            <el-descriptions-item label="起始位置">{{ shuixiData.startLocation }}</el-descriptions-item>
            <el-descriptions-item label="结束位置">{{ shuixiData.endLocation }}</el-descriptions-item>
            <el-descriptions-item label="级别">{{ shuixiData.riverLevel }}</el-descriptions-item>
            <el-descriptions-item label="河口流量">{{ shuixiData.estuaryFlow }}</el-descriptions-item>
            <el-descriptions-item label="流域总水量">{{ shuixiData.basinvol }}</el-descriptions-item>
            <el-descriptions-item label="径流">{{ shuixiData.runoff }}</el-descriptions-item>
            <el-descriptions-item label="已用水量">{{ shuixiData.consum }}</el-descriptions-item>
            <el-descriptions-item label="可用水量">{{ shuixiData.available }}</el-descriptions-item>
          </el-descriptions>
        </div>
        <div class="charts">
          <img class="charts-img" src="http://xiangoos.ruankun.xyz/%E5%81%A5%E5%BA%B7%E8%AF%84%E4%BB%B7.jpg">
        </div>
      </el-tab-pane>
      <el-tab-pane  class="basicInfo" label="水资源" name="resource">Config</el-tab-pane>
      <el-tab-pane  class="basicInfo" label="水安全" name="safe">Role</el-tab-pane>
      <el-tab-pane  class="basicInfo" label="水利工程" name="project">Task</el-tab-pane>
      <el-tab-pane  class="basicInfo" label="岸线规划" name="shoreline">Task</el-tab-pane>
      <el-tab-pane  class="basicInfo" label="河湖划界" name="bourdary">Task</el-tab-pane>
      <el-tab-pane  class="basicInfo" label="健康评价" name="health">Task</el-tab-pane>
      <el-tab-pane  class="basicInfo" label="灾害转移" name="disaster">Task</el-tab-pane>
    </el-tabs>
  </div>

</template>
<script>
import { reactive, toRefs, ref} from 'vue'
import {Search} from '@element-plus/icons-vue'
import bus from "../utils/bus.js";
import GradientStackedAreaChart from "./GradientStackedAreaChart.vue";

export default {
  components: {GradientStackedAreaChart},
  data() {
    return {
    }
  },
  methods: {
    handleClick(tab, event){
      console.log(tab, event)
    }
  },
  computed: {
    Search() {
      return Search
    }
  },
  props: {

  },
  mounted() {
  },
  setup(props) {
    const state = reactive({
      shuixiData:{},
      activeName:ref('basic')
    })
    bus.on('shuixiCellClick',(data)=>{
      console.log('我在detailTab里面', data)
      state.shuixiData = data
      console.log(state.shuixiData)
    })
    return {
      ...toRefs(state)
    }
  }
}
</script>
<style>
.detailTabContainer{
  height: 100%;
  width: 100%;
  margin: 0;
  padding: 0;
}
.propsTable {
  height: 340px;
}
.charts {
  height: 340px;
}
.charts-img {
  height: 100%;
}

.basicInfo {
  display: flex;
  flex-direction: row;
}
</style>