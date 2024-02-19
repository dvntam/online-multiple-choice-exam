<template>
  <section
    class="items-center justify-center p-32 bg-gradient-to-r from-sky-800 from-10% to-purple-700 h-screen"
  >
    <form
      @keydown="validateFormRegister()"
      class="w-fit py-10 md:w-1/2 bg-white mx-auto rounded-lg h-fit flex flex-col items-center"
    >
      <!-- <Icon name="logo" class="w-24 md:w-32 h-20 md:h-28" /> -->
      <p
        class="font-bold text-3xl mt-2 text-center from-sky-800 from-10% to-purple-700 bg-gradient-to-r bg-clip-text text-transparent"
      >
        Register Account
      </p>

      <div class="grid grid-cols-1 lg:grid-cols-2 gap-2 md:gap-6 mt-6">
        <div class="flex flex-col">
          <label class="font-bold text-zinc-600 px-2">
            Full Name<span class="text-red-600 font-bold">*</span>
          </label>
          <input
            v-model="form.fullName"
            class="border text-sm md:text-lg border-black rounded-md h-10 md:h-12 w-52 md:w-72 pl-2 placeholder-black"
            placeholder="Enter Full Name"
          />
          <template v-if="validate.fullName.$error">
            <div
              v-for="(error, index) in validate.fullName.$errors"
              :key="index"
              class="text-red-500 mt-2 italic text-sm"
            >
              {{ validationMessage(error.$message, "Full name") }}
            </div>
          </template>
        </div>
        <div class="flex flex-col">
          <label class="font-bold text-zinc-600 px-2">
            Phone<span class="text-red-600 font-bold">*</span>
          </label>
          <input
            placeholder="Enter Phone Number "
            v-model="form.phone"
            class="placeholder-black border border-black text-sm md:text-lg rounded-md h-10 md:h-12 w-52 md:w-72 pl-2"
          />
          <template v-if="validate.phone.$error">
            <div
              v-for="(error, index) in validate.phone.$errors"
              :key="index"
              class="text-red-500 mt-2 italic text-sm"
            >
              {{ validationMessage(error.$message, "Phone number") }}
            </div>
          </template>
        </div>
        <div class="flex flex-col">
          <label class="font-bold text-zinc-600 px-2">
            Email<span class="text-red-600 font-bold">*</span>
          </label>
          <input
            v-model="form.email"
            type="email"
            class="border border-black rounded-md h-10 md:h-12 w-52 md:w-72 pl-2 placeholder-black text-sm md:text-lg"
            placeholder="Enter email"
          />
          <template v-if="validate.email.$error">
            <div
              v-for="(error, index) in validate.email.$errors"
              :key="index"
              class="text-red-500 mt-2 italic text-sm"
            >
              {{ validationMessage(error.$message, "Email") }}
            </div>
          </template>
        </div>
        <div class="flex flex-col">
          <label class="font-bold text-zinc-600 px-2">
            Username<span class="text-red-600 font-bold">*</span>
          </label>
          <input
            v-model="form.username"
            placeholder="Enter Username"
            type="text"
            class="border border-black rounded-md h-10 md:h-12 w-52 md:w-72 pl-2 placeholder-black text-sm md:text-lg"
          />
          <template v-if="validate.username.$error">
            <div
              v-for="(error, index) in validate.username.$errors"
              :key="index"
              class="text-red-500 mt-2 italic text-sm"
            >
              {{ validationMessage(error.$message, "Username") }}
            </div>
          </template>
        </div>
        <div class="flex flex-col">
          <label class="font-bold text-zinc-600 px-2">
            Password<span class="text-red-600 font-bold">*</span>
          </label>
          <input
            type="password"
            v-model="form.userPass"
            class="border border-black rounded-md h-10 md:h-12 w-52 md:w-72 pl-2 placeholder-black text-sm md:text-lg"
            placeholder="Enter password"
          />
          <template v-if="validate.userPass.$error">
            <div
              v-for="(error, index) in validate.userPass.$errors"
              :key="index"
              class="text-red-500 mt-2 italic text-sm"
            >
              {{ validationMessage(error.$message, "Password") }}
            </div>
          </template>
        </div>
        <div class="flex flex-col">
          <label class="font-bold text-zinc-600 px-2">
            Corfirm Password<span class="text-red-600 font-bold">*</span>
          </label>
          <input
            v-model="form.comfirmPass"
            type="password"
            placeholder="Enter confirm password"
            class="border border-black rounded-md h-10 md:h-12 w-52 md:w-72 pl-2 placeholder-black text-sm md:text-lg"
          />
          <template v-if="validate.comfirmPass.$error">
            <div
              v-for="(error, index) in validate.comfirmPass.$errors"
              :key="index"
              class="text-red-500 mt-2 italic text-sm"
            >
              {{ validationMessage(error.$message, "Confirm") }}
            </div>
          </template>
        </div>
        <div class="flex flex-col">
          <label class="font-bold text-zinc-600 px-2">
            Address<span class="text-red-600 font-bold">*</span>
          </label>
          <input
            v-model="form.address"
            type="text"
            placeholder="Enter Address"
            class="border border-black rounded-md h-10 md:h-12 w-52 md:w-72 pl-2 placeholder-black text-sm md:text-lg"
          />
          <template v-if="validate.address.$error">
            <div
              v-for="(error, index) in validate.address.$errors"
              :key="index"
              class="text-red-500 mt-2 italic text-sm"
            >
              {{ validationMessage(error.$message, "Confirm") }}
            </div>
          </template>
        </div>
        <div class="flex flex-col">
          <label class="font-bold text-zinc-600 px-2">
            Image<span class="text-red-600 font-bold">*</span>
          </label>
          <input
            type="file"
            @change="onFileSelect"
            accept="image/png, image/jpeg, image/jpg"
          />
          <template v-if="validate.address.$error">
            <div
              v-for="(error, index) in validate.address.$errors"
              :key="index"
              class="text-red-500 mt-2 italic text-sm"
            >
              {{ validationMessage(error.$message, "Confirm") }}
            </div>
          </template>
        </div>
      </div>
      <button
        class="rounded-xl text-white bg-gradient-to-r from-sky-800 from-10% to-purple-700 w-36 h-10 md:h-12 mt-7 md:mt-10 text-sm md:text-lg"
        @click.prevent="onRegister()"
      >
        Register
      </button>
    </form>

    <!-- <div id="app"></div> -->
  </section>
