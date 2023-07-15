<template>
  <div class="detailTabContainer">
    <!-- <el-dialog v-model="dialogVisible" @close="restore"> -->
    <el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick">
      <el-tab-pane class="basicInfo" label="基本信息" name="basic">
        <div class="propsTable">
          <el-descriptions :border="true" :column="2" size="large">
            <el-descriptions-item label="名称">{{ watersystemsresult.riverName }}</el-descriptions-item>
            <el-descriptions-item label="类型">{{ watersystemsresult.waterType }}</el-descriptions-item>
            <el-descriptions-item label="代码">{{ watersystemsresult.surveyCode }}</el-descriptions-item>
            <el-descriptions-item label="长度">{{ watersystemsresult.totalLength }}</el-descriptions-item>
            <el-descriptions-item label="起始位置">{{ watersystemsresult.startLocation }}</el-descriptions-item>
            <el-descriptions-item label="结束位置">{{ watersystemsresult.endLocation }}</el-descriptions-item>
            <el-descriptions-item label="级别">{{ watersystemsresult.riverLevel }}</el-descriptions-item>
            <el-descriptions-item label="河口流量">{{ watersystemsresult.estuaryFlow }}</el-descriptions-item>
            <el-descriptions-item label="流域总水量">{{ watersystemsresult.basinvol }}</el-descriptions-item>
            <el-descriptions-item label="径流">{{ watersystemsresult.runoff }}</el-descriptions-item>
            <el-descriptions-item label="可用水量">{{ watersystemsresult.available }}</el-descriptions-item>
            <el-descriptions-item label="已用水量">{{ watersystemsresult.consum }}</el-descriptions-item>
            <el-descriptions-item label="健康评价"><img class="charts-img" src="http://xiangoos.ruankun.xyz/%E5%81%A5%E5%BA%B7%E8%AF%84%E4%BB%B7.jpg"></el-descriptions-item>
          </el-descriptions>
        </div>
      </el-tab-pane>
      <el-tab-pane class="basicInfo" label="太阳能提灌站" name="solarirstation">
        <div class="propsTable">
          <!--solarirstationresult-->
          <el-table :data="solarirstationresult" style="width: 100%" :empty-text="'暂无数据'">
            <el-table-column prop="id" label="ID"/>
            <el-table-column prop="name" label="名称"/>
            <el-table-column prop="comment" label="说明" />
            <el-table-column prop="region" label="区域" />
            <el-table-column prop="river" label="流域" />
          </el-table>
        </div>
      </el-tab-pane>
      <el-tab-pane class="basicInfo" label="采砂" name="sandsite">
        <div class="propsTable">
          <el-table :data="sandsiteresult" style="width: 100%" :empty-text="'暂无数据'">
            <el-table-column prop="id" label="ID"/>
            <el-table-column prop="name" label="名称"/>
            <el-table-column prop="intro" label="说明" />
            <el-table-column prop="region" label="区域" />
            <el-table-column prop="river" label="流域" />
          </el-table>
        </div>
      </el-tab-pane>
      <el-tab-pane class="basicInfo" label="涉河建筑" name="riversidebuilding">
        <div class="propsTable">
          <el-table :data="riversidebuildingresult" style="width: 100%" :empty-text="'暂无数据'">
            <el-table-column prop="id" label="ID"/>
            <el-table-column prop="name" label="名称"/>
            <el-table-column prop="type" label="类别"/>
            <el-table-column prop="intro" label="说明" />
            <el-table-column prop="region" label="区域" />
            <el-table-column prop="river" label="流域" />
          </el-table>
        </div>
      </el-tab-pane>
      <el-tab-pane class="basicInfo" label="水网管线" name="waternetpipe">
        <div class="propsTable">
          <el-table :data="waternetpiperesult" style="width: 100%" :empty-text="'暂无数据'">
            <el-table-column prop="id" label="ID"/>
            <el-table-column prop="name" label="名称"/>
            <el-table-column prop="comment" label="说明" />
            <el-table-column prop="region" label="区域" />
            <el-table-column prop="river" label="流域" />
          </el-table>
        </div>
      </el-tab-pane>
      <el-tab-pane class="basicInfo" label="灌区" name="irrigationarea">
        <div class="propsTable">
          <el-table :data="irrigationarearesult" style="width: 100%" :empty-text="'暂无数据'">
            <el-table-column prop="id" label="ID"/>
            <el-table-column prop="name" label="名称"/>
            <el-table-column prop="perimet" label="周长" />
            <el-table-column prop="area" label="面积" />
            <el-table-column prop="region" label="区域" />
            <el-table-column prop="river" label="流域" />
          </el-table>
        </div>
      </el-tab-pane>
      <el-tab-pane class="basicInfo" label="拟建水库" name="proposalreservoir">
        <div class="propsTable">
          <el-table :data="proposalreservoirresult" style="width: 100%" :empty-text="'暂无数据'">
            <el-table-column prop="id" label="ID"/>
            <el-table-column prop="name" label="名称"/>
            <el-table-column prop="region" label="区域" />
            <el-table-column prop="river" label="流域" />
          </el-table>
        </div>
      </el-tab-pane>
      <el-tab-pane class="basicInfo" label="水安全" name="watersafety">
        <div class="propsTable">
          <el-table :data="watersafetyresult" style="width: 100%" :empty-text="'暂无数据'">
            <el-table-column prop="id" label="ID"/>
            <el-table-column prop="name" label="名称"/>
            <el-table-column prop="type" label="类型"/>
            <el-table-column prop="address" label="地址"/>
            <el-table-column prop="length" label="长度"/>
            <el-table-column prop="construction_year" label="建设时间"/>
            <el-table-column prop="responsible_department" label="负责单位"/>
            <el-table-column prop="description" label="描述"/>
            <el-table-column prop="height" label="高度"/>
            <el-table-column prop="region" label="区域" />
            <el-table-column prop="river" label="流域" />
          </el-table>
        </div>
      </el-tab-pane>
      <el-tab-pane class="basicInfo" label="水资源" name="waterresources">
        <div class="propsTable">
          <el-table :data="waterresourcesresult" style="width: 100%" :empty-text="'暂无数据'">
            <el-table-column prop="id" label="ID"/>
            <el-table-column prop="resourceType" label="类型"/>
            <el-table-column prop="resourceVolume" label="总量"/>
            <el-table-column prop="resourceUsage" label="用量"/>
            <el-table-column prop="resourceQuality" label="质量"/>
            <el-table-column prop="resourceStatus" label="状态"/>
            <el-table-column prop="extractionRate" label="利用率"/>
            <el-table-column prop="dataYear" label="年份"/>
            <el-table-column prop="region" label="区域"/>
            <el-table-column prop="river" label="流域"/>
          </el-table>
        </div>
      </el-tab-pane>
      <el-tab-pane class="basicInfo" label="水利工程" name="waterprojects">
        <div class="propsTable">
          <el-table :data="waterprojectsresult" style="width: 100%" :empty-text="'暂无数据'">
            <el-table-column prop="id" label="ID"/>
            <el-table-column prop="name" label="名称"/>
            <el-table-column prop="type" label="类型"/>
            <el-table-column prop="capacity" label="容量"/>
            <el-table-column prop="status" label="状态"/>
            <el-table-column prop="description" label="描述"/>
            <el-table-column prop="region" label="区域"/>
            <el-table-column prop="river" label="流域"/>
          </el-table>
        </div>
      </el-tab-pane>
