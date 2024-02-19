<template>
  <Navigation />
  <SideBar />
  <div class="flex flex-col w-3/4 mx-auto">
    <div class="flex flex-row justify-between">
      <h3 class="inline font-bold text-3xl">Blogs</h3>
      <router-link
        v-if="authStore.isAuthorized"
        :to="{ name: 'Blogs.Create' }"
        class="px-5 py-2 bg-lightblue rounded-xl text-white font-semibold flex flex-row text-center items-center"
      >
        <Icon class="w-5 h-5 mr-2 stroke-slate-800" name="plus" />
        Create blog
      </router-link>
    </div>
    <p>
      Compilation of articles sharing experiences in self-learning programming
      online and programming techniques.
    </p>
    <div class="flex flex-col w-full mt-10 mx-auto space-y-4">
      <Item v-for="blog in allBlogs" :key="Number(blog.blogId)" :blog="blog" />
    </div>
  </div>
  <Pagination
    @get-list="getList"
    :last-page="response.last_page"
    :current-page="response.current_page"
  />
</template>
<script lang="ts" setup>
import Navigation from "../components/Navigation.vue";
import Item from "../components/Blog/Item.vue";
import SideBar from "../components/SideBar.vue";
import { Blog } from "../model/blog";
import { api } from "../services/http-common";
import { ref, Ref, onBeforeMount, onMounted } from "vue";
import Icon from "../icons/ClientDashboard.vue";
import Pagination from "./Pagination.vue";
import { useAuthStore } from "../store/authStore";
const authStore = useAuthStore();
const allBlogs: Ref<Array<Blog>> = ref([]);
interface SearchForm {
  page: number;
  size: number;
}
const searchForm: Ref<SearchForm> = ref({
  page: 1,
  size: 5,
});
interface ResponseData {
  last_page: number;
  current_page: number;
}
const response: Ref<ResponseData> = ref({
  last_page: 1,
  current_page: 0,
});
const getAllBlogs = async () => {
  try {
    const data = await api.get("/blogs");

    allBlogs.value = data.data.content;
    console.log(allBlogs.value);
  } catch (e) {
    console.error(e);
  }
};

const getList = async (page: number = 0) => {
  try {
    searchForm.value.page = page;
    // @ts-ignore
    const params = new URLSearchParams(searchForm.value).toString();
    let data = await api.get(`/blogs?${params}`);
    allBlogs.value = data.data.content;
    response.value.current_page = data.data.number;
    response.value.last_page = data.data.totalPages - 1;
  } catch (e) {}
};
onBeforeMount(async () => {
  await getAllBlogs();
});
onMounted(() => {
  getList();
});
</script>
