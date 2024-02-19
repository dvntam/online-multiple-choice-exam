<template>
  <Navigation />
  <SideBar />
  <div class="flex flex-col w-3/4 mx-auto space-y-4 items-center">
    <div
      v-for="(result, index) in listResult"
      :key="index"
      class="rounded-xl shadow-lg p-5 flex flex-col text-center w-full"
    >
      <p class="text-xl font-semibold">
        Exam times: <span class="text-red-500">{{ result.exam.examName }}</span>
      </p>
      <p class="text-xl font-semibold">
        Exam times: <span class="text-red-500">{{ index + 1 }}</span>
      </p>
      <p class="text-xl">
        Point:
        <span v-if="result.point > 50" class="text-green-500">{{
          result.point
        }}</span>
        <span v-else class="text-red-500">{{ result.point }}</span>
      </p>
    </div>
  </div>
</template>
<script lang="ts" setup>
import { ref, onBeforeMount } from "vue";
import { useRoute } from "vue-router";
import { useAuthStore } from "../../store/authStore";
import { api } from "../../services/http-common";
import Navigation from "../../components/Navigation.vue";
import SideBar from "../../components/SideBar.vue";
const route = useRoute();
const authStore = useAuthStore();
const listResult = ref();
const getResultExamById = async () => {
  if (authStore.isAuthorized) {
    try {
      const { data } = await api.get(
        `/result-exam/user-and-exam/${authStore.id}/${route.params.id}`
      );
      listResult.value = data;
    } catch (e) {
      console.error(e);
    }
  }
};
onBeforeMount(() => {
  getResultExamById();
});
</script>
