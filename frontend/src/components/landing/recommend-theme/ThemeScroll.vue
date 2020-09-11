<template>
  <div class="theme-scroll wrap">
    <div class="show-themes mt-8" v-for="t in theme" :key="t">
      <div class="color-group" @click="selectTheme(t)">
        <!-- {{t}}  -->
        <div class="theme-colors" :style="{'background-color' : t.color1}">
        </div>
        <div class="theme-colors" :style="{'background-color' : t.color2}">
        </div>
        <div class="theme-colors" :style="{'background-color' : t.color3}">
        </div>
        <div class="theme-colors" :style="{'background-color' : t.color4}">
        </div>
        <div class="theme-colors" :style="{'background-color' : t.color5}">
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data(){
    return{
      theme: [
        {
          color1: "#e63946",
          color2: "#f1faee",
          color3: "#a8dadc",
          color4: "#457b9d",
          color5: "#1d3557",
        },
        {
          color1: "#d90429",
          color2: "#ef233c",
          color3: "#edf2f4",
          color4: "#edf2f4",
          color5: "#8d99ae",
        },
        {
          color1: "#003049",
          color2: "#d62828",
          color3: "#f77f00",
          color4: "#fcbf49",
          color5: "#eae2b7",
        },
        {
          color1: "#03071e",
          color2: "#370617",
          color3: "#6a040f",
          color4: "#9d0208",
          color5: "#d00000",
        },
        {
          color1: "#e63946",
          color2: "#f1faee",
          color3: "#a8dadc",
          color4: "#457b9d",
          color5: "#1d3557",
        },

      ]
    }
  },
  methods:{
    selectTheme(theme){
      alert(theme);
    }
  }
}
</script>
<style scoped>
.theme-scroll.wrap{
}

.color-group{
  height: 85px;
  width: 90%;
  margin-left: 5%;
}
.theme-colors{
  height: 20%; 
  width: 20%;
}
</style>