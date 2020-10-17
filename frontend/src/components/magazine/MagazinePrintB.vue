<template>
  <div ref="detail" class="magazine-template-b wrap">
    <div class="template-b-left">
      <div class="interview wrap">
        <div>
          <span class="question">{{content.question}}</span>
        </div>
        <div>
          <span class="answer">{{content.answer}}</span>
        </div>
      </div>
      <span :style="{'color' : rgb}">{{content.mainText}}</span>
    </div>
    <div class="template-b-right"  :style="{'background-color' : rgb}">
      <img :src="content.url">
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
    font-size: 30px;
    font-weight: 600;
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
