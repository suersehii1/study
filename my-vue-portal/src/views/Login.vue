/* eslint-disable */
<template>
  <div class="login">
    <h1>My portal</h1>
    <h1>Login</h1>
    <input type="text" placeholder="Username" v-model="username" />
    <input type="password" placeholder="Password" v-model="password" />
    <button @click="login">Login</button>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      username: 'robin',
      password: 'password',
    };
  },
  methods: {
    async login() {
        try {
        const response = await axios.post('http://localhost:8080/api/login', {
          id: this.username,
          password: this.password
        });

        localStorage.setItem('token', response.data.token); // JWT 토큰 저장
        console.log("1.response:", response)
        console.log("2.localStorage:", localStorage)
        this.$router.push('/main');
      } catch (error) {
        console.log('response->', error)
        alert("Login fail. there is no user information")

      }
    },

  },
};
</script>

<style>
.login {
  text-align: center;
  margin-top: 100px;
}
</style>
