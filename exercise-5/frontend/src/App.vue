<template>
  <nav>
    <router-link to="/calculator">Calculator</router-link> |
    <router-link to="/contact">Contact</router-link> |
    <router-link to="/sign-up">Sign Up</router-link> |
    <router-link to="/login">Login</router-link> |
    <router-link @click=logout to="/login">Logout</router-link>
  </nav>
  <router-view/>
</template>

<style>
html {
  background-color: #2b2e4a;
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #903749;
}

nav a.router-link-exact-active {
  color: #e84545;
}
</style>
<script setup>
import logout from "@/features/Logout";
</script>
