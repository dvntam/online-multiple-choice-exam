<script lang="ts" setup>
import { useRoute, useRouter } from "vue-router";
import { Blog } from "../../../model/blog";
import Navigation from "../../Navigation.vue";
import SideBar from "../../SideBar.vue";
import ClassicEditor from "@ckeditor/ckeditor5-build-classic";
import { api } from "../../../services/http-common";
import { ref, onBeforeMount } from "vue";
import { useNotificationStore } from "../../../store/notificationStore";
const notificationStore = useNotificationStore();
import { useVuelidate } from "@vuelidate/core";
import { required, minLength, maxLength } from "@vuelidate/validators";
const $externalResults = ref({});
const form = ref({
  blogId: 0,
  titleBlog: "",
  contentBlog: "",
  numberVisitors: "",
  user: {
    userId: 1,
    username: "tom.denesik",
    userPass: "password",
    fullName: "Vandervort",
    email: "rayna.gulgowski@yahoo.com",
    phone: "0867987223",
    address: "Suite 810 5389 Andre Rapid, Hahnborough, WA 18463-3193",
    imageUser: "http://lorempixel.com/320/200/nature/",
    role: null,
  },
});
const rules = {
  titleBlog: { required, minLength: minLength(10), maxLength: maxLength(256) },
};
const validate = useVuelidate(rules, form, { $externalResults });

const validationMessage = (error: any, text: string) => {
  if (error) {
    return error.replace("Value", text);
  }
  return error;
};
const validateFormBlog = () => {
  validate.value.$clearExternalResults();
  validate.value.$touch();
  if (validate.value.$invalid) {
    notificationStore.openError("Check information field.");
    return;
  }
};
const route = useRoute();
const router = useRouter();
const getblogById = async () => {
  try {
    const data = await api.get(`/blogs/${route.params.id}`);
    form.value = data.data;
    // console.log(form.value);
  } catch (e) {
    console.error(e);
  }
};
const updateBlog = async () => {
  try {
    const { data } = await api.put(
      `/blogs/user-blog/update/blogId=${form.value.blogId}`,
      form.value
    );
    notificationStore.openSuccess("Update success.");
    await router.push("/blog/index");
  } catch (e) {
    console.error(e);
  }
};
onBeforeMount(() => {
  getblogById();
});
</script>

<template>
  <Navigation />
  <SideBar />
  <div class="flex flex-col justify-center container">
    <form
      @submit.prevent="updateBlog"
      @keydown="validateFormBlog"
      class="px-10 mt-20 py-10 border flex flex-col justify-center space-y-4 text-center rounded-xl"
    >
      <p class="uppercase text-2xl font-bold">Blog</p>
      <div class="flex flex-col items-start">
        <label class="text-lg font-semibold">Title</label>
        <input
          v-model="form.titleBlog"
          class="w-1/2 h-10 rounded-xl border-2 px-2 border-black"
          type="text"
        />
        <template v-if="validate.titleBlog.$error">
          <div
            v-for="(error, index) in validate.titleBlog.$errors"
            :key="index"
            class="text-red-500 mt-2 italic text-sm"
          >
            {{ validationMessage(error.$message, "Title blog") }}
          </div>
        </template>
      </div>
      <ckeditor v-model="form.contentBlog" :editor="ClassicEditor"></ckeditor>
      <button class="text-white bg-lightblue px-5 py-2 w-32 rounded-xl">
        Update
      </button>
    </form>
  </div>
</template>
<style>
.ck-editor__editable_inline {
  min-height: 600px;
}
</style>
