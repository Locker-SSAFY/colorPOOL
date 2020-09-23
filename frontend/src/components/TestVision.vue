<template>
  <div style="position: absolute; top: 30%; left: 30%;">
    <h1>This is Test Vision Page</h1>
    <v-btn @click="testApi">
      testApi
    </v-btn>
  </div>
</template>
<script>
import axios from 'axios'
export default {
  methods: {
    testApi(){
      axios.request('https://dapi.kakao.com/v2/vision/product/detect',
                      { params: {'image_url': 'https://t1.daumcdn.net/alvolo/_vision/openapi/r2/images/06.jpg'},
                        headers: {'Authorization': 'KakaoAK f820c9d3518ab3543b0918e9d6676dff'}})
      .then((res) => {
        console.log(res.data.result.objects)
      })
      .catch((err) => {
        console.log(err)
      })
    }
  }
}
</script>
<style scoped>
</style>