/* eslint-disable */
<template>
  <nav class="header-menu">
    <router-link to="/main/home">Home</router-link>
    <router-link to="/main/about">About</router-link>
    <router-link to="/main/dashboard">Dashboard</router-link>
  </nav>
</template>

<style>
.header-menu {
  background-color: #333;
  padding: 10px;
  display: flex;
  gap: 15px;
}
.header-menu a {
  color: white;
  text-decoration: none;
}
.header-menu a.router-link-active {
  font-weight: bold;
  color: #4caf50;
}
</style>
