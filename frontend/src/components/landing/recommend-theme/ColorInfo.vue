<template>
<div class="color-info wrap">
  <p class="color-title">YOUR COLOR</p>
  <div class="underline"></div>

  <!-- <div class="textfield-tape">
    <v-text-field dense v-model="nickName" placeholder="nickname" required></v-text-field>
  </div> -->

  <div class="color-name-tape">
    <p :style="{'color' : this.$parent.selectedColor.hex}">{{color.name}}</p>
  </div>

  <div class="color-info container">
    <div>
      <table>
        <tr>
          <td><span>Complementary</span></td>
          <td><span>색상 대비를 이루는 색상 조합</span></td>
        </tr>
      </table>
      <div :style="{'background': 'linear-gradient(to right, rgb(' + this.complementary[0].r + ',' + this.complementary[0].g + ',' + this.complementary[0].b + ')'  + ' 0% 50%, rgb(' + this.complementary[1].r + ',' + this.complementary[1].g + ',' + this.complementary[1].b + ') 50% 100%)'}"></div>
    </div>
    <div>
      <table>
        <tr>
          <td><span>Analogous</span></td>
          <td><span>유사한 색상들을 통해 안정감을 주는 조합</span></td>
        </tr>
      </table>
      <div :style="{'background': 'linear-gradient(to right, rgb(' + this.analogous[0].r + ',' + this.analogous[0].g + ',' + this.analogous[0].b + ')'  + ' 0% 33%, rgb(' + this.analogous[1].r + ',' + this.analogous[1].g + ',' + this.analogous[1].b + ') 33% 67%, rgb(' + this.analogous[2].r + ',' + this.analogous[2].g + ',' + this.analogous[2].b + ') 67% '}"></div>
    </div>
    <div>
      <table>
        <tr>
          <td><span>Triad</span></td>
          <td><span>색상표에서 고르게 자리잡고 있는 조합</span></td>
        </tr>
      </table>
      <div :style="{'background': 'linear-gradient(to right, rgb(' + this.triad[0].r + ',' + this.triad[0].g + ',' + this.triad[0].b + ')'  + ' 0% 33%, rgb(' + this.triad[1].r + ',' + this.triad[1].g + ',' + this.triad[1].b + ') 33% 67%, rgb(' + this.triad[2].r + ',' + this.triad[2].g + ',' + this.triad[2].b + ') 67% '}"></div>
    </div>
    <div>
      <table>
        <tr>
          <td><span>Tetradic</span></td>
          <td><span>두 쌍의 보색들을 사용하는 지배적인 조합</span></td>
        </tr>
      </table>
      <div :style="{'background': 'linear-gradient(to right, rgb(' + this.tetradic[0].r + ',' + this.tetradic[0].g + ',' + this.tetradic[0].b + ')'  + ' 0% 25%, rgb(' + this.tetradic[1].r + ',' + this.tetradic[1].g + ',' + this.tetradic[1].b + ') 25% 50%, rgb(' + this.tetradic[2].r + ',' + this.tetradic[2].g + ',' + this.tetradic[2].b + ') 50% 75%, rgb(' + this.tetradic[3].r + ',' + this.tetradic[3].g + ',' + this.tetradic[3].b + ') 75%'}"></div>
    </div>
    <div>
      <table>
        <tr>
          <td><span>Square</span></td>
          <td><span>색상표에서 고르게 자리잡은 보색쌍 조합</span></td>
        </tr>
      </table>
      <div :style="{'background': 'linear-gradient(to right, rgb(' + this.square[0].r + ',' + this.square[0].g + ',' + this.square[0].b + ')'  + ' 0% 25%, rgb(' + this.square[1].r + ',' + this.square[1].g + ',' + this.square[1].b + ') 25% 50%, rgb(' + this.square[2].r + ',' + this.square[2].g + ',' + this.square[2].b + ') 50% 75%, rgb(' + this.square[3].r + ',' + this.square[3].g + ',' + this.square[3].b + ') 75%'}"></div>
    </div>
  </div>
</div>
</template>
<script>
import complementaryColors from 'complementary-colors'

export default {
  props: {
    color: {
      default: void 0
    },
  },
  data() {
    return {
      complementary: [],
      triad: [],
      square: [],
      analogous: [],
      splitComplementary: [],
      tetradic: [],
    }
  },
  created() {
    var myColor = new complementaryColors(this.color.hex);
    this.complementary = myColor.complementary();
    myColor = new complementaryColors(this.color.hex);
    this.triad = myColor.triad();
    myColor = new complementaryColors(this.color.hex);
    this.square = myColor.square();
    myColor = new complementaryColors(this.color.hex);
    this.analogous = myColor.analogous();
    myColor = new complementaryColors(this.color.hex);
    this.tetradic = myColor.tetradic();
  },
  watch: {
    color () {
      var myColor = new complementaryColors(this.color.hex);
      this.complementary = myColor.complementary();
      myColor = new complementaryColors(this.color.hex);
      this.triad = myColor.triad();
      myColor = new complementaryColors(this.color.hex);
      this.square = myColor.square();
      myColor = new complementaryColors(this.color.hex);
      this.analogous = myColor.analogous();
      myColor = new complementaryColors(this.color.hex);
      this.tetradic = myColor.tetradic();
    }
  }
}
</script>
<style scoped>
  .color-info.wrap {
    padding: 5%;
    padding-top: 25%;
    height: 100%;
    width: 100%;
  }

  .color-info.wrap .color-title {
    display: inline;
    font-family: 'PermanentMarker-Regular';
    font-size: 2.1rem;
    font-weight: bold;
    margin-left: 1rem;
  }

  .underline{
    background-color: black;
    position: absolute;
    left: 0%;
    height: 2px;
    width: 80%;
  }

  .color-name-tape {
    margin: 29px 0;
    background-color: #424242;
    padding: 1% 5% 0% 7%;
    width: 75%;
    height: 45px;

    mask-image: url(https://cdn2.bustle.com/nylon/2020/tape_b-80d71c228a.svg);
    mask-repeat: no-repeat;
    mask-size: 100% 100%;
    background-image: url(https://imgix.bustle.com/uploads/image/2020/3/10/428e5a38-91a6-4f27-8187-e0c507694930-tape_poster.png);
    background-size: cover;
    background-repeat: no-repeat;
    background-blend-mode: difference;
  }
  
  .color-name-tape p {
    font-family: 'PermanentMarker-Regular';
    font-size: 1.7rem;
  }

  .color-info.container {
    overflow-y: scroll;
    width: 100%;
    padding: 10px 10px;
    height: auto;
  }

  .color-info.container div {
    width: 100%;
    margin-top: 8px;
  }

  .color-info.container div table {
    width: 100%;
  }

  .color-info.container div table tr td:nth-child(1) {
    text-align: left;
    font-weight: 600;
    font-size: 18px;
  }

  .color-info.container div table tr td:nth-child(2) {
    text-align: right;
    font-weight: 600;
    font-size: 15px;
    color: rgba(0,0,0, 0.5);
  }

  .color-info.container div div {
    width: 100%;
    height: 50px;
    border-radius: 5px;
    box-shadow: 3px 3px 10px 0px rgba(0,0,0,0.5);
  }

</style>