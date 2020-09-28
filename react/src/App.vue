<template>
  <div id="app">
    <div>
      <b-navbar toggleable="lg" type="light" variant="faded" :sticky="true" class="topnav">
        <b-navbar-brand class="topnav-left" @click="highlight('/Home')">
          <router-link to="/Home">
            <img src="./assets/logo.jpg" class="a-img" />
          </router-link>
        </b-navbar-brand>
        <b-navbar-toggle target="nav-collapse" @click="flip()" id="icon"></b-navbar-toggle>
        <b-collapse id="nav-collapse" is-nav>
          <b-navbar-nav class="ml-auto topnav-right">
            <b-nav-item class="top-item" @click="highlight('/AboutUs')">
              <router-link to="/AboutUs" class="underline" id="aboutUs">About Us</router-link>
            </b-nav-item>
            <b-nav-item class="top-item" @click="highlight('/Products')">
              <router-link to="/Products" class="underline" id="products">Products</router-link>
            </b-nav-item>
            <b-nav-item class="top-item" @click="highlight('/Services')">
              <router-link to="/Services" class="underline" id="services">Services</router-link>
            </b-nav-item>
            <b-nav-item class="top-item" @click="highlight('/JoinUs')">
              <router-link to="/JoinUs" class="underline" id="joinUs">Join Us</router-link>
            </b-nav-item>
            <b-nav-item class="top-item" @click="highlight('/ContactUs')">
              <router-link to="/ContactUs" class="underline" id="contactUs">Contact Us</router-link>
            </b-nav-item>
          </b-navbar-nav>
        </b-collapse>
      </b-navbar>
    </div>
    <!-- <div>
      <img
        src="./assets/hot-air-balloon.png"
        class="top-button"
        @click="goToTop()"
        title="Back to Top"
      />
    </div>-->

    <div class="content">
      <router-view></router-view>
    </div>
  </div>
</template>
<script src='https://cdn.jsdelivr.net/npm/vue'></script>
<script src="https://unpkg.com/vue/dist/vue.js"></script>
<script src="https://unpkg.com/vue-router/dist/vue-router.js"></script>
<script src="lodash.js"></script>
<script>
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";

export default {
  name: "app",
  data() {
    return {
      newsLst: [],
      errors: [],
      itemLst: ["aboutUs", "products", "services", "joinUs", "contactUs"],
      flipped: false
    };
  },
  updated() {
    this.highlight(this.$router.currentRoute.path);

  },
  methods: {
    highlight(index) {
      switch (index) {
        case "/Home":
          for (var i = 0; i < this.itemLst.length; i++) {
            document
              .getElementById(this.itemLst[i])
              .classList.remove("highlight");
          }
          if (this.flipped) {
            document.getElementById("icon").click();
            // this.flipped = !this.flipped;
          }

          break;
        case "/AboutUs":
          document.getElementById("aboutUs").classList.add("highlight");

          for (var i = 0; i < this.itemLst.length; i++) {
            if (this.itemLst[i] != "aboutUs") {
              document
                .getElementById(this.itemLst[i])
                .classList.remove("highlight");
            }
          }
          break;
        case "/Products":
          document.getElementById("products").classList.add("highlight");

          for (var i = 0; i < this.itemLst.length; i++) {
            if (this.itemLst[i] != "products") {
              document
                .getElementById(this.itemLst[i])
                .classList.remove("highlight");
            }
          }
          break;
        case "/Services":
          document.getElementById("services").classList.add("highlight");

          for (var i = 0; i < this.itemLst.length; i++) {
            if (this.itemLst[i] != "services") {
              document
                .getElementById(this.itemLst[i])
                .classList.remove("highlight");
            }
          }
          break;
        case "/JoinUs":
          document.getElementById("joinUs").classList.add("highlight");

          for (var i = 0; i < this.itemLst.length; i++) {
            if (this.itemLst[i] != "joinUs") {
              document
                .getElementById(this.itemLst[i])
                .classList.remove("highlight");
            }
          }
          break;
        case "/ContactUs":
          document.getElementById("contactUs").classList.add("highlight");

          for (var i = 0; i < this.itemLst.length; i++) {
            if (this.itemLst[i] != "contactUs") {
              document
                .getElementById(this.itemLst[i])
                .classList.remove("highlight");
            }
          }
          break;
      }
      if (index != "/Home") {
        this.flip();
      }
    },
    goToTop() {
      document.body.scrollTop = 0;
      document.documentElement.scrollTop = 0;
    },
    expandMenu(page) {
      switch (page) {
        case "home":
          this.isActiveHome = true;
          break;
        case "aboutUs":
          this.isActiveAboutUs = true;
          break;
        case "products":
          this.expandProducts = !this.expandProducts;
          break;
        case "services":
          this.isActiveServices = true;
          break;
        case "solutions":
          this.isActiveSolutions = true;
          break;
        case "joinUs":
          this.isActiveJoinUs = true;
          break;
        case "contactUs":
          this.isActiveContactUs = true;
          break;
      }
    },
    handleOver(name) {
      var x = document.getElementById(name);
      x.style.display = "block";
    },
    handleLeave(name) {
      var x = document.getElementById(name);
      x.style.display = "none";
    },
    flip() {
      var x = document.getElementsByClassName("navbar-toggler-icon");
      if (!this.flipped) {
        x[0].classList.add("flip");
      } else {
        x[0].classList.remove("flip");
      }
      this.flipped = !this.flipped;
    }
  }
};
</script>

