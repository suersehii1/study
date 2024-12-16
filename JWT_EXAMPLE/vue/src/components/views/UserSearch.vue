/* eslint-disable */
<template>
  <div id="app">
    <h1>Axios 통신 예제</h1>
    <button @click="fetchUsers">사용자 목록 가져오기</button>
    <ul v-if="users.length">
      <li v-for="(user, index) in users" :key="index">
        {{ user.ID }} - {{ user.NAME }} - {{ user.EMAIL }}
        {{ user.CONTENTS }}
        <br>
      </li>
    </ul>
    <p v-else>데이터가 없습니다.</p>
  </div>
</template>

<script>

import axios from "axios";

export default {
  data() {
    return {
      users: [], // 사용자 데이터 저장
    };
  },
  methods: {
    async fetchUsers() {
      try {
        // const token = localStorage.getItem('token'); // JWT 토큰 가져오기
        // const response = await axios.get('http://localhost:8080/api/user', {
        //   headers: { Authorization: `Bearer ${token}` }
        // });

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
  text-align: center;
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
</style>
