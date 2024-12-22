const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    port: 9090, // 개발 서버 포트 설정
  },
  lintOnSave: false, // ESLint를 완전히 비활성화
})
