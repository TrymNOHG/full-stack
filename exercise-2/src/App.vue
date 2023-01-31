<template>
  <div id="app">
    <div id="nav">
      <router-link :to="{ name:  'CalcPage' }">Calculator</router-link> |
      <router-link :to="{ name: 'About' }">About</router-link>
    </div>
    <router-view />
  </div>
</template>

<style scoped>

#app {
  background-color: #00bd7e;
}

header {
  line-height: 1.5;
}


@media (min-width: 1024px) {
  header {
    display: flex;
    place-items: center;
  }

}
</style>
