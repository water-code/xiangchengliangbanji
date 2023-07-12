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
        <el-table :data="shuixiTableData" style="width: 100%; background-color: rgba(255,255,255,0)" height="620" row-key="id" border @cell-click="shuixiCellClick">
          <el-table-column prop="riverName" label="河流名称" />
        </el-table>
      </div>
    </el-tab-pane>
    <el-tab-pane label="水系相关资源" name="safe">
      <p style="font-size: 16px">1.选择水利数据类型</p>
      <el-select v-model="typevalue" class="m-2" placeholder="选择水利数据类型" size="large">
        <el-option
            v-for="item in typeOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value"
        />
      </el-select>
      <p style="font-size: 16px">2.选择行政区域并输入关键词</p>
      <div class="input-size">
        <div class="mt-4">
          <el-input v-model="input3" placeholder="搜索空即为搜索所有" class="input-with-select" size="large">
            <template #prepend>
              <el-select v-model="select" placeholder="行政区" style="width: 115px">
                <el-option label="尼斯乡" value="尼斯乡" />
                <el-option label="青德乡" value="青德乡" />
                <el-option label="正斗乡" value="正斗乡" />
                <el-option label="香巴拉镇" value="香巴拉镇" />
                <el-option label="定波乡" value="定波乡" />
                <el-option label="水洼乡" value="水洼乡" />
                <el-option label="沙贡乡" value="沙贡乡" />
                <el-option label="洞松乡" value="洞松乡" />
                <el-option label="然乌乡" value="然乌乡" />
                <el-option label="热打乡" value="热打乡" />
                <el-option label="青麦乡" value="青麦乡" />
                <el-option label="白依乡" value="白依乡" />
              </el-select>
            </template>
            <template #append>
              <el-button :icon="Search" />
            </template>
          </el-input>
        </div>
      </div>
    </el-tab-pane>
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
      shuixiTableData: [],
      initialData:[
        {
          "id": 27,
          "riverName": "许曲",
          "children": [
            {
              "id": 17,
              "riverName": "色坝沟"
            },{
              "id": 10,
              "riverName": "过绒沟"
            },{
              "id": 7,
              "riverName": "马熊沟"
            },{
              "id": 20,
              "riverName": "日翁卓沟"
            },{
              "id": 23,
              "riverName": "百另八条沟"
            },{
              "id": 9,
              "riverName": "浪充拥沟"
            },{
              "id": 21,
              "riverName": "热勒拥沟"
            },{
              "id": 11,
              "riverName": "冷龙沟"
            },{
              "id": 22,
              "riverName": "登尕冈休沟"
            },{
              "id": 0,
              "riverName": "仁堆沟"
            },{
              "id": 26,
              "riverName": "然乌沟"
            },{
              "id": 19,
              "riverName": "青达沟"
            },{
              "id": 13,
              "riverName": "牛龙沟"
            }
          ]
        },{
          "id": 29,
          "riverName": "定曲",
          "children": [
            {
              "id": 15,
              "riverName": "结绒溪右支河"
            },{
              "id": 25,
              "riverName": "结绒溪"
            },{
              "id": 8,
              "riverName": "曾斗溪"
            },{
              "id": 14,
              "riverName": "麻拉沟"
            }
          ]
        },{
          "id": 28,
          "riverName": "玛曲",
          "children": [
            {
              "id": 3,
              "riverName": "弄仰溪"
            },{
              "id": 1,
              "riverName": "扎衣溪"
            },{
              "id": 24,
              "riverName": "毕斗溪"
            },{
              "id": 2,
              "riverName": "水浇沟"
            },{
              "id": 12,
              "riverName": "松协通沟"
            },{
              "id": 16,
              "riverName": "莫让溪"
            },{
              "id": 18,
              "riverName": "纳力拥沟"
            },{
              "id": 4,
              "riverName": "曲刀拥沟"
            },{
              "id": 5,
              "riverName": "布吉沟"
            },{
              "id": 6,
              "riverName": "清多沟"
            }
          ]
        },{
          "id": -1,
          "riverName": "湖泊",
          "children": [
            {
              "id": 30,
              "riverName": "次拥措"
            },{
              "id": 31,
              "riverName": "沙绕措"
            },{
              "id": 32,
              "riverName": "吾年措"
            },{
              "id": 33,
              "riverName": "吾年措"
            },{
              "id": 34,
              "riverName": "吾年措"
            },{
              "id": 35,
              "riverName": "泽仁措"
            },{
              "id": 36,
              "riverName": "巴姆七湖"
            },{
              "id": 37,
              "riverName": "松措"
            },{
              "id": 38,
              "riverName": "查呈措"
            }
          ]
        }
      ],
      typeOptions:[
        {
          value:"护岸",
          label:"护岸"
        },{
          value:"堤防",
          label:"堤防"
        },{
          value:"水资源",
          label:"水资源"
        },{
          value:"地质灾害点信息",
          label:"地质灾害点信息"
        },{
          value:"水电站",
          label:"水电站"
        },{
          value:"水文站",
          label:"水文站"
        },{
          value:"岸线规划功能分区",
          label:"岸线规划功能分区"
        },{
          value:"河湖划界",
          label:"河湖划界"
        },{
          value:"灌区",
          label:"灌区"
        },{
          value:"拟建水库",
          label:"拟建水库"
        },{
          value:"地质灾害发生实际区域和转移路线",
          label:"地质灾害发生实际区域和转移路线"
        },{
          value:"引水工程",
          label:"引水工程"
        },{
          value:"水网管线",
          label:"水网管线"
        },{
          value:"涉河建筑物",
          label:"涉河建筑物"
        },{
          value:"采砂点位",
          label:"采砂点位"
        }
      ]
    }
  },
  methods: {
    async shuixiInputChange(element){
      if(element === ""){
        this.shuixiTableData = this.initialData
      }else{
        const riverInfoRes = await axios({ url: `/api/water-systems/by-keyword`, method: 'get' , data:{keyword:element}}) //水系基本信息
        console.log(riverInfoRes.data)
        this.shuixiTableData = riverInfoRes.data
      }
    },
    async shuixiDataFetchAll(){
      const riverInfoRes = await axios({ url: `/api/water-systems`, method: 'get'}) //水系基本信息
      console.log(riverInfoRes.data)
      this.shuixiTableData = riverInfoRes.data
    },
    async shuixiCellClick(row){
      console.log('点击这一行：',row)
      const result = await axios({ url: `/api/water-systems/by-keyword`, method: 'get' , data:{keyword:row.riverName}}) //水系基本信息
      console.log('点击后查询到的数据：',result)
      bus.emit('shuixiCellClick', result.data[0])
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
    // this.shuixiDataFetchAll()
    //设置一下
    this.shuixiTableData = this.initialData
  },
  setup(props) {
    const state = reactive({
      activeName: ref('shuiXi'),
      select:"",
      input3:"",
      typevalue:""
    })
    return {
      ...toRefs(state)
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
.input-with-select .el-input-group__prepend {
  background-color: var(--el-fill-color-blank);
}
</style>