<style>
#app {
  font-family: Century gothic;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  -webkit-touch-callout: none; /* iOS Safari */
  -webkit-user-select: none; /* Safari */
  -khtml-user-select: none; /* Konqueror HTML */
  -moz-user-select: none; /* Old versions of Firefox */
  -ms-user-select: none; /* Internet Explorer/Edge */
  user-select: none; /* Non-prefixed version, currently supported by Chrome, Edge, Opera and Firefox */
  width: 100%;
  scroll-behavior: smooth;
}
@media only screen and (min-width: 768px) {
  html {
    overflow: -moz-hidden-unscrollable;
    height: 100%;
  }
  body::-webkit-scrollbar {
    display: none;
  }
  body {
    -ms-overflow-style: none;
    height: 100%;
    width: calc(100vw + 18px);
    overflow: auto;
  }
}
.banner {
  /* width: 100%;
  background: linear-gradient(135deg, #fa8b78 50%, #6a81e9 50%);
  color: white;
  height: 50%;
  display: flex;
  font-size: 70px;
  font-weight: bold;
  justify-content: center;
  flex-direction: column;
  text-shadow: 1px 1px 2px #333; */
   width: 100%;
   background-image: url("./assets/banner-3-template.jpg");
  color: black;
  height: 50%;
  display: flex;
  font-size: 70px;
  font-weight: bold;
  justify-content: center;
  flex-direction: column;
  
  background-repeat: no-repeat;
}
.topnav {
  background-color: #fff;
  position: fixed;
  width: 100%;
  box-shadow: 0px 2px 10px 0px #ddd;
  min-height: 10.3vh;
  z-index: 1;
}
.topnav img {
  height: 60px;
}
.topnav a {
  color: #020202;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 19px;
}
.topnav a:hover {
  color: #004d92;
  font-weight: bold;
}
.underline {
  display: inline;
  position: relative;
  overflow: hidden;
}
.underline:after {
  content: "";
  position: absolute;
  z-index: -1;
  right: 0;
  width: 0;
  bottom: 0px;
  background: linear-gradient(90deg, #fa8b78 50%, #6a81e9 50%);
  height: 4px;
  transition-property: width;
  transition-duration: 0.3s;
  transition-timing-function: ease-out;
}
.underline:hover:after {
  left: 0;
  right: auto;
  width: 100%;
}
.topnav-right {
  margin-right: 15%;
  width: max-content;
}
.topnav-left {
  margin-left: 15%;
}
.content {
  margin-top: 10.3vh;
  /* min-height: 30vh; */
}
.hidden {
  display: none;
}

.top-button {
  position: fixed;
  width: 5rem;
  left: 0;
  margin-top: 15px;
  margin-left: 10px;
  -webkit-transform: scaleX(-1);
  transform: scaleX(-1);
  cursor: pointer;
}
.top-button:hover {
  margin-left: 6px;
  margin-top: 12px;
  width: 5.5rem;
}

.mobile {
  display: none;
}
.desktop {
  display: block;
}
@media only screen and (max-width: 500px) {
  .highlight {
    display: inline;
    position: relative;
    overflow: hidden;
  }
  .highlight:after {
    content: "";
    position: absolute;
    z-index: -1;
    bottom: -10px;
    background: linear-gradient(90deg, #fa8b78 50%, #6a81e9 50%);
    height: 4px;
    width: 100%;
  }
  .desktop {
    display: none;
  }
  .mobile {
    display: flex;
  }
  html {
    overflow: hidden;
  }
  body {
    overflow: hidden;
    position: relative;
    margin: 0;
    padding: 0;
  }
  .topnav {
    min-height: 86px;
    max-height: 100vh;
  }
  .topnav img {
    height: 60px;
  }
  .topnav a {
    padding: 0;
  }
  .topnav-right,
  .topnav-left {
    margin: 0;
    width: unset;
  }
  .banner {
    background-image: url("./assets/mobile-banner.jpg");
    font-size: 40px;
    background-size: cover;
  }
  .content {
    margin-top: 86px;
  }
  .top-item {
    padding: 10px 0;
  }
  .navbar-light .navbar-toggler-icon {
    background-image: url("./assets/down-arrow.png");
    transition-duration: 0.6s;
  }
  .flip {
    -webkit-transform: scaleY(-1);
    transform: scaleY(-1);
    transition-duration: 0.3s;
  }
}
</style>

