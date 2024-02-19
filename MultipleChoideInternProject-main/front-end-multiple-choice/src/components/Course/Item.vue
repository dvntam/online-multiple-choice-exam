<script lang="ts" setup>
import { Course } from "../../model/course";
import Icon from "../../icons/ClientDashboard.vue";
interface Props {
  course: Course;
}
const prop = defineProps<Props>();
</script>
<template>
  <div class="flex flex-col space-y-2 rounded-xl shadow-xl">
    <div class="group">
      <div
        class="bg-cover overflow-hidden bg-center bg-no-repeat h-80 rounded-xl relative flex items-center justify-center"
        :style="`background-image: url('${prop.course.imageCourse}');`"
      >
        <div
          class="group-hover:block hidden absolute bg-black opacity-50 w-full h-full"
        >
          <router-link
            :to="{
              name: 'Courses.Detail',
              params: { id: `${prop.course.courseId}` },
            }"
            class="bg-white rounded-xl absolute hidden group-hover:block py-2 px-4 cursor-pointer"
          >
            Watch more
          </router-link>
        </div>
      </div>
    </div>

    <router-link
      class="text-2xl font-bold f capitalize hover:underline"
      :to="{
        name: 'Courses.Detail',
        params: { id: `${prop.course.courseId}` },
      }"
    >
      {{ course.courseName }}</router-link
    >

    <p class="text-gray-500 flex flex-row items-center font-semibold space-x-2">
      <Icon name="groupuser" class="w-8 h-8" />
      {{ course.numberStudent }}
    </p>
  </div>
</template>
