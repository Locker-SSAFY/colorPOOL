import axios from "axios";

export default axios.create({
  baseURL: "https://j3a303.p.ssafy.io/api/",

  headers: {
    "Content-type": "application/json",
  },
});