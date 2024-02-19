<template>
  <Navigation />
  <div class="flex justify-center">
    <form
      @submit.prevent="updateCourse"
      class="w-3/6 rounded-md border-2 border-zinc-800 h-3/4 my-14 mb-32"
    >
      <h1 class="flex justify-center font-medium text-2xl my-10">
        Update course
      </h1>
      <div class="grid grid-cols-2 gap-2 md:gap-6 place-items-center mt-4">
        <div>
          <p class="font-bold text-zinc-600 px-2">
            Course name<span class="text-red-600 font-bold">*</span>
          </p>
          <input
            class="rounded-md h-8 w-64 pl-2 border-2"
            name=""
            v-model="form.courseName"
            id=""
            placeholder="Nhập mã khóa hỞc"
          />
        </div>

        <div>
          <p class="font-bold text-zinc-600 px-2">
            Subject <span class="text-red-600 font-bold">*</span>
          </p>
          <input
            v-model="form.subject"
            class="rounded-md h-8 w-64 pl-2 border-2"
            name=""
            id=""
            placeholder="Java"
          />
        </div>
        <div>
          <p class="font-bold text-zinc-600 px-2">Num of students</p>
          <input
            v-model="form.numberStudent"
            class="rounded-md h-8 w-64 pl-2 border-2"
            name=""
            id=""
            placeholder="Nhập mã khóa hỞc"
          />
        </div>

        <div class="flex flex-row items-center justify-items-center space-x-2">
          <p class="font-bold text-zinc-600 px-2">
            Status <span class="text-red-600 font-bold">*</span>
          </p>
          <div class="flex items-center">
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
              >Disable</label
            >
          </div>
        </div>
        <div>
          <p class="font-bold text-zinc-600 px-2">Image</p>
          <div
            class="h-56 w-60 rounded-md border-2 bg-white flex justify-center items-center"
          >
            <div class="w-14 h-fit py-2 bg-zinc-400 flex flex-col items-center">
              <label for="file-input">
                <img
                  class="rounded-full w-5 h-5"
                  src="src/assets/img/iccong.svg"
                  alt=""
                />
              </label>
              <input
                class="hidden"
                id="file-input"
                type="file"
                @change="onFileSelect"
              />
              <p class="font-bold text-xs">Import</p>
            </div>
          </div>
        </div>
      </div>
      <div v-for="chapter in chapters">
        <input v-model="chapter.description" />
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
import { ref, Ref, onBeforeMount } from "vue";
import { api } from "../../../services/http-common";
import { useNotificationStore } from "../../../store/notificationStore";
import { useRouter, useRoute } from "vue-router";
const notificationStore = useNotificationStore();
const chapters = ref([]);
const router = useRouter();
const route = useRoute();
const form = ref({
  courseName: "",
  teacherId: "",
  numberStudent: "",
  subject: "",
  status: "",
});
const currentFile = ref();

const onFileSelect = (e: any): void => {
  const file = e.target.files[0];
  currentFile.value = file;
};
const updateCourse = async () => {
  try {
    const formData = new FormData();
    formData.append("courseName", form.value.courseName);
    formData.append("teacherId", "2");
    formData.append("numberStudent", form.value.numberStudent);
    formData.append("subject", form.value.subject);
    formData.append("status", form.value.status),
      formData.append("imageCourse", currentFile.value, currentFile.value.name);
    await api.post("/courses/create-course", formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });
    notificationStore.openSuccess("Update succes!");
    await router.push("/course/index");
  } catch (e) {
    notificationStore.openError("Update success!");
  }
};
const getCourse = async () => {
  try {
    const { data } = await api.get(`/courses/${route.params.id}`);
    form.value = data;
    chapters.value = data.chapters;
    console.log(chapters.value);
  } catch (e) {
    console.error(e);
  }
};
onBeforeMount(() => {
  getCourse();
});
// const addChapter = () => {
//   chapters.value.push({
//     previous: "",
//     expiration: "",
//   });
// };
// function deleteChapter(counter) {
//   chapters.value.splice(counter, 1);
// }
</script>
<!-- <div id="visa">
    <h1>Vue Visa Application</h1>
    <form>
      <label for="first name">First Name:</label>
      <input type="text" required />
      <br />
      <label for="last name">Last Name:</label>
      <input type="text" required />
      <br />
      <label for="country">Nationality:</label>
      <input type="text" required />
      <br />
      <label for="passport number">Passport Number:</label>
      <input type="text" required />
  
      <label for="duration">Duration of stay:</label>
      <input type="text" required />
      <br /><br />
      <button @click="addChapter">Add another previous visa</button>
      <br />
      <div class="previous" v-for="(chapter, index) in chapters" :key="index">
        <span @click="deleteChapter(index + 1)">x</span>
        <label for="duration">Previous Visa:</label>
        <input type="text" required />
        <label for="duration">Year of expiration:</label>
        <input type="text" required />
      </div>
    </form>
  </div> -->
