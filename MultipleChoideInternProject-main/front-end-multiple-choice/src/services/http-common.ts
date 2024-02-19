import axios, { AxiosInstance, AxiosResponse } from "axios";
import { useRouter } from "vue-router";

export const api: AxiosInstance = axios.create({
  baseURL: `http://localhost:8080/api/v1`,
  headers: {
    "Content-Type": "application/json",
    Accept: "application/json",
  },
});
// api.interceptors.request.use((config) => {});
