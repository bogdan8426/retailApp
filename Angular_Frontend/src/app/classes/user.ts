import { Role } from "./role";
import {Address} from "./address";
import { Product } from "./product";

export class User {
  usersId: number;
	email: string;
	firstName: string;
	lastName: string;
	password : string;
	phoneNumber: string;
	role: Role;
	addresses: Address[]; 
	products: Product[]
}
