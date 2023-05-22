<template>
    <el-dialog v-model="dialogVisible" :title="河流搜索结果" width="50%" @close="restore">
      <el-tabs v-model="activeTab">
        <el-tab-pane label="河流搜索结果" name="basic">
          <div class="basicInfo">
            <el-descriptions :border="true" :column="2">
              <!-- <el-descriptions-item label="id">{{ riverInfo.id }}</el-descriptions-item> -->
              <el-descriptions-item label="水域类型">{{ riverInfo.waterType }}</el-descriptions-item>
            </el-descriptions>
          </div>
        </el-tab-pane>
      </el-tabs>
    </el-dialog>
  </template>
  
  <script>
  import { reactive, toRefs, watchEffect, watch, toRaw, onMounted } from 'vue'
  import { ElDialog, ElTabs, ElTabPane } from 'element-plus'
  import axios from '../api/request'
  import Radar from './Radar.vue'
  
  export default {
    components: { ElDialog, ElTabs, ElTabPane, Radar },
    methods: {
    //   planningPosition(index, row) {
    //     //定位planning函数
    //     const start = JSON.parse(row.planningStartPoint)
    //     const end = JSON.parse(row.planningEndPoint)
    //     console.log(start, end)
    //     const Lng = (start.lng + end.lng) / 2
    //     const Lat = (start.lat + end.lat) / 2
    //     const z = 3000
    //     const zoom = 15
    //     //组件之间通信，将经纬度传递给SceneView进行处理
    //     this.$eventBus.emit('location', [[Lng, Lat, z], zoom])
    //   }
    },
    props: {
    srname: {
        type: String,
        required: true
      }
    },
    setup(props) {
      const state = reactive({
        dialogVisible: false,
        activeTab: 'basic',
        riverInfoRes: {},
      })
  
      const fetchData = async () => {
        //监听 fid 的变化,因为父传过来的props可能产生异步,所以要监听props的变化
        watch(
          () => props.srname,
          async newsrname => {
            console.log(props.srname, newsrname)
            try {
              const riverInfoRes = await axios({ url: `/api/water-systems/by-river-code-or-river-name`, data:{riverName:newsrname}, method: 'get'}) //水系基本信息
              console.log(riverInfoRes)
              state.riverInfoRes = riverInfoRes
            } catch (error) {
              console.error(error)
            }
          },
          { immediate: true }
        ) // 立即执行一次
      }
  
      const openDialog = () => {
        state.dialogVisible = true
        fetchData()
      }
  
      // 关闭当前河流窗口后触发
      const restore = () => {
        // 收缩雷达图信息栏collapse
        state.radarList.activeName = []
        // 重置Tab当前所在信息栏
        state.activeTab = 'basic'
      }
  
      return {
        ...toRefs(state),
        openDialog,
        restore
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