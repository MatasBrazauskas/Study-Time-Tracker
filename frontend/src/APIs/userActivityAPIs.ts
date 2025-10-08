import { USER_ACTIVITY_CONTROLLER_URL } from "../Utilities/const";
import { axiosAuth } from "../Utilities/fetchSettings";

import { type ActivityState } from "../Utilities/types";

export async function getYearsActivity(year: number, seconds: number): Promise<ActivityState>{
    const response = await axiosAuth.get(`${USER_ACTIVITY_CONTROLLER_URL}/${year}/${seconds}`);
    return response.data;
}

