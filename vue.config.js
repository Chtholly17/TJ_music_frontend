const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  devServer: {
    https: false,
    proxy: {
      '/api': {
        target: 'http://4l5821882j.imdo.co:42372',
        changeOrigin: true,
        pathRewrite: {
          '^/api': ''      //相当于用api代替target里面的地址，后面组件中调用接口时直接用api代替
        }
      }
    }
  }
})
