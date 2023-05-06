<template>
  <div>
      <h1> 这里是一片蛮荒…… </h1>
  </div>
</template>

<script lang="ts">
import {defineComponent, onBeforeMount} from "vue";
import router from "@/router";

export default defineComponent({
    setup() {
        onBeforeMount(()=>{
            router.replace({path: "/hello"})
        })
    }
})
</script>
