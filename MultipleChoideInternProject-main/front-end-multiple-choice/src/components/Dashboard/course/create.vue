<template>
  <Navigation />
  <div class="flex justify-center">
    <form
      @submit.prevent="saveCourse()"
      @keydown="validateFormCourse()"
      class="w-1/2 shadow-xl rounded-xl border h-fit my-10"
    >
      <h1 class="flex uppercase justify-center font-medium text-2xl my-10">
        Add Course
      </h1>
      <div
        class="grid grid-cols-2 gap-2 md:gap-6 place-items-center mt-4 px-10"
      >
        <div class="w-full">
          <label
            class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
          >
            Nane Course<span class="text-red-600 font-bold">*</span>
          </label>
          <input
            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
            name=""
            v-model="form.courseName"
            id=""
            type="text"
            placeholder="Enter  Nane Course"
          />
          <template v-if="validate.courseName.$error">
            <div
              v-for="(error, index) in validate.courseName.$errors"
              :key="index"
              class="text-red-500 mt-2 italic text-sm"
            >
              {{ validationMessage(error.$message, " Nane Course") }}
            </div>
          </template>
        </div>

        <div class="w-full">
          <label
            class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
          >
            Name Subject<span class="text-red-600 font-bold">*</span>
          </label>
          <input
            v-model="form.subject"
            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
            name=""
            id=""
            type="text"
            required
            placeholder="Java"
          />
          <template v-if="validate.subject.$error">
            <div
              v-for="(error, index) in validate.subject.$errors"
              :key="index"
              class="text-red-500 mt-2 italic text-sm"
            >
              {{ validationMessage(error.$message, "Name Subject") }}
            </div>
          </template>
        </div>

        <div class="flex flex-col items-start space-x-2 w-full">
          <label
            class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
          >
            Status <span class="text-red-600 font-bold">*</span>
          </label>
          <div class="flex flex-row items-center">
            <input
              id="default-radio-1"
              type="radio"
              value="true"
              v-model="form.status"
              name="default-radio"
              class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
            />
            <label
              for="default-radio-1"
              class="ml-2 text-sm font-medium text-gray-900 dark:text-gray-300"
              >Active
            </label>
          </div>
          <div class="flex items-center">
            <input
              checked
              id="default-radio-2"
              type="radio"
              value="false"
              name="default-radio"
              v-model="form.status"
              class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
            />
            <label
              for="default-radio-2"
              class="ml-2 text-sm font-medium text-gray-900 dark:text-gray-300"
              >UnActive</label
            >
          </div>
        </div>
        <div>
          <p class="font-bold text-zinc-600 px-2">Image</p>
          <div
            class="h-56 w-60 rounded-md border-2 bg-white flex justify-center items-center"
          >
            <div class="w-14 h-fit py-2 bg-zinc-400 flex flex-col items-center">
              <label for="file-input"> Image </label>
              <input
                class="hidden"
                id="file-input"
                type="file"
                @change="onFileSelect"
                accept="image/png, image/jpeg, image/jpg"
              />
            </div>
          </div>
        </div>
      </div>
      <div class="flex-row gap-10 my-10 flex justify-center h-10">
        <button
          type="submit"
          class="rounded-md bg-green-300 w-20 hover:bg-green-600 font-bold text-white"
        >
          Save
        </button>
        <button
          class="rounded-md bg-zinc-400 w-20 hover:bg-red-600 font-bold text-white"
        >
          Cancel
        </button>
      </div>
    </form>
  </div>
</template>
<script lang="ts" setup>
import Navigation from "../../Navigation.vue";
import { ref, Ref } from "vue";
import { api } from "../../../services/http-common";
import { useNotificationStore } from "../../../store/notificationStore";
import { useRouter } from "vue-router";
import { useVuelidate } from "@vuelidate/core";
import { required, minLength, maxLength, numeric } from "@vuelidate/validators";
import { useAuthStore } from "../../../store/authStore";
const authStore = useAuthStore();
const notificationStore = useNotificationStore();
const $externalResults = ref({});
const rules = {
  courseName: { required, minLength: minLength(10), maxLength: maxLength(256) },
  subject: { required, minLength: minLength(2), maxLength: maxLength(256) },
};

const router = useRouter();
const form = ref({
  courseName: "",
  teacherId: authStore.id,
  numberStudent: 0,
  subject: "",
  status: "",
});
const validate = useVuelidate(rules, form, { $externalResults });

const validationMessage = (error: any, text: string) => {
  if (error) {
    return error.replace("Value", text);
  }
  return error;
};
const currentFile = ref();

const onFileSelect = (e: any): void => {
  const file = e.target.files[0];
  currentFile.value = file;
};
const validateFormCourse = () => {
  validate.value.$clearExternalResults();
  validate.value.$touch();
  if (validate.value.$invalid) {
    notificationStore.openError("Kiểm tra thông ti.");
    return;
  }
};
const saveCourse = async () => {
  try {
    const formData = new FormData();
    formData.append("courseName", form.value.courseName);
    //@ts-ignore
    formData.append("teacherId", authStore.id);
    //@ts-ignore
    formData.append("numberStudent", form.value.numberStudent);
    formData.append("subject", form.value.subject);
    formData.append("status", form.value.status),
      formData.append("imageCourse", currentFile.value, currentFile.value.name);
    await api.post("/courses/create-course", formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });
    notificationStore.openSuccess("Create Course Success.");

    await router.push("/course/index");
  } catch (e) {
    notificationStore.openError("Error has an error");
  }
};
</script>
