<template>
  <Navigation />
  <div class="container flex flex-col">
    <table class="w-full border-slate-400">
      <caption class="caption-top uppercase text-3xl font-bold">
        List Blog
      </caption>
      <thead
        class="bg-gradient-to-r from-sky-800 from-10% to-purple-700 text-white h-12 rounded-xl border-t-2"
      >
        <tr class="uppercase">
          <th scope="col" class="border border-slate-300 col-span-1">ID</th>
          <th scope="col" class="border border-slate-300">Title</th>
          <th scope="col" class="border border-slate-300">Content</th>
          <th scope="col" class="border border-slate-300">Views</th>
          <th scope="col" class="border border-slate-300">Action</th>
        </tr>
      </thead>
      <tbody class="boder-2 border-gray-300 overflow-hidden">
        <tr
          v-for="blog in allBlogs"
          :key="Number(blog.blogId)"
          class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600 text-justify"
        >
          <td class="w-20 px-5">
            <router-link
              :to="{
                name: 'Blogs.Detail',
                params: { id: Number(blog.blogId) },
              }"
              >{{ blog.blogId }}
            </router-link>
          </td>
          <td class="w-80">
            {{ blog.titleBlog }}
          </td>
          <td class="line-clamp-4 px-5">
            {{ blog.contentBlog }}
          </td>
          <td class="w-52 px-5">
            {{ blog.numberVisitors }}
          </td>
          <td class="w-60">
            <div class="flex flex-row space-x-4 w-full justify-center">
              <router-link
                :to="{
                  name: 'Dashboard.Blog.Update',
                  params: { id: Number(blog.blogId) },
                }"
              >
                <Icon
                  name="edit"
                  class="w-6 h-6 stroke-green-400 cursor-pointer"
                />
              </router-link>
              <Icon
                @click="deleteBlogItem(blog.blogId)"
                name="delete"
                class="w-6 h-6 fill-red-500 cursor-pointer"
              />
            </div>
          </td>
        </tr>
      </tbody>
    </table>
    <Pagination
      @get-list="getList"
      :last-page="response.last_page"
      :current-page="response.current_page"
    />
    <ModalsContainer />
  </div>
</template>
<script lang="ts" setup>
import Navigation from "../../Navigation.vue";
import { Blog } from "../../../model/blog";
import { api } from "../../../services/http-common";
import { ref, Ref, onBeforeMount, onMounted } from "vue";
import Icon from "../../../icons/ClientDashboard.vue";
import { ModalsContainer, useModal } from "vue-final-modal";
import ModalConfirm from "../../modals/ModalConfirm.vue";
import Pagination from "../../Pagination.vue";
const allBlogs: Ref<Array<Blog>> = ref([]);
const currentIdBlog: Ref<Number | undefined> = ref(undefined);
const deleteConfirmationModal: Ref<boolean> = ref(false);
import { useNotificationStore } from "../../../store/notificationStore";
const notificationStore = useNotificationStore();
interface ResponseData {
  last_page: number;
  current_page: number;
}
interface SearchForm {
  page: number;
  size: number;
}
const response: Ref<ResponseData> = ref({
  last_page: 1,
  current_page: 0,
});
const searchForm: Ref<SearchForm> = ref({
  page: 0,
  size: 5,
});
const { open, close } = useModal({
  component: ModalConfirm,
  attrs: {
    title: "Xác nhận!",

    async onConfirm() {
      try {
        const data = await api.delete(
          `/blogs/user-blogs/delete/${currentIdBlog.value}`
        );

        await getAllBlogs();
        notificationStore.openSuccess("Delete Blog success.");
        close();
      } catch (e) {
        console.error(e);
      }
    },
    onReject() {
      currentIdBlog.value = undefined;
      deleteConfirmationModal.value = false;
      close();
    },
  },
  slots: {
    default: `<p class="text-center font-semibold text-xl">Bạn có chắc chắn muốn xóa</p>`,
  },
});
const deleteBlogItem = (id: Number) => {
  currentIdBlog.value = id;
  open();
};
const getAllBlogs = async () => {
  try {
    const data = await api.get("/blogs");
    console.log(data);
    allBlogs.value = data.data.content;
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
onBeforeMount(() => {
  getAllBlogs();
});
onMounted(() => {
  getList();
});
</script>
