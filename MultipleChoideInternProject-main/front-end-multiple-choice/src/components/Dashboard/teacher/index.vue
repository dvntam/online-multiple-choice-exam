<template>
  <Navigation />
  <SideBar />
  <div class="container flex flex-col">
    <router-link
      :to="{ name: 'Dashboard.Teacher.Create' }"
      class="w-40 capitalize py-3 bg-gradient-to-r from-sky-800 from-10% to-purple-700 rounded-xl text-white font-semibold text-center"
    >
      Add Teacher
    </router-link>
    <table class="w-full border-slate-400">
      <caption class="caption-top uppercase text-3xl font-bold">
        List Teacher
      </caption>
      <thead
        class="bg-gradient-to-r from-sky-800 from-10% to-purple-700 text-white h-12 rounded-xl border-t-2"
      >
        <tr class="uppercase">
          <th scope="col" class="border border-slate-300 col-span-1">ID</th>
          <th scope="col" class="border border-slate-300">Full Name</th>
          <th scope="col" class="border border-slate-300">Email</th>
          <th scope="col" class="border border-slate-300">Address</th>
          <th scope="col" class="border border-slate-300">Image</th>
          <th scope="col" class="border border-slate-300">Username</th>
          <th scope="col" class="border border-slate-300">Phone</th>
        </tr>
      </thead>
      <tbody class="boder-2 border-gray-300 overflow-hidden">
        <tr
          v-for="teacher in allTeacher"
          :key="Number(teacher.id)"
          class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600 text-justify"
        >
          <td class="w-20 px-5">{{ teacher.id }}</td>
          <td class="w-80">
            {{ teacher.fullName }}
          </td>
          <td class="line-clamp-4 px-5">
            {{ teacher.email }}
          </td>
          <td class="w-52 px-5">
            {{ teacher.address }}
          </td>
          <td class="w-52 px-5">
            <img
              :src="
                'http://localhost:8080/api/v1/file/upload/' +
                teacher.imageTeacher
              "
              alt="imgae-user"
            />
          </td>
          <td class="w-52 px-5">
            {{ teacher.username }}
          </td>
          <td class="w-52 px-5">
            {{ teacher.phone }}
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>
<script lang="ts" setup>
import Navigation from "../../Navigation.vue";
import SideBar from "../../Dashboard/SideBarDashboard.vue";
import { api } from "../../../services/http-common";
import { ref, Ref, onBeforeMount } from "vue";
const allTeacher: Ref<Array<Blog>> = ref([]);
const getAllBlogs = async () => {
  try {
    const data = await api.get("/user-connect/teacher/list");
    console.log(data);
    allTeacher.value = data.data.data;
  } catch (e) {
    console.error(e);
  }
};

onBeforeMount(() => {
  getAllBlogs();
});
</script>
