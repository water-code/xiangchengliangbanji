<template>
  <el-tabs v-model="activeName" class="search-tab">
    <el-tab-pane label="水系" name="shuiXi">
      <div class="input-size">
        <el-input v-model="shuixiInput" class="w-50 m-2" size="large" placeholder="请输入水系名称" :suffix-icon="Search" @input="shuixiInputChange"/>
      </div>
      <div class="data-static">
        干流3条，支流27条，湖泊7个
      </div>
      <div class="data-table">
        <el-table :data="shuixiTableData" style="width: 100%; background-color: rgba(255,255,255,0)" height="620" @cell-click="shuixiCellClick">
          <el-table-column prop="riverName" label="河流名称" />
          <el-table-column prop="waterType" label="干支流" />
        </el-table>
      </div>
    </el-tab-pane>
    <el-tab-pane label="护岸" name="huAn">huAn</el-tab-pane>
    <el-tab-pane label="堤防" name="diFang">diFang</el-tab-pane>
    <el-tab-pane label="水资源" name="shuiZiYuan">shuiZiYuan</el-tab-pane>
    <el-tab-pane label="灾害点" name="zaiHaiDian">zaiHaiDian</el-tab-pane>
    <el-tab-pane label="水电站" name="shuiDianZhan">shuiDianZhan</el-tab-pane>
    <el-tab-pane label="水文站" name="shuiWenZhan">shuiWenZhan</el-tab-pane>
    <el-tab-pane label="岸线规划" name="anXianGuiHua">anXianGuiHua</el-tab-pane>
    <el-tab-pane label="灌区" name="guanQu">guanQu</el-tab-pane>
    <el-tab-pane label="水库" name="shuiKu">shuiKu</el-tab-pane>
    <el-tab-pane label="河湖划界" name="heHuHuaJie">heHuHuaJie</el-tab-pane>
  </el-tabs>
</template>
<script>
import { reactive, toRefs, ref} from 'vue'
import { ElDialog, ElTabs, ElTabPane } from 'element-plus'
import {Search} from '@element-plus/icons-vue'
import axios from "../api/request.js";
import bus from "../utils/bus.js";

export default {
  components: { ElDialog, ElTabs, ElTabPane },
  data() {
    return {
      shuixiInput: "",
      shuixiTableData: []
    }
  },
  methods: {
    async shuixiInputChange(element){
      const riverInfoRes = await axios({ url: `/api/water-systems/by-keyword`, method: 'get' , data:{keyword:element}}) //水系基本信息
      console.log(riverInfoRes.data)
      this.shuixiTableData = riverInfoRes.data
    },
    async shuixiDataFetchAll(){
      const riverInfoRes = await axios({ url: `/api/water-systems`, method: 'get'}) //水系基本信息
      console.log(riverInfoRes.data)
      this.shuixiTableData = riverInfoRes.data
    },
    async shuixiCellClick(row){
      console.log(row)
      bus.emit('shuixiCellClick', row)
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
    //加载时候把所有数据获取一遍
    this.shuixiDataFetchAll()
  },
  setup(props) {
    const state = reactive({
      activeName: ref('shuiXi'),
    })
    return {
      ...toRefs(state),
    }

  }
}
</script>
<style>
.search-tab > .el-tabs__content {
  padding: 5px;
  color: #6b778c;
  font-size: 32px;
  font-weight: 600;
  height: 100%;
  max-height: 820px;
}
.search-tab {
  padding-top: 30px;
}
.data-static {
  border-radius: 10px;
  height: 100px;
  width: 100%;
  margin: 10px 0 10px 0;
}
.data-table {
  height: 900px;
  width: 100%;
}
.input-size {
  height: 10%;
}
.data-static {
  height: 20%;
}
.data-table {
  height: 70%;
}
</style>