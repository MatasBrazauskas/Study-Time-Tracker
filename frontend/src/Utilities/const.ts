export const GOOGLE_CLIENT_ID: string = import.meta.env.VITE_GOOGLE_CLIENT_ID;
export const USER_PROFILE_CONTROLLER_URL:string = import.meta.env.VITE_USER_PROFILE_CONTROLLER_URL;
export const USER_ACTIVITY_CONTROLLER_URL: string = import.meta.env.VITE_USER_YEAR_CONTROLLER_URL;

export const REGISTER_CONTROLLER_URL = `${USER_PROFILE_CONTROLLER_URL}/register`;
export const LOG_IN_CONTROLLER_URL = `${USER_PROFILE_CONTROLLER_URL}/logIn`;

export const urlParameterization = (baseUrl: string, ...arr: string[]) => {
    let newUrl = baseUrl;
    for(const i in arr){
        newUrl += `/${i}`;
    }
    return newUrl;
}  


export const USERS_DATA_SLICES_NAME = 'usersData';
export const ERRORS_SLICES_NAME = 'errorsData';
export const ACTIVITY_SLICES_NAME = 'activityData';


export const USER_PROFILE_QUERY_KEY = 'userProfile';
export const USER_YEAR_ACTIVITY_QUERY_KEY = 'usersActivity';