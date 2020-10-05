<template>
  <div class="container">
    <div class="post">
      <div class="avatar"></div>
      <div class="avatar"></div>
      <div class="avatar"></div>
      <div class="avatar"></div>
    </div>

    <div class="post">
      <div class="avatar"></div>
      <div class="avatar"></div>
      <div class="avatar"></div>
      <div class="avatar"></div>
    </div>

    <div class="post">
      <div class="avatar"></div>
      <div class="avatar"></div>
      <div class="avatar"></div>
      <div class="avatar"></div>
    </div>
  </div>
</template>

<script>
export default {

}
</script>

<style scoped>
  .container {
    width: 100%; height: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }

  .post {
    width: 100%;
    height: 250px;
  }
  .post .avatar {
    float: left;
    width: 150px;
    height: 200px;
    background-color: #ccc;
    border-radius: 5px;
    margin: 8px;
    background-image: linear-gradient(90deg, #ddd 0px, #e8e8e8 40px, #ddd 80px);
    background-size: 600px;
    animation: shine-avatar 1.6s infinite linear;
  }

  .post .avatar:nth-child(1) {
    height: 180px;
  }

  .post .avatar:nth-child(2) {
    height: 150px;
  }

  .post .avatar:nth-child(4) {
    height: 170px;
  }

  @keyframes shine-avatar {
    0% {
      background-position: -32px;
    }
    40%, 100% {
      background-position: 208px;
    }
  }
</style>