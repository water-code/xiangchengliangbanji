<script>
import SceneView from '../components/SceneView.vue'
import InformationTab from '../components/InformationTab.vue'
import SearchTab from '../components/SearchTab.vue'

import { reactive, ref } from 'vue'
import { ElButton } from 'element-plus'
import ClickInfoTab from '../components/ClickInfoTab.vue'
import bus from '../utils/bus.js'

export default {
  name: 'Index',
  components: {
    ClickInfoTab,
    SceneView,
    ElButton,
    InformationTab
  },
  data() {
    return {
      mapProperties: {
        portalItem: {
          // 在此处设置您的 Portal Item ID 或 URL
        }
      },
      viewProperties: {
        // 在此处设置 SceneView 的属性，例如初始位置、缩放级别等
        center: [120, 30],
        zoom: 4
      }
    }
  },
  methods: {
    onMapReady(sceneView) {
      // 场景视图已准备就绪
      console.log('场景视图已加载')
    }
  },
  setup() {
    let attributes = ref({}) // 假设当前河流信息的fid为123
    let name = ref('')

    const clickInfoTab = ref(null)
    // let updateChildProps = () => {}

    const hadleSetAttributes = (n, attr) => {
      attributes.value = attr
      name.value = n
      console.log('这里是', attr, n)
      clickInfoTab.value.openDialog()
    }

    bus.on('loadMapData', data => {
      console.log(name, data)
      hadleSetAttributes(data[0], data[1])
    })
    return {
      attributes,
      name,
      clickInfoTab,
      hadleSetAttributes
    }
  }
}
</script>
<template>
  <SceneView :mapProperties="mapProperties" @map-ready="onMapReady" @setAttributes="hadleSetAttributes" />
  <ClickInfoTab :attributes="attributes" :name="name" ref="clickInfoTab"></ClickInfoTab>
</template>
<style scoped>
</style>
