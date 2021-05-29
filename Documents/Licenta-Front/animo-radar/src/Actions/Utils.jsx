import axios from "axios";
import _ from "lodash";

export const API_ENDPOINT ="localhost:8080";


export const isLoggedIn = () => {
    return (
      localStorage.getItem("jwt") != null && localStorage.getItem("jwt") !== ""
    );
  };
  export const POST = (url, data, crossDomain = true) => {
    console.log("Insde axios post")
    return axios.post(url, data, {
      crossDomain,
    });
  };
  export const AUTHORIZED_GET = (url, crossDomain = true) => {
    return axios.get(url, {
      headers: { Authorization: `Bearer ${localStorage.getItem("jwt")}` },
      crossDomain,
    });
  };
