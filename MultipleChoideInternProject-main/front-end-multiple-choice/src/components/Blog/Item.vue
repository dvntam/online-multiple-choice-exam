<script lang="ts" setup>
import { Blog } from "../../model/blog";
interface Props {
  blog: Blog;
}
defineProps<Props>();
function removeTags(str) {
  if (str === null || str === "") return false;
  else str = str.toString();
  return str.replace(/(<([^>]+)>)/gi, "");
}
</script>
<template>
  <div class="flex flex-row bg-white rounded-xl border p-5 shadow-lg w-3/4">
    <div class="flex flex-col w-3/4 space-y-4">
      <div class="flex flex-row items-center space-x-4">
        <img
          class="w-10 h-10 rounded-full"
          :src="'http://localhost:8080/api/v1/file/upload/' + blog.imageUser"
        />
        <p class="capitalize font-semibold hover:underline">
          {{ blog.fullName }}
        </p>
      </div>
      <router-link
        :to="{ name: 'Blogs.Detail', params: { id: Number(blog.blogId) } }"
        class="font-bold text-2xl hover:underline line-clamp-2"
      >
        {{ blog.titleBlog }}
      </router-link>
      <p class="line-clamp-2 w-4/5">
        {{ removeTags(blog.contentBlog) }}
      </p>
    </div>
    <img
      class="rounded-xl w-1/4"
      src="data:image/jpeg;base64,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"
    />
  </div>
</template>
