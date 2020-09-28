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
          <p>Home</p>
        </div>
      </slide>
      <slide>
        <div class="news">
          <div class="news-title">News</div>
          <div class="desktop">
            <div class="news-grid">
              <!-- <img v-for="news of newsLst" :key="news.id" :src="'data:image/png;base64,'+news.img" class="news-img" /> -->
              <div v-for="news of newsLst" :key="news.id" class="news-cell">
                <img :src="'data:image/png;base64,'+news.img" class="news-img" />

                <p class="news-text">
                  <strong>
                    <a :href="news.url" target="_blank">{{news.title}}</a>
                  </strong>
                </p>
                <p class="date-time">{{formateDateTime(news.createdDateTime)}}</p>
              </div>
            </div>
          </div>
          <div class="mobile">
            <b-carousel
              id="carousel-1"
              :interval="4000"
              background="white"
              style="text-shadow: 1px 1px 2px #333;"
            >
              <div v-for="news of newsLst" :key="news.id">
                <!-- <b-carousel-slide style="height:100%" :img-src="'data:image/png;base64,'+news.img"></b-carousel-slide> -->
                <b-carousel-slide style="height:40vh">
                  <img :src="'data:image/png;base64,'+news.img" class="news-img" />

                  <p class="news-text">
                    <strong>
                      <a :href="news.url" target="_blank">{{news.title}}</a>
                    </strong>
                  </p>
                  <p class="date-time">{{formateDateTime(news.createdDateTime)}}</p>
                </b-carousel-slide>
              </div>
            </b-carousel>
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
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
import Footer from "./Footer";
import Bottom from "./Bottom";
export default {
  name: "Home",
  components: {
    Hooper,
    Slide,
    HooperPagination,
    Footer,
    Bottom
  },
  data() {
    return {
      newsLst: [],
      errors: [],
      hooperSettings: {
        itemsToShow: 2,
        itemsToSlide: 1,
        vertical: true,
        mouseDrag: false
      }
    };
  },
  watch: {
    carouselData() {
      this.$refs.carousel.slideTo(this.carouselData);
    }
  },
  methods: {
    formateDateTime(dateTime) {
      var processedDate = new Date(dateTime);
      var date = processedDate.getDate();
      var month = processedDate.getMonth() + 1;
      var year = processedDate.getFullYear();
      var result = date + "/" + month + "/" + year;
      return result;
    },
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
      .get(`http://17216110428888.opm.com.hk/news/getNews`)
      .then(response => {
        this.newsLst = response.data;
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
.home-banner {
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
.news-text {
  text-align: center;
  padding: 10px 10px 0 10px;
  margin-bottom: 0px;
}
.news-text a {
  color: black;
  font-size: 17px;
}
.date-time {
  font-size: 13px;
  text-align: right;
  padding-right: 20px;
  margin-bottom: 5px;
}
.news {
  /* background-image: url("../assets/19366.jpg"); */
  background-image: url("../assets/news-bg.jpg");
  display: flex;
  flex-direction: column;
  justify-content: center;
  padding-bottom: 100px;
  /* background-color: #ffe; */
  height: 100vh;
  /* background-size: 100%; */
  /* padding-bottom: 40px; */
}
.news-title {
  font-size: 30px;
  /* padding: 1rem; */
  font-weight: bold;
  color: #004d92;
}
.news-grid {
  /* display: grid; */
  /* grid-template-columns: repeat(3, 1fr);
  grid-auto-rows: minmax(50px, auto); */
  /* min-width: 70%; */
  display: flex;
  justify-content: center;
  flex-flow: wrap;
  width: 80%;
  margin: 0 auto;
  /* grid-gap: 30px; */
}
.news-cell {
  background-color: white;
  box-shadow: 0px 5px 13px 0px #777;
  width: 400px;
  margin: 1rem;
  
}
.news-img {
  max-height: 12rem;
  max-width: 297px;
}
@media only screen and (max-width: 500px) {
  .trans-pag {
    display: none;
  }
  .news {
    display: flex;
    flex-direction: column;
    justify-content: center;
    padding-bottom: 100px;
  }
  .news-title {
    padding: 20px;
  }
  .mobile {
    display: block;
    width: 90%;
    margin: 0 auto;
    padding-bottom: 30px;
  }
  /deep/ .carousel-caption {
    position: unset !important;
    padding: 12px;
  }
  .date-time {
    color: black;
  }
}
</style>
