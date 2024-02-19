<script lang="ts" setup>
import Navigation from "../components/Navigation.vue";
import SideBar from "../components/SideBar.vue";
import { api } from "../services/http-common";
import { ref, Ref, onBeforeMount } from "vue";
import Item from "../components/Exam/Item.vue";
import { useAuthStore } from "../store/authStore";
import { useNotificationStore } from "../store/notificationStore";
const authStore = useAuthStore();
const notificationStore = useNotificationStore();
const listExamInComing = ref([]);
const listExamFinish = ref([]);
const getInComingExam = async () => {
  if (authStore.isAuthorized) {
    const { data } = await api.get(
      `user/${authStore.id}/incoming-exams?page=0&size=10`
    );
    listExamInComing.value = data.content;
  } else {
    notificationStore.openError("You are not sign in!");
  }
};
const getFinishExam = async () => {
  if (authStore.isAuthorized) {
    const { data } = await api.get(
      `user/${authStore.id}/finished-exams?page=0&size=5`
    );
    listExamFinish.value = data.content;
    console.log(listExamFinish.value[0]);
  } else {
    notificationStore.openError("You are not sign in!");
  }
};

onBeforeMount(() => {
  getInComingExam();
  getFinishExam();
});
</script>

<template>
  <Navigation />
  <SideBar />
  <div class="flex flex-col w-3/4 mx-auto">
    <h3 class="inline font-bold text-3xl">Exams</h3>
    <div class="pt-10 space-y-4 text-start">
      <p v-if="listExamInComing.length == 0" class="text-red-500 text-xl">
        There are no upcoming exams
      </p>
      <div v-else class="grid grid-cols-3 gap-6">
        <div
          v-for="examInComing in listExamInComing"
          class="flex flex-col rounded-xl shadow-xl items-center space-y-4 py-5 bg-slate-200"
        >
          <p class="text-xl font-normal">
            Name Exam:<span class="text-xl ml-2 text-red-500 font-semibold">{{
              examInComing.examName
            }}</span>
          </p>

          <p class="text-xl font-normal">
            Number of Question:<span class="text-xl ml-2 font-semibold">{{
              examInComing.numQuestion
            }}</span>
          </p>
          <p class="text-xl font-normal">
            Time:<span class="text-xl ml-2 font-semibold text-red-600">{{
              examInComing.duration
            }}</span>
          </p>

          <router-link
            :to="{
              name: 'Exam.TakeExam',
              params: { id: examInComing.examId },
            }"
            class="bg-gradient-to-r from-sky-800 from-10% to-purple-700 text-white font-see.mibold px-3 py-2 w-32 rounded-xl text-center"
          >
            Start</router-link
          >
        </div>
      </div>
    </div>
    <div class="flex flex-col w-full mt-10 mx-auto space-y-4">
      <p
        class="font-semibold text-3xl relative w-fit h-10 flex flex-col justify-end"
      >
        Fininsh Exam
      </p>
      <p v-if="listExamFinish.length == 0" class="text-red-500 text-xl">
        You have not completed any test yet
      </p>
      <div v-else class="grid grid-cols-3 gap-6">
        <div
          v-for="examFinish in listExamFinish"
          class="flex flex-col rounded-xl shadow-xl items-center space-y-4 bg-slate-200 py-10"
        >
          <p class="text-xl font-normal">
            Name Exam:<span class="text-xl ml-2 text-red-500 font-semibold">{{
              examFinish.examName
            }}</span>
          </p>
          <p class="text-xl font-normal">
            Number of Question:<span class="text-xl ml-2 font-semibold">{{
              examFinish.numQuestion
            }}</span>
          </p>
          <p class="text-xl font-normal">
            Time:<span class="text-xl ml-2 font-semibold text-red-600">{{
              examFinish.duration
            }}</span>
          </p>

          <div class="flex flex-row space-x-6">
            <router-link
              :to="{
                name: 'Exam.TakeExam',
                params: { id: examFinish.examId },
              }"
              class="bg-gradient-to-r from-sky-800 from-10% to-purple-700 text-white font-semibold px-3 py-2 w-32 rounded-xl text-center"
            >
              ReTest</router-link
            >
            <router-link
              :to="{
                name: 'Exam.ReultExam',
                params: { id: examFinish.examId },
              }"
              class="bg-gradient-to-r from-sky-800 from-10% to-purple-700 text-white font-semibold px-3 py-2 w-32 rounded-xl text-center"
            >
              View Result</router-link
            >
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
