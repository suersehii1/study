<template>
  <div id="app">
    <header>
      <h1>Vue App</h1>
    </header>
    <main>
      <!-- 라우터에서 지정된 컴포넌트가 여기에 렌더링됩니다 -->
      <router-view></router-view>
    </main>
  </div>
</template>

<script>
export default {
  name: 'App',
};
</script>

<style>
/* 전체 레이아웃 스타일 */
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

header {
  background-color: #42b983;
  color: white;
  padding: 20px 0;
}

main {
  margin: 20px;
}
</style>
