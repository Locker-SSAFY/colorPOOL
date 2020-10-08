<template>
  <div ref="detail" class="detail wrap">
    <v-carousel
      height="100%"
      width="100%"
      class="magazine-carousel wrap" 
      hide-delimiter-background
      delimiter-icon="mdi-minus"
      show-arrows-on-hover>
      <v-carousel-item
        v-for="(content, index) in magazine.contents" 
        :key="index"
        class="magazine-slide"
      >
        <TemplateA v-if="content.template == '0'" v-bind:content="content" v-bind:rgb="rgb"></TemplateA>
        <TemplateB v-if="content.template == '1'" v-bind:content="content" v-bind:rgb="rgb"></TemplateB>
        <TemplateC v-if="content.template == '2'" v-bind:content="content" v-bind:rgb="rgb"></TemplateC>
      </v-carousel-item>
    </v-carousel>
  </div>
</template>

<script>
import TemplateA from '../components/magazine/MagazineViewerA'
import TemplateB from '../components/magazine/MagazineViewerB'
import TemplateC from '../components/magazine/MagazineViewerC'
import {mapGetters, mapActions} from 'vuex'
import html2canvas from 'html2canvas'
// import jsPDF from 'jspdf' 
const detailStore = 'detailStore'
const headerStore = 'headerStore'

export default {
  name: 'Detail',
  components: {
    TemplateA, TemplateB, TemplateC
  },
  computed: {
    ...mapGetters(detailStore, {
      storeDetail: 'GE_DETAIL'
    })
  },
  created() {
    this.magazine = this.storeDetail[0];
    console.log(this.magazine);
    this.AC_HEADER(true);
    console.log('magazine', this.magazine)
    var theme = this.magazine.selectedColor.themes[0];
    console.log(theme);
    const r = parseInt((theme.red1 + theme.red2 + theme.red3 + theme.red4 + theme.red5) / 5);
    const g = parseInt((theme.green1 + theme.green2 + theme.green3 + theme.green4 + theme.green5) / 5);
    const b = parseInt((theme.blue1 + theme.blue2 + theme.blue3 + theme.blue4 + theme.blue5) / 5);
    this.rgb = 'rgb(' + r + ',' + g + ',' + b + ')';
  },
  async mounted() {
    // let el = this.$refs.detail.$el;
    // let options = {type: 'dataURL'};
    // let output = await html2canvas(el, options);
    // console.log(output);
    // await this.downloadVisualReport();
    
  },
  data() {
    return {
      magazine: null,
      rgb: '',
    }
  },
  methods: {
    ...mapActions(headerStore, ['AC_HEADER']),
    showCaptureRef() {
      console.log("this.$refs.detail: " + this.$refs.detail);
      let vc = this;
      return vc.$refs.detail;
    },
    downloadVisualReport () {
      let vc = this
      // alert("Descargando reporte visual")
      console.log('campaign-view#downloadVisualReport');
      window.html2canvas = html2canvas;
      window.html2canvas(vc.showCaptureRef(),  { letterRendering: 1, allowTaint : true}).then(canvas => {
        console.log(canvas);
        this.saveAs(canvas.toDataURL(), "capture.png");
        console.log("!!!!!!!!!!")
        const img = canvas.toDataURL("image/jpeg", 0.8);
        console.log(img);
        console.log('canvas', canvas);
        // document.body.appendChild(canvas)
        // doc.addImage(img,'JPEG',20,20);
        // doc.save("C:\\sample.pdf");
      }).catch((error) => {
        console.log("Erorr descargando reporte visual", error)
      });
    },
    saveAs(uri, filename) {
      var link = document.createElement('a');
      if (typeof link.download === 'string') {
        link.href = uri;
        link.download = filename;
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
      } else {
        window.open(uri);
      }
    },
  }
}
</script>

<style scoped>
  .detail.wrap {
    width: 100vw;
    height: 100vh;
  }
</style>