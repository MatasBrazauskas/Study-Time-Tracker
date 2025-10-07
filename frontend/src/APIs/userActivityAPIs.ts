import { USER_ACTIVITY_CONTROLLER_URL } from "../Utilities/const";
import { axiosAuth } from "../Utilities/fetchSettings";

export async function getYearsActivity(year: number, seconds: number){
    const response = await axiosAuth.get(`${USER_ACTIVITY_CONTROLLER_URL}/${year}/${seconds}`);
    return response.data;
}