<!--      <el-tab-pane class="basicInfo" label="健康评价" name="healthevaluation">healthevaluation</el-tab-pane> 放在基本信息中-->
<!--      <el-tab-pane class="basicInfo" label="移民搬迁" name="relocation">relocation</el-tab-pane> 暂时没有数据-->
<!--      <el-tab-pane class="basicInfo" label="河湖划界" name="riverlakeboudary">-->
<!--        <div class="propsTable">-->
<!--        </div>-->
<!--      </el-tab-pane>-->
      <el-tab-pane class="basicInfo" label="岸线规划" name="shorelineplanning">
        <div class="propsTable">
          <el-table :data="shorelineplanningresult" style="width: 100%" :empty-text="'暂无数据'">
            <el-table-column prop="id" label="ID"/>
            <el-table-column prop="planningName" label="名称"/>
            <el-table-column prop="shorelineType" label="类型"/>
            <el-table-column prop="planningLength" label="长度"/>
<!--            <el-table-column prop="planningDescription" label="描述"/>-->
            <el-table-column prop="approvalStatus" label="是否审批"/>
          </el-table>
        </div>
      </el-tab-pane>
    </el-tabs>
    <!-- </el-dialog> -->
  </div>

</template>
<script>
import { reactive, toRefs, ref } from 'vue'
import { Search } from '@element-plus/icons-vue'
import bus from '../utils/bus.js'
import GradientStackedAreaChart from './GradientStackedAreaChart.vue'

