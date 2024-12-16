/* eslint-disable */
<template>
  <div class="login">
    <h1>로그인</h1>
    <form @submit.prevent="handleLogin">
      <div>
        <label for="id">아이디</label>
        <input v-model="id" id="id" type="text" placeholder="아이디 입력" />
      </div>
      <div>
        <label for="password">비밀번호</label>
        <input v-model="password" id="password" type="password" placeholder="비밀번호 입력" />
      </div>
      <button type="submit">확인</button>
    </form>
  </div>
</template>

<script>

import axios from "axios";

export default {
  data() {
    return {
      id: 'admin',
      password: 'password',
    };
  },
  methods: {
    async handleLogin() {
      // 백엔드로 로그인 데이터를 전송한다고 가정
      const isLoginSuccessful = true; // 예시용 조건

      const response = await axios.post('http://localhost:8080/api/login', {
        id: 'admin',
        password: 'password'
      });

      localStorage.setItem('token', response.data.token); // JWT 토큰 저장

      console.log("1.response:", response)
      console.log("2.localStorage:", localStorage)

      if (isLoginSuccessful) {
        this.$router.push('/user-search');
      } else {
        alert('로그인 실패');
      }
    },
  },
};
</script>

<style scoped>
.login {
  text-align: center;
  margin-top: 50px;
}
</style>
