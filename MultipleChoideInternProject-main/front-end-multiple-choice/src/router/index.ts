import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";
import { useAuthStore } from "../store/authStore";
import { useNotificationStore } from "../store/notificationStore";
const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    name: "Home",
    component: () => import("../components/HomePage.vue"),
  },
  {
    path: "/home",
    beforeEnter: (to, from, next) => {
      const authStore = useAuthStore();
      if (authStore.isAuthorized) {
        if (authStore.isUser) {
          window.location.assign("/course");
        } else {
          window.location.assign("/dashboard");
        }
        return false;
      }
      return next();
    },
    component: () => import("../components/HomePage.vue"),
  },
  {
    path: "/login",
    name: "Login",
    component: () => import("../components/Login.vue"),
  },
  {
    path: "/register",
    name: "Register",
    component: () => import("../components/Register.vue"),
  },
  {
    path: "/dashboard",
    name: "Dashboard",
    beforeEnter: (to, from, next) => {
      const notificationStore = useNotificationStore();
      const authStore = useAuthStore();
      if (authStore.isAuthorized) {
        if (authStore.isUser) {
          console.log(authStore.isUser);
          notificationStore.openError("Bạn không thể truy cập trang này");
          window.location.assign(from.path);
        }
        return next();
      }
      return window.location.assign("/login");
    },
    children: [
      {
        path: "",
        name: "Dashboard.Index",
        component: () => import("../components/Dashboard/dashboard.vue"),
      },
      {
        path: "/blog/index",
        name: "Dashboard.Blog.Index",
        component: () => import("../components/Dashboard/blog/index.vue"),
      },
      {
        path: ":id/update",
        name: "Dashboard.Blog.Update",
        component: () => import("../components/Dashboard/blog/update.vue"),
      },
      {
        path: "/course/index",
        name: "Dashboard.Course.Index",
        component: () => import("../components/Dashboard/course/index.vue"),
      },
      {
        path: "/course/create",
        name: "Dashboard.Course.Create",
        component: () => import("../components/Dashboard/course/create.vue"),
      },
      {
        path: ":id/update",
        name: "Dashboard.Course.Update",
        component: () => import("../components/Dashboard/course/update.vue"),
      },
      {
        path: ":subject/question/create",
        name: "Dashboard.Question.Create",
        component: () => import("../components/Dashboard/question/create.vue"),
      },
      {
        path: "subject/:subject",
        name: "Dashboard.Question.Index",
        component: () => import("../components/Dashboard/question/index.vue"),
      },
      {
        path: ":idcourse/exam/create",
        name: "Dashboard.Exam.Create",
        component: () => import("../components/Dashboard/exam/create.vue"),
      },
      {
        path: ":id/exam/addquestion/:subject",
        name: "Dashboard.Exam.AddQuestion",
        component: () => import("../components/Dashboard/exam/addquestion.vue"),
      },
      {
        path: "exam/detail",
        name: "Dashboard.Exam.Detail",
        component: () => import("../components/Dashboard/exam/detail.vue"),
      },
      {
        path: "exam/list",
        name: "Dashboard.Exam.List",
        component: () => import("../components/Dashboard/exam/list.vue"),
      },
      {
        path: "teacher",
        name: "Dashboard.Teacher.Index",
        component: () => import("../components/Dashboard/teacher/index.vue"),
      },
      {
        path: "teacher/add",
        name: "Dashboard.Teacher.Create",
        component: () => import("../components/Dashboard/teacher/add.vue"),
      },
    ],
  },
  {
    path: "/blog",
    name: "Blogs",
    meta: {
      title: "Blog",
    },
    children: [
      {
        path: "",
        name: "Blogs.Index",
        component: () => import("../components/Blog.vue"),
      },
      {
        path: ":id/detail",
        name: "Blogs.Detail",
        component: () => import("../components/Blog/Detail.vue"),
      },
      {
        path: "/create",
        name: "Blogs.Create",
        component: () => import("../components/Blog/Create.vue"),
      },
      {
        path: ":id/update",
        name: "Blogs.Update",
        component: () => import("../components/Dashboard/blog/update.vue"),
      },
    ],
  },
  {
    path: "/exam",
    name: "Exam",
    component: () => import("../components/Exam.vue"),
  },
  {
    path: "/exam/:id/taking-exam",
    name: "Exam.TakeExam",
    component: () => import("../components/Dashboard/exam/takingexam.vue"),
  },
  {
    path: "/:id/result-exam",
    name: "Exam.ReultExam",
    component: () => import("../components/Exam/ResultExam.vue"),
  },
  {
    path: "/course",
    name: "Courses",
    meta: {
      title: "Courses",
    },
    children: [
      {
        path: "",
        name: "Courses.Index",
        component: () => import("../components/Coure.vue"),
        meta: {
          title: "Courses",
        },
      },
      {
        path: ":id",
        name: "Courses.Detail",
        component: () => import("../components/Course/Detail.vue"),
        meta: {
          title: "Courses",
        },
      },
      {
        path: "/room",
        name: "Room",
        component: () => import("../components/Course/Room.vue"),
      },
    ],
  },
  {
    path: "/regiter",
    component: () => import("../components/Register.vue"),
  },
  {
    path: "/profile/user/:id",
    name: "Profile",
    component: () => import("../components/Profile.vue"),
  },
  {
    path: "/profile/user/",
    name: "Profile",
    component: () => import("../components/Profile.vue"),
  },
];
const router = createRouter({
  history: createWebHistory(),
  routes,
  scrollBehavior(to, from, savedPosition) {
    // always scroll to top
    return { top: 0 };
  },
});

export default router;
