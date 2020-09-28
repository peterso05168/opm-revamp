<template>
  <div>
    <!-- <img class="banner" src="../assets/opm-header-animation.gif" /> -->

    <br />

    <br />

    <br />
    <form @submit="encrypt()" id="f1">
      <input type="text" id="d" />
      <input type="submit" />
    </form>
    <form @submit="setEncrypt()" id="f2">
      <input type="text" id="e" />
      <input type="submit" value="set encrypt text" />
    </form>
    <button @click="decrypt()">decrypt</button>
    <p>encrypt string: {{this.encryptText}}</p>
    <p>result: {{this.result}}</p>
    <p>decrypt Text: {{this.decryptText}}</p>
  </div>
</template>

<script>
export default {
  name: "Solutions",
  data() {
    return {
      data: "",
      encryptText: "",
      result: "",
      decryptText: ""
    };
  },
  methods: {
    encrypt() {
      var input = document.getElementById("d").value;
      var inputArr = input.split("");
      var eTextArr = this.encryptText.split("");
      var result = [];
      var temp = 0;
      for (var i = 0; i < inputArr.length; i++) {
        if (i >= eTextArr.length) {
          temp =
            inputArr[i].charCodeAt(0) +
            eTextArr[
              i - eTextArr.length * Math.floor(i / eTextArr.length)
            ].charCodeAt(0);
          result.push(String.fromCharCode(temp));
        } else {
          temp = inputArr[i].charCodeAt(0) + eTextArr[i].charCodeAt(0);
          result.push(String.fromCharCode(temp));
        }
      }
      console.log("result: ", result);
      this.result = result.join("");
    },
    setEncrypt() {
      var input = document.getElementById("e").value;
      this.encryptText = input;
    },
    decrypt() {
      var inputArr = this.result.split("");
      var eTextArr = this.encryptText.split("");
      var result = [];
      var temp = 0;
      for (var i = 0; i < inputArr.length; i++) {
        if (i >= eTextArr.length) {
          temp =
            inputArr[i].charCodeAt(0) -
            eTextArr[
              i - eTextArr.length * Math.floor(i / eTextArr.length)
            ].charCodeAt(0);
          result.push(String.fromCharCode(temp));
        } else {
          temp = inputArr[i].charCodeAt(0) - eTextArr[i].charCodeAt(0);
          result.push(String.fromCharCode(temp));
        }
      }
      console.log("result: ", result);
      this.decryptText = result.join("");
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
