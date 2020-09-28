<template>
  <div>
    <div class="banner" style="height: 350px">
      <p>Join Us</p>
    </div>
    <div class="join-us">
      <!-- <div class="title">Join Us</div> -->
      <div class="job-list" v-for="job of jobLst" :key="job.id">
        <div class="job-title" @click="toggleOpen(job.id)">
          {{job.jobTitle}}
          <img :id="job.id" class="arrow" src="../assets/down-arrow.png" />
        </div>
        <div class="job-content" :id="'content'+job.id">
          <p class="content-title desktop">{{job.jobTitle}}</p>
          <p>Responsibilities:</p>
          <ul v-for="responsibilities of job.responsibilities.split('*')" :key="responsibilities">
            <li>{{responsibilities}}</li>
          </ul>
          <br />
          <p>Requirements:</p>
          <ul v-for="requirements of job.requirements.split('*')" :key="requirements">
            <li>{{requirements}}</li>
          </ul>
          <div class="desktop">
            <input
              class="submit job-apply"
              @click="toggleOpenApply(job.id)"
              :id="'apply'+job.id"
              type="button"
              value="apply"
            />
          </div>
          <!-- <div class="mobile">
            <img class="mobile-arrow" src="../assets/down-arrow.png" @click="back(job.id)"/>
          </div> -->
          <div class="form" :id="'form'+job.id">
            <form @submit="handleSubmit(job.id)" :id="'applyForm'+job.id">
              <input type="text" placeholder="name" :id="'name'+job.id" />
              <input type="email" placeholder="email" :id="'email'+job.id" />
              <br />
              <input type="text" placeholder="phone" :id="'phone'+job.id" />
              <br />
              <input class="file" type="file" :id="'file' + job.id" />
              <input class="submit" type="submit" value="send" />
              <input class="submit back" type="reset" value="back" @click="back(job.id)" />
            </form>
          </div>
        </div>
      </div>
    </div>
    <Footer />
  </div>
</template>

<script>
import Footer from "./Footer";
export default {
  name: "JoinUs",
  components: {
    Footer
  },
  data() {
    return {
      jobLst: [],
      errors: []
    };
  },
  methods: {
    toggleOpen(id) {
      if (document.getElementById(id).className == "arrow active") {
        this.back(id);
      } else {
        document.getElementById(id).className = "arrow active";
        document.getElementById("content" + id).style.display = "block";
      }
    },
    toggleOpenApply(id) {
      document.getElementById("form" + id).style.display = "flex";
      document.getElementById("apply" + id).style.display = "none";
    },
    back(id) {
      document.getElementById(id).className = "arrow";
      document.getElementById("content" + id).style.display = "none";
      document.getElementById("form" + id).style.display = "none";
      document.getElementById("apply" + id).style.display = "block";
    },
    handleSubmit(id) {
      const formData = new FormData();
      formData.append("name", document.getElementById("name" + id).value);
      formData.append("email", document.getElementById("email" + id).value);
      formData.append("phone", document.getElementById("phone" + id).value);
      formData.append("resume", document.getElementById("file" + id).files[0]);
      formData.append("jobId", id);
      this.$axios
        .post(`http://17216110428888.opm.com.hk/job/applyJob`, formData)
        .then(response => {
          if (response.data.status) {
            alert("Submit successfully");
          }
        })
        .catch(e => {
          console.log(e);
          this.errors.push(e);
        });
      document.getElementById("applyForm" + id).reset();
      this.back(id);
    }
  },
  mounted() {
    document.documentElement.style.overflow = "scroll";
    this.$axios
      .get(`http://17216110428888.opm.com.hk/job/getCurrentJobVacancies`)
      .then(response => {
        this.jobLst = response.data;
        console.log("list: ", this.jobLst);
      })
      .catch(e => {
        console.log(e);
        this.errors.push(e);
      });
  },
  destroyed() {
    document.documentElement.style.overflow = "hidden";
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.join-banner {
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
p {
  font-weight: bold;
}
.title {
  font-size: 30px;
  padding-bottom: 25px;
  padding-top: 70px;
  font-weight: bold;
  color: #004d92;
}
.join-us {
  background-image: url("../assets/3246023.jpg");
  background-size: 100%;
  display: block;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  z-index: 1;
  background-color: hsla(0, 0%, 100%, 0.9);
  background-blend-mode: overlay;
  background-repeat: repeat;
  min-height: 70rem;
}
.job-list {
  width: 70%;
  margin: 0 auto;
  text-align: left;
  font-size: 20px;
  color: #004d92;
  padding: 2rem 0;
}
.job-title {
  text-align: left;
  font-size: 25px;
  cursor: pointer;
  border-bottom: 3px solid #004d92;
  margin-bottom: 15px;
}
.job-content {
  display: none;
  margin-bottom: 5rem;
}
.arrow {
  height: 15px;
  float: right;
  margin-top: 15px;
  margin-right: 10px;
}
.active {
  -webkit-transform: scaleY(-1);
  transform: scaleY(-1);
}
.content-title {
  text-align: center;
  font-size: 32px;
  font-weight: normal;
}

.form {
  min-height: 22rem;
  display: none;
  align-items: center;
  justify-content: center;
}
input {
  border-top: 0px;
  border-right: 0px;
  border-left: 0px;
  background-color: transparent;
  width: 500px;
  margin: 0 2rem 3rem 2rem;
}
.submit {
  border-bottom: 0px;
  background-color: #ff3344;
  color: white;
  border-radius: 25%;
  padding: 7px 20px;
  width: auto;
  float: right;
  margin-left: 0px;
}
.back {
  background-color: lightgray;
  color: grey;
}
.job-apply {
  float: right;
}
@media only screen and (max-width: 500px) {
  .job-list {
    width: 90%;
    font-size: 3.5vw;
  }
  .job-title {
    font-size: 20px;
  }
  .arrow {
    height: 12px;
    margin-top: 10px;
  }
  ul {
    margin: 5px;
    padding-inline-start: 20px;
  }

}
</style>
