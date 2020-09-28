<template>
  <div>
    <!-- <transition name="fade" mode="out-in">
      <div v-if="loading" class="loading">loading</div>
    </transition> -->
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
          <p>Services</p>
        </div>
      </slide>
      <slide v-for="services of serviceLst" :key="services.id">
        <div class="bg" :style="'background-image: url(data:image/png;base64,' + services.img+')'">
          <div class="services-block">
            <div>
              <p class="services-block-title">{{services.title}}</p>

              <div class="services-block-content" v-if="productLst[services.id].length>0">
                <b-carousel
                  id="carousel-1"
                  :interval="4000"
                  controls
                  indicators
                  background="#ababab"
                  img-width="1024"
                  img-height="480"
                  style="text-shadow: 1px 1px 2px #333;"
                >
                  <router-link
                    v-for="products of productLst[services.id]"
                    :key="products.id"
                    :to="'/Products#'+products.id"
                  >
                    <b-carousel-slide
                      :img-src="'data:image/png;base64,'+products.img"
                      class="services-block-img"
                    ></b-carousel-slide>
                  </router-link>
                </b-carousel>
              </div>
              <br />
              <div class="services-block-info">{{services.description}}</div>
              <br />
              <input v-if="services.child == 1" type="button" value="read more" class="read-more" />
            </div>
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
        style="background-color: #aaa; border-radius: 10px;"
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
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
import Footer from "./Footer";
import Bottom from "./Bottom";
export default {
  name: "Services",
  components: {
    Hooper,
    Slide,
    HooperPagination,
    Footer,
    Bottom
  },
  data() {
    return {
      serviceLst: [],
      productLst: [],
      errors: [],
      hooperSettings: {
        itemsToShow: 2,
        itemsToSlide: 1,
        vertical: true,
        mouseDrag: false
      },
      loading: true
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
  mounted() {
    this.$axios
      .get(`http://17216110428888.opm.com.hk/services/getServiceList`)
      .then(response => {
        this.serviceLst = response.data;
        console.log("list: ", this.serviceLst);
        for (var i = 0; i < this.serviceLst.length; i++) {
          this.$axios
            .get(
              `http://17216110428888.opm.com.hk/products/getProductListByServiceId`,
              { params: { id: this.serviceLst[i].id } }
            )
            .then(response => {
              this.productLst[response.config.params.id] = response.data;
              console.log("repsonse : ", response.data);
              this.$forceUpdate();
            })
            .catch(e => {
              console.log(e);
              this.errors.push(e);
            })
            .finally(() => (this.loading = false));
        }
      })
      .catch(e => {
        console.log(e);
        this.errors.push(e);
      });
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
.s-banner {
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
.bg {
  background-image: url("../assets/3246023.jpg");
  background-position: top;
  height: 100vh;
  z-index: 1;
  background-color: hsla(0, 0%, 80%, 0.9);
  background-blend-mode: overlay;
  background-repeat: no-repeat;
  min-height: 70rem;
  color: #004d92;
  background-size: auto 90vh;
}
.services-block {
  align-items: center;
  padding-bottom: 50px;
  height: 100vh;
  display: flex;
  width: 70%;
  margin: 0 auto;
}
.services-block-title {
  font-weight: bold;
  font-size: 30px;
  text-shadow: 1px 1px 2px #779;
}
.services-block-info {
  font-size: 19px;
  text-shadow: 1px 1px 2px #779;
}
.services-block-content {
  /* text-align: left; */
  width: 60%;
  margin: 0 auto;
}
.services-block-img {
  height: 100%;
  /* width:200px */
}
.read-more {
  border: 0px;
  background-color: #3954a0;
  color: white;
  padding: 7px 20px;
  width: auto;
  float: right;
  margin-left: 0px;
}
.loading {
  background: lightslategray;
  height: 100vh;
  z-index: 100;
}
@media only screen and (min-width: 1920px) {
  .bg {
    background-size: 100%;
  }
}
@media only screen and (max-width: 500px) {
  .trans-pag {
    display: none;
  }
  .services-block {
    width: 90%;
  }
  .services-block-info {
    font-size: 14px;
  }
  .read-more {
    font-size: 14px;
  }
  .services-block-title {
    padding-bottom: 10px;
    font-size: 28px;
  }
  .services-block-content {
    width: 90%;
  }
}
</style>
