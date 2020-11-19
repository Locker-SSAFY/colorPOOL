import axios from "axios";

export default axios.create({
  // baseURL: "https://cors-anywhere.herokuapp.com/https://j3a303.p.ssafy.io/api",
  baseURL: "https://j3a303.p.ssafy.io/api",

  headers: {
    "Content-type": "application/json",
  },
});