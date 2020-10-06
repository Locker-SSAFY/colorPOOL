<template>
  <div class="magazine-template-c wrap" :style="{'background': 'linear-gradient(to bottom, ' + rgb + ', 50%, white 50% 100%)'}">
    <div class="template-c-left">
      <img :src="content.url">
    </div>
    <div class="template-c-right">
      <span :style="{'background-color' : rgb}">{{content.mainText}}</span>
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
  .magazine-template-c.wrap {
    width: 100%;
    height: 100%;
    display: flex;
  }

  .template-c-left {
    width: 50%;
    height: 100%;
    float: left;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .template-c-left img {
    max-width: 80%;
    max-height: 80%;
    object-fit: contain;
  }

  .template-c-right {
    width: 50%;
    height: 100%;
    float: right;
  }

  .template-c-right span {
    background: inherit;
    background-clip: text;
    color: transparent;
    font-size: 30px;
    font-weight: 600;
    filter: invert(1);
    position: absolute;
    top: calc(50% - 60px);
  }

  .interview.wrap {
    margin-top: calc(50% + 60px);
    height: 200px;
    width: 80%;
    margin-left: 10%;
    display: flex;
    flex-direction: column;
    justify-content: center;
  }

  .interview.wrap span{
    color: black;
    font-size: 12px;
    filter: none;
    position: static;
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