<template>
  <div class="weather-container">
    <div class="weather-date">
      {{ weatherDate }}
    </div>
    <div class="weather-content">
      <div class="weather-content-row">预 报：{{ weatherKeyPoint }}</div>
      <div class="weather-content-row">云 量：{{ cloudrate }}</div>
      <div class="weather-content-row">湿 度：{{ humidity }}</div>
      <div class="weather-content-row">温 度：{{ temperature }}</div>
      <div class="weather-content-row">能见度：{{ visibility }}</div>
    </div>
  </div>
</template>
<script>
import { reactive, toRefs, ref} from 'vue'
import { ElDialog, ElTabs, ElTabPane } from 'element-plus'
import axios from "../api/request.js";

export default {
  components: { ElDialog, ElTabs, ElTabPane },
  data() {
    return {
    }
  },
  methods: {
  },
  computed: {
  },
  props: {
  },
  mounted() {
  },
  setup(props) {
    const state = reactive({
      weatherDate: ref(''),
      weatherKeyPoint: ref(''),
      cloudrate: ref(''),
      humidity: ref(''),
      temperature: ref(''),
      visibility: ref(''),
    })

    let url = "/api/weather?lat=99.78&lon=29.00"
    const weatherInfo = axios({url: url, method: 'get'}).then(weatherInfo=>{
      console.log('嘿嘿', weatherInfo)
      //weatherInfo.data.server_time   //服务器时间
      //weatherInfo.data.result.forecast_keypoint  预报话
      let date = new Date()
      state.weatherDate = date.getFullYear() + "-" + date.getMonth() + "-" + date.getDay() + " " + date.getHours() + ":" + date.getMinutes()
      state.weatherKeyPoint = weatherInfo.data.result.forecast_keypoint
      state.cloudrate = weatherInfo.data.result.realtime.cloudrate
      state.humidity = weatherInfo.data.result.realtime.humidity
      state.temperature = weatherInfo.data.result.realtime.temperature
      state.visibility = weatherInfo.data.result.realtime.visibility
      //weatherInfo.data.result.realtime.cloudrate
      //weatherInfo.data.result.realtime.humidity
      //weatherInfo.data.result.realtime.temperature
      //weatherInfo.data.result.realtime.visibility

      console.log(state.weatherDate)
      console.log(state.weatherKeyPoint)
    })
    return {
      ...toRefs(state),
    }

  }
}
</script>
<style>
.weather-container{
  margin: 0;
  padding: 0;
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
}
.weather-date {
  padding-left: 10px;
  padding-top: 5px;
  height: 30%;
  width: 100%;
  text-align: left;
  font-size: 20px;
  font-weight: bold;
}
.weather-content {
  height: 70%;
  font-size: 16px;
  color: blue;
  display: flex;
  flex-direction: column;
}
.weather-content-row {
  padding-left: 50px;
  text-align: left;
}
</style>