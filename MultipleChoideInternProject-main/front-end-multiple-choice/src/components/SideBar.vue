<template>
  <div class="relative w-1/6 z-50">
    <div class="fixed bg-white">
      <ul class="space-y-6 p-4 text-center flex flex-col">
        <!-- <router-link
          :to="{ name: 'Home' }"
          class="flex flex-col text-[12px] space-y-2 hover:bg-slate-200 py-2 px-6 rounded-xl"
        >
          <Icon name="plus" class="w-8 h-8" />
        </router-link> -->
        <router-link
          :to="{ name: 'Home' }"
          class="flex flex-col text-[12px] space-y-2 hover:bg-slate-200 py-2 px-6 rounded-xl"
          :class="route.name === 'Home' ? 'bg-slate-400' : ''"
        >
          <Icon name="home" class="w-8 h-8 mx-auto" />
          <p>Home</p>
        </router-link>

        <router-link
          :to="{ name: 'Courses.Index' }"
          class="flex flex-col text-[12px] space-y-2 hover:bg-slate-200 py-2 px-6 rounded-xl"
          :class="route.name === 'Courses.Index' ? 'bg-slate-400' : ''"
        >
          <Icon name="course" class="w-8 h-8 mx-auto" />
          <p>Course</p>
        </router-link>
        <router-link
          :to="{ name: 'Exam' }"
          class="flex flex-col text-[12px] space-y-2 hover:bg-slate-200 py-2 px-6 rounded-xl"
          :class="route.name === 'Exam' ? 'bg-slate-400' : ''"
        >
          <Icon name="exam" class="w-8 h-8 mx-auto" />
          <p>Exam</p>
        </router-link>
        <router-link
          :to="{ name: 'Blogs.Index' }"
          class="flex flex-col text-[12px] space-y-2 hover:bg-slate-200 py-2 px-6 rounded-xl"
          :class="route.name === 'Blog' ? 'bg-slate-400' : ''"
        >
          <Icon name="blog" class="w-8 h-8 mx-auto" />
          <p>Blog</p>
        </router-link>
        <router-link
          v-if="authStore.isAuthorized && authStore.isTeacher"
          :to="{ name: 'Dashboard.Index' }"
          class="flex flex-col text-[12px] space-y-2 hover:bg-slate-200 py-2 px-6 rounded-xl"
          :class="route.name === 'Dashboard' ? 'bg-slate-400' : ''"
        >
          <Icon name="management" class="w-8 h-8 mx-auto" />
          <p>Management</p>
        </router-link>
        <router-link
          v-else
          :to="{ name: 'Dashboard.Index' }"
          class="hidden"
          :class="route.name === 'Dashboard' ? 'bg-slate-400' : ''"
        >
        </router-link>

        <li
          v-if="authStore.isAuthorized"
          @click="authStore.logOut"
          class="flex flex-col text-[12px] space-y-2 hover:bg-slate-200 py-2 px-6 rounded-xl"
        >
          <Icon name="logout" class="w-8 h-8 mx-auto" />
          <p>Logout</p>
        </li>
        <li v-else></li>
      </ul>
    </div>
  </div>
</template>
<script lang="ts" setup>
import Icon from "../icons/ClientDashboard.vue";
import { useRoute } from "vue-router";
import { useAuthStore } from "../store/authStore";
const authStore = useAuthStore();
const route = useRoute();
</script>
