<template>
  <div class="menu">
    <ul :class="{ horizontal: isTopMenu }">
      <li v-for="item in menu" :key="item.id">
        <div @click="handleClick(item)">
          {{ item.name }}
        </div>
        <Menu
            v-if="item.children && selectedMenuId === item.id"
            :menu="item.children"
            :isTopMenu="false"
        />
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "Menu",
  props: {
    menu: {
      type: Array,
      required: true,
    },
    isTopMenu: {
      type: Boolean,
      default: true, // 기본값으로 최상위 메뉴인지 여부
    },
  },
  data() {
    return {
      selectedMenuId: null, // 선택된 메뉴 ID
    };
  },
  methods: {
    handleClick(item) {
      if (item.children) {
        // 하위 메뉴가 있는 경우, 해당 메뉴를 펼침
        this.selectedMenuId = this.selectedMenuId === item.id ? null : item.id;
      } else if (item.path) {
        // 하위 메뉴가 없으면 해당 경로로 이동
        this.$router.push(item.path);
      }
    },
  },
};
</script>

<style>
.menu ul {
  list-style: none;
  padding: 0;
  margin: 0;
}
.menu ul.horizontal {
  display: flex; /* 가로로 정렬 */
  gap: 15px; /* 메뉴 간격 */
}
.menu li {
  cursor: pointer;
}
.menu li div {
  padding: 10px;
}
.menu li div:hover {
  background-color: #e30d57;
}
</style>
