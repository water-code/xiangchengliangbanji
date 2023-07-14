<template>
  <div class="search">
    <el-input v-model="data.inputVal" class="w-50 m-2 " placeholder="请输入河流关键字信息" clearable @change="getAllRiverDataByKey" @clear="getAllRiverData" @blur="onBlur">
      <template #prefix>
        <el-icon class="el-input__icon">
          <search />
        </el-icon>
      </template>
    </el-input>
    <!-- <el-input v-model="data.inputVal" placeholder="请输入河流关键字信息" clearable class="input" @clear="getAllRiverData" />
    <el-button type="primary" @click="getAllRiverDataByKey">查询</el-button> -->
  </div>
  <el-collapse v-model="data.activeNames" @change="handleChange">
    <el-collapse-item name="1">
      <el-table ref="singleTable" :data="data.tableData" highlight-current-row @current-change="handleCurrentChange" @row-click="goLocation" style="width: 100%" height="500" class="table">
        <el-table-column property="name" label="河流名称">
        </el-table-column>
        <el-table-column property="type" label="水域类型">
        </el-table-column>
        <el-table-column property="location" label="经纬度">
        </el-table-column>
      </el-table>
    </el-collapse-item>
  </el-collapse>

</template>

<script setup>
import { reactive } from 'vue'
import axios from '../api/request'
import bus from '../utils/bus.js'
// 定义数据
const data = reactive({
  activeNames: [],
  inputVal: '',
  tableData: [],
  currentRow: null
})

// 定义方法
function handleCurrentChange(val) {
  data.currentRow = val
}
function goLocation(row) {
  const locate = JSON.parse(row.location)
  const longitude = locate.longitude
  const latitude = locate.latitude
  const zoom = 12
  bus.emit('location', [[longitude, latitude], zoom])
}
function onBlur() {
  // 失去焦点时折叠面板关闭
  data.activeNames = []
}
// 请求河流基本信息
async function storageAllRiverData() {
  const res = await axios({ url: '/api/water-systems', method: 'get' }) //水系基本信息
  if (res.status == 200) {
    localStorage.setItem('riverList', JSON.stringify(res.data))
    data.tableData = res.data.map(item => ({ name: item.riverName, type: item.waterType, location: item.extraJson }))
  }
}
// 通过关键字请求对应的信息
function getAllRiverDataByKey() {
  // 搜索对应关键字的时候折叠面板展开
  data.activeNames = ['1']
  const list = JSON.parse(localStorage.getItem('riverList'))
  if (list) {
    data.tableData = list.filter(item => item.riverName.includes(data.inputVal.trim())).map(item => ({ name: item.riverName, type: item.waterType, location: item.extraJson }))
  }
}
// 获取河流全部基本信息
function getAllRiverData() {
  const list = JSON.parse(localStorage.getItem('riverList'))
  if (list) {
    data.tableData = list.map(item => ({ name: item.riverName, type: item.waterType, location: item.extraJson }))
  }
}
storageAllRiverData()
</script>

<style scoped>
/* 搜索功能板块 */
.el-input__wrapper {
  width: 350px;
}
.table {
  display: flex;
  justify-content: space-between;
}
.el-collapse-item__header {
  height: 28px;
}
</style>