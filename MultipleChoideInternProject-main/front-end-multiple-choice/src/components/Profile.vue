<template>
  <div>
    <Navigation />
    <SideBar />
    <div
      class="mt-3 mx-auto flex flex-col justify-center items-center w-3/4 h-full"
    >
      <div class="flex flex-col justify-center items-center">
        <div
          class="flex flex-col justify-center items-center border-solid border-2 rounded-md border-gray-300 p-3 mb-7"
        >
          <img
            :src="'http://localhost:8080/api/v1/file/upload/' + authStore.image"
            class="w-36 h-36 rounded-full mr-1"
            alt=""
          />
          <input
            type="file"
            @change="onFileSelect"
            accept="image/png, image/jpeg, image/jpg"
          />
          <button
            @click="updateUserImage"
            class="rounded-xl bg-slate-500 w-20 h-9 md:h-9 mt-2 md:mt-2 text-sm md:text-lg"
          >
            Save
          </button>
        </div>

        <div>
          <p class="font-bold text-zinc-600 px-2">Full Name</p>
          <input
            v-model="form.fullName"
            class="rounded-md h-8 w-64 pl-2 border-2"
            name=""
            id=""
            placeholder="Full name"
          />
        </div>

        <div>
          <p class="font-bold text-zinc-600 px-2">Address</p>
          <input
            v-model="userData.address"
            class="rounded-md h-8 w-64 pl-2 border-2"
            name=""
            id=""
            placeholder="Address"
          />
        </div>

        <div>
          <p class="font-bold text-zinc-600 px-2">Phone</p>
          <input
            v-model="userData.phone"
            class="rounded-md h-8 w-64 pl-2 border-2"
            name=""
            id=""
            placeholder="Phone"
          />
        </div>

        <div>
          <p class="font-bold text-zinc-600 px-2">Email</p>
          <input
            v-model="userData.email"
            class="rounded-md h-8 w-64 pl-2 border-2"
            name=""
            id=""
            placeholder="Email"
          />
        </div>

        <div>
          <p class="font-bold text-zinc-600 px-2">Username</p>
          <input
            v-model="authStore.username"
            class="rounded-md h-8 w-64 pl-2 border-2"
            name=""
            :disabled="true"
            id=""
            placeholder="Username"
          />
        </div>

        <div>
          <p class="font-bold text-zinc-600 px-2">User password</p>
          <input
            v-model="userData.userPass"
            class="rounded-md h-8 w-64 pl-2 border-2"
            name=""
            id=""
            type="password"
            placeholder="User password"
          />
        </div>
        <!-- ... Other form fields ... -->
        <div class="mt-3 mx-auto flex flex-row justify-center w-3/4 h-full">
          <button
            class="rounded-xl text-white bg-lightblue w-36 h-10 md:h-10 mt-3 md:mt-3 text-sm md:text-lg mb-4"
            @click.prevent="updateUser()"
          >
            Save
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import Navigation from "../components/Navigation.vue";
import SideBar from "../components/SideBar.vue";
import { ref, onBeforeMount } from "vue";
import { api } from "../services/http-common";
import { useAuthStore } from "../store/authStore";
import { useNotificationStore } from "../store/notificationStore";
import {
  required,
  // ... Other validators ...
  sameAs,
} from "@vuelidate/validators";
import { useRouter } from "vue-router";
import { onMounted } from "vue";

const authStore = useAuthStore();
const notificationStore = useNotificationStore();

const router = useRouter();

const form = ref({
  userName: authStore.username,
  userPass: "",
  fullName: authStore.fullName,
  email: authStore.email,
  phone: authStore.phone,
  address: authStore.address,
  imageUser: authStore.image,
  roleId: 3,
  comfirmPass: "",
});
const currentFile = ref();
const onFileSelect = (e: any): void => {
  const file = e.target.files[0];
  currentFile.value = file;
  // console.log("onFileSelect" + currentFile);
};

// H�m ki?m tra �?nh d?ng email
const isEmailValid = (email) => {
  // S? d?ng bi?u th?c ch�nh quy �? ki?m tra �?nh d?ng email
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  return emailRegex.test(email);
};
// H�m ki?m tra �?nh d?ng s? �i?n tho?i
const isPhoneValid = (phone) => {
  // S? d?ng bi?u th?c ch�nh quy �? ki?m tra �?nh d?ng s? �i?n tho?i
  const phoneRegex = /^[0-9]{10,}$/; // �?nh d?ng c� �t nh?t 10 ch? s?
  return phoneRegex.test(phone);
};
// H�m ki?m tra �? d�i t?i thi?u c?a m?t kh?u
const isPasswordValid = (password) => {
  return password.length >= 6; // Y�u c?u �t nh?t 8 k? t?
};

let userData = ref(form); // S? d?ng ref �? gi? gi� tr? c?a userData

const responseUserInfo = api.get(`/user-connect/user/${authStore.id}`);

authStore.isAuthorized;
const onEditClick = async () => {
  const response = await responseUserInfo;
  form.value.userName = response.data.data.userName;
  form.value.userPass = response.data.data.userPass;
  form.value.fullName = response.data.data.fullName;
  form.value.email = response.data.data.email;
  form.value.phone = response.data.data.phone;
  form.value.address = response.data.data.address;
  form.value.imageUser = response.data.data.imageUser;
};

onBeforeMount(() => {
  onEditClick();
});
const updateUserImage = async () => {
  try {
    const formData = new FormData();
    if (currentFile.value) {
      formData.append("imageUser", currentFile.value, currentFile.value.name);
    }
    const response = await api.put(
      `users/update/image/${authStore.id}`,
      formData,
      {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      }
    );
    if (response.status === 200) {
      notificationStore.openSuccess("Update User Image Success.");
      authStore.logOut;
      router.push("/login");
    } else {
      notificationStore.openError("Error updating user image.");
    }
  } catch (error) {
    notificationStore.openError("An error occurred while updating user.");
  }
};
const updateUser = async () => {
  console.log("email: " + authStore.email);
  console.log(authStore.image);

  try {
    const formData = new FormData();
    formData.append("userName", form.value.userName);
    formData.append("fullName", form.value.fullName);
    formData.append("phone", form.value.phone);
    formData.append("email", form.value.email);
    formData.append("address", form.value.address);
    formData.append("userPass", form.value.userPass);
    formData.append("roleId", form.value.roleId);

    if (!isEmailValid(form.value.email)) {
      notificationStore.openError("Invalid email format.");
      return;
    }

    if (!isPhoneValid(form.value.phone)) {
      notificationStore.openError("Invalid phone format.");
      return;
    }
    if (!isPasswordValid(form.value.userPass)) {
      notificationStore.openError("Password must have at least 6 characters.");
      return;
    }
    const response = await api.put(
      `/users/updateUser/${authStore.id}`,
      formData
    );
    if (response.status === 200) {
      notificationStore.openSuccess("Update User Success.");
      authStore.logOut;
      router.push("/login");
    } else {
      notificationStore.openError("Error updating user.");
    }
  } catch (error) {
    notificationStore.openError("An error occurred while updating user.");
  }
};
</script>
