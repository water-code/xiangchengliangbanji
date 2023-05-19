import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import viteCompression from 'vite-plugin-compression'

// https://vitejs.dev/config/
export default defineConfig({

//在plugins配置数组里添加gzip插件
 plugins: [vue(),viteCompression({
  verbose: true,
  disable: false,
  threshold: 10240,
  algorithm: 'gzip',
  ext: '.gz',
})],
  define: {
    'process.env':{}
  },
  optimizeDeps: {
    include: ['axios'],
  },
  server: {
    host:'0.0.0.0',
    cors: true,
    open: true,
    proxy: {
      '/api': {
        target: 'http://127.0.0.1:8081',   //代理接口
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, '')  // 重定向.
      }
    }
  },
  build: {
    rollupOptions: {
      external: ['/api/captcha?type=math'],
    }
  }
})
