<template>
  <router-link to="/">个人主页</router-link> |
    <router-link to="/Login">登录</router-link> |
    <router-link to="/Register">注册</router-link>
  <router-view></router-view>
</template>

<script>

export default {
  name: 'App',
  components: {

  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
