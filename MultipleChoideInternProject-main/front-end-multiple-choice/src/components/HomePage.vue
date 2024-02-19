<script lang="ts" setup>
import Navigation from "../components/Navigation.vue";
import Item from "../components/Course/Item.vue";
import Blog from "../components/Blog/Item.vue";
import SideBar from "../components/SideBar.vue";
import Carousel from "../components/Carousel.vue";
import { Course } from "../model/course";
import { ref, Ref, onBeforeMount } from "vue";
import { api } from "../services/http-common";
import { useAuthStore } from "../store/authStore";
const useAuth = useAuthStore();
const popularCourses: Ref<Array<Course>> = ref([]);
const allCourses: Ref<Array<Course>> = ref([]);
const listBlog = ref([]);
const getCoursePopular = async () => {
  try {
    const data = await api.get("/courses/popular-courses");
    console.log(data.data);
    popularCourses.value = data.data;
    popularCourses.value.splice(3, 4);
  } catch (e) {
    console.error(e);
  }
};
const getAllCourses = async () => {
  try {
    const data = await api.get("/courses");
    allCourses.value = data.data.content;
  } catch (e) {
    console.error(e);
  }
};
const getBlogs = async () => {
  const data = api.get("/blogs");
  listBlog.value = data.data.content;
};
onBeforeMount(() => {
  getCoursePopular();
  getAllCourses();
  getBlogs();
});
</script>
<template>
  <Navigation />
  <SideBar />
  <Carousel />
  <div class="flex flex-col w-3/4 pt-10 mx-auto space-y-4">
    <p class="font-semibold text-3xl">Popular courses</p>
    <div class="grid grid-cols-3 gap-4">
      <Item
        v-for="course in popularCourses"
        :key="course.courseId"
        :course="course"
      />
    </div>
  </div>
  <div class="flex flex-col w-3/4 py-10 mx-auto space-y-4">
    <p class="font-semibold text-3xl">Free courses</p>
    <div class="grid grid-cols-3 gap-6">
      <Item
        v-for="course in allCourses"
        :key="course.courseId"
        :course="course"
      />
    </div>
  </div>
</template>
