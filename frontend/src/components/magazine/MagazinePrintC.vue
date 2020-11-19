<template>
  <div ref="detail" class="magazine-template-c wrap" :style="{'background': 'linear-gradient(to bottom, ' + rgb + ', 50%, white 50% 100%)'}">
    <div class="template-c-left">
      <img :src="content.url">
    </div>
    <div class="template-c-right">
      <span :style="{'color' : rgb}">{{content.mainText}}</span>
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
import html2canvas from 'html2canvas'
export default {
  props: {
    content: {
      default: void 0
    },
    rgb : {
      default: void 0
    }
  },
  mounted() {
    this.downloadVisualReport();
  },
  methods: {
    showCaptureRef() {
      console.log("this.$refs.detail: " + this.$refs.detail);
      let vc = this;
      return vc.$refs.detail;
    },
    downloadVisualReport () {
      let vc = this
      // alert("Descargando reporte visual")
      var div = document.createElement("div");
      div.style.width = "100px";
      div.style.height = "200px";
      div.style.background = "white";
      
      console.log('campaign-view#downloadVisualReport');
      window.html2canvas = html2canvas;
      window.html2canvas(vc.showCaptureRef(),  { letterRendering: 1, allowTaint : true}).then(canvas => {
          document.body.appendChild(canvas)
          document.body.appendChild(div);
      }).catch((error) => {
        console.log("Erorr descargando reporte visual", error)
      });
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
    font-size: 30px;
    font-weight: 600;
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
