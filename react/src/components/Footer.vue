<template>
  <div class="footer">
    <div class="footer-content">
      <div>
        <p class="title">OPM SYSTEMS COMPANY LTD</p>
        <p class="address">
          Unit 1904, 19/F,
          <br />Global Gateway Tower,
          <br />63 Wing Hong Street,
          <br />Cheung Sha Wan
        </p>
        <table>
          <tr>
            <td style="padding-right: 40px">Tel:</td>
            <td>(852) 3743 8900</td>
          </tr>
          <tr>
            <td>Fax:</td>
            <td>(852) 3743 8901</td>
          </tr>
          <tr>
            <td>Email:</td>
            <td>info@opm.com.hk</td>
          </tr>
        </table>
      </div>
      <div class="footer-right">
        <a href="/Home" style="color: white;">Home</a>
        <br />
        <br />
        <a href="/AboutUs" style="color: white;">About Us</a>
        <br />
        <br />
        <a href="/Products" style="color: white;">Products</a>
        <br />
        <br />
        <a href="/Services" style="color: white;">Services</a>
        <br />
        <br />
        <a href="/JoinUs" style="color: white;">Join Us</a>
        <br />
        <br />
        <a href="/ContactUs" style="color: white;">Contact Us</a>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Footer",
  data() {
    return {};
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.footer {
  background-color: #555;
  color: white;
  text-align: left;
  height: 100vh;
  display: flex;
  align-items: flex-start;
  padding-top: 100px;
}
.footer-content {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  grid-auto-rows: minmax(50px, auto);
  width: 70%;
  margin: 0 auto;
  grid-gap: 30px;
}
.title {
  font-size: 30px;
  font-weight: bold;
}
.footer-right {
  padding-left: 100px;
  padding-top: 70px;
}
@media only screen and (max-width: 500px) {
  .footer {
    padding-top: 50px;
    font-size: 18px;
  }
  .title {
    font-size: 24px;
  }
  .footer-content {
    grid-template-columns: repeat(1, 1fr);
    width: 90%;
  }
  .address {
    padding-top: 30px;
    padding-bottom: 30px;
  }
  .footer-right {
    display: none;
  }
}
</style>
