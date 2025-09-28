import { USER_ACTIVITY_CONTROLLER_URL } from "../Utilities/const";
import { axiosAuth } from "../Utilities/fetchSettings";
import { urlParameterization } from "../Utilities/const";

export async function getYearsActivity(year: number){
    const response = await axiosAuth.get(urlParameterization(USER_ACTIVITY_CONTROLLER_URL, String(year)));
    return response.data;
}

