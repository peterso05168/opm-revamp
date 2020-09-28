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
          <p>About Us</p>
        </div>
      </slide>
      <slide>
        <div class="about-us">
          <div class="title">About us</div>
          <div class="desktop">
            <div class="about-us-album">
              <img
                v-for="aboutUs of aboutUsLst.imageList"
                :key="aboutUs.id"
                :src="'data:image/png;base64,'+aboutUs.img"
              />
            </div>
          </div>
          <div class="mobile">
            <b-carousel
              id="carousel-1"
              :interval="4000"
              controls
              indicators
              background="white"
              style="text-shadow: 1px 1px 2px #333;"
            >
              <div v-for="aboutUs of aboutUsLst.imageList" :key="aboutUs.id">
                <b-carousel-slide
                  style="height:100%"
                  :img-src="'data:image/png;base64,'+aboutUs.img"
                >
                  <!-- <img :src="'data:image/png;base64,'+aboutUs.img"/> -->
                </b-carousel-slide>
              </div>
            </b-carousel>
          </div>
          <div class="about-us-content" v-if="partnerLst.systemParam != undefined">
            <p v-for="desc of aboutUsLst.systemParam.value.split('*')" :key="desc">{{desc}}</p>
          </div>
        </div>
      </slide>
      <slide>
        <div class="bus">
          <div class="title">Business Partner</div>
          <div class="desktop">
            <div class="about-us-album">
              <img
                v-for="partner of partnerLst.imageList"
                :key="partner.id"
                :src="'data:image/png;base64,'+partner.img"
              />
            </div>
          </div>
          <div class="mobile">
            <b-carousel
              id="carousel-1"
              :interval="4000"
              controls
              indicators
              background="white"
              style="text-shadow: 1px 1px 2px #333;"
            >
              <div v-for="partner of partnerLst.imageList" :key="partner.id">
                <b-carousel-slide
                  style="height:100%"
                  :img-src="'data:image/png;base64,'+partner.img"
                ></b-carousel-slide>
              </div>
            </b-carousel>
          </div>
          <div class="about-us-content" v-if="partnerLst.systemParam != undefined">
            <p>{{partnerLst.systemParam.value}}</p>
          </div>
        </div>
      </slide>
      <slide>
        <Bottom />
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
import Bottom from "./Bottom";
export default {
  name: "AboutUs",
  components: {
    Hooper,
    Slide,
    HooperPagination,
    Footer,
    Bottom
  },
  data() {
    return {
      partnerLst: [],
      aboutUsLst: [],
      errors: [],
      hooperSettings: {
        itemsToShow: 2,
        itemsToSlide: 1,
        vertical: true,
        mouseDrag: false
      }
    };
  },
  mounted() {
    let url1 = this.$axios.get(
      `http://17216110428888.opm.com.hk/systemParams/getSystemParamWithImageList`,
      { params: { code: "ABTUS" } }
    );
    let url2 = this.$axios.get(
      `http://17216110428888.opm.com.hk/systemParams/getSystemParamWithImageList`,
      { params: { code: "PTR" } }
    );

    Promise.all([url1, url2])
      .then(response => {
        console.log("values: ", response);
        this.aboutUsLst = response[0].data;
        this.partnerLst = response[1].data;
        console.log("abt list: ", this.aboutUsLst);
      })
      .catch(e => {
        console.log(e);
        this.errors.push(e);
      });
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
.au-banner {
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
.about-us {
  display: flex;
  flex-direction: column;
  justify-content: center;
  height: 100%;
  /* background-color: ivory; */
  background-image:  url("../assets/about-us.jpg");
}

.bus {
  display: flex;
  flex-direction: column;
  justify-content: center;
  height: 100%;
  padding-bottom: 100px;
  /* background-color: white; */
  
  background-image:  url("../assets/about-us-2.jpg");
}
.title {
  font-size: 30px;
  padding: 25px;
  font-weight: bold;
  color: #004d92;
}
.about-us-album {
  /* display: grid; */
  grid-template-columns: repeat(4, 1fr);
  grid-auto-rows: minmax(50px, auto);
  width: 80%;
  margin: 0 auto;
  /* grid-gap: 10px; */
}
.about-us-album img {
  width: 350px;
  padding-right: 5px;
  padding-left: 5px;
}
.about-us-content {
  width: 50%;
  margin: 0 auto;
  padding-top: 30px;
}
.about-us-content p {
  color: #004d92;
}
@media only screen and (max-width: 500px) {
  .trans-pag {
    display: none;
  }
  .about-us,
  .bus {
    height: 90vh;
  }
  .about-us-content {
    font-size: 14px;
    width: 90%;
    padding: 0px;
  }
  .mobile {
    display: block;
    width: 90%;
    margin: 0 auto;
    padding-bottom: 30px;
  }
  .au-banner {
    font-size: 30px;
    background-size: 100%;

    background-repeat: no-repeat;
  }
}
</style>