</template>
<script lang="ts" setup>
import Icon from "../icons/ClientDashboard.vue";
import { api } from "../services/http-common";
import { ref } from "vue";
import { useRouter } from "vue-router";
import { useNotificationStore } from "../store/notificationStore";
import { useVuelidate } from "@vuelidate/core";
import {
  required,
  minLength,
  maxLength,
  email,
  numeric,
  sameAs,
  alpha,
} from "@vuelidate/validators";
const notificationStore = useNotificationStore();
const $externalResults = ref({});
const form = ref({
  username: "",
  userPass: "",
  fullName: "",
  email: "",
  phone: "",
  address: "",
  imageUser: "",
  roleId: 3,
  comfirmPass: "",
});

const rules = {
  username: {
    required,
    minLength: minLength(6),
    maxLength: maxLength(50),
  },
  userPass: {
    required,
    minLength: minLength(6),
    maxLength: maxLength(50),
  },
  fullName: {
    required,
    alpha,
    minLength: minLength(6),
    maxLength: maxLength(50),
  },
  email: {
    required,
    email,
  },
  address: {
    required,
    minLength: minLength(6),
    maxLength: maxLength(50),
  },
  phone: {
    required,
    numeric,
    minLength: minLength(10),
    maxLength: maxLength(10),
  },
  comfirmPass: {
    sameAs: sameAs("password"),
  },
};
const validate = useVuelidate(rules, form, { $externalResults });

const validateFormRegister = () => {
  validate.value.$clearExternalResults();
  validate.value.$touch();
  if (validate.value.$invalid) {
    notificationStore.openError("Please check field value.");
    return;
  }
};
const router = useRouter();
const onRegister = async () => {
  const data = await api.post(
    "/user-connect/user/check-email",
    {},
    { params: { email: form.value.email } }
  );
  if (data.data.data == false) {
    notificationStore.openError("Exist Email");
    return;
  }
  const data2 = await api.post(
    "/user-connect/user/check-username",
    {},
    { params: { username: form.value.username } }
  );
  if (data2.data.data == false) {
    notificationStore.openError("Exist Username");
    return;
  }
  try {
    const formData = new FormData();
    formData.append("userName", form.value.username);
    formData.append("userPass", form.value.userPass);
    formData.append("fullName", form.value.fullName);
    formData.append("email", form.value.email);
    formData.append("phone", form.value.phone);
    formData.append("address", form.value.address);
    //@ts-ignore
    formData.append("roleId", form.value.roleId);
    formData.append("imageUser", currentFile.value, currentFile.value.name);
    await api.post("/user-connect/user/create", formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });
    notificationStore.openSuccess("Register Success.");

    await router.push("/login");
  } catch (e) {
    notificationStore.openError("Error has an error");
  }
};
const currentFile = ref();
const onFileSelect = (e: any): void => {
  const file = e.target.files[0];
  currentFile.value = file;
};
const validationMessage = (error: any, text: string) => {
  if (error) {
    return error.replace("Value", text);
  }
  return error;
};
</script>
