<template>
  <section>
    <div
      class="flex flex-wrap z-50 items-center justify-between py-4 px-10 border-b-gray-400 border-b w-full"
    >
      <a href="#" class="flex items-center">
        <img
          src="https://flowbite.com/docs/images/logo.svg"
          class="h-8 mr-3"
          alt="Flowbite Logo"
        />
        <router-link
          :to="{ name: 'Home' }"
          class="self-center text-2xl font-semibold whitespace-nowrap dark:text-white"
          >Study and test online
        </router-link>
      </a>

      <!-- <form class="rounded-xl w-1/4">
        <label
          for="default-search"
          class="mb-2 text-sm font-medium text-gray-900 sr-only dark:text-white"
          >Search</label
        >
        <div class="relative">
          <div
            class="absolute inset-y-0 left-0 flex items-center pl-3 pointer-events-none"
          >
            <svg
              class="w-4 h-4 text-gray-500 dark:text-gray-400"
              aria-hidden="true"
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 20 20"
            >
              <path
                stroke="currentColor"
                stroke-linecap="round"
                stroke-linejoin="round"
                stroke-width="2"
                d="m19 19-4-4m0-7A7 7 0 1 1 1 8a7 7 0 0 1 14 0Z"
              />
            </svg>
          </div>
          <input
            type="search"
            id="default-search"
            class="block w-full p-4 pl-10 text-sm text-gray-900 border border-gray-300 rounded-lg bg-gray-50 focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
            placeholder="Course search. articles, documents..."
            required
          />
          <button
            type="submit"
            class="text-white absolute right-2.5 bottom-2.5 bg-gradient-to-r from-indigo-500 from-10% via-sky-500 via-30% to-emerald-500 to-90% hover:bg-blue-500 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-xl text-sm px-4 py-2"
          >
            Search
          </button>
        </div>
      </form> -->
      <div v-if="!authStore.isAuthorized">
        <router-link
          :to="{ name: 'Login' }"
          class="text-white bg-gradient-to-r from-sky-800 from-10% to-purple-700 hover:bg-blue-500 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-xl text-sm px-10 py-3"
        >
          Login
        </router-link>
      </div>
      <div v-else class="flex flex-row cursor-pointer items-center space-x-2">
        <router-link
          :to="{ name: 'Profile' }"
          class="flex flex-row items-center space-x-2"
        >
          <img
            :src="'http://localhost:8080/api/v1/file/upload/' + authStore.image"
            class="w-10 h-10 rounded-full"
          />
          <p class="text-xl">{{ authStore.fullName }}</p>
        </router-link>
      </div>
    </div>
  </section>
</template>
<script lang="ts" setup>
import { useAuthStore } from "../store/authStore";
const authStore = useAuthStore();
// const goToProfilePage = () => {
//   this.$router.push({ name: 'profile' }); // N?u b?n �ang s? d?ng Vue Router
// };
</script>
