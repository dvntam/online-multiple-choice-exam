import { Role } from "./role";
export interface User {
  userId: number;
  address: string;
  email: string;
  fullName: string;
  imageUser: string;
  phone: string;
  username: string;

  // role: Array<Role>;
}
