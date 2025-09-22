import axios from "axios";
import { USER_PROFILE_CONTROLLER_URL } from "../Utilities/const";

async function LogInUser(data: any) : Promise<null>
{
    try{
        const usersInformation = axios.post(USER_PROFILE_CONTROLLER_URL, data);

    } catch(e){
        console.error(e);
    }

    return null;
}