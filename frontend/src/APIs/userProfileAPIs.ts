import { axiosAuth } from "../Utilities/fetchSettings";

import { USER_PROFILE_CONTROLLER_URL } from "../Utilities/const";
import { LOG_IN_CONTROLLER_URL, REGISTER_CONTROLLER_URL} from "../Utilities/const";
import { type UserProfileOutput, type UserCredentials } from '../Utilities/types';

export async function getUsersProfile(): Promise<UserProfileOutput>{
    const response = await axiosAuth.get(USER_PROFILE_CONTROLLER_URL);
    return response.data;
}

export async function RegisterUser(data: UserCredentials) : Promise<UserProfileOutput>
{
    const response = await axiosAuth.post(REGISTER_CONTROLLER_URL, data);
    return response.data;
}

export async function LogInUser(data: UserCredentials): Promise<UserProfileOutput>{
    const response = await axiosAuth.post(LOG_IN_CONTROLLER_URL, data);
    return response.data;
}

export async function deleteUser(): Promise<void>{
    await axiosAuth.delete(USER_PROFILE_CONTROLLER_URL);
}