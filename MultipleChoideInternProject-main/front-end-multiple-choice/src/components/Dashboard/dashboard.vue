<template>
  <Navigation />
  <SideBarDashboard />
  <div class="w-2/3 flex flex-col items-center mx-auto text-center">
    <div class="grid grid-cols-4 gap-4 w-full py-10">
      <div class="bg-sky-500 rounded-xl py-10 flex flex-col items-center">
        <Icon class="w-16 h-16" name="groupuser" />
        <p class="text-white text-2xl font-semibold">
          {{ allUser.length }} Users
        </p>
      </div>
      <div class="bg-yellow-400 rounded-xl py-10 flex flex-col items-center">
        <Icon class="w-16 h-16" name="groupuser" />
        <p class="text-white text-2xl font-semibold">
          {{ allTeacher.length }} Teachers
        </p>
      </div>
      <div class="bg-red-400 rounded-xl flex flex-col py-10 items-center">
        <Icon class="w-10 h-12" name="blog" />
        <p class="text-white text-2xl font-semibold mt-4">
          {{ allBlog.length }} Blogs
        </p>
      </div>
      <div class="bg-green-500 rounded-xl flex flex-col py-10 items-center">
        <Icon class="w-10 h-12" name="course" />
        <p class="text-white text-2xl font-semibold mt-4">
          {{ allCourse.length }} Courses
        </p>
      </div>
    </div>
  </div>
</template>
<script lang="ts" setup>
import Navigation from "../../components/Navigation.vue";
import SideBarDashboard from "./SideBarDashboard.vue";
import { api } from "../../services/http-common";
import { ref, onBeforeMount } from "vue";
import Icon from "../../icons/ClientDashboard.vue";
const allUser = ref([]);
const allTeacher = ref([]);
const allBlog = ref([]);
const allCourse = ref([]);

const getAllUser = async () => {
  const { data } = await api.get(`/user-connect/user/list`);
  allUser.value = data.data;
};
const getAllTeacher = async () => {
  const { data } = await api.get(`/user-connect/teacher/list`);
  allTeacher.value = data.data;
};
const getAllBlog = async () => {
  const data = await api.get(`/blogs`);
  allBlog.value = data.data.content;
};
const getAllCourse = async () => {
  const data = await api.get(`/courses`);
  allCourse.value = data.data.content;
};
onBeforeMount(() => {
  getAllTeacher();
  getAllUser();
  getAllCourse();
  getAllBlog();
});
</script>
