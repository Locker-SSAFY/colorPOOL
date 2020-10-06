<template>
  <div class="magazine-template-b wrap">
    <div class="template-b-left">
      <div class="interview wrap">
        <div>
          <span class="question">{{content.question}}</span>
        </div>
        <div>
          <span class="answer">{{content.answer}}</span>
        </div>
      </div>
      <span :style="{'background-color' : rgb}">{{content.mainText}}</span>
    </div>
    <div class="template-b-right"  :style="{'background-color' : rgb}">
      <img :src="content.url">
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
  .magazine-template-b.wrap {
    width: 100%;
    height: 100%;
    display: flex;
  }
  .template-b-left {
    width: 50%;
    height: 100%;
    float: left;
    background-color: white;
    color: black;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .template-b-left span {
    background: inherit;
    background-clip: text;
    color: transparent;
    font-size: 30px;
    font-weight: 600;
    filter: invert(1);
  }

  .template-b-right {
    width: 50%;
    height: 100%;
    float: right;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .template-b-right img {
    max-width: 80%;
    max-height: 80%;
    object-fit: contain;
  }

  .interview.wrap {
    position: absolute;
    top: 100px;
    height: 200px;
    width: 40%;
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