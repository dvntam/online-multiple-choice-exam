import { defineStore } from "pinia";

import { api } from "../services/http-common";
import { useRouter, useRoute } from "vue-router";
const router = useRouter();
type AuthState = {
  id: Number | undefined;
  address: String;
  email: String;
  fullName: String;
  image: String;
  phone: String;
  username: String;
  role: Number;
};

// @ts-ignore
export const useAuthStore = defineStore("auth", {
  state: (): AuthState => ({
    id: 0,
    address: "",
    email: "",
    fullName: "",
    image: "",
    phone: "",
    username: "",
    role: 3,
  }),
  getters: {
    //@ts-ignore
    isAuthorized: (state) => {
      if (localStorage.getItem("id")) {
        state.id = Number(localStorage.getItem("id")?.toString());
      }
      if (localStorage.getItem("address")) {
        state.address = localStorage.getItem("address")?.toString();
      }
      if (localStorage.getItem("email")) {
        state.email = localStorage.getItem("email")?.toString();
      }
      if (localStorage.getItem("fullName")) {
        state.fullName = localStorage.getItem("fullName")?.toString();
      }
      if (localStorage.getItem("image")) {
        state.image = localStorage.getItem("image")?.toString();
      }
      if (localStorage.getItem("phone")) {
        state.phone = localStorage.getItem("phone")?.toString();
      }
      if (localStorage.getItem("username")) {
        state.username = localStorage.getItem("username")?.toString();
      }
      if (localStorage.getItem("role")) {
        state.role = Number(localStorage.getItem("role")?.toString());
      }

      return !!state.id;
    },
    userType: (state) => {
      if (state.role) {
        return state.role;
      }
      return "3";
    },

    isAdmin() {
      // @ts-ignore
      if (!this.isAuthorized) return false;
      // @ts-ignore
      return this.role == 1 ? true : false;
    },
    isTeacher() {
      // @ts-ignore
      if (!this.isAuthorized) return false;
      // @ts-ignore
      return this.role == 2 ? true : false;
    },
    isUser() {
      if (!this.isAuthorized || this.isAdmin || this.isTeacher) return false;
      // @ts-ignore
      return true;
    },
    userId: (state) => {
      return state.id ? state.id : 0;
    },
  },
  actions: {
    setId(id: string) {
      this.id = Number(id);
      localStorage.setItem("id", id);
    },
    setAddress(address: string) {
      this.address = address;
      localStorage.setItem("address", address);
    },
    setEmail(email: string) {
      this.email = email;
      localStorage.setItem("email", email);
    },
    setPhone(phone: string) {
      this.phone = phone;
      localStorage.setItem("phone", phone);
    },
    setImage(image: string) {
      this.image = image;
      localStorage.setItem("image", image);
    },
    setFullName(fullName: string) {
      this.fullName = fullName;
      localStorage.setItem("fullName", fullName);
    },
    setUserName(username: string) {
      this.username = username;
      localStorage.setItem("username", username);
    },

    setRole(role: string) {
      localStorage.setItem("role", role);
    },

    async refreshProfile() {
      try {
        const { data } = await api.get(`/auth/profile`);
        this.setCurrentUser(data.data.user);
        if (data.data.banned_readers) {
          const bannedReaders: Array<User> = [];
          data.data.banned_readers.forEach((bannedReader: any) => {
            bannedReaders.push(bannedReader.user);
          });
          this.setBannedReaders(bannedReaders);
        }
      } catch (error) {}
    },
    async logOut() {
      this.id = undefined;
      localStorage.removeItem("id");
      localStorage.removeItem("address");
      localStorage.removeItem("email");
      localStorage.removeItem("fullName");
      localStorage.removeItem("image");
      localStorage.removeItem("phone");
      localStorage.removeItem("username");
      localStorage.removeItem("role");
      router.push({ name: "Home" });
    },
  },
});
