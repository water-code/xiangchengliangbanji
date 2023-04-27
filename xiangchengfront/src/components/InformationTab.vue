<template>
  <el-dialog v-model="dialogVisible" :title="riverInfo.riverName + '（河流编码:' + riverInfo.riverCode + '）'" width="50%">
    <el-tabs v-model="activeTab">
      <el-tab-pane label="河流基本信息" name="basic">
        <div class="basicInfo">
          <el-descriptions :border="true" :column="2">
            <!-- <el-descriptions-item label="id">{{ riverInfo.id }}</el-descriptions-item> -->
            <el-descriptions-item label="水域类型">{{ riverInfo.waterType }}</el-descriptions-item>
            <el-descriptions-item label="河流编码">{{ riverInfo.riverCode }}</el-descriptions-item>
            <el-descriptions-item label="河流名称">{{ riverInfo.riverName }}</el-descriptions-item>
            <el-descriptions-item label="别名">{{ riverInfo.riverAlias }}</el-descriptions-item>
            <el-descriptions-item label="总长度">{{ riverInfo.totalLength }}</el-descriptions-item>
            <el-descriptions-item label="流域面积">{{ riverInfo.basinArea }}</el-descriptions-item>
            <el-descriptions-item label="入海口径流量">{{ riverInfo.estuaryFlow }}</el-descriptions-item>
            <el-descriptions-item label="所属流域">{{ riverInfo.affiliatedBasin }}</el-descriptions-item>
            <el-descriptions-item label="岸别">{{ riverInfo.bank }}</el-descriptions-item>
            <el-descriptions-item label="测站编码">{{ riverInfo.surveyCode }}</el-descriptions-item>
            <el-descriptions-item label="支流级别">{{ riverInfo.branchLevel }}</el-descriptions-item>
            <el-descriptions-item label="交汇方式">{{ riverInfo.crossType }}</el-descriptions-item>
            <el-descriptions-item label="河流级别">{{ riverInfo.riverLevel }}</el-descriptions-item>
            <el-descriptions-item label="起点位置">{{ riverInfo.startLocation }}</el-descriptions-item>
            <el-descriptions-item label="终点位置">{{ riverInfo.endLocation }}</el-descriptions-item>
          </el-descriptions>
        </div>
      </el-tab-pane>
      <el-tab-pane label="健康评价" name="health">
        <div class="basicInfo">
            <el-descriptions :border="true" :column="2">
                <!-- <el-descriptions-item label="id">{{ healthInfo.id }}</el-descriptions-item> -->
                <!-- <el-descriptions-item label="水系ID">{{ healthInfo.waterSystemsId }}</el-descriptions-item> -->
                <el-descriptions-item label="评价标准">{{ healthInfo.evaluationStandard }}</el-descriptions-item>
                <el-descriptions-item label="河长">{{ healthInfo.riverChief }}</el-descriptions-item>
                <el-descriptions-item label="健康得分">
                    <el-text :style="{ color: healthInfo.healthScore < 60 ? 'red' : (healthInfo.healthScore < 70 ? 'orange' : (healthInfo.healthScore < 80 ? 'gray' : 'green')) }">
                        {{ healthInfo.healthScore }}
                    </el-text>
                </el-descriptions-item>
                <el-descriptions-item label="健康状况">
                  <el-result :icon="getIcon(healthInfo.healthScore)" :title="getTitle(healthInfo.healthScore)">
                    {{ healthInfo.healthStatus }}
                  </el-result>
                </el-descriptions-item>
                <el-descriptions-item label="评价结论">{{ healthInfo.evaluationConclusion }}</el-descriptions-item>
                <el-descriptions-item label="附件">{{ healthInfo.attachment }}</el-descriptions-item>
                <el-descriptions-item label="评价时间">{{ healthInfo.evaluationDate }}</el-descriptions-item>
            </el-descriptions>
        </div>
      </el-tab-pane>
      <el-tab-pane label="水安全" name="water_safety">
        <div class="basicInfo">
        </div>
      </el-tab-pane>
      <el-tab-pane label="水资源" name="water_resources">
        <div class="basicInfo">
          
        </div>
      </el-tab-pane>
      <el-tab-pane label="水利工程" name="water_projects">
        <div class="basicInfo">
        </div>
      </el-tab-pane>
      <el-tab-pane label="岸线规划" name="shoreline_planning">
        <div class="basicInfo">
          <el-table :data="planningList" :height="300">
            <el-table-column prop="id" label="ID"></el-table-column>
            <el-table-column prop="planningName" label="规划名称"></el-table-column>
            <el-table-column prop="waterSystemsId" label="水系编号"></el-table-column>
            <!-- <el-table-column prop="shorelineType" label="岸别类型"></el-table-column> -->
            <el-table-column prop="planningStartPoint" label="规划起点"></el-table-column>
            <el-table-column prop="planningEndPoint" label="规划终点"></el-table-column>
            <el-table-column prop="planningEndPoint" label="定位">
              <template #default="scope">
                <el-button link type="primary" size="small" @click="planningPosition(scope.$index, scope.row)">定位</el-button>
              </template>
            </el-table-column>
            <!-- <el-table-column prop="planningLength" label="规划长度"></el-table-column> -->
            <!-- <el-table-column prop="startPileNumber" label="起始桩号"></el-table-column> -->
            <!-- <el-table-column prop="endPileNumber" label="结束桩号"></el-table-column> -->
            <!-- <el-table-column prop="planningDescription" label="规划描述"></el-table-column> -->
            <el-table-column prop="approvalStatus" label="审批状态"></el-table-column>
          </el-table>
        </div>
      </el-tab-pane>
      <el-tab-pane label="河湖划界" name="river_lake_boundary">
        <div class="basicInfo">
        </div>
      </el-tab-pane>
      <el-tab-pane label="移民搬迁" name="relocation">
        <div class="basicInfo">
        </div>
      </el-tab-pane>
      <el-tab-pane label="防洪减灾" name="disaster_prevention_points">
        <div class="basicInfo">
        </div>
      </el-tab-pane>
    </el-tabs>
  </el-dialog>
