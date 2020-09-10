<template>
  <v-dialog v-model="dialog" scrollable max-width="300px">
        <template v-slot:activator="{ on, attrs }">
          <div class="show-selected-tone">{{selectedTone}}</div>
          <div class="button-text right">
            <v-btn
              icon
              text
              class="mt-1"
              v-bind="attrs"
              v-on="on"
            >
              <v-icon>mdi-plus</v-icon>
              톤 선택 옵션
            </v-btn>
          </div>
        </template>
        <v-card>
          <v-card-title>Select Tone</v-card-title>
          <v-divider></v-divider>
          <v-card-text style="height: 300px;">
            <v-radio-group v-model="selectedTone" column>
              <v-radio label="vivid" value="vivid"></v-radio>
              <v-radio label="strong" value="strong"></v-radio>
              <v-radio label="bright" value="bright"></v-radio>
              <v-radio label="greyish" value="greyish"></v-radio>
              <v-radio label="pale" value="pale"></v-radio>
              <v-radio label="dull" value="dull"></v-radio>
              <v-radio label="dark" value="dark"></v-radio>
              <v-radio label="deep" value="deep"></v-radio>
              <v-radio label="soft" value="soft"></v-radio>
              <v-radio label="mono" value="mono"></v-radio>
            </v-radio-group>
          </v-card-text>
          <v-divider></v-divider>
          <v-card-actions>
            <v-btn color="blue darken-1" text @click="dialog = false">Close</v-btn>
            <v-btn color="blue darken-1" text @click="dialog = false">Save</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
</template>
<script>
export default {
  name: 'ThemeScroll',
  computed: {
  },
  data () {
    return {
      selectedTone: '',
      dialog: false,
    }
  },
  methods : {
  }
}
</script>

<style scoped>
  .show-selected-tone{
    position: absolute;
    right: 22%;
    top:-0.3%;
    font-size: 1.17rem;
    font-weight: bold;
    margin-top: 0.7rem;
  }
  .button-text.right .v-btn{
    position: absolute;
    right: 8%;
    top:-0.3%;
    font-size: 1.17rem;
    font-weight: bold;
  }
</style>