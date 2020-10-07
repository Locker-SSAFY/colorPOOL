<template>
  <v-row justify="center">

    <!-- 배색 추천 받으러 가기 버튼 -->
    <v-btn
      icon
      text
      @click="checkColor"
    >
      <v-icon size="80">mdi-arrow-right</v-icon>
    </v-btn>

    <div class="button-desc">
      <p>Get color recommendations</p>
      <p>based on the selected color</p>
    </div>

    <v-dialog
      v-model="dialog"
      max-width="500"
    >
      <v-card>
        <v-card-title class="headline">NOTICE!</v-card-title>

        <v-card-text class="dialog-text">
          색을 먼저 선택해주세요!
        </v-card-text>

        <v-card-actions>
          <v-spacer></v-spacer>

          <v-btn
            color="green darken-3"
            text
            @click="dialog = false"
          >
            확인
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
  export default {
    data () {
      return {
        dialog: false,
      }
    },
    methods: {
      checkColor(){
        if(this.$parent.selectedColor === ''){
          this.dialog = true;
        }else {
          document.body.className = "unlock";
          console.log(document.body);
          window.scrollTo({left: 0, top: 1000, behavior: 'smooth'});
        }
      }
    }
  }
</script>
<style scoped>
  .next-button {
    position: absolute;
    right: 20%;
    top: 85%;
  }

  .button-desc {
    position: absolute;
    font-size: 2.3rem;
    text-align: left;
    right: 4rem;
    bottom: 0.5rem;
    /* right: 30%;
    bottom: 20%; */
    transform: rotate(-15deg)
  }
  
  .button-desc p {
    font-family: 'ReenieBeanie-Regular';
    line-height: 0.7;
  }

  .dialog-text{
    font-size: 1.8rem;
  }
</style>