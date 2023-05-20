import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import viteCompression from 'vite-plugin-compression'


// https://vitejs.dev/config/
export default defineConfig({
  publicPath:'./',
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
      
      output: {
        chunkFileNames: 'static/js/[name]-[hash].js',
        entryFileNames: 'static/js/[name]-[hash].js',
        assetFileNames: 'static/[ext]/[name]-[hash].[ext]',
        // manualChunks(id) {
        //   if (id.includes('node_modules')) {
        //     return id.toString().split('node_modules/')[1].split('/')[0].toString();
        //   }
        // }
      }

    }
  }
})
