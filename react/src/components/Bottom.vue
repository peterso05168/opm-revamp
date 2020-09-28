<template>
  <div class="bottom" style="background-color: white;">
    <div class="bottom-left">
      <div class="bottom-right">
        <p class="line">_______</p>
        <p class="line-1">WE PARTNER FOR A BRIGHT FUTURE</p>
        <p class="line-2">with blockchain and technology</p>
        <p class="line-3">Ask more on how OPM enhances your business</p>
        <router-link to="/ContactUs">
          <div class="line-4" @click="toggleClass('contactUs')">Contact Us</div>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Bottom",
  data() {
    return {};
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.bottom {
  /* background-image: url("../assets/3246023.jpg"); */
  height: 100vh;
  background-image: url("../assets/contact-us-bg-2.jpg");
  /* background-size: 50%; */
  /* display: block; */
  z-index: 1;
  /* background-color: hsla(0, 0%, 100%, 0.9);
  background-blend-mode: overlay; */
  height: 100vh;
}

.bottom-left {
  /* background-image: url("../assets/work-1-09.png");
  background-repeat: no-repeat;
  z-index: 101; */
}
.bottom-right {
  /* background-image: url("../assets/work-1-10.png");
  background-repeat: no-repeat;
  z-index: 100;
  background-position: right;
  background-position-y: 80%;
  height:100vh; */
}
.line {
  padding-top: 300px;
  padding-bottom: 10px;
  color: darkred;
}
.line-1 {
  text-align: center;
  font-weight: bold;
  font-size: 30px;
  color: #004d92;
}
.line-2 {
  text-align: center;
  font-size: 18px;
  color: #004d92;
  margin-top: -18px;
}
.line-3 {
  text-align: center;
  font-size: 18px;
  color: grey;
}

.line-4:after {
  color: #004d92;
  border-right: 23.5px solid;
  border-top: 23.5px solid transparent;
  border-bottom: 23.5px solid transparent;
  display: inline-block;
  content: "";
  position: absolute;
  left: -23.5px;
  top: 0px;
}
.line-4:before {
  color: #004d92;
  border-left: 23.5px solid;
  border-top: 23.5px solid transparent;
  border-bottom: 23.5px solid transparent;
  display: inline-block;
  content: "";
  position: absolute;
  right: -23.5px;
  top: 0px;
}
.line-4 {
  text-align: center;
  font-size: 18px;
  color: white;
  width: 100px;
  background-color: #004d92;
  margin: auto;
  margin-top: 100px;
  width: 50%;
  padding: 10px;
  position: relative;
  cursor: pointer;
}
@media only screen and (max-width: 500px) {
  .bottom {
    background-repeat: repeat;
    display: flex;
    flex-direction: column;
    justify-content: center;
    padding-bottom: 30%;
  }
  .line {
    padding-top: 0px;
  }
  .line-1 {
    font-size: 21px;
  }
  .line-2 {
    font-size: 14px;
  }
  .line-3 {
    font-size: 14px;
  }
  .line-4 {
    margin-top: 80px;
  }
}
</style>
