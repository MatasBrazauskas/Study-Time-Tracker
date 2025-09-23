export const GOOGLE_CLIENT_ID: string = import.meta.env.VITE_GOOGLE_CLIENT_ID;

export const USER_PROFILE_CONTROLLER_URL:string = import.meta.env.VITE_USER_PROFILE_CONTROLLER_URL;
export const REGISTER_CONTROLLER_URL = `${USER_PROFILE_CONTROLLER_URL}/register`;
export const LOG_IN_CONTROLLER_URL = `${USER_PROFILE_CONTROLLER_URL}/logIn`;

export const USERS_DATA_SLICES_NAME = 'usersData';

import axios from "axios";
export const axiosDefaultSetUp = () => {
    axios.defaults.withCredentials = true;
};