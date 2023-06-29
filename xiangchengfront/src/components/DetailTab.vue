<template>
  <div class="detailTabContainer">
    <div class="propsTable">
      <el-descriptions :border="true" :column="1" size="large">
        <el-descriptions-item label="名称">{{ shuixiData.riverName }}</el-descriptions-item>
        <el-descriptions-item label="类型">{{ shuixiData.waterType }}</el-descriptions-item>
        <el-descriptions-item label="代码">{{ shuixiData.surveyCode }}</el-descriptions-item>
        <el-descriptions-item label="长度">{{ shuixiData.totalLength }}</el-descriptions-item>
        <el-descriptions-item label="起始位置">{{ shuixiData.startLocation }}</el-descriptions-item>
        <el-descriptions-item label="结束位置">{{ shuixiData.endLocation }}</el-descriptions-item>
        <el-descriptions-item label="级别">{{ shuixiData.riverLevel }}</el-descriptions-item>
        <el-descriptions-item label="河口流量">{{ shuixiData.estuaryFlow }}</el-descriptions-item>
      </el-descriptions>
    </div>
    <div class="charts">

    </div>
  </div>

</template>
<script>
import { reactive, toRefs, ref} from 'vue'
import {Search} from '@element-plus/icons-vue'
import axios from "../api/request.js";
import bus from "../utils/bus.js";
import GradientStackedAreaChart from "./GradientStackedAreaChart.vue";

export default {
  components: {GradientStackedAreaChart},
  data() {
    return {
    }
  },
  methods: {

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
      shuixiData:{}
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
  display: flex;
  flex-direction: row;
}
.propsTable{
  width: 40%;
}
.charts{
  width: 60%;
}
</style>