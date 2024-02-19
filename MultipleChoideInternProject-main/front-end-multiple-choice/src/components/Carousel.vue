<script lang="ts" setup>
import "vue3-carousel/dist/carousel.css";
import { Carousel, Slide } from "vue3-carousel";
import { Course } from "../model/course";
import { api } from "../services/http-common";
import { ref, Ref, onBeforeMount } from "vue";
const popularCourses: Ref<Array<Course>> = ref([]);

const getCoursePopular = async () => {
  try {
    const data = await api.get("/courses/popular-courses");
    console.log(data.data);
    popularCourses.value = data.data;
    console.log(popularCourses.value);
  } catch (e) {
    console.error(e);
  }
};
onBeforeMount(() => {
  getCoursePopular();
});
</script>

<template>
  <div>
    <Carousel :transition="500">
      <Slide v-for="course in popularCourses" :key="course.courseId">
        <div
          class="w-3/4 mx-auto rounded-xl overflow-hidden flex flex-row h-80 justify-between bg-gradient-to-r from-sky-800 from-10% to-purple-700"
        >
          <div class="flex flex-col space-y-4 p-10 text-start text-white">
            <p class="text-3xl font-bold">{{ course.subject }}</p>
            <p class="text-md font-semibold">
              To achieve good results in everything, we need to set clear goals
              for that task. Learning programming is also no exception.
            </p>
            <router-link
              :to="{
                name: 'Courses.Detail',
                params: { id: Number(course.courseId) },
              }"
              class="border-collapse border-white rounded-2xl border-2 w-40 pl-2 text-md font-semibold hover:font-bold hover:bg-slate-500"
            >
              Read more ...
            </router-link>
          </div>
          <div class="w-1/2">
            <img
              class="opacity-80 h-full w-full"
              :src="course.imageCourse"
              alt="img-banner"
            />
          </div>
        </div>
      </Slide>
    </Carousel>
  </div>
</template>
