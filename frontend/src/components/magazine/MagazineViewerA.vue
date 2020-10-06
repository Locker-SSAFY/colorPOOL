<template>
  <div class="magazine-template-a wrap">
    <div class="template-a-left" :style="{'background-color' : rgb}">
      <img :src="content.url">
    </div>
    <div class=template-a-right>
      <span :style="{'background-color' : rgb}">{{content.mainText}}</span>
      <br><br>
      <h3>{{content.subText}}</h3>
      <div class="interview wrap">
        <div>
          <span class="question">{{content.question}}</span>
        </div>
        <div>
          <span class="answer">{{content.answer}}</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    content: {
      default: void 0
    },
    rgb : {
      default: void 0
    }
  }
}
</script>

<style scoped>
  .magazine-template-a.wrap {
    width: 100%;
    height: 100%;
    display: flex;
  }
  .template-a-left {
    width: 50%;
    height: 100%;
    float: left;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .template-a-left img {
    max-width: 80%;
    max-height: 80%;
    object-fit: contain;
  }

  .template-a-right {
    width: 50%;
    height: 100%;
    float: right;
    background-color: white;
    color: black;
  }
  .template-a-right span {
    background: inherit;
    background-clip: text;
    color: transparent; 
    font-size: 30px;
    font-weight: 600;
    filter: invert(1);
    margin-left: -100px;
  }

  .interview.wrap {
    margin-top: calc(50% - 200px);
    height: 200px;
    width: 80%;
    margin-left: 10%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    /* align-items: center;  */
  }

  .interview.wrap span{
    color: black;
    font-size: 12px;
    filter: none;
    margin-left: 0;
  }

  .interview.wrap div:nth-child(1) {
    text-align: center;
    width: 100%;
    float: top;
  }
  .interview.wrap div:nth-child(2) {
    text-align: center;
    width: 100%;
    float: bottom;
  }

  .interview.wrap .question {
    font-size: 15px;
  }

  .interview.wrap .answer {
    color: slategray;
  }
</style>