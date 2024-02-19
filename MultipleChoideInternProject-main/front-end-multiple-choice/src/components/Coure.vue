<template>
  <Navigation />
  <SideBar />
  <div class="flex flex-col w-3/4 mx-auto">
    <h3 class="inline font-bold text-3xl">Course</h3>
    <p>
      The Open Courses are designed to suit beginners, many of them are free
      cost, quality, easy to understand content.
    </p>
    <div class="flex flex-col w-full mt-10 mx-auto space-y-4" v-if="authStore.isAuthorized">
      <p  class="font-semibold text-3xl relative w-fit h-10 flex flex-col justify-end">My Course 
      </p>
      <template v-if="myCourses.length >0">
       
      <div class="grid grid-cols-3 gap-6">
        <Item v-for="course in myCourses " :key="course.id" :course="course" />
      </div>
     </template>

     <template v-else>
      <p class="text-red-600">You are not taking any courses</p>
     </template>
    
    </div>
    <div class="flex flex-col w-full mt-10 mx-auto space-y-4">
    
      <p class="font-semibold text-3xl relative w-fit h-16 flex flex-col justify-end ">Popular Course
        <div class="absolute top-0 right-0  text-sm text-white font-semibold bg-blue-500 px-2 rounded-md">
          New
        </div>
      </p>
      <div class="grid grid-cols-3 gap-6">
        <Item v-for="course in popularCourses" :key="course.courseId" :course="course" />
      </div>
     
    </div>
    <div class="flex flex-col w-full mt-10 mx-auto space-y-4">
      <p class="font-semibold text-3xl relative w-fit h-10 flex flex-col justify-end">Free Course
      </p>
      <div class="grid grid-cols-3 gap-6">
        <Item v-for="course in allCourses " :key="course.id" :course="course" />
      </div>
     
    </div>
  </div>
</template>
<script lang="ts" setup>
import Navigation from "../components/Navigation.vue";
import Item from "../components/Course/Item.vue";
import SideBar from "../components/SideBar.vue";
import { Course } from "../model/course";
import { api } from "../services/http-common";
import {ref, Ref, onBeforeMount} from"vue"
import { useAuthStore } from "../store/authStore";
const authStore = useAuthStore();
const popularCourses : Ref<Array<Course>> = ref([])
const allCourses :  Ref<Array<Course>> = ref([])
const myCourses = ref();

const getCoursePopular = async ()=>{
 try{
    const data = await api.get('/courses/popular-courses') ;
    console.log(data.data)
    popularCourses.value = data.data
 }
 catch(e){
  console.error(e)
 }
}
const getAllCourses = async ()=>{
 try{
    const data = await api.get('/courses') ;
    console.log(data.data)
    allCourses.value = data.data.content
    
 }
 catch(e){
  console.error(e)
 }
}
const getMyCourses = async ()=>{
  if(authStore.isAuthorized){
    try {
    const {data} = await api.get(`/courses/student-courses/${authStore.id}`);
    myCourses.value = data.courses.content;
  }
  catch(e){
    console.error(e)
  }
  }

}
onBeforeMount(() => {
  getCoursePopular()
  getAllCourses()
  getMyCourses()
})
</script>
