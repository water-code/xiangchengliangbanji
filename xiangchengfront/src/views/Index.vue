<script>
import SceneView from '../components/SceneView.vue'
import InformationTab from '../components/InformationTab.vue'
import SearchTab from '../components/SearchTab.vue'

import { ref } from 'vue'
import { ElButton } from 'element-plus'
import ClickInfoTab from "../components/ClickInfoTab.vue";

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
    },
    hadleSetAttributes(name, attrs) {
      this.attributes = attrs
      this.name = name
      console.log('这里是', attrs)
      const clickInfoTab = this.$refs.clickInfoTab
      clickInfoTab.openDialog()
    }
  },
  setup() {
    const attributes = ref({}) // 假设当前河流信息的fid为123
    const name = "水系"
    return {
      attributes,name
    }
  }
}
</script>
<template>
  <SceneView :mapProperties="mapProperties" @map-ready="onMapReady" @setAttributes="hadleSetAttributes"/>
  <ClickInfoTab :attributes="attributes" :name="name" ref="clickInfoTab"></ClickInfoTab>
</template>
<style scoped>

</style>
