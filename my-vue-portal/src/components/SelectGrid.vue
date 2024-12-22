/* eslint-disable */
<template>
  <div id="app">
    <h1>User 정보 (Axios 통신 예제)</h1>
    <button @click="fetchUsers">User 목록 가져오기</button>
    <ul v-if="users.length">
      <li v-for="(user, index) in users" :key="index">
        {{ user.ID }} - {{ user.NAME }} - {{ user.EMAIL }}
        {{ user.CONTENTS }}
        <br>
      </li>
    </ul>
    <p v-else>데이터가 없습니다.</p>
  </div>
  <div class="aggrid-view">
    <h1>UserInfo( Example Ag-Grid)</h1>
    <CommonAgGrid v-if="showGrid" :gridData="gridData" :gridColumns="gridColumns" :gridOptions="gridOptions" />
  </div>
</template>

<script>

import axios from "axios";
import CommonAgGrid from "@/components/api/CommonAgGrid.vue";

export default {
  created() {
    this.getUserRoles()
  },
  mounted() {
    this.$nextTick(() => {
      console.log("nextTick")
      this.showGrid = true
    });

  },
  components: {
    CommonAgGrid,
  },
  data() {
    return {
      users: [], // 사용자 데이터 저장
      showGrid: false,
      gridData: [],
      gridColumns: [
        { headerName: "ID", field: "ID", sortable: true, filter: true },
        { headerName: "NAME", field: "NAME", sortable: true, filter: true },
        { headerName: "EMAIL", field: "EMAIL", sortable: true, filter: true },
        { headerName: "ROLE_ID", field: "ROLE_ID", sortable: true, filter: true },
        { headerName: "ROLE_NAME", field: "ROLE_NAME", sortable: true, filter: true },
        { headerName: "ASSIGNED_AT", field: "ASSIGNED_AT", sortable: true, filter: true },
      ],
      gridOptions: {
        pagination: true,
        defaultColDef: {
          resizable: true,
        },
      },
    };
  },
  methods: {
    getUserRoles() {
      const token = localStorage.getItem('token'); // JWT 토큰 가져오기
      axios.get("http://localhost:8080/api/user/getUserRoles", {
        headers: { Authorization: `Bearer ${token}` },
      }).then((res) => {
        if (res.data) {
          this.gridData = res.data
        }
        console.log(res)
      })
    },
    async fetchUsers() {
      try {
        const token = localStorage.getItem('token'); // JWT 토큰 가져오기
        const response = await axios.get("http://localhost:8080/api/user/all", {
          headers: { Authorization: `Bearer ${token}` }
        });

        this.users = response.data; // API 응답 데이터를 users 배열에 저장
      } catch (error) {
        console.error("Error fetching users:", error);
        alert("데이터를 가져오는 중 오류가 발생했습니다.");
      }
    },
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  text-align: left;
  margin-top: 40px;
}
button {
  padding: 10px 20px;
  font-size: 16px;
  cursor: pointer;
}
ul {
  list-style: none;
  padding: 0;
}
li {
  margin: 5px 0;
  font-size: 18px;
}
.aggrid-view {
  padding-top: 10px;
  width: 1000px;
  height: 400px;
}
</style>
