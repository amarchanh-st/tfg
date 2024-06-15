import { Comment } from "./chat";
import { User } from "./user";

export class Budget {
    id!: number;
    title!: string;
    brand!: string;
    model!: string;
    description!: string;
    price?: string;
    status!: string;
    user! : User;
    images?: string[];
    comments?: Comment[];
    estimatedDate?: Date;
    creationDate?: Date;
}