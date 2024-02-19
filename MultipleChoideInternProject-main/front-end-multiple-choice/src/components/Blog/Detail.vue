<template>
  <Navigation />
  <SideBar />
  <div class="flex flex-col w-3/4 pt-10 mx-auto container space-y-4 text-start">
    <h1 class="font-semibold text-3xl">{{ blog.titleBlog }}</h1>

    <div class="flex flex-row items-center space-x-4 justify-between">
      <div class="flex flex-row items-center space-x-2">
        <img
          :src="'http://localhost:8080/api/v1/file/upload/' + blog.imageUser"
          class="w-14 h-14 rounded-full"
        />
        <p class="font-semibold">{{ blog.fullName }}</p>
      </div>
      <template v-if="authStore.isAuthorized">
        <router-link
          v-if="authStore.id == blog.userId"
          :to="{ name: 'Blogs.Update', params: { id: blog.blogId } }"
          class="w-40 capitalize py-3 bg-gradient-to-r from-sky-800 from-10% to-purple-700 rounded-xl text-white font-semibold text-center"
        >
          Update blog
        </router-link>
      </template>
    </div>
    <span v-html="blog.contentBlog"></span>
  </div>
  <div></div>
</template>
<script lang="ts" setup>
import Navigation from "../Navigation.vue";
import { useRoute } from "vue-router";
import SideBar from "../SideBar.vue";
import { Blog } from "../../model/blog";
import { ref, Ref, onBeforeMount } from "vue";
import { api } from "../../services/http-common";
import { useAuthStore } from "../../store/authStore";
const authStore = useAuthStore();
const route = useRoute();
const blog: Ref<Blog> = ref({});
const getBlogDetail = async () => {
  try {
    const { data } = await api.get(`/blogs/${route.params.id}`);
    blog.value = data;
  } catch (e) {
    console.error(e);
  }
};
onBeforeMount(async () => {
  await getBlogDetail();
});
</script>