</template>

<script>
import { reactive, toRefs, watchEffect,watch  } from 'vue'
import { ElDialog, ElTabs, ElTabPane } from 'element-plus'
import axios from '../api/request'

export default {
  components: { ElDialog, ElTabs, ElTabPane },
  
  methods:{
    getIcon(score) {
      if (score < 60) {
        return "error";
      } else if (score < 80) {
        return "warning";
      } else {
        return "success";
      }
    },
    getTitle(score) {
      if (score < 60) {
        return "健康得分过低";
      } else if (score < 80) {
        return "健康得分较低";
      } else {
        return "健康得分良好";
      }
    },
    planningPosition(index,row){
      //定位planning函数
      const start = JSON.parse(row.planningStartPoint)
      const end = JSON.parse(row.planningEndPoint)
      console.log(start, end)
      const Lng = (start.lng + end.lng) / 2;
      const Lat = (start.lat + end.lat) / 2;
      const z = 3000;
      const zoom = 8
      //组件之间通信，将经纬度传递给SceneView进行处理
      this.$eventBus.emit('location', [[Lng, Lat, z],zoom])
    }
  },
  props: {
    fid: {
      type: Number,
      required: true
    }
  },
  setup(props) {
    const state = reactive({
      dialogVisible: false,
      activeTab: 'basic',
      riverInfo: {},
      healthInfo: {},
      planningList: []
    })

    const fetchData = async () => {
      //监听 fid 的变化,因为父传过来的props可能产生异步,所以要监听props的变化
      watch(() => props.fid, async newfid => {
        console.log(props.fid, newfid)
        try {
          const riverInfoRes = await axios({url:`/api/water-systems/${newfid}`,method:"get"})  //水系基本信息
          const healthRes = await axios({url:`/api/health-evaluation/${newfid}`,method:"get"})  // 健康评价数据
          const shorelinePlanningList = await axios({url:`/api/shoreline-planning/by-water-systems-id?waterSystemsId=${newfid}`,method:"get"})  // 健康评价数据
          console.log(shorelinePlanningList)
          state.riverInfo = riverInfoRes.data
          state.healthInfo = healthRes.data
          state.planningList = shorelinePlanningList.data
        } catch (error) {
          console.error(error)
        }
      }, { immediate: true }) // 立即执行一次
    }

    const openDialog =  () => {
      console.log('hello world')
      state.dialogVisible = true
      fetchData()
    }
    // watchEffect(() => {
    //   console.log('监控', state.riverInfo) // 确认是否正确更新 riverInfo 数据
    //   console.log('监控', state.healthInfo) // 确认是否正确更新 healthInfo 数据
    // })

    return {
      ...toRefs(state),
      openDialog
    }
  }
}
</script>
<style>
.el-form-item__label {
  color: #333; /* 设置标签颜色 */
}
.el-result {
  --el-result-padding: 0px 0px !important;
  --el-result-icon-font-size: 30px !important;
  --el-result-title-font-size: 13px !important;
  --el-result-title-margin-top: 0px !important;
}
</style>