export default {
  components: { GradientStackedAreaChart },
  data() {
    return {}
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event)
    }
  },
  computed: {
    Search() {
      return Search
    }
  },
  props: {},
  mounted() {},
  setup(props) {
    const state = reactive({
      watersystemsresult: [],
      solarirstationresult:[],
      sandsiteresult:[],
      riversidebuildingresult:[],
      waternetpiperesult:[],
      irrigationarearesult:[],
      proposalreservoirresult:[],
      watersafetyresult:[],
      waterresourcesresult:[],
      waterprojectsresult:[],
      shorelineplanningresult:[],
      activeName: ref('basic'),
      dialogVisible: false
    })
    bus.on('shuixiCellClick', (result) => {
      //watersystemsresult, solarirstationresult,sandsiteresult, riversidebuildingresult, waternetpiperesult, irrigationarearesult, proposalreservoirresult, watersafetyresult, waterresourcesresult, shorelineplanningresult
      console.log('所有的数据都传过来了：', result)

      state.watersystemsresult = result[0]

      state.solarirstationresult = result[1]

      state.sandsiteresult = result[2]
      state.riversidebuildingresult = result[3]
      state.waternetpiperesult = result[4]
      state.irrigationarearesult = result[5]
      state.proposalreservoirresult = result[6]
      state.watersafetyresult = result[7]
      state.waterresourcesresult = result[8]
      state.waterprojectsresult = result[9]
      state.shorelineplanningresult = result[10]

      state.dialogVisible = true
    })
    const restore = () => {
      // 重置Tab当前所在信息栏
      state.activeName = 'basic'
    }
    return {
      ...toRefs(state),
      restore
    }
  }
}
</script>
<style lang="less" scoped>
.detailTabContainer {
  // overflow: auto;
  position: absolute;
  top: 0;
  left: 0;
  display: flex;
  padding: 15px;
  height: 100%;
}
.charts {
  width: 100%;
}
.charts-img {
  width: 100%;
}
.basicInfo {
  display: flex;
  flex-direction: column;
  width: 100%;
}
.el-tabs__content {
  height: 80%;
}
.propsTable {
  width: 100%;
  height: 400px;
  overflow: auto;
  // margin-top: 50px;
}

// 背景色调制透明
/deep/.el-descriptions__body {
  background-color: rgba(255, 255, 255, 0.3);
}
/deep/.el-descriptions__label.el-descriptions__cell.is-bordered-label {
  background-color: rgba(255, 255, 255, 0);
}

//设置table透明
.propsTable /deep/  .el-table, .el-table__expanded-cell {
  background-color: transparent;
}
.propsTable /deep/ .el-table tr {
  background-color: transparent!important;
}
.propsTable /deep/  .el-table--enable-row-transition .el-table__body td, .el-table .cell{
  background-color: transparent;
}
.el-table::before {//去除底部白线
  left: 0;
  bottom: 0;
  width: 100%;
  height: 0px;
}
</style>