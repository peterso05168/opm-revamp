<template>
  <div>
    <hooper
      :settings="hooperSettings"
      class="abc trans"
      ref="carousel"
      :transition="480"
      @slide="handleScroll"
      style="height: 180vh"
      id="hooper1"
    >
      <slide>
        <div style="height: 60%" />
        <div class="banner" style="height: 40%">
          <p>Contact Us</p>
        </div>
      </slide>
      <slide>
        <!-- <div class="bg"> -->
        <div class="bottom">
          <div class="bottom-left">
            <div class="bottom-right">
              <div class="desktop">
                <div>
                  <p class="line">_______</p>
                  <p class="line-1">WE PARTNER FOR A BRIGHT FUTURE</p>
                  <p class="line-2">with blockchain and technology</p>
                  <p class="line-3">Ask more on how OPM enhances your business</p>
                  <p class="line">_______</p>
                </div>
                <div class="contact-us-body">
                  <div class="contact-us-word">
                    <p class="title">CONTACT US</p>
                    <p>
                      Unit 1904, 19/F,
                      <br />Global Gateway Tower,
                      <br />63 Wing Hong Street,
                      <br />Cheung Sha Wan
                    </p>
                    <br />
                    <table>
                      <tr>
                        <td class="index">Tel:</td>
                        <td>(852) 3743 8900</td>
                      </tr>
                      <tr>
                        <td class="index">Fax:</td>
                        <td>(852) 3743 8901</td>
                      </tr>
                      <tr>
                        <td class="index">Email:</td>
                        <td>info@opm.com.hk</td>
                      </tr>
                    </table>
                  </div>

                  <div class="map">
                    <img src="../assets/map.jpg" />
                  </div>
                </div>
              </div>

              <div class="mobile">
                <div class="map">
                  <img src="../assets/map.jpg" />
                </div>
                <div class="contact-us-word">
                  <table class="contact-us-table">
                    <tr>
                      <td class="index">
                        <img src="../assets/location.png" />
                      </td>
                      <td>Unit 1904, 19/F,</td>
                    </tr>
                    <tr>
                      <td class="index"></td>
                      <td>Global Gateway Tower,</td>
                    </tr>
                    <tr>
                      <td class="index"></td>
                      <td>63 Wing Hong Street,</td>
                    </tr>
                    <tr>
                      <td class="text-underline index"></td>
                      <td class="text-underline" style="width: 100vw;">Cheung Sha Wan</td>
                    </tr>

                    <tr>
                      <td class="text-underline index">
                        <img src="../assets/phone.png" />
                      </td>
                      <td class="text-underline" style="width: 100vw;">(852) 3743 8900</td>
                    </tr>
                    <tr>
                      <td class="text-underline index">
                        <img src="../assets/fax.png" />
                      </td>
                      <td class="text-underline" style="width: 100vw;">(852) 3743 8901</td>
                    </tr>
                    <tr>
                      <td class="text-underline index">
                        <img src="../assets/mail.png" />
                      </td>
                      <td class="text-underline" style="width: 100vw;">info@opm.com.hk</td>
                    </tr>
                  </table>
                </div>
                <br />
              </div>
            </div>
          </div>
        </div>
      </slide>
      <slide>
        <Footer />
      </slide>
      <hooper-pagination
        style="background-color: #aaa; border-radius: 10px"
        slot="hooper-addons"
        class="trans-pag"
        id="pag"
      ></hooper-pagination>
    </hooper>
  </div>
</template>

<script>
import { Hooper, Slide, Pagination as HooperPagination } from "hooper";
import "hooper/dist/hooper.css";
import Footer from "./Footer";
export default {
  name: "ContactUs",
  components: {
    Hooper,
    Slide,
    HooperPagination,
    Footer
  },
  data() {
    return {
      hooperSettings: {
        itemsToShow: 2,
        itemsToSlide: 1,
        vertical: true,
        mouseDrag: false
      }
    };
  },
  methods: {
    handleScroll(payload) {
      console.log("payload: ", payload);
      if (payload.currentSlide != 0) {
        document.getElementById("hooper1").style.transform = "translateY(0vh)";
        document.getElementById("pag").style.transform = "translateY(-54vh)";
      } else {
        document.getElementById("hooper1").style.transform =
          "translateY(-54vh)";
        document.getElementById("pag").style.transform = "translateY(-100vh)";
      }
    },
    preventMotion(event) {
      window.scrollTo(0, 0);
      event.preventDefault();
      event.stopPropagation();
    }
  },

  created() {
    window.addEventListener("scroll", this.preventMotion);
    window.addEventListener("touchmove", this.preventMotion);
  },
  destroyed() {
    window.removeEventListener("scroll", this.preventMotion);
    window.removeEventListener("touchmove", this.preventMotion);
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.contact-banner {
  background-image: url("../assets/banner-3-template.jpg");
  color: black;
  height: 50%;
  display: flex;
  font-size: 70px;
  font-weight: bold;
  justify-content: center;
  flex-direction: column;
  /* text-shadow: 1px 1px 2px #333; */
}
.bottom {
  /* background-image: url("../assets/3246023.jpg"); */
  height: 100vh;
  background-image: url("../assets/contact-us-bg-2.jpg");
  background-size: 100%;
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
.trans {
  transform: translateY(-54vh);
  transition-duration: 0.5s;
}
.trans-pag {
  transform: translateY(-100vh);
  transition-duration: 0.5s;
  right: 30px;
}
.abc {
  height: 90vh;
}
.bg {
  background-image: url("../assets/3246023.jpg");
  background-size: 100%;
  display: block;
  z-index: 1;
  background-color: hsla(0, 0%, 100%, 0.9);
  /* background: hsla(0, 0%, 100%, 0.9); */
  background-blend-mode: overlay;
  background-repeat: no-repeat;

  height: 100vh;
}

.index {
  width: 5rem;
  font-weight: bold;
  color: #004d92;
}
.contact-us-body {
  display: flex;
  margin-left: 20%;
  margin-right: 20%;
}
.contact-us-word {
  width: 50%;
  text-align: left;
  font-size: 21px;
  margin-top: 5%;
}
.title {
  font-size: 36px;
  font-weight: bold;
  color: #004d92;
}

.map {
  width: 50%;
  margin-top: 5%;
}
.map img {
  width: 100%;
}

.line {
  padding: 20px;
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
@media only screen and (max-width: 500px) {
  .trans-pag {
    display: none;
  }
  .bg {
    background-repeat: repeat;
    display: flex;
    background: white;
  }
  .mobile {
    flex-direction: column;
    margin: 0 auto;
    width: 90%;
  }
  .contact-us-word {
    font-size: 15px;
    width: 100%;
    color: #004d92;
  }
  .title {
    text-align: center;
    font-size: 30px;
    padding: 10px;
  }
  .map {
    width: 100%;
    padding: 35px 0;
  }
  .index {
    width: 3rem;
  }
  .index img {
    width: 1.1rem;
  }
  .text-underline {
    border-bottom: 1pt solid lightslategray;
  }
}
</style>
