<script lang="ts" setup>
import Navigation from "../../components/Navigation.vue";
import SideBar from "../../components/SideBar.vue";
import ClassicEditor from "@ckeditor/ckeditor5-build-classic";
import { api } from "../../services/http-common";
import { ref, Ref, onBeforeMount } from "vue";
const form = ref({
  titleBlog: "",
  contentBlog: "",
  numberVisitors: "",
  userId: 0,
  fullName: "",
  imageUser: " ",
});
const createBlog = async () => {
  const data = api.post("/blogs/add", form.value);
  console.log(data);
};
</script>
<template>
  <Navigation />
  <SideBar />
  <div class="flex flex-col justify-center container">
    <form
      @submit.prevent="createBlog"
      class="px-10 mt-20 py-10 border flex flex-col justify-center space-y-4 text-center rounded-xl"
    >
      <p class="uppercase text-2xl font-bold">Tạo bài viết</p>
      <div class="flex flex-col">
        <label>Tiêu đề khóa học</label>
        <input
          v-model="form.titleBlog"
          class="w-40 border border-black"
          type="text"
          required
        />
      </div>
      <ckeditor v-model="form.contentBlog" :editor="ClassicEditor"></ckeditor>
      <button class="text-white bg-lightblue px-5 py-2 w-32 rounded-xl">
        Tao
      </button>
    </form>
  </div>
</template>
<style>
.ck-editor__editable_inline {
  min-height: 600px;
}
</style>
