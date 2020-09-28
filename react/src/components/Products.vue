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
      <!-- <slide>
        <div style="height: 60%" />
        <div class="banner" style="height: 40%">
          <p>- Products -</p>
        </div>
      </slide>-->
      <slide>
        <div style="height: 60%" />
        <div class="banner" style="height: 40%">
          <p>Products</p>
        </div>
      </slide>
      <slide v-for="products of productLst" :key="products.id">
        <div
          class="bg desktop"
          :style="'background-image: url(data:image/png;base64,' + products.img+')'"
          :id="products.id"
        >
          <div class="title">{{products.title}}</div>
          <div class="grid">
            <div class="cell">
              <div class="feature">
                <p>Feature:</p>
                <ul v-for="feature of products.feature.split('*')" :key="feature">
                  <li>{{feature}}</li>
                </ul>
              </div>
              <br />
              <div class="benefit">
                <p>Benefit:</p>
                <ul v-for="benefit of products.benefit.split('*')" :key="benefit">
                  <li>{{benefit}}</li>
                </ul>
              </div>
              <br />
            </div>
            <div class="cell-right">
              <img :src="'data:image/png;base64,' + products.img" />
              <br />
              <br />
              <p>{{products.description}}</p>
            </div>
          </div>
        </div>
        <div
          class="bg mobile"
          :style="'background-image: url(data:image/png;base64,' + products.img+')'"
          :id="products.id"
        >
          <div class="title">{{products.title}}</div>
        </div>
        <b-carousel
          id="carousel-1"
          :interval="4000"
          indicators
          background="#fff"
          class="cell mobile"
        >
          <b-carousel-slide class="slide">
            <div class="feature">
              <p>Feature:</p>
              <ul v-for="feature of products.feature.split('*')" :key="feature">
                <li>{{feature}}</li>
              </ul>
            </div>
          </b-carousel-slide>
          <b-carousel-slide class="slide">
            <div class="benefit">
              <p>Benefit:</p>
              <ul v-for="benefit of products.benefit.split('*')" :key="benefit">
                <li>{{benefit}}</li>
              </ul>
            </div>
          </b-carousel-slide>
        </b-carousel>
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
  name: "Products",
  components: {
    Hooper,
    Slide,
    HooperPagination,
    Footer,
    Bottom
  },

  data() {
    return {
      productLst: [],
      hooperSettings: {
        itemsToShow: 2,
        itemsToSlide: 1,
        vertical: true,
        mouseDrag: false
      }
    };
  },
  async beforeCreate() {
    await this.$axios
      .get(`http://17216110428888.opm.com.hk/products/getProductList`)
      .then(response => {
        this.productLst = response.data;
      })
      .catch(e => {
        console.log(e);
        this.errors.push(e);
      });
  },
  beforeRouteEnter(to, from, next) {
    var counter = 0;
    next(vm => {
      setTimeout(() => {
        for (var i = 0; i < vm.productLst.length; i++) {
          counter++;
          if (vm.productLst[i].id == to.hash.replace("#", "")) {
            vm.$refs.carousel.slideTo(counter);
            break;
          }
        }
      }, 500);
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
.product-banner {
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
.benefit li:before {
  content: "\2714";
  margin-left: -20px;
  margin-right: 10px;
  font-size: 20px;
  color: rgb(42, 221, 6);
}
.feature li:before {
  content: "\22B3";
  margin-left: -20px;
  margin-right: 10px;
  /* font-size: 13px; */
}
.cell li {
  list-style-type: none;
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
.title {
  font-size: 34px;
  padding-bottom: 25px;
  padding-top: 70px;
  font-weight: bold;
  color: #004d92;
}
.bg {
  background-image: url("../assets/3246023.jpg");
  background-size: auto 90vh;
  background-position: top;
  height: 100vh;
  z-index: 1;
  background-color: hsla(0, 0%, 100%, 0.9);
  background-blend-mode: overlay;
  background-repeat: no-repeat;
  color: #004d92;
}
.grid {
  min-height: 40vh;
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  grid-auto-rows: minmax(50px, auto);
  width: 80%;
  margin: 0 auto;
  margin-bottom: 100px;
  grid-gap: 20px;
}
.grid img {
  width: 80%;
}
.cell {
  width: 80%;
  text-align: left;
  margin: 0 auto;
}
.cell p {
  font-size: 30px;
  color: rgb(238, 28, 0);
}
.cell-right {
  align-items: center;
  display: flex;
  flex-direction: column;
  justify-content: center;
}
.cell-right p {
  font-size: 13px;
}
@media only screen and (min-width: 1920px) {
  .bg {
    background-size: 100%;
  }
}
@media only screen and (min-width: 768px) {
  .grid {
    width: 90%;
  }
}
@media only screen and (max-width: 500px) {
  .trans-pag {
    display: none;
  }
  .bg {
    height: 30vh;
    flex-direction: column;
    justify-content: center;
    background-color: hsla(0, 0%, 80%, 0.9);
    background-size: 100%;
  }
  .title {
    padding: 0;
  }
  .cell {
    width: 100%;
  }
  .feature,
  .benefit {
    color: #004d92;
    text-align: left;
    font-size: 14px;
    width: 100%;
  }
  .feature p,
  .benefit p {
    margin: unset;
    font-size: 20px;
  }
  .slide {
    height: 60vh;
  }
  /deep/ .carousel-caption {
    position: unset !important;
    padding: 12px;
  }
}
</style>
