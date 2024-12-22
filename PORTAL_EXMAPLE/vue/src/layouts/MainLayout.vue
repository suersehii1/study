<!-- src/layouts/MainLayout.vue -->
<template>
  <div>
    <header>
      <Menu :menu="menuData" />
    </header>
    <main>
      <router-view />
    </main>
  </div>
</template>

<script>
import Menu from "../components/Menu.vue";
import { menuData } from "../data/menu.js";

export default {
  components: {
    Menu,
  },
  data() {
    return {
      menuData, // 동적 메뉴 데이터
    };
  },
};
</script>

<style>
header {
  background-color: #333;
  color: white;
  padding: 10px;
}
main {
  padding: 20px;
}
</style>
