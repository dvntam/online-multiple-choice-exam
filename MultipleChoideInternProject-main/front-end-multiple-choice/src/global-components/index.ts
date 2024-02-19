import Notification from "./notification/Main.vue";
import { App } from "vue";
export default (app: App) => {
  app.component("Notification", Notification);
};
