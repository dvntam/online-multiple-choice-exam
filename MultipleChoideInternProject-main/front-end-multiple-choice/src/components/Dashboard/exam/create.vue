<template>
  <Navigation />
  <div class="flex justify-center">
    <form
      @submit.prevent="saveExam()"
      @keydown="validateFormExam"
      class="w-3/6 rounded-md border-2 border-zinc-800 h-3/4 my-14 mb-32"
    >
      <h1 class="flex uppercase justify-center font-medium text-3xl my-10">
        Create exam for
        <span class="text-red-600">{{ course.courseName }}</span>
      </h1>
      <div class="grid grid-cols-2 gap-2 md:gap-6 place-items-center mt-4">
        <div class="flex flex-col items-start">
          <label class="text-lg font-semibold">Name of Course: </label>
          <p>{{ course.courseName }}</p>
        </div>
        <div class="flex flex-col items-start">
          <label class="text-lg font-semibold">Name of Subject: </label>
          <p>{{ course.subject }}</p>
        </div>
        <div class="flex flex-row">
          <label class="text-lg font-semibold"
            >Name Teacher :
            <p class="font-semibold">{{ authStore.fullName }}</p>
          </label>
        </div>
        <div>
          <p class="font-bold px-2">Name</p>
          <input
            v-model="form.examName"
            class="rounded-md h-8 w-64 pl-2 border-2"
            min="1"
            max="1000"
            required
            placeholder="Enter Name Exam"
          />
          <template v-if="validate.examName.$error">
            <div
              v-for="(error, index) in validate.examName.$errors"
              :key="index"
              class="text-red-500 mt-2 italic text-sm"
            >
              {{ validationMessage(error.$message, "Exam name") }}
            </div>
          </template>
        </div>
        <div>
          <p class="font-bold px-2">
            Num of question <span class="text-red-600">*</span>
          </p>
          <input
            v-model="form.numQuestion"
            class="rounded-md h-8 pl-2 border-2"
            type="number"
            min="1"
            max="1000"
            required
            placeholder="Enter num of questions"
          />
          <template v-if="validate.numQuestion.$error">
            <div
              v-for="(error, index) in validate.numQuestion.$errors"
              :key="index"
              class="text-red-500 mt-2 italic text-sm"
            >
              {{ validationMessage(error.$message, "Num of question") }}
            </div>
          </template>
        </div>
        <div>
          <p class="font-bold px-2">Time</p>
          <input
            v-model="form.duration"
            class="rounded-md h-8 pl-2 border-2"
            min="1"
            max="1000"
            required
            placeholder="Enter time"
          />
          <template v-if="validate.numQuestion.$error">
            <div
              v-for="(error, index) in validate.numQuestion.$errors"
              :key="index"
              class="text-red-500 mt-2 italic text-sm"
            >
              {{ validationMessage(error.$message, "Num of question") }}
            </div>
          </template>
        </div>
      </div>
      <div class="flex-row gap-10 my-10 flex justify-center h-10">
        <button
          type="submit"
          class="rounded-xl bg-green-300 w-20 hover:bg-green-600 font-bold text-white uppercase"
        >
          Save
        </button>
        <button
          class="rounded-xl bg-zinc-400 w-20 hover:bg-red-600 font-bold text-white uppercase"
        >
          cancel
        </button>
      </div>
    </form>
  </div>
</template>
<script lang="ts" setup>
import Navigation from "../../Navigation.vue";
import { ref, onBeforeMount } from "vue";
import { api } from "../../../services/http-common";
import { useNotificationStore } from "../../../store/notificationStore";
import { useVuelidate } from "@vuelidate/core";
import { useRoute, useRouter } from "vue-router";
import {
  required,
  minLength,
  maxLength,
  numeric,
  minValue,
  maxValue,
} from "@vuelidate/validators";
import { useAuthStore } from "../../../store/authStore";
const authStore = useAuthStore();
const notificationStore = useNotificationStore();
const $externalResults = ref({});
const rules = {
  examName: {
    required,
    minLength: minLength(6),
    maxLength: maxLength(100),
  },
  duration: {
    required,
    numeric,
    minValue: minValue(1),
    maxValue: maxValue(50),
  },
  numQuestion: {
    numeric,
    required,
    minValue: minValue(5),
    maxValue: maxValue(50),
  },
};
const course = ref({});
const route = useRoute();
function padTo2Digits(num) {
  return num.toString().padStart(2, "0");
}
function toHoursAndMinutes(totalMinutes) {
  const minutes = totalMinutes % 60;
  const hours = Math.floor(totalMinutes / 60);

  return `${padTo2Digits(hours)}:${padTo2Digits(minutes)}:00`;
}
const router = useRouter();
const form = ref({
  courses: {
    courseId: 0,
    courseName: course.value.courseName,
    imageCourse: "",
    numberStudent: 0,
    status: true,
    subject: course.value.subject,
    teacher: {
      id: authStore.id,
    },
  },

  duration: "",
  examId: 0,
  examName: "",
  numQuestion: 0,
});
console.log(form.value.duration.toString());
const validate = useVuelidate(rules, form, { $externalResults });

const validationMessage = (error: any, text: string) => {
  if (error) {
    return error.replace("Value", text);
  }
  return error;
};

const validateFormExam = () => {
  validate.value.$clearExternalResults();
  validate.value.$touch();
  if (validate.value.$invalid) {
    notificationStore.openError("Check information fields.");
    return;
  }
};
const getCourseById = async () => {
  const { data } = await api.get(`/courses/${route.params.idcourse}`);
  course.value = data;
  form.value.courses = course.value;
};
const saveExam = async () => {
  try {
    form.value.duration = toHoursAndMinutes(form.value.duration);
    const data = await api.post(`/course/exam/add`, form.value);
    router.push({
      name: "Dashboard.Exam.AddQuestion",
      params: { id: data.data.examId, subject: course.value.subject },
    });
  } catch (e) {
    console.error(e);
  }
};
onBeforeMount(() => {
  getCourseById();
});
</script>
